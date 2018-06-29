package com.lawu.eshop.mall.constants;

/**
 * @author zhangrc
 * @date 2017/9/25.
 */
public enum AppStatusEnum {
	DELETE((byte)0x00,"删除"),
    ENABLE((byte) 0x01,"启用"),//启用
    DISENABLE((byte) 0x02,"禁用");//禁用
    public Byte val;
    
    private String name;

    public String getName() {
        return name;
    }

    public Byte getVal() {
		return val;
	}

    AppStatusEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static AppStatusEnum getEnum(Byte val) {
    	AppStatusEnum[] values = AppStatusEnum.values();
        for (AppStatusEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }
}
