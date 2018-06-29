package com.lawu.eshop.idworker.client.impl;

/**
 * 业务ID类型
 * @author Leach
 * @date 2017/10/24
 */
public enum BizIdType {
    /**
     *  会员
     */
    MEMBER("M"),
    
    /**
     *  商家
     */
    MERCHANT("B"),
    
    /**
     *  代理商
     */
    AGENT("A"),
    
    /**
     *  商品订单
     */
    ORDER("O"),
    
    /**
     *  广告
     */
    AD("AD"),
    
    /**
     *  用户红包
     */
    USER_REDPACKET("UR"),
    
    /**
     *  买单
     */
    PAY_ORDER("PO"),
    
    /**
     *  交易
     */
    TRANSACTION("T"),
    
    /**
     *  退款
     */
    REFUND("RE"),
    
    /**
     * 保证金
     */
    DEPOSIT("D"),
    
    /**
     * 积分
     */
    POINT("P"),
    
    /**
     * 爱心账户
     */
    LOVE("L"),
    
    /**
     * 充值
     */
    RECHARGE("RE"),
    
    /**
     * 提现
     */
    CASH("C"),
    
    /**
     * 业务编号
     */
    BUSINESS("BZ"),
    
    /**
     * 头脑PK参与编号
     */
    MINDGAME("MG"),
    
    /**
     * 题目
     */
    QUESTION("Q"),
    
    PUZZLE("PU");

    private String prefix;

    BizIdType(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
