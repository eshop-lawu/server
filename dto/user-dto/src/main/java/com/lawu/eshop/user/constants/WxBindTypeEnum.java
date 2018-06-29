package com.lawu.eshop.user.constants;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年1月16日
 */
public enum WxBindTypeEnum {

    H5_BIND((byte) 0x01, "H5_BIND"),     
    WEI_XIN_LOGIN((byte) 0x02,"WEI_XIN_LOGIN");  
	
    public Byte val;
    private String name;
    
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

	WxBindTypeEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static WxBindTypeEnum getEnum(Byte val) {
        WxBindTypeEnum[] values = WxBindTypeEnum.values();
        for (WxBindTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
