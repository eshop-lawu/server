package com.lawu.eshop.operator.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author Leach
 * @date 2017/4/18
 */
@Api(tags = "test")
@RestController
@RequestMapping(value = "test/")
public class TestController extends BaseController {


    @ApiOperation(value = "授权测试-编辑用户", notes = "编辑用户（孙林青）", httpMethod = "GET")
    @RequiresPermissions("user:edit")
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public Result edit() {
        return successGet();
    }


    @ApiOperation(value = "授权测试-新增用户", notes = "新增用户（孙林青）", httpMethod = "GET")
    @RequiresPermissions("user:add")
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public Result add() {
        return successGet();
    }
}
