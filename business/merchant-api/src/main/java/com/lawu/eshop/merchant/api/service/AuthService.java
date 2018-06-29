package com.lawu.eshop.merchant.api.service;

import com.lawu.framework.web.Result;
import com.lawu.weixinapi.dto.WeixinUserDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 获取微信用户信息
 * @Description
 * @author zhangrc
 * @date 2018年1月19日
 */
@FeignClient(value = "weixin-srv")
public interface AuthService {
	
	
	@RequestMapping(value = "auth/merchant/getAuthUserInfo", method = RequestMethod.GET)
	Result<WeixinUserDTO> getMerchantAuthUserInfo(@RequestParam("code") String code);

}
