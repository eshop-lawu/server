package com.lawu.eshop.jobs.mock.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.jobs.service.MemberService;
import com.lawu.eshop.user.dto.UserDTO;
import com.lawu.eshop.user.dto.VisitUserInfoDTO;
import com.lawu.framework.web.Result;

@Service
public class MockMemberServiceImpl implements MemberService {


	@Override
	public Integer getTotalCount() {
		return null;
	}

	@Override
	public VisitUserInfoDTO findUserAccountAndRegionPathByNum(@RequestParam("userNum") String userNum) {
		return null;
	}

	@Override
	public Result<UserDTO> findMemberInfo(@PathVariable("memberId") Long memberId) {
		return null;
	}

	@Override
	public Result<Boolean> isFinishInformation(@RequestParam("userNum") String userNum) {
		return null;
	}
}
