package com.lawu.eshop.agent.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.agent.api.service.ReportAreaAdPointDailyService;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.statistics.dto.ReportAreaAdPointMonthDTO;
import com.lawu.eshop.statistics.param.AgentSelectAreaAdPointParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
/**
 * @author hongqm
 * @date 2017/8/14
 */
@Api(tags = "reportAreaAdPointMonth")
@RestController
@RequestMapping(value = "reportAreaAdPointMonth/")
public class ReportAreaAdPointMonthController extends BaseController {

	@Autowired
	private ReportAreaAdPointDailyService reportAreaAdPointDailyService;
	
	@ApiOperation(value = "代理商查询月广告投放统计", notes = "代理商查询月广告投放统计（洪钦明）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "selectReportAreaAdPointMonth", method = RequestMethod.GET)
	public Result<List<ReportAreaAdPointMonthDTO>> selectReportAreaAdPointMonth(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ModelAttribute @ApiParam(value = "查询信息") AgentSelectAreaAdPointParam param) {
		Result<List<ReportAreaAdPointMonthDTO>> rtn = reportAreaAdPointDailyService.selectReportAreaAdPointMonth(param);
	    return rtn;
	}

}
