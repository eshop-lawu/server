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
import com.lawu.eshop.activity.dto.DrawLotteryActivityDTO;
import com.lawu.eshop.activity.dto.DrawLotteryActivityDetailDTO;
import com.lawu.eshop.activity.dto.DrawLotteryActivityNoticeDTO;
import com.lawu.eshop.activity.dto.DrawLotteryActivityPrizeDTO;
import com.lawu.eshop.activity.query.DrawLotteryActivityQuery;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.member.api.service.DrawLotteryActivityMonthRecordService;
import com.lawu.eshop.member.api.service.DrawLotteryActivityService;
import com.lawu.eshop.member.api.service.MerchantStoreService;
import com.lawu.eshop.member.api.service.UserGradeService;
import com.lawu.eshop.user.dto.LotteryGradeDTO;
import com.lawu.eshop.user.dto.MerchantStoreDTO;
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
 * @date 2018/1/12.
 */
@Api(tags = "drawLotteryActivity")
@RestController
@RequestMapping(value = "drawLotteryActivity/")
public class DrawLotteryActivityController extends BaseController {

    @Autowired
    private DrawLotteryActivityService drawLotteryActivityService;

    @Autowired
    private UserGradeService userGradeService;

    @Autowired
    private MerchantStoreService merchantStoreService;

    @Autowired
    private DrawLotteryActivityMonthRecordService drawLotteryActivityMonthRecordService;

    @Audit(date = "2018-01-17", reviewer = "孙林青")
    @ApiOperation(value = "抽奖活动列表", notes = "抽奖活动列表。(梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "listDrawLotteryActivity", method = RequestMethod.GET)
    public Result<Page<DrawLotteryActivityDTO>> listDrawLotteryActivity(@ModelAttribute DrawLotteryActivityQuery query) {
        Result<Page<DrawLotteryActivityDTO>> result = drawLotteryActivityService.listDrawLotteryActivity(query);
        return successGet(result);
    }

    @Audit(date = "2018-01-17", reviewer = "孙林青")
    @ApiOperation(value = "抽奖活动详情", notes = "抽奖活动详情。[1002] (梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "getDrawLotteryActivityDetail/{id}", method = RequestMethod.GET)
    public Result<DrawLotteryActivityDetailDTO> getDrawLotteryActivityDetail(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                                             @PathVariable @ApiParam(required = true, value = "抽奖活动ID") Long id) {
        Long userId = UserUtil.getCurrentUserId(getRequest());
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        Result<DrawLotteryActivityDetailDTO> result = drawLotteryActivityService.getDrawLotteryActivityDetail(id, userNum);
        DrawLotteryActivityDetailDTO detailDTO = result.getModel();
        if (detailDTO == null) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        Result<LotteryGradeDTO> gradeDTOResult = userGradeService.getLotteryGradeInfo(userId, detailDTO.getGradeEnum().getVal());
        Result<Integer> monthFreeLotteryResult = drawLotteryActivityMonthRecordService.countMonthFreeLottery(userNum);
        detailDTO.setPoint(gradeDTOResult.getModel().getLotteryActivityPoint());
        if (gradeDTOResult.getModel().getFreeLotteryCount() <= monthFreeLotteryResult.getModel()) {
            detailDTO.setFreeLotteryCount(0);
        }

        for (DrawLotteryActivityPrizeDTO prizeDTO : detailDTO.getActivityPrizeDTOS()) {
            Result<MerchantStoreDTO> storeDTOResult = merchantStoreService.getMerchantStoreById(prizeDTO.getMerchantStoreId());
            if (isSuccess(storeDTOResult)) {
                prizeDTO.setStoreName(storeDTOResult.getModel().getName());
            }
            String storeLogo = merchantStoreService.getLogoUrlByStoreId(prizeDTO.getMerchantStoreId());
            prizeDTO.setStoreLogo(storeLogo);
        }
        return successGet(detailDTO);
    }

    @Audit(date = "2018-01-17", reviewer = "孙林青")
    @ApiOperation(value = "中奖公告列表", notes = "中奖公告列表。(梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "listDrawLotteryActivityNotice", method = RequestMethod.GET)
    public Result<Page<DrawLotteryActivityNoticeDTO>> listDrawLotteryActivityNotice(@ModelAttribute DrawLotteryActivityQuery query) {
        Result<Page<DrawLotteryActivityNoticeDTO>> result = drawLotteryActivityService.listDrawLotteryActivityNotice(query);
        return successGet(result);
    }

}
