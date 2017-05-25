package com.qtt.sms.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qtt.sms.mapper.LogMapper;
import com.qtt.sms.model.Log;
import com.qtt.sms.model.LogExample;
import com.qtt.sms.model.User;
import com.qtt.sms.util.IpUtils;
import com.qtt.sms.util.T;


@Service
public class LogService {
	@Autowired
	LogMapper logMapper;
	
	@Autowired
	UserService userService;
	
	public int save(Log log) {
		return logMapper.insert(log);
	}
	
	public int save(HttpServletRequest req, HttpServletResponse resp) {
		Log log = new Log();
		User user = userService.getCurrentUser(req, resp);
		log.setUserId(user.getId());
		log.setIp(IpUtils.getIp(req));
		log.setLogAt(T.getNow());
		log.setUri(req.getRequestURI());
		log.setQuery(req.getQueryString());
		return logMapper.insert(log);
	}
	
	public int save(int userId, HttpServletRequest req) {
		Log log = new Log();
		log.setUserId(userId);
		log.setIp(IpUtils.getIp(req));
		log.setLogAt(T.getNow());
		log.setUri(req.getRequestURI());
		log.setQuery(req.getQueryString());
		return logMapper.insert(log);
	}
	
	public int save(HttpServletRequest req, HttpServletResponse resp, String query) {
		Log log = new Log();
		User user = userService.getCurrentUser(req, resp);
		log.setUserId(user.getId());
		log.setIp(IpUtils.getIp(req));
		log.setLogAt(T.getNow());
		log.setUri(req.getRequestURI());
		log.setQuery(query);
		return logMapper.insert(log);
	}
    
    public PageInfo<Log> getPageUser(LogExample example, int pageNum, int pageSize) {
    	PageHelper.startPage(pageNum, pageSize);
    	PageHelper.orderBy("id desc");
    	List<Log> list = logMapper.selectByExample(example);
    	PageInfo<Log> page = new PageInfo<Log>(list);
    	return page;
    }
}
