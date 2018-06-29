package com.lawu.eshop.merchant.api.controller;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.merchant.api.service.RongUserService;
import com.lawu.eshop.user.dto.RongYunOnlineDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangyong
 * @date 2017/4/14.
 */
@Api(tags = "rongUser")
@RestController
@RequestMapping(value = "rongUser")
public class RongUserController extends BaseController {

    @Autowired
    private RongUserService rongUserService;

    @Audit(date = "2017-04-15", reviewer = "孙林青")
    @ApiOperation(value = "检查在线状态", notes = "检查在线状态。[1000] (章勇)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "checkOnline", method = RequestMethod.GET)
    public Result<RongYunOnlineDTO> checkOnline(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) throws Exception {
        String userNum = UserUtil.getCurrentUserNum(getRequest());

        Result<RongYunOnlineDTO> onlineDTO = rongUserService.checkOnline(userNum);
        return onlineDTO;
    }

}
