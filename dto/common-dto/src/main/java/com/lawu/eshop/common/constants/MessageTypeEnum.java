package com.lawu.eshop.common.constants;

/**
 * 消息类型枚举类
 * Created by Administrator on 2017/3/30.
 */
public enum MessageTypeEnum {
    MESSAGE_TYPE_INVITE_FANS((byte) 0x01),//邀请粉丝--会员

    MESSAGE_TYPE_ACTIVE((byte) 0x03),//活动消息

    
    MESSAGE_TYPE_WITHDRAW_APPLY((byte) 0x06),//提现申请
    MESSAGE_TYPE_WITHDRAW_SUCCESS((byte) 0x07),//提现成功
    MESSAGE_TYPE_WITHDRAW_FAIL((byte) 0x08),//提现失败

    MESSAGE_TYPE_RECHARGE_BALANCE((byte) 0x09),//余额充值
    MESSAGE_TYPE_RECHARGE_POINT((byte) 0x0A),//积分充值 10

    MESSAGE_TYPE_PAY_ORDER_SUCCESS((byte) 0x0B),//买单成功 11
    MESSAGE_TYPE_RECOMMEND_MEMBER_BALANCE((byte) 0x10),//推荐E友获取现金 16
    MESSAGE_TYPE_RECOMMEND_MEMBER_POINT((byte) 0x11),//推荐E友获取积分 17
    MESSAGE_TYPE_RECOMMEND_MERCHANT_BALANCE((byte) 0x12),//推荐商家获取现金 18
    MESSAGE_TYPE_RECOMMEND_MERCHANT_POINT((byte) 0x13),//推荐商家获取积分 19


    MESSAGE_TYPE_CHECK_AD_SUCCESS((byte) 0x14),//ad审核通过 20
    MESSAGE_TYPE_CHECK_AD_FAIL((byte) 0x15),//ad审核不通过 21
    MESSAGE_TYPE_CHECK_STORE_SUCCESS((byte) 0x16),//门店审核通过22
    MESSAGE_TYPE_CHECK_STORE_FAIL((byte) 0x17),//门店审核不通过23
    MESSAGE_TYPE_CHECK_DEPOSIT_SUCCESS((byte) 0x18),//保证金审核通过24
    
    MESSAGE_TYPE_ORDER_PAY((byte) 0x02),//订单付款
    MESSAGE_TYPE_ORDER_START_SEND((byte) 0x04),//订单已发货
    MESSAGE_TYPE_ORDER_SENDING((byte) 0x05),//订单正在派件
    MESSAGE_TYPE_REFUND_AGREE((byte) 0x0C),//同意退款 12
    MESSAGE_TYPE_REFUND_REFUSE((byte) 0x0D),//拒绝退款 13
    MESSAGE_TYPE_REFUND_SUCCESS((byte) 0x0E),//退款成功 14
    MESSAGE_TYPE_PAYMENT_SUCCESS((byte) 0x0F),//付款成功 15
    MESSAGE_TYPE_RETURN((byte) 0x2B),//商家填写退货地址提醒 43
    
    //商家端
    MESSAGE_TYPE_NEW_ORDER((byte) 0x19),//新增订单 25
    MESSAGE_TYPE_SEND_NOTICE((byte) 0x1A),//商家发货提醒 26
    MESSAGE_TYPE_RETURN_NOTICE((byte) 0x1B),//商家退货提醒 27
    MESSAGE_TYPE_REFUND_APPLY((byte) 0x26),//退款申请 38
    MESSAGE_TYPE_USER_SEND((byte) 0x1C),//用户已发货 28

    MESSAGE_TYPE_PLATFORM_NOTICE((byte) 0x1D),//平台通知 29

    MESSAGE_TYPE_REDPACKET((byte) 0x1E),//现金红包 30

    MESSAGE_TYPE_AD_AUTO_DOWN((byte) 0x1F),//ad自动下架 31
    MESSAGE_TYPE_INVITE_FANS_MERCHANT((byte) 0x20),//邀请粉丝--商家 32

    MESSAGE_TYPE_ORDER_SIGN((byte) 0x21),//订单已签收 33

