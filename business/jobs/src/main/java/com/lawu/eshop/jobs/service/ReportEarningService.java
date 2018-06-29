package com.lawu.eshop.jobs.service;

import java.util.Date;
import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.statistics.dto.ReportEarningsDailyDTO;
import com.lawu.eshop.statistics.param.ReportEarningParam;
import com.lawu.framework.web.Result;

@FeignClient(value= "statistics-srv")
public interface ReportEarningService {

	@RequestMapping(value = "reportEarning/saveDaily", method = RequestMethod.POST)
	Result saveDaily(@RequestBody  ReportEarningParam param);

	
	@RequestMapping(value = "reportEarning/saveMonth", method = RequestMethod.POST)
	Result saveMonth(@RequestBody  ReportEarningParam param);
	
	@RequestMapping(value = "reportEarning/getDailyList", method = RequestMethod.GET)
	Result<List<ReportEarningsDailyDTO>> getDailyList(@RequestParam("reportDate") String reportDate);
	
	@RequestMapping(value = "reportEarning/getDaily", method = RequestMethod.GET)
	public Date getDaily();
	
	@RequestMapping(value = "reportEarning/getMonth", method = RequestMethod.GET)
	public Date getMonth();
}
