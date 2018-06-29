package com.lawu.eshop.jobs.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2018/6/8.
 */
@FeignClient(value = "activity-srv" , path="richMerchantPowerTaskRecord/")
public interface RichMerchantPowerTaskRecordService {
    @RequestMapping(value = "getMerchantPowerTaskRecordListCount",method = RequestMethod.GET)
    Result<Integer> getMerchantPowerTaskRecordListCount();

    @RequestMapping(value = "resetMerchantTaskRecord", method = RequestMethod.PUT)
    Result resetMerchantTaskRecord(@RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize);
}
