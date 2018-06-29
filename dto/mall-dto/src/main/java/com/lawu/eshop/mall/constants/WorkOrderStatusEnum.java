package com.lawu.eshop.mall.constants;

public enum WorkOrderStatusEnum {
	
	NOT_YET_DEAL((byte) 0x01),      //未处理
    REPLIED((byte) 0x02),     		//已处理
	NOT_DEAL((byte) 0x03);     		//不予处理
    public Byte val;

    WorkOrderStatusEnum(Byte val) {
        this.val = val;
    }

    public static WorkOrderStatusEnum getEnum(Byte val) {
    	WorkOrderStatusEnum[] values = WorkOrderStatusEnum.values();
        for (WorkOrderStatusEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }
}
