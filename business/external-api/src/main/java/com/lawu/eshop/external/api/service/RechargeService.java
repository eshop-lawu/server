package com.lawu.eshop.external.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.order.dto.ThirdPayCallBackQueryPayOrderDTO;
import com.lawu.eshop.property.param.NotifyCallBackParam;
import com.lawu.framework.web.Result;

/**
 * 
 * <p>
 * Description: 充值余额或积分
 * </p>
 * @author Yangqh
 * @date 2017年4月13日 上午10:13:53
 *
 */
@FeignClient(value= "property-srv")
public interface RechargeService {

	/**
	 * 处理第三方充值回调
	 * @param param
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, value = "recharge/doHandleRechargeNotify")
	Result doHandleRechargeNotify(@RequestBody NotifyCallBackParam param);

	/**
	 * 获取需要充值的金额
	 * @param rechargeId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "recharge/getRechargeMoney")
	ThirdPayCallBackQueryPayOrderDTO getRechargeMoney(@RequestParam("rechargeId") String rechargeId);
}
