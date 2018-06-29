package com.lawu.eshop.operator.api.service;

import com.lawu.eshop.property.param.ThirdPayRefundParam;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "property-srv")
public interface PayQuickOperateService {

    @RequestMapping(method = RequestMethod.POST, value = "payQuick/refund")
    Result refund(@RequestBody ThirdPayRefundParam param);


}
