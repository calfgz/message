package com.qtt.sms.service.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qtt.sms.model.Agent;
import com.qtt.sms.model.FailRecord;
import com.qtt.sms.model.SendRecord;
import com.qtt.sms.model.SendRecordExample;
import com.qtt.sms.service.AgentService;
import com.qtt.sms.service.FailRecordService;
import com.qtt.sms.service.SendRecordService;
import com.qtt.sms.util.CodeUtil;
import com.qtt.sms.util.T;

@Service
public class ApiService {
    private static Log log = LogFactory.getLog(ApiService.class);
	@Autowired
	AgentService agentService;
	
	@Autowired
	QingMaYunService qingMaYunService;
	
	@Autowired
	SendRecordService sendRecordService;
	
	@Autowired
	FailRecordService failRecordService;

    
    //接收请求直接发送
    public JSONObject sendSmsNotTemplate(int agentId, String phones, String content){
        JSONObject json = new JSONObject();
        try {
            String resultContent = qingMaYunService.sendContentSms(phones, content);
            log.info("sendapi phone: " + phones + " content: " + content + " result: " + resultContent);
            //log.error("sendapi phone: " + phoneString + " content: " + content + " result: " + resultContent);
            JSONObject result = JSONObject.parseObject(resultContent);
            if (result != null) {
                String respCode = result.getString("respCode");
                if (respCode.equals("00000")) {
                    String smsid = result.getString("smsId");  
                    //发送记录
                    SendRecord record = new SendRecord();
                    record.setAgentId(agentId);
                    record.setSmsid(smsid);
                    record.setQueryStatus(0);
                    record.setCount(0);
                    record.setContent(content);
                    record.setPhones(phones);
                    record.setRespStatus(-1);
                    record.setFailCount(result.getIntValue("failCount"));
                    record.setSendAt(T.dateValue(result.getString("createTime"), "yyyyMMdd HH:mm:ss", new Date()));
                    sendRecordService.save(record);

                    json.put("code", 0);
                    json.put("msg", "success");
                    json.put("smsid", smsid);
                } else {
                    //失败记录
                    FailRecord failRecord = new FailRecord();
                    failRecord.setAgentId(agentId);
                    failRecord.setContent(content);
                    failRecord.setPhones(phones);
                    int msc = phones.split(",").length * T.getLength(content);;
                    failRecord.setCount(msc);
                    failRecord.setRespCode(respCode);
                    failRecord.setSendAt(T.getNow());
                    failRecordService.save(failRecord);

                    int code = CodeUtil.getCodeForAgent(T.integerValue(respCode));
                    json.put("code", code);
                    //json.put("respCode", respCode);
                    json.put("msg", CodeUtil.getCodeDesc(code));                         
                }
            } else {
                json.put("code", 1);
                json.put("msg", "fail"); 
            }
        } catch (Exception e) {
            e.printStackTrace();
            //throw (e);
            json.put("code", 1);
            json.put("msg", "fail"); 
        }
        return json;
    } 

