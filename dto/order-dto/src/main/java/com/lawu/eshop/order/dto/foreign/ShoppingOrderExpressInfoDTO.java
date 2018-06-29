package com.lawu.eshop.order.dto.foreign;

import io.swagger.annotations.ApiModelProperty;

public class ShoppingOrderExpressInfoDTO {
	
    /**
     * 运单编号
     */
	@ApiModelProperty("运单编号")
    private String waybillNum;

    /**
     * 快递公司名称
     */
	@ApiModelProperty("快递公司名称")
    private String expressCompanyName;
    
	/**
	 * 快递公司id
	 */
	@ApiModelProperty("快递公司id")
	private Integer expressCompanyId;
	
	/**
	 * 商品特征图片
	 */
	@ApiModelProperty(value = "商品特征图片", required = true)
	private String productFeatureImage;
	
	/**
	 * 数量
	 */
	@ApiModelProperty(value = "数量", required = true)
	private Integer totalQuantity;

	public String getWaybillNum() {
		return waybillNum;
	}

	public void setWaybillNum(String waybillNum) {
		this.waybillNum = waybillNum;
	}

	public String getExpressCompanyName() {
		return expressCompanyName;
	}

	public void setExpressCompanyName(String expressCompanyName) {
		this.expressCompanyName = expressCompanyName;
	}

	public Integer getExpressCompanyId() {
		return expressCompanyId;
	}

	public void setExpressCompanyId(Integer expressCompanyId) {
		this.expressCompanyId = expressCompanyId;
	}

	public String getProductFeatureImage() {
		return productFeatureImage;
	}

	public void setProductFeatureImage(String productFeatureImage) {
		this.productFeatureImage = productFeatureImage;
	}

	public Integer getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Integer totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	
}