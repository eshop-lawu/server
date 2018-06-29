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
import com.lawu.eshop.member.api.service.FansInviteContentService;
import com.lawu.eshop.user.dto.FansInviteContentDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
/**
 * @author hongqm
 * @date 2017/8/4.
 */
@Api(tags = "fansInviteContent")
@RestController
@RequestMapping(value = "fansInviteContent/")
public class FansInviteContentController extends BaseController{

	
	@Autowired
	private FansInviteContentService fansInviteContentService;

    @Audit(date = "2017-08-10", reviewer = "孙林青")
	@ApiOperation(value = "查询邀请粉丝详情", notes = "查询邀请粉丝详情。 [] (洪钦明)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "selectInviteContentById/{id}/{relateId}", method = RequestMethod.GET)
    public Result<FansInviteContentDTO> selectInviteContentById(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,@PathVariable("id") @ApiParam(value = "messageId", required = true) Long id,@PathVariable("relateId") @ApiParam(value = "mesasge里的relateId", required = true) Long relateId) {
        Long memberId = UserUtil.getCurrentUserId(getRequest());
		Result<FansInviteContentDTO> result = fansInviteContentService.selectInviteContentById(id,relateId,memberId);
		return result;
    }
}
