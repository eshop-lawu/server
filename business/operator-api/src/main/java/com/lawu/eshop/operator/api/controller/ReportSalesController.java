package com.lawu.eshop.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.operator.api.service.ReportSalesService;
import com.lawu.eshop.statistics.dto.ReportDataDTO;
import com.lawu.eshop.statistics.param.PlatformTotalSalesQueryParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * 平台总销量服务接口
 * 
 * @author Sunny
 * @date 2017年7月4日
 */
@Api(tags = "reportSales")
@RestController
@RequestMapping(value = "reportSales/")
public class ReportSalesController extends BaseController {

    @Autowired
    private ReportSalesService reportSalesService;

    @SuppressWarnings("unchecked")
	@ApiOperation(value = "查询平台总销量记录", notes = "查询平台总销量记录[]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("reportSales:list")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Result<ReportDataDTO> selectListByMerchant(@ModelAttribute @ApiParam(value = "查询参数") PlatformTotalSalesQueryParam param) {
    	Result<ReportDataDTO> listResult = reportSalesService.list(param);
    	return successGet(listResult);
    }

}
