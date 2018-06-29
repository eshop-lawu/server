package com.lawu.eshop.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.activity.dto.RichDiamondRecordDTO;
import com.lawu.eshop.activity.query.DiamondRecordQueryParam;
import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.operator.api.service.RichDiamondRecordService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * 钻石记录控制器
 * @author jiangxinjun
 * @createDate 2018年6月7日
 * @updateDate 2018年6月7日
 */
@RestController
@RequestMapping(value = "richDiamondRecord/")
public class RichDiamondRecordController extends BaseController {
    
    @Autowired
    private RichDiamondRecordService richDiamondRecordService;
    
    @PageBody
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "分页查询钻石分配记录", notes = "分页查询钻石分配记录（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("richDiamondRecord:list")
    @RequestMapping(value = "page",method = RequestMethod.GET)
    public Result<Page<RichDiamondRecordDTO>> page(@ModelAttribute @Validated DiamondRecordQueryParam param){
        return successGet(richDiamondRecordService.page(param));
    }
}
