package com.lawu.eshop.activity.constants;

/**
 * 红包活动类型
 * @author jiangxinjun
 * @createDate 2018年1月23日
 * @updateDate 2018年1月23日
 */
public enum RedPacketActivityTypeEnum {

    /**
     * 新年活动
     */
    NEW_YEAR((byte) 0x01),

    /**
     * 元宵活动
     */
    LANTERN((byte) 0x02);

    private Byte val;

    RedPacketActivityTypeEnum(Byte val) {
        this.val = val;
    }

    public Byte getVal() {
        return val;
    }

    public static RedPacketActivityTypeEnum getEnum(Byte val) {
        RedPacketActivityTypeEnum[] values = RedPacketActivityTypeEnum.values();
        for (RedPacketActivityTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