    MESSAGE_TYPE_SEEAD((byte) 0x22),//看广告获取金额 34
    MESSAGE_TYPE_ROB_PIAISE((byte) 0x23),//抢赞获取金额 35

    MESSAGE_TYPE_CHECK_DEPOSIT_FAIL((byte) 0x24),//保证金审核未通过 36
    MESSAGE_TYPE_AD_FORCE_DOWN((byte) 0x25),//ad强制下架 37
    MESSAGE_TYPE_USE_POINT_AD((byte) 0x27),//消费积分投放广告 39
    MESSAGE_TYPE_USE_POINT_REDPACKET((byte) 0x28),//消费积分红包 40
    MESSAGE_TYPE_ORDER_SUCCESS((byte) 0x29),//订单成功--商家 41
    MESSAGE_TYPE_PAY_ORDER_SUCCESS_MERCHANT((byte) 0x2A),//买单成功--商家 42

    MESSAGE_TYPE_MONEY_FREEZE((byte) 0x2C),//资金冻结 44

    MESSAGE_TYPE_MONEY_UNFREEZE((byte) 0x2D),//资金解冻 45
	
	MESSAGE_TYPE_AD_PRAISE_NOTICE((byte) 0x2E),//抢赞通知
	
	MESSAGE_TYPE_REPLIED_WORK_ORDER((byte) 0x2F),//工单已处理 47
	
	MESSAGE_TYPE_PRODUCT_FORCE_DOWN((byte) 0x30),//商品强制下架48
	
	MESSAGE_TYPE_PLAT_RED_PACKET((byte) 0x31),//领取平台红包49
    
    MESSAGE_TYPE_SECKILL_ACTIVITY_ABOUT_START((byte) 0x32),//抢购即将开始提醒50

    MESSAGE_TYPE_SECKILL_ACTIVITY_PRODUCT_CHECK_FAIL((byte) 0x33),//活动商品审核不通过51
    
    MESSAGE_TYPE_REVOKE_REFUND_REQUEST((byte) 0x34),//用户撤销退款申请52
	
	MESSAGE_TYPE_INVITER_BOUNTY((byte) 0x35),//邀请注册奖励金 53
	
	MESSAGE_TYPE_AD_DOWN((byte) 0x36),//广告自动下架54

    LOTTERY_ACTIVITY_WIN((byte) 0x37),//中奖通知55

    PLAT_RECHARGE_BALANCE((byte) 0x38),//平台充值余额56
    PLAT_RECHARGE_POINT((byte) 0x39),//平台充值积分57

    MESSAGE_TYPE_SHARE_PRODUCT((byte) 0x3A),//分享商品58
    MESSAGE_TYPE_SHARE_AD_FLAT((byte) 0x3B),//分享平面广告59
    MESSAGE_TYPE_SHARE_AD_VIDEO((byte) 0x3C),//分享视频广告60
    MESSAGE_TYPE_SHARE_AD_PRAISE((byte) 0x3D),//分享E咻广告61
    MESSAGE_TYPE_SHARE_GAME_MIND((byte) 0x3E),//分享头脑游戏62
    MESSAGE_TYPE_SHARE_REDPACKET_MEMBER((byte) 0x3F),//用户分享红包63
    MESSAGE_TYPE_SHARE_REDPACKET_MERCHANT((byte) 0x40),//商家分享红包64
    MESSAGE_TYPE_SHARE_SECKILL((byte) 0x41),//分享抢购65
    MESSAGE_TYPE_SHARE_GAME_PUZZLE((byte) 0x42),//分享拼图游戏66
    MESSAGE_TYPE_SHARE_GAME_DIAL((byte) 0x43),//分享转盘游戏67

    LOTTERY_ACTIVITY_WIN_MONEY((byte) 0x44),//中奖通知68
    LOTTERY_ACTIVITY_WIN_POINT((byte) 0x45);//中奖通知69

    private Byte val;
    
    public Byte getVal() {
		return val;
	}

	MessageTypeEnum(Byte val) {
        this.val = val;
    }

    public static MessageTypeEnum getEnum(Byte val) {
        MessageTypeEnum[] values = MessageTypeEnum.values();
        for (MessageTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }
}
