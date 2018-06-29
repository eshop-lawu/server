package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.user.dto.InviteeMechantCountDTO;
import com.lawu.eshop.user.dto.InviteeMemberCountDTO;
import com.lawu.framework.web.Result;

/**
 * 
 * @author zhangrc
 * @date 2017/03/24
 *
 */
@FeignClient(value= "user-srv")
public interface MemberProfileService {
	
	/**
	 * 我的E友总数量查询
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "memberProfile/getMemberCount")
	public Result<InviteeMemberCountDTO> getMemberCount(@RequestParam("id")Long id);

	/**
	 * 我推荐的商家总数量
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "memberProfile/getMerchantCount")
	public Result<InviteeMechantCountDTO> getMerchantCount(@RequestParam("id")Long id);

	/**
	 * 更新为已助力瑞奇岛动力任务
	 *
	 * @param userNum
	 * @author meishuquan
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "memberProfile/updateHelpRichTask")
	Result updateHelpRichTask(@RequestParam("userNum") String userNum);

}
