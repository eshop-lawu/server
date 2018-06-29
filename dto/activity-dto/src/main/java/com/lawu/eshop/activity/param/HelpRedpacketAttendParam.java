package com.lawu.eshop.activity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;

/**
 * 报名活动
 * 
 * @Description
 * @author zhangrc
 * @date 2017年12月28日
 */
@ApiModel 
public class HelpRedpacketAttendParam {

    /**
     * 红包活动id
     */
    @ApiParam(value = "红包活动id", required = true)
    private Integer activityId;
    
	/**
	 * 用户编号
	 */
	@ApiParam(name = "userNum", required = true, value = "用户编号")
	private String userNum;

	/**
	 * 用户账号
	 */
	@ApiParam(name = "account", required = true, value = "用户账号")
	private String account;

	/**
	 * 微信wxOpenid
	 */
	@ApiParam(name = "wxOpenid", required = true, value = "微信wxOpenid")
	private String wxOpenid;

	/**
	 * 用户昵称
	 */
	@ApiParam(name = "nickname", value = "用户昵称")
	private String nickname;

	/**
	 * 用户图像
	 */
	@ApiParam(name = "headimg", value = "用户图像")
	private String headimg;
	
	public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getWxOpenid() {
		return wxOpenid;
	}

	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}

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

}
