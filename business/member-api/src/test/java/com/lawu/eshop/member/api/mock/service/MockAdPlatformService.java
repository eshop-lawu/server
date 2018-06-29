package com.lawu.eshop.member.api.mock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.ad.constants.PositionEnum;
import com.lawu.eshop.ad.constants.TypeEnum;
import com.lawu.eshop.ad.dto.AdPlatformDTO;
import com.lawu.eshop.ad.dto.AdPlatformFlatDTO;
import com.lawu.eshop.ad.dto.AdPlatformProductDTO;
import com.lawu.eshop.ad.dto.AdPlatformVideoDTO;
import com.lawu.eshop.ad.dto.AdPlatformVideoFlatDTO;
import com.lawu.eshop.ad.param.AdPlatformInternalParam;
import com.lawu.eshop.member.api.service.AdPlatformService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@Service
public class MockAdPlatformService extends BaseController implements AdPlatformService {


    @Override
    public Result<List<AdPlatformDTO>> selectByPosition(@RequestBody PositionEnum positionEnum) {
        AdPlatformDTO dto = new AdPlatformDTO();
        List<AdPlatformDTO> list = new ArrayList<>();
        list.add(dto);
        return successCreated(list);
    }

    @Override
    public Result<List<AdPlatformProductDTO>> getAdPlatformByTypePosition(@RequestParam("typeEnum") TypeEnum typeEnum, @RequestParam("positionEnum") PositionEnum positionEnum) {
        AdPlatformProductDTO dto = new AdPlatformProductDTO();
        dto.setMediaUrl("1.jpg");
        dto.setProductId(1L);
        dto.setTitle("title");
        List<AdPlatformProductDTO> list = new ArrayList<>();
        list.add(dto);
        return successCreated(list);
    }

    @Override
    public Result<List<AdPlatformDTO>> getAdPlatformByTypePositionRegionPath(@RequestParam("typeEnum") TypeEnum typeEnum, @RequestParam("positionEnum") PositionEnum positionEnum, @RequestParam("regionPath") String regionPath) {
        AdPlatformDTO dto = new AdPlatformDTO();
        dto.setId(1L);
        dto.setContent("ccdf");
        dto.setLinkUrl("343");
        dto.setMediaUrl("343");
        dto.setMerchantStoreId(1L);
        dto.setProductId(1L);
        dto.setTitle("rere");
        List<AdPlatformDTO> list = new ArrayList<>();
        list.add(dto);
        return successCreated(list);
    }

	@Override
	public Result<AdPlatformVideoFlatDTO> selAdPlatformPositionAd(AdPlatformInternalParam param) {
		AdPlatformVideoFlatDTO dto = new AdPlatformVideoFlatDTO();
        return successCreated(dto);
	}

	@Override
	public Result<List<AdPlatformProductDTO>> getAdPlatformProductTop() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
