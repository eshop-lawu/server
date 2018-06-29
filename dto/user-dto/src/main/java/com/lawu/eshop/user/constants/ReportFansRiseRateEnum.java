package com.lawu.eshop.user.constants;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年5月2日 下午2:30:22
 *
 */
public enum ReportFansRiseRateEnum {

    DAY((byte) 0x01),//日增长
    MONTH((byte) 0x02);//月增长
	
    private Byte value;
    public Byte getValue() {
		return value;
	}

	ReportFansRiseRateEnum(Byte value) {
        this.value = value;
    }

    public static ReportFansRiseRateEnum getEnum(Byte value) {
        ReportFansRiseRateEnum[] values = ReportFansRiseRateEnum.values();
        for (ReportFansRiseRateEnum object : values) {
            if (object.getValue().equals(value)) {
                return object;
            }
        }
        return null;
    }
}
