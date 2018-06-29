package com.lawu.eshop.order.constants;

/**
 * 订单查询-排序枚举类
 * 
 * @author Sunny
 * @date 2017年4月27日
 */
public enum ShoppingOrderSortEnum {
	
	/**
	 * 订单编号
	 */
	orderNum("order_num"),
	
	/**
	 * 订单状态
	 */
	orderStatus("order_status"),
	
	/**
	 * 收货人姓名
	 */
	consigneeName("consignee_name"),
	
	/**
	 * 创建时间
	 */
	gmtCreate("gmt_create");
	
	private String databaseField;
	
	ShoppingOrderSortEnum(String databaseField) {
		this.databaseField = databaseField;
	}
	
	public String getDatabaseField() {
		return databaseField;
	}

}
