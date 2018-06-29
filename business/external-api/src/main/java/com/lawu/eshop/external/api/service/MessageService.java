package com.lawu.eshop.external.api.service;

import com.lawu.eshop.mall.param.MessageInfoParam;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangyong
 * @date 2017/4/10.
 */
@FeignClient(value = "mall-srv")
public interface MessageService {

    /**
     * 插入站内消息
     *
     * @param userNum          用户编号
     * @param messageInfoParam 消息参数
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, value = "message/saveMessage/{userNum}")
    Result saveMessage(@PathVariable("userNum") String userNum, @ModelAttribute MessageInfoParam messageInfoParam);
}
