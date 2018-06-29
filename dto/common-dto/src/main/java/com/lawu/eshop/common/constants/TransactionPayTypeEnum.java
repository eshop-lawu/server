package com.lawu.eshop.common.constants;

/**
 * 
 * <p>
 * Description:
 * </p>
 * 
 * @author Yangqh
 * @date 2017年4月5日 下午5:29:15
 *
 */
public enum TransactionPayTypeEnum {
    /**
     * 余额
     */
    BALANCE((byte) 0x01, "余额"),

    /**
     * 支付宝
     */
    ALIPAY((byte) 0x02, "支付宝"),

    /**
     * 微信
     */
    WX((byte) 0x03, "微信"),

    /**
     * 平台
     */
    PLAT((byte) 0x04, "平台");

    private Byte val;

    private String name;

    TransactionPayTypeEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static TransactionPayTypeEnum getEnum(Byte val) {
        TransactionPayTypeEnum[] values = TransactionPayTypeEnum.values();
        for (TransactionPayTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

    public Byte getVal() {
        return val;
    }

    public String getName() {
        return name;
    }

}
