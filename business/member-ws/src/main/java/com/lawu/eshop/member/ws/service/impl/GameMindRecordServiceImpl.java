package com.lawu.eshop.member.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.cache.constants.GameRoomPlayerStatusEnum;
import com.lawu.eshop.cache.dto.CheckPointsDeductionStatusDTO;
import com.lawu.eshop.cache.dto.GameRoomDetailsDTO;
import com.lawu.eshop.cache.dto.GameRoomPlayerInfoDTO;
import com.lawu.eshop.cache.dto.VerifyAnswerDTO;
import com.lawu.eshop.cache.param.ReadyStartGameParam;
import com.lawu.eshop.cache.param.VerifyAnswerParam;
import com.lawu.eshop.common.dto.GameUserInfoDTO;
import com.lawu.eshop.game.dto.GameMindBattleUserUserInfoDTO;
import com.lawu.eshop.game.dto.GameMindParticipateGameDTO;
import com.lawu.eshop.game.dto.MindPkOnlineDTO;
import com.lawu.eshop.game.dto.MindPkStartDTO;
import com.lawu.eshop.game.param.MindPkStartParam;
import com.lawu.eshop.game.param.ParticipateGameMindParam;
import com.lawu.eshop.member.ws.channel.ChannelManager;
import com.lawu.eshop.member.ws.service.GameMindAttendRecordService;
import com.lawu.eshop.member.ws.service.GameMindCacheService;
import com.lawu.eshop.member.ws.service.GameMindRecordService;
import com.lawu.eshop.member.ws.service.MemberService;
import com.lawu.eshop.member.ws.service.UserInfoService;
import com.lawu.eshop.member.ws.util.ResultUtil;
import com.lawu.framework.web.Result;

/**
 * 头脑PK记录接口实现类
 * @author jiangxinjun
 * @createDate 2018年3月14日
 * @updateDate 2018年3月16日
 */
@Service
public class GameMindRecordServiceImpl implements GameMindRecordService {
    
    @Autowired
    private GameMindCacheService gameMindCacheService;
    
    @Autowired
    private GameMindAttendRecordService gameMindAttendRecordService;
    
    @Autowired
    private MemberService memberService;
    
    @Autowired
    private UserInfoService userInfoService;
    
    @Override
    public Result<MindPkOnlineDTO> readyStartGame(ReadyStartGameParam param) {
        Result<MindPkOnlineDTO> result = new Result<>();
        Result<List<String>> readyStartGameResult = gameMindCacheService.readyStartGame(param);
        if (!ResultUtil.isSuccess(readyStartGameResult)) {
            result.setRet(readyStartGameResult.getRet());
            result.setMsg(readyStartGameResult.getMsg());
            return result;
        }
        List<String> readyStartGameModel = readyStartGameResult.getModel();
        Result<List<GameUserInfoDTO>> findUserInfoForGameMindResult = userInfoService.findUserInfo(readyStartGameModel);
        if (!ResultUtil.isSuccess(findUserInfoForGameMindResult)) {
            result.setRet(findUserInfoForGameMindResult.getRet());
            result.setMsg(findUserInfoForGameMindResult.getMsg());
            return result;
        }
        List<GameRoomPlayerInfoDTO> users = new ArrayList<>();
        for (GameUserInfoDTO item : findUserInfoForGameMindResult.getModel()) {
            GameRoomPlayerInfoDTO gameRoomPlayerInfoDTO = new GameRoomPlayerInfoDTO();
            gameRoomPlayerInfoDTO.setHeadImg(item.getHeadImg());
            gameRoomPlayerInfoDTO.setIsRoomHost(param.getIsHomeowner());
            gameRoomPlayerInfoDTO.setNickName(item.getNickName());
            gameRoomPlayerInfoDTO.setStatusEnum(GameRoomPlayerStatusEnum.READY);
            gameRoomPlayerInfoDTO.setUserNum(item.getUserNum());
            users.add(gameRoomPlayerInfoDTO);
        }
        MindPkOnlineDTO model = new MindPkOnlineDTO();
        model.setUsers(users);
        return ResultUtil.getSuccess(model);
    }


