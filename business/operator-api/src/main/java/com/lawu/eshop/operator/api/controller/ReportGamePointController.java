package com.lawu.eshop.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.operator.api.service.ReportGamePointService;
import com.lawu.eshop.statistics.constants.GameTypeEnum;
import com.lawu.eshop.statistics.dto.ReportDialTotalGamePointDTO;
import com.lawu.eshop.statistics.dto.ReportGamePointDTO;
import com.lawu.eshop.statistics.dto.ReportTotalGamePointDTO;
import com.lawu.eshop.statistics.param.ReportGamePointQuery;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月26日
 */
@Api(tags = "reportGamePoint")
@RestController
@RequestMapping(value = "reportGamePoint/")
public class ReportGamePointController extends BaseController{
	
	@Autowired
	private ReportGamePointService reportGamePointService;
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "游戏积分日统计", notes = "游戏积分日统计,[]（张荣成）", httpMethod = "POST")
	@RequiresPermissions("report:gamePoint")
	@RequestMapping(value = "selectReport", method = RequestMethod.POST)
	public Result<ReportGamePointDTO> selectReport(@RequestBody @ApiParam ReportGamePointQuery query) {
		return successGet(reportGamePointService.selectReport(query));
	}
	
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "游戏总积分统计", notes = "游戏总积分统计,[]（张荣成）", httpMethod = "POST")
	@RequestMapping(value = "selectTotalReport", method = RequestMethod.POST)
	public Result<ReportTotalGamePointDTO> selectTotalReport(@RequestParam @ApiParam GameTypeEnum typeEnum) {
		return successGet(reportGamePointService.selectTotalReport(typeEnum));
	}

	@ApiOperation(value = "游戏积分总收入", notes = "游戏积分日统计,[]（张荣成）", httpMethod = "GET")
	@RequiresAuthentication
	@RequestMapping(value = "getTotalGamePoint", method = RequestMethod.GET)
	public Result<ReportDialTotalGamePointDTO> getTotalGamePoint(@ModelAttribute ReportGamePointQuery param) {
		Result<ReportDialTotalGamePointDTO> pointDTO = reportGamePointService.getTotalGamePoint(param);
		return successGet(pointDTO.getModel());
	}
}
