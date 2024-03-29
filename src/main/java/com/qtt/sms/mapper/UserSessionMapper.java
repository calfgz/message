package com.qtt.sms.mapper;

import com.qtt.sms.model.UserSession;
import com.qtt.sms.model.UserSessionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserSessionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_session
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    int countByExample(UserSessionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_session
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    int deleteByExample(UserSessionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_session
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    int deleteByPrimaryKey(String sessionId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_session
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    int insert(UserSession record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_session
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    int insertSelective(UserSession record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_session
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    List<UserSession> selectByExample(UserSessionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_session
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    UserSession selectByPrimaryKey(String sessionId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_session
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    int updateByExampleSelective(@Param("record") UserSession record, @Param("example") UserSessionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_session
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    int updateByExample(@Param("record") UserSession record, @Param("example") UserSessionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_session
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    int updateByPrimaryKeySelective(UserSession record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_session
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    int updateByPrimaryKey(UserSession record);
}