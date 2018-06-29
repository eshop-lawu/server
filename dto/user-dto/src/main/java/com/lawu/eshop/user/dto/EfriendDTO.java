package com.lawu.eshop.user.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.user.constants.UserSexEnum;

import io.swagger.annotations.ApiModelProperty;

public class EfriendDTO {

    @ApiModelProperty(value = "昵称")
    private String nickname;
    
    @ApiModelProperty(value = "电话")
    private String mobile;

    @ApiModelProperty(value = "头像")
    private String headimg;
    
    @ApiModelProperty(value = "等级")
    private Integer level;
    
    @ApiModelProperty(value = "受邀者总数")
    private Integer inviterCount;
    
    @ApiModelProperty(value = "注册时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gmtCreate;


    private UserSexEnum userSex;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }


    public UserSexEnum getUserSex() {
        return userSex;
    }

    public void setUserSex(UserSexEnum userSex) {
        this.userSex = userSex;
    }

	public Integer getInviterCount() {
		return inviterCount;
	}

	public void setInviterCount(Integer inviterCount) {
		this.inviterCount = inviterCount;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	

}
