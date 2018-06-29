package com.lawu.eshop.member.ws.processor.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.cache.constants.GameConfigStatusEnum;
import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.cache.dto.GamePuzzleConfigCacheDTO;
import com.lawu.eshop.cache.dto.GameRoomDetailsDTO;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.game.constants.AttendTypeEnum;
import com.lawu.eshop.game.dto.GameAttendSaveReturnDTO;
import com.lawu.eshop.game.dto.GamePuzzleAttendRecordDTO;
import com.lawu.eshop.game.dto.GamePuzzleGetPicReturnDTO;
import com.lawu.eshop.game.dto.GamePuzzleResultDTO;
import com.lawu.eshop.game.dto.MindPkOnlineDTO;
import com.lawu.eshop.game.dto.MindPkReadyDTO;
import com.lawu.eshop.game.dto.PuzzlePKRejectDTO;
import com.lawu.eshop.game.dto.PuzzlePkAbandonDTO;
import com.lawu.eshop.game.param.GamePuzzleCheckDeductionPointParam;
import com.lawu.eshop.game.param.GamePuzzleCheckParam;
import com.lawu.eshop.game.param.GamePuzzleValidSuccSrvParam;
import com.lawu.eshop.game.param.JoinGameRoomParam;
import com.lawu.eshop.game.param.MindPkReadyParam;
import com.lawu.eshop.game.param.PuzzlePKRejectParam;
import com.lawu.eshop.game.param.PuzzlePKStartParam;
import com.lawu.eshop.game.param.PuzzlePKSubmitParam;
import com.lawu.eshop.game.param.PuzzlePkAbandonParam;
import com.lawu.eshop.game.param.PuzzlePkOnlineParam;
import com.lawu.eshop.member.ws.channel.ChannelManager;
import com.lawu.eshop.member.ws.processor.GameRevProcessor;
import com.lawu.eshop.member.ws.processor.SendProcessor;
import com.lawu.eshop.member.ws.service.GameConfigCacheService;
import com.lawu.eshop.member.ws.service.GamePuzzleRecordService;
import com.lawu.eshop.member.ws.service.GameRoomExtendService;
import com.lawu.eshop.member.ws.util.ResultUtil;
import com.lawu.framework.web.Result;

/**
 * @author Leach
 * @date 2018/3/13
 */
@Service(value = "PuzzleRevProcessorImpl")
public class PuzzleRevProcessorImpl implements GameRevProcessor {

    @Autowired
    private SendProcessor sendProcessor;

    @Autowired
    private GameRoomExtendService gameRoomExtendService;
    
    @Autowired
    private GamePuzzleRecordService gamePuzzleRecordService;

    @Autowired
    private GameConfigCacheService gameConfigCacheService;

    /**
     * 上线
     * @param userNum
     * @param param
     * @return
     */
    @Override
    public Result online(String userNum, Object param) {
        PuzzlePkOnlineParam puzzlePkOnlineParam = (PuzzlePkOnlineParam) param;
        String groupNum = ChannelManager.getGroupNum(userNum);
        Result<MindPkOnlineDTO> result = null;
        if (AttendTypeEnum.MANYPEOPLE.equals(puzzlePkOnlineParam.getAttendType())) {//多人
            // 房间内上线操作
            JoinGameRoomParam joinGameRoomParam = new JoinGameRoomParam();
            joinGameRoomParam.setGameType(GameTypeEnum.PUZZLE);
            joinGameRoomParam.setUserNum(userNum);
            joinGameRoomParam.setRoomNum(groupNum);
            result = gameRoomExtendService.online(joinGameRoomParam);
        } else if (AttendTypeEnum.RANDOM.equals(puzzlePkOnlineParam.getAttendType())) {//随机
            // 在缓存中记录当前上线的用户, 并且返回当前组中的所有用户
            result = gamePuzzleRecordService.readyPuzzleStartGame(userNum, groupNum);
        }
        if (ResultUtil.isSuccess(result)) {
            return ResultUtil.get(ResultCode.GAME_WS_USER_ONLINE, result.getModel());
        } else {
            // 如果异常只发送给当前用户
            sendProcessor.send(ResultUtil.get(result.getRet(), result.getMsg()), userNum);
        }
        return null;
    }

