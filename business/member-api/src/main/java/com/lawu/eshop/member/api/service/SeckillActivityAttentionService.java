package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.product.param.SeckillActivityProductAttentionParam;
import com.lawu.framework.web.Result;

/**
 * 抢购活动商品服务接口
 * 
 * @author jiangxinjun
 * @createDate 2017年11月24日
 * @updateDate 2017年11月24日
 */
@FeignClient(value = "product-srv", path="seckillActivityAttention/")
public interface SeckillActivityAttentionService {
    
    /**
     * 根据活动商品id添加关注记录以及增加商品关注人数
     * 
     * @param activityProductId 活动商品id
     * @param memberId 用户id
     * @author jiangxinjun
     * @createDate 2017年11月24日
     * @updateDate 2017年11月24日
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(path = "attention/{activityProductId}", method = RequestMethod.POST)
    Result attention(@PathVariable("activityProductId") Long activityProductId, @RequestBody SeckillActivityProductAttentionParam param);
}
