package com.qtt.sms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qtt.sms.model.CounterMonthly;
import com.qtt.sms.model.CounterMonthlyExample;
import com.qtt.sms.model.vo.CounterMonthlyVo;

public interface CounterMonthlyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter_monthly
     *
     * @mbggenerated Sat Jan 09 15:15:15 CST 2016
     */
    int countByExample(CounterMonthlyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter_monthly
     *
     * @mbggenerated Sat Jan 09 15:15:15 CST 2016
     */
    int deleteByExample(CounterMonthlyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter_monthly
     *
     * @mbggenerated Sat Jan 09 15:15:15 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter_monthly
     *
     * @mbggenerated Sat Jan 09 15:15:15 CST 2016
     */
    int insert(CounterMonthly record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter_monthly
     *
     * @mbggenerated Sat Jan 09 15:15:15 CST 2016
     */
    int insertSelective(CounterMonthly record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter_monthly
     *
     * @mbggenerated Sat Jan 09 15:15:15 CST 2016
     */
    List<CounterMonthly> selectByExample(CounterMonthlyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter_monthly
     *
     * @mbggenerated Sat Jan 09 15:15:15 CST 2016
     */
    CounterMonthly selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter_monthly
     *
     * @mbggenerated Sat Jan 09 15:15:15 CST 2016
     */
    int updateByExampleSelective(@Param("record") CounterMonthly record, @Param("example") CounterMonthlyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter_monthly
     *
     * @mbggenerated Sat Jan 09 15:15:15 CST 2016
     */
    int updateByExample(@Param("record") CounterMonthly record, @Param("example") CounterMonthlyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter_monthly
     *
     * @mbggenerated Sat Jan 09 15:15:15 CST 2016
     */
    int updateByPrimaryKeySelective(CounterMonthly record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter_monthly
     *
     * @mbggenerated Sat Jan 09 15:15:15 CST 2016
     */
    int updateByPrimaryKey(CounterMonthly record);
    
    /**
     * 批量插入
     * @param list
     * @return
     */
    int insertBatch(List<CounterMonthly> list);
    
    /**
     * 统计月计数据
     * @param counterDailyVo
     * @return
     */
    List<CounterMonthly> selectCounterMonthlyFromAll(CounterMonthlyVo counterMonthlyVo);
}