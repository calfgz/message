package com.qtt.sms.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qtt.sms.mapper.AgentMapper;
import com.qtt.sms.model.Agent;
import com.qtt.sms.model.AgentExample;
import com.qtt.sms.model.AgentSession;
import com.qtt.sms.model.RechargeLog;
import com.qtt.sms.util.MD5;
import com.qtt.sms.util.Signature;
import com.qtt.sms.util.T;

@Service
public class AgentService {
	@Autowired
	AgentMapper agentMapper;

	@Autowired
	RechargeLogService rechargeLogService;
    
    @Autowired
    AgentSessionService agentSessionService;
    
    public Agent findAgentById(int id) {
    	return agentMapper.selectByPrimaryKey(id);
    }
    
    public int save(Agent agent) {
    	return agentMapper.insert(agent);
    }
    
    public int updateAll(Agent agent) {
    	return agentMapper.updateByPrimaryKey(agent);
    }
    
    public int updateByNoNull(Agent agent) {
    	return agentMapper.updateByPrimaryKeySelective(agent);
    }
    
    public Agent findAgentByAccount(String account) {
    	AgentExample example = new AgentExample();
    	example.createCriteria().andAccountEqualTo(account);
    	List<Agent> list = agentMapper.selectByExample(example);
    	Agent agent = null;
    	if (!list.isEmpty()) {
    		agent = list.get(0);
    	}
    	return agent;
    }
    
    public Agent findAgentByAccId(String accid) {
    	AgentExample example = new AgentExample();
    	example.createCriteria().andAccidEqualTo(accid);
    	List<Agent> list = agentMapper.selectByExample(example);
    	Agent agent = null;
    	if (!list.isEmpty()) {
    		agent = list.get(0);
    	}
    	return agent;
    }
    
    public Agent getCurrentAgent(HttpServletRequest req, HttpServletResponse resp) {
        AgentSession session = agentSessionService.recognize(req, resp);
        if (session != null) {
            return agentMapper.selectByPrimaryKey(session.getAgentId());
        }
        return null;
    }
    
    public PageInfo<Agent> getPageAgent(AgentExample example, int pageNum, int pageSize) {
    	PageHelper.startPage(pageNum, pageSize);
    	PageHelper.orderBy("id desc");
    	List<Agent> list = agentMapper.selectByExample(example);
    	PageInfo<Agent> page = new PageInfo<Agent>(list);
    	return page;
    }
    
    /**
     * 创建客户，自动生成accid, appKey, 加密password
     * @param agent
     * @return
     */
    public int create(Agent agent) {
		Date now = T.getNow();
  		agent.setAccid(MD5.MD5Encode(agent.getAccount()));
  		agent.setPassword(MD5.MD5Encode(agent.getPassword()));
  		agent.setKeyValue(Signature.getRandomStringByLength(32));
  		agent.setStatus(1);
  		agent.setBalance(0);
  		agent.setGivenBalance(0);
  		agent.setLoginAt(now);
  		agent.setRegAt(now);
  		agent.setUpdateAt(now);
  		return agentMapper.insert(agent);
	}
    
    //批量更新
    public int updateByExampleNoNull(Agent agent, AgentExample example) {
    	return agentMapper.updateByExampleSelective(agent, example);
    }
  	
  	//验证密码
  	public boolean validPassword(Agent agent, String password) {
  	    boolean result = false;
  	    if (!T.isBlank(password)) {
  	        result = agent.getPassword().equals(MD5.MD5Encode(password));
  	    }
  	    return result;	    
  	}
  	
  	/**
  	 * 充值,记录充值日志
  	 * @param rechargeLog
  	 * @param agent
  	 */
  	public void recharge(RechargeLog rechargeLog, Agent agent) {  		
  		Agent tmpAgent = new Agent();
  		tmpAgent.setId(agent.getId());
        
        if (rechargeLog.getBalance() > 0) {
        	tmpAgent.setBalance(agent.getBalance() + rechargeLog.getBalance());
        }
        if (rechargeLog.getGivenBalance() > 0) {
        	tmpAgent.setGivenBalance(agent.getGivenBalance() + rechargeLog.getGivenBalance());
        }
        Date now = T.getNow();
        tmpAgent.setUpdateAt(now);
        
        rechargeLog.setCreateAt(now);
        rechargeLogService.save(rechargeLog);
        updateByNoNull(tmpAgent);
  	}
}
