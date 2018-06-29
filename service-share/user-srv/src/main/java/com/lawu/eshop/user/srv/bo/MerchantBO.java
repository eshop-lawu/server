package com.lawu.eshop.user.srv.bo;


import java.util.Date;

/**
 * @author meishuquan
 * @date 2017/3/22
 */
public class MerchantBO {

    private Long id;

    private String num;

    private String account;

    private String pwd;

    private String mobile;

    private String headimg;

    private Byte status;

    private Long inviterId;

    private Byte inviterType;

    private Integer level;

    private Date gmtModified;

    private Date gmtCreate;

    private String gtCid;

    private String ryToken;
    
    private String principalName;

    private Boolean isFreeze;

    private String freezeReason;

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

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getInviterId() {
        return inviterId;
    }

    public void setInviterId(Long inviterId) {
        this.inviterId = inviterId;
    }

    public Byte getInviterType() {
        return inviterType;
    }

    public void setInviterType(Byte inviterType) {
        this.inviterType = inviterType;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    public String getGtCid() {
        return gtCid;
    }

    public void setGtCid(String gtCid) {
        this.gtCid = gtCid;
    }

    public String getRyToken() {
        return ryToken;
    }

    public void setRyToken(String ryToken) {
        this.ryToken = ryToken;
    }

	public String getPrincipalName() {
		return principalName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}

    public Boolean getIsFreeze() {
        return isFreeze;
    }

    public void setIsFreeze(Boolean freeze) {
        isFreeze = freeze;
    }

    public String getFreezeReason() {
        return freezeReason;
    }

    public void setFreezeReason(String freezeReason) {
        this.freezeReason = freezeReason;
    }
}
