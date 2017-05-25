package com.qtt.sms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;
import com.qtt.sms.model.AgentLog;
import com.qtt.sms.model.AgentLogExample;
import com.qtt.sms.service.AgentLogService;
import com.qtt.sms.util.T;

@Controller
public class AgentLogController {
	@Autowired
	AgentLogService agentLogService;

    /**
     * 列表
     * @param req
     * @param resp
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/admin/agentlog/list.do")
    public String list(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //check right
    	int pageNum = T.intValue(req.getParameter("pageNum"), 1);
    	int pageSize = T.intValue(req.getParameter("pageSize"), 25);
    	int agentId = T.intValue(req.getParameter("agnetId"), 0);
    	AgentLogExample example = new AgentLogExample();
    	AgentLogExample.Criteria criteria = example.createCriteria();
    	if (agentId > 0) {
    		criteria.andAgentIdEqualTo(agentId);
    	}
    	PageInfo<AgentLog> pager = agentLogService.getPager(example, pageNum, pageSize);
        req.setAttribute("pager", pager);
        return "admin/agentlog/list";
    }
}
