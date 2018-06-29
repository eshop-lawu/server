package com.lawu.eshop.operator.api.service;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.ad.dto.InviterBountyDTO;
import com.lawu.eshop.ad.dto.SentTotalInviterBountyDTO;
import com.lawu.eshop.ad.param.InviterBountyParam;
import com.lawu.eshop.ad.param.InviterBountyQueryParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * 邀请注册奖励金接口
 * 
 * @author zhangrc
 * @date 2017/12/21
 *
 */
@FeignClient(value = "ad-srv")
public interface InviterBountyService {
	
	/**
	 * 设置邀请奖励金
	 * @param param
	 */
	@RequestMapping(method = RequestMethod.POST, value = "inviterBounty/saveInviterBounty")
	Result saveInviterBounty(@RequestBody InviterBountyParam param);
	
	/**
	 * 禁用邀请注册奖励金
	 * @param id
	 * @param auditorId
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "inviterBounty/setInviterBounty/{id}")
	Result setInviterBounty(@PathVariable("id") Long id,@RequestParam("auditorId") Long auditorId);
	
	/**
	 * 邀请奖励金列表查询
	 * @param query
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "inviterBounty/queryInviterBounty")
	Result<Page<InviterBountyDTO>> queryInviterBounty(@RequestBody InviterBountyQueryParam query);
	
	/**
	 * 统计总奖励金
	 * @return
	 */
	@RequestMapping(value = "inviterBounty/queryInviterBountyTotalMoney", method = RequestMethod.GET)
	Result<SentTotalInviterBountyDTO> queryInviterBountyTotalMoney();

}
