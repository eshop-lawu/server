package com.lawu.eshop.jobs.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.framework.web.Result;

import java.util.Map;

/**
 * @author zhangyong
 * @date 2017/7/3.
 */
@FeignClient(value = "cache-srv")
public interface UserVisitCacheService {

    @RequestMapping(value = "userVisit/getVisitRecords",method = RequestMethod.GET)
    Map<String,String> getVisitRecords(@RequestParam("currentPage") Integer currentPage,
                                       @RequestParam("time") String time,
                                       @RequestParam("type") Byte type);

    @RequestMapping(value = "userVisit/delVisitRecords",method = RequestMethod.DELETE)
    Result delVisitRecords(@RequestParam("time") String time);
}