    /**
     * 发送短信
     * @param type 发送类型:1行业邮件短信,2营销邮件短信,3行业短信,4营销短信
     * @param templateId 模板ID。创建模板提交后由系统自动分配
     * @param param 内容数据。用于替换模板中{数字}，若有多个替换内容，用|分隔
     * @param smsContent 短信内容。直接发送短信内容需要联系管理员开通权限。参数模板ID和短信内容必须输入其中一个
     * @param to 短信接收端手机号码集合。用英文逗号分开，每批发送的手机号数量不得超过100个。
     * @param portNumber 轻码云平台分配给开发者的端口号，暂不支持
     * @return
     * @throws Exception
     */
    public JSONObject sendSMS(int type, int agentId, String to, String param, String templateId, String smsContent, String portNumber){
        JSONObject json = new JSONObject();
        try {
            String resultContent = null;
            switch (type) {
            case 1: resultContent = qingMaYunService.sendIndustryEmailSMS(templateId, param, to, null);	break;
			case 2: resultContent = qingMaYunService.sendAffMarkEmailSMS(templateId, param, to, null); break;
			case 3: resultContent = qingMaYunService.sendIndustrySMS(templateId, param, null, to, null); break;
			case 4: resultContent = qingMaYunService.sendAffMarkSMS(templateId, param, null, to, null);	break;
			default: break;
			}
            //qingMaYunService.sendIndustrySMS(templateId, param, smsContent, to, portNumber);
            log.error("sendSMS type:" + type + " templateId:" + templateId + " to: " + to + " param: " + param + " result: " + resultContent);
            //log.error("sendapi phone: " + phoneString + " content: " + content + " result: " + resultContent);
            JSONObject result = JSONObject.parseObject(resultContent);
            if (result != null) {
                String respCode = result.getString("respCode");
                if (respCode.equals("00000")) {
                    String smsid = result.getString("smsId");  
                    //发送记录
                    SendRecord record = new SendRecord();
                    record.setAgentId(agentId);
                    record.setSmsid(smsid);
                    record.setQueryStatus(0);
                    record.setCount(0);
                    record.setContent(param);
                    record.setPhones(to);
                    record.setRespStatus(-1);
                    record.setFailCount(result.getIntValue("failCount"));
                    record.setType(type);
                    record.setTemplateId(templateId);
                    record.setSendAt(T.dateValue(result.getString("createTime"), "yyyyMMdd HH:mm:ss", new Date()));
                    sendRecordService.save(record);

                    json.put("code", 0);
                    json.put("msg", "success");
                    json.put("smsid", smsid);
                } else {
                    //失败记录
                    FailRecord failRecord = new FailRecord();
                    failRecord.setAgentId(agentId);
                    failRecord.setContent(param);
                    failRecord.setPhones(to);
                    int msc = to.split(",").length;
                    failRecord.setCount(msc);
                    failRecord.setRespCode(respCode);
                    failRecord.setTemplateId(templateId);
                    failRecord.setType(type);
                    failRecord.setSendAt(T.getNow());
                    failRecordService.save(failRecord);

                    int code = CodeUtil.getCodeForAgent(T.integerValue(respCode));
                    json.put("code", code);
                    //json.put("respCode", respCode);
                    json.put("msg", CodeUtil.getCodeDesc(code));                         
                }
            } else {
                json.put("code", 1);
                json.put("msg", "fail"); 
            }
        } catch (Exception e) {
            e.printStackTrace();
            //throw (e);
            json.put("code", 1);
            json.put("msg", "fail"); 
        }
        return json;
    } 
    
    /**
     * 根据smsid查询回执状态
     * @param agent
     * @param smsid
     * @return
     */
    public JSONObject queryResultByAPI(Agent agent, String smsid) {
        JSONObject json = new JSONObject();
        SendRecord sendRecord = sendRecordService.findBySmsId(smsid);
        if (sendRecord != null) {
            if (sendRecord.getAgentId() == agent.getId()) {
                int respStatus = sendRecord.getRespStatus();
                //已获取回执
                if (respStatus >= 0) {
                    json.put("code", 0);
                    JSONArray array = new JSONArray();
                    JSONObject sr = new JSONObject();
                    sr.put("status", respStatus);
                    sr.put("smsid", smsid);
                    sr.put("successphones", sendRecord.getPhones());
                    sr.put("message", sendRecord.getRespMessage());
                    array.add(sr);
                    json.put("smsBox", array);
                    
                    if (sendRecord.getQueryStatus() == 0) {
                        //标明已查询
                    	SendRecord tmpRecord = new SendRecord();
                    	tmpRecord.setId(sendRecord.getId());
                    	tmpRecord.setQueryStatus(1);
                        sendRecordService.updateNoNull(tmpRecord);
                    }
                } 
            } else {
                json.put("code", 4);
                json.put("smsid", smsid);    
                json.put("msg", "smsid fail."); 
            }
        } else {
            json.put("code", 4);
            json.put("smsid", smsid);    
            json.put("msg", "smsid fail."); 
        }
        return json;
    }   
    
