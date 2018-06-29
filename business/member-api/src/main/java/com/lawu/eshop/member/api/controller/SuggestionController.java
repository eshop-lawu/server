package com.lawu.eshop.member.api.controller;

import com.lawu.eshop.framework.web.impl.constants.UserConstant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.mall.param.SuggestionParam;
import com.lawu.eshop.member.api.service.SuggestionService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author Sunny 
 * @date 2017/3/23
 */
@Api(tags = "suggestion")
@RestController
@RequestMapping(value = "suggestion/")
public class SuggestionController extends BaseController {

    @Autowired
    private SuggestionService suggestionService;

    @Audit(date = "2017-04-01", reviewer = "孙林青")
    @ApiOperation(value = "保存反馈意见", notes = "保存反馈意见。[1004|1005]（蒋鑫俊）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ModelAttribute @ApiParam(name = "parm", required = true, value = "反馈意见资料") SuggestionParam param) {
    	String userNum = UserUtil.getCurrentUserNum(getRequest());
    	return successCreated(suggestionService.save(userNum, param));
    }
    
}
