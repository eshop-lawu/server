package com.lawu.eshop.jobs.mock.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.common.constants.DelIndexTypeEnum;
import com.lawu.eshop.jobs.service.MerchantStoreService;
import com.lawu.eshop.user.dto.MerchantStoreDTO;
import com.lawu.eshop.user.dto.NewMerchantStoreDTO;
import com.lawu.eshop.user.dto.RecommendFoodDTO;
import com.lawu.eshop.user.dto.VisitUserInfoDTO;
import com.lawu.eshop.user.param.ListMerchantStoreParam;
import com.lawu.eshop.user.param.StoreIndexParam;
import com.lawu.eshop.user.param.StoreStatisticsParam;
import com.lawu.framework.web.Result;

@Service
public class MockMerchantStoreServiceImpl implements MerchantStoreService {


	@Override
	public Result<List<MerchantStoreDTO>> listMerchantStore(@ModelAttribute ListMerchantStoreParam listMerchantStoreParam) {
		return null;
	}

	@Override
	public void updateStoreStatisticsById(@PathVariable("id") Long id, @ModelAttribute StoreStatisticsParam param) {

	}

	@Override
	public Result<MerchantStoreDTO> selectMerchantStoreByMId(@RequestParam("merchantId") Long merchantId) {
		return null;
	}

	@Override
	public VisitUserInfoDTO findAccountAndRegionPathByNum(@RequestParam("merchantNum") String merchantNum) {
		return null;
	}

	@Override
	public Result<List<NewMerchantStoreDTO>> listNewMerchant(@RequestParam("regionPath") String regionPath) {
		return null;
	}

	@Override
	public Result<List<RecommendFoodDTO>> listRecommendFoodConsume(@PathVariable("industryId") Integer industryId, @RequestParam("regionPath") String regionPath) {
		return null;
	}

	@Override
	public Result<List<RecommendFoodDTO>> listRecommendFoodComment(@PathVariable("industryId") Integer industryId, @RequestParam("regionPath") String regionPath) {
		return null;
	}

	@Override
	public Result rebuildStoreIndex(@RequestBody List<StoreIndexParam> indexParamList) {
		return null;
	}

	@Override
	public Result delInvalidStoreIndex(@RequestParam("typeEnum") DelIndexTypeEnum typeEnum) {
		return null;
	}
}
