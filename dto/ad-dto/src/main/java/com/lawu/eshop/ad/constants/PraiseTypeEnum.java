package com.lawu.eshop.ad.constants;

public enum PraiseTypeEnum {
	
	/**
	 * 拼图
	 */
	PRAISE_TYPE_PUZZLE((byte) 0x01, "拼图"),
	
	/**
	 * 点赞
	 */
	PRAISE_TYPE_CLICK((byte) 0x02, "点赞");
	
    private Byte val;
    
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
		this.name = name;
	}
    public Byte getVal() {
		return val;
	}

	PraiseTypeEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static PraiseTypeEnum getEnum(Byte val) {
    	PraiseTypeEnum[] values = PraiseTypeEnum.values();
        for (PraiseTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
