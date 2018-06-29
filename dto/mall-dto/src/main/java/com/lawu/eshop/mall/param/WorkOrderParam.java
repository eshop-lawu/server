package com.lawu.eshop.mall.param;

import com.lawu.eshop.mall.constants.WorkOrderTypeEnum;

public class WorkOrderParam {
	
	private String account;
	
	private String userNum;
	
	private WorkOrderTypeEnum workOrderTypeEnum;
	
	private String name;
	
	private String content;

	public String getAccount() {
		return account;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public void setAccount(String account) {
		this.account = account;
	}


	public WorkOrderTypeEnum getWorkOrderTypeEnum() {
		return workOrderTypeEnum;
	}

	public void setWorkOrderTypeEnum(WorkOrderTypeEnum workOrderTypeEnum) {
		this.workOrderTypeEnum = workOrderTypeEnum;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
