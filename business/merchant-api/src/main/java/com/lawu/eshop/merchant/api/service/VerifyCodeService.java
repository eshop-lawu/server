package com.lawu.eshop.merchant.api.service;

import com.lawu.eshop.mall.constants.VerifyCodePurposeEnum;
import com.lawu.eshop.mall.dto.VerifyCodeDTO;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author meishuquan
 * @date 2017/3/28.
 */
@FeignClient(value = "mall-srv")
public interface VerifyCodeService {

    /**
     * 保存图形验证码
     *
     * @param mobile  手机号码
     * @param picCode 图形验证码
     * @param purpose 用途
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "verifyCode/savePicCode/{mobile}")
    Result savePicCode(@PathVariable("mobile") String mobile, @RequestParam("picCode") String picCode, @RequestParam("purpose") VerifyCodePurposeEnum purpose);

    /**
     * 校验手机验证码
     *
     * @param id      ID
     * @param smsCode 手机验证码
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "verifyCode/verifySmsCode/{id}")
    Result<VerifyCodeDTO> verifySmsCode(@PathVariable("id") Long id, @RequestParam("smsCode") String smsCode);

    /**
     * 校验图形验证码
     *
     * @param mobile  手机号码
     * @param picCode 图形验证码
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "verifyCode/verifyPicCode/{mobile}")
    Result verifyPicCode(@PathVariable("mobile") String mobile, @RequestParam("picCode") String picCode);

    /**
     * 查询验证码
     *
     * @param id ID
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "verifyCode/getVerifyCode/{id}")
    Result getVerifyCodeById(@PathVariable("id") Long id);
}
