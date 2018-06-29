package com.lawu.eshop.member.api.service;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.ad.dto.GetInviterBountyDTO;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
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
	 * 邀请注册获取奖励金
	 * @param userNum
	 * @return
	 */
	@RequestMapping(value = "inviterBounty/getInviterBounty", method = RequestMethod.GET)
	public Result<GetInviterBountyDTO> getInviterBounty(@RequestParam("userNum") String userNum,@RequestParam("regNum") String regNum,@RequestParam("userType") UserTypeEnum userType);

}
