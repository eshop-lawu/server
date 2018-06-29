package com.lawu.eshop.common.constants;

/**
 * 商家店铺类型 
 *  
 * @author jiangxinjun
 * @date 2017年7月18日
 */
public enum ManageTypeEnum {
   
	/**
	 * 普通
	 */
    COMMON((byte) 0x01),
    
    /**
     * 实体
     */
	ENTITY((byte) 0x02);
	
    private Byte val;

    ManageTypeEnum(Byte val) {
        this.val = val;
    }
    
    public Byte getVal() {
		return val;
	}

	public static ManageTypeEnum getEnum(Byte val) {
    	ManageTypeEnum[] values = ManageTypeEnum.values();
        for (ManageTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
