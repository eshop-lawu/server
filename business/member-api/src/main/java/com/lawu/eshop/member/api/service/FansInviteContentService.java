package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.user.dto.FansInviteContentDTO;
import com.lawu.framework.web.Result;
/**
 * 
 * @author hongqm
 *
 */
@FeignClient(value= "user-srv")
public interface FansInviteContentService {
	
	@RequestMapping(method = RequestMethod.POST, value = "fansInviteContent/selectInviteContentById/{id}/{relateId}")
	Result<FansInviteContentDTO> selectInviteContentById(@PathVariable("id") Long id, @PathVariable("relateId") Long relateId, @RequestParam("memberId")Long memberId);
	
}
