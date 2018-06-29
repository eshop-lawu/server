package com.lawu.eshop.member.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.mall.dto.LotteryActivityDTO;
import com.lawu.eshop.mall.dto.LotteryActivityPointDTO;
import com.lawu.eshop.mall.query.LotteryActivityQuery;
import com.lawu.eshop.mall.query.LotteryActivityRealQuery;
import com.lawu.eshop.member.api.service.LotteryActivityService;
import com.lawu.eshop.member.api.service.UserGradeService;
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
 * @date 2017/11/23.
 */
@Api(tags = "lotteryActivity")
@RestController
@RequestMapping(value = "lotteryActivity/")
public class LotteryActivityController extends BaseController {

    @Autowired
    private LotteryActivityService lotteryActivityService;

    @Autowired
    private UserGradeService userGradeService;

    @Audit(date = "2017-11-24", reviewer = "孙林青")
    @ApiOperation(value = "抽奖活动列表", notes = "抽奖活动列表。 (梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "listLotteryActivity", method = RequestMethod.GET)
    public Result<Page<LotteryActivityDTO>> listLotteryActivity(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                                @ModelAttribute LotteryActivityQuery activityQuery) {
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        LotteryActivityRealQuery query = new LotteryActivityRealQuery();
        query.setCurrentPage(activityQuery.getCurrentPage());
        query.setPageSize(activityQuery.getPageSize());
        query.setUserNum(userNum);
        Result<Page<LotteryActivityDTO>> result = lotteryActivityService.listLotteryActivity(query);
        for (LotteryActivityDTO activityDTO : result.getModel().getRecords()) {
            Result<Integer> pointResult = userGradeService.selectLotteryActivityPointByGradeValue(activityDTO.getGradeEnum().getVal());
            if (pointResult.getModel() != null) {
                activityDTO.setPoint(pointResult.getModel());
            }
        }
        return successGet(result);
    }

    @Audit(date = "2017-11-28", reviewer = "孙林青")
    @ApiOperation(value = "抽奖活动所需积分", notes = "抽奖活动所需积分。[1002] (梅述全)", httpMethod = "GET")
    @Authorization
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getLotteryActivityPoint/{id}", method = RequestMethod.GET)
    public Result<LotteryActivityPointDTO> getLotteryActivityPoint(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                                   @PathVariable @ApiParam(required = true, value = "抽奖活动ID") Long id) {
        Result<LotteryActivityDTO> activityDTOResult = lotteryActivityService.getLotteryActivityById(id);
        if (activityDTOResult.getModel() == null) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        Result<Integer> pointResult = userGradeService.selectLotteryActivityPointByGradeValue(activityDTOResult.getModel().getGradeEnum().getVal());
        if (pointResult.getModel() == null) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        LotteryActivityPointDTO pointDTO = new LotteryActivityPointDTO();
        pointDTO.setPoint(pointResult.getModel());
        return successGet(pointDTO);
    }

}
