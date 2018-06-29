package com.lawu.eshop.jobs.service;

import java.util.List;

import com.lawu.eshop.property.dto.ReportAreaRechargeDailyDTO;
import com.lawu.eshop.property.param.AgentReportRechargeQueryParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.property.dto.RechargeReportDTO;
import com.lawu.eshop.property.param.RechargeReportParam;
import com.lawu.framework.web.Result;

@FeignClient(value= "property-srv")
public interface PropertyRechargeService {

	/**
	 * 获取某天充值余额成功的记录
	 * @param param
	 * @return
	 * @author yangqh
	 * @date 2017年6月29日 下午5:17:37
	 */
	@RequestMapping(method = RequestMethod.POST, value = "recharge/selectWithdrawCashListByDateAndStatus")
	Result<RechargeReportDTO> selectWithdrawCashListByDateAndStatus(@RequestBody RechargeReportParam param);

	/**
	 * 代理商区域统计，获取某天第三方充值记录，分组查询
	 * @param param
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "recharge/selectAgentAreaReportRechargeListByDate")
	Result<List<ReportAreaRechargeDailyDTO>> selectAgentAreaReportRechargeListByDate(@RequestBody AgentReportRechargeQueryParam param);
}
