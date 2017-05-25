package com.qtt.sms.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qtt.sms.mapper.AgentLogMapper;
import com.qtt.sms.model.Agent;
import com.qtt.sms.model.AgentLog;
import com.qtt.sms.model.AgentLogExample;
import com.qtt.sms.util.IpUtils;
import com.qtt.sms.util.T;

@Service
public class AgentLogService {
	@Autowired
	AgentLogMapper agentLogMapper;
	
	@Autowired
	AgentService agentService;
	
	public int save(AgentLog log) {
		return agentLogMapper.insert(log);
	}
	
	public int save(HttpServletRequest req, HttpServletResponse resp) {
		AgentLog log = new AgentLog();
		Agent agent = agentService.getCurrentAgent(req, resp);
		log.setAgentId(agent.getId());
		log.setIp(IpUtils.getIp(req));
		log.setLogAt(T.getNow());
		log.setUri(req.getRequestURI());
		log.setQuery(req.getQueryString());
		return agentLogMapper.insert(log);
	}
	
	public int save(int agentId, HttpServletRequest req) {
		AgentLog log = new AgentLog();
		log.setAgentId(agentId);
		log.setIp(IpUtils.getIp(req));
		log.setLogAt(T.getNow());
		log.setUri(req.getRequestURI());
		log.setQuery(req.getQueryString());
		return agentLogMapper.insert(log);
	}
	
	public int save(HttpServletRequest req, HttpServletResponse resp, String query) {
		AgentLog log = new AgentLog();
		Agent agent = agentService.getCurrentAgent(req, resp);
		log.setAgentId(agent.getId());
		log.setIp(IpUtils.getIp(req));
		log.setLogAt(T.getNow());
		log.setUri(req.getRequestURI());
		log.setQuery(query);
		return agentLogMapper.insert(log);
	}
	
	public PageInfo<AgentLog> getPager(AgentLogExample example, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		PageHelper.orderBy("id desc");
		List<AgentLog> list = agentLogMapper.selectByExample(example);
		PageInfo<AgentLog> pager = new PageInfo<AgentLog>(list);
		return pager;
	}
}
