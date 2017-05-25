package com.qtt.sms.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.qtt.sms.model.User;
import com.qtt.sms.model.UserSession;
import com.qtt.sms.service.FailRecordService;
import com.qtt.sms.service.LogService;
import com.qtt.sms.service.SendRecordService;
import com.qtt.sms.service.UserService;
import com.qtt.sms.service.UserSessionService;
import com.qtt.sms.service.api.ApiService;
import com.qtt.sms.service.api.QingMaYunService;
import com.qtt.sms.util.MD5;
import com.qtt.sms.util.T;

@Controller
public class AdminController {
    @Autowired
    UserSessionService userSessionService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    LogService logService;
    
    @Autowired
    ApiService apiService;
    
    @Autowired
    QingMaYunService qingMaYunService;
    
    @Autowired
    SendRecordService sendRecordService;
    
    @Autowired
    FailRecordService failRecordService;

    @RequestMapping(value="/admin/index.do", method=RequestMethod.GET)
    public String showIndex(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        User user =  userService.getCurrentUser(req, resp);
        if (user == null) {
            return "redirect:/admin/login.do";
        }
        return "admin/index";
    }
    
    @RequestMapping(value="/admin/login.do", method=RequestMethod.GET)
    public String showLogin(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        return "admin/login";
    }
    
    @RequestMapping(value="/admin/login.do", method=RequestMethod.POST)
    public String login(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = userService.findUserByAccount(username);
        if (user == null) {
            return "redirect:/admin/login.do";
        }
        password = MD5.MD5Encode(password);
        if (password.equals(user.getPassword())) {
            Date now = T.getNow();
            UserSession session = new UserSession();
            session.setSessionId(user.getId() + String.valueOf(System.currentTimeMillis()));
            session.setUserId(user.getId());
            session.setCreateAt(now);
            userSessionService.saveSession(session, resp);
            User tmpUser = new User();
            tmpUser.setId(user.getId());;
            tmpUser.setLoginAt(now);
            userService.updateByNoNull(tmpUser);
            logService.save(user.getId(), req);
            return "redirect:/admin/index.do";
        } else {
            return "redirect:/admin/login.do";
        }
    }
    
    @RequestMapping(value="/admin/logout.do")
    public String logout(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        userSessionService.clearSession(req, resp);
        return "redirect:/admin/login.do";
    }
    
    @RequestMapping(value="/admin/password.do", method=RequestMethod.GET)
    public String showPassword(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        User user = userService.getCurrentUser(req, resp);
        req.setAttribute("user", user);
        return "admin/setting/password";
    }
    
    @RequestMapping(value="/admin/password.do", method=RequestMethod.POST)
    public String password(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        String oldPassword = req.getParameter("oldPassword");
        String password = req.getParameter("password");
        User user = userService.getCurrentUser(req, resp);
        JSONObject json = new JSONObject();
        if (user == null) {
            json.put("statusCode", 300);
            json.put("message", "用户未登录");  
        } else {
            if (MD5.MD5Encode(oldPassword).equals(user.getPassword())) {
                password = MD5.MD5Encode(password);
                User tmpUser = new User();
                tmpUser.setId(user.getId());
                tmpUser.setPassword(password);
                userService.updateByNoNull(tmpUser);
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
    
    @RequestMapping(value="/admin/send.do", method=RequestMethod.GET)
    public String showSend(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //check right
        return "admin/setting/send";
    }
    
    @RequestMapping(value="/admin/send.do", method=RequestMethod.POST)
    public String send(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //check right
        String phones = req.getParameter("phones");
        String content = req.getParameter("content");
        String sign = req.getParameter("sign");
        JSONObject json = new JSONObject();
        if(T.isBlank(phones) || T.isBlank(content) || T.isBlank(sign)) {
            json.put("statusCode", 300);
            json.put("message", "发送数据错误");
            json.put("callbackType", "closeCurrent");
        } else {
            content += "【" + sign + "】";
        	json = apiService.sendSmsNotTemplate(1, phones, content);
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
    
    @RequestMapping(value="/admin/userinfo.do", method=RequestMethod.GET)
    public String userinfo(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        String result = qingMaYunService.getAccountInfo();
        JSONObject json = JSONObject.parseObject(result);
        logService.save(req, resp);
        req.setAttribute("json", json);
        return "admin/setting/userinfo";
    }
}
