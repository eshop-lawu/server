package com.lawu.eshop.mall.constants;

public enum WorkOrderTypeEnum {
	
	MEMBER((byte) 0x01),      //会员
    MERCHANT((byte) 0x02);     //商家
    public Byte val;

    WorkOrderTypeEnum(Byte val) {
        this.val = val;
    }

    public static WorkOrderTypeEnum getEnum(Byte val) {
    	WorkOrderTypeEnum[] values = WorkOrderTypeEnum.values();
        for (WorkOrderTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }
}
