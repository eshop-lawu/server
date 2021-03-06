package com.lawu.eshop.mall.srv.domain;

import java.io.Serializable;
import java.util.Date;

public class WorkOrderDO implements Serializable {
    /**
     *
     * 主键
     * work_order.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * 用户编号
     * work_order.user_num
     *
     * @mbg.generated
     */
    private String userNum;

    /**
     *
     * 账号
     * work_order.account
     *
     * @mbg.generated
     */
    private String account;

    /**
     *
     * 提交人类型 1--用户，2--商家
     * work_order.type
     *
     * @mbg.generated
     */
    private Byte type;

    /**
     *
     * 提交工单人/门店名称
     * work_order.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * 状态 1--未处理，2--已回复，3--不予处理
     * work_order.status
     *
     * @mbg.generated
     */
    private Byte status;

    /**
     *
     * 工单内容
     * work_order.content
     *
     * @mbg.generated
     */
    private String content;

    /**
     *
     * 回复内容
     * work_order.reply_content
     *
     * @mbg.generated
     */
    private String replyContent;

    /**
     *
     * 审核人员ID
     * work_order.auditor_id
     *
     * @mbg.generated
     */
    private Integer auditorId;

    /**
     *
     * 审核人员名称
     * work_order.auditor_name
     *
     * @mbg.generated
     */
    private String auditorName;

    /**
     *
     * 处理时间
     * work_order.gmt_deal
     *
     * @mbg.generated
     */
    private Date gmtDeal;

    /**
     *
     * 修改时间
     * work_order.gmt_modified
     *
     * @mbg.generated
     */
    private Date gmtModified;

    /**
     *
     * 创建时间
     * work_order.gmt_create
     *
     * @mbg.generated
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table work_order
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work_order.id
     *
     * @return the value of work_order.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work_order.id
     *
     * @param id the value for work_order.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work_order.user_num
     *
     * @return the value of work_order.user_num
     *
     * @mbg.generated
     */
    public String getUserNum() {
        return userNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work_order.user_num
     *
     * @param userNum the value for work_order.user_num
     *
     * @mbg.generated
     */
    public void setUserNum(String userNum) {
        this.userNum = userNum == null ? null : userNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work_order.account
     *
     * @return the value of work_order.account
     *
     * @mbg.generated
     */
    public String getAccount() {
        return account;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work_order.account
     *
     * @param account the value for work_order.account
     *
     * @mbg.generated
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work_order.type
     *
     * @return the value of work_order.type
     *
     * @mbg.generated
     */
    public Byte getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work_order.type
     *
     * @param type the value for work_order.type
     *
     * @mbg.generated
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work_order.name
     *
     * @return the value of work_order.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work_order.name
     *
     * @param name the value for work_order.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work_order.status
     *
     * @return the value of work_order.status
     *
     * @mbg.generated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work_order.status
     *
     * @param status the value for work_order.status
     *
     * @mbg.generated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work_order.content
     *
     * @return the value of work_order.content
     *
     * @mbg.generated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work_order.content
     *
     * @param content the value for work_order.content
     *
     * @mbg.generated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work_order.reply_content
     *
     * @return the value of work_order.reply_content
     *
     * @mbg.generated
     */
    public String getReplyContent() {
        return replyContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work_order.reply_content
     *
     * @param replyContent the value for work_order.reply_content
     *
     * @mbg.generated
     */
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent == null ? null : replyContent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work_order.auditor_id
     *
     * @return the value of work_order.auditor_id
     *
     * @mbg.generated
     */
    public Integer getAuditorId() {
        return auditorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work_order.auditor_id
     *
     * @param auditorId the value for work_order.auditor_id
     *
     * @mbg.generated
     */
    public void setAuditorId(Integer auditorId) {
        this.auditorId = auditorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work_order.auditor_name
     *
     * @return the value of work_order.auditor_name
     *
     * @mbg.generated
     */
    public String getAuditorName() {
        return auditorName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work_order.auditor_name
     *
     * @param auditorName the value for work_order.auditor_name
     *
     * @mbg.generated
     */
    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName == null ? null : auditorName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work_order.gmt_deal
     *
     * @return the value of work_order.gmt_deal
     *
     * @mbg.generated
     */
    public Date getGmtDeal() {
        return gmtDeal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work_order.gmt_deal
     *
     * @param gmtDeal the value for work_order.gmt_deal
     *
     * @mbg.generated
     */
    public void setGmtDeal(Date gmtDeal) {
        this.gmtDeal = gmtDeal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work_order.gmt_modified
     *
     * @return the value of work_order.gmt_modified
     *
     * @mbg.generated
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work_order.gmt_modified
     *
     * @param gmtModified the value for work_order.gmt_modified
     *
     * @mbg.generated
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column work_order.gmt_create
     *
     * @return the value of work_order.gmt_create
     *
     * @mbg.generated
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column work_order.gmt_create
     *
     * @param gmtCreate the value for work_order.gmt_create
     *
     * @mbg.generated
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}