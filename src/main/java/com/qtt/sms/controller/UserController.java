package com.qtt.sms.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.qtt.sms.model.User;
import com.qtt.sms.model.UserExample;
import com.qtt.sms.service.LogService;
import com.qtt.sms.service.UserService;
import com.qtt.sms.util.MD5;
import com.qtt.sms.util.T;


@Controller
public class UserController {
    @Autowired
    UserService userService; 
    
    @Autowired
    LogService logService;

    @RequestMapping(value="/admin/user/list.do")
    public String list(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //check right
    	int pageNum = T.intValue(req.getParameter("pageNum"), 1);
    	int pageSize = T.intValue(req.getParameter("pageSize"), 20);
    	int userId = T.intValue(req.getParameter("userId"), 0);
    	String account = req.getParameter("acccount");
    	UserExample example = new UserExample();
    	UserExample.Criteria criteria = example.createCriteria();
    	if (userId > 0) {
    		criteria.andIdEqualTo(userId);
    	}
    	if (!T.isBlank(account)) {
    		criteria.andAccountEqualTo(account);
    	}
    	PageInfo<User> pager = userService.getPageUser(example, pageNum, pageSize);
        req.setAttribute("pager", pager);
        return "admin/user/list";
    }
    
    @RequestMapping(value="/admin/user/create.do", method=RequestMethod.GET)
    public String showCreate(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.setAttribute("method", "create");
    	return "admin/user/edit";
    }
    
    @RequestMapping(value="/admin/user/create.do", method=RequestMethod.POST)
    public String create(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    	String account = req.getParameter("account");
    	String password = req.getParameter("password");
    	String name = req.getParameter("name");
    	if (T.isBlank(account) || T.isBlank(password) || T.isBlank(name)) {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(T.getJsonString(300, "数据不能为空"));
            return null;            		
    	}
    	if (userService.findUserByAccount(account) != null) {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(T.getJsonString(300, "用户已存在"));
            return null;            		    		
    	}
    	try {
	    	Date now = T.getNow();
	    	User user = new User();
	    	user.setPassword(MD5.MD5Encode(password));
	    	user.setAccount(account);
	    	user.setName(name);
	    	user.setCreateAt(now);
	    	userService.save(user);
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
		json.put("navTabId", "list-user");
		json.put("callbackType", "closeCurrent");
		resp.getWriter().println(json.toJSONString());
    	return null;
    }
}
