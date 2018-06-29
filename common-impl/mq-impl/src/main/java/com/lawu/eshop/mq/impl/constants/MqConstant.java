package com.lawu.eshop.mq.impl.constants;

/**
 * @author Leach
 * @date 2017/4/11
 */
public class MqConstant {

    /** topic定义 */

    /**
     * ad-srv模块MQ生产者topic
     */
    public static final String TOPIC_AD_SRV = "ad_srv";

    /**
     * mall-srv模块MQ生产者topic
     */
    public static final String TOPIC_MALL_SRV = "mall_srv";

    /**
     * order-srv模块MQ生产者topic
     */
    public static final String TOPIC_ORDER_SRV = "order_srv";

    /**
     * product-srv模块MQ生产者topic
     */
    public static final String TOPIC_PRODUCT_SRV = "product_srv";

    /**
     * property-srv模块MQ生产者topic
     */
    public static final String TOPIC_PROPERTY_SRV = "property_srv";

    /**
     * user-srv模块MQ生产者topic
     */
    public static final String TOPIC_USER_SRV = "user_srv";

    /**
     * activity-srv模块MQ生产者topic
     */
    public static final String TOPIC_ACTIVITY_SRV = "activity_srv";

    /**
     * game-srv模块MQ生产者topic
     */
    public static final String TOPIC_GAME_SRV = "game_srv";

    /******************************************/
    /******* ad-srv模块tag **********/

    /**
     * 商家发广告
     */
    public static final String TAG_AD_ME_CUT_POINT = "ad_me_cutPoint";

    /**
     * 商家广告金额退还
     */
    public static final String TAG_AD_ME_ADD_POINT = "ad_me_addPoint";

    /**
     * 获取E咻金额
     */
    public static final String TAG_AD_USER_ADD_POINT = "ad_user_addPoint";

    public static final String TAG_RP_ME_CUT_POINT = "rp_me_cutPoint";

    /**
     * 点广告
     */
    public static final String TAG_AD_USER_CLICK_POINT = "rp_user_cutPoint";

    /**
     * 领取商家红包
     */
    public static final String TAG_AD_USER_SWEEP_RED = "rp_user_sweep_red";

    /**
     * 邀请注册奖励金
     */
    public static final String TAG_AD_USER_TAKE_INVITER_BOUNTY = "tag_ad_user_take_inviter_bounty";

    //领取平台红包
    public static final String TAG_AD_USER_TAKE_PLAT_RED = "tag_ad_user_take_plat_redpacket";

    //用户红包注销退回
    public static final String TAG_AD_USER_REDPACKET_CANNEL_REFUND_MONEY = "tag_ad_user_redpacket_cannel_refund_money";

    //用户领取红包（用户发的红包）
    public static final String TAG_USER_GET_AD_USER_REDPACKET = "tag_user_get_ad_user_redpacket";

    //广告自动下架提醒
    public static final String TAG_TO_AD_DOWN_NOTICE = "tag_to_ad_down_notice";

    /******************************************/
    /******* mall-srv模块tag **********/
    /**
     * 评论商品时发布的MQ消息
     */
    public static final String TAG_COMMENT_PRODUCT = "comment_product";
    /**
     * 评论商家时发布MQ消息
     */
    public static final String TAG_COMMENT_MERCHANT = "comment_merchant";

    /**
     * 增加站内消息发推送
     */
    public static final String TAG_GTPUSH = "gtpush";

    /**
     * 增加站内消息发推送所有用户
     */
    public static final String TAG_GTPUSHALL = "pushall";
    /**
     * 增加站内消息发推送所有区域内用户
     */
    public static final String TAG_GTPUSH_AREA = "push_area";

    /******************************************/
    /******* order-srv模块tag **********/

    /**
     * 买单更改状态发布MQ增加买单笔数
     */
    public static final String TAG_BUY_NUMBERS = "buy_numbers";

    /**
     * 更改评论状态发布MQ增加评论人数
     */
    public static final String TAG_COMMENTS_COUNT = "comments_count";

    /**
     * 创建购物订单时发布的MQ消息
     */
    public static final String TAG_CREATE_ORDER = "create_order";

    /**
     * 取消购物订单时发布的MQ消息
     */
    public static final String TAG_CANCEL_ORDER = "cancel_order";

    /**
     * 确认收货时发布的MQ消息
     */
    public static final String TAG_TRADING_SUCCESS = "trading_success";

    /**
     * 定时器发送增加评论信息消息
     */
    public static final String TAG_AUTO_COMMENT = "auto_comment";

    /**
     * 商家同意退款发布的MQ消息
     */
    public static final String TAG_AGREE_TO_REFUND = "agree_to_refund";

