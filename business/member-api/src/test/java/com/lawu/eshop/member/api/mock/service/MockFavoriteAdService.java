package com.lawu.eshop.member.api.mock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.ad.dto.FavoriteAdCountDTO;
import com.lawu.eshop.ad.dto.FavoriteAdDOViewDTO;
import com.lawu.eshop.ad.param.FavoriteAdParam;
import com.lawu.eshop.member.api.service.FavoriteAdService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@Service
public class MockFavoriteAdService extends BaseController implements FavoriteAdService {

	@Override
	public Result save(@RequestParam("memberId") Long memberId, @RequestParam("adId") Long adId, @RequestParam("userNum") String userNum) {
		return successCreated();
	}

	@Override
	public Result remove(@PathVariable("adId") Long adId, @RequestParam("memberId") Long memberId) {
		return successCreated();
	}

	@Override
	public Result<Page<FavoriteAdDOViewDTO>> selectMyFavoriteAd(@RequestParam("memberId") Long id, @RequestBody FavoriteAdParam param) {
		FavoriteAdDOViewDTO dto = new FavoriteAdDOViewDTO();
		dto.setMerchantId(1L);
		List<FavoriteAdDOViewDTO> list = new ArrayList<>();
		list.add(dto);
		Page<FavoriteAdDOViewDTO> page = new Page();
		page.setCurrentPage(1);
		page.setTotalCount(100);
		page.setRecords(list);
		return successCreated(page);
	}

	@Override
	public Result<Boolean> isFavoriteAd(@RequestParam("adId") Long adId, @RequestParam("memberId") Long memberId) {
		return successCreated(true);
	}

	@Override
	public Result<FavoriteAdCountDTO> getFavoriteAdCount(@RequestParam("memberId") Long memberId) {
		return null;
	}
}
