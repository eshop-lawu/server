package com.lawu.eshop.mall.srv.converter;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.mall.constants.DiscountPackageStatusEnum;
import com.lawu.eshop.mall.dto.DiscountPackageDetailDTO;
import com.lawu.eshop.mall.srv.bo.DiscountPackageExtendBO;
import com.lawu.eshop.mall.srv.domain.extend.DiscountPackageExtendDO;

/**
 * @author zhangyong
 * @date 2017/7/19.
 */
public class DiscountPackageExtendConverterTest {

    @Test
    public void convert(){
        DiscountPackageExtendDO discountPackageExtendDO = new DiscountPackageExtendDO();
        discountPackageExtendDO.setCoverImage("image");
        discountPackageExtendDO.setGmtCreate(new Date());
        discountPackageExtendDO.setGmtModified(new Date());
        discountPackageExtendDO.setGmtUp(new Date());
        discountPackageExtendDO.setId(1L);
        discountPackageExtendDO.setIsReservation(true);
        discountPackageExtendDO.setMerchantId(1L);
        discountPackageExtendDO.setMerchantStoreId(1L);
        discountPackageExtendDO.setName("NAME");
        discountPackageExtendDO.setOriginalPrice(BigDecimal.TEN);
        discountPackageExtendDO.setOtherInstructions("instruction");
        discountPackageExtendDO.setPrice(BigDecimal.TEN);
        discountPackageExtendDO.setStatus(DiscountPackageStatusEnum.UP.getValue());
        discountPackageExtendDO.setUseRules("rule");
        discountPackageExtendDO.setUseTimeBegin(new Date());
        discountPackageExtendDO.setUseTimeEnd(new Date());
        discountPackageExtendDO.setUseTimeWeek("week");
        discountPackageExtendDO.setValidityPeriodBegin(new Date());
        discountPackageExtendDO.setValidityPeriodEnd(new Date());
        DiscountPackageExtendBO extendBO = DiscountPackageExtendConverter.convert(discountPackageExtendDO);
        Assert.assertEquals(discountPackageExtendDO.getPrice(),extendBO.getPrice());
        Assert.assertEquals(discountPackageExtendDO.getName(),extendBO.getName());
    }

    @Test
    public void convertDTO(){
        DiscountPackageExtendBO extendBO = new DiscountPackageExtendBO();
        extendBO.setCoverImage("image");
        extendBO.setGmtCreate(new Date());
        extendBO.setGmtModified(new Date());
        extendBO.setGmtUp(new Date());
        extendBO.setId(1L);
        extendBO.setIsReservation(true);
        extendBO.setMerchantId(1L);
        extendBO.setMerchantStoreId(1L);
        extendBO.setName("NAME");
        extendBO.setOriginalPrice(BigDecimal.TEN);
        extendBO.setOtherInstructions("instruction");
        extendBO.setPrice(BigDecimal.TEN);
        extendBO.setStatus(DiscountPackageStatusEnum.UP);
        extendBO.setUseRules("rule");
        extendBO.setUseTimeBegin(new Date());
        extendBO.setUseTimeEnd(new Date());
        extendBO.setUseTimeWeek("week");
        extendBO.setValidityPeriodBegin(new Date());
        extendBO.setValidityPeriodEnd(new Date());
        DiscountPackageDetailDTO packageDetailDTO = DiscountPackageExtendConverter.convert(extendBO);
        Assert.assertEquals(extendBO.getPrice(),packageDetailDTO.getPrice());
        Assert.assertEquals(extendBO.getName(),packageDetailDTO.getName());
    }
}
