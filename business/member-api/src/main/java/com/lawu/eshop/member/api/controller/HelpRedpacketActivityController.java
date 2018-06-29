package com.lawu.eshop.member.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.activity.dto.HelpRedpacketActivityOpenDTO;
import com.lawu.eshop.member.api.service.HelpRedpacketActivityService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

@Api(tags = "helpRedpacketActivity")
@RestController
@RequestMapping(value = "helpRedpacketActivity/")
public class HelpRedpacketActivityController extends BaseController {
    
    @Autowired
    private HelpRedpacketActivityService helpRedpacketActivityService;
    
    
    /**
     * 查询助力红包活动是否开启
     * @return
     * @author jiangxinjun
     * @createDate 2017年12月29日
     * @updateDate 2017年12月29日
     */
    @Audit(date = "2018-01-02", reviewer = "孙林青")
    @SuppressWarnings("unchecked")  
    @ApiOperation(value = "助力红包活动是否开启", notes = "助力红包活动是否开启（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "isOpen", method = RequestMethod.GET)
    public Result<HelpRedpacketActivityOpenDTO> isOpen(@RequestParam(name = "id", required = false) @ApiParam(value = "红包活动id", required = true) Integer id) {
        if (id == null) {
            return successGet(helpRedpacketActivityService.isOpen());
        } else {
            return successGet(helpRedpacketActivityService.isOpen(id));
        }
    }
    
    /**
     * 获取开启的活动列表
     * 
     * @author jiangxinjun
     * @createDate 2018年1月15日
     * @updateDate 2018年1月15日
     */
    @Audit(date = "2018-01-17", reviewer = "孙林青")
    @SuppressWarnings("unchecked")  
    @ApiOperation(value = "获取开启的活动列表", notes = "获取开启的活动列表（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "openActivityList", method = RequestMethod.GET)
    public Result<List<HelpRedpacketActivityOpenDTO>> openActivityList() {
        return successGet(helpRedpacketActivityService.openActivityList());
    }
}