    /**
     * 准备
     * @param userNum
     * @param param
     * @return
     */
    @Override
    public Result<MindPkReadyDTO> ready(String userNum, Object param) {
        MindPkReadyParam readyParam = (MindPkReadyParam) param;
        Result<MindPkReadyDTO> result = gameRoomExtendService.ready(userNum, readyParam, GameTypeEnum.PUZZLE);
        if (ResultUtil.isSuccess(result)) {
            return ResultUtil.get(ResultCode.GAME_WS_USER_READY, result.getModel());
        } else {
            // 如果异常只发送给当前用户
            sendProcessor.send(ResultUtil.get(result.getRet(), result.getMsg()), userNum);
        }
        return null;
    }

    /**
     * 开始
     * @param userNum
     * @param param
     * @return
     */
    @Override
    public Result<GameAttendSaveReturnDTO> start(String userNum, Object param) {//初始化记录并扣除积分
        Result<GamePuzzleConfigCacheDTO> gamePuzzleConfig = gameConfigCacheService.getGamePuzzleConfig();
        if (gamePuzzleConfig == null || gamePuzzleConfig.getModel() == null
                || GameConfigStatusEnum.DISABLE == gamePuzzleConfig.getModel().getStatusEnum()) {
            return ResultUtil.get(ResultCode.GAME_SETTING_DISABLE, gamePuzzleConfig.getModel().getForbiddenRemark());
        }
    	PuzzlePKStartParam gameParam =(PuzzlePKStartParam)param;
        return gamePuzzleRecordService.startGame(userNum,gameParam);
    }

    /**
     * 检查积分扣除情况
     * @param userNum
     * @param param
     * @return
     */
    @Override
    public Result<GamePuzzleGetPicReturnDTO> check(String userNum, Object param) {
        GamePuzzleCheckParam ckParam =(GamePuzzleCheckParam)param;
        //Result rl = gameCommonCacheService.incrementAndGet(ckParam.getAttendNum());
        GamePuzzleCheckDeductionPointParam checkParam =new GamePuzzleCheckDeductionPointParam();
        checkParam.setUserNum(userNum);
        checkParam.setAttendNum(ckParam.getAttendNum());
        checkParam.setAttendType(ckParam.getAttendType());
        Result<GamePuzzleGetPicReturnDTO> result =gamePuzzleRecordService.checkDeductionPointSucc(checkParam);
        if(result.getRet()==ResultCode.GAME_WS_USER_CHECK_NOTDONE){
            return null;
        }
        return result;
    }

    /**
     * 提交
     * @param userNum
     * @param param
     * @return
     */
    @Override
    public Result<GamePuzzleResultDTO> submit(String userNum, Object param) {
    	PuzzlePKSubmitParam puzzleParam =(PuzzlePKSubmitParam)param;
    	GamePuzzleValidSuccSrvParam validParam =new GamePuzzleValidSuccSrvParam();
    	validParam.setUserNum(userNum);
    	validParam.setAttendNum(puzzleParam.getAttendNum());
    	validParam.setGameId(puzzleParam.getGameId());
    	validParam.setPicNum(puzzleParam.getPicNum());
        Result<GamePuzzleResultDTO> result = gamePuzzleRecordService.checkPuzzlePicStatus(validParam);
        if(result.getModel()==null){
            return null;
        }
        if(result.getModel().getLastCount()<=0){
            //游戏结束清楚缓存
            gamePuzzleRecordService.removeStartCacheData(validParam);
            return ResultUtil.get(ResultCode.GAME_WS_USER_SUBMIT_DONE, result.getModel());
        }
        return ResultUtil.get(ResultCode.GAME_WS_USER_SUBMIT_SINGLE,  result.getModel());
    }

    /**
     * 踢人
     * @param userNum
     * @param param
     * @return
     */
    @Override
    public Result<PuzzlePKRejectDTO> reject(String userNum, Object param) {
        PuzzlePKRejectParam puzzlePKRejectParam =(PuzzlePKRejectParam)param;
        Result<GameRoomDetailsDTO> result = gameRoomExtendService.exitGameRoom(userNum, puzzlePKRejectParam.getKickedUserNum(), GameTypeEnum.PUZZLE);
        if(ResultUtil.isSuccess(result)){
            // 发送踢出消息给被踢出用户
            PuzzlePKRejectDTO model =new PuzzlePKRejectDTO();
            model.setExitUserNum(puzzlePKRejectParam.getKickedUserNum());
            model.setIsDissolution(false);
            return ResultUtil.get(ResultCode.GAME_WS_USER_OFFLINE, model);
        }else{
            // 如果异常只发送给当前用户
            sendProcessor.send(ResultUtil.get(result.getRet(), result.getMsg()), userNum);
        }
        return null;
    }

