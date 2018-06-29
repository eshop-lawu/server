package com.lawu.eshop.member.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.member.api.service.SeckillActivityService;
import com.lawu.eshop.product.dto.RecentlySeckillActivityDTO;
import com.lawu.eshop.product.dto.SeckillActivityThatDayDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * 抢购活动控制器
 * 
 * @author jiangxinjun
 * @createDate 2017年11月24日
 * @updateDate 2017年11月24日
 */
@Api(tags = "seckillActivity")
@RestController
@RequestMapping(value = "seckillActivity/")
public class SeckillActivityController extends BaseController {
    
    @Autowired
    private SeckillActivityService seckillActivityService;

    @Audit(date = "2017-11-24", reviewer = "孙林青")
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "获取当天的所有活动", notes = "获取当天的所有活动[]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "thatday/list", method = RequestMethod.GET)
    public Result<List<SeckillActivityThatDayDTO>> thatDayList() {
        return successGet(seckillActivityService.thatDayList());
    }

    @Deprecated
    @Audit(date = "2017-11-24", reviewer = "孙林青")
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "获取最近一天的所有活动", notes = "获取最近一天的所有活动[]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "recently/list", method = RequestMethod.GET)
    public Result<List<SeckillActivityThatDayDTO>> recentlyList() {
        return successGet(seckillActivityService.recentlyList());
    }

    @Deprecated
    @Audit(date = "2018-03-01", reviewer = "孙林青")
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "查询最近一场的活动", notes = "查询最近一场活动的首页图片以及倒计时[]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "recently", method = RequestMethod.GET)
    public Result<RecentlySeckillActivityDTO> recentlyActivity() {
        return successGet(seckillActivityService.recentlyActivity());
    }

}
