package com.lawu.eshop.user.param;

import java.util.List;

/**
 * 
 * @author Sunny
 * @date 2017年5月3日
 */
public class ShoppingOrderFindUserInfoParam {
	
	/**
	 * 商家id列表
	 */
	private List<Long> merchantIdList;
	
	/**
	 * 用户id
	 */
	private Long memberId;

	public List<Long> getMerchantIdList() {
		return merchantIdList;
	}

	public void setMerchantIdList(List<Long> merchantIdList) {
		this.merchantIdList = merchantIdList;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
}
