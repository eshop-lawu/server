package com.lawu.eshop.product.constant;

/**
 * 
 * <p>
 * Description: 商品图片类型
 * </p>
 * @author Yangqh
 * @date 2017年3月27日 下午7:27:24
 *
 */
public enum ProductImgTypeEnum {
	
	PRODUCT_IMG_HEAD((byte)0x01),		//1-头部滚动图片
	PRODUCT_IMG_DETAIL((byte)0x02);		//2-详情图片
	
	private Byte val;
	ProductImgTypeEnum(Byte val){
		this.val = val;
	}
	
	public static ProductImgTypeEnum getEnum(Byte val){
		ProductImgTypeEnum[] values = ProductImgTypeEnum.values();
		for (ProductImgTypeEnum object : values) {
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
