package com.lawu.eshop.jobs.mock.service;

import com.lawu.eshop.jobs.service.UserService;
import com.lawu.eshop.user.dto.UserIncomeExpenditureUserInfoWrapperDTO;
import com.lawu.eshop.user.param.UserIncomeExpenditureUserInfoQueryParam;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class MockUserServiceImpl implements UserService {


	@Override
	public Result<UserIncomeExpenditureUserInfoWrapperDTO> selectAccount(@RequestBody UserIncomeExpenditureUserInfoQueryParam param) {
		return null;
	}
}
