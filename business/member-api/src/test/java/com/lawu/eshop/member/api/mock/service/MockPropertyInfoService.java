package com.lawu.eshop.member.api.mock.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.member.api.service.PropertyInfoService;
import com.lawu.eshop.property.constants.PropertyinfoFreezeEnum;
import com.lawu.eshop.property.dto.OrderAssetInformationDTO;
import com.lawu.eshop.property.dto.PropertyBalanceDTO;
import com.lawu.eshop.property.dto.PropertyInfoFreezeDTO;
import com.lawu.eshop.property.dto.PropertyLoveAccountDTO;
import com.lawu.eshop.property.dto.PropertyPointAndBalanceDTO;
import com.lawu.eshop.property.dto.PropertyPointDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@Service
class MockPropertyInfoService extends BaseController implements PropertyInfoService {


	@Override
	public Result updatePayPwd(@PathVariable("userNum") String userNum, @RequestParam("originalPwd") String originalPwd, @RequestParam("newPwd") String newPwd) {
		return successCreated();
	}

	@Override
	public Result resetPayPwd(@PathVariable("userNum") String userNum, @RequestParam("newPwd") String newPwd) {
		return successCreated();
	}

	@Override
	public Result setPayPwd(@PathVariable("userNum") String userNum, @RequestParam("newPwd") String newPwd) {
		return successCreated();
	}

	@Override
	public Result updatePayPwd(@PathVariable("userNum") String userNum, @RequestParam("originalPwd") String originalPwd, @RequestParam("newPwd") String newPwd, @RequestParam("type") Integer type) {
		return null;
	}

	@Override
	public Result isSetPayPwd(@PathVariable("userNum") String userNum) {
		return successCreated();
	}

	@Override
	public Result<PropertyBalanceDTO> getPropertyBalance(@PathVariable("userNum") String userNum) {
		PropertyBalanceDTO dto = new PropertyBalanceDTO();
		dto.setBalance(new BigDecimal("1"));
		return successCreated(dto);
	}

	@Override
	public Result<PropertyPointDTO> getPropertyPoint(@PathVariable("userNum") String userNum) {
		PropertyPointDTO dto = new PropertyPointDTO();
		dto.setPoint(new BigDecimal("100"));
		return successCreated(dto);
	}

	@Override
	public Result varifyPayPwd(@RequestParam("userNum") String userNum, @RequestParam("payPwd") String payPwd) {
		return successCreated(true);
	}

	@Override
	public Result<PropertyPointAndBalanceDTO> getPropertyInfoMoney(@PathVariable("userNum") String userNum) {
		PropertyPointAndBalanceDTO dto = new PropertyPointAndBalanceDTO();
		dto.setPoint(new BigDecimal("10"));
		return successCreated(dto);
	}

	@Override
	public Result<PropertyLoveAccountDTO> selectLoveAccount(@PathVariable("userNum") String userNum) {
		PropertyLoveAccountDTO dto = new PropertyLoveAccountDTO();
		dto.setLoveAccount(new BigDecimal("1100"));
		return successCreated(dto);
	}

	@Override
	public Result<PropertyInfoFreezeDTO> getPropertyinfoFreeze(@PathVariable("userNum") String userNum) {
		PropertyInfoFreezeDTO dto = new PropertyInfoFreezeDTO();
		dto.setStatus(PropertyinfoFreezeEnum.NO);
		return successCreated(dto);
	}

	@Override
	public Result validatePoint(@RequestParam("userNum") String userNum, @RequestParam("point") String point) {
		return successGet();
	}

    @Override
    public Result<OrderAssetInformationDTO> orderAssetInformation(String userNum) {
        return successGet();
    }

	@Override
	public Result getRechargeRate(String name) {
		return null;
	}


}
