package com.lawu.eshop.user.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.common.constants.MemberGradeEnum;
import com.lawu.eshop.user.constants.UserSexEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Leach
 * @date 2017/3/13
 */
@ApiModel
public class UserDTO {

    @ApiModelProperty(value = "主键", required = true)
    private Long id;

    @ApiModelProperty(name = "num", value = "编号")
    private String num;

    @ApiModelProperty(name = "account", value = "账号")
    private String account;

    @ApiModelProperty(name = "nickname", value = "昵称")
    private String nickname;

    @ApiModelProperty(name = "regionPath", value = "地区路径")
    private String regionPath;
    @ApiModelProperty(name = "regionName", value = "地区名称")
    private String regionName;

    @ApiModelProperty(name = "birthday", value = "生日")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;

    @ApiModelProperty(name = "headimg", value = "头像")
    private String headimg;

    @ApiModelProperty(name = "userSex", value = "性别")
    private UserSexEnum userSex;

    @ApiModelProperty(name = "level", value = "等级")
    private Integer level;

    @ApiModelProperty(name = "level", value = "个推CID")
    private String gtCid;

    @ApiModelProperty(name = "level", value = "融云token")
    private String ryToken;

    private Boolean isFreeze;

    @ApiModelProperty(name = "gradeEnum", value = "消费等级枚举")
    private MemberGradeEnum gradeEnum;

    @ApiModelProperty(name = "grade", value = "消费等级")
    private Byte grade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserSexEnum getUserSex() {
        return userSex;
    }

    public void setUserSex(UserSexEnum userSex) {
        this.userSex = userSex;
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

    public MemberGradeEnum getGradeEnum() {
        return gradeEnum;
    }

    public void setGradeEnum(MemberGradeEnum gradeEnum) {
        this.gradeEnum = gradeEnum;
    }

    public Byte getGrade() {
        return grade;
    }

    public void setGrade(Byte grade) {
        this.grade = grade;
    }
}
