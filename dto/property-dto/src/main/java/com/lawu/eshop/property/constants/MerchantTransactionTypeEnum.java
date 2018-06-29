package com.lawu.eshop.property.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * 商家交易类型枚举
 *
 * @author Sunny
 * @date 2017/3/29
 */
public enum MerchantTransactionTypeEnum {

    PAY((byte) 0x64, "买单", new MerchantTransactionCategoryEnum[]{}, "", "买单收入"),
    ORDER((byte) 0x65, "商品收入", new MerchantTransactionCategoryEnum[]{MerchantTransactionCategoryEnum.PRODUCT_INCOME}, "", "购物收入"),
    LOWER_INCOME((byte) 0x66, "推荐E友收益", new MerchantTransactionCategoryEnum[]{MerchantTransactionCategoryEnum.RECOMMEND_INCOME}, "推荐E友收益", "广告提成收益"),
    RECHARGE((byte) 0x67, "余额充值", new MerchantTransactionCategoryEnum[]{MerchantTransactionCategoryEnum.RECHARGE}, "余额充值", "余额充值"),
    INTEGRAL_RECHARGE((byte) 0x69, "积分充值", new MerchantTransactionCategoryEnum[]{MerchantTransactionCategoryEnum.RECHARGE}, "积分充值", "积分充值"),
    WITHDRAW((byte) 0x6B, "提现", new MerchantTransactionCategoryEnum[]{MerchantTransactionCategoryEnum.WITHDRAW}, "提现申请", "提现申请"),
    WITHDRAW_BACK((byte) 0x6C, "提现失败", new MerchantTransactionCategoryEnum[]{MerchantTransactionCategoryEnum.WITHDRAW, MerchantTransactionCategoryEnum.REFUND_MONEY}, "提现失败退款", "提现失败退回"),
    INVITE_FANS((byte) 0x6D, "邀请粉丝", null, "", "邀请粉丝"),
    ADD_AD((byte) 0x6E, "投放广告", null, "", "投放广告"),
    AD_RETURN_POINT((byte) 0x70, "积分退还", null, "", "积分退还"),
    SALES_COMMISSION((byte) 0x73, "推荐E友收益", new MerchantTransactionCategoryEnum[]{MerchantTransactionCategoryEnum.RECOMMEND_INCOME}, "推荐E友收益", "下级用户买单和购物提成收益"),
    VOLUME_COMMISSION((byte) 0x74, "推荐E友收益", new MerchantTransactionCategoryEnum[]{MerchantTransactionCategoryEnum.RECOMMEND_INCOME}, "推荐E友收益", "下级商家买单和购物营业额提成收益"),
    BACKAGE((byte) 0x75, "平台充值", new MerchantTransactionCategoryEnum[]{MerchantTransactionCategoryEnum.RECHARGE}, "", "平台充值"),
    AD_DOWN((byte) 0x76, "红包未领取", new MerchantTransactionCategoryEnum[]{MerchantTransactionCategoryEnum.REFUND_MONEY}, "红包未领取", "红包未领取"),
    RED_PACKET((byte) 0x77, "红包", new MerchantTransactionCategoryEnum[]{MerchantTransactionCategoryEnum.RED_PACKET}, "发红包", "发红包"),
    PLATFORM_RED_SWEEP_REG((byte) 0x78, "E店红包", new MerchantTransactionCategoryEnum[]{MerchantTransactionCategoryEnum.RED_PACKET}, "E店红包-邀请奖励", "邀请奖励"),
    FREIGHT_REFUND((byte) 0x79, "运费退回", new MerchantTransactionCategoryEnum[]{MerchantTransactionCategoryEnum.REFUND_MONEY}, "运费退回", "运费退回");

    private Byte value;

    private String name;

    private MerchantTransactionCategoryEnum[] category;

    private String descPrefix;

    private String remark;

    MerchantTransactionTypeEnum(Byte value, String name, MerchantTransactionCategoryEnum[] category, String descPrefix, String remark) {
        this.value = value;
        this.name = name;
        this.category = category;
        this.descPrefix = descPrefix;
        this.remark = remark;
    }

    public Byte getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getDescPrefix() {
        return descPrefix;
    }

    public String getRemark() {
        return remark;
    }

    public MerchantTransactionCategoryEnum[] getCategory() {
        return category;
    }

    public MerchantTransactionCategoryEnum getPriorityCategory() {
        if(category == null || category.length == 0){
            return null;
        }
        return category[0];
    }

    /**
     * 根据交易种类获取相应的交易类型
     *
     * @param category 交易种类
     * @return
     * @author jiangxinjun
     * @date 2017年10月20日
     */
    public static List<Byte> getEnum(MerchantTransactionCategoryEnum category) {
        List<Byte> rtn = null;
        MerchantTransactionTypeEnum[] values = MerchantTransactionTypeEnum.values();
        for (MerchantTransactionTypeEnum object : values) {
            if (object.getCategory() != null) {
                for (MerchantTransactionCategoryEnum categoryItem : object.getCategory()) {
                    if (categoryItem.equals(category)) {
                        if (rtn == null) {
                            rtn = new ArrayList<>();
                        }
                        rtn.add(object.getValue());
                    }
                }
            }
        }
        return rtn;
    }

    public static MerchantTransactionTypeEnum getEnum(Byte val) {
        MerchantTransactionTypeEnum[] values = MerchantTransactionTypeEnum.values();
        for (MerchantTransactionTypeEnum object : values) {
            if (object.getValue().equals(val)) {
                return object;
            }
        }
        return null;
    }
}