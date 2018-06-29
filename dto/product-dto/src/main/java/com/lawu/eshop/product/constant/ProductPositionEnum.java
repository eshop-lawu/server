package com.lawu.eshop.product.constant;

public enum ProductPositionEnum {

    CANCEL((byte) 0x00, "取消"),
    SHOPWINDOW((byte) 0x01, "橱窗");

    private Byte val;
    private String name;

    ProductPositionEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static ProductPositionEnum getEnum(Byte val) {
        ProductPositionEnum[] values = ProductPositionEnum.values();
        for (ProductPositionEnum object : values) {
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
