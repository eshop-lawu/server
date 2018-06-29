package com.lawu.eshop.activity.constants;

/**
 * 分配状态
 * 
 * @author jiangxinjun
 * @createDate 2018年5月7日
 * @updateDate 2018年5月7日
 */
public enum DistributionStatusEnum {

    /**
     * 分配中
     */
    ALLOCATION((byte) 0x01),
    
    /**
     * 已分配
     */
    ALLOCATED((byte) 0x02),
    
    /**
     * 已发放
     */
    ISSUED((byte) 0x03);

    private Byte val;

    DistributionStatusEnum(Byte val) {
        this.val = val;
    }

    public Byte getVal() {
        return val;
    }

    public static DistributionStatusEnum getEnum(Byte val) {
        DistributionStatusEnum[] values = DistributionStatusEnum.values();
        for (DistributionStatusEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }
}
