package com.lawu.eshop.game.srv.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.cache.dto.GameRoomDetailsDTO;
import com.lawu.eshop.cache.param.GameRoomOperationParam;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/3/13.
 */
@FeignClient(value = "cache-srv", path = "gameRoom/")
public interface GameRoomCacheService {

    /**
     * 获取房间号
     *
     * @param roomType
     * @param roomTotalNum
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getGameRoomNum", method = RequestMethod.GET)
    Result<String> getGameRoomNum(@RequestParam("roomType") String roomType, @RequestParam("roomTotalNum") Integer roomTotalNum);

    /**
     * 回收房间号
     *
     * @param roomType
     * @param roomNum
     * @author meishuquan
     */
    @RequestMapping(value = "recycleGameRoomNum", method = RequestMethod.GET)
    Result recycleGameRoomNum(@RequestParam("roomType") String roomType, @RequestParam("roomNum") String roomNum);

    /**
     * 房主创建房间
     *
     * @param param
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "createGameRoom", method = RequestMethod.POST)
    Result<GameRoomDetailsDTO> createGameRoom(@RequestBody GameRoomOperationParam param);

    /**
     * 加入房间
     *
     * @param param
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "joinGameRoom", method = RequestMethod.POST)
    Result<GameRoomDetailsDTO> joinGameRoom(@RequestBody GameRoomOperationParam param);

    /**
     * 退出房间
     *
     * @param param
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "exitGameRoom", method = RequestMethod.PUT)
    Result<GameRoomDetailsDTO> exitGameRoom(@RequestBody GameRoomOperationParam param);

    /**
     * 更新房间用户准备状态
     *
     * @param param
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "updateGameRoomPlayerReadyStatus", method = RequestMethod.PUT)
    Result<GameRoomDetailsDTO> updateGameRoomPlayerReadyStatus(@RequestBody GameRoomOperationParam param);

    /**
     * 获取房间所有玩家
     *
     * @param param
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getGameRoomAllPlayer", method = RequestMethod.PUT)
    Result<GameRoomDetailsDTO> getGameRoomAllPlayer(@RequestBody GameRoomOperationParam param);
}
