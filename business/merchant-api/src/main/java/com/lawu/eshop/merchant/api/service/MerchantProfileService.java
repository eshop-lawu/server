package com.lawu.eshop.merchant.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/5/4
 */
@FeignClient(value = "user-srv")
public interface MerchantProfileService {

    /**
     * 更新为已助力瑞奇岛动力任务
     *
     * @param userNum
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.PUT, value = "merchantProfile/updateHelpRichTask")
    Result updateHelpRichTask(@RequestParam("userNum") String userNum);

}
