package com.qtt.sms.mapper;

import com.qtt.sms.model.RecvRecord;
import com.qtt.sms.model.RecvRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RecvRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recv_record
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    int countByExample(RecvRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recv_record
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    int deleteByExample(RecvRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recv_record
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recv_record
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    int insert(RecvRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recv_record
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    int insertSelective(RecvRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recv_record
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    List<RecvRecord> selectByExample(RecvRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recv_record
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    RecvRecord selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recv_record
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    int updateByExampleSelective(@Param("record") RecvRecord record, @Param("example") RecvRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recv_record
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    int updateByExample(@Param("record") RecvRecord record, @Param("example") RecvRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recv_record
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    int updateByPrimaryKeySelective(RecvRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recv_record
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    int updateByPrimaryKey(RecvRecord record);
}