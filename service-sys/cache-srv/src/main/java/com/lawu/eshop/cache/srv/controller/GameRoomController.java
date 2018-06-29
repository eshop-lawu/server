package com.lawu.eshop.cache.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.cache.dto.GameRoomDetailsDTO;
import com.lawu.eshop.cache.param.GameRoomOperationParam;
import com.lawu.eshop.cache.srv.service.GameRoomService;
import com.lawu.eshop.common.exception.DataNotExistException;
import com.lawu.eshop.common.exception.WrongOperationException;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/3/13.
 */
@RestController
@RequestMapping(value = "gameRoom/")
public class GameRoomController extends BaseController {

    @Autowired
    private GameRoomService gameRoomService;

    /**
     * 获取房间号
     *
     * @param roomType
     * @param roomTotalNum
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getGameRoomNum", method = RequestMethod.GET)
    public Result<String> getGameRoomNum(@RequestParam String roomType, @RequestParam Integer roomTotalNum) {
        String roomNum = gameRoomService.getGameRoomNum(roomType, roomTotalNum);
        return successGet(roomNum);
    }

    /**
     * 回收房间号
     *
     * @param roomType
     * @param roomNum
     * @author meishuquan
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "recycleGameRoomNum", method = RequestMethod.GET)
    public Result recycleGameRoomNum(@RequestParam String roomType, @RequestParam String roomNum) {
        gameRoomService.recycleGameRoomNum(roomType, roomNum);
        return successGet();
    }

    /**
     * 房主创建房间
     *
     * @param param
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "createGameRoom", method = RequestMethod.POST)
    public Result<GameRoomDetailsDTO> createGameRoom(@RequestBody GameRoomOperationParam param) {
        try {
            GameRoomDetailsDTO model = gameRoomService.createGameRoom(param);
            return successGet(model);
        } catch (WrongOperationException e) {
            return successCreated(ResultCode.GAME_ROOM_JOIN_FAIL, e.getMessage());
        }
    }

    /**
     * 加入房间
     *
     * @param param
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "joinGameRoom", method = RequestMethod.POST)
    public Result<GameRoomDetailsDTO> joinGameRoom(@RequestBody GameRoomOperationParam param) {
        try {
            GameRoomDetailsDTO model = gameRoomService.joinGameRoom(param);
            return successGet(model);
        } catch (DataNotExistException e) {
            return successCreated(e.getRet(), e.getMessage());
        } catch (WrongOperationException e) {
            return successCreated(e.getRet(), e.getMessage());
        }
    }

    /**
     * 退出房间
     *
     * @param param
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "exitGameRoom", method = RequestMethod.PUT)
    public Result<GameRoomDetailsDTO> exitGameRoom(@RequestBody GameRoomOperationParam param) {
        try {
            GameRoomDetailsDTO model = gameRoomService.exitGameRoom(param);
            return successGet(model);
        } catch (DataNotExistException e) {
            return successCreated(e.getRet(), e.getMessage());
        } catch (WrongOperationException e) {
            return successCreated(e.getRet(), e.getMessage());
        }
    }

    /**
     * 更新房间用户准备状态
     *
     * @param param
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "updateGameRoomPlayerReadyStatus", method = RequestMethod.PUT)
    public Result<GameRoomDetailsDTO> updateGameRoomPlayerReadyStatus(@RequestBody GameRoomOperationParam param) {
        try {
            GameRoomDetailsDTO model = gameRoomService.updateGameRoomPlayerReadyStatus(param);
            return successGet(model);
        } catch (DataNotExistException e) {
            return successCreated(e.getRet(), e.getMessage());
        } catch (WrongOperationException e) {
            return successCreated(e.getRet(), e.getMessage());
        }
    }

    /**
     * 获取房间所有玩家
     *
     * @param param
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getGameRoomAllPlayer", method = RequestMethod.PUT)
    public Result<GameRoomDetailsDTO> getGameRoomAllPlayer(@RequestBody GameRoomOperationParam param) {
        try {
            GameRoomDetailsDTO model = gameRoomService.getGameRoomAllPlayer(param);
            return successGet(model);
        } catch (DataNotExistException e) {
            return successCreated(e.getRet(), e.getMessage());
        }
    }

}
