package com.lawu.eshop.member.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.product.dto.RecentlySeckillActivityDTO;
import com.lawu.eshop.product.dto.SeckillActivityThatDayDTO;
import com.lawu.framework.web.Result;

/**
 * 抢购活动服务接口
 * 
 * @author jiangxinjun
 * @createDate 2017年11月24日
 * @updateDate 2017年11月24日
 */
@FeignClient(value = "product-srv", path="seckillActivity/")
public interface SeckillActivityService {
    
    /**
     * 查询当天所有活动
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月24日
     * @updateDate 2017年11月24日
     */
    @RequestMapping(path = "thatday/list", method = RequestMethod.GET)
    Result<List<SeckillActivityThatDayDTO>> thatDayList();
    
    /**
     * 查询当天所有活动
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月24日
     * @updateDate 2017年11月24日
     */
    @RequestMapping(path = "recently/list", method = RequestMethod.GET)
    Result<List<SeckillActivityThatDayDTO>> recentlyList();
    
    
    /**
     * 查询最近一场活动的首页图片以及倒计时
     * 用于用户端
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2018年2月28日
     * @updateDate 2018年2月28日
     */
    @GetMapping(value = "recently")
    Result<RecentlySeckillActivityDTO> recentlyActivity();
}
