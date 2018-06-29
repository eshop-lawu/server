package com.lawu.eshop.jobs.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.statistics.param.PlatformTotalSalesSaveParam;
import com.lawu.framework.web.Result;

/**
 * 平台总销量接口服务
 * 
 * @author Sunny
 * @date 2017年7月3日
 */
@FeignClient(value = "statistics-srv", path = "reportSales/")
public interface ReportSalesService {

	/**
	 * 保存平台总销量记录
	 * 
	 * @param param
	 * @author Sunny
	 * @date 2017年7月3日
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	Result save(@RequestBody PlatformTotalSalesSaveParam param);

}
