package com.lawu.eshop.property.srv.service;

import java.util.List;

import com.lawu.eshop.property.param.AgentWithdrawCashReportParam;
import com.lawu.eshop.property.param.CashBackageOperDataParam;
import com.lawu.eshop.property.param.CashBackageQueryDataParam;
import com.lawu.eshop.property.param.CashBackageQueryDetailParam;
import com.lawu.eshop.property.param.CashBackageQuerySumParam;
import com.lawu.eshop.property.param.WithdrawCashReportParam;
import com.lawu.eshop.property.srv.bo.WithdrawCashBackageQueryBO;
import com.lawu.eshop.property.srv.bo.WithdrawCashBackageQuerySumBO;
import com.lawu.eshop.property.srv.bo.WithdrawCashReportBO;
import com.lawu.eshop.property.srv.bo.WithdrawCashTotalReportBO;
import com.lawu.eshop.property.srv.exception.ThirdCashTransferException;
import com.lawu.framework.core.page.Page;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author Yangqh
 * @date 2017年4月5日 下午2:48:51
 */
public interface CashManageBackageService {

    /**
     * 运营后台提现管理列表
     *
     * @param param
     * @return
     */
    Page<WithdrawCashBackageQueryBO> findCashInfo(CashBackageQueryDataParam param);

    /**
     * 运营平台提现管理统计成功总笔数和成功总金额
     *
     * @param param
     * @return
     */
    WithdrawCashBackageQuerySumBO getTotalNum(CashBackageQuerySumParam param);

    /**
     * 运营平台提现详情
     *
     * @param param
     * @return
     */
    Page<WithdrawCashBackageQueryBO> findCashInfoDetail(CashBackageQueryDetailParam param);

    /**
     * 提现后台处理
     *
     * @param param
     * @return
     */
    int updateWithdrawCash(CashBackageOperDataParam param);

    /**
     * 查询某天平台用户商家提现成功的记录
     *
     * @param param
     * @return
     * @author yangqh
     * @date 2017年6月28日 下午4:34:03
     */
    List<WithdrawCashReportBO> selectWithdrawCashListByDateAndStatus(WithdrawCashReportParam param);

    /**
     * 查询某天平台用户商家提现成功的总金额
     *
     * @param param
     * @return
     * @author yangqh
     * @date 2017年11月14日
     */
    WithdrawCashTotalReportBO selectWithdrawCashTotalByDateAndStatus(WithdrawCashReportParam param);

    List<WithdrawCashReportBO> selectAgentWithdrawCashList(AgentWithdrawCashReportParam param);

    WithdrawCashTotalReportBO selectAgentWithdrawCashTotal(AgentWithdrawCashReportParam param);

    int saveThirdplatformLog(String userNum, ThirdCashTransferException tte);
}