    /**
     * 商家同意退款 发布给mall模块的MQ消息，删除商品评论
     */
    public static final String TAG_AGREE_TO_REFUND_DELETE_COMMENT = "agree_to_refund_delete_comment";

    /**
     * 提醒卖家发货时发布的MQ消息
     */
    public static final String TAG_REMIND_SHIPMENTS = "remind_shipments";

    /**
     * 删除购物订单时发布的MQ消息
     */
    public static final String TAG_DELETE_SHOPPING_ORDER = "delete_shopping_order";

    /**
     * 退款中-待商家处理 退款类型-退款 商家处理超时发布的MQ消息
     */
    public static final String TAG_TO_BE_CONFIRMED_FOR_REFUND_REMIND = "to_be_confirmed_for_refund_remind";

    /**
     * 退款中-商家填写退货地址发布的MQ消息 发送到mall模块，提醒买家操作
     */
    public static final String TAG_FILL_RETURN_ADDRESS_REMIND = "fill_return_address_remind";

    /**
     * 退款中-等待买家退货 买家处理超时发布的MQ消息 发送到mall模块，提醒商家操作
     */
    public static final String TAG_TO_BE_RETURN_REMIND = "to_be_return_remind";

    /**
     * 退款中-等待商家退款 买家处理超时发布的MQ消息 发送到mall模块，提醒买家操作
     */
    public static final String TAG_TO_BE_REFUND_REMIND = "to_be_refund_remind";

    /**
     * 创建购物订单时发布的MQ消息 发送到user模块，用户 成为商家粉丝
     */
    public static final String TAG_CREATE_ORDER_FANS = "create_order_fans";

    /**
     * 确认收货时发布的MQ消息 发送到产品模块，添加销量
     */
    public static final String TAG_TRADING_SUCCESS_INCREASE_SALES = "trading_success_increase_sales";

    /**
     * 确认收货之后执行定时任务，如果超过退款时间发布的MQ消息 发送到资产模块，打款给商家
     */
    public static final String TAG_TRADING_SUCCESS_PAYMENTS_TO_MERCHANT = "trading_success_payments_to_merchant";

    /**
     * 订单付款成功发布的MQ消息 发送到商城模块，推送给商家和用户
     */
    public static final String TAG_PAYMENT_SUCCESSFUL_PUSH = "payment_successful_push";

    /**
     * 定时任务 提醒买家有未支付的订单-发布的MQ消息 发送到商城模块，推送给买家
     */
    public static final String TAG_ORDER_NO_PAYMENT_PUSH_TO_MEMBER = "order_no_payment_push_to_member";

    /**
     * 商家拒绝退款退款 发送到商城模块，推送给买家
     */
    public static final String TAG_REFUSE_REFUND_REMIND = "refuse_refund_remind";

    /**
     * 商家新增订单交易收入 发送到商城模块，推送给商家
     */
    public static final String TAG_ORDERS_TRADING_INCOME_NOTICE = "orders_trading_income_notice";

    /**
     * 撤销退款申请提醒
     */
    public static final String TAG_REVOKE_REFUND_REQUEST_NOTICE = "revoke_refund_request_notice";

    /**
     * 创建购物订单扣除积分
     */
    public static final String TAG_CREATE_ORDER_DEDUCTION_POINTS = "create_order_deduction_points";

    /**
     * 取消购物订单退还积分
     */
    public static final String TAG_CANCEL_ORDER_REFUND_POINTS = "cancel_order_refund_points";

    /**
     * 审核成功或失败后发送消息通知mall-srv模块发送推送消息给商家
     */
    public static final String TAG_PROPERTY_DEPOSIT_DO_RESULT = "property_deposit_do_result";
    
    /**
     * 订单付款, 完成购物任务
     */
    public static final String TAG_COMPLETE_SHOPPING_TASK = "complete_shopping_task";

    /******************************************/
    /******* product-srv模块tag **********/
    public static final String TAG_DEL_COMMENT = "del_comment";

    /**
     * 活动即将开始提醒
     */
    public static final String TAG_SECKILL_ACTIVITY_ABOUT_START_NOTICE = "seckill_activity_about_start_notice";

    /******************************************/
    /******* property-srv模块tag **********/

    /**
     * 买单时支付成功发布的MQ消息改状态
     */
    public static final String TAG_PAYORDER = "payorder";

    /**
     * 购物订单支付时发布的MQ消息改状态
     */
    public static final String TAG_PAY_SHOPPING_ORDER = "pay_shopping_order";

    /**
     * 缴纳保证金发布的MQ消息改门店状态
     */
    public static final String TAG_HANDLE_DEPOSIT = "handle_deposit";

