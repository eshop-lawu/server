package com.lawu.eshop.user.param;

import com.lawu.eshop.common.constants.UserSexEnum;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月11日
 */
public class FensCountQuery {
	
	private Long merchantId;
	
	private UserSexEnum sexEnum;
	
	private Integer minAge;
	
	private Integer maxAge;

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public UserSexEnum getSexEnum() {
		return sexEnum;
	}

	public void setSexEnum(UserSexEnum sexEnum) {
		this.sexEnum = sexEnum;
	}

	public Integer getMinAge() {
		return minAge;
	}

	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}

	public Integer getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(Integer maxAge) {
		this.maxAge = maxAge;
	}
	
	

}
