package com.lawu.eshop.jobs.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.statistics.param.ReportUserIncomeExpenditureSaveParam;
import com.lawu.eshop.statistics.param.ReportUserIncomeExpenditureSaveWrapperParam;
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
	 * 保存用户支出收入记录
	 * 
	 * @param param
	 * @author Sunny
	 * @date 2017年7月3日
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST)
	Result save(@RequestBody ReportUserIncomeExpenditureSaveParam param);

	/**
	 * 批量保存用户支出收入记录
	 * 
	 * @param param
	 * @author Sunny
	 * @date 2017年7月3日
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "batch", method = RequestMethod.POST)
	Result batchSave(@RequestBody ReportUserIncomeExpenditureSaveWrapperParam param);
}
