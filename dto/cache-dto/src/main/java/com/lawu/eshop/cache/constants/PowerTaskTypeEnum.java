package com.lawu.eshop.cache.constants;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月2日
 */
public enum PowerTaskTypeEnum {

	/**
	 * 登录
	 */
    LOGIN((byte) 0x01, "登录"),
    
    /**
  	 * 邀请E友
  	 */
    INVITE((byte) 0x02, "邀请E友"),
    
    /**
	 * 娱乐
	 */
    GAME((byte) 0x03, "每日娱乐"),
    
    /**
   	 * 看广告
   	 */
    AD((byte) 0x04, "看广告"),
    
    /**
   	 * 购物
   	 */
    SHOPPING((byte) 0x05, "每日购物"),
    
    /**
     * 绑定支付宝
     */
    ALIPAY_BIND((byte) 0x06, "绑定支付宝"),
    
    /**
     * 身份验证
     */
    USER_AUTH((byte) 0x07, "身份验证"),
    /**
     * 幸运值领取消耗
     */
    GET_LUCKY_DIAMOND((byte)0x08,"幸运值领取消耗");
	
    private Byte val;

    private String name;

    PowerTaskTypeEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static PowerTaskTypeEnum getEnum(Byte val) {
        PowerTaskTypeEnum[] values = PowerTaskTypeEnum.values();
        for (PowerTaskTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

    public static String getName(Byte val) {
        PowerTaskTypeEnum[] values = PowerTaskTypeEnum.values();
        for (PowerTaskTypeEnum object : values) {
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
