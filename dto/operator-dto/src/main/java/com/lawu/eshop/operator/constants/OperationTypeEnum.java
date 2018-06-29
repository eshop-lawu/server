package com.lawu.eshop.operator.constants;

/**
 * @author meishuquan
 * @date 2017/5/3.
 */
public enum OperationTypeEnum {

    ALL((byte) 0x00),       //查询条件，所有
    ADD((byte) 0x01),       //新增
    UPDATE((byte) 0x02),    //修改
    DELETE((byte) 0x03);    //删除

    public Byte val;

    OperationTypeEnum(Byte val) {
        this.val = val;
    }

    public static OperationTypeEnum getEnum(Byte val) {
        OperationTypeEnum[] values = OperationTypeEnum.values();
        for (OperationTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }
}
