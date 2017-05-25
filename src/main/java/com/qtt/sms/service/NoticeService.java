package com.qtt.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qtt.sms.mapper.NoticeMapper;
import com.qtt.sms.model.Notice;
import com.qtt.sms.model.NoticeExample;

@Service
public class NoticeService {
	@Autowired
	NoticeMapper noticeMapper;
	
	public int save(Notice record) {
		return noticeMapper.insert(record);
	}
	
	public Notice findById(int id) {
		return noticeMapper.selectByPrimaryKey(id);
	}
	
	public int updateAll(Notice record) {
		return noticeMapper.updateByPrimaryKey(record);
	}
	
	public int updateNoNull(Notice record) {
		return noticeMapper.updateByPrimaryKeySelective(record);
	}
	
	public PageInfo<Notice> getPager(NoticeExample example, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		PageHelper.orderBy("send_at desc");
		List<Notice> list = noticeMapper.selectByExample(example);
		PageInfo<Notice> pager = new PageInfo<Notice>(list);
		return pager;
	}
	
	public List<Notice> getList(NoticeExample example, int top, String orderBy) {
		if(top > 0) {
			PageHelper.startPage(1, top);
		}
		;
		if(orderBy != null) {
			PageHelper.orderBy(orderBy);
		}
		List<Notice> list = noticeMapper.selectByExample(example);
		return list;
	}
    
    /**
     * 批量更新
     * @param record
     * @param example
     * @return
     */
    public int updateByExampleNoNull(Notice record, NoticeExample example) {
    	return noticeMapper.updateByExampleSelective(record, example);
    }
}
