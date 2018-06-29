package com.lawu.eshop.operator.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.operator.api.service.ReportEarningService;
import com.lawu.eshop.statistics.dto.ReportCommonEarningsDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author zhangrc
 * @date 2017年7月5日 下午13:17:53
 *
 */
@Api(tags = "reportEarnings")
@RestController
@RequestMapping(value = "reportEarnings/")
public class ReportEarningsController extends BaseController {

	@Autowired
	private ReportEarningService reportEarningService;

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "广告收益日/月统计", notes = "广告收益日/月统计,[]（张荣成）", httpMethod = "GET")
	@RequestMapping(value = "selectReport", method = RequestMethod.GET)
	public Result<ReportCommonEarningsDTO> selectReport(@RequestParam("bdate") String bdate,@RequestParam("edate") String edate) {
		return successCreated(reportEarningService.selectReport(bdate,edate));
	}

	
}
