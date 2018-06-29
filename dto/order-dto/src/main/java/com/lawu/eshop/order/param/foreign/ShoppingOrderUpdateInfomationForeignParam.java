package com.lawu.eshop.order.param.foreign;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.lawu.eshop.order.constants.ShoppingOrderStatusEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * 运营平台修改订单资料参数 api暴露给运营平台参数
 * 
 * @author Sunny
 * @date 2017/4/15
 */
public class ShoppingOrderUpdateInfomationForeignParam {

	/**
	 * 订单状态
	 */
	@ApiModelProperty(value = "订单状态|PENDING 待处理|PENDING_PAYMENT 待付款|BE_SHIPPED 待发货|TO_BE_RECEIVED 待收货|TRADING_SUCCESS 交易成功|CANCEL_TRANSACTION 交易关闭|REFUNDING 退款中", required = false)
	@NotNull(message="订单状态不能为空")
	private ShoppingOrderStatusEnum orderStatus;
	
	/**
	 * 收货人姓名
	 */
	@ApiModelProperty(value = "收货人姓名", required = true)
	@NotNull(message="收货人姓名不能为空")
	private String consigneeName;
	
	/**
	 * 收货人地址
	 */
	@ApiModelProperty(value = "收货人地址", required = true)
	@NotNull(message="收货人地址不能为空")
	private String consigneeAddress;

	/**
	 * 收货人手机号码
	 */
	@ApiModelProperty(value = "收货人手机号码", required = true)
	@Pattern(regexp="\\d+", message="收货人手机号码格式不正确")
	private String consigneeMobile;
	
	/**
	 * 运单编号
	 */
	@ApiModelProperty(value = "运单编号", required = true)
	@NotNull(message="运单编号不能为空")
	private String waybillNum;

	/**
	 * 快递公司id
	 */
	@ApiModelProperty(value = "快递公司id", required = true)
	@NotNull(message="快递公司id不能为空")
	private Integer expressCompanyId;

	public ShoppingOrderStatusEnum getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(ShoppingOrderStatusEnum orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public String getConsigneeAddress() {
		return consigneeAddress;
	}

	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}

	public String getConsigneeMobile() {
		return consigneeMobile;
	}

	public void setConsigneeMobile(String consigneeMobile) {
		this.consigneeMobile = consigneeMobile;
	}

	public String getWaybillNum() {
		return waybillNum;
	}

	public void setWaybillNum(String waybillNum) {
		this.waybillNum = waybillNum;
	}

	public Integer getExpressCompanyId() {
		return expressCompanyId;
	}

	public void setExpressCompanyId(Integer expressCompanyId) {
		this.expressCompanyId = expressCompanyId;
	}

}
