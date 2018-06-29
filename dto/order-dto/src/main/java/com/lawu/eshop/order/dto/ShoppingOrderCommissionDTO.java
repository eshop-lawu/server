package com.lawu.eshop.order.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;

/**
 * 
 * @author Sunny
 * @date 2017年4月25日
 */
@ApiModel
public class ShoppingOrderCommissionDTO {
    
	/**
     * 订单id
     */
    private Long id;
	
    /**
    * 会员编号
    */
    private String memberNum;
    
    /**
    * 商家编号
    */
    private String merchantNum;
    
	/**
	 * 实际支付给商家的金额(需要减去退款的商品！)
	 */
	private BigDecimal actualAmount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
	}

	public String getMerchantNum() {
		return merchantNum;
	}

	public void setMerchantNum(String merchantNum) {
		this.merchantNum = merchantNum;
	}

	public BigDecimal getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}
}