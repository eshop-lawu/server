package com.lawu.eshop.cache.constants;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月2日
 */
public enum MerchantPowerTaskTypeEnum {

	/**
	 * 登录
	 */
    LOGIN((byte) 0x01, "登录"),
    
    /**
  	 * 邀请E友
  	 */
    INVITE_FRIEND((byte) 0x02, "邀请E友"),
  
    
    /**
     * 绑定支付宝
     */
    ALIPAY_BIND((byte) 0x06, "绑定支付宝"),
    
    /**
     * 绑定微信
     */
    WX_BIND((byte) 0x09, "绑定微信"),
    
    /**
     * 充值积分
     */
    RECHARGE_POINT((byte) 0x0A, "充值积分"),
    
    
    /**
  	 * 邀请粉丝
  	 */
    INVITE_FENS((byte) 0x0B, "邀请粉丝"),
      
    /**
      * 发广告
      */
     AD((byte) 0x0C, "发广告"),
      
	/**
	 * 新品上架
	 */
	NEW_SHOPPING((byte) 0x0D, "新品上架"),
    
    /**
     * 专场活动
     */
    ACTIVITY((byte) 0x0E, "专场活动");
	
	
    private Byte val;

    private String name;

    MerchantPowerTaskTypeEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static MerchantPowerTaskTypeEnum getEnum(Byte val) {
        MerchantPowerTaskTypeEnum[] values = MerchantPowerTaskTypeEnum.values();
        for (MerchantPowerTaskTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

    public static String getName(Byte val) {
        MerchantPowerTaskTypeEnum[] values = MerchantPowerTaskTypeEnum.values();
        for (MerchantPowerTaskTypeEnum object : values) {
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
