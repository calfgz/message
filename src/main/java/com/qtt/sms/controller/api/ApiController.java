package com.qtt.sms.controller.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qtt.sms.model.Agent;
import com.qtt.sms.model.FailRecord;
import com.qtt.sms.model.Notice;
import com.qtt.sms.model.SendRecord;
import com.qtt.sms.model.Template;
import com.qtt.sms.service.AgentService;
import com.qtt.sms.service.FailRecordService;
import com.qtt.sms.service.NoticeService;
import com.qtt.sms.service.SendRecordService;
import com.qtt.sms.service.TemplateService;
import com.qtt.sms.service.api.ApiService;
import com.qtt.sms.service.api.QingMaYunService;
import com.qtt.sms.util.IpUtils;
import com.qtt.sms.util.Signature;
import com.qtt.sms.util.T;

@Controller
public class ApiController {
	private Log log = LogFactory.getLog(ApiController.class);
	
	@Autowired
	AgentService agentService;
	
	@Autowired
	ApiService apiService;
	
	@Autowired
	NoticeService noticeService;
	
	@Autowired
	QingMaYunService qingMaYunService;
	
	@Autowired
	TemplateService templateService;
	
	@Autowired
	SendRecordService sendRecordService;
	
	@Autowired
	FailRecordService failRecordService;


