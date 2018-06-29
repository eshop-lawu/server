package com.lawu.eshop.member.ws.processor.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.cache.constants.GameConfigStatusEnum;
import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.cache.dto.CheckPointsDeductionStatusDTO;
import com.lawu.eshop.cache.dto.GameMindConfigDTO;
import com.lawu.eshop.cache.dto.GameRoomDetailsDTO;
import com.lawu.eshop.cache.dto.MindPkSubmitDTO;
import com.lawu.eshop.cache.dto.VerifyAnswerDTO;
import com.lawu.eshop.cache.param.ReadyStartGameParam;
import com.lawu.eshop.cache.param.VerifyAnswerParam;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.game.constants.AttendTypeEnum;
import com.lawu.eshop.game.dto.MindPkAbandonDTO;
import com.lawu.eshop.game.dto.MindPkCheckDTO;
import com.lawu.eshop.game.dto.MindPkOnlineDTO;
import com.lawu.eshop.game.dto.MindPkReadyDTO;
import com.lawu.eshop.game.dto.MindPkStartDTO;
import com.lawu.eshop.game.dto.MindPkSubmitSingleDTO;
import com.lawu.eshop.game.param.JoinGameRoomParam;
import com.lawu.eshop.game.param.MindPkOnlineParam;
import com.lawu.eshop.game.param.MindPkReadyParam;
import com.lawu.eshop.game.param.MindPkRejectParam;
import com.lawu.eshop.game.param.MindPkStartParam;
import com.lawu.eshop.game.param.MindPkSubmitParam;
import com.lawu.eshop.member.ws.channel.ChannelManager;
import com.lawu.eshop.member.ws.event.EventPublisher;
import com.lawu.eshop.member.ws.processor.GameRevProcessor;
import com.lawu.eshop.member.ws.processor.SendProcessor;
import com.lawu.eshop.member.ws.service.GameConfigCacheService;
import com.lawu.eshop.member.ws.service.GameMindRecordService;
import com.lawu.eshop.member.ws.service.GameRoomExtendService;
import com.lawu.eshop.member.ws.util.ResultUtil;
import com.lawu.framework.web.Result;

/**
 * @author Leach
 * @date 2018/3/13
 */
@Service(value = "MindPkRevProcessorImpl")
public class MindPkRevProcessorImpl implements GameRevProcessor {
    
    private static final Logger logger = LoggerFactory.getLogger(MindPkRevProcessorImpl.class);
    
    /**
     * 主动推送服务
     */
    @Autowired
    private SendProcessor sendProcessor;
    
    @Autowired
    private GameMindRecordService gameMindRecordService;
    
    @Autowired
    private GameRoomExtendService gameRoomExtendService;

    @Autowired
    private GameConfigCacheService gameConfigCacheService;

    @Autowired
    private EventPublisher eventPublisher;
    
    @Override
    public Result<MindPkOnlineDTO> online(String userNum, Object param) {
        MindPkOnlineParam mindPkOnlineParam = (MindPkOnlineParam) param;
        Result<MindPkOnlineDTO> result = null;
        if (AttendTypeEnum.MANYPEOPLE.equals(mindPkOnlineParam.getAttendType())) {
            // 房间内上线操作
            JoinGameRoomParam joinGameRoomParam = new JoinGameRoomParam();
            joinGameRoomParam.setGameType(GameTypeEnum.MIND);
            joinGameRoomParam.setUserNum(userNum);
            result = gameRoomExtendService.online(joinGameRoomParam);
        } else if (AttendTypeEnum.RANDOM.equals(mindPkOnlineParam.getAttendType())) {
            // 在缓存中记录当前上线的用户, 并且返回当前组中的所有用户
            ReadyStartGameParam readyStartGameParam = new ReadyStartGameParam();
            readyStartGameParam.setAttendNum(ChannelManager.getGroupNum(userNum));
            readyStartGameParam.setIsHomeowner(mindPkOnlineParam.getIsHomeowner());
            readyStartGameParam.setUserNum(userNum);
            result = gameMindRecordService.readyStartGame(readyStartGameParam);
        }
        if (result != null) {
            if (ResultUtil.isSuccess(result)) {
                return ResultUtil.get(ResultCode.GAME_WS_USER_ONLINE, result.getModel());
            } else {
                // 如果异常只发送给当前用户
                sendProcessor.send(ResultUtil.get(result.getRet(), result.getMsg()), userNum);
            }
        }
        return null;
    }

    @Override
    public Result<MindPkReadyDTO> ready(String userNum, Object param) {
        MindPkReadyParam readyParam = (MindPkReadyParam) param;
        Result<MindPkReadyDTO> result = gameRoomExtendService.ready(userNum, readyParam, GameTypeEnum.MIND);
        if (ResultUtil.isSuccess(result)) {
            return ResultUtil.get(ResultCode.GAME_WS_USER_READY, result.getModel());
        } else {
            // 如果异常只发送给当前用户
            sendProcessor.send(ResultUtil.get(result.getRet(), result.getMsg()), userNum);
        }
        return null;
    }

