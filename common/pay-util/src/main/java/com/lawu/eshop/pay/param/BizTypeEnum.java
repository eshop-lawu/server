package com.lawu.eshop.pay.param;

public enum BizTypeEnum {

    REFUND("退款"),

    MMPAY("企业付款");

    private String name;

    BizTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
