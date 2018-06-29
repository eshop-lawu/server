package com.lawu.eshop.ad.constants;

public enum AuditEnum {
	
	AD_AUDIT_PASS((byte) 0x01),      //审核通过
	AD_AUDIT_UN_PASS((byte) 0x02);     //审核不通过
	
    public Byte val;

    AuditEnum(Byte val) {
        this.val = val;
    }

    public static AuditEnum getEnum(Byte val) {
    	AuditEnum[] values = AuditEnum.values();
        for (AuditEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