    @Override
    public Result<MindPkStartDTO> start(String userNum, Object param) {
        Result<GameMindConfigDTO> configDTOResult = gameConfigCacheService.getGameMindConfig();
        if (configDTOResult == null || configDTOResult.getModel() == null
                || GameConfigStatusEnum.DISABLE == configDTOResult.getModel().getStatusEnum()) {
            return ResultUtil.get(ResultCode.GAME_SETTING_DISABLE, configDTOResult.getModel().getForbiddenRemark());
        }
        MindPkStartParam mindPkStartParam = (MindPkStartParam) param;
        Result<MindPkStartDTO> result = gameMindRecordService.participateGame(userNum, mindPkStartParam);
        if (ResultUtil.isSuccess(result)) {
            return ResultUtil.get(ResultCode.GAME_WS_USER_START, result.getModel());
        } else {
            // 如果异常只发送给当前用户
            sendProcessor.send(ResultUtil.get(result.getRet(), result.getMsg()), userNum);
        }
        return null;
    }

    @Override
    public Result<MindPkCheckDTO> check(String userNum, Object param) {
        Result<CheckPointsDeductionStatusDTO> result = gameMindRecordService.checkPointsDeductionStatus(userNum);
        if (ResultUtil.isSuccess(result)) {
            CheckPointsDeductionStatusDTO checkPointsDeductionStatusDTO = result.getModel();
            MindPkCheckDTO model = new MindPkCheckDTO();
            model.setIsSuccess(checkPointsDeductionStatusDTO.getIsAllComplete());
            /*
             *  如果该用户积分没扣除成功, 发送离线的消息给组员
             *  还需要判断扣除失败的用户是不是房主, 如果是房主需要解散房间
             */
            if ((checkPointsDeductionStatusDTO.getIsDeductedSuccess() != null && !checkPointsDeductionStatusDTO.getIsDeductedSuccess())) {
                Result<GameRoomDetailsDTO> quitResult = gameMindRecordService.quit(userNum);
                if (ResultUtil.isSuccess(quitResult)) {
                    GameRoomDetailsDTO quitModel = quitResult.getModel();
                    MindPkAbandonDTO mindPkAbandonDTO = new MindPkAbandonDTO();
                    mindPkAbandonDTO.setExitUserNum(userNum);
                    mindPkAbandonDTO.setIsDissolution(quitModel.getIsDissolution());
                    sendProcessor.sendToGroupByUser(ResultUtil.get(ResultCode.GAME_WS_USER_OFFLINE, mindPkAbandonDTO), userNum);
                } else {
                    // 如果异常只发送给当前用户
                    sendProcessor.send(ResultUtil.get(quitResult.getRet(), quitResult.getMsg()), userNum);
                }
            }
            if (model.getIsSuccess()) {
                //玩游戏完成瑞奇岛动力任务
                eventPublisher.publishRichPowerTaskEvent(userNum, GameTypeEnum.MIND);
                return ResultUtil.get(ResultCode.GAME_WS_USER_CHECK_DONE, model);
            }
        } else {
            // 如果异常只发送给当前用户
            sendProcessor.send(ResultUtil.get(result.getRet(), result.getMsg()), userNum);
        }
        return null;
    }

    @Override
    public Result<MindPkSubmitDTO> submit(String userNum, Object param) {
        MindPkSubmitParam mindPkSubmitParam = (MindPkSubmitParam) param;
        VerifyAnswerParam verifyAnswerParam = new VerifyAnswerParam();
        verifyAnswerParam.setUserNum(userNum);
        verifyAnswerParam.setQuestionId(mindPkSubmitParam.getQuestionId());
        verifyAnswerParam.setRightAnswer(mindPkSubmitParam.getRightAnswer());
        submit(verifyAnswerParam);
        return null;
    }

    @Override
    public Result<MindPkAbandonDTO> reject(String userNum, Object param) {
        MindPkRejectParam gameExitKickParam = (MindPkRejectParam) param;
        Result<GameRoomDetailsDTO> result = gameRoomExtendService.exitGameRoom(userNum, gameExitKickParam.getKickedUserNum(), GameTypeEnum.MIND);
        if (ResultUtil.isSuccess(result)) {
            MindPkAbandonDTO model = new MindPkAbandonDTO();
            model.setExitUserNum(gameExitKickParam.getKickedUserNum());
            model.setIsDissolution(false);
            return ResultUtil.get(ResultCode.GAME_WS_USER_OFFLINE, model);
        } else {
            // 如果异常只发送给当前用户
            sendProcessor.send(ResultUtil.get(result.getRet(), result.getMsg()), userNum);
        }
        return null;
    }

