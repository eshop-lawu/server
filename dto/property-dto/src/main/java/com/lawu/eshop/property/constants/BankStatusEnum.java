package com.lawu.eshop.property.constants;


public enum BankStatusEnum {

    NONE((byte) 0x00),      //无效
    YES((byte) 0x01);     //有效
    private Byte val;

    BankStatusEnum(Byte val) {
        this.val = val;
    }

    public static BankStatusEnum getEnum(Byte val) {
        BankStatusEnum[] values = BankStatusEnum.values();
        for (BankStatusEnum object : values) {
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
