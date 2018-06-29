package com.lawu.eshop.merchant.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.user.param.FansInviteContentExtendParam;
import com.lawu.framework.web.Result;

/**
 * 邀请粉丝内容表接口
 * 
 * @author hongqm
 * @date 2017/08/04
 *
 */
@FeignClient(value = "user-srv")
public interface FansInviteContentService {

	
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, value = "fansInviteContent/saveInviteContentService")
	Result saveFansInviteContent(@RequestBody FansInviteContentExtendParam fansInviteContentExtendParam);
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, value = "fansInviteContent/saveInviteContentExtendService")
	Result saveFansInviteExtendContent(@RequestBody FansInviteContentExtendParam fansInviteContentExtendParam);
}
