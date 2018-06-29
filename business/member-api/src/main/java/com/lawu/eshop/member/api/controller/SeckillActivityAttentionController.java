package com.lawu.eshop.member.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.member.api.service.SeckillActivityAttentionService;
import com.lawu.eshop.product.param.SeckillActivityProductAttentionParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * 抢购活动关注控制器
 * 
 * @author jiangxinjun
 * @createDate 2017年11月24日
 * @updateDate 2017年11月24日
 */
@Api(tags = "seckillActivityAttention")
@RestController
@RequestMapping(value = "seckillActivityAttention/")
public class SeckillActivityAttentionController extends BaseController {
    
    @Autowired
    private SeckillActivityAttentionService seckillActivityAttentionService;

    @Audit(date = "2017-11-24", reviewer = "孙林青")
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "关注抢购活动商品", notes = "关注抢购活动商品[]（蒋鑫俊）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "attention/{activityProductId}", method = RequestMethod.POST)
    public Result attention(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ApiParam(value = "抢购活动商品id", required = true) @PathVariable("activityProductId") Long activityProductId) {
        SeckillActivityProductAttentionParam param = new SeckillActivityProductAttentionParam();
        param.setMemberId(UserUtil.getCurrentUserId(getRequest()));
        param.setMemberNum(UserUtil.getCurrentUserNum(getRequest()));
        seckillActivityAttentionService.attention(activityProductId, param);
        return successCreated();
    }
    
}
