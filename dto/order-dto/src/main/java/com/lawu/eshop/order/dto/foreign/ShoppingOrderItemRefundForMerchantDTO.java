package com.lawu.eshop.order.dto.foreign;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.order.constants.RefundStatusEnum;

import io.swagger.annotations.ApiModelProperty;

public class ShoppingOrderItemRefundForMerchantDTO {

	/**
	 * 购物订单退款详情id
	 */
	@ApiModelProperty(value = "购物订单退款详情id", required = true)
	private Long shoppingRefundDetailId;
	
    /**
     * 收货人姓名
     */
	@ApiModelProperty(value = "收货人姓名", required = true)
    private String consigneeName;
	
	/**
	 * 商品名称
	 */
	@ApiModelProperty(value = "商品名称", required = true)
	private String productName;

	/**
	 * 商品型号名称
	 */
	@ApiModelProperty(value = "商品型号名称", required = true)
	private String productModelName;

	/**
	 * 商品特征图片
	 */
	@ApiModelProperty(value = "商品特征图片", required = true)
	private String productFeatureImage;

	/**
	 * 退款状态
	 */
	@ApiModelProperty(value = "退款状态|TO_BE_CONFIRMED 待商家确认|FILL_RETURN_ADDRESS 填写退货地址|TO_BE_RETURNED 待退货|TO_BE_REFUNDED 待退款|REFUND_SUCCESSFULLY 退款成功|REFUND_FAILED 退款失败|PLATFORM_INTERVENTION 平台介入")
	private RefundStatusEnum refundStatus;
	
	/**
	 * 申请时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "申请时间", required = true)
	private Date gmtCreate;
	
	/**
	 * 购物订单项id
	 */
	@ApiModelProperty(value = "购物订单项id", required = true)
	private Long id;
	
	public Long getShoppingRefundDetailId() {
		return shoppingRefundDetailId;
	}

	public void setShoppingRefundDetailId(Long shoppingRefundDetailId) {
		this.shoppingRefundDetailId = shoppingRefundDetailId;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductModelName() {
		return productModelName;
	}

	public void setProductModelName(String productModelName) {
		this.productModelName = productModelName;
	}

	public String getProductFeatureImage() {
		return productFeatureImage;
	}

	public void setProductFeatureImage(String productFeatureImage) {
		this.productFeatureImage = productFeatureImage;
	}

	public RefundStatusEnum getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(RefundStatusEnum refundStatus) {
		this.refundStatus = refundStatus;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}