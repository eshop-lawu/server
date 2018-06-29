package com.lawu.eshop.game.srv.mock.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.cache.dto.GameRoomDetailsDTO;
import com.lawu.eshop.cache.dto.GameRoomPlayerInfoDTO;
import com.lawu.eshop.cache.param.GameRoomOperationParam;
import com.lawu.eshop.game.srv.service.GameRoomCacheService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2018/3/19.
 */
@Service
public class MockGameRoomCacheService extends BaseController implements GameRoomCacheService {

    @Override
    public Result<String> getGameRoomNum(@RequestParam("roomType") String roomType, @RequestParam("roomTotalNum") Integer roomTotalNum) {
        return successGet("1");
    }

    @Override
    public Result recycleGameRoomNum(@RequestParam("roomType") String roomType, @RequestParam("roomNum") String roomNum) {
        return null;
    }

    @Override
    public Result<GameRoomDetailsDTO> createGameRoom(@RequestBody GameRoomOperationParam param) {
        GameRoomDetailsDTO detailsDTO = new GameRoomDetailsDTO();
        detailsDTO.setIsDissolution(true);
        List<GameRoomPlayerInfoDTO> playerInfos = new ArrayList<>();
        GameRoomPlayerInfoDTO infoDTO = new GameRoomPlayerInfoDTO();
        infoDTO.setJoinTime(new Date());
        playerInfos.add(infoDTO);
        detailsDTO.setPlayerInfos(playerInfos);
        return successGet(detailsDTO);
    }

    @Override
    public Result<GameRoomDetailsDTO> joinGameRoom(@RequestBody GameRoomOperationParam param) {
        GameRoomDetailsDTO detailsDTO = new GameRoomDetailsDTO();
        detailsDTO.setIsDissolution(true);
        List<GameRoomPlayerInfoDTO> playerInfos = new ArrayList<>();
        GameRoomPlayerInfoDTO infoDTO = new GameRoomPlayerInfoDTO();
        infoDTO.setJoinTime(new Date());
        playerInfos.add(infoDTO);
        detailsDTO.setPlayerInfos(playerInfos);
        return successGet(detailsDTO);
    }

    @Override
    public Result<GameRoomDetailsDTO> exitGameRoom(@RequestBody GameRoomOperationParam param) {
        GameRoomDetailsDTO detailsDTO = new GameRoomDetailsDTO();
        detailsDTO.setIsDissolution(true);
        return successGet(detailsDTO);
    }

    @Override
    public Result<GameRoomDetailsDTO> updateGameRoomPlayerReadyStatus(@RequestBody GameRoomOperationParam param) {
        GameRoomDetailsDTO detailsDTO = new GameRoomDetailsDTO();
        detailsDTO.setIsDissolution(true);
        return successGet(detailsDTO);
    }

    @Override
    public Result<GameRoomDetailsDTO> getGameRoomAllPlayer(@RequestBody GameRoomOperationParam param) {
        return null;
    }
}
