package com.lawu.eshop.framework.web.impl.constants;

/**
 * 
 * @author jiangxinjun
 * @date 2017年8月25日
 */
public enum  MerchantStoreTypeEnum {
	
	/**
	 * 没有商铺
	 */
    NOT_MERCHANT((byte)0x00),
	
	/**
	 * 普通商铺
	 */
    NORMAL_MERCHANT((byte)0x01),
    
    /**
     * 实体商铺
     */
    ENTITY_MERCHANT((byte)0x02);
	
    private Byte val;
    
    MerchantStoreTypeEnum(Byte val) {
        this.val = val;
    }
    
    public Byte getVal() {
		return val;
	}

	public static MerchantStoreTypeEnum getEnum(Byte val){
        MerchantStoreTypeEnum[] values = MerchantStoreTypeEnum.values();
        for (MerchantStoreTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
