package com.lawu.eshop.jobs.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zhangyong
 * @date 2017/7/4.
 */
@FeignClient(value = "user-srv")
public interface MerchantService {

    @RequestMapping(value = "merchant/getTotalCount",method = RequestMethod.GET)
    Integer getTotalCount();
}
