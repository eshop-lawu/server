package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.mall.dto.LotteryActivityDTO;
import com.lawu.eshop.mall.query.LotteryActivityRealQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/11/23.
 */
@FeignClient(value = "mall-srv")
public interface LotteryActivityService {

    /**
     * 抽奖活动列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.POST, value = "lotteryActivity/listLotteryActivity")
    Result<Page<LotteryActivityDTO>> listLotteryActivity(@ModelAttribute LotteryActivityRealQuery query);

    /**
     * 根据id查询抽奖活动
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.GET, value = "lotteryActivity/getLotteryActivity/{id}")
    Result<LotteryActivityDTO> getLotteryActivityById(@PathVariable("id") Long id);

}
