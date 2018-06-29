package com.lawu.eshop.mq.dto.order;

import java.io.Serializable;

/**
 * 更新库存DTO
 * 
 * @author jiangxinjun
 * @createDate 2017年11月24日
 * @updateDate 2017年11月24日
 */
public class ProductModeUpdateInventoryDTO implements Serializable {
    
    private static final long serialVersionUID = 2505963000435878237L;

    /**
	 * 商品模型id
	 */
	private Long prodecutModelId;
	
    /**
     * 活动商品型号id
     */
    private Long activityProductModelId;
	
	/**
	 * 数量
	 */
	private Integer quantity;

	public Long getProdecutModelId() {
		return prodecutModelId;
	}

	public void setProdecutModelId(Long prodecutModelId) {
		this.prodecutModelId = prodecutModelId;
	}
	
	public Long getActivityProductModelId() {
        return activityProductModelId;
    }

    public void setActivityProductModelId(Long activityProductModelId) {
        this.activityProductModelId = activityProductModelId;
    }

    public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
