package com.qtt.sms.controller.agent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.PageInfo;
import com.qtt.sms.model.Agent;
import com.qtt.sms.model.FailRecord;
import com.qtt.sms.model.FailRecordExample;
import com.qtt.sms.model.SendRecord;
import com.qtt.sms.model.SendRecordExample;
import com.qtt.sms.service.AgentService;
import com.qtt.sms.service.FailRecordService;
import com.qtt.sms.service.SendRecordService;
import com.qtt.sms.util.T;

@Controller
public class RecordController {
	@Autowired
	AgentService agentService;
	
	@Autowired
	SendRecordService sendRecordService;
	
	@Autowired
	FailRecordService failRecordService;

    @RequestMapping(value="/agent/record/success.do", method=RequestMethod.GET)
    public String success(HttpServletRequest req, HttpServletResponse resp) throws Exception{
    	Agent agent = agentService.getCurrentAgent(req, resp);
    	int pageNum = T.intValue(req.getParameter("pageNum"), 1);
    	int pageSize = T.intValue(req.getParameter("pageSize"), 25);
    	String smsid = req.getParameter("smsid");
    	SendRecordExample example = new SendRecordExample();
    	SendRecordExample.Criteria criteria = example.createCriteria();
    	criteria.andAgentIdEqualTo(agent.getId());
    	if (!T.isBlank(smsid)) {
    		criteria.andSmsidEqualTo(smsid);
    	}
    	PageInfo<SendRecord> pager = sendRecordService.getPager(example, pageNum, pageSize);
        req.setAttribute("pager", pager);        
        return "agent/record/success";
    }

    @RequestMapping(value="/agent/record/fail.do", method=RequestMethod.GET)
    public String fail(HttpServletRequest req, HttpServletResponse resp) throws Exception{
    	Agent agent = agentService.getCurrentAgent(req, resp);
    	int pageNum = T.intValue(req.getParameter("pageNum"), 1);
    	int pageSize = T.intValue(req.getParameter("pageSize"), 25);
    	FailRecordExample example = new FailRecordExample();
    	FailRecordExample.Criteria criteria = example.createCriteria();
    	criteria.andAgentIdEqualTo(agent.getId());
    	PageInfo<FailRecord> pager = failRecordService.getPager(example, pageNum, pageSize);
        req.setAttribute("pager", pager);        
        return "agent/record/fail";    	
    }

    @RequestMapping(value="/admin/record/faildetail.do")
    public String faildetail(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //check right
        int id = T.intValue(req.getParameter("id"), -1);
        FailRecord record = failRecordService.findById(id);
        if (record == null) {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(T.getJsonString(300, "记录不存在"));
            return null;            
        }
        req.setAttribute("entity", record);
        return "admin/record/faildetail";    	
    }

    @RequestMapping(value="/admin/record/senddetail.do")
    public String senddetail(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //check right
        int id = T.intValue(req.getParameter("id"), -1);
        SendRecord record = sendRecordService.findById(id);
        if (record == null) {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(T.getJsonString(300, "记录不存在"));
            return null;            
        }
        req.setAttribute("entity", record);
        return "admin/record/senddetail";    	
    }
}
