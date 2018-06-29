package com.lawu.eshop.member.ws.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.cache.dto.GameRoomDetailsDTO;
import com.lawu.eshop.common.dto.GameUserInfoDTO;
import com.lawu.eshop.game.dto.MindPkOnlineDTO;
import com.lawu.eshop.game.dto.MindPkReadyDTO;
import com.lawu.eshop.game.param.ExitGameRoomParam;
import com.lawu.eshop.game.param.JoinGameRoomParam;
import com.lawu.eshop.game.param.MindPkReadyParam;
import com.lawu.eshop.game.param.UpdateGameRoomPlayerReadyStatusParam;
import com.lawu.eshop.member.ws.channel.ChannelManager;
import com.lawu.eshop.member.ws.service.GameRoomExtendService;
import com.lawu.eshop.member.ws.service.GameRoomService;
import com.lawu.eshop.member.ws.service.MemberService;
import com.lawu.eshop.member.ws.service.UserInfoService;
import com.lawu.eshop.member.ws.util.ResultUtil;
import com.lawu.framework.web.Result;

@Service
public class GameRoomExtendServiceImpl implements GameRoomExtendService {
    
    @Autowired
    private MemberService memberService;

    @Autowired
    private GameRoomService gameRoomService;
    
    @Autowired
    private UserInfoService userInfoService;

    @Override
    public Result<MindPkOnlineDTO> online(JoinGameRoomParam param) {
        String groupNum = ChannelManager.getGroupNum(param.getUserNum());
        // 查找用户信息
        Result<List<GameUserInfoDTO>> findUserInfoForGameMindResult = userInfoService.findUserInfo(Arrays.asList(param.getUserNum()));
        if (!ResultUtil.isSuccess(findUserInfoForGameMindResult)) {
            return ResultUtil.get(findUserInfoForGameMindResult.getRet(), findUserInfoForGameMindResult.getMsg());
        }
        param.setRoomNum(groupNum);
        GameUserInfoDTO userInfo = findUserInfoForGameMindResult.getModel().get(0);
        param.setNickName(userInfo.getNickName());
        param.setHeadImg(userInfo.getHeadImg());
        param.setRegionName(userInfo.getRegionName());
        // 加入房间
        Result<GameRoomDetailsDTO> joinGameRoomResult = gameRoomService.joinGameRoom(param);
        if (!ResultUtil.isSuccess(joinGameRoomResult)) {
            return ResultUtil.get(joinGameRoomResult.getRet(), joinGameRoomResult.getMsg());
        }
        MindPkOnlineDTO model = new MindPkOnlineDTO();
        model.setUsers(joinGameRoomResult.getModel().getPlayerInfos());
        return ResultUtil.getSuccess(model);
    }

    @Override
    public Result<MindPkReadyDTO> ready(String userNum, MindPkReadyParam param, GameTypeEnum gameType) {
        String roomNum = ChannelManager.getGroupNum(userNum);
        UpdateGameRoomPlayerReadyStatusParam updateGameRoomPlayerReadyStatusParam = new UpdateGameRoomPlayerReadyStatusParam();
        updateGameRoomPlayerReadyStatusParam.setUserNum(userNum);
        updateGameRoomPlayerReadyStatusParam.setRoomNum(roomNum);
        updateGameRoomPlayerReadyStatusParam.setGameType(gameType);
        Result<GameRoomDetailsDTO> updateGameRoomPlayerReadyStatusResult = gameRoomService.updateGameRoomPlayerReadyStatus(updateGameRoomPlayerReadyStatusParam);
        if (!ResultUtil.isSuccess(updateGameRoomPlayerReadyStatusResult)) {
            return ResultUtil.get(updateGameRoomPlayerReadyStatusResult.getRet(), updateGameRoomPlayerReadyStatusResult.getMsg());
        }
        MindPkReadyDTO model = new MindPkReadyDTO();
        model.setReadyUserNum(userNum);
        return ResultUtil.getSuccess(model);
    }

    @Override
    public Result<GameRoomDetailsDTO> exitGameRoom(String currentUserNum, String userNum, GameTypeEnum gmetype) {
        String roomNum = ChannelManager.getGroupNum(userNum);
        // 考虑被踢用户已经断开连接, 导致roomNum获取不到, 根据currentUserNum获取
        if (roomNum == null && currentUserNum != null) {
            roomNum = ChannelManager.getGroupNum(currentUserNum);
        }
        ExitGameRoomParam exitGameRoomParam = new ExitGameRoomParam();
        exitGameRoomParam.setCurrentUserNum(currentUserNum);
        exitGameRoomParam.setRoomNum(roomNum);
        exitGameRoomParam.setUserNum(userNum);
        exitGameRoomParam.setGameType(gmetype);
        return gameRoomService.exitGameRoom(exitGameRoomParam);
    }

}
