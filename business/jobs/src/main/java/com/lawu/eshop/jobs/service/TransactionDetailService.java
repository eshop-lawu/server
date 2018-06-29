package com.lawu.eshop.jobs.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.property.dto.TotalSalesDTO;
import com.lawu.eshop.property.dto.UserIncomeExpenditureDatailDTO;
import com.lawu.eshop.property.param.TotalSalesQueryParam;
import com.lawu.eshop.property.param.UserIncomeExpenditureQueryParam;
import com.lawu.framework.web.Result;

/**
 * 交易明细服务
 * 
 * @author Sunny
 * @date 2017年7月4日
 */
@FeignClient(value= "property-srv", path = "transactionDetail/")
public interface TransactionDetailService {

	/**
	 * 查询指定日期的平台销量
	 *
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "totalSales", method = RequestMethod.PUT)
	public Result<TotalSalesDTO> selectTotalSales(@RequestBody TotalSalesQueryParam param);
	
	/**
	 * 查询用户收入和支出
	 *
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "userIncomeExpenditure", method = RequestMethod.PUT)
	Result<UserIncomeExpenditureDatailDTO> selectUserIncomeExpenditure(@RequestBody UserIncomeExpenditureQueryParam param);
	
}
