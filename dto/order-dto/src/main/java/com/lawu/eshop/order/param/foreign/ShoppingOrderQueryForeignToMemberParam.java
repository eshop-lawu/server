package com.lawu.eshop.order.param.foreign;

import com.lawu.eshop.order.constants.ShoppingOrderStatusToMemberEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModelProperty;

/**
 * 购物订单查询参数 api暴露给app参数
 * 
 * @author Sunny
 * @date 2017/4/6
 */
public class ShoppingOrderQueryForeignToMemberParam extends AbstractPageParam {

	/**
	 * 订单状态
	 */
	@ApiModelProperty(name = "orderStatus", required = false, value = "订单状态|默认全部|PENDING_PAYMENT 待付款|BE_SHIPPED 待发货|TO_BE_RECEIVED 待收货|BE_EVALUATED 待评价")
	private ShoppingOrderStatusToMemberEnum orderStatus;

	/**
	 * 搜索订单的关键字
	 */
	@ApiModelProperty(name = "keyword", required = false, value = "搜索订单的关键字(订单号|商品名称)")
	private String keyword;

	public ShoppingOrderStatusToMemberEnum getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(ShoppingOrderStatusToMemberEnum orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
