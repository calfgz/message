package com.qtt.sms.mapper;

import com.qtt.sms.model.Template;
import com.qtt.sms.model.TemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TemplateMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table template
     *
     * @mbggenerated Tue Mar 22 15:08:04 CST 2016
     */
    int countByExample(TemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table template
     *
     * @mbggenerated Tue Mar 22 15:08:04 CST 2016
     */
    int deleteByExample(TemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table template
     *
     * @mbggenerated Tue Mar 22 15:08:04 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table template
     *
     * @mbggenerated Tue Mar 22 15:08:04 CST 2016
     */
    int insert(Template record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table template
     *
     * @mbggenerated Tue Mar 22 15:08:04 CST 2016
     */
    int insertSelective(Template record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table template
     *
     * @mbggenerated Tue Mar 22 15:08:04 CST 2016
     */
    List<Template> selectByExampleWithBLOBs(TemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table template
     *
     * @mbggenerated Tue Mar 22 15:08:04 CST 2016
     */
    List<Template> selectByExample(TemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table template
     *
     * @mbggenerated Tue Mar 22 15:08:04 CST 2016
     */
    Template selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table template
     *
     * @mbggenerated Tue Mar 22 15:08:04 CST 2016
     */
    int updateByExampleSelective(@Param("record") Template record, @Param("example") TemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table template
     *
     * @mbggenerated Tue Mar 22 15:08:04 CST 2016
     */
    int updateByExampleWithBLOBs(@Param("record") Template record, @Param("example") TemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table template
     *
     * @mbggenerated Tue Mar 22 15:08:04 CST 2016
     */
    int updateByExample(@Param("record") Template record, @Param("example") TemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table template
     *
     * @mbggenerated Tue Mar 22 15:08:04 CST 2016
     */
    int updateByPrimaryKeySelective(Template record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table template
     *
     * @mbggenerated Tue Mar 22 15:08:04 CST 2016
     */
    int updateByPrimaryKeyWithBLOBs(Template record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table template
     *
     * @mbggenerated Tue Mar 22 15:08:04 CST 2016
     */
    int updateByPrimaryKey(Template record);
}