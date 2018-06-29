package com.lawu.eshop.member.api.mock.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.member.api.service.MemberProfileService;
import com.lawu.eshop.user.dto.InviteeMechantCountDTO;
import com.lawu.eshop.user.dto.InviteeMemberCountDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@Service
class MockMemberProfileService extends BaseController implements MemberProfileService {


	@Override
	public Result<InviteeMemberCountDTO> getMemberCount(@RequestParam("id") Long id) {
		return null;
	}

	@Override
	public Result<InviteeMechantCountDTO> getMerchantCount(@RequestParam("id") Long id) {
		InviteeMechantCountDTO dto = new InviteeMechantCountDTO();
		return successCreated(dto);
	}

	@Override
	public Result updateHelpRichTask(@RequestParam("userNum") String userNum) {
		return null;
	}
}
