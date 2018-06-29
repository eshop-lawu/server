package com.lawu.eshop.merchant.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.activity.dto.MerchantRichPowerTaskDTO;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.merchant.api.service.RichPowerTaskRecordService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * @author zhangyong
 * @date 2018/6/8.
 */
@Api(tags = "richMerchantPowerTaskRecord")
@RestController
@RequestMapping("richMerchantPowerTaskRecord/")
public class RichMerchantPowerTaskRecordController extends BaseController {

    @Autowired
    private RichPowerTaskRecordService richPowerTaskRecordService;

    @ApiOperation(value = "动力任务查询列表", notes = "动力任务查询列表。[1000] (章勇)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "getPowerTasks", method = RequestMethod.GET)
    public Result<MerchantRichPowerTaskDTO> getPowerTasks(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        return richPowerTaskRecordService.getPowerTasks(userNum);
    }
}
