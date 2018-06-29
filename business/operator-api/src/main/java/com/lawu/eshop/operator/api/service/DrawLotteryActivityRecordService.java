package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.activity.dto.DrawLotteryActivityRecordOperatorDTO;
import com.lawu.eshop.activity.query.OperatorDrawLotteryActivityRecordQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/1/17.
 */
@FeignClient(value = "activity-srv", path = "drawLotteryActivityRecord/")
public interface DrawLotteryActivityRecordService {

    /**
     * 运营平台抽奖记录列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listOperatorDrawLotteryActivityRecord", method = RequestMethod.POST)
    Result<Page<DrawLotteryActivityRecordOperatorDTO>> listOperatorDrawLotteryActivityRecord(@RequestBody OperatorDrawLotteryActivityRecordQuery query);

    /**
     * 发放奖品
     *
     * @param id
     * @author meishuquan
     */
    @RequestMapping(value = "sendPrize/{id}", method = RequestMethod.PUT)
    Result sendPrize(@PathVariable("id") Long id,@RequestParam("expressNum") String expressNum);

}
