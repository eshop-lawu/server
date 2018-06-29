package com.lawu.eshop.merchant.api.mock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lawu.eshop.mall.dto.RegionDTO;
import com.lawu.eshop.mall.dto.RegionPathDTO;
import com.lawu.eshop.mall.dto.RegionProvinceDTO;
import com.lawu.eshop.merchant.api.service.RegionService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@Service
public class MockRegionService extends BaseController implements RegionService {
	@Override
	public Result<List<RegionDTO>> getRegionList() {
		return successGet();
	}

	@Override
	public Result<List<RegionPathDTO>> list() {
		return successGet();
	}

	@Override
	public Result<List<RegionProvinceDTO>> group() {
		return successGet();
	}

	@Override
	public Result<String> getById(Long id) {
		return null;
	}

}
