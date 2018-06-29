package com.lawu.eshop.product.constant;

/**
 * 
 * 
 * @author zhangrc
 * @date 2018/4/16.
 */
public enum CustomSpecStatusEnum {

	DELETE((byte) 0x00, "删除"),
	ON_DEAL_WITH((byte) 0x01, "待处理"),
    DEAL_WITH((byte) 0x02, "已处理"),
    UN_DEAL_WITH((byte) 0x03, "不处理");


    private Byte val;
    private String name;

    CustomSpecStatusEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static CustomSpecStatusEnum getEnum(Byte val) {
        CustomSpecStatusEnum[] values = CustomSpecStatusEnum.values();
        for (CustomSpecStatusEnum object : values) {
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
