package com.lawu.eshop.common.constants;

public enum MemberGradeEnum {

    SILVER((byte) 0x01, "白银会员"),
    GOLD((byte) 0x02, "黄金会员"),
    PLATINUM((byte) 0x03, "铂金会员"),
    MASONRY((byte) 0x04, "钻石会员"),
    CROWN((byte) 0x05, "皇冠会员");
    private Byte val;
    private String name;

    MemberGradeEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static MemberGradeEnum getEnum(Byte val) {
        MemberGradeEnum[] values = MemberGradeEnum.values();
        for (MemberGradeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

    public Byte getVal() {
        return val;
    }

    public String getName() {
        return name;
    }
}
