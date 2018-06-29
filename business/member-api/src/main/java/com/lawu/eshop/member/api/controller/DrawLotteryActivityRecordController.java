package com.lawu.eshop.member.api.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.activity.constants.DrawLotteryActivityPrizeTypeEnum;
import com.lawu.eshop.activity.constants.DrawLotteryActivityRecordChannelEnum;
import com.lawu.eshop.activity.constants.DrawLotteryActivityRecordStatusEnum;
import com.lawu.eshop.activity.constants.DrawLotteryActivityStatusEnum;
import com.lawu.eshop.activity.dto.DrawLotteryActivityDTO;
import com.lawu.eshop.activity.dto.DrawLotteryActivityPrizeDTO;
import com.lawu.eshop.activity.dto.DrawLotteryActivityPrizeDetailDTO;
import com.lawu.eshop.activity.dto.DrawLotteryActivityRecordDTO;
import com.lawu.eshop.activity.dto.DrawLotteryActivityRecordPageDTO;
import com.lawu.eshop.activity.dto.TakePartLotteryInfoDTO;
import com.lawu.eshop.activity.param.RichPowerTaskRecordParam;
import com.lawu.eshop.activity.param.TakeLotteryParam;
import com.lawu.eshop.activity.param.TakePartLotteryParam;
import com.lawu.eshop.activity.query.DrawLotteryActivityRecordQuery;
import com.lawu.eshop.cache.constants.PowerTaskTypeEnum;
import com.lawu.eshop.common.constants.MessageTypeEnum;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.mall.param.MessageInfoParam;
import com.lawu.eshop.mall.param.MessageTempParam;
import com.lawu.eshop.member.api.event.EventPublisher;
import com.lawu.eshop.member.api.service.DrawLotteryActivityMonthRecordService;
import com.lawu.eshop.member.api.service.DrawLotteryActivityPrizeService;
import com.lawu.eshop.member.api.service.DrawLotteryActivityRecordService;
import com.lawu.eshop.member.api.service.DrawLotteryActivityService;
import com.lawu.eshop.member.api.service.MemberService;
import com.lawu.eshop.member.api.service.MessageService;
import com.lawu.eshop.member.api.service.PropertyInfoService;
import com.lawu.eshop.member.api.service.UserGradeService;
import com.lawu.eshop.user.dto.LotteryGradeDTO;
import com.lawu.eshop.user.dto.UserDTO;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;
import com.lawu.utils.LotteryHelper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author meishuquan
 * @date 2018/1/15.
 */
@Api(tags = "drawLotteryActivityRecord")
@RestController
@RequestMapping(value = "drawLotteryActivityRecord/")
public class DrawLotteryActivityRecordController extends BaseController {

    @Autowired
    private DrawLotteryActivityRecordService drawLotteryActivityRecordService;

    @Autowired
    private DrawLotteryActivityService drawLotteryActivityService;

    @Autowired
    private DrawLotteryActivityPrizeService drawLotteryActivityPrizeService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private UserGradeService userGradeService;

    @Autowired
    private PropertyInfoService propertyInfoService;

    @Autowired
    private DrawLotteryActivityMonthRecordService drawLotteryActivityMonthRecordService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private EventPublisher eventPublisher;

