package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.statistics.dto.ReportCommonBackDTO;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年7月3日 下午3:38:48
 *
 */
@FeignClient(value = "statistics-srv")
public interface ReportPointConsumeService {

	@RequestMapping(value = "pointConsume/selectReport",method = RequestMethod.GET)
	ReportCommonBackDTO selectReport(@RequestParam("bdate") String bdate,@RequestParam("edate") String edate);

}
