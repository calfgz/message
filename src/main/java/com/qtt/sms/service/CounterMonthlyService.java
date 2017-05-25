package com.qtt.sms.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qtt.sms.mapper.CounterMonthlyMapper;
import com.qtt.sms.model.CounterMonthly;
import com.qtt.sms.model.CounterMonthlyExample;
import com.qtt.sms.model.vo.CounterMonthlyVo;
import com.qtt.sms.util.T;

@Service
public class CounterMonthlyService {
	private static Log log = LogFactory.getLog(CounterMonthlyService.class); 
	
	@Autowired
	CounterMonthlyMapper counterMonthlyMapper;
	
	public int save(CounterMonthly counterDaily) {
		return counterMonthlyMapper.insert(counterDaily);
	}
	
	public List<CounterMonthly> selectByExample(CounterMonthlyExample example) {
		return counterMonthlyMapper.selectByExample(example);
	}
	
	/**
	 * 批量删除
	 * @param example
	 * @return
	 */
	public int deleteByExample(CounterMonthlyExample example) {
		return counterMonthlyMapper.deleteByExample(example);
	}
	
	/**
	 * 批量插入
	 * @param list
	 * @return
	 */
	public int insertBatch(List<CounterMonthly> list) {
		return counterMonthlyMapper.insertBatch(list);
	}
	
	public PageInfo<CounterMonthly> getPager(CounterMonthlyExample example, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		PageHelper.orderBy("rec_date desc");
		List<CounterMonthly> list = counterMonthlyMapper.selectByExample(example);
		PageInfo<CounterMonthly> pager = new PageInfo<CounterMonthly>(list);
		return pager;
	}
	
	/**
	 * 查询数量
	 * @param date
	 * @return
	 */
	public int countTheDay(Date date) {
		CounterMonthlyExample example = new CounterMonthlyExample();
		CounterMonthlyExample.Criteria criteria = example.createCriteria();
		criteria.andRecDateEqualTo(T.getTheDay(date));
		int count = counterMonthlyMapper.countByExample(example);
		return count;
	}
	
	/**
	 * 批量删除
	 * @param date
	 * @return
	 */
	public int deleteTheDay(Date date) {
		CounterMonthlyExample example = new CounterMonthlyExample();
		CounterMonthlyExample.Criteria criteria = example.createCriteria();
		criteria.andRecDateEqualTo(T.getTheDay(date));
		int count = counterMonthlyMapper.deleteByExample(example);
		return count;
	}
	
	/**
	 * 统计
	 * @param date
	 */
	public void monthlyCounter(Date date) {
		CounterMonthlyVo vo = new CounterMonthlyVo();
		vo.setStartDate(T.getMonthStart(date));
		vo.setEndDate(T.getMonthEnd(date));
		List<CounterMonthly> list = counterMonthlyMapper.selectCounterMonthlyFromAll(vo);
		int delete = deleteTheDay(date);
		log.info("delete count : " + delete);
		int inster = insertBatch(list);
		log.info("inster count : " + inster);
	}
}
