package com.qtt.sms.model;

import java.util.Date;

public class SendRecord {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column send_record.id
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column send_record.smsid
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    private String smsid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column send_record.agent_id
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    private Integer agentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column send_record.query_status
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    private Integer queryStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column send_record.phones
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    private String phones;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column send_record.content
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column send_record.count
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    private Integer count;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column send_record.fail_count
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    private Integer failCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column send_record.resp_status
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    private Integer respStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column send_record.resp_message
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    private String respMessage;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column send_record.send_at
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    private Date sendAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column send_record.passageway_id
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    private Integer passagewayId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column send_record.template_id
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    private String templateId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column send_record.type
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    private Integer type;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column send_record.id
     *
     * @return the value of send_record.id
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column send_record.id
     *
     * @param id the value for send_record.id
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column send_record.smsid
     *
     * @return the value of send_record.smsid
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    public String getSmsid() {
        return smsid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column send_record.smsid
     *
     * @param smsid the value for send_record.smsid
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    public void setSmsid(String smsid) {
        this.smsid = smsid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column send_record.agent_id
     *
     * @return the value of send_record.agent_id
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    public Integer getAgentId() {
        return agentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column send_record.agent_id
     *
     * @param agentId the value for send_record.agent_id
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column send_record.query_status
     *
     * @return the value of send_record.query_status
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    public Integer getQueryStatus() {
        return queryStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column send_record.query_status
     *
     * @param queryStatus the value for send_record.query_status
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    public void setQueryStatus(Integer queryStatus) {
        this.queryStatus = queryStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column send_record.phones
     *
     * @return the value of send_record.phones
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    public String getPhones() {
        return phones;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column send_record.phones
     *
     * @param phones the value for send_record.phones
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    public void setPhones(String phones) {
        this.phones = phones;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column send_record.content
     *
     * @return the value of send_record.content
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column send_record.content
     *
     * @param content the value for send_record.content
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column send_record.count
     *
     * @return the value of send_record.count
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    public Integer getCount() {
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column send_record.count
     *
     * @param count the value for send_record.count
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column send_record.fail_count
     *
     * @return the value of send_record.fail_count
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    public Integer getFailCount() {
        return failCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column send_record.fail_count
     *
     * @param failCount the value for send_record.fail_count
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column send_record.resp_status
     *
     * @return the value of send_record.resp_status
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    public Integer getRespStatus() {
        return respStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column send_record.resp_status
     *
     * @param respStatus the value for send_record.resp_status
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    public void setRespStatus(Integer respStatus) {
        this.respStatus = respStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column send_record.resp_message
     *
     * @return the value of send_record.resp_message
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    public String getRespMessage() {
        return respMessage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column send_record.resp_message
     *
     * @param respMessage the value for send_record.resp_message
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    public void setRespMessage(String respMessage) {
        this.respMessage = respMessage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column send_record.send_at
     *
     * @return the value of send_record.send_at
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    public Date getSendAt() {
        return sendAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column send_record.send_at
     *
     * @param sendAt the value for send_record.send_at
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    public void setSendAt(Date sendAt) {
        this.sendAt = sendAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column send_record.passageway_id
     *
     * @return the value of send_record.passageway_id
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    public Integer getPassagewayId() {
        return passagewayId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column send_record.passageway_id
     *
     * @param passagewayId the value for send_record.passageway_id
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    public void setPassagewayId(Integer passagewayId) {
        this.passagewayId = passagewayId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column send_record.template_id
     *
     * @return the value of send_record.template_id
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    public String getTemplateId() {
        return templateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column send_record.template_id
     *
     * @param templateId the value for send_record.template_id
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column send_record.type
     *
     * @return the value of send_record.type
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column send_record.type
     *
     * @param type the value for send_record.type
     *
     * @mbggenerated Mon Mar 28 10:41:33 CST 2016
     */
    public void setType(Integer type) {
        this.type = type;
    }    
    
    //queryStatus; //0：未获取，1：已获取
    public String getQueryStatusDesc() {
        if (this.queryStatus == 0) {
            return "未查询";
        } else {
            return "已查询";
        }
    }
    //respStatus;  //回执状态 -1:未获取, 0:成功, 1:失败, 2:未知
    public String getRespStatusDesc() {
        String result = "";
        switch (this.respStatus) {
        case -1: result = "未获取"; break;
        case 0: result = "成功"; break;
        case 1: result = "失败"; break;
        case 2: result = "未知"; break;
        default: result = "未知"; break;
        }
        return result;
    }
    
    public String getTypeDesc() {
        String result = "";
        switch (this.type) {
        case 1: result = "行业邮件短信"; break;
        case 2: result = "营销邮件短信"; break;
        case 3: result = "行业短信"; break;
        case 4: result = "营销短信"; break;
        default: result = "未知"; break;
        }
        return result;    	
    }
}