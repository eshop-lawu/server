package com.lawu.eshop.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.operator.api.service.ReportWithdrawCashService;
import com.lawu.eshop.statistics.dto.ReportCommonBackDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年6月30日 下午5:20:16
 *
 */
@Api(tags = "reportWithdrawCash")
@RestController
@RequestMapping(value = "reportWithdrawCash/")
public class ReportWithdrawCashController extends BaseController {

	@Autowired
	private ReportWithdrawCashService reportWithdrawCashService;

	@ApiOperation(value = "提现日/月统计", notes = "提现日/月统计,[]（杨清华）", httpMethod = "GET")
	@RequestMapping(value = "selectReport", method = RequestMethod.GET)
	@RequiresPermissions("report:withdraw")
	public Result<ReportCommonBackDTO> selectReport(@RequestParam("bdate") String bdate,@RequestParam("edate") String edate) {
		
		return successCreated(reportWithdrawCashService.selectReport(bdate,edate));
	}

	
}
