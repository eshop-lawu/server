package com.lawu.eshop.member.api.service;

import com.lawu.eshop.mall.dto.MerchantFavoredDTO;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zhangyong
 * @date 2017/4/10.
 */
@FeignClient(value = "mall-srv")
public interface MerchantFavoredService {

    /**
     * 根据商家ID查询商家优惠信息
     *
     * @param merchantId
     * @return
     */
    @RequestMapping(value = "merchantFavored/findFavoredByMerchantId/{merchantId}", method = RequestMethod.GET)
    Result<MerchantFavoredDTO> findFavoredByMerchantId(@PathVariable("merchantId") Long merchantId);

    /**
     * 根据ID查询商家优惠信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "merchantFavored/findFavoredById/{id}", method = RequestMethod.GET)
    public Result<MerchantFavoredDTO> findFavoredById(@PathVariable("id") Long id);

}