    @Audit(date = "2018-01-17", reviewer = "孙林青")
    @ApiOperation(value = "免费抽奖", notes = "免费抽奖。[1002|9101|9102|2022|9105] (梅述全)", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "takePartLottery/{drawLotteryActivityId}", method = RequestMethod.POST)
    public Result<TakePartLotteryInfoDTO> takePartLottery(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                          @PathVariable @ApiParam(required = true, value = "抽奖活动ID") Long drawLotteryActivityId) {
        Long userId = UserUtil.getCurrentUserId(getRequest());
        String account = UserUtil.getCurrentAccount(getRequest());
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        Result<TakePartLotteryInfoDTO> lotteryInfoDTOResult = drawLotteryActivityRecordService.getTakePartLottery(drawLotteryActivityId, userNum, DrawLotteryActivityRecordStatusEnum.TAKE_PART_LOTTERY);
        if (lotteryInfoDTOResult.getModel().getId() > 0) {
            return successCreated(lotteryInfoDTOResult);
        }

        Result<DrawLotteryActivityDTO> activityDTOResult = drawLotteryActivityService.getDrawLotteryActivity(drawLotteryActivityId);
        if (activityDTOResult.getModel() == null) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND);
        }
        if (activityDTOResult.getModel().getStatusEnum().equals(DrawLotteryActivityStatusEnum.FINISHED)) {
            return successCreated(ResultCode.LOTTERY_ACTIVITY_FINISHED);
        }
        if (!activityDTOResult.getModel().getStatusEnum().equals(DrawLotteryActivityStatusEnum.LOTTERYING)) {
            return successCreated(ResultCode.LOTTERY_NOT_ING);
        }

        Result<UserDTO> userResult = memberService.findMemberInfo(userId);
        if (userResult.getModel().getGrade() < activityDTOResult.getModel().getGrade()) {
            return successCreated(ResultCode.GRADE_NOT_MATCH);
        }

        Result<LotteryGradeDTO> gradeDTOResult = userGradeService.getLotteryGradeInfo(userId, activityDTOResult.getModel().getGrade());
        Result<Integer> monthFreeLotteryResult = drawLotteryActivityMonthRecordService.countMonthFreeLottery(userNum);
        if (gradeDTOResult.getModel().getFreeLotteryCount() <= monthFreeLotteryResult.getModel()) {
            return successCreated(ResultCode.FREE_LOTTERY_COUNT_OVER);
        }

        TakePartLotteryParam param = new TakePartLotteryParam();
        param.setUserId(userId);
        param.setUserNum(userNum);
        param.setUserAccount(account);
        param.setDrawLotteryActivityId(drawLotteryActivityId);
        param.setChannelEnum(DrawLotteryActivityRecordChannelEnum.FREE_LOTTERY);
        Result<TakePartLotteryInfoDTO> result = drawLotteryActivityRecordService.takePartLottery(param);
        return successCreated(result);
    }

    @Audit(date = "2018-01-17", reviewer = "孙林青")
    @ApiOperation(value = "积分抽奖", notes = "积分抽奖。[1002|2022|9101|6002|6003|6024|6010|9105|9106] (梅述全)", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "takePartLotteryByPay/{drawLotteryActivityId}", method = RequestMethod.POST)
    public Result<TakePartLotteryInfoDTO> takePartLotteryByPay(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                               @PathVariable @ApiParam(required = true, value = "抽奖活动ID") Long drawLotteryActivityId) {
        Long userId = UserUtil.getCurrentUserId(getRequest());
        String account = UserUtil.getCurrentAccount(getRequest());
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        Result<TakePartLotteryInfoDTO> lotteryInfoDTOResult = drawLotteryActivityRecordService.getTakePartLottery(drawLotteryActivityId, userNum, DrawLotteryActivityRecordStatusEnum.POINT_DEDUCT_ING);
        if (lotteryInfoDTOResult.getModel().getId() > 0) {
            return successCreated(ResultCode.LOTTERY_RECORD_HANDLE);
        }
        lotteryInfoDTOResult = drawLotteryActivityRecordService.getTakePartLottery(drawLotteryActivityId, userNum, DrawLotteryActivityRecordStatusEnum.TAKE_PART_LOTTERY);
        if (lotteryInfoDTOResult.getModel().getId() > 0) {
            return successCreated(lotteryInfoDTOResult);
        }

        Result<DrawLotteryActivityDTO> activityDTOResult = drawLotteryActivityService.getDrawLotteryActivity(drawLotteryActivityId);
        if (activityDTOResult.getModel() == null) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND);
        }
        if (activityDTOResult.getModel().getStatusEnum().equals(DrawLotteryActivityStatusEnum.FINISHED)) {
            return successCreated(ResultCode.LOTTERY_ACTIVITY_FINISHED);
        }
        if (!activityDTOResult.getModel().getStatusEnum().equals(DrawLotteryActivityStatusEnum.LOTTERYING)) {
            return successCreated(ResultCode.LOTTERY_NOT_ING);
        }

        Result<UserDTO> userResult = memberService.findMemberInfo(userId);
        if (userResult.getModel().getGrade() < activityDTOResult.getModel().getGrade()) {
            return successCreated(ResultCode.GRADE_NOT_MATCH);
        }

        Result<Integer> pointResult = userGradeService.selectLotteryActivityPointByGradeValue(activityDTOResult.getModel().getGrade());
        if (pointResult.getModel() == null) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND);
        }

        Result validatePointResult = propertyInfoService.validatePoint(userNum, String.valueOf(pointResult.getModel()));
        if (!isSuccess(validatePointResult)) {
            return successCreated(validatePointResult.getRet());
        }

        TakePartLotteryParam param = new TakePartLotteryParam();
        param.setUserId(userId);
        param.setUserNum(userNum);
        param.setUserAccount(account);
        param.setDrawLotteryActivityId(drawLotteryActivityId);
        param.setPayPoint(BigDecimal.valueOf(pointResult.getModel()));
        param.setChannelEnum(DrawLotteryActivityRecordChannelEnum.POINT_LOTTERY);
        Result<TakePartLotteryInfoDTO> result = drawLotteryActivityRecordService.takePartLottery(param);
        return successCreated(result);
    }

    @Audit(date = "2018-02-01", reviewer = "孙林青")
    @ApiOperation(value = "查询抽奖记录", notes = "查询抽奖记录。[1002] (梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "getDrawLotteryActivityRecord/{id}", method = RequestMethod.GET)
    public Result<DrawLotteryActivityRecordDTO> getDrawLotteryActivityRecord(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                                             @PathVariable @ApiParam(required = true, value = "抽奖记录ID") Long id) {
        Result<DrawLotteryActivityRecordDTO> recordDTOResult = drawLotteryActivityRecordService.getDrawLotteryActivityRecord(id);
        if (recordDTOResult.getModel() == null) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        return successGet(recordDTOResult);
    }

    @Audit(date = "2018-01-17", reviewer = "孙林青")
    @ApiOperation(value = "放弃领奖", notes = "放弃领奖。[1002|9108] (梅述全)", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "giveUpTakeLottery/{id}", method = RequestMethod.PUT)
    public Result giveUpTakeLottery(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                    @PathVariable @ApiParam(required = true, value = "抽奖记录ID") Long id) {
        Result<DrawLotteryActivityRecordDTO> recordDTOResult = drawLotteryActivityRecordService.getDrawLotteryActivityRecord(id);
        if (recordDTOResult.getModel() == null) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND);
        }
        if (!DrawLotteryActivityRecordStatusEnum.GET_LOTTERY.equals(recordDTOResult.getModel().getStatusEnum())) {
            return successCreated(ResultCode.LOTTERY_RECORD_HAVE_DEAL);
        }
        drawLotteryActivityRecordService.giveUpTakeLottery(id);
        return successCreated();
    }

    @Audit(date = "2018-01-17", reviewer = "孙林青")
    @ApiOperation(value = "领奖", notes = "领奖。[1002|9108] (梅述全)", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "takeLottery/{id}", method = RequestMethod.PUT)
    public Result takeLottery(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                              @ModelAttribute TakeLotteryParam param) {
        Result<DrawLotteryActivityRecordDTO> recordDTOResult = drawLotteryActivityRecordService.getDrawLotteryActivityRecord(param.getId());
        if (recordDTOResult.getModel() == null) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND);
        }
        if (!DrawLotteryActivityRecordStatusEnum.GET_LOTTERY.equals(recordDTOResult.getModel().getStatusEnum())) {
            return successCreated(ResultCode.LOTTERY_RECORD_HAVE_DEAL);
        }

        drawLotteryActivityRecordService.takeLottery(param);
        Result<DrawLotteryActivityDTO> activityDTOResult = drawLotteryActivityService.getDrawLotteryActivity(recordDTOResult.getModel().getDrawLotteryActivityId());
        Result<DrawLotteryActivityPrizeDetailDTO> prizeDetailDTOResult = drawLotteryActivityPrizeService.getPrizeByRecordId(param.getId());
        MessageTempParam messageTempParam = new MessageTempParam();
        messageTempParam.setActivityName(activityDTOResult.getModel().getGradeDes().substring(0, 2));
        messageTempParam.setProductName(recordDTOResult.getModel().getPrizeName());
        MessageInfoParam messageInfoParam = new MessageInfoParam();
        messageInfoParam.setRelateId(param.getId());
        messageInfoParam.setIsPush(true);
        messageInfoParam.setMessageParam(messageTempParam);
        if (prizeDetailDTOResult.getModel().getPrizeTypeEnum() == DrawLotteryActivityPrizeTypeEnum.MONEY) {
            messageInfoParam.setTypeEnum(MessageTypeEnum.LOTTERY_ACTIVITY_WIN_MONEY);
        } else if (prizeDetailDTOResult.getModel().getPrizeTypeEnum() == DrawLotteryActivityPrizeTypeEnum.POINT) {
            messageInfoParam.setTypeEnum(MessageTypeEnum.LOTTERY_ACTIVITY_WIN_POINT);
        } else {
            messageInfoParam.setTypeEnum(MessageTypeEnum.LOTTERY_ACTIVITY_WIN);
        }
        messageService.saveMessage(UserUtil.getCurrentUserNum(getRequest()), messageInfoParam);
        return successCreated();
    }

    @Audit(date = "2018-01-17", reviewer = "孙林青")
    @ApiOperation(value = "抽奖", notes = "抽奖。[1002|9100|9101|9103|9105|9106|9107] (梅述全)", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "openLottery/{id}", method = RequestMethod.PUT)
    public Result<DrawLotteryActivityPrizeDetailDTO> openLottery(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                                 @PathVariable @ApiParam(required = true, value = "抽奖记录ID") Long id) {
        Result<DrawLotteryActivityRecordDTO> recordDTOResult = drawLotteryActivityRecordService.getDrawLotteryActivityRecord(id);
        if (recordDTOResult.getModel() == null) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND);
        }
        if (DrawLotteryActivityRecordStatusEnum.POINT_DEDUCT_ING.equals(recordDTOResult.getModel().getStatusEnum())) {
            return successCreated(ResultCode.LOTTERY_RECORD_HANDLE);
        }
        if (DrawLotteryActivityRecordStatusEnum.POINT_DEDUCT_FAIL.equals(recordDTOResult.getModel().getStatusEnum())) {
            return successCreated(ResultCode.TAKE_PART_LOTTERY_ERROR);
        }
        if (!DrawLotteryActivityRecordStatusEnum.TAKE_PART_LOTTERY.equals(recordDTOResult.getModel().getStatusEnum())) {
            return successCreated(ResultCode.LOTTERY_RECORD_USED);
        }
        Result<DrawLotteryActivityDTO> activityDTOResult = drawLotteryActivityService.getDrawLotteryActivity(recordDTOResult.getModel().getDrawLotteryActivityId());
        if (activityDTOResult.getModel() == null) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND);
        }
        if (activityDTOResult.getModel().getStatusEnum().equals(DrawLotteryActivityStatusEnum.FINISHED)) {
            return successCreated(ResultCode.LOTTERY_ACTIVITY_FINISHED);
        }
        if (!activityDTOResult.getModel().getStatusEnum().equals(DrawLotteryActivityStatusEnum.LOTTERYING)) {
            return successCreated(ResultCode.LOTTERY_NOT_ING);
        }

        //查询抽奖活动奖品
        Result<List<DrawLotteryActivityPrizeDTO>> prizeDTOResult = drawLotteryActivityPrizeService.getActivityAllPrize(recordDTOResult.getModel().getDrawLotteryActivityId());
        List<DrawLotteryActivityPrizeDTO> prizeDTOS = prizeDTOResult.getModel();
        if (prizeDTOS.isEmpty()) {
            return successCreated(ResultCode.LOTTERY_PRIZE_ERROR);
        }

        //玩游戏完成瑞奇岛动力任务
        RichPowerTaskRecordParam taskRecordParam = new RichPowerTaskRecordParam();
        taskRecordParam.setMemberNum(UserUtil.getCurrentUserNum(getRequest()));
        taskRecordParam.setType(PowerTaskTypeEnum.GAME);
        eventPublisher.publishRichPowerTaskEvent(taskRecordParam);

        Map<Object, Double> probs = new HashMap<>();
        for (DrawLotteryActivityPrizeDTO prizeDTO : prizeDTOS) {
            if (prizeDTO.getInventory() > 0) {
                probs.put(prizeDTO.getId(), prizeDTO.getRate().doubleValue());
            }
        }
        if (probs.isEmpty()) {
            return successCreated(ResultCode.LOTTERY_ACTIVITY_FINISHED);
        }
        Object prizeId = LotteryHelper.draw(probs, 2);
        if (prizeId == null) {
            drawLotteryActivityRecordService.notLottery(id);
            //查询随机奖品广告图片，未中奖返回
            Result<String> adImgResult = drawLotteryActivityPrizeService.getRandPrizeAdImg(recordDTOResult.getModel().getDrawLotteryActivityId());
            DrawLotteryActivityPrizeDetailDTO detailDTO = new DrawLotteryActivityPrizeDetailDTO();
            detailDTO.setAdImgPath(adImgResult.getModel());
            return successCreated(detailDTO);
        }
        Result result = drawLotteryActivityRecordService.winLottery(id, (Long) prizeId);
        if (!isSuccess(result)) {
            drawLotteryActivityRecordService.notLottery(id);
            //查询随机奖品广告图片，未中奖返回
            Result<String> adImgResult = drawLotteryActivityPrizeService.getRandPrizeAdImg(recordDTOResult.getModel().getDrawLotteryActivityId());
            DrawLotteryActivityPrizeDetailDTO detailDTO = new DrawLotteryActivityPrizeDetailDTO();
            detailDTO.setAdImgPath(adImgResult.getModel());
            return successCreated(detailDTO);
        }
        Result<DrawLotteryActivityPrizeDetailDTO> prizeDetailDTOResult = drawLotteryActivityPrizeService.getPrizeDetail((Long) prizeId);
        return successCreated(prizeDetailDTOResult);
    }



    @Audit(date = "2018-05-15", reviewer = "孙林青")
    @SuppressWarnings("unchecked")
	@ApiOperation(value = "查询中奖列表记录", notes = "查询中奖列表记录。 (张荣成)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "listDrawLotteryActivityRecord", method = RequestMethod.GET)
    public Result<Page<DrawLotteryActivityRecordPageDTO>> listDrawLotteryActivityRecord(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
    		@ModelAttribute DrawLotteryActivityRecordQuery query) {
        Result<Page<DrawLotteryActivityRecordPageDTO>> recordDTOResult = drawLotteryActivityRecordService.listDrawLotteryActivityRecord(UserUtil.getCurrentUserNum(getRequest()), query);
        return successGet(recordDTOResult);
    }

}
