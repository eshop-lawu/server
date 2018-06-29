package com.lawu.eshop.order.srv.constants;

/**
 * 分布式事务业务类型
 * 
 * @author Sunny
 * @date 2017年4月14日
 */
public class TransactionConstant {

    /**
     * 隐藏默认的构造函数
     */
    private TransactionConstant() {
        throw new IllegalAccessError("Utility class");
    }

    /**
     * 买单
     */
    public static final byte PAYORDER = 0x01;

    /**
     * 创建购物订单
     */
    public static final byte CREATE_ORDER = 0x02;

    /**
     * 取消购物订单
     */
    public static final byte CANCEL_ORDER = 0x03;

    /**
     * 确认收货 用户手动确认收货
     */
    public static final byte TRADING_SUCCESS = 0x04;

    /**
     * 定时任务自动评论
     */
    public static final byte AUTO_COMMENT = 0x05;

    /**
     * 商家同意退款
     */
    public static final byte AGREE_TO_REFUND = 0x06;

    /**
     * 删除订单
     */
    public static final byte DELETE_SHOPPING_ORDER = 0x08;

    /**
     * 创建购物订单-成为商家粉丝
     */
    public static final byte CREATE_ORDER_FANS = 0x0f;

    /**
     * 商家同意退款-删除评论
     */
    public static final byte AGREE_TO_REFUND_DELETE_COMMENT = 0x10;

    /**
     * 确认收货 增加销量
     */
    public static final byte TRADING_SUCCESS_INCREASE_SALES = 0x11;

    /**
     * 确认收货之后，超过退款时间，打款给商家
     */
    public static final byte TRADING_SUCCESS_PAYMENTS_TO_MERCHANT = 0x12;

    /**
     * 订单付款成功,推送给商家
     */
    public static final byte PAYMENT_SUCCESSFUL_PUSH_TO_MERCHANT = 0x13;

    /**
     * 创建购物订单扣除积分
     */
    public static final byte CREATE_ORDER_DEDUCTION_POINTS = 0x14;
    
    /**
     * 取消购物订单退还积分
     */
    public static final byte CANCEL_ORDER_REFUND_POINTS = 0x15;
}
