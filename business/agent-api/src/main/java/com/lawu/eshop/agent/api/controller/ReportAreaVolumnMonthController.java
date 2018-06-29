package com.lawu.eshop.agent.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.eshop.agent.api.service.ReportAreaVolumnMonthService;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.statistics.dto.ReportAreaVolumnMonthDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * @author hongqm
 * @date 2017/8/14
 */
@Api(tags = "reportAreaVolumnMonth")
@RestController
@RequestMapping(value = "reportAreaVolumnMonth/")
public class ReportAreaVolumnMonthController extends BaseController {
	
	@Autowired
	private ReportAreaVolumnMonthService reportAreaVolumnMonthService;
	
	@ApiOperation(value = "根据区域和时间查询总销量月列表", notes = "根据区域和时间查询总销量月列表,(洪钦明)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "selectReportAreaVolumnMonth", method = RequestMethod.GET)
    public Result<List<ReportAreaVolumnMonthDTO>> selectReportAreaVolumnMonth(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
    		@RequestParam("cityId") Integer cityId, @RequestParam("bdate") String bdate, @RequestParam("edate") String edate) {
        return reportAreaVolumnMonthService.selectReportAreaVolumnMonth(cityId, bdate, edate);
    }
}
