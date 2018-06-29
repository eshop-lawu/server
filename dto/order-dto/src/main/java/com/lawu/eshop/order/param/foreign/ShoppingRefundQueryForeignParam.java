package com.lawu.eshop.order.param.foreign;

import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModelProperty;

/**
 * 购物退货记录查询参数 
 * api暴露给app参数
 * 
 * @author Sunny
 * @date 2017/4/6
 */
public class ShoppingRefundQueryForeignParam extends AbstractPageParam {

	@ApiModelProperty(value = "订单号或收货人")
	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
