package com.lawu.eshop.mall.constants;

/**
 * 优惠套餐状态
 * 
 * @author Sunny
 * @date 2017年6月26日
 */
public enum DiscountPackageStatusEnum {
	
	/**
	 * 0-删除
	 */
    INVALID((byte) 0x00),
	
	/**
	 * 1-上架
	 */
	UP((byte) 0x01),
	
	/**
	 * 2-下架
	 */
	DOWN((byte) 0x02);
	
    private Byte value;

    DiscountPackageStatusEnum(Byte value) {
        this.value = value;
    }
    
    public Byte getValue() {
		return value;
	}

	public static DiscountPackageStatusEnum getEnum(Byte value) {
        DiscountPackageStatusEnum[] values = DiscountPackageStatusEnum.values();
        for (DiscountPackageStatusEnum object : values) {
            if (object.getValue().equals(value)) {
                return object;
            }
        }
        return null;
    }
}
