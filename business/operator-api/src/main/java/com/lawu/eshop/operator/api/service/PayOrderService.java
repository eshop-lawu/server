package com.lawu.eshop.operator.api.service;

import com.lawu.eshop.order.dto.OperatorPayOrderListDTO;
import com.lawu.eshop.order.param.OperatorPayOrderParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zhangyong
 * @date 2017/6/28.
 */
@FeignClient(value = "order-srv")
public interface PayOrderService {

    @RequestMapping(value = "payOrder/getOperatorPayOrderList", method = RequestMethod.POST)
    Result<Page<OperatorPayOrderListDTO>> getOperatorPayOrderList(OperatorPayOrderParam param);

}
