package com.lawu.eshop.jobs.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.framework.web.Result;
import com.lawu.weixinapi.dto.RedpackResultDTO;
import com.lawu.weixinapi.dto.param.RedpackParam;

@FeignClient(value = "weixin-srv")
public interface PayService {

    /**
     * 发送微信红包
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "pay/sendRedpack", method = RequestMethod.POST)
    Result<RedpackResultDTO> sendRedpack(@RequestBody RedpackParam param);

    /**
     * 查询红包记录
     *
     * @param mchBillNo
     * @return
     */
    @RequestMapping(value = "pay/queryRedpack/{mchBillNo}", method = RequestMethod.GET)
    Result<RedpackResultDTO> queryRedpack(@PathVariable("mchBillNo") String mchBillNo);

}
