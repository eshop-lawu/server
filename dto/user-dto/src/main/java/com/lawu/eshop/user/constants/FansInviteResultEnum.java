package com.lawu.eshop.user.constants;

public enum FansInviteResultEnum {

    REFUSE((byte) 0x0,"拒绝"),
    AGREE((byte) 0x01,"同意"),
    NOT_DEAL((byte) 0x02,"未处理");
	
    private Byte value;
    private String name;
    
    public Byte getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	FansInviteResultEnum(Byte value,String name) {
        this.value = value;
		this.name = name;
    }

    public static FansInviteResultEnum getEnum(Byte value) {
    	FansInviteResultEnum[] values = FansInviteResultEnum.values();
        for (FansInviteResultEnum object : values) {
            if (object.getValue().equals(value)) {
                return object;
            }
        }
        return null;
    }
}