    /**
     * 放弃
     */
    @Override
    public Result<PuzzlePkAbandonDTO> abandon(String userNum, Object param) {
        PuzzlePkAbandonParam puzzlePkAbandonParam =(PuzzlePkAbandonParam)param;
        gamePuzzleRecordService.removeMyPuzzleCacheData(userNum);//清除自己缓存并且修改缓存题目答题失败
        Result<PuzzlePkAbandonDTO> result = null;
        if (AttendTypeEnum.MANYPEOPLE.equals(puzzlePkAbandonParam.getAttendType())) {
            Result<GameRoomDetailsDTO> exitGameRoomResult = gameRoomExtendService.exitGameRoom(null, userNum, GameTypeEnum.PUZZLE);
            if (ResultUtil.isSuccess(exitGameRoomResult)) {
                GameRoomDetailsDTO exitGameRoomModel = exitGameRoomResult.getModel();
                PuzzlePkAbandonDTO model = new PuzzlePkAbandonDTO();
                model.setIsDissolution(exitGameRoomModel.getIsDissolution());
                model.setExitUserNum(userNum);
                result = ResultUtil.getSuccess(model);
            } else {
                result = ResultUtil.get(exitGameRoomResult.getRet(), exitGameRoomResult.getMsg());
            }
        } else if (AttendTypeEnum.RANDOM.equals(puzzlePkAbandonParam.getAttendType())) {

        }
        if (ResultUtil.isSuccess(result)) {
            return ResultUtil.get(ResultCode.GAME_WS_USER_OFFLINE, result.getModel());
        } else {
            // 如果异常只发送给当前用户
            sendProcessor.send(ResultUtil.get(result.getRet(), result.getMsg()), userNum);
        }
        return null;
    }

    @Override
    public Result timeout(String userNum, Object param) {
        return submit(userNum,param);

    }

    @Override
    public Result<PuzzlePkAbandonDTO> offline(String userNum) {
        Result<String> resultCount = gamePuzzleRecordService.exceptionExitExecute(userNum);//判断是否最后一个人，如果是直接触发结算  多人的
        Result<GamePuzzleAttendRecordDTO> resultDto = gamePuzzleRecordService.getPuzzleAttendRecordNearlyData(userNum);
        gamePuzzleRecordService.removeMyPuzzleCacheData(userNum);//清除自己缓存并且修改缓存题目答题失败 随机匹配
        if(null!=resultDto.getModel()){
            if(resultDto.getModel().getAttendType().equals(AttendTypeEnum.MANYPEOPLE.getVal())){
                /*Result<GameRoomDetailsDTO> result = gamePuzzleRecordService.quitRoom(userNum);*/
                Result<GameRoomDetailsDTO> result = gameRoomExtendService.exitGameRoom(null, userNum, GameTypeEnum.PUZZLE);
                if(ResultUtil.isSuccess(result)){
                    GameRoomDetailsDTO gameRoomDetailsDTO = result.getModel();
                    PuzzlePkAbandonDTO model = new PuzzlePkAbandonDTO();
                    model.setExitUserNum(userNum);
                    if (gameRoomDetailsDTO != null) {
                        model.setIsDissolution(gameRoomDetailsDTO.getIsDissolution());
                    }else{
                        model.setIsDissolution(false);
                    }
                    return ResultUtil.get(ResultCode.GAME_WS_USER_OFFLINE, model);
                }else{
                    if (result != null) {
                        // 如果异常只发送给当前用户
                        sendProcessor.send(ResultUtil.get(result.getRet(), result.getMsg()), userNum);
                    }
                }
            }else if(resultDto.getModel().getAttendType().equals(AttendTypeEnum.RANDOM.getVal())){
                PuzzlePkAbandonDTO model = new PuzzlePkAbandonDTO();
                model.setExitUserNum(userNum);
                model.setIsDissolution(false);
                return ResultUtil.get(ResultCode.GAME_WS_USER_OFFLINE, model);
            }
        }

      /*  System.out.println("下线返回结果是"+JSON.toJSONString(resultCount));
        if(Integer.valueOf(resultCount.getModel())==0||Integer.valueOf(resultCount.getModel())==1){
            return ResultUtil.get(ResultCode.GAME_WS_USER_SUBMIT_DONE,  "");
        }*/
        return null;

    }

    @Override
    public boolean isValid(String userNum) {
        return false;
    }
}
