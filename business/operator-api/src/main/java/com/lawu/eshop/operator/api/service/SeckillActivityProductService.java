package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.product.dto.SeckillActivityProductInfoDTO;
import com.lawu.eshop.product.param.SeckillActivityProductAuditParam;
import com.lawu.eshop.product.param.SeckillActivityProductNotPassedParam;
import com.lawu.eshop.product.param.SeckillActivityProductPageSearchParam;
import com.lawu.eshop.product.param.SeckillActivityProductUpdateParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

@FeignClient(value = "product-srv", path = "seckillActivityProduct/")
public interface SeckillActivityProductService {
    
    /**
     * 根据id和查询参数分页查询抢购活动商品列表
     * 用于运营平台
     * 
     * @param id 抢购活动id
     * @param param 分页查询参数
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月27日
     * @updateDate 2017年11月27日
     */
    @RequestMapping(value = "pageSearch/{id}", method = RequestMethod.PUT)
    Result<Page<SeckillActivityProductInfoDTO>> pageSearch(@PathVariable("id") Long id, @RequestBody SeckillActivityProductPageSearchParam param);
    
    /**
     * 抢购活动商品
     * 审核不通过
     * 
     * @param id 抢购活动商品id
     * @param param 反馈参数
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月27日
     * @updateDate 2017年11月27日
     */
    @RequestMapping(value = "notPassed/{id}", method = RequestMethod.PUT)
    Result<?> notPassed(@PathVariable("id") Long id, @RequestBody SeckillActivityProductNotPassedParam param);
    
    /**
     * 审核抢购活动商品
     * 
     * @param id 抢购活动商品id
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月27日
     * @updateDate 2017年11月27日
     */
    @RequestMapping(value = "audit/{id}", method = RequestMethod.PUT)
    Result<?> audit(@PathVariable("id") Long id, @RequestBody SeckillActivityProductAuditParam param);
    
    /**
     * 更新抢购活动商品
     * @param activityProductId
     * @return
     * @author jiangxinjun
     * @createDate 2018年5月23日
     * @updateDate 2018年5月23日
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "update/{activityProductId}", method = RequestMethod.PUT)
    Result update(@PathVariable("activityProductId") Long activityProductId, @RequestBody SeckillActivityProductUpdateParam param);
}
