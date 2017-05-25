package com.qtt.sms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;
import com.qtt.sms.model.Log;
import com.qtt.sms.service.LogService;
import com.qtt.sms.util.T;

@Controller
public class LogController {
	@Autowired
	LogService logService;

    @RequestMapping(value="/admin/log/list.do")
    public String list(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //check right
    	int pageNum = T.intValue(req.getParameter("pageNum"), 1);
    	int pageSize = T.intValue(req.getParameter("pageSize"), 25);
    	PageInfo<Log> pager = logService.getPageUser(null, pageNum, pageSize);
        req.setAttribute("pager", pager);
        return "admin/log/list";
    }
}
