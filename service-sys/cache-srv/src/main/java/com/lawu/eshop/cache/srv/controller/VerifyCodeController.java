package com.lawu.eshop.cache.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.cache.srv.service.VerifyCodeService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/3/12.
 */
@RestController
@RequestMapping(value = "verifyCode/")
public class VerifyCodeController extends BaseController {

    @Autowired
    private VerifyCodeService verifyCodeService;

    /**
     * 获取短信接口调用频率
     *
     * @param expireTime
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getVisitFrequency", method = RequestMethod.GET)
    public Result<Long> getVisitFrequency(String tag, @RequestParam Integer expireTime) {
        Long frequency = verifyCodeService.getVisitFrequency(tag, expireTime);
        return successGet(frequency);
    }

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
    public Result<Boolean> getPicCodeFlag(@RequestParam String tag, @RequestParam Integer frequencyExpireTime, @RequestParam Integer flagExpireTime, @RequestParam Integer frequencyLimit) {
        Boolean flag = verifyCodeService.getPicCodeFlag(tag, frequencyExpireTime, flagExpireTime, frequencyLimit);
        return successGet(flag);
    }

    /**
     * 校验图形验证码
     *
     * @param key
     * @param picCode
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "verifyPicCode", method = RequestMethod.GET)
    public Result<Boolean> verifyPicCode(@RequestParam String key, @RequestParam String picCode) {
        Boolean result = verifyCodeService.verifyPicCode(key, picCode);
        return successGet(result);
    }

    /**
     * 保存图形验证码
     *
     * @param key
     * @param picCode
     * @param expireTime
     * @author meishuquan
     */
    @RequestMapping(value = "savePicCode", method = RequestMethod.GET)
    public Result savePicCode(@RequestParam String key, @RequestParam String picCode, @RequestParam Integer expireTime) {
        verifyCodeService.savePicCode(key, picCode, expireTime);
        return successGet();
    }

    /**
     * 更新图形验证码标记
     *
     * @param isOpen
     * @author meishuquan
     */
    @RequestMapping(value = "updatePicCodeFlag", method = RequestMethod.GET)
    public Result updatePicCodeFlag(@RequestParam Boolean isOpen) {
        verifyCodeService.updatePicCodeFlag(isOpen);
        return successGet();
    }

}
