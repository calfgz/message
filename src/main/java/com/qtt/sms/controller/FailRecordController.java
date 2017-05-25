package com.qtt.sms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;
import com.qtt.sms.model.FailRecord;
import com.qtt.sms.model.FailRecordExample;
import com.qtt.sms.service.FailRecordService;
import com.qtt.sms.util.T;

@Controller
public class FailRecordController {
	@Autowired
	FailRecordService failRecordService;

    @RequestMapping(value="/admin/failrecord/detail.do")
    public String detail(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //check right
        int id = T.intValue(req.getParameter("id"), -1);
        FailRecord record = failRecordService.findById(id);
        if (record == null) {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(T.getJsonString(300, "记录不存在"));
            return null;            
        }
        req.setAttribute("entity", record);
        return "admin/failrecord/detail";    	
    }
    
    @RequestMapping(value="/admin/failrecord/list.do")
    public String list(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //check right
    	int pageNum = T.intValue(req.getParameter("pageNum"), 1);
    	int pageSize = T.intValue(req.getParameter("pageSize"), 25);
    	int agentId = T.intValue(req.getParameter("agnetId"), 0);
    	FailRecordExample example = new FailRecordExample();
    	FailRecordExample.Criteria criteria = example.createCriteria();
    	if (agentId > 0) {
    		criteria.andAgentIdEqualTo(agentId);
    	}
    	PageInfo<FailRecord> pager = failRecordService.getPager(example, pageNum, pageSize);
        req.setAttribute("pager", pager);
        return "admin/failrecord/list";
    }
}
