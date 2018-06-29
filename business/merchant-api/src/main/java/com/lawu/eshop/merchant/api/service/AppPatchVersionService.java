package com.lawu.eshop.merchant.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.mall.constants.AppTypeEnum;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/12/12.
 */
@FeignClient(value = "mall-srv")
public interface AppPatchVersionService {

    /**
     * 获取APP补丁版本号
     *
     * @param typeEnum
     * @param platform
     * @param appVersion
     * @return
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.GET, value = "appPatchVersion/getAppPatchVersion")
    Result<Integer> getAppPatchVersion(@RequestParam("typeEnum") AppTypeEnum typeEnum, @RequestParam("platform") Byte platform, @RequestParam("appVersion") String appVersion);

}
