package com.lawu.eshop.activity.constants;

/**
 * 红包类型
 * 
 * @author jiangxinjun
 * @createDate 2018年1月16日
 * @updateDate 2018年1月16日
 */
public enum RedPacketTypeEnum {

    /**
     * 微信红包
     */
    WX((byte) 0x01, "微信红包"),

    /**
     * 余额红包
     */
    BALANCE((byte) 0x02, "余额红包");

    private Byte val;

    private String name;

    RedPacketTypeEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public Byte getVal() {
        return val;
    }

    public String getName() {
        return name;
    }

    public static RedPacketTypeEnum getEnum(Byte val) {
        RedPacketTypeEnum[] values = RedPacketTypeEnum.values();
        for (RedPacketTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
