package com.lawu.eshop.property.srv.constans;

/**
 * 
 * <p>
 * Description: 分布式事务业务类型
 * </p>
 * @author Yangqh
 * @date 2017年4月11日 下午7:46:08
 *
 */
public class TransactionConstant {

    /**
     * 订单余额支付
     */
    public static final byte ORDER_BALANCE_PAY = 0x01;
    /**
     * 买余额支付
     */
    public static final byte BILL_BALANCE_PAY = 0x02;

    /**
     * 买单
     */
    public static final byte PAYORDER = 0x03;
    
    /**
     * 支付商品订单成功
     */
    public static final byte PAY_SHOPPING_ORDER = 0x04;

    /**
     * 交保证金成功
     */
    public static final byte HANDLE_DESPOISIT_SUCCESS = 0x05;

    /**
     * 交保证金财务审核成功
     */
    public static final byte HANDLE_DESPOISIT_AUDIT_PASS = 0x06;
    /**
     * 退保证金财务审核注销
     */
    public static final byte HANDLE_DESPOISIT_AUDIT_CANCEL = 0x07;
    /**
     * 用户发红包支付成功后修改红包状态
     */
    public static final byte HANDLE_MEMBER_RED_PACKET = 0x08;

    /**
     * 退保证金财务审核成功后修改商品下架
     */
    public static final byte HANDLE_DESPOISIT_AUDIT_CANCEL_DOWN_PRODUCT = 0x09;

    /**
     * 商家发广告异步回调后发笑锡修改广告记录
     */
    public static final byte HANDLE_MERCHANT_AD = 0x0A;

    /**
     * 积分兑换抽奖
     */
    public static final byte POINT_CONVERT_LOTTERY = 0x0B;

    /**
     * 支付购物订单成功后修改用户等级
     */
    public static final byte SHOPPING_ORDER_PAY_UPDATE_USER_GRADE = 0x0C;

    /**
     * 支付买单订单成功后修改用户等级
     */
    public static final byte PAY_ORDER_PAY_UPDATE_USER_GRADE = 0x0D;

}