    @Override
    public Result<MindPkStartDTO> participateGame(String userNum, MindPkStartParam param) {
        ParticipateGameMindParam participateGameMindParam = new ParticipateGameMindParam();
        participateGameMindParam.setAttendType(param.getAttendType());
        participateGameMindParam.setGroupNum(ChannelManager.getGroupNum(userNum));
        Result<GameMindParticipateGameDTO> participateGameResult = gameMindAttendRecordService.participateGame(participateGameMindParam);
        if (!ResultUtil.isSuccess(participateGameResult)) {
            return ResultUtil.get(participateGameResult.getRet(), participateGameResult.getMsg());
        }
        GameMindParticipateGameDTO gameMindParticipateGameDTO = participateGameResult.getModel();
        Result<List<GameUserInfoDTO>> findUserInfoForGameMindResult = userInfoService.findUserInfo(gameMindParticipateGameDTO.getUserNums());
        if (!ResultUtil.isSuccess(findUserInfoForGameMindResult)) {
            return ResultUtil.get(findUserInfoForGameMindResult.getRet(), findUserInfoForGameMindResult.getMsg());
        }
        List<GameMindBattleUserUserInfoDTO> battleUsers = new ArrayList<>();
        for (GameUserInfoDTO item : findUserInfoForGameMindResult.getModel()) {
            GameMindBattleUserUserInfoDTO battleUser = new GameMindBattleUserUserInfoDTO();
            battleUser.setUserNum(item.getUserNum());
            battleUser.setHeadImg(item.getHeadImg());
            battleUser.setNickName(item.getNickName());
            battleUser.setRegionPath(item.getRegionPath());
            battleUser.setRegionName(item.getRegionName());
            battleUsers.add(battleUser);
        }
        MindPkStartDTO model = new MindPkStartDTO();
        model.setMaximumScore(gameMindParticipateGameDTO.getMaximumScore());
        model.setQuestions(gameMindParticipateGameDTO.getQuestions());
        model.setBattleUsers(battleUsers);
        return ResultUtil.getSuccess(model);
    }
    
    @Override
    public Result<CheckPointsDeductionStatusDTO> checkPointsDeductionStatus(String userNum) {
        Result<CheckPointsDeductionStatusDTO> checkPointsDeductionStatusResult = gameMindCacheService.checkPointsDeductionStatus(userNum);
        if (!ResultUtil.isSuccess(checkPointsDeductionStatusResult)) {
            return ResultUtil.get(checkPointsDeductionStatusResult.getRet(), checkPointsDeductionStatusResult.getMsg());
        }
        return checkPointsDeductionStatusResult;
    }
    
    @SuppressWarnings("rawtypes")
    @Override
    public Result<VerifyAnswerDTO> verifyAnswer(VerifyAnswerParam param) {
        // 验证提交的答案
        Result<VerifyAnswerDTO> verifyAnswerResult = gameMindCacheService.verifyAnswer(param);
        if (!ResultUtil.isSuccess(verifyAnswerResult)) {
            return ResultUtil.get(verifyAnswerResult.getRet(), verifyAnswerResult.getMsg());
        }
        VerifyAnswerDTO model = verifyAnswerResult.getModel();
        if (model.getIsLastAnswer() && model.getIsLastQuestion()) {
            Result synchronizeDataFormCacheResult = gameMindAttendRecordService.synchronizeDataFormCache(ChannelManager.getGroupNum(param.getUserNum()));
            if (!ResultUtil.isSuccess(synchronizeDataFormCacheResult)) {
                return ResultUtil.get(synchronizeDataFormCacheResult.getRet(), synchronizeDataFormCacheResult.getMsg());
            }
        }
        return verifyAnswerResult;
    }

    @Override
    public Result<GameRoomDetailsDTO> quit(String userNum) {
        String groupNum = ChannelManager.getGroupNum(userNum);
        // 分组编号为空
        if (groupNum == null) {
            groupNum = "";
        }
        return gameMindAttendRecordService.quit(userNum, groupNum);
    }
}
