package com.lawu.eshop.operator.api.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.activity.constants.DrawLotteryActivityPrizeStatusEnum;
import com.lawu.eshop.activity.dto.DrawLotteryActivityPrizeDTO;
import com.lawu.eshop.activity.dto.DrawLotteryActivityPrizeDetailDTO;
import com.lawu.eshop.activity.param.DrawLotteryActivityPrizeParam;
import com.lawu.eshop.activity.query.OperatorDrawLotteryActivityPrizeQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/1/12.
 */
@FeignClient(value = "activity-srv", path = "drawLotteryActivityPrize/")
public interface DrawLotteryActivityPrizeService {

    /**
     * 新增抽奖奖品
     *
     * @param param
     * @author meishuquan
     */
    @RequestMapping(value = "saveDrawLotteryActivityPrize", method = RequestMethod.POST)
    Result saveDrawLotteryActivityPrize(@RequestBody DrawLotteryActivityPrizeParam param);

    /**
     * 运营平台抽奖奖品列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listOperatorDrawLotteryActivityPrize", method = RequestMethod.POST)
    Result<Page<DrawLotteryActivityPrizeDTO>> listOperatorDrawLotteryActivityPrize(@RequestBody OperatorDrawLotteryActivityPrizeQuery query);

    /**
     * 更新奖品状态
     *
     * @param id
     * @param statusEnum
     * @author meishuquan
     */
    @RequestMapping(value = "updateDrawLotteryActivityPrizeStatus/{id}", method = RequestMethod.PUT)
    Result updateDrawLotteryActivityPrizeStatus(@PathVariable("id") Long id, @RequestParam("statusEnum") DrawLotteryActivityPrizeStatusEnum statusEnum);

    /**
     * 查询奖品详情
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getPrizeDetail/{id}", method = RequestMethod.GET)
    Result<DrawLotteryActivityPrizeDetailDTO> getPrizeDetail(@PathVariable("id") Long id);

    /**
     * 查询奖品剩余中奖率
     *
     * @param drawLotteryActivityId
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getLeftPrizeRate/{drawLotteryActivityId}", method = RequestMethod.GET)
    Result<BigDecimal> getLeftPrizeRate(@PathVariable("drawLotteryActivityId") Long drawLotteryActivityId);

    /**
     * 查询活动奖品
     *
     * @param drawLotteryActivityId
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getActivityAllPrize/{drawLotteryActivityId}", method = RequestMethod.GET)
    Result<List<DrawLotteryActivityPrizeDTO>> getActivityAllPrize(@PathVariable("drawLotteryActivityId") Long drawLotteryActivityId);

}
