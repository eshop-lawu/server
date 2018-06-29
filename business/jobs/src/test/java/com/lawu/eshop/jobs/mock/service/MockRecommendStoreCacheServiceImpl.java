package com.lawu.eshop.jobs.mock.service;

import com.lawu.eshop.jobs.service.RecommendStoreCacheService;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class MockRecommendStoreCacheServiceImpl implements RecommendStoreCacheService {


	@Override
	public Result saveNewMerchant(@RequestParam("regionPath") String regionPath, @RequestBody String storeInfo) {
		return null;
	}

	@Override
	public Result saveRecommendFoodConsume(@RequestParam("regionPath") String regionPath, @RequestBody String storeInfo) {
		return null;
	}

	@Override
	public Result saveRecommendFoodComment(@RequestParam("regionPath") String regionPath, @RequestBody String storeInfo) {
		return null;
	}

	@Override
	public Result delNewMerchant(@RequestParam("regionPath") String regionPath) {
		return null;
	}

	@Override
	public Result delRecommendFoodConsume(@RequestParam("regionPath") String regionPath) {
		return null;
	}

	@Override
	public Result delRecommendFoodComment(@RequestParam("regionPath") String regionPath) {
		return null;
	}
}
