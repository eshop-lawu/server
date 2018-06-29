package com.lawu.eshop.order.srv.domain.extend;

import java.io.Serializable;

/**
 * 
 * @author Sunny
 * @date 2017年5月19日
 */
public class NotShippedDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数量
     */
    private Long count;
    
    /**
     * 用户编号
     */
    private String merchantNum;

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getMerchantNum() {
		return merchantNum;
	}

	public void setMerchantNum(String merchantNum) {
		this.merchantNum = merchantNum;
	}
}
