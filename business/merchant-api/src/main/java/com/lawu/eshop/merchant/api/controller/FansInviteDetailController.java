package com.lawu.eshop.merchant.api.controller;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.merchant.api.service.FansInviteDetailService;
import com.lawu.eshop.property.dto.FansInviteDetailDTO;
import com.lawu.eshop.property.param.ListFansInviteDetailParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author meishuquan
 * @date 2017/4/24.
 */
@Api(tags = "fansInviteDetail")
@RestController
@RequestMapping(value = "fansInviteDetail/")
public class FansInviteDetailController extends BaseController {

    @Autowired
    private FansInviteDetailService fansInviteDetailService;

    @Audit(date = "2017-04-26", reviewer = "孙林青")
    @ApiOperation(value = "查询商家邀请粉丝记录", notes = "商家邀请粉丝记录列表。 (梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "listFansInviteDetail", method = RequestMethod.GET)
    public Result<Page<FansInviteDetailDTO>> listFansInviteDetail(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                                  @ModelAttribute @ApiParam ListFansInviteDetailParam listFansInviteDetailParam) {
        long merchantId = UserUtil.getCurrentUserId(getRequest());
        return fansInviteDetailService.listFansInviteDetail(merchantId, listFansInviteDetailParam);
    }

    @Audit(date = "2017-04-26", reviewer = "孙林青")
    @ApiOperation(value = "查询邀请粉丝详情", notes = "根据积分编号查询邀请粉丝详情。[1100] (梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "getFansInviteDetail/{pointNum}", method = RequestMethod.GET)
    public Result<FansInviteDetailDTO> getFansInviteDetail(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                           @PathVariable @ApiParam(required = true, value = "积分编号") String pointNum) {
        return fansInviteDetailService.getFansInviteDetailByPointNum(pointNum);
    }
}
