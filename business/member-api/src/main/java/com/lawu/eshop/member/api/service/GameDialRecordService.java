package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.game.constants.GameDialRecordStatusEnum;
import com.lawu.eshop.game.dto.GameDialRecordInfoDTO;
import com.lawu.eshop.game.dto.GameDialRecordAttendDTO;
import com.lawu.eshop.game.dto.TakePartLotteryInfoDTO;
import com.lawu.eshop.game.param.TakeLotteryParam;
import com.lawu.eshop.game.param.TakePartLotteryParam;
import com.lawu.eshop.game.query.GameDialRecordUserQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/3/16.
 */
@FeignClient(value = "game-srv", path = "gameDialRecord/")
public interface GameDialRecordService {

    /**
     * 中奖记录
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listGameDialRecord", method = RequestMethod.POST)
    Result<Page<GameDialRecordInfoDTO>> listGameDialRecord(@RequestBody GameDialRecordUserQuery query);

    /**
     * 根据状态查询用户抽奖记录
     *
     * @param gameDialId
     * @param userNum
     * @param statusEnum
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getTakePartLottery/{gameDialId}", method = RequestMethod.GET)
    Result<TakePartLotteryInfoDTO> getTakePartLottery(@PathVariable("gameDialId") Long gameDialId, @RequestParam("userNum") String userNum, @RequestParam("statusEnum") GameDialRecordStatusEnum statusEnum);

    /**
     * 保存抽奖记录
     *
     * @param param
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "saveGameDialRecord", method = RequestMethod.POST)
    Result<TakePartLotteryInfoDTO> saveGameDialRecord(@RequestBody TakePartLotteryParam param);

    /**
     * 根据id查询抽奖记录状态
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getGameDialRecordAttend/{id}", method = RequestMethod.GET)
    Result<GameDialRecordAttendDTO> getGameDialRecordAttend(@PathVariable("id") Long id);

    /**
     * 领奖
     *
     * @param param
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "takeLottery", method = RequestMethod.PUT)
    Result takeLottery(@RequestBody TakeLotteryParam param);

    /**
     * 未中奖
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "notLottery/{id}", method = RequestMethod.PUT)
    Result notLottery(@PathVariable("id") Long id);

    /**
     * 中奖
     *
     * @param id
     * @param gameDialId
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "winLottery/{id}", method = RequestMethod.PUT)
    Result winLottery(@PathVariable("id") Long id, @RequestParam("gameDialId") Long gameDialId);

}
