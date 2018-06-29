package com.lawu.eshop.operator.api.controller;

import com.lawu.eshop.mall.dto.RegionDTO;
import com.lawu.eshop.operator.api.service.RegionService;
import com.lawu.eshop.operator.api.service.UserRegService;
import com.lawu.eshop.statistics.dto.ReportUserRegAreaDTO;
import com.lawu.eshop.statistics.dto.ReportUserRegDTO;
import com.lawu.eshop.statistics.param.UserRegParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author meishuquan
 * @date 2017/6/30.
 */
@Api(tags = "userReg")
@RestController
@RequestMapping(value = "userReg/")
public class UserRegController extends BaseController {

    @Autowired
    private UserRegService userRegService;

    @Autowired
    private RegionService regionService;

    @ApiOperation(value = "查询日统计列表", notes = "查询日统计列表（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("user_reg:date")
    @RequestMapping(value = "getReportUserRegDaily", method = RequestMethod.GET)
    public Result<List<ReportUserRegDTO>> getReportUserRegDaily(@ModelAttribute UserRegParam param) {
        return userRegService.getReportUserRegDaily(param);
    }

    @ApiOperation(value = "查询月统计列表", notes = "查询月统计列表（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("user_reg:date")
    @RequestMapping(value = "getReportUserRegMonth", method = RequestMethod.GET)
    public Result<List<ReportUserRegDTO>> getReportUserRegMonth(@ModelAttribute UserRegParam param) {
        return userRegService.getReportUserRegMonth(param);
    }

    @ApiOperation(value = "查询区域统计列表", notes = "查询区域统计列表（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("user_reg:area")
    @RequestMapping(value = "getReportUserRegArea", method = RequestMethod.GET)
    public Result<List<ReportUserRegAreaDTO>> getReportUserRegArea() {
        Result<List<ReportUserRegAreaDTO>> result = userRegService.getReportUserRegArea();
        if (!result.getModel().isEmpty()) {
            for (ReportUserRegAreaDTO regAreaDTO : result.getModel()) {
                Result<RegionDTO> regionDTOResult = regionService.getRegionById(regAreaDTO.getCityId());
                if (regionDTOResult.getModel() != null) {
                    regAreaDTO.setLongitude(regionDTOResult.getModel().getLongitude());
                    regAreaDTO.setLatitude(regionDTOResult.getModel().getLatitude());
                }
            }
        }
        return result;
    }

}
