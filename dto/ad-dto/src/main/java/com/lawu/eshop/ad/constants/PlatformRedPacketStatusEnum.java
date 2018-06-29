package com.lawu.eshop.ad.constants;

public enum PlatformRedPacketStatusEnum {
	
	DISENABLE((byte) 0x00),     //禁用
	ENABLE((byte) 0x01);      //启用
	
	
    public Byte val;

    PlatformRedPacketStatusEnum(Byte val) {
        this.val = val;
    }

    public static PlatformRedPacketStatusEnum getEnum(Byte val) {
    	PlatformRedPacketStatusEnum[] values = PlatformRedPacketStatusEnum.values();
        for (PlatformRedPacketStatusEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
