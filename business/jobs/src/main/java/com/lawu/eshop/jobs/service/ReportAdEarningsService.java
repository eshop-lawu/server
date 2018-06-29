package com.lawu.eshop.jobs.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.statistics.dto.ReportAdEarningsEndDTO;
import com.lawu.eshop.statistics.param.ReportAdEarningsParam;
import com.lawu.framework.web.Result;

/**
 * 广告收益
 * @author zhangrc
 * @date 2017/06/29
 *
 */
@FeignClient(value= "statistics-srv")
public interface ReportAdEarningsService {
	
	/**
	 * 统计保存
	 * @param reportAdEarningsParam
	 * @return
	 */
	@RequestMapping(value = "reportAdEarnings/saveReportAdEarnings", method = RequestMethod.POST)
	public Result saveReportAdEarnings(@RequestBody ReportAdEarningsParam reportAdEarningsParam);
	
	/**
	 * 已结束的广告
	 * @return
	 */
	@RequestMapping(value = "reportAdEarnings/getReportAdEarningsIds", method = RequestMethod.GET)
	public Result<List<ReportAdEarningsEndDTO>> getReportAdEarningsIds();

	

}
