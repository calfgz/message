package com.qtt.sms.model;

import java.util.Date;

public class AgentLog {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column agent_log.id
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column agent_log.agent_id
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    private Integer agentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column agent_log.ip
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    private String ip;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column agent_log.log_at
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    private Date logAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column agent_log.uri
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    private String uri;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column agent_log.query_
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    private String query;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column agent_log.id
     *
     * @return the value of agent_log.id
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column agent_log.id
     *
     * @param id the value for agent_log.id
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column agent_log.agent_id
     *
     * @return the value of agent_log.agent_id
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    public Integer getAgentId() {
        return agentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column agent_log.agent_id
     *
     * @param agentId the value for agent_log.agent_id
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column agent_log.ip
     *
     * @return the value of agent_log.ip
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    public String getIp() {
        return ip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column agent_log.ip
     *
     * @param ip the value for agent_log.ip
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column agent_log.log_at
     *
     * @return the value of agent_log.log_at
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    public Date getLogAt() {
        return logAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column agent_log.log_at
     *
     * @param logAt the value for agent_log.log_at
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    public void setLogAt(Date logAt) {
        this.logAt = logAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column agent_log.uri
     *
     * @return the value of agent_log.uri
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    public String getUri() {
        return uri;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column agent_log.uri
     *
     * @param uri the value for agent_log.uri
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column agent_log.query_
     *
     * @return the value of agent_log.query_
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    public String getQuery() {
        return query;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column agent_log.query_
     *
     * @param query the value for agent_log.query_
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    public void setQuery(String query) {
        this.query = query;
    }
}