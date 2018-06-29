package com.lawu.eshop.external.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.property.param.NotifyCallBackParam;
import com.lawu.framework.web.Result;

/**
 * 
 * <p>
 * Description: 订单处理
 * </p>
 * 
 * @author Yangqh
 * @date 2017年4月13日 下午1:58:23
 *
 */
@FeignClient(value= "property-srv")
public interface OrderService {

	/**
	 * 处理第三方订单付款回调
	 * @param param
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, value = "order/doHandleOrderPayNotify")
	Result doHandleOrderPayNotify(@RequestBody NotifyCallBackParam param);

	/**
	 * 处理第三方买单回调
	 * @param param
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, value = "order/doHandlePayOrderNotify")
	Result doHandlePayOrderNotify(@RequestBody NotifyCallBackParam param);

}
