package com.qtt.sms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;
import com.qtt.sms.model.RechargeLog;
import com.qtt.sms.model.RechargeLogExample;
import com.qtt.sms.service.RechargeLogService;
import com.qtt.sms.util.T;


@Controller
public class RechargeController {
	@Autowired
	RechargeLogService rechargeLogService;

    /**
     * 列表
     * @param req
     * @param resp
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/admin/recharge/list.do")
    public String list(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //check right
    	int pageNum = T.intValue(req.getParameter("pageNum"), 1);
    	int pageSize = T.intValue(req.getParameter("pageSize"), 25);
    	int agentId = T.intValue(req.getParameter("agnetId"), 0);
    	RechargeLogExample example = new RechargeLogExample();
    	RechargeLogExample.Criteria criteria = example.createCriteria();
    	if (agentId > 0) {
    		criteria.andAgentIdEqualTo(agentId);
    	}
    	PageInfo<RechargeLog> pager = rechargeLogService.getPager(example, pageNum, pageSize);
        req.setAttribute("pager", pager);
        return "admin/recharge/list";
    }
}
