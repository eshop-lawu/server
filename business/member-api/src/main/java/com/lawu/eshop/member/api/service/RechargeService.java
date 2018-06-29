package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.order.dto.ThirdPayCallBackQueryPayOrderDTO;
import com.lawu.eshop.property.param.RechargeSaveDataParam;
import com.lawu.framework.web.Result;

/**
 * 
 * <p>
 * Description: 会员充值余额或积分
 * </p>
 * @author Yangqh
 * @date 2017年4月12日 下午9:22:27
 *
 */
@SuppressWarnings("rawtypes")
@FeignClient(value= "property-srv")
public interface RechargeService {

	/**
	 * 会员充值余额或积分
	 * @param param
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "recharge/save")
	Result save(@RequestBody RechargeSaveDataParam param);

	/**
	 * 获取需要充值的金额
	 * @param bizIds
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "recharge/getRechargeMoney")
	ThirdPayCallBackQueryPayOrderDTO getRechargeMoney(@RequestParam("rechargeId") String rechargeId);

   
}
