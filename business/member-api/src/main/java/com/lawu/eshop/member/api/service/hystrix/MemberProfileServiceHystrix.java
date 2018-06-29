package com.lawu.eshop.member.api.service.hystrix;

import org.springframework.stereotype.Component;

import com.lawu.eshop.member.api.service.MemberProfileService;

/**
 * E友，商家数量接口异常处理类
 * @author zhangrc 
 * @date 2017/03/22
 *
 */
@Component
public class MemberProfileServiceHystrix  {

	public Integer getMemberCount(Long id) {
		System.out.println("服务器调用异常");
		return -1;
	}

	public Integer getMerchantCount(Long id) {
		System.out.println("服务器调用异常");
		return -1;
	}


}
