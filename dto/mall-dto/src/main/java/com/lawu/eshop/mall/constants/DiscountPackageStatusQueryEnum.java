package com.lawu.eshop.mall.constants;

/**
 * 优惠套餐状态
 * 
 * @author Sunny
 * @date 2017年6月26日
 */
public enum DiscountPackageStatusQueryEnum {
	
	/**
	 * 1-上架
	 */
	UP((byte) 0x01),
	
	/**
	 * 2-下架
	 */
	DOWN((byte) 0x02);
	
    private Byte value;

    DiscountPackageStatusQueryEnum(Byte value) {
        this.value = value;
    }
    
    public Byte getValue() {
		return value;
	}

	public static DiscountPackageStatusQueryEnum getEnum(Byte value) {
        DiscountPackageStatusQueryEnum[] values = DiscountPackageStatusQueryEnum.values();
        for (DiscountPackageStatusQueryEnum object : values) {
            if (object.getValue().equals(value)) {
                return object;
            }
        }
        return null;
    }
}
