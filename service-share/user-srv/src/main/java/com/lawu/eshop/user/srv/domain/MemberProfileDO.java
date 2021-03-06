package com.lawu.eshop.user.srv.domain;

import java.io.Serializable;
import java.util.Date;

public class MemberProfileDO implements Serializable {
    /**
     *
     * 主键，与member主键一致
     * member_profile.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * 邀请会员数(一级)
     * member_profile.invite_member_count
     *
     * @mbg.generated
     */
    private Integer inviteMemberCount;

    /**
     *
     * 邀请会员数(二级)
     * member_profile.invite_member_count2
     *
     * @mbg.generated
     */
    private Integer inviteMemberCount2;

    /**
     *
     * 邀请会员数(三级)
     * member_profile.invite_member_count3
     *
     * @mbg.generated
     */
    private Integer inviteMemberCount3;

    /**
     *
     * 邀请商家数(一级)
     * member_profile.invite_merchant_count
     *
     * @mbg.generated
     */
    private Integer inviteMerchantCount;

    /**
     *
     * 邀请商家数(二级)
     * member_profile.invite_merchant_count2
     *
     * @mbg.generated
     */
    private Integer inviteMerchantCount2;

    /**
     *
     * 邀请商家数(三级)
     * member_profile.invite_merchant_count3
     *
     * @mbg.generated
     */
    private Integer inviteMerchantCount3;

    /**
     *
     * 注册来源IP
     * member_profile.reg_ip
     *
     * @mbg.generated
     */
    private String regIp;

    /**
     *
     * 注册系统版本
     * member_profile.reg_platform_ver
     *
     * @mbg.generated
     */
    private String regPlatformVer;

    /**
     *
     * 注册app版本
     * member_profile.reg_app_ver
     *
     * @mbg.generated
     */
    private String regAppVer;

    /**
     *
     * 最后登录时间
     * member_profile.gmt_last_login
     *
     * @mbg.generated
     */
    private Date gmtLastLogin;

    /**
     *
     * 是否助力瑞奇岛任务
     * member_profile.is_help_rich_task
     *
     * @mbg.generated
     */
    private Boolean isHelpRichTask;

    /**
     *
     * 修改日期
     * member_profile.gmt_modified
     *
     * @mbg.generated
     */
    private Date gmtModified;

