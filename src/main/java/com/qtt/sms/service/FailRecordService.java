package com.qtt.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qtt.sms.mapper.FailRecordMapper;
import com.qtt.sms.model.FailRecord;
import com.qtt.sms.model.FailRecordExample;

@Service
public class FailRecordService {
	@Autowired
	FailRecordMapper failRecordMapper;
	
	public FailRecord findById(int id) {
		return failRecordMapper.selectByPrimaryKey(id);
	}
	
	public int save(FailRecord failRecord) {
		return failRecordMapper.insert(failRecord);
	}
	
	public PageInfo<FailRecord> getPager(FailRecordExample example, int pageNum, int pageSize) {
    	PageHelper.startPage(pageNum, pageSize);
    	PageHelper.orderBy("id desc");
    	List<FailRecord> list = failRecordMapper.selectByExample(example);
    	PageInfo<FailRecord> pager = new PageInfo<FailRecord>(list);
    	return pager;
	}
}
