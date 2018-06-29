package com.lawu.eshop.jobs.mock.service;

import com.lawu.eshop.jobs.service.MerchantFavoredService;
import com.lawu.eshop.mall.dto.MerchantFavoredDTO;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class MockMerchantFavoredServiceImpl implements MerchantFavoredService {

	@Override
	public Result<MerchantFavoredDTO> findFavoredByMerchantId(@PathVariable("merchantId") Long merchantId) {
		return null;
	}
}
