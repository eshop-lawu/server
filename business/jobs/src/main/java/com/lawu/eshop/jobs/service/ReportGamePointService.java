package com.lawu.eshop.jobs.service;

import java.util.Date;
import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.statistics.param.GamePointReportParam;
import com.lawu.framework.web.Result;

@FeignClient(value= "statistics-srv")
public interface ReportGamePointService {
	
	
	@RequestMapping(value = "reportGamePoint/getDaily", method = RequestMethod.GET)
	Result<Date> getDaily();

	@RequestMapping(value = "reportGamePoint/saveReportGamePointDaily", method = RequestMethod.POST)
	Result saveReportGamePointDaily(@RequestBody List<GamePointReportParam> list);

}
