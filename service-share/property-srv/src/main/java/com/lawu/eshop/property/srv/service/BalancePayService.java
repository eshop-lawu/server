package com.lawu.eshop.property.srv.service;

import com.lawu.eshop.property.param.BalancePayDataParam;
import com.lawu.eshop.property.param.BalancePayValidateDataParam;

public interface BalancePayService {

	/**
	 * 商品订单余额支付
	 * 
	 * 1、校验接口参数规则 2、判断用户余额是否足够 3、新增会员交易明细 4、减财产余额 *
	 * 
	 * 支持多订单支付
	 * 
	 * @param param
	 * @return
	 */
	int balancePayProductOrder(BalancePayDataParam param);

	/**
	 * 余额支付(买单）
	 * 
	 * 1、校验接口参数规则 2、判断用户余额是否足够 3、新增会员交易明细 4、减会员财产余额 5、新增商家交易明细 6、加商家财产余额
	 * 
	 * @param param
	 * @return
	 */
	int balancePay(BalancePayDataParam param);

	/**
	 * 余额充值积分 //校验余额 //根据系统参数获取充值比例 //减财产余额、加财产积分 //新增余额充值积分交易明细 //新增积分交易明细
	 * 
	 * @param param
	 * @return
	 */
	int balancePayPoint(BalancePayDataParam param);

	/**
	 * 用户红包余额支付
	 * @param param
	 * @return
	 */
	int memberRedPacketPay(BalancePayDataParam param);

	//#####################################################################################################


	/**
	 * 商品订单余额支付
	 *
	 * 1、校验接口参数规则 2、判断用户余额是否足够 3、新增会员交易明细 4、减财产余额 *
	 *
	 * 支持多订单支付
	 *
	 * @param param
	 * @return
	 */
	int balancePayProductOrderValidatePwd(BalancePayValidateDataParam param);

	/**
	 * 余额支付(买单）
	 *
	 * 1、校验接口参数规则 2、判断用户余额是否足够 3、新增会员交易明细 4、减会员财产余额 5、新增商家交易明细 6、加商家财产余额
	 *
	 * @param param
	 * @return
	 */
	int balancePayValidatePwd(BalancePayValidateDataParam param);

	/**
	 * 余额充值积分 //校验余额 //根据系统参数获取充值比例 //减财产余额、加财产积分 //新增余额充值积分交易明细 //新增积分交易明细
	 *
	 * @param param
	 * @return
	 */
	int balancePayPointValidatePwd(BalancePayValidateDataParam param);

	/**
	 * 用户红包余额支付
	 * @param param
	 * @return
	 */
	int memberRedPacketPayValidatePwd(BalancePayValidateDataParam param);
}
