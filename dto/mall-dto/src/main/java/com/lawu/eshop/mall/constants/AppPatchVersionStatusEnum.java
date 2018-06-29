package com.lawu.eshop.mall.constants;

/**
 * @author meishuquan
 * @date 2017/12/12.
 */
public enum AppPatchVersionStatusEnum {

    NOT_ENABLED((byte) 0x01, "未启用"),//未启用
    ENABLE((byte) 0x02, "启用");//启用

    private Byte val;
    private String name;

    public String getName() {
        return name;
    }

    public Byte getVal() {
        return val;
    }

    AppPatchVersionStatusEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static AppPatchVersionStatusEnum getEnum(Byte val) {
        AppPatchVersionStatusEnum[] values = AppPatchVersionStatusEnum.values();
        for (AppPatchVersionStatusEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }
}
