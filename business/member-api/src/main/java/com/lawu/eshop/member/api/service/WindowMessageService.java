package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.mall.dto.WindowMessageListDTO;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/3/1.
 */
@FeignClient(value = "mall-srv", path = "windowMessage")
public interface WindowMessageService {

    /**
     * 弹窗消息列表
     *
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listWindowMessage", method = RequestMethod.GET)
    Result<WindowMessageListDTO> listWindowMessage();

}
