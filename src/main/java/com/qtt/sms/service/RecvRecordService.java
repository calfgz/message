package com.qtt.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qtt.sms.mapper.RecvRecordMapper;
import com.qtt.sms.model.RecvRecord;
import com.qtt.sms.model.RecvRecordExample;

@Service
public class RecvRecordService {
	@Autowired
	RecvRecordMapper recvRecordMapper;
	
	public int save(RecvRecord record) {
		return recvRecordMapper.insert(record);
	}
	
	public PageInfo<RecvRecord> getPager(RecvRecordExample example, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		PageHelper.orderBy("id desc");
		List<RecvRecord> list = recvRecordMapper.selectByExample(example);
		PageInfo<RecvRecord> pager = new PageInfo<RecvRecord>(list);
		return pager;
	}
}