    /**
     * 发短信api
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value="/api/sendsms.do", method=RequestMethod.POST)
    public String sendSms(HttpServletRequest req, HttpServletResponse resp) throws Exception{        
        JSONObject json = new JSONObject();
        String accid = req.getParameter("accid");
        String phones = req.getParameter("phones");
        String content = req.getParameter("content");
        String sign = req.getParameter("sign");
        
        log.error("ip:" + IpUtils.getIp(req) + ", accid:" + accid + ", phones:" + phones + ", content:" + content + ", sign:" + sign);
        
        System.err.println("==ip:" + IpUtils.getIp(req) + ", accid:" + accid + ", phones:" + phones + ", content:" + content + ", sign:" + sign);
        
    	if (T.isBlank(accid) || T.isBlank(sign) || T.isBlank(phones) || T.isBlank(content)) {
    		json.put("respCode", 4);
    		json.put("msg", "invalid argument");
            resp.getWriter().println(json.toString());
            return null;
    	}
        
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("accid", accid);
        param.put("phones", phones);
        param.put("content", content);
        param.put("sign", sign);
        try {        
            Agent agent = agentService.findAgentByAccId(accid);
            
            if (agent == null || agent.getStatus() != 0) {                
                json.put("respCode", 20);
                json.put("msg", "user not found");
            } else if ((agent.getBalance() + agent.getGivenBalance()) < -100){            
                json.put("respCode", 23);
                json.put("msg", "balance is not enough");                
            } else {
                //签名验证
                if (Signature.checkIsSignValidFromResponseString(param, agent.getKeyValue())){
                    //直接发送，再写发送记录
                    json = apiService.sendSmsNotTemplate(agent.getId(), phones, content);                    		
                } else {
                    json.put("respCode", 6);
                    json.put("msg", "sign fail.");            
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            json.put("respCode", 1);
            json.put("msg", "server error."); 
        }
        resp.getWriter().println(json.toString());
        return null;
    }
	
    /**
     * 查询回执信息
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value="/api/query.do", method=RequestMethod.POST)
    public String querySmsStatus(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String accid = req.getParameter("accid");
        String smsid = req.getParameter("smsid");
        String sign = req.getParameter("sign");
    	String timestamp = req.getParameter("timestamp");
        
        JSONObject json = new JSONObject();
    	if (T.isBlank(accid) || T.isBlank(sign)) {
    		json.put("respCode", 4);
    		json.put("msg", "invalid argument");
            resp.getWriter().println(json.toString());
            return null;
    	}
        try {
            Agent agent = agentService.findAgentByAccId(accid);
            
            if (agent == null) {                
                json.put("respCode", 20);
                json.put("msg", "user not found");
                resp.getWriter().println(json.toString());
                return null;
            } else {
                //签名验证
                if (Signature.checkIsSignValidFromResponseString(accid, timestamp, agent.getKeyValue(), sign)){   
                    if (T.isBlank(smsid)) {
                        json = apiService.queryResultByAPI(agent);
                    } else {
                        json = apiService.queryResultByAPI(agent, smsid);
                    }
                } else {
                    json.put("respCode", 6);
                    json.put("msg", "sign fail.");            
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("respCode", 1);
            json.put("msg", "server error.");             
        }
        resp.getWriter().println(json.toString());
        return null;
    }
	
    /**
     * 用户信息api
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value="/api/userinfo.do", method=RequestMethod.POST)
    public String queryAgent(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String accid = req.getParameter("accid");
        String sign = req.getParameter("sign");
    	String timestamp = req.getParameter("timestamp");
        
        JSONObject json = new JSONObject();
        try {
        	if (T.isBlank(accid) || T.isBlank(sign)) {
        		json.put("respCode", 4);
        		json.put("msg", "invalid argument");
                resp.getWriter().println(json.toString());
                return null;
        	}
            Agent agent = agentService.findAgentByAccId(accid);
            
            if (agent == null) {                
                json.put("respCode", 20);
                json.put("msg", "user not found");
                resp.getWriter().println(json.toString());
                return null;
            }
            //签名验证
            if (Signature.checkIsSignValidFromResponseString(accid, timestamp, agent.getKeyValue(), sign)){ 
                json.put("respCode", 0);
                json.put("msg", "success");
                json.put("accid", agent.getAccid());
                json.put("name", agent.getName());
                json.put("mobile", agent.getMobile());
                json.put("balance", agent.getBalance());
                json.put("givenbalance", agent.getGivenBalance());
                json.put("status", agent.getStatus());
                json.put("createAt", T.format(agent.getRegAt(), "yyyy-MM-dd HH:mm:ss"));
                json.put("updateAt", T.format(agent.getUpdateAt(), "yyyy-MM-dd HH:mm:ss"));
            } else {
                json.put("respCode", 6);
                json.put("msg", "sign fail.");            
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("respCode", 1);
            json.put("msg", "server error."); 
        }
        resp.getWriter().println(json.toString());
        return null;
    }

    @RequestMapping(value="/MONotice", method=RequestMethod.POST)
    public String MONotice(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    	String inputLine;
    	String returnStr = "";	
    	try {
    		while ((inputLine = req.getReader().readLine()) != null) {
    			returnStr += inputLine;
    		}
    		req.getReader().close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}	
    	log.fatal("MONotice.do json:" + returnStr + ", ip:" + IpUtils.getIp(req));
    	JSONObject json = new JSONObject();
        try {
        	json = JSONObject.parseObject(returnStr);
            Notice notice = new Notice();
            notice.setPort(json.getString("MOPort"));
            notice.setPhone(json.getString("phone"));
            notice.setContent(json.getString("content"));
            notice.setCreateAt(json.getDate("MOTime"));
            notice.setStamp(json.getString("timestamp"));
            notice.setSign(json.getString("sig"));
            noticeService.save(notice);
            json.put("respCode", "00000");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("respCode", "00001");
            json.put("msg", "server error."); 
        }
        resp.getWriter().println(json.toString());
        return null;
    }

    /**
     * 创建模板
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value="/api/createTemplate.do", method=RequestMethod.POST)
    public String createTemplate(HttpServletRequest req, HttpServletResponse resp) throws Exception{        
        JSONObject json = new JSONObject();
        String accid = req.getParameter("accid");
        String sign = req.getParameter("sign");
    	int template_type = T.intValue(req.getParameter("template_type"), 1);
    	String title = req.getParameter("title");
    	String signature = req.getParameter("signature");
    	String content = req.getParameter("content");
    	String mailbox = req.getParameter("mailbox");
    	String nickname = req.getParameter("nickname");
    	String emailContent = req.getParameter("email_content");
    	String timestamp = req.getParameter("timestamp");
        
        log.error("ip:" + IpUtils.getIp(req) + ", accid:" + accid + ", timestame:" + timestamp + ", sign:" + sign);
        
    	if (T.isBlank(accid) || T.isBlank(sign) || T.isBlank(signature) || T.isBlank(content) || T.isBlank(timestamp)) {
    		json.put("respCode", 4);
    		json.put("msg", "invalid argument");
            resp.getWriter().println(json.toString());
            return null;
    	}

    	if (template_type == 1) {
    		if (T.isBlank(mailbox) || T.isBlank(nickname) || T.isBlank(emailContent)) {
        		json.put("respCode", 5);
        		json.put("msg", "invalid argument");
                resp.getWriter().println(json.toString());
                return null;            		
        	}
    	}
        Agent agent = agentService.findAgentByAccId(accid);
    	if (agent == null) {          
            json.put("respCode", 20);
            json.put("msg", "user not found");
            resp.getWriter().println(json.toString());
            return null;            		      		
    	}

        if (Signature.checkIsSignValidFromResponseString(accid, timestamp, agent.getKeyValue(), sign)){
        	try {
        		String reslut = qingMaYunService.createTemplate(String.valueOf(template_type), title, content, signature, mailbox, nickname, emailContent);
        		json = JSONObject.parseObject(reslut);
        		String respCode = json.getString("respCode");
        		if ("00000".equals(respCode)) {
    				Template template = new Template();
    				template.setAgentId(agent.getId());
    				template.setTemplateType(template_type);
    				template.setTitle(title);
    				template.setTemplateId(json.getString("templateId"));
    				template.setSignature(signature);
    				template.setContent(content);
    				template.setSenderMailbox(mailbox);
    				template.setSenderNickname(nickname);
    				template.setEmailContent(emailContent);
    				template.setAuditStatus(0);
    				template.setEnableStatus(0);
    				template.setCreateTime(T.getNow());
    				templateService.save(template);
        		} 
        	} catch (Exception e) {
                e.printStackTrace();
                json.put("respCode", 1);
                json.put("msg", "server error.");  		    	
        	}
        }else {
            json.put("respCode", 6);
            json.put("msg", "sign fail.");            
        }
        resp.getWriter().println(json.toString());
        return null;
    }

    /**
     * 查询模板
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value="/api/queryTemplate.do", method=RequestMethod.POST)
    public String queryTemplate(HttpServletRequest req, HttpServletResponse resp) throws Exception{        
        JSONObject json = new JSONObject();
        String accid = req.getParameter("accid");
        String sign = req.getParameter("sign");
        String templateId = req.getParameter("templateId");
    	String timestamp = req.getParameter("timestamp");
        
        log.error("ip:" + IpUtils.getIp(req) + ", accid:" + accid + ", templateId:" + templateId + ", timestame:" + timestamp + ", sign:" + sign);
        
    	if (T.isBlank(accid) || T.isBlank(sign) || T.isBlank(templateId) ||  T.isBlank(timestamp)) {
    		json.put("respCode", 4);
    		json.put("msg", "invalid argument");
            resp.getWriter().println(json.toString());
            return null;
    	}
    	
        Agent agent = agentService.findAgentByAccId(accid);
    	if (agent == null) {          
            json.put("respCode", 20);
            json.put("msg", "user not found");
            resp.getWriter().println(json.toString());
            return null;            		      		
    	}
    	
        Template template = templateService.findByTemplateId(templateId);
        if (template == null || template.getAgentId() != agent.getId()) {
            json.put("respCode", 20);
            json.put("msg", "template not found");
            resp.getWriter().println(json.toString());
            return null;            		      		
        }

        if (Signature.checkIsSignValidFromResponseString(accid, timestamp, agent.getKeyValue(), sign)){
            try {
            	String reslut = qingMaYunService.queryTemplate(template.getTemplateId());
        		json = JSONObject.parseObject(reslut);
        		String respCode = json.getString("respCode");
        		if ("00000".equals(respCode)) {
        			JSONArray array = json.getJSONArray("templateInfo");
        			if (!array.isEmpty()) {
        				for (int i=0; i < array.size(); i++) {
        					JSONObject info = array.getJSONObject(i);    					
        					Template temp = new Template();
        					temp.setId(template.getId());
        					temp.setTemplateId(info.getString("templateId"));
        					temp.setSignature(info.getString("smsSignature"));
        					temp.setContent(info.getString("smsTemplate"));
        					temp.setSenderMailbox(info.getString("senderMailbox"));
        					temp.setSenderNickname(info.getString("senderNickname"));
        					temp.setEmailContent(info.getString("emailTemplate"));
        					temp.setAuditStatus(info.getInteger("auditStatus"));
        					temp.setEnableStatus(info.getInteger("enableStatus"));
        					temp.setCreateTime(info.getDate("createTime"));
        					templateService.updateNoNull(temp);
        				}
        			}
        		} 
    		} catch (Exception e) {
                e.printStackTrace();
                json.put("respCode", 1);
                json.put("msg", "server error."); 
    		}
        }else {
            json.put("respCode", 6);
            json.put("msg", "sign fail.");            
        }
        resp.getWriter().println(json.toString());
        return null;
    }

    /**
     * 创建模板
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value="/api/modifyTemplate.do", method=RequestMethod.POST)
    public String modifyTemplate(HttpServletRequest req, HttpServletResponse resp) throws Exception{        
        JSONObject json = new JSONObject();
        String accid = req.getParameter("accid");
        String sign = req.getParameter("sign");
        String templateId = req.getParameter("templateId");
    	int template_type = T.intValue(req.getParameter("template_type"), 1);
    	String title = req.getParameter("title");
    	String signature = req.getParameter("signature");
    	String content = req.getParameter("content");
    	String mailbox = req.getParameter("mailbox");
    	String nickname = req.getParameter("nickname");
    	String emailContent = req.getParameter("email_content");
    	String timestamp = req.getParameter("timestamp");
        
        log.error("ip:" + IpUtils.getIp(req) + ", accid:" + accid + ", timestame:" + timestamp + ", sign:" + sign);
        
    	if (T.isBlank(accid) || T.isBlank(sign) || T.isBlank(signature) || T.isBlank(content) || T.isBlank(timestamp)) {
    		json.put("respCode", 4);
    		json.put("msg", "invalid argument");
            resp.getWriter().println(json.toString());
            return null;
    	}

    	if (template_type == 1) {
    		if (T.isBlank(mailbox) || T.isBlank(nickname) || T.isBlank(emailContent)) {
        		json.put("respCode", 5);
        		json.put("msg", "invalid argument");
                resp.getWriter().println(json.toString());
                return null;            		
        	}
    	}
        Agent agent = agentService.findAgentByAccId(accid);
    	if (agent == null) {          
            json.put("respCode", 20);
            json.put("msg", "user not found");
            resp.getWriter().println(json.toString());
            return null;            		      		
    	}
    	
        Template template = templateService.findByTemplateId(templateId);
        if (template == null || template.getAgentId() != agent.getId()) {
            json.put("respCode", 20);
            json.put("msg", "template not found");
            resp.getWriter().println(json.toString());
            return null;            		      		
        }

        if (Signature.checkIsSignValidFromResponseString(accid, timestamp, agent.getKeyValue(), sign)){
        	try {
        		String reslut = qingMaYunService.modifyTemplate(template.getTemplateId(), String.valueOf(template.getTemplateType()), title, content, signature, mailbox, nickname, emailContent);
        		json = JSONObject.parseObject(reslut);
        		String respCode = json.getString("respCode");
        		if ("00000".equals(respCode)) {
        			Template tmp = new Template();
        			tmp.setId(template.getId());
        			tmp.setTitle(title);
        			tmp.setSignature(signature);
        			tmp.setContent(content);
        			tmp.setSenderMailbox(mailbox);
        			tmp.setSenderNickname(nickname);
        			tmp.setEmailContent(emailContent);
        			templateService.updateNoNull(tmp);
        		}
        	} catch (Exception e) {
                e.printStackTrace();
                json.put("respCode", 1);
                json.put("msg", "server error.");  		    	
        	}
        }else {
            json.put("respCode", 6);
            json.put("msg", "sign fail.");            
        }
        resp.getWriter().println(json.toString());
        return null;
    }

    /**
     * 查询模板
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value="/api/deleteTemplate.do", method=RequestMethod.POST)
    public String deleteTemplate(HttpServletRequest req, HttpServletResponse resp) throws Exception{        
        JSONObject json = new JSONObject();
        String accid = req.getParameter("accid");
        String sign = req.getParameter("sign");
        String templateId = req.getParameter("templateId");
    	String timestamp = req.getParameter("timestamp");
        
        log.error("ip:" + IpUtils.getIp(req) + ", accid:" + accid + ", templateId:" + templateId + ", timestame:" + timestamp + ", sign:" + sign);
        
    	if (T.isBlank(accid) || T.isBlank(sign) || T.isBlank(templateId) ||  T.isBlank(timestamp)) {
    		json.put("respCode", 4);
    		json.put("msg", "invalid argument");
            resp.getWriter().println(json.toString());
            return null;
    	}
    	
        Agent agent = agentService.findAgentByAccId(accid);
    	if (agent == null) {          
            json.put("respCode", 20);
            json.put("msg", "user not found");
            resp.getWriter().println(json.toString());
            return null;            		      		
    	}
    	
        Template template = templateService.findByTemplateId(templateId);
        if (template == null || template.getAgentId() != agent.getId()) {
            json.put("respCode", 20);
            json.put("msg", "template not found");
            resp.getWriter().println(json.toString());
            return null;            		      		
        }

        if (Signature.checkIsSignValidFromResponseString(accid, timestamp, agent.getKeyValue(), sign)){
            try {
            	String reslut = qingMaYunService.deleteTemplate(template.getTemplateId());
        		json = JSONObject.parseObject(reslut);
        		String respCode = json.getString("respCode");
        		if ("00000".equals(respCode)) {
        			templateService.delete(template.getId());
        		}
    		} catch (Exception e) {
                e.printStackTrace();
                json.put("respCode", 1);
                json.put("msg", "server error."); 
    		}
        }else {
            json.put("respCode", 6);
            json.put("msg", "sign fail.");            
        }
        resp.getWriter().println(json.toString());
        return null;
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
    @RequestMapping(value="/api/sendSMS.do", method=RequestMethod.POST)
    public String sendSMS(HttpServletRequest req, HttpServletResponse resp) throws Exception{        
        JSONObject json = new JSONObject();
        String accid = req.getParameter("accid");
        String sign = req.getParameter("sign");
        String templateId = req.getParameter("templateId");
    	String timestamp = req.getParameter("timestamp");
    	int type = T.intValue(req.getParameter("type"), -1);
    	String to = req.getParameter("to");
    	String param = req.getParameter("param");
        
        log.error("ip:" + IpUtils.getIp(req) + ", accid:" + accid + ", templateId:" + templateId + ", timestame:" + timestamp + ", sign:" + sign);
        
    	if (T.isBlank(accid) || T.isBlank(sign) || T.isBlank(templateId) ||  T.isBlank(timestamp)) {
    		json.put("respCode", 4);
    		json.put("msg", "invalid argument");
            resp.getWriter().println(json.toString());
            return null;
    	}
    	
        Agent agent = agentService.findAgentByAccId(accid);
    	if (agent == null) {          
            json.put("respCode", 20);
            json.put("msg", "user not found");
            resp.getWriter().println(json.toString());
            return null;            		      		
    	}
    	
        Template template = templateService.findByTemplateId(templateId);
        if (template == null || template.getAgentId() != agent.getId()) {
            json.put("respCode", 20);
            json.put("msg", "template not found");
            resp.getWriter().println(json.toString());
            return null;            		      		
        }

        if (Signature.checkIsSignValidFromResponseString(accid, timestamp, agent.getKeyValue(), sign)){
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
                json = JSONObject.parseObject(resultContent);
                String respCode = json.getString("respCode");
                if (respCode.equals("00000")) {
                    String smsid = json.getString("smsId");  
                    //发送记录
                    SendRecord record = new SendRecord();
                    record.setAgentId(agent.getId());
                    record.setSmsid(smsid);
                    record.setQueryStatus(0);
                    record.setCount(0);
                    record.setContent(param);
                    record.setPhones(to);
                    record.setRespStatus(-1);
                    record.setFailCount(json.getIntValue("failCount"));
                    record.setType(type);
                    record.setTemplateId(templateId);
                    record.setSendAt(T.dateValue(json.getString("createTime"), "yyyyMMdd HH:mm:ss", new Date()));
                    sendRecordService.save(record);
                } else {
                    //失败记录
                    FailRecord failRecord = new FailRecord();
                    failRecord.setAgentId(agent.getId());
                    failRecord.setContent(param);
                    failRecord.setPhones(to);
                    int msc = to.split(",").length;
                    failRecord.setCount(msc);
                    failRecord.setRespCode(respCode);
                    failRecord.setTemplateId(templateId);
                    failRecord.setType(type);
                    failRecord.setSendAt(T.getNow());
                    failRecordService.save(failRecord);                      
                }
            } catch (Exception e) {
                e.printStackTrace();
                //throw (e);
                json.put("respCode", 1);
                json.put("msg", "fail"); 
            }
        }else {
            json.put("respCode", 6);
            json.put("msg", "sign fail.");            
        }
        resp.getWriter().println(json.toString());
        return null;
    }
}
