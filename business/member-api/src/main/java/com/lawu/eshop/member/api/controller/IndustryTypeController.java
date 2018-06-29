package com.lawu.eshop.member.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.autotest.client.AutoTesting;
import com.lawu.eshop.mall.dto.IndustryTypeDTO;
import com.lawu.eshop.member.api.service.IndustryTypeService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author meishuquan
 * @date 2017/4/5.
 */
@Api(tags = "industryType")
@RestController
@RequestMapping(value = "industryType/")
public class IndustryTypeController extends BaseController {

    @Autowired
    private IndustryTypeService industryTypeService;

    @AutoTesting
    @Audit(date = "2017-04-12", reviewer = "孙林青")
    @ApiOperation(value = "查询行业", notes = "查询所有行业。 [1100] (梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "listIndustryType", method = RequestMethod.GET)
    public Result<List<IndustryTypeDTO>> listIndustryType() {
        return industryTypeService.listIndustryType();
    }

    @AutoTesting
    @Audit(date = "2017-05-11", reviewer = "孙林青")
    @ApiOperation(value = "查询父行业下的所有行业", notes = "查询父行业下的所有行业。 [1100] (梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "listIndustryType/{parentId}", method = RequestMethod.GET)
    public Result<List<IndustryTypeDTO>> listIndustryType(@PathVariable @ApiParam(required = true, value = "父行业ID") Short parentId) {
        return industryTypeService.listIndustryTypeByParentId(parentId);
    }

}
