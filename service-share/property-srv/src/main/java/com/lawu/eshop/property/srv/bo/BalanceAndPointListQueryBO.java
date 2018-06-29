package com.lawu.eshop.property.srv.bo;

import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.property.constants.ThirdPayStatusEnum;

public class BalanceAndPointListQueryBO {

	private String rechargeMoney;
	
	private String currentScale;
	
	private String money;

	private String rechargeType;
	
	private TransactionPayTypeEnum channel;

	private ThirdPayStatusEnum status;
	
	private String rechargeNumber;
	
	private String thirdNumber;
	
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
