package com.lawu.eshop.property.dto;

import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.property.constants.ThirdPayStatusEnum;

import io.swagger.annotations.ApiModelProperty;

public class BalanceAndPointListQueryDTO {

	@ApiModelProperty(value = "充值金额")
	private String rechargeMoney;
	
	@ApiModelProperty(value = "充值比例")
	private String currentScale;
	
	@ApiModelProperty(value = "充值所得金额/积分")
	private String money;

	@ApiModelProperty(value = "充值类型")
	private String rechargeType;
	
	@ApiModelProperty(value = "充值渠道")
	private TransactionPayTypeEnum channel;

	@ApiModelProperty(value = "状态")
	private ThirdPayStatusEnum status;
	
	@ApiModelProperty(value = "充值订单号")
	private String rechargeNumber;
	
	@ApiModelProperty(value = "第三方订单号")
	private String thirdNumber;
	
	@ApiModelProperty(value = "充值时间")
	private String gmtCreate;

	public String getRechargeMoney() {
		return rechargeMoney;
	}

	public void setRechargeMoney(String rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
	}

	public String getCurrentScale() {
		return currentScale;
	}

	public void setCurrentScale(String currentScale) {
		this.currentScale = currentScale;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getRechargeType() {
		return rechargeType;
	}

	public void setRechargeType(String rechargeType) {
		this.rechargeType = rechargeType;
	}

	public TransactionPayTypeEnum getChannel() {
		return channel;
	}

	public void setChannel(TransactionPayTypeEnum channel) {
		this.channel = channel;
	}

	public ThirdPayStatusEnum getStatus() {
		return status;
	}

	public void setStatus(ThirdPayStatusEnum status) {
		this.status = status;
	}

	public String getRechargeNumber() {
		return rechargeNumber;
	}

	public void setRechargeNumber(String rechargeNumber) {
		this.rechargeNumber = rechargeNumber;
	}

	public String getThirdNumber() {
		return thirdNumber;
	}

	public void setThirdNumber(String thirdNumber) {
		this.thirdNumber = thirdNumber;
	}

	public String getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(String gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	
}
