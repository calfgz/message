package com.qtt.sms.model;

import java.util.Date;

public class User {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.id
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.account
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    private String account;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.password
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.name
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.create_at
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    private Date createAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.login_at
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    private Date loginAt;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.id
     *
     * @return the value of user.id
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.id
     *
     * @param id the value for user.id
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.account
     *
     * @return the value of user.account
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    public String getAccount() {
        return account;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.account
     *
     * @param account the value for user.account
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.password
     *
     * @return the value of user.password
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.password
     *
     * @param password the value for user.password
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.name
     *
     * @return the value of user.name
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.name
     *
     * @param name the value for user.name
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.create_at
     *
     * @return the value of user.create_at
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.create_at
     *
     * @param createAt the value for user.create_at
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.login_at
     *
     * @return the value of user.login_at
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    public Date getLoginAt() {
        return loginAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.login_at
     *
     * @param loginAt the value for user.login_at
     *
     * @mbggenerated Mon Dec 28 12:35:31 CST 2015
     */
    public void setLoginAt(Date loginAt) {
        this.loginAt = loginAt;
    }
}