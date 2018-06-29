package com.lawu.eshop.member.api.mock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.mall.dto.RegionDTO;
import com.lawu.eshop.mall.dto.RegionPathDTO;
import com.lawu.eshop.mall.dto.RegionProvinceDTO;
import com.lawu.eshop.member.api.service.RegionService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;


@Service
public class MockRegionService extends BaseController implements RegionService {

    @Override
    public Result<List<RegionDTO>> getRegionList() {
        RegionDTO dto = new RegionDTO();
        List<RegionDTO> list = new ArrayList<>();
        list.add(dto);
        return successCreated(list);
    }

    @Override
    public Result<String> getAreaName(@RequestParam("regionPath") String regionPath) {
        return successCreated(new String("dfdf"));
    }

	@Override
	public Result<List<RegionPathDTO>> list() {
		RegionPathDTO dto = new RegionPathDTO();
        List<RegionPathDTO> list = new ArrayList<>();
        list.add(dto);
        return successGet(list);
	}

	@Override
	public Result<List<RegionProvinceDTO>> group() {
		RegionProvinceDTO dto = new RegionProvinceDTO();
        List<RegionProvinceDTO> list = new ArrayList<>();
        list.add(dto);
        return successGet(list);
	}
}
