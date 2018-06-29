package com.lawu.eshop.cache.constants;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月2日
 */
public enum PowerTaskStatusEnum {


	/**
	 * 启用
	 */
	ENABLED((byte) 0x01, "启用"),
    
    /**
  	 * 禁用
  	 */
	DISABLED((byte) 0x02, "禁用");
	
    private Byte val;

    private String name;

    PowerTaskStatusEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static PowerTaskStatusEnum getEnum(Byte val) {
        PowerTaskStatusEnum[] values = PowerTaskStatusEnum.values();
        for (PowerTaskStatusEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

    public static String getName(Byte val) {
        PowerTaskStatusEnum[] values = PowerTaskStatusEnum.values();
        for (PowerTaskStatusEnum object : values) {
            if (object.val.equals(val)) {
                return object.name;
            }
        }
        return null;
    }

    public Byte getVal() {
        return val;
    }

    public void setVal(Byte val) {
        this.val = val;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
