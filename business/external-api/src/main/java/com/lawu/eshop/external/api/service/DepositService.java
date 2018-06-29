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
 * Description: 保证金回调
 * </p>
 * @author Yangqh
 * @date 2017年4月15日 上午11:58:46
 *
 */
@FeignClient(value= "property-srv")
public interface DepositService {

	/**
	 * 处理第三方充值回调
	 * @param param
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, value = "businessDeposit/doHandleDepositNotify")
	Result doHandleDepositNotify(@RequestBody NotifyCallBackParam param);

   
}
