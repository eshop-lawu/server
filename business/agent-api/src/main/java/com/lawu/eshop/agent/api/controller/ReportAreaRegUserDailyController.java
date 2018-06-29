package com.lawu.eshop.agent.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.eshop.agent.api.service.ReportAreaUserRegDailyService;
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
 * @date 2017/8/11.
 */
@Api(tags = "regUserDaily")
@RestController
@RequestMapping(value = "regUserDaily/")
public class ReportAreaRegUserDailyController extends BaseController {

    @Autowired
    private ReportAreaUserRegDailyService reportAreaUserRegDailyService;

    @ApiOperation(value = "查询注册用户日统计列表", notes = "查询注册用户日统计列表（章勇）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "getUserRegListDaily", method = RequestMethod.GET)
    public Result<List<AgentUserRegUserListDTO>> getUserRegListDaily(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                                     @ModelAttribute AgentReportParam param) {
        return reportAreaUserRegDailyService.getUserRegListDaily(param);
    }
}
