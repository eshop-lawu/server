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
import com.lawu.eshop.activity.param.RichPowerTaskRecordParam;
import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.cache.constants.PowerTaskTypeEnum;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.game.constants.GameDialRecordStatusEnum;
import com.lawu.eshop.game.constants.GameDialStatusEnum;
import com.lawu.eshop.game.dto.GameDialDTO;
import com.lawu.eshop.game.dto.GameDialPrizeDetailDTO;
import com.lawu.eshop.game.dto.GameDialPrizeLotteryDTO;
import com.lawu.eshop.game.dto.GameDialRecordAttendDTO;
import com.lawu.eshop.game.dto.GameDialRecordInfoDTO;
import com.lawu.eshop.game.dto.TakePartLotteryInfoDTO;
import com.lawu.eshop.game.param.TakeLotteryParam;
import com.lawu.eshop.game.param.TakePartLotteryParam;
import com.lawu.eshop.game.query.GameDialRecordQuery;
import com.lawu.eshop.game.query.GameDialRecordUserQuery;
import com.lawu.eshop.member.api.event.EventPublisher;
import com.lawu.eshop.member.api.service.GameAccountService;
import com.lawu.eshop.member.api.service.GameDialPrizeService;
import com.lawu.eshop.member.api.service.GameDialRecordService;
import com.lawu.eshop.member.api.service.GameDialService;
import com.lawu.eshop.member.api.service.PropertyInfoService;
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
 * @date 2018/3/16.
 */
@Api(tags = "gameDialRecord")
@RestController
@RequestMapping(value = "gameDialRecord/")
public class GameDialRecordController extends BaseController {

    @Autowired
    private GameDialRecordService gameDialRecordService;

    @Autowired
    private GameDialService gameDialService;

    @Autowired
    private GameAccountService gameAccountService;

    @Autowired
    private PropertyInfoService propertyInfoService;

    @Autowired
    private GameDialPrizeService gameDialPrizeService;

    @Autowired
    private EventPublisher eventPublisher;

    @Audit(date = "2018-03-17", reviewer = "杨清华")
    @ApiOperation(value = "中奖记录", notes = "中奖记录。(梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "listGameDialRecord", method = RequestMethod.GET)
    public Result<Page<GameDialRecordInfoDTO>> listGameDialRecord(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                                  @ModelAttribute GameDialRecordQuery query) {
        GameDialRecordUserQuery userQuery = new GameDialRecordUserQuery();
        userQuery.setUserNum(UserUtil.getCurrentUserNum(getRequest()));
        userQuery.setGameDialId(query.getGameDialId());
        userQuery.setCurrentPage(query.getCurrentPage());
        userQuery.setPageSize(query.getPageSize());
        Result<Page<GameDialRecordInfoDTO>> result = gameDialRecordService.listGameDialRecord(userQuery);
        return successGet(result);
    }

    @Audit(date = "2018-03-17", reviewer = "杨清华")
    @ApiOperation(value = "免费抽奖", notes = "免费抽奖。[1002|1004|9100|9102|10011] (梅述全)", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "takePartLottery/{gameDialId}", method = RequestMethod.POST)
    public Result<TakePartLotteryInfoDTO> takePartLottery(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                          @PathVariable @ApiParam(required = true, value = "抽奖活动ID") Long gameDialId) {
        if (gameDialId == null || gameDialId <= 0) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY);
        }
        Long userId = UserUtil.getCurrentUserId(getRequest());
        String account = UserUtil.getCurrentAccount(getRequest());
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        Result<TakePartLotteryInfoDTO> lotteryInfoDTOResult = gameDialRecordService.getTakePartLottery(gameDialId, userNum, GameDialRecordStatusEnum.TAKE_PART_LOTTERY);
        if (lotteryInfoDTOResult.getModel().getId() > 0) {
            return successCreated(lotteryInfoDTOResult);
        }

