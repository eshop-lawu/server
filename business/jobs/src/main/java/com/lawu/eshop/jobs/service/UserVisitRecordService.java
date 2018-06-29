package com.lawu.eshop.jobs.service;

import com.lawu.eshop.statistics.param.UserVisitRecordParam;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zhangyong
 * @date 2017/7/4.
 */
@FeignClient(value = "statistics-srv")
public interface UserVisitRecordService {

    @RequestMapping(value = "visitRecord/addUserVisitRecord" ,method = RequestMethod.POST)
    Result addUserVisitRecord(@RequestBody UserVisitRecordParam userVisitRecordParam);
}
