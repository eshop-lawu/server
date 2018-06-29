package com.lawu.eshop.member.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.activity.dto.DrawLotteryActivityPrizeDTO;
import com.lawu.eshop.activity.dto.DrawLotteryActivityPrizeDetailDTO;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/1/12.
 */
@FeignClient(value = "activity-srv", path = "drawLotteryActivityPrize/")
public interface DrawLotteryActivityPrizeService {

    /**
     * 抽奖活动详情
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getPrizeDetail/{id}", method = RequestMethod.GET)
    Result<DrawLotteryActivityPrizeDetailDTO> getPrizeDetail(@PathVariable("id") Long id);

    /**
     * 查询活动所有奖品
     *
     * @param drawLotteryActivityId
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getActivityAllPrize/{drawLotteryActivityId}", method = RequestMethod.GET)
    Result<List<DrawLotteryActivityPrizeDTO>> getActivityAllPrize(@PathVariable("drawLotteryActivityId") Long drawLotteryActivityId);

    /**
     * 随机查询奖品广告图片
     *
     * @param drawLotteryActivityId
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getRandPrizeAdImg/{drawLotteryActivityId}", method = RequestMethod.GET)
    Result<String> getRandPrizeAdImg(@PathVariable("drawLotteryActivityId") Long drawLotteryActivityId);

    /**
     * 根据抽奖记录id查询奖品信息
     *
     * @param recordId
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getPrizeByRecordId/{recordId}", method = RequestMethod.GET)
    Result<DrawLotteryActivityPrizeDetailDTO> getPrizeByRecordId(@PathVariable("recordId") Long recordId);

}
