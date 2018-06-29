package com.lawu.eshop.user.srv.bo;


import java.util.Date;

import com.lawu.eshop.user.constants.FreezeTypeEnum;
import com.lawu.eshop.user.constants.UserSexEnum;

/**
 * 会员BO
 *
 * @author Leach
 * @date 2017/3/13
 */
public class MemberBO {

    private Long id;

    private String account;

    private String num;

    private String pwd;

    private String mobile;

    private String name;

    private String nickname;

    private String regionPath;

    private Byte sex;

    private Date birthday;

    private String headimg;

    private Byte status;

    private Long inviterId;

    private Byte inviterType;

    private Integer level;

    private Date gmtModified;

    private Date gmtCreate;
    
    private Integer inviterCount;

    private UserSexEnum userSex;

    private String gtCid;

    private String ryToken;

    private String regionName;

    private Boolean isFreeze;

    private String freezeReason;

    private Byte grade;

    private Integer growthValue;

    private String regOrigin;

    private FreezeTypeEnum freezeTypeEnum;

    public UserSexEnum getUserSex() {
        return userSex;
    }

    public void setUserSex(UserSexEnum userSex) {
        this.userSex = userSex;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNum() {
        return num;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getInviterType() {
        return inviterType;
    }

    public void setInviterType(Byte inviterType) {
        this.inviterType = inviterType;
    }

    public void setNum(String num) {
        this.num = num;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRegionPath() {
        return regionPath;
    }

    public void setRegionPath(String regionPath) {
        this.regionPath = regionPath;
    }


    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public FreezeTypeEnum getFreezeTypeEnum() {
        return freezeTypeEnum;
    }

    public void setFreezeTypeEnum(FreezeTypeEnum freezeTypeEnum) {
        this.freezeTypeEnum = freezeTypeEnum;
    }

    public Long getInviterId() {
        return inviterId;
    }

    public void setInviterId(Long inviterId) {
        this.inviterId = inviterId;
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

	public Integer getInviterCount() {
		return inviterCount;
	}

	public void setInviterCount(Integer inviterCount) {
		this.inviterCount = inviterCount;
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

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
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

    public Byte getGrade() {
        return grade;
    }

    public void setGrade(Byte grade) {
        this.grade = grade;
    }

    public Integer getGrowthValue() {
        return growthValue;
    }

    public void setGrowthValue(Integer growthValue) {
        this.growthValue = growthValue;
    }

    public String getRegOrigin() {
        return regOrigin;
    }

    public void setRegOrigin(String regOrigin) {
        this.regOrigin = regOrigin;
    }
}
