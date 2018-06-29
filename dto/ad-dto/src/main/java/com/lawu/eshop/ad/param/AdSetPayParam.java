package com.lawu.eshop.ad.param;

import com.lawu.eshop.ad.constants.AdPayTypeEnum;

public class AdSetPayParam {
	
	private Long id;
	
	private AdPayTypeEnum payTypeEnum;
	
	private String thirdNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AdPayTypeEnum getPayTypeEnum() {
		return payTypeEnum;
	}

	public void setPayTypeEnum(AdPayTypeEnum payTypeEnum) {
		this.payTypeEnum = payTypeEnum;
	}

	public String getThirdNumber() {
		return thirdNumber;
	}

	public void setThirdNumber(String thirdNumber) {
		this.thirdNumber = thirdNumber;
	}

	
	
	

}
