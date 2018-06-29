package com.lawu.eshop.jobs.mock.service;

import java.util.List;

import com.lawu.eshop.jobs.service.CollectionUserActiveService;
import com.lawu.eshop.statistics.dto.UserActiveDTO;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class MockCollectionUserActiveServiceImpl implements CollectionUserActiveService {


	@Override
	public Result<Integer> collectionMemberActiveDaily(@RequestParam(value = "reportDate") String reportDate) {
		return null;
	}

	@Override
	public Result<Integer> collectionMerchantActiveDaily(@RequestParam(value = "reportDate") String reportDate) {
		return null;
	}

	@Override
	public Result<Integer> collectionMemberActiveMonth(@RequestParam(value = "reportDate") String reportDate) {
		return null;
	}

	@Override
	public Result<Integer> collectionMerchantActiveMonth(@RequestParam(value = "reportDate") String reportDate) {
		return null;
	}

	@Override
	public Result<List<UserActiveDTO>> collectionMemberActiveAreaDaily(@RequestParam(value = "reportDate") String reportDate) {
		return null;
	}

	@Override
	public Result<List<UserActiveDTO>> collectionMerchantActiveAreaDaily(@RequestParam(value = "reportDate") String reportDate) {
		return null;
	}

	@Override
	public Result<List<UserActiveDTO>> collectionMemberActiveAreaMonth(@RequestParam(value = "reportDate") String reportDate) {
		return null;
	}

	@Override
	public Result<List<UserActiveDTO>> collectionMerchantActiveAreaMonth(@RequestParam(value = "reportDate") String reportDate) {
		return null;
	}
}
