package com.lawu.eshop.user.srv.domain.extend;

import com.lawu.eshop.common.constants.UserSexEnum;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月11日
 */
public class FensCountView {
	
	private Long merchantId;
	
	private Byte sex;
	
	private Integer minAge;
	
	private Integer maxAge;

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Byte getSex() {
		return sex;
	}

	public void setSex(Byte sex) {
		this.sex = sex;
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
