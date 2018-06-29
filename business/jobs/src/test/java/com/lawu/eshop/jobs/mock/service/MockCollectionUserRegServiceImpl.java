package com.lawu.eshop.jobs.mock.service;

import com.lawu.eshop.jobs.service.CollectionUserRegService;
import com.lawu.eshop.user.param.CollectionUserRegParam;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class MockCollectionUserRegServiceImpl implements CollectionUserRegService {


	@Override
	public Result<Integer> collectionMemberRegDaily(@RequestBody CollectionUserRegParam param) {
		return null;
	}

	@Override
	public Result<Integer> collectionMerchantRegDaily(@RequestBody CollectionUserRegParam param) {
		return null;
	}

	@Override
	public Result<Integer> collectionMemberRegMonth(@RequestBody CollectionUserRegParam param) {
		return null;
	}

	@Override
	public Result<Integer> collectionMerchantRegMonth(@RequestBody CollectionUserRegParam param) {
		return null;
	}

	@Override
	public Result<Integer> collectionMemberRegArea(@RequestBody CollectionUserRegParam param) {
		return null;
	}

	@Override
	public Result<Integer> collectionMerchantCommonRegArea(@RequestBody CollectionUserRegParam param) {
		return null;
	}

	@Override
	public Result<Integer> collectionMerchantEntityRegArea(@RequestBody CollectionUserRegParam param) {
		return null;
	}

	@Override
	public Result<Integer> collectionMemberRegDailyArea(@RequestBody CollectionUserRegParam param) {
		return null;
	}

	@Override
	public Result<Integer> collectionMerchantNormalRegDailyArea(@RequestBody CollectionUserRegParam param) {
		return null;
	}

	@Override
	public Result<Integer> collectionMerchantEntityRegDailyArea(@RequestBody CollectionUserRegParam param) {
		return null;
	}

	@Override
	public Result<Integer> collectionMemberRegMonthArea(@RequestBody CollectionUserRegParam param) {
		return null;
	}

	@Override
	public Result<Integer> collectionMerchantNormalRegMonthArea(@RequestBody CollectionUserRegParam param) {
		return null;
	}

	@Override
	public Result<Integer> collectionMerchantEntityRegMonthArea(@RequestBody CollectionUserRegParam param) {
		return null;
	}
}
