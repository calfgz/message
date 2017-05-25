package com.qtt.sms.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qtt.sms.mapper.CounterDailyMapper;
import com.qtt.sms.model.CounterDaily;
import com.qtt.sms.model.CounterDailyExample;
import com.qtt.sms.model.vo.CounterDailyVo;
import com.qtt.sms.util.T;

@Service
public class CounterDailyService {
	private static Log log = LogFactory.getLog(CounterDailyService.class); 
	
	@Autowired
	CounterDailyMapper counterDailyMapper;
	
	public int save(CounterDaily counterDaily) {
		return counterDailyMapper.insert(counterDaily);
	}
	
	public List<CounterDaily> selectByExample(CounterDailyExample example) {
		return counterDailyMapper.selectByExample(example);
	}
	
	/**
	 * 批量删除
	 * @param example
	 * @return
	 */
	public int deleteByExample(CounterDailyExample example) {
		return counterDailyMapper.deleteByExample(example);
	}
	
	/**
	 * 批量插入
	 * @param list
	 * @return
	 */
	public int insertBatch(List<CounterDaily> list) {
		return counterDailyMapper.insertBatch(list);
	}
	
	public PageInfo<CounterDaily> getPager(CounterDailyExample example, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		PageHelper.orderBy("rec_date desc");
		List<CounterDaily> list = counterDailyMapper.selectByExample(example);
		PageInfo<CounterDaily> pager = new PageInfo<CounterDaily>(list);
		return pager;
	}
	
	/**
	 * 查询数量
	 * @param date
	 * @return
	 */
	public int countTheDay(Date date) {
		CounterDailyExample example = new CounterDailyExample();
		CounterDailyExample.Criteria criteria = example.createCriteria();
		criteria.andRecDateEqualTo(T.getTheDay(date));
		int count = counterDailyMapper.countByExample(example);
		return count;
	}
	
	/**
	 * 批量删除
	 * @param date
	 * @return
	 */
	public int deleteTheDay(Date date) {
		CounterDailyExample example = new CounterDailyExample();
		CounterDailyExample.Criteria criteria = example.createCriteria();
		criteria.andRecDateEqualTo(T.getTheDay(date));
		int count = counterDailyMapper.deleteByExample(example);
		return count;
	}
	
	public List<CounterDaily> getDailyCounter(Date date) {
		CounterDailyVo vo = new CounterDailyVo();
		vo.setStartDate(T.getTheDay(date));
		vo.setEndDate(T.getLastTheDay(date));
		List<CounterDaily> list = counterDailyMapper.selectDailyCounterFromAll(vo);
		return list;
	}
	
	/**
	 * 统计
	 * @param date
	 */
	public void dailyCounter(Date date) {
		CounterDailyVo vo = new CounterDailyVo();
		vo.setStartDate(T.getTheDay(date));
		vo.setEndDate(T.getLastTheDay(date));
		List<CounterDaily> list = counterDailyMapper.selectDailyCounterFromAll(vo);
		int delete = deleteTheDay(date);
		log.info("delete count : " + delete);
		int inster = insertBatch(list);
		log.info("inster count : " + inster);
		list.clear();
		list = counterDailyMapper.selectDailyCounterTotal(vo);
		insertBatch(list);
	}
}
