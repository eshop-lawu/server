package com.lawu.eshop.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.activity.dto.DiamondDistributionRecordDTO;
import com.lawu.eshop.activity.query.DiamondDistributionRecordQueryParam;
import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.operator.api.service.DiamondDistributionRecordService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * 钻石分配记录控制器
 * @author jiangxinjun
 * @createDate 2018年5月8日
 * @updateDate 2018年5月8日
 */
@Api(tags = {"diamondDistributionRecord", "钻石分配记录控制器"})
@RestController
@RequestMapping(value = "diamondDistributionRecord/")
public class DiamondDistributionRecordController extends BaseController {
    
    @Autowired
    private DiamondDistributionRecordService diamondDistributionRecordService;

    /**
     * 分页查询钻石分配记录
     * 
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年5月8日
     * @updateDate 2018年5月8日
     */
    @PageBody
    @ApiOperation(value = "钻石分配记录", notes = "分页查询钻石分配记录[]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("diamondDistributionRecord:page")
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "page",method = RequestMethod.GET)
    public Result<Page<DiamondDistributionRecordDTO>> page(@ModelAttribute @Validated DiamondDistributionRecordQueryParam param){
        return successGet(diamondDistributionRecordService.page(param));
    }
}
