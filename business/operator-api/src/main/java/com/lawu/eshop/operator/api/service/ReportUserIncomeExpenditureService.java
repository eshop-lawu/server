package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.statistics.dto.ReportUserIncomeExpenditureDTO;
import com.lawu.eshop.statistics.param.ReportUserIncomeExpenditureQueryParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * 用户支出收入接口服务
 * 
 * @author Sunny
 * @date 2017年7月3日
 */
@FeignClient(value = "statistics-srv", path = "reportUserIncomeExpenditure/")
public interface ReportUserIncomeExpenditureService {

	/**
	 * 查询用户支出收入记录
	 * 
	 * @param param
	 * @return
	 * @author Sunny
	 * @date 2017年7月3日
	 */
	@RequestMapping(value = "page", method = RequestMethod.PUT)
	Result<Page<ReportUserIncomeExpenditureDTO>> page(@RequestBody ReportUserIncomeExpenditureQueryParam param);
	
}
