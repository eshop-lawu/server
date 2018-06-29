package com.lawu.eshop.jobs.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/9/22
 */
@FeignClient(value = "user-srv")
public interface FansInviteContentService {

    @RequestMapping(method = RequestMethod.GET, value = "fansInviteContent/dealOverdueFansInvite")
    Result dealOverdueFansInvite(@RequestParam("pageSize") Integer pageSize);

}
