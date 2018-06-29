package com.lawu.eshop.ad.constants;

public enum PointPoolStatusEnum {
	
	AD_POINT_NO_GET((byte) 0x00, "未领取"),   
	AD_POINT_GET((byte) 0x01, "已领取"),    
	AD_POINT_OUT((byte) 0x02, "过期");    
	
    public Byte val;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    PointPoolStatusEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static PointPoolStatusEnum getEnum(Byte val) {
    	PointPoolStatusEnum[] values = PointPoolStatusEnum.values();
        for (PointPoolStatusEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
