package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.activity.dto.PointLotteryAttentDTO;
import com.lawu.eshop.activity.param.PointLotteryAttentParam;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/2/1.
 */
@FeignClient(value = "activity-srv", path = "pointLotteryActivityOrder/")
public interface PointLotteryActivityOrderService {

    /**
     * 保存积分抽奖活动订单
     *
     * @param param
     * @author meishuquan
     */
    @RequestMapping(value = "savePointLotteryActivityOrder", method = RequestMethod.POST)
    Result<PointLotteryAttentDTO> savePointLotteryActivityOrder(@RequestBody PointLotteryAttentParam param);

    /**
     * 参与抽奖详情
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getPointLotteryAttentInfo/{id}", method = RequestMethod.GET)
    Result<PointLotteryAttentDTO> getPointLotteryAttentInfo(@PathVariable("id") Long id);

}
