package com.lawu.eshop.beh.analyze.srv.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.cache.dto.AbnormalRedisCountDTO;
import com.lawu.eshop.cache.param.AbnormalInfoParam;
import com.lawu.eshop.cache.param.EarlyAbnormalParam;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2018/1/18.
 */
@FeignClient(value = "cache-srv")
public interface RegMachineService {

    @RequestMapping(value = "regMachine/addHfRegRedisRecord",method = RequestMethod.POST)
    Result addHfRegRedisRecord(@ModelAttribute AbnormalInfoParam infoParam);

    @RequestMapping(value = "regMachine/getAbnormalCount",method = RequestMethod.GET)
    Result<AbnormalRedisCountDTO> getAbnormalCount(@RequestParam("userNum") String userNum);

    @RequestMapping(value = "regMachine/addEarlyHfRedisRecord",method = RequestMethod.POST)
    Result addEarlyHfRedisRecord(@RequestBody EarlyAbnormalParam param);
}
