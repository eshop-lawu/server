package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.statistics.dto.ReportCommonEarningsDTO;
import com.lawu.framework.web.Result;

/**
 * 
 * @author zhangrc
 * @date 2017/07/05
 *
 */
@FeignClient(value = "statistics-srv")
public interface ReportEarningService {
	
	@RequestMapping(value = "reportEarning/selectReport",method = RequestMethod.GET)
	Result<ReportCommonEarningsDTO> selectReport(@RequestParam("bdate") String bdate,@RequestParam("edate") String edate);


}
