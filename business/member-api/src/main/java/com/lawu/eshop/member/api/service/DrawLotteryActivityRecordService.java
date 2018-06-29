package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.activity.constants.DrawLotteryActivityRecordStatusEnum;
import com.lawu.eshop.activity.dto.DrawLotteryActivityRecordDTO;
import com.lawu.eshop.activity.dto.DrawLotteryActivityRecordPageDTO;
import com.lawu.eshop.activity.dto.TakePartLotteryInfoDTO;
import com.lawu.eshop.activity.param.TakeLotteryParam;
import com.lawu.eshop.activity.param.TakePartLotteryParam;
import com.lawu.eshop.activity.query.DrawLotteryActivityRecordQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/1/15.
 */
@FeignClient(value = "activity-srv", path = "drawLotteryActivityRecord/")
public interface DrawLotteryActivityRecordService {

    /**
     * 查询已参与的抽奖
     *
     * @param drawLotteryActivityId
     * @param userNum
     * @param statusEnum
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getTakePartLottery/{drawLotteryActivityId}", method = RequestMethod.GET)
    Result<TakePartLotteryInfoDTO> getTakePartLottery(@PathVariable("drawLotteryActivityId") Long drawLotteryActivityId, @RequestParam("userNum") String userNum, @RequestParam("statusEnum") DrawLotteryActivityRecordStatusEnum statusEnum);

    /**
     * 参与抽奖
     *
     * @param param
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "takePartLottery", method = RequestMethod.POST)
    Result<TakePartLotteryInfoDTO> takePartLottery(@RequestBody TakePartLotteryParam param);

    /**
     * 未中奖
     *
     * @param id
     * @author meishuquan
     */
    @RequestMapping(value = "notLottery/{id}", method = RequestMethod.PUT)
    Result notLottery(@PathVariable("id") Long id);

    /**
     * 放弃领奖
     *
     * @param id
     * @author meishuquan
     */
    @RequestMapping(value = "giveUpTakeLottery/{id}", method = RequestMethod.PUT)
    Result giveUpTakeLottery(@PathVariable("id") Long id);

    /**
     * 中奖
     *
     * @param id
     * @param drawLotteryActivityPrizeId
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "winLottery/{id}", method = RequestMethod.PUT)
    Result winLottery(@PathVariable("id") Long id, @RequestParam("drawLotteryActivityPrizeId") Long drawLotteryActivityPrizeId);

    /**
     * 领奖
     *
     * @param param
     * @author meishuquan
     */
    @RequestMapping(value = "takeLottery", method = RequestMethod.PUT)
    Result takeLottery(@RequestBody TakeLotteryParam param);

    /**
     * 根据抽奖记录id查询活动抽奖活动id
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getDrawLotteryActivityId/{id}", method = RequestMethod.GET)
    Result<Long> getDrawLotteryActivityId(@PathVariable("id") Long id);

    /**
     * 根据id查询抽奖记录
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getDrawLotteryActivityRecord/{id}", method = RequestMethod.GET)
    Result<DrawLotteryActivityRecordDTO> getDrawLotteryActivityRecord(@PathVariable("id") Long id);
    
    
    /**
     * 领取记录
     */
    @RequestMapping(value = "listDrawLotteryActivityRecord", method = RequestMethod.POST)
    Result<Page<DrawLotteryActivityRecordPageDTO>> listDrawLotteryActivityRecord (@RequestParam("memberNum") String memberNum,@RequestBody DrawLotteryActivityRecordQuery query);

}
