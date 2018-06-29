package com.lawu.eshop.member.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.activity.param.RichPowerTaskRecordParam;
import com.lawu.eshop.cache.constants.GameAttendRecordStatusEnum;
import com.lawu.eshop.cache.constants.GameConfigStatusEnum;
import com.lawu.eshop.cache.constants.PowerTaskTypeEnum;
import com.lawu.eshop.cache.dto.GameMindConfigDTO;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.game.constants.AttendTypeEnum;
import com.lawu.eshop.game.dto.GameAttendStatusDTO;
import com.lawu.eshop.game.dto.GameMindQuestionResultDTO;
import com.lawu.eshop.game.dto.GameMindResultDTO;
import com.lawu.eshop.game.param.GameMindAnswerParam;
import com.lawu.eshop.game.param.GameMindAnswerQuestionParam;
import com.lawu.eshop.game.param.MindAttendParam;
import com.lawu.eshop.member.api.event.EventPublisher;
import com.lawu.eshop.member.api.service.GameConfigCacheService;
import com.lawu.eshop.member.api.service.GameMindAttendRecordService;
import com.lawu.eshop.member.api.service.GameMindCacheService;
import com.lawu.eshop.member.api.service.GameMindQuestionService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * @author zhangyong
 * @date 2018/3/12.
 */
@Api(tags = "gameMind")
@RestController
@RequestMapping(value = "gameMind/")
public class GameMindController extends BaseController {

    @Autowired
    private GameConfigCacheService gameConfigCacheService;

    @Autowired
    private GameMindQuestionService gameMindQuestionService;

    @Autowired
    private GameMindAttendRecordService gameMindAttendRecordService;

    @Autowired
    private GameMindCacheService gameMindCacheService;

    @Autowired
    private EventPublisher eventPublisher;


    @Audit(date = "2018-03-15", reviewer = "孙林青")
    @Authorization
    @ApiOperation(value = "单机开始游戏获取题目", notes = "单机开始游戏获取题目（章勇）[1000,10001,10015]", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "startGame", method = RequestMethod.GET)
    public Result<GameMindQuestionResultDTO> startGame(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        Result<GameMindConfigDTO> result = gameConfigCacheService.getGameMindConfig();
        GameMindConfigDTO configDTO = result.getModel();
        if(configDTO == null ||  GameConfigStatusEnum.DISABLE == configDTO.getStatusEnum()){
            return successGet(ResultCode.GAME_SETTING_DISABLE, configDTO == null ? "未设置头脑配置" : configDTO.getForbiddenRemark());
        }
        MindAttendParam param = new MindAttendParam();
        param.setUserNum(userNum);
        param.setAttendPoint(configDTO.getAttendPoint());
        param.setAttendStar(configDTO.getAwardStar());
        param.setAttendType(AttendTypeEnum.STANDALONE);
        Result<GameMindQuestionResultDTO> resultDTOResult= gameMindQuestionService.getMindQuestionList(param);

        //玩游戏完成瑞奇岛动力任务
        RichPowerTaskRecordParam taskRecordParam = new RichPowerTaskRecordParam();
        taskRecordParam.setMemberNum(userNum);
        taskRecordParam.setType(PowerTaskTypeEnum.GAME);
        eventPublisher.publishRichPowerTaskEvent(taskRecordParam);
        return successGet(resultDTOResult);
    }


    @Audit(date = "2018-03-15", reviewer = "孙林青")
    @Authorization
    @ApiOperation(value = "查询参与游戏的状态", notes = "查询参与游戏的状态（章勇）[1000,10002]", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getGameAttendStatus/{attendId}", method = RequestMethod.GET)
    public Result<GameAttendStatusDTO> getGameAttendStatus(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                           @PathVariable("attendId") Long attendId) {
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        return gameMindAttendRecordService.getGameAttendStatus(attendId, userNum);
    }

    @Audit(date = "2018-03-15", reviewer = "孙林青")
    @Authorization
    @ApiOperation(value = "答题返回答题结果", notes = "答题返回答题结果（章勇）[1000,10005]", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "answerQuestion", method = RequestMethod.POST)
    public Result<GameMindResultDTO> answerQuestion(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                    @ModelAttribute GameMindAnswerParam param) {

        String userNum = UserUtil.getCurrentUserNum(getRequest());
        Result<String> questionIdsResult = gameMindCacheService.getMindQuestions(param.getAttendId().toString());
        //获取开始时间
        if(questionIdsResult.getModel().isEmpty()){
            return successCreated(ResultCode.GAME_MIND_ANSWER_QUESTION_ERROR);
        }
        Result<Long> startTimeResult = gameMindCacheService.getLastGameTime(param.getAttendId());
        if (startTimeResult.getModel() == null || startTimeResult.getModel() == 0L) {
            return successCreated(ResultCode.GAME_MIND_ANSWER_QUESTION_ERROR);
        }
        Long nowTime = System.currentTimeMillis();
        Long useTime = (nowTime - startTimeResult.getModel()) / 1000;//用时多少秒
        GameMindAnswerQuestionParam questionParam = new GameMindAnswerQuestionParam();
        questionParam.setAttendId(param.getAttendId());
        questionParam.setQuestionId(param.getQuestionId());
        questionParam.setQuestionIds(questionIdsResult.getModel());
        questionParam.setRightAnswer(param.getRightAnswer());
        questionParam.setUserTime(useTime.intValue());
        questionParam.setUserNum(userNum);
       // gameMindCacheService.setGameTime(param.getAttendId(),System.currentTimeMillis());
        return gameMindAttendRecordService.answerQuestion(questionParam);
    }

    @Audit(date = "2018-03-17", reviewer = "章勇")
    @Authorization
    @ApiOperation(value = "游戏开始计时", notes = "游戏开始计时（章勇）[1000,10002]", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "beginKeepTime", method = RequestMethod.POST)
    public Result beginKeepTime(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                @RequestParam("attendId") Long attendId) {
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        Result<GameAttendStatusDTO> resultDTOResult = gameMindAttendRecordService.getGameAttendStatus(attendId, userNum);
        if (resultDTOResult.getModel() == null || resultDTOResult.getModel().getAttendStatus() != GameAttendRecordStatusEnum.ATTENDSUCCESS) {
            return successCreated(ResultCode.GAME_MIND_ATTEND_FAIL);
        }
        gameMindCacheService.setGameTime(attendId, System.currentTimeMillis());
        return successCreated();
    }
    
}
