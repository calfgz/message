package com.qtt.sms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.qtt.sms.model.Template;
import com.qtt.sms.model.TemplateExample;
import com.qtt.sms.service.AgentService;
import com.qtt.sms.service.LogService;
import com.qtt.sms.service.TemplateService;
import com.qtt.sms.service.api.ApiService;
import com.qtt.sms.service.api.QingMaYunService;
import com.qtt.sms.util.T;

@Controller
public class TemplateController {
	@Autowired
	TemplateService templateService;
	
	@Autowired
	AgentService agentService;
	
	@Autowired
	QingMaYunService qingMaYunService;
	
	@Autowired
	LogService logService;
	
	@Autowired
	ApiService apiService;

    /**
     * 列表
     * @param req
     * @param resp
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/admin/template/list.do")
    public String list(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //check right
    	int pageNum = T.intValue(req.getParameter("pageNum"), 1);
    	int pageSize = T.intValue(req.getParameter("pageSize"), 25);
    	int agentId = T.intValue(req.getParameter("agnetId"), 0);
    	TemplateExample example = new TemplateExample();
    	TemplateExample.Criteria criteria = example.createCriteria();
    	if (agentId > 0) {
    		criteria.andAgentIdEqualTo(agentId);
    	}
    	PageInfo<Template> pager = templateService.getPager(example, pageNum, pageSize);
        req.setAttribute("pager", pager);
        return "admin/template/list";
    }

    @RequestMapping(value="/admin/template/detail.do")
    public String detail(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //check right
        int id = T.intValue(req.getParameter("id"), -1);
        Template record = templateService.findById(id);
        if (record == null) {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(T.getJsonString(300, "记录不存在"));
            return null;            
        }
        req.setAttribute("entity", record);
        return "admin/template/detail";    	
    }
    
    @RequestMapping(value="/admin/template/create.do", method=RequestMethod.GET)
    public String showCreate(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //check right
        req.setAttribute("method", "create");
        return "admin/template/edit";
    }
    
    @RequestMapping(value="/admin/template/create.do", method=RequestMethod.POST)
    public String create(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //check right
    	int template_type = T.intValue(req.getParameter("template_type"), 1);
    	String title = req.getParameter("title");
    	String signature = req.getParameter("signature");
    	String content = req.getParameter("content");
    	String mailbox = req.getParameter("mailbox");
    	String nickname = req.getParameter("nickname");
    	String emailContent = req.getParameter("email_content");
    	int agentId = T.intValue(req.getParameter("agentId"), 1);
    	
    	if (T.isBlank(signature) || T.isBlank(content)) {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(T.getJsonString(300, "数据不能为空"));
            return null;            		
    	}
    	if (template_type == 1) {
    		if (T.isBlank(mailbox) || T.isBlank(nickname) || T.isBlank(emailContent)) {
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().println(T.getJsonString(300, "邮件短信数据不能为空"));
                return null;            		
        	}
    	}
    	if (agentService.findAgentById(agentId) == null) {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(T.getJsonString(300, "客户不存在"));
            return null;            		      		
    	}
    	try {
    		String reslut = qingMaYunService.createTemplate(String.valueOf(template_type), title, content, signature, mailbox, nickname, emailContent);
    		JSONObject json = JSONObject.parseObject(reslut);
    		String respCode = json.getString("respCode");
    		if ("00000".equals(respCode)) {
				Template template = new Template();
				template.setAgentId(agentId);
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
    		} else {
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().println(T.getJsonString(300, reslut));
                return null;     
    		}
            logService.save(req, resp);
    	} catch (Exception e) {
    		e.printStackTrace();
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(T.getJsonString(300, "创建失败"));
            return null;            		    	
    	}
        resp.setCharacterEncoding("UTF-8");
        JSONObject json = new JSONObject();
		json.put("statusCode", 200);
		json.put("message", "操作成功");
		json.put("navTabId", "list-template");
		json.put("callbackType", "closeCurrent");
		resp.getWriter().println(json.toJSONString());
    	return null;
    }
    
    @RequestMapping(value="/admin/template/update.do", method=RequestMethod.GET)
    public String showUpdate(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //check right
        int id = T.intValue(req.getParameter("id"), -1);
        Template template = templateService.findById(id);
        if (template == null) {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(T.getJsonString(300, "记录不存在"));
            return null;            
        }
        req.setAttribute("method", "update");
        req.setAttribute("entity", template);
        return "admin/template/edit";
    }
    
    @RequestMapping(value="/admin/template/update.do", method=RequestMethod.POST)
    public String update(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = T.intValue(req.getParameter("id"), -1);
        Template template = templateService.findById(id);
        if (template == null) {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(T.getJsonString(300, "记录不存在"));
            return null;            
        }
    	String title = req.getParameter("title");
    	String signature = req.getParameter("signature");
    	String content = req.getParameter("content");
    	String mailbox = req.getParameter("mailbox");
    	String nickname = req.getParameter("nickname");
    	String emailContent = req.getParameter("email_content");
    	//int agentId = T.intValue(req.getParameter("agentId"), -1);
    	try {
    		String reslut = qingMaYunService.modifyTemplate(template.getTemplateId(), String.valueOf(template.getTemplateType()), title, content, signature, mailbox, nickname, emailContent);
    		JSONObject json = JSONObject.parseObject(reslut);
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
    		} else {
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().println(T.getJsonString(300, reslut));
                return null;     
    		}
            logService.save(req, resp);
    	} catch (Exception e) {
    		e.printStackTrace();
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(T.getJsonString(300, "更新失败"));
            return null;            		    	
    	}
        resp.setCharacterEncoding("UTF-8");
        JSONObject json = new JSONObject();
		json.put("statusCode", 200);
		json.put("message", "操作成功");
		json.put("navTabId", "list-template");
		json.put("callbackType", "closeCurrent");
		resp.getWriter().println(json.toJSONString());
    	return null;
    }
    
    @RequestMapping(value="/admin/template/query.do")
    public String updateStatus(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = T.intValue(req.getParameter("id"), -1);
        Template template = templateService.findById(id);
        if (template == null) {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(T.getJsonString(300, "记录不存在"));
            return null;            
        }
        try {
        	String reslut = qingMaYunService.queryTemplate(template.getTemplateId());
    		JSONObject json = JSONObject.parseObject(reslut);
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
    		} else {
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().println(T.getJsonString(300, reslut));
                return null;     
    		}
		} catch (Exception e) {
			// TODO: handle exception
		}
        resp.setCharacterEncoding("UTF-8");
        JSONObject json = new JSONObject();
		json.put("statusCode", 200);
		json.put("message", "操作成功");
		json.put("navTabId", "list-template");
		resp.getWriter().println(json.toJSONString());
    	return null;
    }

    
    @RequestMapping(value="/admin/template/delete.do")
    public String delete(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = T.intValue(req.getParameter("id"), -1);
        Template template = templateService.findById(id);
        if (template == null) {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(T.getJsonString(300, "记录不存在"));
            return null;            
        }
        try {
        	String reslut = qingMaYunService.deleteTemplate(template.getTemplateId());
    		JSONObject json = JSONObject.parseObject(reslut);
    		String respCode = json.getString("respCode");
    		if ("00000".equals(respCode)) {
    			templateService.delete(id);
    		} else {
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().println(T.getJsonString(300, reslut));
                return null;     
    		}
		} catch (Exception e) {
			// TODO: handle exception
		}
        resp.setCharacterEncoding("UTF-8");
        JSONObject json = new JSONObject();
		json.put("statusCode", 200);
		json.put("message", "操作成功");
		json.put("navTabId", "list-template");
		resp.getWriter().println(json.toJSONString());
    	return null;
    }
    
    @RequestMapping(value="/admin/template/send.do", method=RequestMethod.GET)
    public String showSend(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //check right
        return "admin/template/send";
    }
    
    @RequestMapping(value="/admin/template/send.do", method=RequestMethod.POST)
    public String send(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //check right
    	String templateId = req.getParameter("templateId");
        String to = req.getParameter("to");
        String param = req.getParameter("param");
        int type = T.intValue(req.getParameter("type"), 1);
        int agentId = T.intValue(req.getParameter("agentId"), 1);
        JSONObject json = new JSONObject();
        if(T.isBlank(to) || T.isBlank(param) || T.isBlank(templateId)) {
            json.put("statusCode", 300);
            json.put("message", "发送数据错误");
            json.put("callbackType", "closeCurrent");
        } else {
            /*String result = null;
            switch (type) {
			case 1: result = qingMaYunService.sendIndustryEmailSMS(templateId, param, to, null);				
				break;
			case 2: result = qingMaYunService.sendAffMarkEmailSMS(templateId, param, to, null); break;
			case 3: result = qingMaYunService.sendIndustrySMS(templateId, param, null, to, null);
			case 4: result = qingMaYunService.sendAffMarkSMS(templateId, param, null, to, null);

			default:
				break;
			}*/
        	json = apiService.sendSMS(type, agentId, to, param, templateId, null, null);
        	if (json.getIntValue("code") == 0) {
                json.put("statusCode", 200);
                json.put("message", "发送成功");
                json.put("callbackType", "closeCurrent");        		
        	} else {
                json.put("statusCode", 300);
                json.put("message", "server error code = " + json.getIntValue("code"));        		
        	}        
        }
        
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().println(json.toJSONString());
        return null;
    }
}
