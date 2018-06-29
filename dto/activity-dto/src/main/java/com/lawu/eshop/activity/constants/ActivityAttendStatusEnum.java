package com.lawu.eshop.activity.constants;

public enum ActivityAttendStatusEnum {
	
    /**
     * 已报名
     */
	ATTEND((byte) 0x01,"已报名"),
	/**
	 * 已分配
	 */
	ALLOT((byte) 0x02,"已分配"),
	/**
	 * 已领取
	 */
	GET((byte) 0x03,"已领取"),
	/**
	 * 已发放
	 */
	SEND((byte) 0x04,"已发放"),
	/**
	 * 发放失败
	 */
	SEND_FAIL((byte) 0x05,"发放失败"),
	/**
	 * 发放成功
	 */
	SEND_SUCCESS((byte) 0x06,"发放成功"),
	/**
	 * 已退款
	 */
	REFUND((byte) 0x07,"已退款");
    
    private Byte val;

    private String name;

    ActivityAttendStatusEnum(Byte val, String name) {
		this.val = val;
		this.name = name;
	}
    
    public Byte getVal() {
        return val;
    }

    public String getName() {
        return name;
    }

    public static ActivityAttendStatusEnum getEnum(Byte val) {
    	ActivityAttendStatusEnum[] values = ActivityAttendStatusEnum.values();
        for (ActivityAttendStatusEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }
    
    public static String getName(Byte val) {
    	ActivityAttendStatusEnum[] values = ActivityAttendStatusEnum.values();
		for (ActivityAttendStatusEnum object : values) {
			if (object.val.equals(val)) {
				return object.name;
			}
		}
		return null;
	}

}