    /**
     * 核实操作成功后，发送消息修改门店状态为：待审核,并修改门店审核显示状态
     */
    public static final String TAG_HANDLE_DEPOSIT_AUDIT_PASS = "handle_deposit_audit_pass";

    /**
     * 退款成功操作后，发送消息修改门店状态为：注销
     */
    public static final String TAG_HANDLE_DEPOSIT_AUDIT_CANCEL = "handle_deposit_audit_cancel";

    /**
     * 用户发红包支付成功后修改红包状态
     */
    public static final String TAG_HANDLE_MEMBER_RED_PACKET = "handle_deposit_member_red_packet";

    /**
     * 保证金财务审核成功后修改商品下架
     */
    public static final String TAG_HANDLE_DEPOSIT_DOWN_PRODUCT = "tag_handle_deposit_down_product";

    /**
     * 商家发广告异步回调后发笑锡修改广告记录
     */
    public static final String TAG_HANDLE_AD = "tag_handle_ad";

    /**
     * 支付购物订单成功后修改用户等级
     */
    public static final String TAG_SHOPPING_ORDER_PAY_UPDATE_USER_GRADE = "shopping_order_pay_update_user_grade";

    /**
     * 支付买单订单成功后修改用户等级
     */
    public static final String TAG_PAY_ORDER_PAY_UPDATE_USER_GRADE = "pay_order_pay_update_user_grade";

    /******************************************/
    /******* user-srv模块tag **********/

    /**
     * 注册时发布的MQ消息tag
     */
    public static final String TAG_REG = "reg";

    /**
     * 成为粉丝收益
     */
    public static final String TAG_MEMBER_FANS = "member_fans";

    /**
     * 商家邀请粉丝退还积分
     */
    public static final String TAG_MERCHANT_FANS = "merchant_fans";

    /**
     * 邀请粉丝
     */
    public static final String TAG_INVITE_FANS = "invite_fans";

    /**
     * 积分兑换抽奖
     */
    public static final String TAG_POINT_CONVERT_LOTTERY = "point_convert_lottery";

    /**
     * 新年红包注册助力
     */
    public static final String TAG_REG_HELP_RED_PACKET = "reg_help_red_packet";

    /**
     * 新年红包注册助力,修改用户头像刷新助力明细
     */
    public static final String TAG_REG_HELP_RED_PACKET_REFLASH_HEADIMG = "reg_help_red_packet_reflash_headimg";

    /**
     * 冻结用户账号
     */
    public static final String TAG_FREEZE_USER_ACCOUNT = "freeze_user_account";

    /**
     * 更换微信绑定公众号刷新openId
     */
    public static final String TAG_HELP_RED_PACKET_REFLASH_ATTEND_WX_OPENID = "help_red_packet_attend_reflash_wx_openid";

    /******************************************/
    /******* activity-srv模块tag **********/

    /**
     * 积分抽奖发布的MQ消息tag
     */
    public static final String TAG_DRAW_LOTTERY = "draw_lottery";

    /**
     * 发送红包
     */
    public static final String TAG_RED_PACKET_SEND = "red_packet_send";

    /**
     * 积分夺宝发布的MQ消息tag
     */
    public static final String TAG_POINT_LOTTERY = "point_lottery";

    /**
     * 领奖发布的MQ消息tag
     */
    public static final String TAG_TAKE_LOTTERY = "take_lottery";

    /******************************************/
    /******* game-srv模块tag **********/

    /**
     * 头脑PK扣除积分
     */
    public static final String TAG_GAME_MIND_DEDUCTION_POINTS = "game_mind_deduction_points";

    /**
     * 头脑PK增加积分
     */
    public static final String TAG_GAME_MIND_INCREASE_POINTS = "game_mind_increase_points";
    /**
     * 拼图增加积分
     */
    public static final String TAG_PUZZLE_GAME_INCREASE_POINT = "puzzle_game_increase_point";
    /**
     * 拼图扣除积分
     */
    public static final String TAG_PUZZLE_GAME_DEDUCTION_POINT = "puzzle_game_deduction_point";

    /**
     * 转盘游戏扣除积分
     */
    public static final String TAG_GAME_DIAL_DEDUCTION_POINT = "game_dial_deduction_point";
    /**
     * 转盘游戏领奖
     */
    public static final String TAG_GAME_DIAL_TAKE_LOTTERY = "game_dial_take_lottery";
    
    /**
     * 头脑PK退还积分
     */
    public static final String TAG_GAME_MIND_REFUND_POINTS = "game_mind_refund_points";
    
    public static final String TAG_GAME_PUZZLE_REFUND_POINTS="game_puzzle_refund_points"; 
}
