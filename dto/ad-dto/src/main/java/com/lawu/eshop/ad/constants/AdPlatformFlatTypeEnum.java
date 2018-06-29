package com.lawu.eshop.ad.constants;

/**
 * 广告位置
 * @author zhangrc
 * @date 2017年8月7日
 */
public enum AdPlatformFlatTypeEnum {
	
	/**
	 * 一个平面
	 */
	AD_PLAT_FORM_ONE((byte) 0x01),
	
	/**
	 * 五个平面
	 */
	AD_PLAT_FORM_FIVE((byte) 0x02),
	
	/**
	 * 两个个平面
	 */
	AD_PLAT_FORM_TWO((byte) 0x03);
	
    private Byte val;
    
    AdPlatformFlatTypeEnum(Byte val) {
        this.val = val;
    }
    
    public Byte getVal() {
		return val;
	}

	public static AdPlatformFlatTypeEnum getEnum(Byte val) {
    	AdPlatformFlatTypeEnum[] values = AdPlatformFlatTypeEnum.values();
        for (AdPlatformFlatTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
