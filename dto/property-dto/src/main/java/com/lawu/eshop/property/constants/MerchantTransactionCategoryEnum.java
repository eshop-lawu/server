package com.lawu.eshop.property.constants;

import com.lawu.eshop.common.constants.ManageTypeEnum;

/**
 * 商家余额交易明细大类
 */
public enum MerchantTransactionCategoryEnum {

	ALL((byte) 0x01, "全部分类", new ManageTypeEnum[]{ManageTypeEnum.COMMON, ManageTypeEnum.ENTITY}),
	RECHARGE((byte) 0x02, "充值", new ManageTypeEnum[]{ManageTypeEnum.COMMON, ManageTypeEnum.ENTITY}),
	RECOMMEND_INCOME((byte) 0x03, "推荐", new ManageTypeEnum[]{ManageTypeEnum.COMMON, ManageTypeEnum.ENTITY}),
	WITHDRAW((byte) 0x04, "提现", new ManageTypeEnum[]{ManageTypeEnum.COMMON, ManageTypeEnum.ENTITY}),
//	PAY_INCOME((byte) 0x05, "买单", new ManageTypeEnum[]{ManageTypeEnum.ENTITY}),
	PRODUCT_INCOME((byte) 0x06, "商品收入", new ManageTypeEnum[]{ManageTypeEnum.COMMON}),
	REFUND_MONEY((byte) 0x07, "退款", new ManageTypeEnum[]{ManageTypeEnum.COMMON, ManageTypeEnum.ENTITY}),
	DUE_IN((byte) 0x08, "待收货款", new ManageTypeEnum[]{ManageTypeEnum.COMMON}),
    RED_PACKET((byte) 0x09, "红包", new ManageTypeEnum[]{ManageTypeEnum.COMMON,ManageTypeEnum.ENTITY});

    private Byte value;

    private String name;

    private ManageTypeEnum[] type;

    MerchantTransactionCategoryEnum(Byte value, String name, ManageTypeEnum[] type) {
        this.value = value;
        this.name = name;
        this.type = type;
    }

    public Byte getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public ManageTypeEnum[] getType() {
        return type;
    }

    public static MerchantTransactionCategoryEnum getEnum(Byte val) {
        MerchantTransactionCategoryEnum[] values = MerchantTransactionCategoryEnum.values();
        for (MerchantTransactionCategoryEnum object : values) {
            if (object.getValue().equals(val)) {
                return object;
            }
        }
        return null;
    }
}