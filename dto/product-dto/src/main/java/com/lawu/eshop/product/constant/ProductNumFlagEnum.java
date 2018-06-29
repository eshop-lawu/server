package com.lawu.eshop.product.constant;

/**
 * 产品表和产品型号表对于数量上操作
 * 
 * @author Sunny
 * @date 2017年6月6日
 */
public enum ProductNumFlagEnum {
	
	ADD("A"),	
	MINUS("M");
	
	private String value;
	
	ProductNumFlagEnum(String value){
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

	public static ProductNumFlagEnum getEnum(String val){
		ProductNumFlagEnum[] values = ProductNumFlagEnum.values();
		for (ProductNumFlagEnum object : values) {
			if (object.getValue().equals(val)) {
				return object;
			}
		}
		return null;
	}
	
}
