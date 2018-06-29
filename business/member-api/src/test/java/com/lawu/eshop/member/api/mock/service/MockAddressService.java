package com.lawu.eshop.member.api.mock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.member.api.service.AddressService;
import com.lawu.eshop.user.dto.AddressDTO;
import com.lawu.eshop.user.param.AddressParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@Service
public class MockAddressService extends BaseController implements AddressService {

	@Override
	public Result update(@RequestBody AddressParam address, @PathVariable("id") Long id, @RequestParam("userNum") String userNum) {
		return successCreated();
	}

	@Override
	public Result<AddressDTO> get(@PathVariable("id") Long id, @RequestParam("userNum") String userNum) {
		AddressDTO dto = new AddressDTO();
		dto.setName("公司地址");
		dto.setId(1L);
		dto.setAddr("大冲商务中心D座1301");
		dto.setIsDefault(true);
		dto.setMobile("17512036360");
		dto.setPostcode("00000");
		dto.setRegionName("广东省深圳市南山区");
		dto.setRegionPath("11/1101/110101");
		return successCreated(dto);
	}

	@Override
	public Result delete(@PathVariable("id") Long userId, @RequestParam("userNum") String userNum) {
		return successAccepted();
	}

	@Override
	public Result updateDefault(@PathVariable("id") Long id, @RequestParam("userNum") String userNum) {
		return null;
	}

	@Override
	public Result<List<AddressDTO>> selectByUserNum(@PathVariable("userNum") String userNum) {
		AddressDTO dto = new AddressDTO();
		dto.setName("公司地址");
		dto.setId(1L);
		dto.setAddr("大冲商务中心D座1301");
		dto.setIsDefault(true);
		dto.setMobile("17512036360");
		dto.setPostcode("00000");
		dto.setRegionName("广东省深圳市南山区");
		dto.setRegionPath("11/1101/110101");
		List<AddressDTO> list = new ArrayList<>();
		list.add(dto);
		return successCreated(list);
	}

	@Override
	public Result saveWithUserNum(@PathVariable("userNum") String userNum, @RequestBody @Validated AddressParam addressDO) {
		return successCreated();
	}
}
