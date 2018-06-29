package com.lawu.eshop.mall.param;

import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.mall.constants.MessageQueryTypeEnum;

public class MessageStatisticsParam {
	
	private String userNum;
	
	private UserTypeEnum userType;
	
	private MessageQueryTypeEnum typeEnum;

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public UserTypeEnum getUserType() {
		return userType;
	}

	public void setUserType(UserTypeEnum userType) {
		this.userType = userType;
	}

	public MessageQueryTypeEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(MessageQueryTypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}
	
	

}
