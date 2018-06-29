package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.game.dto.GameRoomCreateDTO;
import com.lawu.eshop.game.dto.GameRoomDTO;
import com.lawu.eshop.game.param.GameRoomParam;
import com.lawu.eshop.game.query.GameRoomQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/3/13.
 */
@FeignClient(value = "game-srv", path = "gameRoom/")
public interface GameRoomService {

    /**
     * 创建房间
     *
     * @param param
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "saveGameRoom", method = RequestMethod.POST)
    Result<GameRoomCreateDTO> saveGameRoom(@RequestBody GameRoomParam param);

    /**
     * 房间列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listGameRoom", method = RequestMethod.POST)
    Result<Page<GameRoomDTO>> listGameRoom(@RequestBody GameRoomQuery query);

    /**
     * 根据id查询房间
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getGameRoom/{id}", method = RequestMethod.GET)
    Result<GameRoomDTO> getGameRoom(@PathVariable("id") Long id);

    /**
     * 密码校验
     *
     * @param id
     * @param pwd
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "verifyGameRoomPwd/{id}", method = RequestMethod.GET)
    Result<Boolean> verifyGameRoomPwd(@PathVariable("id") Long id, @RequestParam("pwd") String pwd);

    /**
     * 根据房间号和房间类型查询房间
     *
     * @param roomNum
     * @param typeEnum
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getGameRoomByRoomNum", method = RequestMethod.GET)
    Result<GameRoomDTO> getGameRoomByRoomNum(@RequestParam("roomNum") String roomNum, @RequestParam("typeEnum") GameTypeEnum typeEnum);

    /**
     * 根据用户编号和房间类型查询房间
     *
     * @param userNum
     * @param typeEnum
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getGameRoomByUserNum", method = RequestMethod.GET)
    Result<GameRoomDTO> getGameRoomByUserNum(@RequestParam("userNum") String userNum, @RequestParam("typeEnum") GameTypeEnum typeEnum);

}
