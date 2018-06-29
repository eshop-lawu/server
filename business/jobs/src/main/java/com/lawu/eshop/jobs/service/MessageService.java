package com.lawu.eshop.jobs.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.mall.param.MessageInfoParam;
import com.lawu.framework.web.Result;

/**
 * @author zhangrc
 * @date 2017/7/27.
 */
@FeignClient(value = "mall-srv")
public interface MessageService {

    /**
     * 插入站内消息
     *
     * @param userNum          用户编号
     * @param messageInfoParam 消息参数
     */
    @RequestMapping(method = RequestMethod.POST, value = "message/saveMessage/{userNum}")
    Result saveMessage(@PathVariable("userNum") String userNum, @ModelAttribute MessageInfoParam messageInfoParam);
}
