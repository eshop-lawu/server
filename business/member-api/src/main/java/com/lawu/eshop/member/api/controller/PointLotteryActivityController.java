package com.lawu.eshop.member.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.activity.dto.PointLotteryDetailDTO;
import com.lawu.eshop.activity.dto.PointLotteryInfoDTO;
import com.lawu.eshop.activity.query.PointLotteryActivityQuery;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.member.api.service.PointLotteryActivityService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author meishuquan
 * @date 2018/2/1.
 */
@Api(tags = "pointLotteryActivity")
@RestController
@RequestMapping(value = "pointLotteryActivity/")
public class PointLotteryActivityController extends BaseController {

    @Autowired
    private PointLotteryActivityService pointLotteryActivityService;

    @Audit(date = "2018-02-06", reviewer = "孙林青")
    @ApiOperation(value = "积分抽奖活动列表", notes = "积分抽奖活动列表。(梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "listPointLotteryActivity", method = RequestMethod.GET)
    public Result<Page<PointLotteryInfoDTO>> listPointLotteryActivity(@ModelAttribute PointLotteryActivityQuery query) {
        Result<Page<PointLotteryInfoDTO>> result = pointLotteryActivityService.listPointLotteryActivity(query);
        return successGet(result);
    }

    @Audit(date = "2018-02-06", reviewer = "孙林青")
    @ApiOperation(value = "积分抽奖活动详情", notes = "积分抽奖活动详情。[1002] (梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getPointLotteryDetail/{id}", method = RequestMethod.GET)
    public Result<PointLotteryDetailDTO> getPointLotteryDetail(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                               @PathVariable @ApiParam(required = true, value = "抽奖活动ID") Long id) {
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        if (userNum == null) {
            userNum = "";
        }
        Result<PointLotteryDetailDTO> result = pointLotteryActivityService.getPointLotteryDetail(id, userNum);
        return successGet(result);
    }

}
