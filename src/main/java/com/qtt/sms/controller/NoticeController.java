package com.qtt.sms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;
import com.qtt.sms.model.Notice;
import com.qtt.sms.model.NoticeExample;
import com.qtt.sms.service.NoticeService;
import com.qtt.sms.util.T;

@Controller
public class NoticeController {
	@Autowired
	NoticeService noticeService;

    /**
     * 列表
     * @param req
     * @param resp
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/admin/notice/list.do")
    public String list(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //check right
    	int pageNum = T.intValue(req.getParameter("pageNum"), 1);
    	int pageSize = T.intValue(req.getParameter("pageSize"), 25);
    	NoticeExample example = new NoticeExample();
    	PageInfo<Notice> pager = noticeService.getPager(example, pageNum, pageSize);
        req.setAttribute("pager", pager);
        return "admin/notice/list";
    }
}
