package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.property.dto.WithdrawCashBackageQueryDTO;
import com.lawu.eshop.property.dto.WithdrawCashBackageQuerySumDTO;
import com.lawu.eshop.property.param.CashBackageOperDataParam;
import com.lawu.eshop.property.param.CashBackageQueryDataParam;
import com.lawu.eshop.property.param.CashBackageQueryDetailParam;
import com.lawu.eshop.property.param.CashBackageQuerySumParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年4月11日 上午11:46:48
 *
 */
@FeignClient(value = "property-srv")
public interface CashManageBackageService {

	/**
	 * 运营后台提现管理列表
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "cashBackage/findCashInfo",method = RequestMethod.POST)
	Result<Page<WithdrawCashBackageQueryDTO>> findCashInfo(@RequestBody CashBackageQueryDataParam param);

	/**
	 * 运营平台提现管理统计成功总笔数和成功总金额
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "cashBackage/getTotalNum",method = RequestMethod.POST)
	Result<WithdrawCashBackageQuerySumDTO> getTotalNum(@RequestBody CashBackageQuerySumParam param);

	/**
	 * 提现详情
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "cashBackage/findCashInfoDetail",method = RequestMethod.POST)
	Result<Page<WithdrawCashBackageQueryDTO>> findCashInfoDetail(@RequestBody CashBackageQueryDetailParam param);

	/**
	 * 提现处理
	 * @param dparam
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "cashBackage/updateWithdrawCash",method = RequestMethod.POST)
	Result updateWithdrawCash(@RequestBody CashBackageOperDataParam dparam);
	
	

}
