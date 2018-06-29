package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/3/12.
 */
@FeignClient(value = "cache-srv", path = "verifyCode/")
public interface VerifyCodeCacheService {

    /**
     * 获取图形验证码标记
     *
     * @param tag
     * @param frequencyExpireTime
     * @param flagExpireTime
     * @param frequencyLimit
     * @return
     */
    @RequestMapping(value = "getPicCodeFlag", method = RequestMethod.GET)
    Result<Boolean> getPicCodeFlag(@RequestParam("tag") String tag, @RequestParam("frequencyExpireTime") Integer frequencyExpireTime, @RequestParam("flagExpireTime") Integer flagExpireTime, @RequestParam("frequencyLimit") Integer frequencyLimit);

    /**
     * 校验图形验证码
     *
     * @param key
     * @param picCode
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "verifyPicCode", method = RequestMethod.GET)
    Result<Boolean> verifyPicCode(@RequestParam("key") String key, @RequestParam("picCode") String picCode);

    /**
     * 保存图形验证码
     *
     * @param key
     * @param picCode
     * @param expireTime
     * @author meishuquan
     */
    @RequestMapping(value = "savePicCode", method = RequestMethod.GET)
    Result savePicCode(@RequestParam("key") String key, @RequestParam("picCode") String picCode, @RequestParam("expireTime") Integer expireTime);

}
