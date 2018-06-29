package com.lawu.eshop.member.ws.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.cache.dto.GameRoomDetailsDTO;
import com.lawu.eshop.game.param.ExitGameRoomParam;
import com.lawu.eshop.game.param.JoinGameRoomParam;
import com.lawu.eshop.game.param.UpdateGameRoomPlayerReadyStatusParam;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/3/13.
 */
@FeignClient(value = "game-srv", path = "gameRoom/")
public interface GameRoomService {

    /**
     * 加入房间
     *
     * @param param
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "joinGameRoom", method = RequestMethod.PUT)
    Result<GameRoomDetailsDTO> joinGameRoom(@RequestBody JoinGameRoomParam param);

    /**
     * 退出房间
     *
     * @param param
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "exitGameRoom", method = RequestMethod.PUT)
    Result<GameRoomDetailsDTO> exitGameRoom(@RequestBody ExitGameRoomParam param);

    /**
     * 更新房间用户准备状态
     *
     * @param param
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "updateGameRoomPlayerReadyStatus", method = RequestMethod.PUT)
    Result<GameRoomDetailsDTO> updateGameRoomPlayerReadyStatus(@RequestBody UpdateGameRoomPlayerReadyStatusParam param);

}
