package com.lawu.eshop.product.constant;

public enum ProductListSortEnum {

	PRODUCT_STATUS_UP_DESC("gmt_up desc"),				//上架时间由近到远
	PRODUCT_STATUS_UP_ASC("gmt_up asc"),				//上架时间由远到近
	PRODUCT_STATUS_DOWN_DESC("gmt_down desc"),			//下架时间由近到远
	PRODUCT_STATUS_DOWN_ASC("gmt_down asc"),			//下架时间由远到近
	TOTAL_INVENTORY_DESC("total_inventory desc"),		//库存从高到低
	TOTAL_INVENTORY_ASC("total_inventory asc"),			//库存从低到高
	TOTAL_SALES_VOLUME_DESC("total_sales_volume desc"),	//销量从高到低
	TOTAL_SALES_VOLUME_ASC("total_sales_volume asc");	//销量低到高

	private String val;
	ProductListSortEnum(String val){
		this.val = val;
	}
	
	public static ProductListSortEnum getEnum(String val){
		ProductListSortEnum[] values = ProductListSortEnum.values();
		for (ProductListSortEnum object : values) {
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
