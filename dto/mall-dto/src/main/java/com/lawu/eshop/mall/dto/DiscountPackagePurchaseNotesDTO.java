package com.lawu.eshop.mall.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 优惠套餐购买须知BO
 * 
 * @author jiangxinjun
 * @date 2017年7月31日
 */
@ApiModel
public class DiscountPackagePurchaseNotesDTO {
	
    /**
     * 主键
     */
	@ApiModelProperty(value = "主键", required = true)
    private Integer id;
	
    /**
     * 购买须知选项
     */
	@ApiModelProperty(value = "购买须知选项", required = true)
    private String note;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}