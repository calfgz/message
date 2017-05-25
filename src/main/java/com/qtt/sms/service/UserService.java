package com.qtt.sms.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qtt.sms.mapper.UserMapper;
import com.qtt.sms.model.User;
import com.qtt.sms.model.UserExample;
import com.qtt.sms.model.UserSession;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    
    @Autowired
    UserSessionService userSessionService;
    
    public int save(User user) {
    	return userMapper.insert(user);
    }
    
    public int updateAll(User user) {
    	return userMapper.updateByPrimaryKey(user);
    }
    
    public int updateByNoNull(User user) {
    	return userMapper.updateByPrimaryKeySelective(user);
    }
    
    public User findUserByAccount(String account) {
    	UserExample example = new UserExample();
    	example.createCriteria().andAccountEqualTo(account);
        List<User> list = userMapper.selectByExample(example);
        User user = null;
        if (!list.isEmpty()) {
            user = list.get(0);
        }
        return user;
    }
    
    public User getCurrentUser(HttpServletRequest req, HttpServletResponse resp) {
        UserSession session = userSessionService.recognize(req, resp);
        if (session != null) {
            return userMapper.selectByPrimaryKey(session.getUserId());
        }
        return null;
    }
    
    public PageInfo<User> getPageUser(UserExample example, int pageNum, int pageSize) {
    	PageHelper.startPage(pageNum, pageSize);
        PageHelper.orderBy("id desc");
    	List<User> list = userMapper.selectByExample(example);
    	PageInfo<User> page = new PageInfo<User>(list);
    	//System.out.println(page);
    	return page;
    }
}
