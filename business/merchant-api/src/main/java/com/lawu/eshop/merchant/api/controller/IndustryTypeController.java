package com.lawu.eshop.merchant.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.autotest.client.AutoTesting;
import com.lawu.eshop.mall.constants.MerchantIndustryTypeEnum;
import com.lawu.eshop.mall.dto.IndustryTypeDTO;
import com.lawu.eshop.merchant.api.service.IndustryTypeService;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author zhangyong
 * @date 2017/5/16.
 */
@Api(tags = "industryType")
@RestController
@RequestMapping(value = "industryType/")
public class IndustryTypeController {

    @Autowired
    private IndustryTypeService industryTypeService;

    @AutoTesting
    @Audit(date = "2017-05-16", reviewer = "孙林青")
    @ApiOperation(value = "查询行业(嵌套分组)", notes = "查询所有行业(嵌套分组)。 [1100] (章勇)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getIndustryListWithGroup", method = RequestMethod.GET)
    public Result<List<IndustryTypeDTO>> getIndustryListWithGroup() {
        return industryTypeService.listIndustryType();
    }

    @AutoTesting
    @Audit(date = "2017-05-16", reviewer = "孙林青")
    @ApiOperation(value = "查询父行业下的所有行业", notes = "查询父行业下的所有行业。 [1100] (章勇)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "listIndustryType/{parentId}", method = RequestMethod.GET)
    public Result<List<IndustryTypeDTO>> listIndustryType(@PathVariable @ApiParam(required = true, value = "父行业ID") Short parentId) {
        return industryTypeService.listIndustryTypeByParentId(parentId);
    }

    @AutoTesting
    @Audit(date = "2017-05-17", reviewer = "孙林青")
    @ApiOperation(value = "查询所有行业（不区分级别）", notes = "查询所有行业（不区分级别）。 [1100] (章勇)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getAllIndustryList", method = RequestMethod.GET)
    public  Result<List<IndustryTypeDTO>> getAllIndustryList() {
        return industryTypeService.getAllIndustryList();
    }

    @AutoTesting
    @Audit(date = "2017-08-18", reviewer = "李洪军")
    @ApiOperation(value = "根据行业类型查询行业列表", notes = "根据行业类型查询行业列表。(梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "listIndustryTypeByType", method = RequestMethod.GET)
    public Result<List<IndustryTypeDTO>> listIndustryTypeByType(@RequestParam @ApiParam(required = true, value = "商家行业列表") MerchantIndustryTypeEnum industryTypeEnum) {
        return industryTypeService.listIndustryTypeByType(industryTypeEnum);
    }
}
