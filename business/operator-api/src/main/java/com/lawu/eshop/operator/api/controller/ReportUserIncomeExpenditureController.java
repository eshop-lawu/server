package com.lawu.eshop.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.operator.api.service.ReportUserIncomeExpenditureService;
import com.lawu.eshop.statistics.dto.ReportUserIncomeExpenditureDTO;
import com.lawu.eshop.statistics.param.ReportUserIncomeExpenditureQueryParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * 查询用户支出收入记录
 * 
 * @author Sunny
 * @date 2017年7月4日
 */
@Api(tags = "reportUserIncomeExpenditure")
@RestController
@RequestMapping(value = "reportUserIncomeExpenditure/")
public class ReportUserIncomeExpenditureController extends BaseController {

    @Autowired
    private ReportUserIncomeExpenditureService reportUserIncomeExpenditureService;

    @SuppressWarnings("unchecked")
	@ApiOperation(value = "查询用户支出收入记录", notes = "分页查询用户支出收入记录[]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @PageBody
    @RequiresPermissions("reportUserIncomeExpenditure:page")
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public Result<Page<ReportUserIncomeExpenditureDTO>> selectListByMerchant(@ModelAttribute @ApiParam(value = "查询参数") ReportUserIncomeExpenditureQueryParam param) {
    	Result<Page<ReportUserIncomeExpenditureDTO>> pageResult = reportUserIncomeExpenditureService.page(param);
    	return successGet(pageResult);
    }

}
