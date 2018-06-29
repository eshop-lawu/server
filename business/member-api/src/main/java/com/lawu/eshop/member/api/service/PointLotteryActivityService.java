package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.activity.dto.PointLotteryDetailDTO;
import com.lawu.eshop.activity.dto.PointLotteryInfoDTO;
import com.lawu.eshop.activity.query.PointLotteryActivityQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/2/1.
 */
@FeignClient(value = "activity-srv", path = "pointLotteryActivity/")
public interface PointLotteryActivityService {

    /**
     * 积分抽奖活动列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listPointLotteryActivity", method = RequestMethod.POST)
    Result<Page<PointLotteryInfoDTO>> listPointLotteryActivity(@RequestBody PointLotteryActivityQuery query);

    /**
     * 积分抽奖活动详情
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getPointLotteryDetail/{id}", method = RequestMethod.GET)
    Result<PointLotteryDetailDTO> getPointLotteryDetail(@PathVariable("id") Long id, @RequestParam("userNum") String userNum);

    /**
     * 根据id查询抽奖活动
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getPointLotteryInfo/{id}", method = RequestMethod.GET)
    Result<PointLotteryInfoDTO> getPointLotteryInfo(@PathVariable("id") Long id);

}
