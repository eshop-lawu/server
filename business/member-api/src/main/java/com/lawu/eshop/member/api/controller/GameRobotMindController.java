package com.lawu.eshop.member.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.member.api.service.GameConfigCacheService;
import com.lawu.eshop.member.api.service.GameMindAttendRecordService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * @author zhangyong
 * @date 2018/5/14.
 */
@Api(tags = "gameRobotMind")
@RestController
@RequestMapping(value = "gameRobotMind/")
public class GameRobotMindController extends BaseController {

    @Autowired
    private GameConfigCacheService gameConfigCacheService;

    @Autowired
    private GameMindAttendRecordService gameMindAttendRecordService;

    @ApiOperation(value = "机器人开始游戏获取题目,初始化答题记录", notes = "机器人开始游戏获取题目,初始化答题记录（章勇）[1000]", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "startGame", method = RequestMethod.GET)
    public Result<String> robotStartGame(@RequestParam("roomNum") String roomNum) {
        return gameMindAttendRecordService.initRobotAttendRecordResult(roomNum);
        //获取头脑配置缓存
      /*  ParticipateGameMindParam participateGameMindParam = new ParticipateGameMindParam();
        participateGameMindParam.setAttendType(AttendTypeEnum.RANDOM);
        participateGameMindParam.setGroupNum(roomNum);
        Result<GameMindParticipateGameDTO> participateGameResult = gameMindAttendRecordService.participateGame(participateGameMindParam);
        if (!isSuccess(participateGameResult)) {
            return successGet(participateGameResult.getRet(), participateGameResult.getMsg());
        }
        String answers = participateGameResult.getModel().getRoBotAnswers();
        return successGet(answers);*/
    }
}
