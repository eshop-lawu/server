package com.lawu.eshop.order.srv.domain.extend;

import java.io.Serializable;

public class ShoppingCartUpdateQuantityDO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    /**
     * 主键
     */
    private Long id;

    /**
     * 数量
     */
    private Integer quantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
    
}