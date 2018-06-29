package com.lawu.eshop.user.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.user.constants.UserSexEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class MemberDTO {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "编号")
    private String num;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "区域")
    private String regionPath;

    @ApiModelProperty(value = "区域名称")
    private String regionName;

    @ApiModelProperty(value = "出生年月")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;

    @ApiModelProperty(value = "头像")
    private String headimg;

    @ApiModelProperty(value = "等级")
    private Integer level;

    @ApiModelProperty(value = "手机")
    private String mobile;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    @ApiModelProperty(value = "性别：SEX_MALE--男，SEX_FEMALE--女，SEX_SECRET--保密")
    private UserSexEnum userSex;
    
    @ApiModelProperty(value = "是否冻结", required = true)
    private Boolean freeze;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public UserSexEnum getUserSex() {
        return userSex;
    }

    public void setUserSex(UserSexEnum userSex) {
        this.userSex = userSex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Boolean getFreeze() {
        return freeze;
    }

    public void setFreeze(Boolean freeze) {
        this.freeze = freeze;
    }
    
}
