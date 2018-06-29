package com.lawu.eshop.property.dto;

import com.lawu.eshop.property.constants.CashStatusEnum;

public class WithdrawCashStatusDTO {
	
    /**
     * 主键
     */
    private Long id;
	
    /**
     * 提现状态
     */
    private CashStatusEnum status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CashStatusEnum getStatus() {
		return status;
	}

	public void setStatus(CashStatusEnum status) {
		this.status = status;
	}
    
}