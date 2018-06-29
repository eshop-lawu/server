package com.lawu.eshop.user.constants;

public enum InviterTypeEnum {
	
	    MEMBER((byte) 0x01),      //会员
	    MERCHANT((byte) 0x02);     //商家
	    public Byte val;

	    InviterTypeEnum(Byte val) {
	        this.val = val;
	    }

	    public static InviterTypeEnum getEnum(Byte val) {
	    	InviterTypeEnum[] values = InviterTypeEnum.values();
	        for (InviterTypeEnum object : values) {
	            if (object.val.equals(val)) {
	                return object;
	            }
	        }
	        return null;
	    }

}
