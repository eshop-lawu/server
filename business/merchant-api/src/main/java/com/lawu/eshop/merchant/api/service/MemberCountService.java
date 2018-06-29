package com.lawu.eshop.merchant.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.user.param.AreasCountQuery;
import com.lawu.eshop.user.param.FensCountQuery;

/**
 * 广告投放或者用户数据
 * @author zhangrc
 * @date 2017/4/10
 *
 */
@FeignClient(value = "user-srv")
public interface MemberCountService {
	
	/**
	 * 根据地区或者用户数量
	 * @param regionPath
	 * @return
	 */
	@RequestMapping(value = "member/findMemberCount", method = RequestMethod.POST)
    Integer findMemberCount(@RequestBody AreasCountQuery query);
	
	/**
	 * 根据当前商家获取当前粉丝
	 * @param merchantId
	 * @return
	 */
	@RequestMapping(value = "fansMerchant/findFensCount", method = RequestMethod.POST)
    Integer findFensCount( @RequestBody FensCountQuery query);


}
