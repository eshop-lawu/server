package com.lawu.eshop.mall.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.mall.constants.DiscountPackageStatusEnum;
import com.lawu.eshop.mall.dto.DiscountPackageQueryDTO;
import com.lawu.eshop.mall.param.DiscountPackageSaveParam;
import com.lawu.eshop.mall.param.DiscountPackageUpdateParam;
import com.lawu.eshop.mall.param.foreign.DiscountPackageContentSaveForeignParam;
import com.lawu.eshop.mall.param.foreign.DiscountPackageContentUpdateForeignParam;
import com.lawu.eshop.mall.srv.bo.DiscountPackageBO;
import com.lawu.eshop.mall.srv.domain.DiscountPackageDO;
import com.lawu.framework.core.page.Page;

/**
 * @author zhangyong
 * @date 2017/7/19.
 */
public class DiscountPackageConverterTest {

    @Test
    public void convert() {
        DiscountPackageDO discountPackageDO = new DiscountPackageDO();
        discountPackageDO.setCoverImage("image");
        discountPackageDO.setGmtCreate(new Date());
        discountPackageDO.setGmtModified(new Date());
        discountPackageDO.setGmtUp(new Date());
        discountPackageDO.setId(1L);
        discountPackageDO.setIsReservation(true);
        discountPackageDO.setMerchantId(2L);
        discountPackageDO.setMerchantStoreId(3L);
        discountPackageDO.setName("name");
        discountPackageDO.setOriginalPrice(BigDecimal.TEN);
        discountPackageDO.setOtherInstructions("instruction");
        discountPackageDO.setPrice(BigDecimal.TEN);
        discountPackageDO.setStatus(DiscountPackageStatusEnum.UP.getValue());
        discountPackageDO.setUseRules("rule");
        discountPackageDO.setUseTimeBegin(new Date());
        discountPackageDO.setUseTimeEnd(new Date());
        discountPackageDO.setUseTimeWeek("week");
        discountPackageDO.setValidityPeriodBegin(new Date());
        discountPackageDO.setValidityPeriodEnd(new Date());
        DiscountPackageBO discountPackageBO = DiscountPackageConverter.convert(discountPackageDO);
        Assert.assertEquals(discountPackageDO.getCoverImage(), discountPackageBO.getCoverImage());
        Assert.assertEquals(discountPackageDO.getGmtCreate(), discountPackageBO.getGmtCreate());
        Assert.assertEquals(discountPackageDO.getGmtModified(), discountPackageBO.getGmtModified());
        Assert.assertEquals(discountPackageDO.getGmtUp(), discountPackageBO.getGmtUp());
        Assert.assertEquals(discountPackageDO.getId(), discountPackageBO.getId());
        Assert.assertEquals(discountPackageDO.getIsReservation(), discountPackageBO.getIsReservation());
        Assert.assertEquals(discountPackageDO.getMerchantId(), discountPackageBO.getMerchantId());
        Assert.assertEquals(discountPackageDO.getMerchantStoreId(), discountPackageBO.getMerchantStoreId());
        Assert.assertEquals(discountPackageDO.getName(), discountPackageBO.getName());
        Assert.assertEquals(discountPackageDO.getOriginalPrice(), discountPackageBO.getOriginalPrice());
        Assert.assertEquals(discountPackageDO.getOtherInstructions(), discountPackageBO.getOtherInstructions());
        Assert.assertEquals(discountPackageDO.getPrice(), discountPackageBO.getPrice());
        Assert.assertEquals(discountPackageDO.getStatus(), discountPackageBO.getStatus().getValue());
        Assert.assertEquals(discountPackageDO.getUseRules(), discountPackageBO.getUseRules());
        Assert.assertEquals(discountPackageDO.getUseTimeBegin(), discountPackageBO.getUseTimeBegin());
        Assert.assertEquals(discountPackageDO.getUseTimeEnd(), discountPackageBO.getUseTimeEnd());
        Assert.assertEquals(discountPackageDO.getUseTimeWeek(), discountPackageBO.getUseTimeWeek());
        Assert.assertEquals(discountPackageDO.getValidityPeriodBegin(), discountPackageBO.getValidityPeriodBegin());
        Assert.assertEquals(discountPackageDO.getValidityPeriodEnd(), discountPackageBO.getValidityPeriodEnd());
        Assert.assertEquals(discountPackageDO.getPrice(), discountPackageBO.getPrice());

    }

