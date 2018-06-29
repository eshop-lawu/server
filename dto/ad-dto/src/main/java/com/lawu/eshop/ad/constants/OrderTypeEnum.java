package com.lawu.eshop.ad.constants;

/**
 * 广告排行版排序类型
 * 
 * @author jiangxinjun
 * @date 2017年7月19日
 */
public enum OrderTypeEnum {
	
	/**
	 * 总价倒序
	 */
	AD_TORLEPOINT_DESC((byte) 0x01),
	
	/**
	 * 单个倒序
	 */
	AD_POINT_DESC((byte) 0x02);
	
    private Byte val;

    OrderTypeEnum(Byte val) {
        this.val = val;
    }
    
    public Byte getVal() {
		return val;
	}

	public static OrderTypeEnum getEnum(Byte val) {
    	OrderTypeEnum[] values = OrderTypeEnum.values();
        for (OrderTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
