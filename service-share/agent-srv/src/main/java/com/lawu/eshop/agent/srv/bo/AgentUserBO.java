package com.lawu.eshop.agent.srv.bo;

import java.util.Date;

/**
 * @author zhangyong
 * @date 2017/8/9.
 */
public class AgentUserBO {
    private Long id;

    /**
     *
     * 代理商编号
     * agent_user.num
     *
     * @mbg.generated
     */
    private String num;

    /**
     *
     * 登录账号
     * agent_user.account
     *
     * @mbg.generated
     */
    private String account;

    /**
     *
     * 登录密码
     * agent_user.pwd
     *
     * @mbg.generated
     */
    private String pwd;

    /**
     *
     * 手机号码
     * agent_user.mobile
     *
     * @mbg.generated
     */
    private String mobile;

    /**
     *
     * 姓名
     * agent_user.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * 地区路径
     * agent_user.region_path
     *
     * @mbg.generated
     */
    private String regionPath;

    /**
     *
     * 区域名称
     * agent_user.region_name
     *
     * @mbg.generated
     */
    private String regionName;

    /**
     *
     * 状态 (0--无效，1--有效)
     * agent_user.status
     *
     * @mbg.generated
     */
    private Byte status;

    /**
     *
     * 修改时间
     * agent_user.gmt_modified
     *
     * @mbg.generated
     */
    private Date gmtModified;

    /**
     *
     * 创建时间
     * agent_user.gmt_create
     *
     * @mbg.generated
     */
    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegionPath() {
        return regionPath;
    }

    public void setRegionPath(String regionPath) {
        this.regionPath = regionPath;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
