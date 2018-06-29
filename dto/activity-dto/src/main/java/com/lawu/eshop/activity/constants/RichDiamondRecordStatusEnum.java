package com.lawu.eshop.activity.constants;

/**
 * @author lihj
 * @date 2018/5/2
 */
public enum RichDiamondRecordStatusEnum {
	HASASSIGNED((byte) 0x00, "已分配"),
    RECEIVE((byte) 0x01, "已领取"),
    NORECEIVE((byte) 0x02, "未领取");
    private Byte val;

    private String name;

    RichDiamondRecordStatusEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public Byte getVal() {
        return val;
    }

    public String getName() {
        return name;
    }

    public static RichDiamondRecordStatusEnum getEnum(Byte val) {
        RichDiamondRecordStatusEnum[] values = RichDiamondRecordStatusEnum.values();
        for (RichDiamondRecordStatusEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }
}
