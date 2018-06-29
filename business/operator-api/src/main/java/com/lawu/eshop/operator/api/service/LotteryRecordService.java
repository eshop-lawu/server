package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.mall.dto.LotteryRecordOperatorDTO;
import com.lawu.eshop.mall.query.OperatorLotteryRecordQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/11/24.
 */
@FeignClient(value = "mall-srv")
public interface LotteryRecordService {

    /**
     * 运营平台查询抽奖列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.POST, value = "lotteryRecord/listOperatorLotteryRecord")
    Result<Page<LotteryRecordOperatorDTO>> listOperatorLotteryRecord(@ModelAttribute OperatorLotteryRecordQuery query);

    /**
     * 更新抽奖结果
     *
     * @param lotteryActivityId
     * @param account
     * @return
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.PUT, value = "lotteryRecord/updateLotteryResult/{lotteryActivityId}")
    Result updateLotteryResult(@PathVariable("lotteryActivityId") Long lotteryActivityId, @RequestParam("account") String account);

}
