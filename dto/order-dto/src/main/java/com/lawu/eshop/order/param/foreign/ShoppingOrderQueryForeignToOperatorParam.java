package com.lawu.eshop.order.param.foreign;

import com.lawu.eshop.order.constants.ShoppingOrderSortEnum;
import com.lawu.eshop.order.constants.ShoppingOrderStatusEnum;
import com.lawu.eshop.order.constants.SortOrderEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModelProperty;

/**
 * 购物订单查询参数 api暴露给app参数
 * 
 * @author Sunny
 * @date 2017/4/6
 */
public class ShoppingOrderQueryForeignToOperatorParam extends AbstractPageParam {

	/**
	 * 订单状态
	 */
	@ApiModelProperty(name = "orderStatus", required = false, value = "订单状态|默认全部|PENDING 待处理|PENDING_PAYMENT 待付款|BE_SHIPPED 待发货|TO_BE_RECEIVED 待收货|TRADING_SUCCESS 交易成功|CANCEL_TRANSACTION 交易关闭|REFUNDING 退款中")
	private ShoppingOrderStatusEnum orderStatus;

	/**
	 * 搜索订单的关键字
	 */
	@ApiModelProperty(name = "keyword", required = false, value = "搜索订单的关键字(订单号|收货人名称)")
	private String keyword;
	
	/**
	 * 排序字段
	 */
	@ApiModelProperty(value = "排序字段(orderNum-订单编号|orderStatus-订单状态|consigneeName-收货人姓名|gmtCreate-创建时间)", required = false)
	private ShoppingOrderSortEnum sortName;
	
	/**
	 * 排序类型
	 */
	@ApiModelProperty(value = "排序类型(desc-反序|asc-正序)", required = false)
	private SortOrderEnum sortOrder;
	
	public ShoppingOrderStatusEnum getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(ShoppingOrderStatusEnum orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public ShoppingOrderSortEnum getSortName() {
		return sortName;
	}

	public void setSortName(ShoppingOrderSortEnum sortName) {
		this.sortName = sortName;
	}

	public SortOrderEnum getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(SortOrderEnum sortOrder) {
		this.sortOrder = sortOrder;
	}

}