        Result<GameDialDTO> dialDTOResult = gameDialService.getGameDialById(gameDialId);
        if (dialDTOResult.getModel() == null) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND);
        }
        if (dialDTOResult.getModel().getStatus().equals(GameDialStatusEnum.DISABLED)) {
            return successCreated(ResultCode.GAME_DIAL_DISABLED);
        }
        Result<Boolean> freeCountResult = gameAccountService.isFreeCount(userNum, GameTypeEnum.DIAL);
        if (!freeCountResult.getModel()) {
            return successCreated(ResultCode.FREE_LOTTERY_COUNT_OVER);
        }

        TakePartLotteryParam param = new TakePartLotteryParam();
        param.setUserId(userId);
        param.setUserNum(userNum);
        param.setUserAccount(account);
        param.setGameDialId(gameDialId);
        param.setPayPoint(BigDecimal.ZERO);
        param.setStatusEnum(GameDialRecordStatusEnum.TAKE_PART_LOTTERY);
        Result<TakePartLotteryInfoDTO> result = gameDialRecordService.saveGameDialRecord(param);
        return successCreated(result);
    }

    @Audit(date = "2018-03-17", reviewer = "杨清华")
    @ApiOperation(value = "积分抽奖", notes = "积分抽奖。[1002|1004|6002|6003|6024|6010|9106|10011] (梅述全)", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "takePartLotteryByPay/{gameDialId}", method = RequestMethod.POST)
    public Result<TakePartLotteryInfoDTO> takePartLotteryByPay(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                               @PathVariable @ApiParam(required = true, value = "抽奖活动ID") Long gameDialId) {
        if (gameDialId == null || gameDialId <= 0) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY);
        }
        Long userId = UserUtil.getCurrentUserId(getRequest());
        String account = UserUtil.getCurrentAccount(getRequest());
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        Result<TakePartLotteryInfoDTO> lotteryInfoDTOResult = gameDialRecordService.getTakePartLottery(gameDialId, userNum, GameDialRecordStatusEnum.POINT_DEDUCT_ING);
        if (lotteryInfoDTOResult.getModel().getId() > 0) {
            return successCreated(ResultCode.LOTTERY_RECORD_HANDLE);
        }
        lotteryInfoDTOResult = gameDialRecordService.getTakePartLottery(gameDialId, userNum, GameDialRecordStatusEnum.TAKE_PART_LOTTERY);
        if (lotteryInfoDTOResult.getModel().getId() > 0) {
            return successCreated(lotteryInfoDTOResult);
        }

        Result<GameDialDTO> dialDTOResult = gameDialService.getGameDialById(gameDialId);
        if (dialDTOResult.getModel() == null) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND);
        }
        if (dialDTOResult.getModel().getStatus().equals(GameDialStatusEnum.DISABLED)) {
            return successCreated(ResultCode.GAME_DIAL_DISABLED);
        }
        Result validatePointResult = propertyInfoService.validatePoint(userNum, String.valueOf(dialDTOResult.getModel().getPoint()));
        if (!isSuccess(validatePointResult)) {
            return successCreated(validatePointResult.getRet());
        }

        TakePartLotteryParam param = new TakePartLotteryParam();
        param.setUserId(userId);
        param.setUserNum(userNum);
        param.setUserAccount(account);
        param.setGameDialId(gameDialId);
        param.setPayPoint(BigDecimal.valueOf(dialDTOResult.getModel().getPoint()));
        param.setStatusEnum(GameDialRecordStatusEnum.POINT_DEDUCT_ING);
        Result<TakePartLotteryInfoDTO> result = gameDialRecordService.saveGameDialRecord(param);
        return successCreated(result);
    }

    @Audit(date = "2018-03-17", reviewer = "杨清华")
    @ApiOperation(value = "查询抽奖记录状态", notes = "查询抽奖记录状态。[1002] (梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "getGameDialRecordStatus/{id}", method = RequestMethod.GET)
    public Result<GameDialRecordAttendDTO> getGameDialRecordStatus(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                                   @PathVariable @ApiParam(required = true, value = "抽奖记录ID") Long id) {
        Result<GameDialRecordAttendDTO> recordStatusDTOResult = gameDialRecordService.getGameDialRecordAttend(id);
        return successGet(recordStatusDTOResult);
    }

    @Audit(date = "2018-03-17", reviewer = "杨清华")
    @ApiOperation(value = "填写收货地址", notes = "填写收货地址。[1002|9108] (梅述全)", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "takeLottery/{id}", method = RequestMethod.PUT)
    public Result takeLottery(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                              @ModelAttribute TakeLotteryParam param) {
        Result<GameDialRecordAttendDTO> recordStatusDTOResult = gameDialRecordService.getGameDialRecordAttend(param.getId());
        if (!isSuccess(recordStatusDTOResult)) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND);
        }
        if (!GameDialRecordStatusEnum.GET_LOTTERY.equals(recordStatusDTOResult.getModel().getStatusEnum())) {
            return successCreated(ResultCode.LOTTERY_RECORD_HAVE_DEAL);
        }
        gameDialRecordService.takeLottery(param);
        return successCreated();
    }

    @Audit(date = "2018-03-17", reviewer = "杨清华")
    @ApiOperation(value = "抽奖", notes = "抽奖。[1002|9100|9106|9107|10011|10012] (梅述全)", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "openLottery/{id}", method = RequestMethod.PUT)
    public Result<GameDialPrizeDetailDTO> openLottery(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                      @PathVariable @ApiParam(required = true, value = "抽奖记录ID") Long id) {
        Result<GameDialRecordAttendDTO> recordStatusDTOResult = gameDialRecordService.getGameDialRecordAttend(id);
        if (!isSuccess(recordStatusDTOResult)) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND);
        }
        if (GameDialRecordStatusEnum.POINT_DEDUCT_ING.equals(recordStatusDTOResult.getModel().getStatusEnum())) {
            return successCreated(ResultCode.LOTTERY_RECORD_HANDLE);
        }
        if (GameDialRecordStatusEnum.POINT_DEDUCT_FAIL.equals(recordStatusDTOResult.getModel().getStatusEnum())) {
            return successCreated(ResultCode.TAKE_PART_LOTTERY_ERROR);
        }
        if (!GameDialRecordStatusEnum.TAKE_PART_LOTTERY.equals(recordStatusDTOResult.getModel().getStatusEnum())) {
            return successCreated(ResultCode.LOTTERY_RECORD_USED);
        }
        Result<GameDialDTO> dialDTOResult = gameDialService.getGameDialById(recordStatusDTOResult.getModel().getGameDialId());
        if (dialDTOResult.getModel() == null) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND);
        }
        if (dialDTOResult.getModel().getStatus().equals(GameDialStatusEnum.DISABLED)) {
            return successCreated(ResultCode.GAME_DIAL_DISABLED);
        }

        //查询抽奖活动奖品
        Result<List<GameDialPrizeLotteryDTO>> prizeDTOResult = gameDialPrizeService.listValidGameDialPrize(recordStatusDTOResult.getModel().getGameDialId());
        List<GameDialPrizeLotteryDTO> prizeDTOS = prizeDTOResult.getModel();
        if (prizeDTOS.isEmpty()) {
            gameDialRecordService.notLottery(id);
            return successCreated(ResultCode.GAME_DIAL_NOT_LOTTERY);
        }

        //玩游戏完成瑞奇岛动力任务
        RichPowerTaskRecordParam taskRecordParam = new RichPowerTaskRecordParam();
        taskRecordParam.setMemberNum(UserUtil.getCurrentUserNum(getRequest()));
        taskRecordParam.setType(PowerTaskTypeEnum.GAME);
        eventPublisher.publishRichPowerTaskEvent(taskRecordParam);

        Map<Object, Double> probs = new HashMap<>();
        for (GameDialPrizeLotteryDTO prizeDTO : prizeDTOS) {
            probs.put(prizeDTO.getId(), prizeDTO.getRate().doubleValue());
        }
        Object prizeId = LotteryHelper.draw(probs, 2);
        if (prizeId == null) {
            gameDialRecordService.notLottery(id);
            return successCreated(ResultCode.GAME_DIAL_NOT_LOTTERY);
        }
        Result result = gameDialRecordService.winLottery(id, (Long) prizeId);
        if (!isSuccess(result)) {
            gameDialRecordService.notLottery(id);
            return successCreated(ResultCode.GAME_DIAL_NOT_LOTTERY);
        }
        Result<GameDialPrizeDetailDTO> prizeDetailDTOResult = gameDialPrizeService.getLotteryPrizeInfo((Long) prizeId);
        return successCreated(prizeDetailDTOResult);
    }

}
