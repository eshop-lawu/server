package com.lawu.eshop.property.constants;

/**
 * @author meishuquan
 * @date 2017/3/29.
 */
public enum SexEnum {

    SEX_MALE((byte) 0x00),      //男
    SEX_SECRET((byte) 0x01),     //全部
    SEX_FEMALE((byte) 0x02);    //女
    private Byte val;

    SexEnum(Byte val) {
        this.val = val;
    }

    public static SexEnum getEnum(Byte val) {
        SexEnum[] values = SexEnum.values();
        for (SexEnum object : values) {
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
