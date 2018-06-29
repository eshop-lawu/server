package com.lawu.eshop.member.api.mock.service;

import com.lawu.eshop.ad.dto.FavoriteAdDOViewDTO;
import com.lawu.eshop.member.api.service.FavoriteMerchantService;
import com.lawu.eshop.user.dto.FavoriteMerchantDTO;
import com.lawu.eshop.user.param.FavoriteMerchantParam;
import com.lawu.eshop.user.param.FavoriteStoreParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class MockFavoriteMerchantService extends BaseController implements FavoriteMerchantService {

	@Override
	public Result save(@RequestParam("memberId") Long memberId, @RequestBody FavoriteStoreParam param) {
		return successCreated();
	}

	@Override
	public Result<Page<FavoriteMerchantDTO>> getMyFavoriteMerchant(@RequestParam("memberId") Long memberId, @RequestBody FavoriteMerchantParam pageQuery) {
		FavoriteMerchantDTO dto = new FavoriteMerchantDTO();
		dto.setMerchantId(1L);
		List<FavoriteMerchantDTO> list = new ArrayList<>();
		list.add(dto);
		Page<FavoriteMerchantDTO> page = new Page();
		page.setCurrentPage(1);
		page.setTotalCount(100);
		page.setRecords(list);
		return successCreated(page);
	}

	@Override
	public Result remove(@RequestBody FavoriteStoreParam param, @RequestParam("memberId") Long memberId) {
		return successCreated();
	}
}