    @Override
    public Result<MindPkAbandonDTO> abandon(String userNum, Object param) {
        offlineCommnon(userNum);
        return null;
    }

    @Override
    public Result<MindPkSubmitDTO> timeout(String userNum, Object param) {
        MindPkSubmitParam mindPkSubmitParam = (MindPkSubmitParam) param;
        mindPkSubmitParam.setRightAnswer(-1);
        submit(userNum, mindPkSubmitParam);
        return null;
    }

    @Override
    public Result<MindPkAbandonDTO> offline(String userNum) {
        offlineCommnon(userNum);
        return null;
    }
    
    /**
     * 提交答案公用方法
     * 
     * @author jiangxinjun
     * @createDate 2018年3月28日
     * @updateDate 2018年3月28日
     */
    private void submit(VerifyAnswerParam verifyAnswerParam) {
        Result<VerifyAnswerDTO> result = gameMindRecordService.verifyAnswer(verifyAnswerParam);
        if (ResultUtil.isSuccess(result)) {
            VerifyAnswerDTO resultModel = result.getModel();
            // 单人成绩, 发送给单人
            MindPkSubmitSingleDTO mindPkSubmitSingleDTO = new MindPkSubmitSingleDTO();
            mindPkSubmitSingleDTO.setAccumulatedGameScore(resultModel.getAccumulatedGameScore());
            mindPkSubmitSingleDTO.setFlag(resultModel.getFlag());
            mindPkSubmitSingleDTO.setRightAnswer(resultModel.getRightAnswer());
            try {
                sendProcessor.sendSync(ResultUtil.get(ResultCode.GAME_WS_USER_SUBMIT_SINGLE, mindPkSubmitSingleDTO), verifyAnswerParam.getUserNum());
            } catch (InterruptedException e) {
                logger.error("发送单人同步消息失败");
            }
            MindPkSubmitDTO model = new MindPkSubmitDTO();
            model.setSelectedAnswer(verifyAnswerParam.getRightAnswer());
            model.setAccumulatedGameScore(resultModel.getAccumulatedGameScore());
            model.setCurrentSubmitUserNum(verifyAnswerParam.getUserNum());
            model.setRanks(resultModel.getRanks());
            model.setSubmitScores(resultModel.getSubmitScores());
            if (resultModel.getIsLastAnswer()) {
                // 如果是最后一题, 而且是最后一个人提交, 提示答题已经结束, 返回排行信息
                if (resultModel.getIsLastQuestion()) {
                    sendProcessor.sendToGroupByUser(ResultUtil.get(ResultCode.GAME_WS_USER_SUBMIT_DONE, model), verifyAnswerParam.getUserNum());
                    return;
                }
                // 如果是这道题最后一个人回答, 提示所有人可以开始下一题了
                sendProcessor.sendToGroupByUser(ResultUtil.get(ResultCode.GAME_WS_USER_SUBMIT_MULTI, model), verifyAnswerParam.getUserNum());
                return;
            }
            sendProcessor.sendToGroupByUser(ResultUtil.get(ResultCode.GAME_WS_USER_SUBMIT_OTHER, model), verifyAnswerParam.getUserNum());
            return;
        } else {
            // 如果异常只发送给当前用户
            sendProcessor.send(ResultUtil.get(result.getRet(), result.getMsg()), verifyAnswerParam.getUserNum());
        }
    }
    
    /**
     * 离线公用方法
     * 
     * @author jiangxinjun
     * @createDate 2018年3月28日
     * @updateDate 2018年3月28日
     */
    private void offlineCommnon(String userNum) {
        Result<GameRoomDetailsDTO> result = gameMindRecordService.quit(userNum);
        if (ResultUtil.isSuccess(result)) {
            GameRoomDetailsDTO gameRoomDetailsDTO = result.getModel();
            MindPkAbandonDTO model = new MindPkAbandonDTO();
            model.setExitUserNum(userNum);
            if (gameRoomDetailsDTO != null) {
                model.setIsDissolution(gameRoomDetailsDTO.getIsDissolution());
            }
            sendProcessor.sendToGroupByUser(ResultUtil.get(ResultCode.GAME_WS_USER_OFFLINE, model), userNum);
            // 触发提交答题, 返回答题信息
            VerifyAnswerParam verifyAnswerParam = new VerifyAnswerParam();
            verifyAnswerParam.setQuestionId(1L);
            verifyAnswerParam.setRightAnswer(-1);
            verifyAnswerParam.setUserNum(userNum);
            submit(verifyAnswerParam);
        } else {
            if (result != null) {
                // 如果异常只发送给当前用户
                sendProcessor.send(ResultUtil.get(result.getRet(), result.getMsg()), userNum);
            }
        }
    }
    
    @Override
    public boolean isValid(String userNum) {
        return false;
    }
}
