package com.qtt.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qtt.sms.mapper.RechargeLogMapper;
import com.qtt.sms.model.RechargeLog;
import com.qtt.sms.model.RechargeLogExample;

@Service
public class RechargeLogService {
	@Autowired
	RechargeLogMapper rechargeLogMapper;
	
	public int save(RechargeLog rechargeLog) {
		return rechargeLogMapper.insert(rechargeLog);
	}
    
    public PageInfo<RechargeLog> getPager(RechargeLogExample example, int pageNum, int pageSize) {
    	PageHelper.startPage(pageNum, pageSize);
    	PageHelper.orderBy("id desc");
    	List<RechargeLog> list = rechargeLogMapper.selectByExample(example);
    	PageInfo<RechargeLog> page = new PageInfo<RechargeLog>(list);
    	return page;
    }
}
