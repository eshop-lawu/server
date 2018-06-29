package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.property.param.ThirdPayDataParam;
import com.lawu.framework.web.Result;

/**
 * 
 * <p>
 * Description: 微信支付
 * </p>
 * @author Yangqh
 * @date 2017年4月7日 下午9:03:09
 *
 */
@FeignClient(value= "property-srv")
public interface WxPayService {
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, value = "wxPay/getPrepayInfo")
	Result getPrepayInfo(@RequestBody ThirdPayDataParam param);

	

}
