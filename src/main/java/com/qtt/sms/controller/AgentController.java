package com.qtt.sms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.qtt.sms.model.Agent;
import com.qtt.sms.model.AgentExample;
import com.qtt.sms.model.RechargeLog;
import com.qtt.sms.model.User;
import com.qtt.sms.service.AgentService;
import com.qtt.sms.service.LogService;
import com.qtt.sms.service.UserService;
import com.qtt.sms.util.T;


@Controller
public class AgentController {
	@Autowired
	AgentService agentService;	
	
	@Autowired 
	UserService userService;
	
	@Autowired
	LogService logService;

    @RequestMapping(value="/admin/agent/list.do")
    public String list(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //check right
    	int pageNum = T.intValue(req.getParameter("pageNum"), 1);
    	int pageSize = T.intValue(req.getParameter("pageSize"), 25);
    	int agentId = T.intValue(req.getParameter("agnetId"), 0);
    	String account = req.getParameter("acccount");
    	AgentExample example = new AgentExample();
    	AgentExample.Criteria criteria = example.createCriteria();
    	if (agentId > 0) {
    		criteria.andIdEqualTo(agentId);
    	}
    	if (!T.isBlank(account)) {
    		criteria.andAccountEqualTo(account);
    	}
    	PageInfo<Agent> pager = agentService.getPageAgent(example, pageNum, pageSize);
        req.setAttribute("pager", pager);
        return "admin/agent/list";
    }
    
    @RequestMapping(value="/admin/agent/create.do", method=RequestMethod.GET)
    public String showCreate(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //check right
        req.setAttribute("method", "create");
        return "admin/agent/edit";
    }
    
