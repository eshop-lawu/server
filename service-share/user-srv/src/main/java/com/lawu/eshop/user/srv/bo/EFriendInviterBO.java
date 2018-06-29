package com.lawu.eshop.user.srv.bo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.user.dto.MerchantStatusEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * 我的E友
 */
public class EFriendInviterBO {

	@ApiModelProperty(value = "名称(商家店铺名称/E店商家或E店用户)", required = true)
	private String titleName;

	@ApiModelProperty(value = "注册日期", required = true)
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date gmtCreate;

	@ApiModelProperty(value = "用户头像或商家logo图片", required = true)
	private String headImg;

	@ApiModelProperty(value = "VIP等级")
	private Integer level;

	@ApiModelProperty(value = "用户昵称或商家责任人姓名")
	private String name;

	@ApiModelProperty(value = "账号", required = true)
	private String account;

	@ApiModelProperty(value = "分享人数", required = true)
	private Integer inviterCount;

	@ApiModelProperty(value = "区域或详细地址", required = true)
	private String regionAddress;
	
    	@ApiModelProperty(name = "merchantStatus", value = "门店状态:MERCHANT_STATUS_UNCHECK:未审核,MERCHANT_STATUS_CHECKED:审核通过,MERCHANT_STATUS_CHECK_FAILED:审核不通过,MERCHANT_STATUS_NOT_MONEY:未提交保证金,MERCHANT_STATUS_GIVE_MONEY_CHECK:已提交保证金待财务核实,MERCHANT_STATUS_GIVE_MONEY_CHECK_FAILED:财务审核不通过")
	private MerchantStatusEnum merchantStatus;

	@ApiModelProperty(value = "总分享人数()", required = true)
	private Integer totalInviteCount;

	private String userNum;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Integer getInviterCount() {
		return inviterCount;
	}

	public void setInviterCount(Integer inviterCount) {
		this.inviterCount = inviterCount;
	}

	public String getRegionAddress() {
		return regionAddress;
	}

	public void setRegionAddress(String regionAddress) {
		this.regionAddress = regionAddress;
	}

	public MerchantStatusEnum getMerchantStatus() {
		return merchantStatus;
	}

	public void setMerchantStatus(MerchantStatusEnum merchantStatus) {
		this.merchantStatus = merchantStatus;
	}

	public Integer getTotalInviteCount() {
		return totalInviteCount;
	}

	public void setTotalInviteCount(Integer totalInviteCount) {
		this.totalInviteCount = totalInviteCount;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
}
