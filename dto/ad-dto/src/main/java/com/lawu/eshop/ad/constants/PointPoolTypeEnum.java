package com.lawu.eshop.ad.constants;

public enum PointPoolTypeEnum {
	
	AD_TYPE_PRAISE((byte) 0x01, "抢赞广告"),    //E赞
	AD_TYPE_PACKET((byte) 0x02, "红包");    //红包
	
    public Byte val;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    PointPoolTypeEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static PointPoolTypeEnum getEnum(Byte val) {
    	PointPoolTypeEnum[] values = PointPoolTypeEnum.values();
        for (PointPoolTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
