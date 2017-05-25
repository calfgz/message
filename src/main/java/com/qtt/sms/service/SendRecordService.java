package com.qtt.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qtt.sms.mapper.SendRecordMapper;
import com.qtt.sms.model.SendRecord;
import com.qtt.sms.model.SendRecordExample;
import com.qtt.sms.util.T;

@Service
public class SendRecordService {
	@Autowired
	SendRecordMapper sendRecordMapper;
	
	public int save(SendRecord record) {
		return sendRecordMapper.insert(record);
	}
	
	public SendRecord findById(int id) {
		return sendRecordMapper.selectByPrimaryKey(id);
	}
	
	public SendRecord findBySmsId(String smsid) {
		SendRecordExample example = new SendRecordExample();
		example.createCriteria().andSmsidEqualTo(smsid);
		List<SendRecord> list = sendRecordMapper.selectByExample(example);
		SendRecord record = null;
		if (!list.isEmpty()) {
			record = list.get(0);
		}
		return record;
	}
	
	public int updateAll(SendRecord record) {
		return sendRecordMapper.updateByPrimaryKey(record);
	}
	
	public int updateNoNull(SendRecord record) {
		return sendRecordMapper.updateByPrimaryKeySelective(record);
	}
	
	public PageInfo<SendRecord> getPager(SendRecordExample example, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		PageHelper.orderBy("send_at desc");
		List<SendRecord> list = sendRecordMapper.selectByExample(example);
		PageInfo<SendRecord> pager = new PageInfo<SendRecord>(list);
		return pager;
	}
	
	public List<SendRecord> getList(SendRecordExample example, int top, String orderBy) {
		if(top > 0) {
			PageHelper.startPage(1, top);
		}
		if(!T.isBlank(orderBy)) {
			PageHelper.orderBy(orderBy);
		}
		List<SendRecord> list = sendRecordMapper.selectByExample(example);
		return list;
	}
    
    /**
     * 获取已获取轻码云回执但没被查询的记录
     * @param agentId
     * @param count
     * @return
     */
    public List<SendRecord> getListByQueryStatus(int agentId, int count) {
        List<SendRecord> list = null;
        SendRecordExample example = new SendRecordExample();
        SendRecordExample.Criteria criteria = example.createCriteria();
        criteria.andAgentIdEqualTo(agentId).andQueryStatusEqualTo(0).andRespStatusGreaterThan(-1);
        list = getList(example, count, "send_at asc");
        return list;        
    }
    
    /**
     * 批量更新
     * @param record
     * @param example
     * @return
     */
    public int updateByExampleNoNull(SendRecord record, SendRecordExample example) {
    	return sendRecordMapper.updateByExampleSelective(record, example);
    }
    
    /**
     * 获取未返回回执信息的数量
     * @return
     */
    public int countUnRespStatus() {
    	SendRecordExample example = new SendRecordExample();
    	SendRecordExample.Criteria criteria = example.createCriteria();
    	criteria.andRespStatusEqualTo(-1);
    	criteria.andSendAtGreaterThan(T.addDay(T.getNow(), -1));
    	int count = sendRecordMapper.countByExample(example);
    	return count;
    }
}
