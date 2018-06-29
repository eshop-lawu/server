package com.lawu.eshop.jobs.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2018/5/7.
 */
@FeignClient(value = "activity-srv" , path="richPowerTaskRecord/")
public interface RichPowerTaskRecordService {

    @RequestMapping(value = "getPowerTaskRecordListCount",method = RequestMethod.GET)
    Result<Integer> getPowerTaskRecordListCount();

    @RequestMapping(value = "resetTaskRecord",method = RequestMethod.PUT)
    Result resetTaskRecord(@RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize);
}
