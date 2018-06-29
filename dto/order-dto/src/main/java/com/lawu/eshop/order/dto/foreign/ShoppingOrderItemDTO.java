package com.lawu.eshop.order.dto.foreign;

import java.math.BigDecimal;

import com.lawu.eshop.order.constants.RefundStatusEnum;
import com.lawu.eshop.order.constants.ShoppingOrderStatusEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ShoppingOrderItemDTO {

	/**
	 * 购物订单项id
	 */
	@ApiModelProperty(value = "购物订单项id", required = true)
	private Long id;
	
	/**
	 * 商品id
	 */
	@ApiModelProperty(value = "商品id", required = true)
	private Long productId;

	/**
	 * 商品名称
	 */
	@ApiModelProperty(value = "商品名称", required = true)
	private String productName;

	/**
	 * 商品型号id
	 */
	@ApiModelProperty(value = "商品型号id", required = true)
	private Long productModelId;

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
	 * 原价
	 */
	@ApiModelProperty(value = "原价", required = true)
	private BigDecimal regularPrice;

	/**
	 * 现价
	 */
	@ApiModelProperty(value = "现价", required = true)
	private BigDecimal salesPrice;

	/**
	 * 数量
	 */
	@ApiModelProperty(value = "数量", required = true)
	private Integer quantity;
	
    /**
    * 是否评价
    */
	@ApiModelProperty(value = "是否评价", required = true)
    private Boolean isEvaluation;
	
	/**
	 * 是否支持退换货(0-否1-是)
	 */
	@ApiModelProperty(value = "是否支持退换货(false-否|true-是)", required = true)
	private Boolean isAllowRefund;
	
	/**
	 * 订单项状态
	 */
	@ApiModelProperty(value = "订单状态|PENDING_PAYMENT 待付款|BE_SHIPPED 待发货|TO_BE_RECEIVED 待收货|TRADING_SUCCESS 交易成功|CANCEL_TRANSACTION 交易关闭|REFUNDING 退款中", required = true)
	private ShoppingOrderStatusEnum orderStatus;
	
    /**
    * 退款状态
    */
	@ApiModelProperty(value = "退款状态|TO_BE_CONFIRMED 待商家确认|FILL_RETURN_ADDRESS 商家填写退货地址|TO_BE_RETURNED 待退货|TO_BE_REFUNDED 待退款|REFUND_SUCCESSFULLY 退款成功|REFUND_FAILED 退款失败|PLATFORM_INTERVENTION 平台介入")
    private RefundStatusEnum refundStatus;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getProductModelId() {
		return productModelId;
	}

	public void setProductModelId(Long productModelId) {
		this.productModelId = productModelId;
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

	public BigDecimal getRegularPrice() {
		return regularPrice;
	}

	public void setRegularPrice(BigDecimal regularPrice) {
		this.regularPrice = regularPrice;
	}

	public BigDecimal getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(BigDecimal salesPrice) {
		this.salesPrice = salesPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Boolean getIsEvaluation() {
		return isEvaluation;
	}

	public void setIsEvaluation(Boolean isEvaluation) {
		this.isEvaluation = isEvaluation;
	}

	public Boolean getIsAllowRefund() {
		return isAllowRefund;
	}

	public void setIsAllowRefund(Boolean isAllowRefund) {
		this.isAllowRefund = isAllowRefund;
	}

	public ShoppingOrderStatusEnum getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(ShoppingOrderStatusEnum orderStatus) {
		this.orderStatus = orderStatus;
	}

	public RefundStatusEnum getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(RefundStatusEnum refundStatus) {
		this.refundStatus = refundStatus;
	}
	
}