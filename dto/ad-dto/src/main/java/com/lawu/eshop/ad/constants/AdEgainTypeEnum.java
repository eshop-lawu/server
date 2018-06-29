package com.lawu.eshop.ad.constants;

/**
 * E赚的广告类型
 * @author jiangxinjun
 * @date 2017年7月18日
 */
public enum AdEgainTypeEnum {
	
	/**
	 * 平面
	 */
	AD_TYPE_FLAT((byte) 0x01),
	
	/**
	 * 视频
	 */
	AD_TYPE_VIDEO((byte) 0x02);
	
    private Byte val;
    
    AdEgainTypeEnum(Byte val) {
        this.val = val;
    }
    
    public Byte getVal() {
		return val;
	}

	public static AdEgainTypeEnum getEnum(Byte val) {
    	AdEgainTypeEnum[] values = AdEgainTypeEnum.values();
        for (AdEgainTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
