package com.lawu.eshop.ad.constants;

public enum TypeEnum {
	
	
	TYPE_LINK((byte) 0x01,"链接"),      //链接
	TYPE_PRODUCT((byte) 0x02, "商品"),     //商品
	TYPE_STORE((byte) 0x03, "门店"),     //门店
	TYPE_AD((byte) 0x04, "广告"),
	TYPE_GAME((byte) 0x05, "游戏"),
	TYPE_ALL((byte) 0x06,"所有");
    public Byte val;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    TypeEnum(Byte val,String name) {
        this.val = val;
        this.name = name;
    }

    public static TypeEnum getEnum(Byte val) {
    	TypeEnum[] values = TypeEnum.values();
        for (TypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
