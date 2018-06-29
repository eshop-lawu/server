package com.lawu.eshop.order.dto.foreign;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.order.constants.ShoppingOrderStatusEnum;

import io.swagger.annotations.ApiModelProperty;

public class ShoppingOrderQueryToOperatorDTO {

	/**
     * 购物订单id
     */
	@ApiModelProperty(value = "购物订单id", required = true)
    private Long id;
	
    /**
     * 订单编号
     */
	@ApiModelProperty(value = "订单编号", required = true)
    private String orderNum;
	
    /**
     * 收货人姓名
     */
	@ApiModelProperty(value = "收货人姓名", required = true)
    private String consigneeName;
	
	/**
	 * 商品特征图片
	 */
	@ApiModelProperty(value = "商品特征图片", required = true)
	private String productFeatureImage;
	
	/**
	 * 订单的总状态
	 */
	@ApiModelProperty(value = "订单状态|PENDING 待处理|PENDING_PAYMENT 待付款|BE_SHIPPED 待发货|TO_BE_RECEIVED 待收货|TRADING_SUCCESS 交易成功|CANCEL_TRANSACTION 交易关闭|REFUNDING 退款中", required = true)
	private ShoppingOrderStatusEnum orderStatus;
	
    /**
     * 创建时间
     */
	@JsonFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "创建时间", required = true)
    private Date gmtCreate;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public String getProductFeatureImage() {
		return productFeatureImage;
	}

	public void setProductFeatureImage(String productFeatureImage) {
		this.productFeatureImage = productFeatureImage;
	}

	public ShoppingOrderStatusEnum getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(ShoppingOrderStatusEnum orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	
}