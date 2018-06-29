package com.lawu.eshop.member.api.service;

import com.lawu.eshop.property.param.BalancePayValidateDataParam;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.property.param.BalancePayDataParam;
import com.lawu.eshop.property.param.BalancePayParam;

@FeignClient(value = "property-srv")
public interface BalancePayService {

    /**
     * 订单余额支付
     *
     * @param param
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(method = RequestMethod.POST, value = "balancePay/orderPay")
    Result orderPay(@RequestBody BalancePayDataParam param);

    /**
     * 买单余额支付
     *
     * @param dparam
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(method = RequestMethod.POST, value = "balancePay/billPay")
    Result billPay(@RequestBody BalancePayDataParam dparam);

    /**
     * 余额充值积分
     *
     * @param param
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(method = RequestMethod.POST, value = "balancePay/balancePayPoint")
    Result balancePayPoint(@RequestBody BalancePayParam param);

    /**
     * 用户红包余额支付
     * @param param
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(method = RequestMethod.POST, value = "balancePay/memberRedPacketPay")
    Result memberRedPacketPay(@RequestBody BalancePayDataParam param);



    //############################################################################################


    /**
     * 订单余额支付
     *  校验支付密码
     *
     * @param param
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(method = RequestMethod.POST, value = "balancePay/orderPayValidatePwd")
    Result orderPayValidatePwd(@RequestBody BalancePayValidateDataParam param);

    /**
     * 买单余额支付
     *  校验支付密码
     *
     * @param dparam
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(method = RequestMethod.POST, value = "balancePay/billPayValidatePwd")
    Result billPayValidatePwd(@RequestBody BalancePayValidateDataParam dparam);

    /**
     * 余额充值积分
     *  校验支付密码
     *
     * @param param
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(method = RequestMethod.POST, value = "balancePay/balancePayPointValidatePwd")
    Result balancePayPointValidatePwd(@RequestBody BalancePayValidateDataParam param);

    /**
     * 用户红包余额支付
     *  校验支付密码
     *
     * @param param
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(method = RequestMethod.POST, value = "balancePay/memberRedPacketPayValidatePwd")
    Result memberRedPacketPayValidatePwd(@RequestBody BalancePayValidateDataParam param);
}
