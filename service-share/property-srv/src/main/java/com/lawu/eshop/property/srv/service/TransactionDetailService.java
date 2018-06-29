package com.lawu.eshop.property.srv.service;

import java.util.List;

import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.constants.MerchantTransactionTypeEnum;
import com.lawu.eshop.property.constants.PayTypeEnum;
import com.lawu.eshop.property.param.*;
import com.lawu.eshop.property.param.foreign.TransactionDetailMonthlyBillOfMemberForeignParam;
import com.lawu.eshop.property.param.foreign.TransactionDetailMonthlyBillOfMerchantForeignParam;
import com.lawu.eshop.property.param.foreign.TransactionDetailQueryForMemberForeignParam;
import com.lawu.eshop.property.param.foreign.TransactionDetailQueryForMerchantForeignParam;
import com.lawu.eshop.property.srv.bo.IncomeMsgBO;
import com.lawu.eshop.property.srv.bo.MonthlyBillBO;
import com.lawu.eshop.property.srv.bo.TotalSalesBO;
import com.lawu.eshop.property.srv.bo.TotalSalesGroupByAreaBO;
import com.lawu.eshop.property.srv.bo.TransactionDetailBO;
import com.lawu.eshop.property.srv.bo.TransactionDetailH5InfoBO;
import com.lawu.eshop.property.srv.bo.UserIncomeExpenditureBO;
import com.lawu.framework.core.page.Page;

/**
 * 交易明细服务接口
 *
 * @author Sunny
 * @date 2017/3/29
 */
public interface TransactionDetailService {

	/**
	 * 根据用户编号、查询参数分页查询交易明细  - 商家
	 * 
	 * @param userNum 用户编号
	 * @param param 查询参数
	 * @return 
	 */
	Page<TransactionDetailBO> findPageByUserNumForMerchant(String userNum, TransactionDetailQueryForMerchantParam param);
	
	/**
	 * 根据用户编号、查询参数分页查询交易明细 - 用户
	 * 
	 * @param userNum 用户编号
	 * @param param 查询参数
	 * @return 
	 */
	@Deprecated
	Page<TransactionDetailBO> findPageByUserNumForMember(String userNum, TransactionDetailQueryForMemberParam param);
	
	/**
	 * 保存交易记录表
	 * @param param
	 * @return
	 */
	int save(TransactionDetailSaveDataParam param);

	/**
	 * 根据用户编号、第三方交易号校验用户是否存在交易记录，存在表示已支付
	 * @param param
	 * @return
	 * @author yangqh
	 * @date 2017年5月9日 下午4:25:02
	 */
	boolean verifyOrderIsPaySuccess(NotifyCallBackParam param);

	/**
	 * 查询运营后台充值记录
	 *
	 * @param param
	 * @return
	 */
	Page<TransactionDetailBO> getBackageRechargePageList(TransactionDetailQueryForBackageParam param);
	
	/**
	 * 查询平台销售金额
	 *
	 * @param param
	 * @return
	 */
	List<TotalSalesBO> selectTotalSales(TotalSalesQueryParam param);
	
	
	/**
	 * 查询平台销售金额groupby area
	 *
	 * @param param
	 * @return
	 */
	List<TotalSalesGroupByAreaBO> selectTotalSalesGroupByArea(TotalSalesQueryParam param);
	
	/**
	 * 查询用户收入和支出金额
	 *
	 * @param param
	 * @return
	 */
	List<UserIncomeExpenditureBO> selectUserIncomeExpenditure(UserIncomeExpenditureQueryParam param);

    List<IncomeMsgBO> getIncomeMsgDataList(String begin, String end,int offset,int pageSize);

	/**
	 * 余额支付校验是否已处理
	 * @param param
	 * @return
	 */
	boolean verifyByUserNumAndTransactionTypeAndBizId(BalancePayDataParam param);
	boolean verifyByUserNumAndTransactionTypeAndBizId(BalancePayValidateDataParam param);

	/**
	 * 余额支付购物订单校验是否已处理
	 * @param param
	 * @return
	 */
	boolean verifyOrderByUserNumAndTransactionType(BalancePayValidateDataParam param);
	boolean verifyOrderByUserNumAndTransactionType(BalancePayDataParam param);

	public String packageTitle(MemberTransactionTypeEnum memberTransactionTypeEnum, MerchantTransactionTypeEnum merchantTransactionTypeEnum, String title);
	
    /**
     * 根据会员编号和查询参数分页查询交易明细
     * 
     * @param userNum 会员编号
     * @param param 查询参数
     * @return
     * @author jiangxinjun
     * @date 2017年10月20日
     */
    Page<TransactionDetailBO> page(String userNum, TransactionDetailQueryForMemberForeignParam param);
    
    /**
     * 根据会员编号和查询参数月结账单
     * 
     * @param userNum 会员编号
     * @param param 查询参数
     * @return
     * @author jiangxinjun
     * @date 2017年10月20日
     */
    MonthlyBillBO monthlyBill(String userNum, TransactionDetailMonthlyBillOfMemberForeignParam param);
    
    /**
     * 根据会员编号和查询参数分页查询交易明细
     * 
     * @param userNum 会员编号
     * @param param 查询参数
     * @return
     * @author jiangxinjun
     * @date 2017年10月20日
     */
    Page<TransactionDetailBO> page(String userNum, TransactionDetailQueryForMerchantForeignParam param);
    
    /**
     * 根据会员编号和查询参数月结账单
     * 
     * @param userNum 会员编号
     * @param param 查询参数
     * @return
     * @author jiangxinjun
     * @date 2017年10月20日
     */
    MonthlyBillBO monthlyBill(String userNum, TransactionDetailMonthlyBillOfMerchantForeignParam param);

	/**
	 * 交易详情
	 * @param id
	 * @return
	 */
	TransactionDetailH5InfoBO getById(Long id);

	/**
	 * 第三方多次支付校验幂等
	 * @param param
	 * @return
	 */
    boolean verifyThirdPayRepeat(NotifyCallBackParam param);

	/**
	 * 根据第三方交易号查询金额
	 * @param tradeNo
	 * @return
	 */
	String getAmountByThirdNumber(String tradeNo);

    List<IncomeMsgBO> getIncomeMsgTotalDataList(String begin, String end);

    int summaryIncome(List<IncomeMsgBO> bos);

	/**
	 * 收益计算时统计播报
	 * @param countIncomeBroadcastParam
	 * @return
	 */
	int countIncomeBroadcast(CountIncomeBroadcastParam countIncomeBroadcastParam);
}
