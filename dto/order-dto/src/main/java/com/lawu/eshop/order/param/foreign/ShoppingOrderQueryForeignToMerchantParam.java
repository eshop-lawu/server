package com.lawu.eshop.order.param.foreign;

import com.lawu.eshop.order.constants.ShoppingOrderStatusToMerchantEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModelProperty;

/**
 * 购物订单查询参数 api暴露给app参数
 * 
 * @author Sunny
 * @date 2017/4/6
 */
public class ShoppingOrderQueryForeignToMerchantParam extends AbstractPageParam {

	/**
	 * 订单状态
	 */
	@ApiModelProperty(name = "orderStatus", required = false, value = "订单状态|默认全部|PROCESSING 进行中|PENDING_PAYMENT 待付款|BE_SHIPPED 待发货|TO_BE_RECEIVED 待收货|REFUNDING 退款中|COMPLETED 已完成|CLOSED 已关闭")
	private ShoppingOrderStatusToMerchantEnum orderStatus;

	/**
	 * 搜索订单的关键字
	 */
	@ApiModelProperty(name = "keyword", required = false, value = "搜索订单的关键字(订单号|收货人名称|用户编号)")
	private String keyword;

	public ShoppingOrderStatusToMerchantEnum getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(ShoppingOrderStatusToMerchantEnum orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
