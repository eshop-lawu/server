package com.lawu.eshop.activity.srv.bo;
/** 
 * 
 * @author lihj
 * @date 2018年5月3日
 */

import java.math.BigDecimal;

public class RichDiamondInfoBO {
	
	/**
	 * 已经发放E钻数量
	 */
	private BigDecimal diamondSent;
	
	/**
	 * 已发放幸运值数量
	 */
	private BigDecimal diamondLuckySent;

	public BigDecimal getDiamondSent() {
		return diamondSent;
	}

	public void setDiamondSent(BigDecimal diamondSent) {
		this.diamondSent = diamondSent;
	}

	public BigDecimal getDiamondLuckySent() {
		return diamondLuckySent;
	}

	public void setDiamondLuckySent(BigDecimal diamondLuckySent) {
		this.diamondLuckySent = diamondLuckySent;
	}
	
}
