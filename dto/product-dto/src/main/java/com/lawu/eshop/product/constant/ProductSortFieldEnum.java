package com.lawu.eshop.product.constant;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年4月17日 下午6:20:52
 *
 */
public enum ProductSortFieldEnum {
	
	TOTAL_INVENTORY("total_inventory"),		//库存
	TOTAL_SALES_VOLUME("total_sales_volume"),//销量
	CDATE("gmt_create");			//创建时间
	
	private String val;
	ProductSortFieldEnum(String val){
		this.val = val;
	}
	
	public static ProductSortFieldEnum getEnum(String val){
		ProductSortFieldEnum[] values = ProductSortFieldEnum.values();
		for (ProductSortFieldEnum object : values) {
			if (object.val.equals(val)) {
				return object;
			}
		}
		return null;
	}

	public String getVal() {
		return val;
	}
	
}
