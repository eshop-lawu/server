package com.lawu.eshop.member.api.mock.service;

import com.lawu.eshop.mall.constants.MerchantFavoredTypeEnum;
import com.lawu.eshop.mall.dto.MerchantFavoredDTO;
import com.lawu.eshop.member.api.service.MerchantFavoredService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.Date;


@Service
public class MockMerchantFavoredService extends BaseController implements MerchantFavoredService {


    @Override
    public Result<MerchantFavoredDTO> findFavoredByMerchantId(@PathVariable("merchantId") Long merchantId) {
        MerchantFavoredDTO dto = new MerchantFavoredDTO();
        dto.setReachAmount(new BigDecimal("10"));
        dto.setFavoredAmount(new BigDecimal("10"));
        dto.setDiscountRate(new BigDecimal("1"));
        dto.setId(1L);
        dto.setValidDayBeginTime("2017");
        dto.setValidDayEndTime("1");
        dto.setValidWeekTime("2");
        dto.setEntireBeginTime(new Date());
        dto.setEntireEndTime(new Date());
        dto.setTypeEnum(MerchantFavoredTypeEnum.TYPE_DISCOUNT);
        return successCreated(dto);
    }

    @Override
    public Result<MerchantFavoredDTO> findFavoredById(@PathVariable("id") Long id) {
        MerchantFavoredDTO dto = new MerchantFavoredDTO();
        dto.setReachAmount(new BigDecimal("10"));
        dto.setFavoredAmount(new BigDecimal("10"));
        dto.setDiscountRate(new BigDecimal("1"));
        dto.setId(1L);
        dto.setValidDayBeginTime("2017");
        dto.setValidDayEndTime("1");
        dto.setValidWeekTime("2");
        dto.setEntireBeginTime(new Date());
        dto.setEntireEndTime(new Date());
        dto.setTypeEnum(MerchantFavoredTypeEnum.TYPE_DISCOUNT);
        return successCreated(dto);
    }
}
