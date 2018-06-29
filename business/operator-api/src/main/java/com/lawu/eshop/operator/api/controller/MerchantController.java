package com.lawu.eshop.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.operator.api.service.MerchantService;
import com.lawu.eshop.user.dto.MerchantDTO;
import com.lawu.eshop.user.dto.MerchantDetailDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author meishuquan
 * @date 2017/5/16.
 */
@Api(tags = "merchant")
@RestController
@RequestMapping(value = "merchant/")
public class MerchantController extends BaseController{

    @Autowired
    private MerchantService merchantService;

    @ApiOperation(value = "根据商家账号查询商家信息", notes = "根据商家账号查询商家信息。[1002]（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    //@RequiresPermissions("index:store")
    @RequestMapping(value = "getMerchant/{account}", method = RequestMethod.GET)
    public Result<MerchantDTO> getMerchant(@PathVariable @ApiParam(value = "商家账号") String account) {
        return merchantService.getMerchantByAccount(account);
    }

    @ApiOperation(value = "根据商家ID查询商家信息", notes = "根据商家ID查询商家信息。（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("account:detail")
    @RequestMapping(value = "getMerchantDetail/{id}", method = RequestMethod.GET)
    public Result<MerchantDetailDTO> getMerchantDetail(@PathVariable @ApiParam(value = "会员账号") Long id) {
        return merchantService.getMerchantDetail(id);
    }
}
