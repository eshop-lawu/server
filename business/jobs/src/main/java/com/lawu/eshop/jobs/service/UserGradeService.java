package com.lawu.eshop.jobs.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.framework.web.Result;

@FeignClient(value = "user-srv", path = "grade/")
public interface UserGradeService {

    /**
     * 更新下次抽奖次数到当前抽奖次数
     *
     * @author meishuquan
     */
    @RequestMapping(value = "updateFreeLotteryCount", method = RequestMethod.PUT)
    Result updateFreeLotteryCount();

}
