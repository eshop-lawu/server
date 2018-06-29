package com.lawu.eshop.jobs.service;

import com.lawu.eshop.order.dto.PayOrderAutoCommentDTO;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author zhangyong
 * @date 2017/7/28.
 */
@FeignClient(value = "order-srv")
public interface PayOrderService {

    @RequestMapping(value = "payOrder/getAutoCommentPayOrderList", method = RequestMethod.GET)
    Result<List<PayOrderAutoCommentDTO>> getAutoCommentPayOrderList();
}
