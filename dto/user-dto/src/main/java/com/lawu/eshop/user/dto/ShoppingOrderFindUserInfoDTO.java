package com.lawu.eshop.user.dto;

import java.util.List;

/**
 * @author Sunny
 * @date 2017/4/10
 */
public class ShoppingOrderFindUserInfoDTO {

	/**
	 * 用户编号
	 */
	private String memberNum;
	
	/**
	 * 用户昵称
	 */
    private String memberNickname;
	
	/**
	 * 商家信息
	 */
	private List<ShoppingOrderFindMerchantInfoDTO> shoppingOrderFindMerchantInfoDTOList;

	public String getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public List<ShoppingOrderFindMerchantInfoDTO> getShoppingOrderFindMerchantInfoDTOList() {
		return shoppingOrderFindMerchantInfoDTOList;
	}

	public void setShoppingOrderFindMerchantInfoDTOList(List<ShoppingOrderFindMerchantInfoDTO> shoppingOrderFindMerchantInfoDTOList) {
		this.shoppingOrderFindMerchantInfoDTOList = shoppingOrderFindMerchantInfoDTOList;
	}
	
}
