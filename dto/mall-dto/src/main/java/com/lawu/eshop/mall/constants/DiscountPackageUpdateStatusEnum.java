package com.lawu.eshop.mall.constants;

/**
 * 优惠套餐状态
 * 
 * @author Sunny
 * @date 2017年7月31日
 */
public enum DiscountPackageUpdateStatusEnum {
	
	/**
	 * 1-上架
	 */
	UP((byte) 0x01),
	
	/**
	 * 2-下架
	 */
	DOWN((byte) 0x02);
	
    private Byte value;

    DiscountPackageUpdateStatusEnum(Byte value) {
        this.value = value;
    }
    
    public Byte getValue() {
		return value;
	}

	public static DiscountPackageUpdateStatusEnum getEnum(Byte value) {
        DiscountPackageUpdateStatusEnum[] values = DiscountPackageUpdateStatusEnum.values();
        for (DiscountPackageUpdateStatusEnum object : values) {
            if (object.getValue().equals(value)) {
                return object;
            }
        }
        return null;
    }
}
