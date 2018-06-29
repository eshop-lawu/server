package com.lawu.eshop.agent.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.eshop.agent.api.service.ReportWithdrawCashService;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.statistics.dto.ReportAreaWithdrawDTO;
import com.lawu.eshop.statistics.param.AgentReportParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * @author zhangyong
 * @date 2017/8/15.
 */
@Api(tags = "reportAreaWithdraw")
@RestController
@RequestMapping(value = "reportAreaWithdraw/")
public class ReportWithdrawCashController extends BaseController {

    @Autowired
    private ReportWithdrawCashService reportWithdrawCashService;


    @ApiOperation(value = "提现日统计", notes = "提现日统计,[]（章勇）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "selectAreaWithdrawDailyReport", method = RequestMethod.GET)
    public Result<ReportAreaWithdrawDTO> selectAreaWithdrawDailyReport(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                                       @ModelAttribute AgentReportParam param) {
        Result<ReportAreaWithdrawDTO> result = reportWithdrawCashService.selectAreaWithdrawDailyReport(param);
        return successGet(result.getModel());
    }

    @ApiOperation(value = "提现月统计", notes = "提现月统计,[]（章勇）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "selectAreaWithdrawMonthReport", method = RequestMethod.GET)
    public Result<ReportAreaWithdrawDTO> selectAreaWithdrawMonthReport(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                                       @ModelAttribute AgentReportParam param) {
        Result<ReportAreaWithdrawDTO> result = reportWithdrawCashService.selectAreaWithdrawMonthReport(param);
        return successGet(result.getModel());
    }

}
