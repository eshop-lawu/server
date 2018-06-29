package com.lawu.eshop.pay;/**
 * Created by ${Yangqh} on 2018/1/22.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * <p> </p>
 *
 * @author yangqh
 * @date 2018/1/22 17:23
 */
public class TradeConstants {

    ////////////////////////////
    //// 支付宝接口常量
    ///////////////////////////
    //单笔转账到支付宝账户接口：收款方账户类型
    public static final String AlipayPayeeType = "ALIPAY_LOGONID";

    //---------------code
    // 交易成功，对应status=SUCCESS
    public static final String AlipayTransOrderQuery_Code_SUCCESS = "10000";
    //--------------status
    public static final String AlipayTransOrderQuery_STATUS_SUCCESS = "SUCCESS";
    //---------------sub_code
    //转账订单不存在
    public static final String AlipayTransOrderQuery_SubCode_ORDER_NOT_EXIST = "ORDER_NOT_EXIST";

    ////////////////////////////
    //// 微信接口常量
    ///////////////////////////


}
