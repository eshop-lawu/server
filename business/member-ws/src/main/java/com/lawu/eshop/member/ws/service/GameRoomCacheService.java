package com.lawu.eshop.member.ws.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.cache.dto.GameRoomDetailsDTO;
import com.lawu.eshop.cache.param.GameRoomOperationParam;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/3/15.
 */
@FeignClient(value = "cache-srv", path = "gameRoom/")
public interface GameRoomCacheService {

    @RequestMapping(value = "getGameRoomAllPlayer", method = RequestMethod.PUT)
    Result<GameRoomDetailsDTO> getGameRoomAllPlayer(@RequestBody GameRoomOperationParam param);
}
