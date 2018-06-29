package com.lawu.eshop.mall.srv.converter;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.mall.constants.MerchantFavoredTypeEnum;
import com.lawu.eshop.mall.dto.MerchantFavoredDTO;
import com.lawu.eshop.mall.srv.bo.MerchantFavoredBO;
import com.lawu.eshop.mall.srv.domain.MerchantFavoredDO;

/**
 * @author zhangyong
 * @date 2017/7/19.
 */
public class MerchantFavoredConverterTest {

    @Test
    public void coverBO(){
        MerchantFavoredDO merchantFavoredDO = new MerchantFavoredDO();
        merchantFavoredDO.setId(1L);
        merchantFavoredDO.setType(MerchantFavoredTypeEnum.TYPE_DISCOUNT.val);
        merchantFavoredDO.setValidDayBeginTime("10:00");
        merchantFavoredDO.setValidDayEndTime("12:00");
        merchantFavoredDO.setValidWeekTime("每天");
        merchantFavoredDO.setDiscountRate(BigDecimal.ONE);
        merchantFavoredDO.setFavoredAmount(BigDecimal.TEN);
        merchantFavoredDO.setReachAmount(BigDecimal.ONE);
        merchantFavoredDO.setEntireBeginTime(new Date());
        merchantFavoredDO.setEntireEndTime(new Date());

        MerchantFavoredBO merchantFavoredBO = MerchantFavoredConverter.coverBO(merchantFavoredDO);
        Assert.assertEquals(merchantFavoredDO.getId(),merchantFavoredBO.getId());
        Assert.assertEquals(merchantFavoredDO.getType(),merchantFavoredBO.getType());
    }

    @Test
    public void coverDTO(){
        MerchantFavoredBO merchantFavoredBO = new MerchantFavoredBO();
        merchantFavoredBO.setId(1L);
        merchantFavoredBO.setType(MerchantFavoredTypeEnum.TYPE_DISCOUNT.val);
        merchantFavoredBO.setValidDayBeginTime("10:00");
        merchantFavoredBO.setValidDayEndTime("12:00");
        merchantFavoredBO.setValidWeekTime("每天");
        merchantFavoredBO.setDiscountRate(BigDecimal.ONE);
        merchantFavoredBO.setFavoredAmount(BigDecimal.TEN);
        merchantFavoredBO.setReachAmount(BigDecimal.ONE);
        merchantFavoredBO.setEntireBeginTime(new Date());
        merchantFavoredBO.setEntireEndTime(new Date());
        MerchantFavoredDTO merchantFavoredDTO =MerchantFavoredConverter.coverDTO(merchantFavoredBO);
        Assert.assertEquals(merchantFavoredBO.getId(),merchantFavoredDTO.getId());
        Assert.assertEquals(merchantFavoredBO.getType(),merchantFavoredDTO.getTypeEnum().val);
    }
}
