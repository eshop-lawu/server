package com.lawu.eshop.operator.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.operator.api.service.RegionService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * 
 * @author jiangxinjun
 * @date 2017年9月29日
 */
@Api(tags = "region")
@RestController
@RequestMapping("region/")
public class RegionController extends BaseController {

    @Autowired
    private RegionService regionService;

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "地区控件数据来源", notes = "地区控件数据来源（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getRegionSelectorData", method = RequestMethod.GET)
    public Result<JSONObject> getRegionSelectorData() {
        return successGet(regionService.getRegionSelectorData());
    }
}
