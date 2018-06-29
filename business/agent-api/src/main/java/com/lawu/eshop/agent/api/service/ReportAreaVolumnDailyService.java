package com.lawu.eshop.agent.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.statistics.dto.ReportAreaVolumeDailyDTO;
import com.lawu.framework.web.Result;

/**
 * @author hongqm
 * @date 2017/8/14
 */
@FeignClient(value = "statistics-srv")
public interface ReportAreaVolumnDailyService {

	
	@RequestMapping(value = "reportAreaVolumnDaily/selectReportAreaVolumnDaily", method = RequestMethod.GET)
	Result<List<ReportAreaVolumeDailyDTO>> selectReportAreaVolumnDaily(@RequestParam("cityId") Integer cityId,@RequestParam("bdate") String bdate,@RequestParam("edate") String edate);
	
}
