package com.lawu.eshop.product.constant;

/**
 * 商家自定义信息
 */
public enum CustomTypeEnum {

	CATEGORY((byte)0x01),	//类目
	BRAND((byte)0x02),		//品牌
	SPEC_OPTION((byte)0x03);//规格选项

	private Byte val;
	CustomTypeEnum(Byte val){
		this.val = val;
	}
	
	public static CustomTypeEnum getEnum(Byte val){
		CustomTypeEnum[] values = CustomTypeEnum.values();
		for (CustomTypeEnum object : values) {
			if (object.val.equals(val)) {
				return object;
			}
		}
		return null;
	}

	public Byte getVal() {
		return val;
	}
}
