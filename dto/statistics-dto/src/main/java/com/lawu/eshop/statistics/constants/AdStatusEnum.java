package com.lawu.eshop.statistics.constants;

public enum AdStatusEnum {
	
	AD_STATUS_ADD((byte) 0x01),      //上架
	AD_STATUS_PUTING((byte) 0x02),     //投放中
	AD_STATUS_PUTED((byte) 0x03);     //投放结束
    public Byte val;
    
    AdStatusEnum(Byte val) {
        this.val = val;
    }

    public static AdStatusEnum getEnum(Byte val) {
    	AdStatusEnum[] values = AdStatusEnum.values();
        for (AdStatusEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
