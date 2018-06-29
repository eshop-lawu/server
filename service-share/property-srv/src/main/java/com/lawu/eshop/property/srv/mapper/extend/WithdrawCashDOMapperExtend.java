package com.lawu.eshop.property.srv.mapper.extend;

import java.util.List;

import com.lawu.eshop.property.param.AgentWithdrawCashReportTotalParam;
import com.lawu.eshop.property.param.WithdrawCashReportParam;
import com.lawu.eshop.property.param.WithdrawCashReportTotalParam;
import com.lawu.eshop.property.srv.domain.extend.WithdrawCashDOView;
import com.lawu.eshop.property.srv.domain.extend.WithdrawCashOperDOView;
import com.lawu.eshop.property.srv.domain.extend.WithdrawCashTotalReportDOView;

public interface WithdrawCashDOMapperExtend {

	/**
	 * 后台提现管理，统计成功笔数和金额
	 * @param view
	 * @return
	 */
	WithdrawCashDOView getTotalNum(WithdrawCashDOView view);

	/**
	 * 批量修改提现记录状态
	 * @param paramList
	 */
	void updateBatchWithdrawCashStatus(List<WithdrawCashOperDOView> paramList);

    List<WithdrawCashTotalReportDOView> selectWithdrawCashTotalByDateAndStatus(WithdrawCashReportTotalParam query);

    List<WithdrawCashTotalReportDOView> selectAgentWithdrawCashTotal(AgentWithdrawCashReportTotalParam query);
}