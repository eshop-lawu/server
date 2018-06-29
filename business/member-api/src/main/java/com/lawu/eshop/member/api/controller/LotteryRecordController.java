package com.lawu.eshop.member.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.mall.constants.LotteryActivityStatusEnum;
import com.lawu.eshop.mall.dto.LotteryActivityDTO;
import com.lawu.eshop.mall.dto.LotteryInfoDTO;
import com.lawu.eshop.mall.dto.LotteryRecordDTO;
import com.lawu.eshop.mall.param.LotteryRecordParam;
import com.lawu.eshop.mall.query.LotteryRecordQuery;
import com.lawu.eshop.member.api.service.LotteryActivityService;
import com.lawu.eshop.member.api.service.LotteryRecordService;
import com.lawu.eshop.member.api.service.MemberService;
import com.lawu.eshop.member.api.service.PointDetailService;
import com.lawu.eshop.member.api.service.PropertyInfoDataService;
import com.lawu.eshop.member.api.service.UserGradeService;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.param.PropertyInfoDataParam;
import com.lawu.eshop.user.dto.UserDTO;
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
@Api(tags = "lotteryRecord")
@RestController
@RequestMapping(value = "lotteryRecord/")
public class LotteryRecordController extends BaseController {

    @Autowired
    private LotteryRecordService lotteryRecordService;

    @Autowired
    private LotteryActivityService lotteryActivityService;

    @Autowired
    private PointDetailService pointDetailService;

    @Autowired
    private PropertyInfoDataService propertyInfoDataService;

    @Autowired
    private UserGradeService userGradeService;

    @Autowired
    private MemberService memberService;

    @Audit(date = "2017-11-24", reviewer = "孙林青")
    @ApiOperation(value = "参与抽奖", notes = "立即抽奖。[1002|2021|2022|2023] (梅述全)", httpMethod = "POST")
    @Authorization
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "takePart", method = RequestMethod.POST)
    public Result takePart(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                           @RequestParam @ApiParam(required = true, value = "抽奖活动ID") Long lotteryActivityId) {
        Long userId = UserUtil.getCurrentUserId(getRequest());
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        String account = UserUtil.getCurrentAccount(getRequest());

        Result<LotteryActivityDTO> activityDTOResult = lotteryActivityService.getLotteryActivityById(lotteryActivityId);
        if (activityDTOResult.getModel() == null) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND);
        }
        if (activityDTOResult.getModel().getStatusEnum().getVal() != LotteryActivityStatusEnum.LOTTERYING.getVal().byteValue()) {
            return successCreated(ResultCode.NOT_LOTTERYING);
        }
        Result<UserDTO> userResult = memberService.findMemberInfo(userId);
        if (userResult.getModel() == null) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND);
        }
        if (userResult.getModel().getGrade() < activityDTOResult.getModel().getGradeEnum().getVal()) {
            return successCreated(ResultCode.GRADE_NOT_MATCH);
        }
        Result<Boolean> result = lotteryRecordService.lotteryRecord(lotteryActivityId, userNum);
        if (result.getModel()) {
            return successCreated(ResultCode.HAVE_TAKE_PART);
        }

        LotteryRecordParam param = new LotteryRecordParam();
        param.setUserId(userId);
        param.setUserNum(userNum);
        param.setAccount(account);
        param.setLotteryActivityId(lotteryActivityId);
        param.setPrizeName(activityDTOResult.getModel().getPrizeName());
        lotteryRecordService.saveLotteryRecord(param);
        return successCreated();
    }

    @Audit(date = "2017-11-24", reviewer = "孙林青")
    @ApiOperation(value = "积分抽奖", notes = "积分抽奖。[1002|1004|2020|2022|2023|6025|6002|6003|6024|6010|6011] (梅述全)", httpMethod = "POST")
    @Authorization
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "takePartByPay", method = RequestMethod.POST)
    public Result takePartByPay(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                @RequestParam @ApiParam(required = true, value = "抽奖活动ID") Long lotteryActivityId) {
        Long id = UserUtil.getCurrentUserId(getRequest());
        String userNum = UserUtil.getCurrentUserNum(getRequest());

        Result<Boolean> result = pointDetailService.existsPointDetailByUserNumAndBizId(userNum, String.valueOf(lotteryActivityId));
        if (result.getModel()) {
            return successCreated(ResultCode.NO_LOTTERY_COUNT);
        }
        Result<LotteryActivityDTO> activityDTOResult = lotteryActivityService.getLotteryActivityById(lotteryActivityId);
        if (activityDTOResult.getModel() == null) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND);
        }
        if (activityDTOResult.getModel().getStatusEnum().getVal() != LotteryActivityStatusEnum.LOTTERYING.getVal().byteValue()) {
            return successCreated(ResultCode.NOT_LOTTERYING);
        }
        Result<UserDTO> userResult = memberService.findMemberInfo(id);
        if (userResult.getModel() == null) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND);
        }
        if (userResult.getModel().getGrade() < activityDTOResult.getModel().getGradeEnum().getVal()) {
            return successCreated(ResultCode.GRADE_NOT_MATCH);
        }
        Result<Integer> pointResult = userGradeService.selectLotteryActivityPointByGradeValue(activityDTOResult.getModel().getGradeEnum().getVal());
        if (pointResult.getModel() == null) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND);
        }

        PropertyInfoDataParam param = new PropertyInfoDataParam();
        param.setUserNum(userNum);
        param.setPoint(String.valueOf(pointResult.getModel()));
        param.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.POINT_CONVERT_LOTTERY);
        param.setBizId(String.valueOf(lotteryActivityId));
        Result propertyResult = propertyInfoDataService.doHanlderMinusPointWithLottery(param);
        return successCreated(propertyResult.getRet());
    }

    @Audit(date = "2017-11-24", reviewer = "孙林青")
    @ApiOperation(value = "中奖滚动列表", notes = "中奖滚动列表。 (梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "listLotteryInfo", method = RequestMethod.GET)
    public Result<List<LotteryInfoDTO>> listLotteryInfo() {
        Result<List<LotteryInfoDTO>> result = lotteryRecordService.listLotteryInfo();
        return successGet(result);
    }

    @Audit(date = "2017-11-24", reviewer = "孙林青")
    @ApiOperation(value = "中奖公告列表", notes = "中奖公告列表。 (梅述全)", httpMethod = "GET")
    @Authorization
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "listLotteryRecord", method = RequestMethod.GET)
    public Result<Page<LotteryRecordDTO>> listLotteryRecord(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                            @ModelAttribute LotteryRecordQuery query) {
        Result<Page<LotteryRecordDTO>> result = lotteryRecordService.listLotteryRecord(query);
        return successGet(result);
    }

}
