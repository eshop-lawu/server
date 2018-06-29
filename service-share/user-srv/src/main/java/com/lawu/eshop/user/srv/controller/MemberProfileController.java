package com.lawu.eshop.user.srv.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.user.dto.AbnormalAccountDTO;
import com.lawu.eshop.user.dto.InviteeMechantCountDTO;
import com.lawu.eshop.user.dto.InviteeMemberCountDTO;
import com.lawu.eshop.user.param.AbnormalJobParam;
import com.lawu.eshop.user.srv.bo.AbnormalAccountBO;
import com.lawu.eshop.user.srv.converter.MemberProfileConverter;
import com.lawu.eshop.user.srv.service.MemberProfileService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 
 * @author zhangrc
 * @date 2017/03/24
 *
 */
@RestController
@RequestMapping(value = "memberProfile/")
public class MemberProfileController extends BaseController{
	
	@Resource
	private  MemberProfileService memberProfileService;
	
	/**
	 * 我的E友总数量
	 * @return
	 */
   @RequestMapping(value = "getMemberCount", method = RequestMethod.GET)
   public Result<InviteeMemberCountDTO> getMemberCount(@RequestParam Long id) {
	   Integer count=memberProfileService.getMemberCount(id);
	   InviteeMemberCountDTO dto=new InviteeMemberCountDTO();
	   dto.setInviteeMemberCount(count);
	   return successGet(dto);
   }
   
   /**
    * 我的商家推荐总数量
    * @param id
    * @return
    */
   @RequestMapping(value = "getMerchantCount", method = RequestMethod.GET)
   public Result<InviteeMechantCountDTO> getMerchantCount(@RequestParam Long id) {
	   Integer count=memberProfileService.getMerchantCount(id);
	   InviteeMechantCountDTO dto=new InviteeMechantCountDTO();
	   dto.setInviteeMechantCount(count);
	   return successGet(dto);
   }

	@RequestMapping(value = "abnormalMemberList", method = RequestMethod.POST)
	public Result<List<AbnormalAccountDTO>> abnormalMemberList(@RequestBody AbnormalJobParam param) {
		List<AbnormalAccountBO> accountBOS = memberProfileService.abnormalMemberList(param);
		return successGet(MemberProfileConverter.convertAbnormalDTOS(accountBOS));
	}

	@RequestMapping(value = "memberSameIpCount/{id}", method = RequestMethod.GET)
	public Result<List<Integer>> memberSameIpCount(@PathVariable("id") Long id, @RequestParam("type") Byte type) {
		return successGet(memberProfileService.memberSameIpCount(id,type));
	}

	@RequestMapping(value = "inviteMemberTotalCount/{id}", method = RequestMethod.GET)
	public Integer inviteMemberTotalCount(@PathVariable("id") Long id, @RequestParam("type") Byte type) {
		return  memberProfileService.inviteMemberTotalCount(id, type);
	}

	/**
	 * 更新为已助力瑞奇岛动力任务
	 *
	 * @param userNum
	 * @author meishuquan
	 */
	@RequestMapping(value = "updateHelpRichTask", method = RequestMethod.PUT)
	public Result updateHelpRichTask(@RequestParam String userNum) {
		memberProfileService.updateHelpRichTask(userNum);
		return successCreated();
	}

}
