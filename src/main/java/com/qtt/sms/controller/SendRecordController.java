package com.qtt.sms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;
import com.qtt.sms.model.SendRecord;
import com.qtt.sms.model.SendRecordExample;
import com.qtt.sms.service.SendRecordService;
import com.qtt.sms.util.T;

@Controller
public class SendRecordController {
	@Autowired
	SendRecordService sendRecordService;

    /**
     * 列表
     * @param req
     * @param resp
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/admin/sendrecord/list.do")
    public String list(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //check right
    	int pageNum = T.intValue(req.getParameter("pageNum"), 1);
    	int pageSize = T.intValue(req.getParameter("pageSize"), 25);
    	int agentId = T.intValue(req.getParameter("agnetId"), 0);
    	String smsid = req.getParameter("smsid");
    	SendRecordExample example = new SendRecordExample();
    	SendRecordExample.Criteria criteria = example.createCriteria();
    	if (agentId > 0) {
    		criteria.andAgentIdEqualTo(agentId);
    	}
    	if (!T.isBlank(smsid)) {
    		criteria.andSmsidEqualTo(smsid);
    	}
    	PageInfo<SendRecord> pager = sendRecordService.getPager(example, pageNum, pageSize);
        req.setAttribute("pager", pager);
        return "admin/sendrecord/list";
    }

    @RequestMapping(value="/admin/sendrecord/detail.do")
    public String detail(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //check right
        int id = T.intValue(req.getParameter("id"), -1);
        SendRecord record = sendRecordService.findById(id);
        if (record == null) {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(T.getJsonString(300, "记录不存在"));
            return null;            
        }
        req.setAttribute("entity", record);
        return "admin/sendrecord/detail";    	
    }
}
