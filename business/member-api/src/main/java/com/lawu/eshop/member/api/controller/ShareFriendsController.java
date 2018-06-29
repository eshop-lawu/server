package com.lawu.eshop.member.api.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.mall.constants.ShareTypeEnum;
import com.lawu.eshop.mall.param.ShareFriendsParam;
import com.lawu.eshop.member.api.event.EventPublisher;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author meihsuquan
 * @date 2018/5/14.
 */
@Api(tags = "shareFriends")
@RestController
@RequestMapping(value = "shareFriends/")
public class ShareFriendsController extends BaseController {

    @Autowired
    private EventPublisher eventPublisher;

    @Audit(date = "2018-05-15", reviewer = "孙林青")
    @ApiOperation(value = "分享给E友插入站内消息", notes = "分享给E友插入站内消息 [1004]（梅述全）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "shareFriends", method = RequestMethod.POST)
    public Result getMessageStatistics(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                       @ModelAttribute @ApiParam ShareFriendsParam param) {
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        if (!param.getIsAll() && StringUtils.isEmpty(param.getUserNums())) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY);
        }

        Long memberId = 0L;
        if (param.getTypeEnum() == ShareTypeEnum.REDPACKET_MEMBER) {
            memberId = UserUtil.getCurrentUserId(getRequest());
        }
        eventPublisher.publishShareFriendsEvent(userNum, memberId, param); 
        return successCreated();
    }

}
