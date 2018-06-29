package com.lawu.eshop.jobs.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.statistics.dto.ReportAreaAdPointDailyDTO;
import com.lawu.eshop.statistics.dto.ReportAreaAdPointMonthDTO;
import com.lawu.eshop.statistics.dto.ReportAreaPointConsumeDailyInMonthDTO;
import com.lawu.eshop.statistics.dto.ReportAreaVolumnDailyInMonthDTO;
import com.lawu.eshop.statistics.param.ReportAreaAdPointDailyParams;
import com.lawu.eshop.statistics.param.ReportAreaAdPointMonthParams;
import com.lawu.eshop.statistics.param.ReportAreaPointConsumeDailyParam;
import com.lawu.eshop.statistics.param.ReportAreaPointConsumeMonthParam;
import com.lawu.eshop.statistics.param.ReportAreaVolumnDailyParam;
import com.lawu.eshop.statistics.param.ReportAreaVolumnMonthParam;
import com.lawu.framework.web.Result;


@FeignClient(value= "statistics-srv")
public interface StatisticsSrvService {

	
	@RequestMapping(method = RequestMethod.GET, value = "reportAreaAdPointDaily/selectReportAreaAdPointDaily/{areaId}")
	Result<List<ReportAreaAdPointDailyDTO>> selectReportAreaAdPointDaily(@PathVariable("areaId") Integer areaId, @RequestParam("date") String date);	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "reportAreaAdPointDaily/insertReportAreaAdPointDaily", method = RequestMethod.POST)
	Result insertReportAreaAdPointDaily(@RequestBody ReportAreaAdPointDailyParams param);
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "reportAreaAdPointDaily/deleteReportAreaAdPointDaily", method = RequestMethod.POST)
	Result deleteReportAreaAdPointDaily(@RequestParam(value = "id") Long id);
	
	@RequestMapping(value = "reportAreaAdPointDaily/selectReportAreaAdPointDailyInMonth", method = RequestMethod.GET)
	Result<List<ReportAreaAdPointMonthDTO>> selectReportAreaAdPointDailyInMonth(@RequestParam(value = "bdate") String bdate, 
			@RequestParam(value = "edate") String edate);
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "reportAreaAdPointMonth/insertReportAreaAdPointMonth", method = RequestMethod.POST)
	Result insertReportAreaAdPointMonth(@RequestBody ReportAreaAdPointMonthParams param);
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "reportAreaVolumnDaily/insertReportAreaVolumnDaily", method = RequestMethod.POST)
	Result insertReportAreaVolumnDaily(@RequestBody ReportAreaVolumnDailyParam param);
	
	@RequestMapping(value = "reportAreaVolumnDaily/selectReportAreaVolumeDailyInMonth", method = RequestMethod.GET)
    Result<List<ReportAreaVolumnDailyInMonthDTO>> selectReportAreaVolumeDailyInMonth(@RequestParam("bdate") String bdate, @RequestParam("edate") String edate);
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "reportAreaVolumnMonth/insertReportAreaVolumnMonth", method = RequestMethod.POST)
    Result insertReportAreaVolumnMonth(@RequestBody ReportAreaVolumnMonthParam param);
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "reportAreaPointConsumeDaily/insertReportAreaPointConsumeDaily", method = RequestMethod.POST)
	Result insertReportAreaPointConsumeDaily(@RequestBody ReportAreaPointConsumeDailyParam param);
	
	@RequestMapping(value = "reportAreaPointConsumeDaily/selectReportAreaPointConsumeDailyInMonth", method = RequestMethod.GET)
	Result<List<ReportAreaPointConsumeDailyInMonthDTO>> selectReportAreaPointConsumeDailyInMonth(@RequestParam("bdate")String bdate, @RequestParam("edate")String edate);
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "reportAreaPointConsumeMonth/saveReportAreaPointConsumeMonth", method = RequestMethod.POST)
	Result saveReportAreaPointConsumeMonth(@RequestBody ReportAreaPointConsumeMonthParam param);

	@RequestMapping(value = "reportAreaPointConsumeMonth/executeCollectReportAreaPointConsumeMonth", method = RequestMethod.GET)
	Result executeCollectReportAreaPointConsumeMonth(@RequestParam("pageSize") Integer pageSize);
}