    @Test
    public void convertDiscountPackageBOList() {
        List<DiscountPackageDO> discountPackageDOList = new ArrayList<>();
        DiscountPackageDO discountPackageDO = new DiscountPackageDO();
        discountPackageDO.setCoverImage("image");
        discountPackageDO.setGmtCreate(new Date());
        discountPackageDO.setGmtModified(new Date());
        discountPackageDO.setGmtUp(new Date());
        discountPackageDO.setId(1L);
        discountPackageDO.setIsReservation(true);
        discountPackageDO.setMerchantId(2L);
        discountPackageDO.setMerchantStoreId(3L);
        discountPackageDO.setName("name");
        discountPackageDO.setOriginalPrice(BigDecimal.TEN);
        discountPackageDO.setOtherInstructions("instruction");
        discountPackageDO.setPrice(BigDecimal.TEN);
        discountPackageDO.setStatus(DiscountPackageStatusEnum.UP.getValue());
        discountPackageDO.setUseRules("rule");
        discountPackageDO.setUseTimeBegin(new Date());
        discountPackageDO.setUseTimeEnd(new Date());
        discountPackageDO.setUseTimeWeek("week");
        discountPackageDO.setValidityPeriodBegin(new Date());
        discountPackageDO.setValidityPeriodEnd(new Date());
        discountPackageDOList.add(discountPackageDO);
        List<DiscountPackageBO> list = DiscountPackageConverter.convertDiscountPackageBOList(discountPackageDOList);
        Assert.assertEquals(discountPackageDOList.get(0).getCoverImage(), list.get(0).getCoverImage());
        Assert.assertEquals(discountPackageDOList.get(0).getGmtCreate(), list.get(0).getGmtCreate());
        Assert.assertEquals(discountPackageDOList.get(0).getGmtModified(), list.get(0).getGmtModified());
        Assert.assertEquals(discountPackageDOList.get(0).getGmtUp(), list.get(0).getGmtUp());
        Assert.assertEquals(discountPackageDOList.get(0).getId(), list.get(0).getId());
        Assert.assertEquals(discountPackageDOList.get(0).getIsReservation(), list.get(0).getIsReservation());
        Assert.assertEquals(discountPackageDOList.get(0).getMerchantId(), list.get(0).getMerchantId());
        Assert.assertEquals(discountPackageDOList.get(0).getMerchantStoreId(), list.get(0).getMerchantStoreId());
        Assert.assertEquals(discountPackageDOList.get(0).getName(), list.get(0).getName());
        Assert.assertEquals(discountPackageDOList.get(0).getOriginalPrice(), list.get(0).getOriginalPrice());
        Assert.assertEquals(discountPackageDOList.get(0).getOtherInstructions(), list.get(0).getOtherInstructions());
        Assert.assertEquals(discountPackageDOList.get(0).getPrice(), list.get(0).getPrice());
        Assert.assertEquals(discountPackageDOList.get(0).getStatus(), list.get(0).getStatus().getValue());
        Assert.assertEquals(discountPackageDOList.get(0).getUseRules(), list.get(0).getUseRules());
        Assert.assertEquals(discountPackageDOList.get(0).getUseTimeBegin(), list.get(0).getUseTimeBegin());
        Assert.assertEquals(discountPackageDOList.get(0).getUseTimeEnd(), list.get(0).getUseTimeEnd());
        Assert.assertEquals(discountPackageDOList.get(0).getUseTimeWeek(), list.get(0).getUseTimeWeek());
        Assert.assertEquals(discountPackageDOList.get(0).getValidityPeriodBegin(), list.get(0).getValidityPeriodBegin());
        Assert.assertEquals(discountPackageDOList.get(0).getValidityPeriodEnd(), list.get(0).getValidityPeriodEnd());
        Assert.assertEquals(discountPackageDOList.get(0).getPrice(), list.get(0).getPrice());

    }

    @Test
    public void convertDO() {
        DiscountPackageSaveParam param = new DiscountPackageSaveParam();
        param.setCoverImage("test");
        param.setIsReservation(true);
        param.setMerchantStoreId(1L);
        param.setName("NAME");
        param.setOtherInstructions("instructions");
        param.setPrice(BigDecimal.TEN);
        param.setUseRules("rule");
        param.setUseTimeBegin(new Date());
        param.setUseTimeEnd(new Date());
        param.setUseTimeWeek("week");
        param.setValidityPeriodBegin(new Date());
        param.setValidityPeriodEnd(new Date());
        List<DiscountPackageContentSaveForeignParam> list = new ArrayList<>();
        DiscountPackageContentSaveForeignParam saveForeignParam = new DiscountPackageContentSaveForeignParam();
        saveForeignParam.setUnitPrice(BigDecimal.ONE);
        saveForeignParam.setUnit("test");
        saveForeignParam.setQuantity(1);
        saveForeignParam.setName("name");
        list.add(saveForeignParam);
        param.setDiscountPackageContents(list);
        DiscountPackageDO discountPackageDO = DiscountPackageConverter.convert(1L, param);
        Assert.assertEquals(param.getCoverImage(), discountPackageDO.getCoverImage());
        Assert.assertEquals(param.getIsReservation(), discountPackageDO.getIsReservation());
        Assert.assertEquals(param.getMerchantStoreId(), discountPackageDO.getMerchantStoreId());
        Assert.assertEquals(param.getName(), discountPackageDO.getName());
    }

