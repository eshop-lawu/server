package com.lawu.eshop.statistics.constants;

public enum ReportAdEarningsStatusEnum {
	
	ANOMALY((byte) 0x00),      //异常
	NORMAL((byte) 0x01);     //正常
    public Byte val;

    ReportAdEarningsStatusEnum(Byte val) {
        this.val = val;
    }

    public static ReportAdEarningsStatusEnum getEnum(Byte val) {
    	ReportAdEarningsStatusEnum[] values = ReportAdEarningsStatusEnum.values();
        for (ReportAdEarningsStatusEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
