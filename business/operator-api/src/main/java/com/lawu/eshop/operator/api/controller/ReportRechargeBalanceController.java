package com.lawu.eshop.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.operator.api.service.ReportRechargeBalanceService;
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
 * @date 2017年7月3日 下午3:17:53
 *
 */
@Api(tags = "reportWithdrawCash")
@RestController
@RequestMapping(value = "reportRechargeBalance/")
public class ReportRechargeBalanceController extends BaseController {

	@Autowired
	private ReportRechargeBalanceService reportRechargeBalanceService;

	@ApiOperation(value = "余额充值日/月统计", notes = "余额充值日/月统计,[]（杨清华）", httpMethod = "GET")
	@RequestMapping(value = "selectReport", method = RequestMethod.GET)
	@RequiresPermissions("report:rechargeBalance")
	public Result<ReportCommonBackDTO> selectReport(@RequestParam("bdate") String bdate,@RequestParam("edate") String edate) {
		return successCreated(reportRechargeBalanceService.selectReport(bdate,edate));
	}

	
}
