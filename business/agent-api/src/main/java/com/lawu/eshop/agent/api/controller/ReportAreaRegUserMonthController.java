package com.lawu.eshop.agent.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.eshop.agent.api.service.ReportAreaUserRegMonthService;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.statistics.dto.AgentUserRegUserListDTO;
import com.lawu.eshop.statistics.param.AgentReportParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * @author zhangyong
 * @date 2017/8/14.
 */
@Api(tags = "regUserMonth")
@RestController
@RequestMapping(value = "regUserMonth/")
public class ReportAreaRegUserMonthController extends BaseController{

    @Autowired
    private ReportAreaUserRegMonthService reportAreaUserRegMonthService;

    @ApiOperation(value = "查询注册用户月统计列表", notes = "查询注册用户月统计列表（章勇）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "getUserRegListMonth", method = RequestMethod.GET)
    public Result<List<AgentUserRegUserListDTO>> getUserRegListMonth(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                                     @ModelAttribute AgentReportParam param) {
        return reportAreaUserRegMonthService.getUserRegListMonth(param);
    }
}
