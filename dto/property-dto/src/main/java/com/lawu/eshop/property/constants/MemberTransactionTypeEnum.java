package com.lawu.eshop.property.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * 会员交易类型枚举
 * 
 * @author Sunny
 * @date 2017/3/29
 */
public enum MemberTransactionTypeEnum {

	RECHARGE_BALANCE((byte) 0x01, "余额充值", new MemberTransactionCategoryEnum[]{MemberTransactionCategoryEnum.RECHARGE},"余额充值","余额充值"),
	LOWER_INCOME((byte) 0x05, "推荐E友收益", new MemberTransactionCategoryEnum[]{MemberTransactionCategoryEnum.RECOMMEND_INCOME},"推荐E友收益","广告提成收益"),
	PAY((byte) 0x06, "买单", new MemberTransactionCategoryEnum[]{},"","买单付款"),
	PAY_ORDERS((byte) 0x07, "购物", new MemberTransactionCategoryEnum[]{MemberTransactionCategoryEnum.SHOPPING},"","购物付款"),
	RECHARGE_POINT((byte) 0x08, "积分充值", new MemberTransactionCategoryEnum[]{MemberTransactionCategoryEnum.RECHARGE},"积分充值","积分充值"),
	WITHDRAW((byte) 0x09, "提现", new MemberTransactionCategoryEnum[]{MemberTransactionCategoryEnum.WITHDRAW},"提现申请","提现申请"),
	WITHDRAW_BACK((byte) 0x0A, "提现失败", new MemberTransactionCategoryEnum[]{MemberTransactionCategoryEnum.WITHDRAW, MemberTransactionCategoryEnum.REFUND_MONEY},"提现失败退款","提现失败"),
	REFUND_ORDERS((byte) 0x0B, "购物退款", new MemberTransactionCategoryEnum[]{MemberTransactionCategoryEnum.REFUND_MONEY},"","购物退款"),
	SALES_COMMISSION((byte) 0x0C, "推荐E友收益", new MemberTransactionCategoryEnum[]{MemberTransactionCategoryEnum.RECOMMEND_INCOME}, "推荐E友收益","下级用户买单和购物提成收益"),
	VOLUME_COMMISSION((byte) 0x0D, "推荐E友收益", new MemberTransactionCategoryEnum[]{MemberTransactionCategoryEnum.RECOMMEND_INCOME}, "推荐E友收益","下级商家买单和购物营业额提成收益"),
	BACKAGE((byte) 0x0F, "平台充值", new MemberTransactionCategoryEnum[]{MemberTransactionCategoryEnum.RECHARGE},"","平台充值"),
	AD_QZ((byte) 0x10, "咻一咻", new MemberTransactionCategoryEnum[]{MemberTransactionCategoryEnum.PREFERRED_RED_SWEEP},"咻一咻-","咻一咻广告"),
	USER_REDPACKET_ADD((byte)0x11, "红包未领取", new MemberTransactionCategoryEnum[]{MemberTransactionCategoryEnum.REFUND_MONEY},"红包未领取","红包未领取"),
	ADD_RED_SWEEP((byte)0x12,"发红包", new MemberTransactionCategoryEnum[]{MemberTransactionCategoryEnum.RED_SWEEP},"发红包","发红包"),
	MERCHANT_RED_SWEEP((byte) 0x14, "商家红包", new MemberTransactionCategoryEnum[]{MemberTransactionCategoryEnum.RED_SWEEP},"商家红包-","商家红包"),
	MEMBER_RED_SWEEP((byte) 0x15, "个人红包", new MemberTransactionCategoryEnum[]{MemberTransactionCategoryEnum.RED_SWEEP},"个人红包-","个人红包"),
	PLATFORM_RED_SWEEP((byte) 0x16, "E店红包", new MemberTransactionCategoryEnum[]{MemberTransactionCategoryEnum.RED_SWEEP},"E店红包","E店红包"),
	AD_PLANE((byte) 0x17, "猜一猜", new MemberTransactionCategoryEnum[]{MemberTransactionCategoryEnum.PREFERRED_RED_SWEEP},"猜一猜-","猜一猜"),
	AD_VIDEO((byte) 0x18, "看一看", new MemberTransactionCategoryEnum[]{MemberTransactionCategoryEnum.PREFERRED_RED_SWEEP},"看一看-","看一看"),
	POINT_CONVERT_LOTTERY((byte) 0x19, "积分兑换抽奖", null,"抽奖","积分兑换抽奖"),
	PLATFORM_RED_SWEEP_REG((byte) 0x1A, "E店红包", new MemberTransactionCategoryEnum[]{MemberTransactionCategoryEnum.RED_SWEEP},"E店红包-邀请奖励","邀请奖励"),
    PLATFORM_RED_SWEEP_ACTIVITY((byte) 0x1B, "E店红包", new MemberTransactionCategoryEnum[]{MemberTransactionCategoryEnum.RED_SWEEP},"E店红包-活动奖励","活动奖励"),
	POINT_LOTTERY((byte) 0x1C, "积分抽奖", null,"抽奖","积分抽奖"),
	POINT_SHOPPING((byte) 0x1D, "积分抵换额购物", null,"购物","购物时积分抵换余额支付"),
	POINT_REDUND((byte) 0x1E, "积分抵换购物退款", null,"购物","购物时积分抵换余额支付退款"),
	POINT_GAME_QUESTION((byte) 0x1F, "头脑PK消费", null,"游戏","头脑PK游戏积分消费"),
	POINT_GAME_PUZZLE((byte) 0x20, "拼图游戏消费", null,"游戏","拼图游戏积分消费"),
	POINT_GAME_LUCKY_DIAL((byte) 0x20, "幸运转盘消费", null,"游戏","幸运转盘游戏积分消费"),
	POINT_GAME_QUESTION_IN((byte) 0x21, "头脑PK获得", null,"游戏","头脑PK游戏获得积分"),
	POINT_GAME_PUZZLE_IN((byte) 0x22, "拼图游戏获得", null,"游戏","拼图游戏获得积分"),
	POINT_GAME_LUCKY_DIAL_IN((byte) 0x23, "幸运转盘获得", null,"游戏","幸运转盘游戏获得积分"),
	POINT_GAME_QUESTION_BACK((byte) 0x24, "头脑PK退还", null,"游戏","头脑PK游戏退还积分"),
	POINT_GAME_PUZZLE_BACK((byte) 0x25, "拼图游戏退还", null,"游戏","拼图游戏退还积分");

