package com.lawu.eshop.merchant.api.mock.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.merchant.api.service.PropertyInfoService;
import com.lawu.eshop.property.constants.MerchantTransactionTypeEnum;
import com.lawu.eshop.property.constants.PropertyinfoFreezeEnum;
import com.lawu.eshop.property.dto.FreezeDTO;
import com.lawu.eshop.property.dto.PropertyBalanceDTO;
import com.lawu.eshop.property.dto.PropertyInfoFreezeDTO;
import com.lawu.eshop.property.dto.PropertyLoveAccountDTO;
import com.lawu.eshop.property.dto.PropertyPointAndBalanceDTO;
import com.lawu.eshop.property.dto.PropertyPointDTO;
import com.lawu.eshop.property.param.FreezeQueryParam;
import com.lawu.eshop.property.param.PropertyInfoDataParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@Service
public class MockPropertyInfoService extends BaseController implements PropertyInfoService {
    @Override
    public Result updatePayPwd(@PathVariable("userNo") String userNo, @RequestParam("originalPwd") String originalPwd, @RequestParam("newPwd") String newPwd) {
        return successCreated();
    }

    @Override
    public Result resetPayPwd(@PathVariable("userNo") String userNo, @RequestParam("newPwd") String newPwd) {
        return successCreated();
    }

    @Override
    public Result setPayPwd(@PathVariable("userNo") String userNo, @RequestParam("newPwd") String newPwd) {
        return successCreated();
    }

    @Override
    public Result isSetPayPwd(@PathVariable("userNum") String userNum) {
        return successGet();
    }

    @Override
    public Result<PropertyBalanceDTO> getPropertyBalance(@PathVariable("userNum") String userNum) {
        return successGet();
    }

    @Override
    public Result<PropertyPointDTO> getPropertyPoint(@PathVariable("userNum") String userNum) {
        PropertyPointDTO dto = new PropertyPointDTO();
        dto.setPoint(BigDecimal.valueOf(100));
        return successGet(dto);
    }

    @Override
    public Result inviteFans(@ModelAttribute PropertyInfoDataParam propertyInfoDataParam) {
    	if (propertyInfoDataParam.getMerchantTransactionTypeEnum() != null && propertyInfoDataParam.getMerchantTransactionTypeEnum()
				.getValue() == MerchantTransactionTypeEnum.INVITE_FANS.getValue()) {
			return successCreated((Object)100);
		}
    	return successCreated();
    }

    @Override
    public Result<Boolean> varifyPayPwd(@RequestParam("userNum") String userNum, @RequestParam("payPwd") String payPwd) {
        return successGet(true);
    }

    @Override
    public Result<PropertyPointAndBalanceDTO> getPropertyInfoMoney(@PathVariable("userNum") String userNum) {
        PropertyPointAndBalanceDTO dto = new PropertyPointAndBalanceDTO();
        dto.setBalance(BigDecimal.valueOf(100));
        dto.setPoint(BigDecimal.valueOf(100));
        return successGet(dto);
    }

    @Override
    public Result<PropertyLoveAccountDTO> selectLoveAccount(@PathVariable("userNum") String userNum) {
        PropertyLoveAccountDTO dto = new PropertyLoveAccountDTO();
        dto.setLoveAccount(BigDecimal.valueOf(100));
        return successGet(dto);
    }

    @Override
    public Result<PropertyInfoFreezeDTO> getPropertyinfoFreeze(@PathVariable("userNum") String userNum) {
        PropertyInfoFreezeDTO dto = new PropertyInfoFreezeDTO();
        dto.setStatus(PropertyinfoFreezeEnum.NO);
        return successGet(dto);
    }

    @Override
    public Result<Page<FreezeDTO>> getFreezeList(@RequestBody FreezeQueryParam param) {
        return successCreated();
    }

    @Override
    public Result validatePoint(@RequestParam("userNum") String userNum, @RequestParam("point") String point) {
        return null;
    }

    @Override
	public Result doHanlderMinusPointByFans(PropertyInfoDataParam propertyInfoDataParam) {
		return successCreated();
	}

}
