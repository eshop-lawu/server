package com.lawu.eshop.agent.api.service;

import com.lawu.eshop.statistics.dto.AgentAreaRechargeQReturnDTO;
import com.lawu.eshop.statistics.param.AgentReportParam;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author yangqh
 * @date 2017/8/15
 */
@FeignClient(value = "statistics-srv")
public interface ReportAreaRechargeService {

	@RequestMapping(value = "reportAreaRecharge/getAreaRechargeList", method = RequestMethod.POST)
	AgentAreaRechargeQReturnDTO getAreaRechargeList(@RequestBody AgentReportParam param);
	
}
