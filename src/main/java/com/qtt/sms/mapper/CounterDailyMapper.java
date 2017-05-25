package com.qtt.sms.mapper;

import com.qtt.sms.model.CounterDaily;
import com.qtt.sms.model.CounterDailyExample;
import com.qtt.sms.model.vo.CounterDailyVo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CounterDailyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter_daily
     *
     * @mbggenerated Mon Dec 28 18:20:53 CST 2015
     */
    int countByExample(CounterDailyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter_daily
     *
     * @mbggenerated Mon Dec 28 18:20:53 CST 2015
     */
    int deleteByExample(CounterDailyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter_daily
     *
     * @mbggenerated Mon Dec 28 18:20:53 CST 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter_daily
     *
     * @mbggenerated Mon Dec 28 18:20:53 CST 2015
     */
    int insert(CounterDaily record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter_daily
     *
     * @mbggenerated Mon Dec 28 18:20:53 CST 2015
     */
    int insertSelective(CounterDaily record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter_daily
     *
     * @mbggenerated Mon Dec 28 18:20:53 CST 2015
     */
    List<CounterDaily> selectByExample(CounterDailyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter_daily
     *
     * @mbggenerated Mon Dec 28 18:20:53 CST 2015
     */
    CounterDaily selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter_daily
     *
     * @mbggenerated Mon Dec 28 18:20:53 CST 2015
     */
    int updateByExampleSelective(@Param("record") CounterDaily record, @Param("example") CounterDailyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter_daily
     *
     * @mbggenerated Mon Dec 28 18:20:53 CST 2015
     */
    int updateByExample(@Param("record") CounterDaily record, @Param("example") CounterDailyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter_daily
     *
     * @mbggenerated Mon Dec 28 18:20:53 CST 2015
     */
    int updateByPrimaryKeySelective(CounterDaily record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter_daily
     *
     * @mbggenerated Mon Dec 28 18:20:53 CST 2015
     */
    int updateByPrimaryKey(CounterDaily record);
    
    /**
     * 批量插入
     * @param list
     * @return
     */
    int insertBatch(List<CounterDaily> list);
    
    /**
     * 统计日计数据
     * @param counterDailyVo
     * @return
     */
    List<CounterDaily> selectDailyCounterFromAll(CounterDailyVo counterDailyVo);
    
    /**
     * 统计日计数据
     * @param counterDailyVo
     * @return
     */
    List<CounterDaily> selectDailyCounterTotal(CounterDailyVo counterDailyVo);
}