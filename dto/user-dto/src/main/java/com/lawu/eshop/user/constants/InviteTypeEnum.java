package com.lawu.eshop.user.constants;

public enum InviteTypeEnum {

	ALL((byte) 0x01),      //全选
	CANCEL_SOME((byte) 0x02), //取消一部分
	CHOOSE_SOME((byte)0x03);   //只选一部分
	public Byte val;
	
	InviteTypeEnum(Byte val) {
	    this.val = val;
	}
	
	public static InviteTypeEnum getEnum(Byte val) {
		InviteTypeEnum[] values = InviteTypeEnum.values();
	    for (InviteTypeEnum object : values) {
	        if (object.val.equals(val)) {
	            return object;
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
	
}
