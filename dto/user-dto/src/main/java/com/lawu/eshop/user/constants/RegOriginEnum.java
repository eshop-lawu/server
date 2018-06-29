package com.lawu.eshop.user.constants;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年1月16日
 */
public enum RegOriginEnum {

    WEIXIN((byte) 0x01, "WEIXIN"),     
    QQ((byte) 0x02,"QQ");  
	
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

	RegOriginEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static RegOriginEnum getEnum(Byte val) {
        RegOriginEnum[] values = RegOriginEnum.values();
        for (RegOriginEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