    @Test
    public void convertDO2() {
        DiscountPackageUpdateParam param = new DiscountPackageUpdateParam();
        param.setCoverImage("image");
        param.setIsReservation(true);
        param.setName("name");
        List<DiscountPackageContentUpdateForeignParam> list = new ArrayList<>();
        DiscountPackageContentUpdateForeignParam saveForeignParam = new DiscountPackageContentUpdateForeignParam();
        saveForeignParam.setUnitPrice(BigDecimal.ONE);
        saveForeignParam.setUnit("test");
        saveForeignParam.setQuantity(1);
        saveForeignParam.setName("name");
        list.add(saveForeignParam);
        param.setDiscountPackageContents(list);
        param.setOtherInstructions("instruction");
        param.setPrice(BigDecimal.TEN);
        param.setUseRules("rule");
        param.setUseTimeBegin(new Date());
        param.setUseTimeEnd(new Date());
        param.setUseTimeWeek("test");
        param.setValidityPeriodBegin(new Date());
        param.setValidityPeriodEnd(new Date());

        DiscountPackageDO discountPackageDO = DiscountPackageConverter.convert(1L, param);
        Assert.assertEquals(param.getCoverImage(), discountPackageDO.getCoverImage());
        Assert.assertEquals(param.getIsReservation(), discountPackageDO.getIsReservation());
        Assert.assertEquals(param.getPrice(), discountPackageDO.getPrice());
        Assert.assertEquals(param.getName(), discountPackageDO.getName());
    }

    @Test
    public void convertDTO() {
        DiscountPackageBO discountPackageBO = new DiscountPackageBO();
        discountPackageBO.setCoverImage("test");
        discountPackageBO.setGmtUp(new Date());
        discountPackageBO.setId(1L);
        discountPackageBO.setIsReservation(true);
        discountPackageBO.setName("name");
        discountPackageBO.setOriginalPrice(BigDecimal.TEN);
        discountPackageBO.setPrice(BigDecimal.TEN);
        DiscountPackageQueryDTO discountPackageQueryDTO = DiscountPackageConverter.convert(discountPackageBO);
        Assert.assertEquals(discountPackageBO.getCoverImage(), discountPackageQueryDTO.getCoverImage());
        Assert.assertEquals(discountPackageBO.getIsReservation(), discountPackageQueryDTO.getIsReservation());
        Assert.assertEquals(discountPackageBO.getPrice(), discountPackageQueryDTO.getPrice());
        Assert.assertEquals(discountPackageBO.getName(), discountPackageQueryDTO.getName());
    }

    @Test
    public void convertDiscountPackageQueryDTOList() {
        List<DiscountPackageBO> discountPackageBOList = new ArrayList<>();
        DiscountPackageBO discountPackageBO = new DiscountPackageBO();
        discountPackageBO.setCoverImage("test");
        discountPackageBO.setGmtUp(new Date());
        discountPackageBO.setId(1L);
        discountPackageBO.setIsReservation(true);
        discountPackageBO.setName("name");
        discountPackageBO.setOriginalPrice(BigDecimal.TEN);
        discountPackageBO.setPrice(BigDecimal.TEN);
        discountPackageBOList.add(discountPackageBO);
        List<DiscountPackageQueryDTO> list = DiscountPackageConverter.convertDiscountPackageQueryDTOList(discountPackageBOList);
        Assert.assertEquals(discountPackageBOList.get(0).getCoverImage(), list.get(0).getCoverImage());
        Assert.assertEquals(discountPackageBOList.get(0).getIsReservation(), list.get(0).getIsReservation());
        Assert.assertEquals(discountPackageBOList.get(0).getPrice(), list.get(0).getPrice());
        Assert.assertEquals(discountPackageBOList.get(0).getName(), list.get(0).getName());
    }

    @Test
    public void convertDiscountPackageQueryDTOPage(){
        List<DiscountPackageBO> discountPackageBOList = new ArrayList<>();
        DiscountPackageBO discountPackageBO = new DiscountPackageBO();
        discountPackageBO.setCoverImage("test");
        discountPackageBO.setGmtUp(new Date());
        discountPackageBO.setId(1L);
        discountPackageBO.setIsReservation(true);
        discountPackageBO.setName("name");
        discountPackageBO.setOriginalPrice(BigDecimal.TEN);
        discountPackageBO.setPrice(BigDecimal.TEN);
        discountPackageBOList.add(discountPackageBO);
        discountPackageBOList.add(discountPackageBO);
        Page<DiscountPackageQueryDTO> page =DiscountPackageConverter.convertDiscountPackageQueryDTOPage(discountPackageBOList);
        Assert.assertEquals(discountPackageBOList.get(0).getCoverImage(), page.getRecords().get(0).getCoverImage());
        Assert.assertEquals(discountPackageBOList.get(0).getIsReservation(),  page.getRecords().get(0).getIsReservation());
        Assert.assertEquals(discountPackageBOList.get(0).getPrice(),  page.getRecords().get(0).getPrice());
        Assert.assertEquals(discountPackageBOList.get(0).getName(),  page.getRecords().get(0).getName());
    }
}
