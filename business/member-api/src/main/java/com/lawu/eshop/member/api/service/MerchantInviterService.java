package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.user.constants.InviterTypeEnum;
import com.lawu.eshop.user.dto.MerchantInviterDTO;
import com.lawu.eshop.user.param.MerchantInviterParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * 推荐的商家
 * @author zhangrc
 * @date 2017/03/28
 *
 */
@FeignClient(value = "user-srv")
public interface MerchantInviterService {
	
	
	@RequestMapping(method = RequestMethod.POST,value = "merchant/getMerchantByInviter")
    Result<Page<MerchantInviterDTO>> getMerchantByInviter(@RequestParam("userId") Long id,@RequestBody MerchantInviterParam query,@RequestParam("inviterType") Byte inviterType );

}