    @RequestMapping(value="/admin/agent/create.do", method=RequestMethod.POST)
    public String create(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //check right
    	String account = req.getParameter("account");
    	String password = req.getParameter("password");
    	String name = req.getParameter("name");
    	String email = req.getParameter("email");
    	String mobile = req.getParameter("mobile");
    	String appId = req.getParameter("appId");
    	String port = req.getParameter("port");
    	
    	if (T.isBlank(account) || T.isBlank(password) || T.isBlank(name)) {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(T.getJsonString(300, "数据不能为空"));
            return null;            		
    	}
    	if (agentService.findAgentByAccount(account) != null) {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(T.getJsonString(300, "客户已存在"));
            return null;            		      		
    	}
    	try {
	    	Agent agent = new Agent();
	    	agent.setAccount(account);
	    	agent.setPassword(password);
	    	agent.setName(name);
	    	agent.setEmail(email);
	    	agent.setMobile(mobile);
	    	agent.setAppId(appId);
	    	agent.setPort(port);
	    	agentService.create(agent);
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
		json.put("navTabId", "list-agent");
		json.put("callbackType", "closeCurrent");
		resp.getWriter().println(json.toJSONString());
    	return null;
    }
    
    @RequestMapping(value="/admin/agent/update.do", method=RequestMethod.GET)
    public String showUpdate(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //check right
        int id = T.intValue(req.getParameter("id"), -1);
        Agent agent = agentService.findAgentById(id);
        if (agent == null) {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(T.getJsonString(300, "记录不存在"));
            return null;            
        }
        req.setAttribute("method", "update");
        req.setAttribute("entity", agent);
        return "admin/agent/edit";
    }
    
    @RequestMapping(value="/admin/agent/update.do", method=RequestMethod.POST)
    public String update(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = T.intValue(req.getParameter("id"), -1);
        Agent agent = agentService.findAgentById(id);
        if (agent == null) {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(T.getJsonString(300, "记录不存在"));
            return null;            
        }
    	String name = req.getParameter("name");
    	String email = req.getParameter("email");
    	String mobile = req.getParameter("mobile");
    	String appId = req.getParameter("appId");
    	String port = req.getParameter("port");
    	try {
	    	Agent tmpAgent = new Agent();
	    	tmpAgent.setId(agent.getId());
	    	tmpAgent.setName(name);
	    	tmpAgent.setEmail(email);
	    	tmpAgent.setMobile(mobile);
	    	tmpAgent.setAppId(appId);
	    	tmpAgent.setPort(port);
	    	tmpAgent.setUpdateAt(T.getNow());
	    	agentService.updateByNoNull(tmpAgent);
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
		json.put("navTabId", "list-agent");
		json.put("callbackType", "closeCurrent");
		resp.getWriter().println(json.toJSONString());
    	return null;
    }
    
    /**
     * 更新状态
     * @param req
     * @param resp
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/admin/agent/status.do", method=RequestMethod.POST)
    public String updateStatus(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //check right
        String[] ids = req.getParameterValues("ids");
        int status = T.intValue(req.getParameter("status"), 0);
        if (ids != null && ids.length > 0) {
            Agent agent = new Agent();
            agent.setStatus(status);
            AgentExample example = new AgentExample();
            AgentExample.Criteria criteria = example.createCriteria();
            List<Integer> idList = new ArrayList<Integer>();
            StringBuffer query = new StringBuffer("{status:" + status + ", ids:");
            for (String idx : ids) {
            	query.append(idx).append(",");
            	int id = T.intValue(idx, 0);
            	if (id > 0) {
            		idList.add(id);
            	}
            }
            criteria.andIdIn(idList);
            query.append("}");
            if (!idList.isEmpty()) {
            	agentService.updateByExampleNoNull(agent, example);
            	//log
            	logService.save(req, resp, query.toString());
            }
        }
        resp.setCharacterEncoding("UTF-8");
        JSONObject json = new JSONObject();
		json.put("statusCode", 200);
		json.put("message", "操作成功");
		json.put("navTabId", "list-agent");
		resp.getWriter().println(json.toJSONString());
    	return null;
    }
    
    @RequestMapping(value="/admin/agent/recharge.do", method=RequestMethod.GET)
    public String showRecharge(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //check right
        int id = T.intValue(req.getParameter("id"), -1);
        Agent agent = agentService.findAgentById(id);
        if (agent == null) {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(T.getJsonString(300, "记录不存在"));
            return null;            
        }
        req.setAttribute("agent", agent);
        return "admin/agent/recharge";
    }
    
    @RequestMapping(value="/admin/agent/recharge.do", method=RequestMethod.POST)
    public String recharge(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    	//check right
    	int agentId = T.intValue(req.getParameter("agentId"), -1);
    	int balance = T.intValue(req.getParameter("balance"), 0);
    	int givenBalance = T.intValue(req.getParameter("givenBalance"), 0);
    	String intro = req.getParameter("intro");
    	
    	RechargeLog rechargeLog = new RechargeLog();
    	rechargeLog.setAgentId(agentId);
    	rechargeLog.setBalance(balance);
    	rechargeLog.setGivenBalance(givenBalance);
    	rechargeLog.setIntro(intro);
        if (rechargeLog.getBalance() == 0 && rechargeLog.getGivenBalance() == 0) {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(T.getJsonString(300, "充值金额不能为0"));
            return null;                  
        }
        Agent agent = agentService.findAgentById(agentId);
        if ( agent == null) {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(T.getJsonString(300, "该客户不存在"));
            return null;                 
        }
    	
    	User user = userService.getCurrentUser(req, resp);
    	rechargeLog.setUserId(user.getId());
    	
    	agentService.recharge(rechargeLog, agent);
    	//log
    	logService.save(user.getId(), req);

        resp.setCharacterEncoding("UTF-8");
        JSONObject json = new JSONObject();
		json.put("statusCode", 200);
		json.put("message", "操作成功");
		json.put("navTabId", "list-agent");
		resp.getWriter().println(json.toJSONString());
    	return null;
    }
    
    @RequestMapping(value="/admin/agent/detail.do", method=RequestMethod.GET)
    public String detail(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    	//check right
        int id = T.intValue(req.getParameter("id"), -1);
        Agent agent = agentService.findAgentById(id);
        if (agent == null) {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(T.getJsonString(300, "记录不存在"));
            return null;            
        }
        req.setAttribute("entity", agent);
        return "admin/agent/detail";    	
    }
}
