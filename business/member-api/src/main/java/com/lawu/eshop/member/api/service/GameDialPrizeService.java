package com.lawu.eshop.member.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.game.dto.GameDialPrizeDetailDTO;
import com.lawu.eshop.game.dto.GameDialPrizeLotteryDTO;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/3/16.
 */
@FeignClient(value = "game-srv", path = "gameDialPrize/")
public interface GameDialPrizeService {

    /**
     * 查询可参与抽奖的奖品列表
     *
     * @param gameDialId
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listValidGameDialPrize", method = RequestMethod.GET)
    Result<List<GameDialPrizeLotteryDTO>> listValidGameDialPrize(@RequestParam("gameDialId") Long gameDialId);

    /**
     * 查询抽中的奖品信息
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getLotteryPrizeInfo/{id}", method = RequestMethod.GET)
    Result<GameDialPrizeDetailDTO> getLotteryPrizeInfo(@PathVariable("id") Long id);

}
