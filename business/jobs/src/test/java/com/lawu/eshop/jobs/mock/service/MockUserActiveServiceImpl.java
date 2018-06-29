package com.lawu.eshop.jobs.mock.service;

import java.util.Date;
import java.util.List;

import com.lawu.eshop.jobs.service.UserActiveService;
import com.lawu.eshop.statistics.dto.UserActiveDTO;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class MockUserActiveServiceImpl implements UserActiveService {


	@Override
	public Result saveUserActiveDaily(@RequestParam(value = "memberCount") Integer memberCount, @RequestParam(value = "merchantCount") Integer merchantCount, @RequestParam(value = "reportDate") String reportDate) {
		return null;
	}

	@Override
	public Result saveUserActiveMonth(@RequestParam(value = "memberCount") Integer memberCount, @RequestParam(value = "merchantCount") Integer merchantCount, @RequestParam(value = "reportDate") String reportDate) {
		return null;
	}

	@Override
	public Result saveUserActiveAreaDaily(@RequestBody List<UserActiveDTO> activeDTOS) {
		return null;
	}

	@Override
	public Result saveMerchantActiveAreaDaily(@RequestBody List<UserActiveDTO> activeDTOS) {
		return null;
	}

	@Override
	public Result saveUserActiveAreaMonth(@RequestBody List<UserActiveDTO> model) {
		return null;
	}

	@Override
	public Result saveMerchantActiveAreaMonth(@RequestBody List<UserActiveDTO> list) {
		return null;
	}

	@Override
	public Date getMemberActiveDaily() {
		return null;
	}

	@Override
	public Date getMemberActiveMonth() {
		return null;
	}

	@Override
	public Date getAreaDaily() {
		return null;
	}

	@Override
	public Date getAreaMonth() {
		return null;
	}
}
