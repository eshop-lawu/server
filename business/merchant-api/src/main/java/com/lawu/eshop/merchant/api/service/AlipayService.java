package com.lawu.eshop.merchant.api.service;

import com.lawu.eshop.property.dto.OauthTokenRtnDTO;
import com.lawu.eshop.property.param.AlipayOauthDataParam;
import com.lawu.eshop.property.param.AlipayOauthParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.property.param.ThirdPayDataParam;
import com.lawu.framework.web.Result;
import com.lawu.eshop.property.param.PcAlipayDataParam;

/**
 * 
 * <p>
 * Description: 支付宝
 * </p>
 * @author Yangqh
 * @date 2017年4月7日 上午9:12:31
 *
 */
@FeignClient(value= "property-srv")
public interface AlipayService {
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, value = "alipay/getAppAlipayReqParams")
	Result getAppAlipayReqParams(@RequestBody ThirdPayDataParam param);

	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, value = "alipay/initPcPay")
	Result initPcPay(PcAlipayDataParam param);

	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, value = "alipay/initPcPay2048")
	Result initPcPay2048(PcAlipayDataParam aparam);

	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, value = "alipay/alipayUserInfoAuth")
	Result alipayUserInfoAuth(@RequestBody AlipayOauthDataParam alipayOauthDataParam);

	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, value = "alipay/getOauthToken")
	Result<OauthTokenRtnDTO> getOauthToken(@RequestBody AlipayOauthParam alipayOauthParam);
}
