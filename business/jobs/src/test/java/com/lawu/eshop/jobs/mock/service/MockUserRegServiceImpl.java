package com.lawu.eshop.jobs.mock.service;

import com.lawu.eshop.jobs.service.UserRegService;
import com.lawu.eshop.statistics.dto.ReportNewDateDTO;
import com.lawu.eshop.statistics.param.UserRegAreaParam;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class MockUserRegServiceImpl implements UserRegService {


	@Override
	public Result saveUserRegDaily(@RequestParam("memberCount") Integer memberCount, @RequestParam("merchantCount") Integer merchantCount) {
		return null;
	}

	@Override
	public Result saveUserRegMonth(@RequestParam("memberCount") Integer memberCount, @RequestParam("merchantCount") Integer merchantCount) {
		return null;
	}

	@Override
	public Result updateUserRegArea(@RequestBody UserRegAreaParam param) {
		return null;
	}

	@Override
	public Result addUserRegAreaDaily(@RequestBody UserRegAreaParam userRegAreaParam) {
		return null;
	}

	@Override
	public Result addUserRegAreaMonth(@RequestBody UserRegAreaParam userRegAreaParam) {
		return null;
	}

	@Override
	public Result<ReportNewDateDTO> getReportDateUserRegDaily() {
		return null;
	}

	@Override
	public Result<ReportNewDateDTO> getReportDateUserRegMonth() {
		return null;
	}
}
