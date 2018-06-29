package com.lawu.eshop.order.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.eshop.order.constants.RefundStatusEnum;
import com.lawu.eshop.order.constants.ShoppingOrderStatusEnum;

public class ShoppingOrderItemBO {

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 订单id
	 */
	private Long shoppingOrderId;

	/**
	 * 商品id
	 */
	private Long productId;

	/**
	 * 商品名称
	 */
	private String productName;

	/**
	 * 商品型号id
	 */
	private Long productModelId;
	
    /**
     * 活动商品型号id
     */
    private Long activityProductModelId;
	
	/**
	 * 商品型号名称
	 */
	private String productModelName;

	/**
	 * 商品特征图片
	 */
	private String productFeatureImage;

	/**
	 * 原价
	 */
	private BigDecimal regularPrice;

	/**
	 * 现价
	 */
	private BigDecimal salesPrice;
	
    /**
     * 抵扣积分
     */
    private BigDecimal deductionPoints;

    /**
     * 积分抵扣金额
     */
    private BigDecimal deductionPointsAmount;
	
	/**
	 * 数量
	 */
	private Integer quantity;

	/**
	 * 是否评价(0-未评价|1-已评价)
	 */
	private Boolean isEvaluation;

	/**
	 * 是否支持退换货(0-否1-是)
	 */
	private Boolean isAllowRefund;

	/**
	 * 订单项状态
	 */
	private ShoppingOrderStatusEnum orderStatus;

	/**
	 * 退款状态
	 */
	private RefundStatusEnum refundStatus;

	/**
	 * 发送提醒的次数
	 */
	private Integer sendTime;

	/**
	 * 创建时间
	 */
	private Date gmtCreate;

	/**
	 * 修改时间
	 */
	private Date gmtModified;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getShoppingOrderId() {
		return shoppingOrderId;
	}

	public void setShoppingOrderId(Long shoppingOrderId) {
		this.shoppingOrderId = shoppingOrderId;
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

	public Long getActivityProductModelId() {
        return activityProductModelId;
    }

    public void setActivityProductModelId(Long activityProductModelId) {
        this.activityProductModelId = activityProductModelId;
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

	public BigDecimal getDeductionPoints() {
        return deductionPoints;
    }

    public void setDeductionPoints(BigDecimal deductionPoints) {
        this.deductionPoints = deductionPoints;
    }

    public BigDecimal getDeductionPointsAmount() {
        return deductionPointsAmount;
    }

    public void setDeductionPointsAmount(BigDecimal deductionPointsAmount) {
        this.deductionPointsAmount = deductionPointsAmount;
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

	public Integer getSendTime() {
		return sendTime;
	}

	public void setSendTime(Integer sendTime) {
		this.sendTime = sendTime;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
}