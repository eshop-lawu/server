package com.lawu.eshop.merchant.api.service;

import com.lawu.eshop.user.param.AliUserInfoDataParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.xml.transform.Result;

@FeignClient(value = "user-srv")
public interface AliUserInfoService {

    @RequestMapping(value = "aliUserInfo/save", method = RequestMethod.POST)
    Result save(@RequestBody AliUserInfoDataParam aliUserInfoDataParam);
}
