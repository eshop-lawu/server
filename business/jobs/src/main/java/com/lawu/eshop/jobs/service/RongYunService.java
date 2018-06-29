package com.lawu.eshop.jobs.service;

import com.lawu.eshop.user.dto.RongYunHistoryMessageDTO;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhangyong
 * @date 2017/5/22.
 */
@FeignClient(value = "user-srv")
public interface RongYunService {
    @RequestMapping(value = "rongUser/getHistoryMessage", method = RequestMethod.POST)
    public Result<RongYunHistoryMessageDTO> getHistoryMessage(@RequestParam("date") String date);
}
