package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.game.dto.GameDialDTO;
import com.lawu.eshop.game.dto.GameDialDetailDTO;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/3/16.
 */
@FeignClient(value = "game-srv", path = "gameDial/")
public interface GameDialService {

    /**
     * 转盘游戏详情
     *
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getGameDialDetail", method = RequestMethod.GET)
    Result<GameDialDetailDTO> getGameDialDetail();

    /**
     * 根据id查询转盘游戏
     *
     * @return
     */
    @RequestMapping(value = "getGameDialById/{id}", method = RequestMethod.GET)
    Result<GameDialDTO> getGameDialById(@PathVariable("id") Long id);

}
