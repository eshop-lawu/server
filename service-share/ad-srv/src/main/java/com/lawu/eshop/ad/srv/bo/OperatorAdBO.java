package com.lawu.eshop.ad.srv.bo;

import com.lawu.eshop.ad.constants.AdEgainTypeEnum;

/**
 * 
 * @author zhangrc
 * @date 2017/08/04
 *
 */
public class OperatorAdBO {
	
	private Long id;
	
	private String title;
	
	private Long merchantId;
	
	private AdEgainTypeEnum typeEnum;
	

	public AdEgainTypeEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(AdEgainTypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
