package com.lawu.eshop.jobs.service;

import java.util.List;

import com.lawu.eshop.property.dto.WithdrawCashTotalReportDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.property.dto.WithdrawCashReportDTO;
import com.lawu.eshop.property.param.AgentWithdrawCashReportParam;
import com.lawu.eshop.property.param.WithdrawCashReportParam;
import com.lawu.framework.web.Result;

@FeignClient(value= "property-srv")
public interface PropertyWithdrawCashService {

	/**
	 * 获取某天提现成功的记录
	 * @param param
	 * @return
	 * @author yangqh
	 * @date 2017年6月28日 下午4:17:17
	 */
	@RequestMapping(method = RequestMethod.POST, value = "cashBackage/selectWithdrawCashListByDateAndStatus")
	Result<List<WithdrawCashReportDTO>> selectWithdrawCashListByDateAndStatus(@RequestBody WithdrawCashReportParam param);

	/**
	 * 获取某天提现成功的金额
	 * @param param
	 * @return
	 * @author yangqh
	 * @date 2017年11月14日
	 */
	@RequestMapping(method = RequestMethod.POST, value = "cashBackage/selectWithdrawCashTotalByDateAndStatus")
	Result<WithdrawCashTotalReportDTO> selectWithdrawCashTotalByDateAndStatus(@RequestBody WithdrawCashReportParam param);

	/**
	 * 获取某天某个地区提现成功的记录
	 * @param param
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "cashBackage/selectAgentWithdrawCashList")
    Result<List<WithdrawCashReportDTO>> selectAgentWithdrawCashList(@RequestBody AgentWithdrawCashReportParam param);

	/**
	 * 获取某天某个地区提现成功的金额
	 * @param param
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "cashBackage/selectAgentWithdrawCashTotal")
	Result<WithdrawCashTotalReportDTO> selectAgentWithdrawCashTotal(@RequestBody AgentWithdrawCashReportParam param);
}
