package com.qtt.sms.controller.agent;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.qtt.sms.model.Agent;
import com.qtt.sms.model.AgentSession;
import com.qtt.sms.service.AgentLogService;
import com.qtt.sms.service.AgentService;
import com.qtt.sms.service.AgentSessionService;
import com.qtt.sms.service.api.ApiService;
import com.qtt.sms.util.MD5;
import com.qtt.sms.util.T;

@Controller
public class LoginController {
	@Autowired
	AgentService agentService;
	
	@Autowired
	AgentSessionService sessionService;
	
	@Autowired
	AgentLogService logService;
	
	@Autowired
	ApiService apiService;
    
    @RequestMapping(value="/index.do", method=RequestMethod.GET)
    public String index(HttpServletRequest req, HttpServletResponse resp) throws Exception{
    	Agent agent = agentService.getCurrentAgent(req, resp);
    	if (agent == null) {
            return "redirect:login.do";          		
    	}
        return "agent/index";
    }
    
    @RequestMapping(value="/login.do", method=RequestMethod.GET)
    public String showLogin(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        return "agent/login";
    }
    
    @RequestMapping(value="/login.do", method=RequestMethod.POST)
    public String login(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        String captcha = req.getParameter("captcha");
        //System.out.println("req captcha : " + captcha);
        if (T.isBlank(captcha)) {
            return "redirect:login.do";            
        }
        String sCaptcha = (String)req.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        //System.out.println("session captcha : " + sCaptcha);
        if (!captcha.equals(sCaptcha)) {
            return "redirect:login.do";
        }
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (T.isBlank(username) || T.isBlank(password)) {
            return "redirect:login.do";
        }
        Agent agent = agentService.findAgentByAccount(username);
        if (agent == null) {
            return "redirect:login.do";
        }
        password = MD5.MD5Encode(password);
        if (password.equals(agent.getPassword())) {
            AgentSession session = new AgentSession();
            session.setSessionId(agent.getId() + String.valueOf(System.currentTimeMillis()));
            session.setAgentId(agent.getId());
            session.setCreateAt(new Date());
            sessionService.saveSession(session, resp);
            
            Agent tmpAgent = new Agent();
            tmpAgent.setId(agent.getId());
            tmpAgent.setLoginAt(T.getNow());
            agentService.updateByNoNull(tmpAgent);
            //log
            logService.save(agent.getId(), req);
            return "redirect:index.do";
        } else {
            return "redirect:login.do";
        }
    }
    
    @RequestMapping(value="/logout.do")
    public String logout(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        sessionService.clearSession(req, resp);
        return "redirect:login.do";
    }
    
    @RequestMapping(value="/agent/password.do", method=RequestMethod.GET)
    public String showPassword(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        return "agent/setting/password";
    }
    
    @RequestMapping(value="/agent/password.do", method=RequestMethod.POST)
    public String password(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        String oldPassword = req.getParameter("oldPassword");
        String password = req.getParameter("password");
        Agent agent = agentService.getCurrentAgent(req, resp);
        JSONObject json = new JSONObject();
        if (agent == null) {
            json.put("statusCode", 300);
            json.put("message", "用户未登录");  
        } else {
            if (MD5.MD5Encode(oldPassword).equals(agent.getPassword())) {
                password = MD5.MD5Encode(password);
                Agent tmpAgent = new Agent();
                tmpAgent.setId(agent.getId());
                tmpAgent.setPassword(password);
                agentService.updateByNoNull(tmpAgent);
                logService.save(req, resp);
                json.put("statusCode", 200);
                json.put("message", "修改成功");  
                json.put("callbackType", "closeCurrent");  
            } else {
                json.put("statusCode", 300);
                json.put("message", "密码错误");  
            }
        }        
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().println(json.toJSONString());
        return null;
    }
    
    @RequestMapping(value="/agent/userinfo.do", method=RequestMethod.GET)
    public String userinfo(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        Agent agent = agentService.getCurrentAgent(req, resp);
        req.setAttribute("agent", agent);
        return "agent/setting/userinfo";
    }
    
    @RequestMapping(value="/agent/edit.do", method=RequestMethod.GET)
    public String showEdit(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        Agent agent = agentService.getCurrentAgent(req, resp);
        req.setAttribute("agent", agent);
        return "agent/setting/edit";
    }

    
    @RequestMapping(value="/agent/edit.do", method=RequestMethod.POST)
    public String edit(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Agent agent = agentService.getCurrentAgent(req, resp);
        if (agent == null) {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(T.getJsonString(300, "记录不存在"));
            return null;
        }
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");
        
        Agent tmpAgent = new Agent();
        tmpAgent.setId(agent.getId());
        tmpAgent.setName(name);
        tmpAgent.setEmail(email);
        tmpAgent.setMobile(mobile);
        tmpAgent.setUpdateAt(T.getNow());
        agentService.updateByNoNull(tmpAgent);
        //log
        logService.save(agent.getId(), req);
        
        resp.setCharacterEncoding("UTF-8");
        JSONObject json = new JSONObject();
		json.put("statusCode", 200);
		json.put("message", "操作成功");
		json.put("callbackType", "closeCurrent");
		resp.getWriter().println(json.toJSONString());
        return null;
    }  
    
    @RequestMapping(value="/agent/send.do", method=RequestMethod.GET)
    public String showSend(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return "agent/setting/send";
    }
    
    @RequestMapping(value="/agent/send.do", method=RequestMethod.POST)
    public String send(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Agent agent = agentService.getCurrentAgent(req, resp);
        JSONObject json = new JSONObject();
        if (agent == null || agent.getStatus() != 0) {
            json.put("statusCode", 300);
            json.put("message", "没有权限");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(json.toJSONString());
            return null;
        }

        String phones = req.getParameter("phones");
        String content = req.getParameter("content");
        String sign = req.getParameter("sign");
        if(T.isBlank(phones) || T.isBlank(content) || T.isBlank(sign)) {
            json.put("statusCode", 300);
            json.put("message", "发送数据错误");
            json.put("callbackType", "closeCurrent");
        } else {
            content += "【" + sign + "】";
        	json = apiService.sendSmsNotTemplate(agent.getId(), phones, content);
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
