package com.lawu.eshop.mall.srv.domain;

import java.io.Serializable;
import java.util.Date;

public class SuggestionDO implements Serializable {
    /**
     *
     * 主键
     * suggestion.id
     *
     * @mbg.generated 2017-03-23 17:01:47
     */
    private Integer id;

    /**
     *
     * 用户编号
     * suggestion.user_num
     *
     * @mbg.generated 2017-03-23 17:01:47
     */
    private String userNum;

    /**
     *
     * 建议内容
     * suggestion.content
     *
     * @mbg.generated 2017-03-23 17:01:47
     */
    private String content;

    /**
     *
     * 用户类型，1是商家，2是会员
     * suggestion.user_type
     *
     * @mbg.generated 2017-03-23 17:01:47
     */
    private Byte userType;

    /**
     *
     * 客户端类型，1是android，2是ios
     * suggestion.client_type
     *
     * @mbg.generated 2017-03-23 17:01:47
     */
    private Byte clientType;

    /**
     *
     * 修改时间
     * suggestion.gmt_modified
     *
     * @mbg.generated 2017-03-23 17:01:47
     */
    private Date gmtModified;

    /**
     *
     * 创建时间
     * suggestion.gmt_create
     *
     * @mbg.generated 2017-03-23 17:01:47
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table suggestion
     *
     * @mbg.generated 2017-03-23 17:01:47
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column suggestion.id
     *
     * @return the value of suggestion.id
     *
     * @mbg.generated 2017-03-23 17:01:47
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column suggestion.id
     *
     * @param id the value for suggestion.id
     *
     * @mbg.generated 2017-03-23 17:01:47
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column suggestion.user_num
     *
     * @return the value of suggestion.user_num
     *
     * @mbg.generated 2017-03-23 17:01:47
     */
    public String getUserNum() {
        return userNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column suggestion.user_num
     *
     * @param userNum the value for suggestion.user_num
     *
     * @mbg.generated 2017-03-23 17:01:47
     */
    public void setUserNum(String userNum) {
        this.userNum = userNum == null ? null : userNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column suggestion.content
     *
     * @return the value of suggestion.content
     *
     * @mbg.generated 2017-03-23 17:01:47
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column suggestion.content
     *
     * @param content the value for suggestion.content
     *
     * @mbg.generated 2017-03-23 17:01:47
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column suggestion.user_type
     *
     * @return the value of suggestion.user_type
     *
     * @mbg.generated 2017-03-23 17:01:47
     */
    public Byte getUserType() {
        return userType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column suggestion.user_type
     *
     * @param userType the value for suggestion.user_type
     *
     * @mbg.generated 2017-03-23 17:01:47
     */
    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column suggestion.client_type
     *
     * @return the value of suggestion.client_type
     *
     * @mbg.generated 2017-03-23 17:01:47
     */
    public Byte getClientType() {
        return clientType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column suggestion.client_type
     *
     * @param clientType the value for suggestion.client_type
     *
     * @mbg.generated 2017-03-23 17:01:47
     */
    public void setClientType(Byte clientType) {
        this.clientType = clientType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column suggestion.gmt_modified
     *
     * @return the value of suggestion.gmt_modified
     *
     * @mbg.generated 2017-03-23 17:01:47
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column suggestion.gmt_modified
     *
     * @param gmtModified the value for suggestion.gmt_modified
     *
     * @mbg.generated 2017-03-23 17:01:47
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column suggestion.gmt_create
     *
     * @return the value of suggestion.gmt_create
     *
     * @mbg.generated 2017-03-23 17:01:47
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column suggestion.gmt_create
     *
     * @param gmtCreate the value for suggestion.gmt_create
     *
     * @mbg.generated 2017-03-23 17:01:47
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}