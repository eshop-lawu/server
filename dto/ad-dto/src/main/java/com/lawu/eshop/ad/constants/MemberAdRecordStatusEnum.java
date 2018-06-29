package com.lawu.eshop.ad.constants;

public enum MemberAdRecordStatusEnum {
	
	NONE((byte) 0x00),      //没有算提成
	YES((byte) 0x01);     //已算提成
	
    private Byte val;
    

    MemberAdRecordStatusEnum(Byte val) {
        this.val = val;
    }

    public static MemberAdRecordStatusEnum getEnum(Byte val) {
    	MemberAdRecordStatusEnum[] values = MemberAdRecordStatusEnum.values();
        for (MemberAdRecordStatusEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

	public Byte getVal() {
		return val;
	}

}
