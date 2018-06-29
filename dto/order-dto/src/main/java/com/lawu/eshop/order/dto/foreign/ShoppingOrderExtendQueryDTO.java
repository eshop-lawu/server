package com.lawu.eshop.order.dto.foreign;

import java.math.BigDecimal;
import java.util.List;

import com.lawu.eshop.order.constants.ShoppingOrderStatusEnum;

import io.swagger.annotations.ApiModelProperty;

public class ShoppingOrderExtendQueryDTO {

	/**
	 * 购物订单id
	 */
	@ApiModelProperty(value = "购物订单id", required = true)
	private Long id;

	/**
	 * 商家ID
	 */
	@ApiModelProperty(value = "商家ID", required = true)
	private Long merchantId;
	
	/**
	 * 商家编号
	 */
	@ApiModelProperty(value = "商家编号", required = true)
	private String merchantNum;
	
	/**
	 * 商家门店ID
	 */
	@ApiModelProperty(value = "商家门店ID", required = true)
	private Long merchantStoreId;
	
	/**
	 * 商家名称
	 */
	@ApiModelProperty(value = "商家名称", required = true)
	private String merchantName;

	/**
	 * 运费
	 */
	@ApiModelProperty(value = "运费", required = true)
	private BigDecimal freightPrice;
	
	/**
	 * 订单总价
	 */
	@ApiModelProperty(value = "订单总价", required = true)
	private BigDecimal orderTotalPrice;

	/**
	 * 订单的总状态
	 */
	@ApiModelProperty(value = "订单状态|PENDING 待处理|PENDING_PAYMENT 待付款|BE_SHIPPED 待发货|TO_BE_RECEIVED 待收货|TRADING_SUCCESS 交易成功|CANCEL_TRANSACTION 交易关闭|REFUNDING 退款中", required = true)
	private ShoppingOrderStatusEnum orderStatus;
	
	/**
	 * 是否支持无理由退货
	 */
	@ApiModelProperty(value = "是否支持无理由退货(false-不支持|true-支持)", required = true)
	private Boolean isNoReasonReturn;
	
	/**
	 * 是否需要物流
	 */
	@ApiModelProperty(value = "是否需要物流(false-不需要|true-需要)", required = false)
	private Boolean isNeedsLogistics;
	
	/**
	 * 商品数量
	 */
	@ApiModelProperty(value = "商品数量", required = true)
	private Integer amountOfGoods;
	
    /**
    * 当前订单是否完成
    */
	@ApiModelProperty(value = "当前订单是否完成", required = true)
    private Boolean isDone;
	
	/**
	 * 订单项
	 */
	@ApiModelProperty(value = "订单项", required = true)
	private List<ShoppingOrderItemDTO> items;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantNum() {
		return merchantNum;
	}

	public void setMerchantNum(String merchantNum) {
		this.merchantNum = merchantNum;
	}

	public Long getMerchantStoreId() {
		return merchantStoreId;
	}

	public void setMerchantStoreId(Long merchantStoreId) {
		this.merchantStoreId = merchantStoreId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public BigDecimal getFreightPrice() {
		return freightPrice;
	}

	public void setFreightPrice(BigDecimal freightPrice) {
		this.freightPrice = freightPrice;
	}

	public BigDecimal getOrderTotalPrice() {
		return orderTotalPrice;
	}

	public void setOrderTotalPrice(BigDecimal orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}

	public ShoppingOrderStatusEnum getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(ShoppingOrderStatusEnum orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Boolean getIsNoReasonReturn() {
		return isNoReasonReturn;
	}

	public void setIsNoReasonReturn(Boolean isNoReasonReturn) {
		this.isNoReasonReturn = isNoReasonReturn;
	}
	
	public Boolean getIsNeedsLogistics() {
		return isNeedsLogistics;
	}

	public void setIsNeedsLogistics(Boolean isNeedsLogistics) {
		this.isNeedsLogistics = isNeedsLogistics;
	}

	public Integer getAmountOfGoods() {
		return amountOfGoods;
	}

	public void setAmountOfGoods(Integer amountOfGoods) {
		this.amountOfGoods = amountOfGoods;
	}

	public Boolean getIsDone() {
		return isDone;
	}

	public void setIsDone(Boolean isDone) {
		this.isDone = isDone;
	}

	public List<ShoppingOrderItemDTO> getItems() {
		return items;
	}

	public void setItems(List<ShoppingOrderItemDTO> items) {
		this.items = items;
	}

}