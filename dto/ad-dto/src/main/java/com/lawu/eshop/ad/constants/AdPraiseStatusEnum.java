package com.lawu.eshop.ad.constants;

/**
 * E赞状态
 * 
 * @author jiangxinjun
 * @date 2017年7月19日
 */
public enum AdPraiseStatusEnum {
	
	/**
	 * 开抢中
	 */
	AD_STATUS_SHOOT((byte) 0x01),
	
	/**
	 * 即将开始
	 */
	AD_STATUS_TOBEGIN((byte) 0x02),
	
	/**
	 * 已结束
	 */
	AD_STATUS_END((byte) 0x03);
	
    private Byte val;

    AdPraiseStatusEnum(Byte val) {
        this.val = val;
    }
    
    public Byte getVal() {
		return val;
	}

	public static AdPraiseStatusEnum getEnum(Byte val) {
    	AdPraiseStatusEnum[] values = AdPraiseStatusEnum.values();
        for (AdPraiseStatusEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
