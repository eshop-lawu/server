package com.lawu.eshop.jobs.mock.service;

import java.util.List;

import com.lawu.eshop.jobs.service.RegionService;
import com.lawu.eshop.mall.dto.RegionDTO;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class MockRegionServiceImpl implements RegionService {


	@Override
	public Result<List<RegionDTO>> getRegionLevelTwo() {
		return null;
	}

	@Override
	public Result<RegionDTO> getRegion(@PathVariable("id") Integer id) {
		return null;
	}

	@Override
	public Result<String> getAreaName(@RequestParam("regionPath") String regionPath) {
		return null;
	}

	@Override
	public Result<String> getById(Long id) {
		return null;
	}
}