    /**
     *
     * 创建日期
     * member_profile.gmt_create
     *
     * @mbg.generated
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table member_profile
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_profile.id
     *
     * @return the value of member_profile.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_profile.id
     *
     * @param id the value for member_profile.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_profile.invite_member_count
     *
     * @return the value of member_profile.invite_member_count
     *
     * @mbg.generated
     */
    public Integer getInviteMemberCount() {
        return inviteMemberCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_profile.invite_member_count
     *
     * @param inviteMemberCount the value for member_profile.invite_member_count
     *
     * @mbg.generated
     */
    public void setInviteMemberCount(Integer inviteMemberCount) {
        this.inviteMemberCount = inviteMemberCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_profile.invite_member_count2
     *
     * @return the value of member_profile.invite_member_count2
     *
     * @mbg.generated
     */
    public Integer getInviteMemberCount2() {
        return inviteMemberCount2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_profile.invite_member_count2
     *
     * @param inviteMemberCount2 the value for member_profile.invite_member_count2
     *
     * @mbg.generated
     */
    public void setInviteMemberCount2(Integer inviteMemberCount2) {
        this.inviteMemberCount2 = inviteMemberCount2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_profile.invite_member_count3
     *
     * @return the value of member_profile.invite_member_count3
     *
     * @mbg.generated
     */
    public Integer getInviteMemberCount3() {
        return inviteMemberCount3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_profile.invite_member_count3
     *
     * @param inviteMemberCount3 the value for member_profile.invite_member_count3
     *
     * @mbg.generated
     */
    public void setInviteMemberCount3(Integer inviteMemberCount3) {
        this.inviteMemberCount3 = inviteMemberCount3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_profile.invite_merchant_count
     *
     * @return the value of member_profile.invite_merchant_count
     *
     * @mbg.generated
     */
    public Integer getInviteMerchantCount() {
        return inviteMerchantCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_profile.invite_merchant_count
     *
     * @param inviteMerchantCount the value for member_profile.invite_merchant_count
     *
     * @mbg.generated
     */
    public void setInviteMerchantCount(Integer inviteMerchantCount) {
        this.inviteMerchantCount = inviteMerchantCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_profile.invite_merchant_count2
     *
     * @return the value of member_profile.invite_merchant_count2
     *
     * @mbg.generated
     */
    public Integer getInviteMerchantCount2() {
        return inviteMerchantCount2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_profile.invite_merchant_count2
     *
     * @param inviteMerchantCount2 the value for member_profile.invite_merchant_count2
     *
     * @mbg.generated
     */
    public void setInviteMerchantCount2(Integer inviteMerchantCount2) {
        this.inviteMerchantCount2 = inviteMerchantCount2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_profile.invite_merchant_count3
     *
     * @return the value of member_profile.invite_merchant_count3
     *
     * @mbg.generated
     */
    public Integer getInviteMerchantCount3() {
        return inviteMerchantCount3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_profile.invite_merchant_count3
     *
     * @param inviteMerchantCount3 the value for member_profile.invite_merchant_count3
     *
     * @mbg.generated
     */
    public void setInviteMerchantCount3(Integer inviteMerchantCount3) {
        this.inviteMerchantCount3 = inviteMerchantCount3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_profile.reg_ip
     *
     * @return the value of member_profile.reg_ip
     *
     * @mbg.generated
     */
    public String getRegIp() {
        return regIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_profile.reg_ip
     *
     * @param regIp the value for member_profile.reg_ip
     *
     * @mbg.generated
     */
    public void setRegIp(String regIp) {
        this.regIp = regIp == null ? null : regIp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_profile.reg_platform_ver
     *
     * @return the value of member_profile.reg_platform_ver
     *
     * @mbg.generated
     */
    public String getRegPlatformVer() {
        return regPlatformVer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_profile.reg_platform_ver
     *
     * @param regPlatformVer the value for member_profile.reg_platform_ver
     *
     * @mbg.generated
     */
    public void setRegPlatformVer(String regPlatformVer) {
        this.regPlatformVer = regPlatformVer == null ? null : regPlatformVer.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_profile.reg_app_ver
     *
     * @return the value of member_profile.reg_app_ver
     *
     * @mbg.generated
     */
    public String getRegAppVer() {
        return regAppVer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_profile.reg_app_ver
     *
     * @param regAppVer the value for member_profile.reg_app_ver
     *
     * @mbg.generated
     */
    public void setRegAppVer(String regAppVer) {
        this.regAppVer = regAppVer == null ? null : regAppVer.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_profile.gmt_last_login
     *
     * @return the value of member_profile.gmt_last_login
     *
     * @mbg.generated
     */
    public Date getGmtLastLogin() {
        return gmtLastLogin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_profile.gmt_last_login
     *
     * @param gmtLastLogin the value for member_profile.gmt_last_login
     *
     * @mbg.generated
     */
    public void setGmtLastLogin(Date gmtLastLogin) {
        this.gmtLastLogin = gmtLastLogin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_profile.is_help_rich_task
     *
     * @return the value of member_profile.is_help_rich_task
     *
     * @mbg.generated
     */
    public Boolean getIsHelpRichTask() {
        return isHelpRichTask;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_profile.is_help_rich_task
     *
     * @param isHelpRichTask the value for member_profile.is_help_rich_task
     *
     * @mbg.generated
     */
    public void setIsHelpRichTask(Boolean isHelpRichTask) {
        this.isHelpRichTask = isHelpRichTask;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_profile.gmt_modified
     *
     * @return the value of member_profile.gmt_modified
     *
     * @mbg.generated
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_profile.gmt_modified
     *
     * @param gmtModified the value for member_profile.gmt_modified
     *
     * @mbg.generated
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_profile.gmt_create
     *
     * @return the value of member_profile.gmt_create
     *
     * @mbg.generated
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_profile.gmt_create
     *
     * @param gmtCreate the value for member_profile.gmt_create
     *
     * @mbg.generated
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}