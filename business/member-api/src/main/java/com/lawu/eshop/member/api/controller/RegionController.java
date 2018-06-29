package com.lawu.eshop.member.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.autotest.client.AutoTesting;
import com.lawu.eshop.mall.dto.RegionDTO;
import com.lawu.eshop.mall.dto.RegionPathDTO;
import com.lawu.eshop.mall.dto.RegionProvinceDTO;
import com.lawu.eshop.member.api.service.RegionService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * @author zhangyong
 * @date 2017/4/10.
 */
@Api(tags = "region")
@RestController
@RequestMapping("region/")
public class RegionController extends BaseController {

    @Autowired
    private RegionService regionService;

    @AutoTesting
    @Audit(date = "2017-04-12", reviewer = "孙林青")
    @ApiOperation(value = "城市列表", notes = "城市列表 [1004,1000] 章勇", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "region/getRegionList", method = RequestMethod.GET)
    public Result<List<RegionDTO>> getRegionList() {
        Result<List<RegionDTO>> listResult = regionService.getRegionList();
        return listResult;
    }

    @AutoTesting
    @Audit(date = "2017-10-20", reviewer = "杨清华")
    @SuppressWarnings("unchecked")
	@ApiOperation(value = "地区列表", notes = "查询所有地区，分组 [1004,1000](蒋鑫俊)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "group", method = RequestMethod.GET)
    public Result<List<RegionProvinceDTO>> group() {
        Result<List<RegionProvinceDTO>> listResult = regionService.group();
        return successGet(listResult);
    }

    @AutoTesting
    @Audit(date = "2017-10-20", reviewer = "杨清华")
    @SuppressWarnings("unchecked")
	@ApiOperation(value = "地区列表", notes = "所有地区列表 [1004,1000](蒋鑫俊)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Result<List<RegionPathDTO>> list() {
        Result<List<RegionPathDTO>> listResult = regionService.list();
        return successGet(listResult);
    }
}
