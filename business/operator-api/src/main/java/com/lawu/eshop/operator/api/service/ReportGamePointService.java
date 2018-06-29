package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.statistics.constants.GameTypeEnum;
import com.lawu.eshop.statistics.dto.ReportDialTotalGamePointDTO;
import com.lawu.eshop.statistics.dto.ReportGamePointDTO;
import com.lawu.eshop.statistics.dto.ReportTotalGamePointDTO;
import com.lawu.eshop.statistics.param.ReportGamePointQuery;
import com.lawu.framework.web.Result;

/**
 * 游戏积分统计
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月26日
 */
@FeignClient(value = "statistics-srv", path = "reportGamePoint/")
public interface ReportGamePointService {
	
	@RequestMapping(value = "selectReport", method = RequestMethod.POST)
	Result<ReportGamePointDTO> selectReport(@RequestBody ReportGamePointQuery query);
	
	
	@RequestMapping(value = "selectTotalReport", method = RequestMethod.POST)
	Result<ReportTotalGamePointDTO> selectTotalReport(@RequestParam("typeEnum") GameTypeEnum typeEnum);

	@RequestMapping(value = "getTotalGamePoint", method = RequestMethod.POST)
	Result<ReportDialTotalGamePointDTO> getTotalGamePoint(@ModelAttribute ReportGamePointQuery param);

}
