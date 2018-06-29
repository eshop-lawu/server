package com.lawu.eshop.activity.constants;

/**
 * 助力红包活动参与记录异常状态枚举
 * 
 * @author jiangxinjun
 * @createDate 2018年3月1日
 * @updateDate 2018年3月1日
 */
public enum AbnormalStatusEnum {

    /**
     * 正常
     */
    NORMAL((byte) 0x00, "正常"),
    
    /**
     * 疑似
     */
    SUSPECTED((byte) 0x01, "疑似"),
    
    /**
     * 异常
     */
    ABNORMAL((byte) 0x02, "异常"),
    
    /**
     * 白名单
     */
    WHITE_LIST((byte) 0x03, "白名单");

    private Byte val;

    private String name;

    AbnormalStatusEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public Byte getVal() {
        return val;
    }

    public String getName() {
        return name;
    }

    public static AbnormalStatusEnum getEnum(Byte val) {
        AbnormalStatusEnum[] values = AbnormalStatusEnum.values();
        for (AbnormalStatusEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