	private Byte value;

	private String name;
	
	private MemberTransactionCategoryEnum[] category;

	private String descPrefix;

	private String remark;

	MemberTransactionTypeEnum(Byte value, String name, MemberTransactionCategoryEnum[] category,String descPrefix,String remark) {
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

	public String getRemark() {
		return remark;
	}

	public MemberTransactionCategoryEnum[] getCategory() {
        return category;
    }

	public String getDescPrefix() {
		return descPrefix;
	}

	public MemberTransactionCategoryEnum getPriorityCategory() {
		if(category == null || category.length == 0){
			return null;
		}
        return category[0];
    }

	/**
	 * 根据交易种类获取相应的交易类型
	 * @param category 交易种类
	 * @return
	 * @author jiangxinjun
	 * @date 2017年10月20日
	 */
    public static List<Byte> getEnum(MemberTransactionCategoryEnum category) {
        List<Byte> rtn = null;
		MemberTransactionTypeEnum[] values = MemberTransactionTypeEnum.values();
		for (MemberTransactionTypeEnum object : values) {
			if (object.getCategory() != null) {
			    for (MemberTransactionCategoryEnum categoryItem : object.getCategory()) {
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
    
    public static MemberTransactionTypeEnum getEnum(Byte val) {
        MemberTransactionTypeEnum[] values = MemberTransactionTypeEnum.values();
        for (MemberTransactionTypeEnum object : values) {
            if (object.getValue().equals(val)) {
                return object;
            }
        }
        return null;
    }
}