    /**
     * 批量查询回执状态
     * @param agent
     * @return
     */
    public JSONObject queryResultByAPI(Agent agent) {
        JSONObject json = new JSONObject();
        json.put("code", 0);
        JSONArray array = new JSONArray();
        List<SendRecord> sendRecords = sendRecordService.getListByQueryStatus(agent.getId(), 500);
        List<Integer> idList = new ArrayList<Integer>();
        int idx = 0;
        for (SendRecord record : sendRecords) {
            JSONObject sr = new JSONObject();
            sr.put("status", record.getRespStatus());
            sr.put("smsid", record.getSmsid());
            sr.put("successphones", record.getPhones());
            sr.put("message", record.getRespMessage());
            array.add(sr);

            idList.add(record.getId());
            idx++;
            if (idx % 20 == 0) {
            	//每20条批量更新一次
                SendRecord tmpRecord = new SendRecord();
                tmpRecord.setQueryStatus(1);
                SendRecordExample example = new SendRecordExample();
                SendRecordExample.Criteria criteria = example.createCriteria();
                criteria.andIdIn(idList);
                sendRecordService.updateByExampleNoNull(tmpRecord, example);
                //清空list
                idList.clear();
            }            
        }
        //批量更新
        if (!idList.isEmpty()) {
            SendRecord tmpRecord = new SendRecord();
            tmpRecord.setQueryStatus(1);
            SendRecordExample example = new SendRecordExample();
            SendRecordExample.Criteria criteria = example.createCriteria();
            criteria.andIdIn(idList);
            sendRecordService.updateByExampleNoNull(tmpRecord, example);
        }       
        
        json.put("smsBox", array);
        return json;
    }       

    /**
     * 根据发送列表查询短信状态
     * @return
     */
    public int querySmsByTask() {
        int count = sendRecordService.countUnRespStatus();
        int idx = 0;
        if (count > 0) {
            try {
                String content = qingMaYunService.querySendResult("");
                JSONObject jsonObject = JSONObject.parseObject(content);
                //{"result":{"respCode": "00000","smsBox":[{"smsId":"65fc35da4b854e299af4410bebce5015","phone":"183xxxxxxxx","status":"0","respMessage":"DELIVRD"}]}}
                JSONObject result = jsonObject.getJSONObject("result");
                if (result != null) {
                    if (result.getString("respCode").equals("00000")) {
                        //
                        JSONArray smsBox = result.getJSONArray("smsBox");
                        if (smsBox.size() > 0) {
                            //统计客户id, 短信数量
                            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
                            for (int i = 0; i < smsBox.size(); i++) {
                                JSONObject json = smsBox.getJSONObject(i);
                                String smsid = json.getString("smsId");
                                SendRecord record = sendRecordService.findBySmsId(smsid);
                                if (record != null) {
                                	SendRecord tmpRecord = new SendRecord();
                                	tmpRecord.setId(record.getId());
                                	int status = json.getIntValue("status");
                                	String respMessage = json.getString("respMessage");
                                    //短信条数
                                    int msc = json.getIntValue("chargingNum");
                                    tmpRecord.setCount(msc); 
                                    tmpRecord.setSendAt(json.getDate("receiveTime"));
                                    /*if ("DELIVRD".equals(respMessage)) {
                                        tmpRecord.setCount(msc);                                    	
                                    } else {
                                        tmpRecord.setFailCount(msc);
                                    }*/
                                    tmpRecord.setRespStatus(status);
                                    tmpRecord.setRespMessage(respMessage);
                                    sendRecordService.updateNoNull(tmpRecord);
                                    
                                    int agentId = record.getAgentId();
                                    if (map.containsKey(agentId)) {
                                        map.put(agentId, map.get(agentId) + msc);
                                    } else {
                                        map.put(agentId, msc);
                                    }
                                }
                            }
                            //统一扣费
                            for (Entry<Integer, Integer> entry : map.entrySet()) {
                                int agId = entry.getKey();
                                int msgcount = entry.getValue();
                                Agent agent = agentService.findAgentById(agId);
                                if (agent != null && msgcount > 0) {
                                	Agent tmpAgent = new Agent();
                                	tmpAgent.setId(agId);
                                    int givenBalance = agent.getGivenBalance();
                                    // 扣赠送账户
                                    if (givenBalance > 0) {
                                        if (msgcount < givenBalance) {
                                            tmpAgent.setGivenBalance(givenBalance - msgcount);
                                            msgcount = 0;
                                        } else {
                                            tmpAgent.setGivenBalance(0);
                                            msgcount -= givenBalance;
                                        }
                                    }
                                    if (msgcount > 0) {
                                        tmpAgent.setBalance(agent.getBalance() - msgcount);
                                        msgcount = 0;
                                    }
                                    agentService.updateByNoNull(tmpAgent);
                                }
                            }
                            idx++;
                        }
                    }                     
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return idx;
    }
}
