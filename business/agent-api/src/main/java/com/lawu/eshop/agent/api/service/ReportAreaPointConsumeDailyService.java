package com.lawu.eshop.agent.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.statistics.dto.ReportAreaPointConsumeDailyDTO;
import com.lawu.framework.web.Result;


@FeignClient(value = "statistics-srv")
public interface ReportAreaPointConsumeDailyService {
	
	/**
	 * 区域积分消费日查询
	 * @param cityId
	 * @param bdate
	 * @param edate
	 * @return
	 */
	@RequestMapping(value = "reportAreaPointConsumeDaily/getReportAreaPointConsumeDaily", method = RequestMethod.GET)
	Result<List<ReportAreaPointConsumeDailyDTO>> getReportAreaPointConsumeDaily(@RequestParam("cityId")Integer cityId, @RequestParam("bdate")String bdate, @RequestParam("edate")String edate);
	
}
