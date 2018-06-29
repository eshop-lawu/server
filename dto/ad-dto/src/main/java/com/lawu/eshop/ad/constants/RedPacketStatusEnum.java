package com.lawu.eshop.ad.constants;

public enum RedPacketStatusEnum {
	
	RED_PACKET_SUCCESS((byte) 0x01),      //领取成功
	RED_PACKET_FAIL((byte) 0x02);     //红包已领完
    public Byte val;

    RedPacketStatusEnum(Byte val) {
        this.val = val;
    }

    public static RedPacketStatusEnum getEnum(Byte val) {
    	RedPacketStatusEnum[] values = RedPacketStatusEnum.values();
        for (RedPacketStatusEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
