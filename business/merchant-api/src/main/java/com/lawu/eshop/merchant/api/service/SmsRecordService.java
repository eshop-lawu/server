package com.lawu.eshop.merchant.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.mall.constants.VerifyCodePurposeEnum;
import com.lawu.eshop.mall.dto.VerifyCodeDTO;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/3/28
 */
@FeignClient(value = "mall-srv")
public interface SmsRecordService {

    /**
     * 发送短信
     *
     * @param mobile  手机号码
     * @param ip      IP
     * @param purpose 短信类型
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "smsRecord/sendSms/{mobile}")
    Result<VerifyCodeDTO> sendSms(@PathVariable("mobile") String mobile, @RequestParam("ip") String ip, @RequestParam("purpose") VerifyCodePurposeEnum purpose);

}
