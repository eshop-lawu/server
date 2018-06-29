package com.lawu.eshop.member.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.mall.dto.LotteryInfoDTO;
import com.lawu.eshop.mall.dto.LotteryRecordDTO;
import com.lawu.eshop.mall.param.LotteryRecordParam;
import com.lawu.eshop.mall.query.LotteryRecordQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/11/23.
 */
@FeignClient(value = "mall-srv")
public interface LotteryRecordService {

    /**
     * 立即抽奖
     *
     * @param param
     * @return
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.POST, value = "lotteryRecord/saveLotteryRecord")
    Result saveLotteryRecord(@ModelAttribute LotteryRecordParam param);

    /**
     * 查询中奖滚动列表
     *
     * @return
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.GET, value = "lotteryRecord/listLotteryInfo")
    Result<List<LotteryInfoDTO>> listLotteryInfo();

    /**
     * 查询中奖公告列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.POST, value = "lotteryRecord/listLotteryRecord")
    Result<Page<LotteryRecordDTO>> listLotteryRecord(@ModelAttribute LotteryRecordQuery query);

    /**
     * 查询用户是否参与抽奖
     *
     * @param lotteryActivityId
     * @param userNum
     * @return
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.GET, value = "lotteryRecord/lotteryRecord/{lotteryActivityId}")
    Result<Boolean> lotteryRecord(@PathVariable("lotteryActivityId") Long lotteryActivityId, @RequestParam("userNum") String userNum);

}
