package com.lawu.eshop.mall.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.mall.dto.DiscountPackageContentDTO;
import com.lawu.eshop.mall.param.foreign.DiscountPackageContentSaveForeignParam;
import com.lawu.eshop.mall.param.foreign.DiscountPackageContentUpdateForeignParam;
import com.lawu.eshop.mall.srv.bo.DiscountPackageContentBO;
import com.lawu.eshop.mall.srv.domain.DiscountPackageContentDO;

/**
 * @author zhangyong
 * @date 2017/7/19.
 */
public class DiscountPackageContentConverterTest {

    @Test
    public void convert() {
        DiscountPackageContentDO packageContentDO = new DiscountPackageContentDO();
        packageContentDO.setDiscountPackageId(1L);
        packageContentDO.setGmtCreate(new Date());
        packageContentDO.setGmtModified(new Date());
        packageContentDO.setId(1L);
        packageContentDO.setName("NAME");
        packageContentDO.setQuantity(1);
        packageContentDO.setStatus(StatusEnum.VALID.getValue());
        packageContentDO.setSubtotal(BigDecimal.TEN);
        packageContentDO.setUnit("UNIT");
        packageContentDO.setUnitPrice(BigDecimal.ONE);
        DiscountPackageContentBO discountPackageContentBO = DiscountPackageContentConverter.convert(packageContentDO);
        Assert.assertTrue(packageContentDO.getDiscountPackageId().equals(discountPackageContentBO.getDiscountPackageId()));
        Assert.assertTrue(packageContentDO.getGmtCreate().equals(discountPackageContentBO.getGmtCreate()));
        Assert.assertTrue(packageContentDO.getGmtModified().equals(discountPackageContentBO.getGmtModified()));
        Assert.assertTrue(packageContentDO.getId().equals(discountPackageContentBO.getId()));
        Assert.assertTrue(packageContentDO.getName().equals(discountPackageContentBO.getName()));
        Assert.assertTrue(packageContentDO.getQuantity().equals(discountPackageContentBO.getQuantity()));
        Assert.assertTrue(packageContentDO.getStatus().equals(discountPackageContentBO.getStatus().getValue()));
        Assert.assertTrue(packageContentDO.getSubtotal().equals(discountPackageContentBO.getSubtotal()));
        Assert.assertTrue(packageContentDO.getUnit().equals(discountPackageContentBO.getUnit()));
    }

    @Test
    public void convertDiscountPackageContentBOList() {
        List<DiscountPackageContentDO> discountPackageContentDOList = new ArrayList<>();
        DiscountPackageContentDO discountPackageContentDO = new DiscountPackageContentDO();
        discountPackageContentDO.setDiscountPackageId(1L);
        discountPackageContentDO.setGmtCreate(new Date());
        discountPackageContentDO.setGmtModified(new Date());
        discountPackageContentDO.setId(1L);
        discountPackageContentDO.setName("NAME");
        discountPackageContentDO.setQuantity(1);
        discountPackageContentDO.setStatus(StatusEnum.VALID.getValue());
        discountPackageContentDO.setSubtotal(BigDecimal.TEN);
        discountPackageContentDO.setUnit("UNIT");
        discountPackageContentDO.setUnitPrice(BigDecimal.ONE);
        discountPackageContentDOList.add(discountPackageContentDO);
        List<DiscountPackageContentBO> list = DiscountPackageContentConverter.convertDiscountPackageContentBOList(discountPackageContentDOList);
        Assert.assertTrue(discountPackageContentDOList.get(0).getDiscountPackageId().equals(list.get(0).getDiscountPackageId()));
        Assert.assertTrue(discountPackageContentDOList.get(0).getGmtCreate().equals(list.get(0).getGmtCreate()));
        Assert.assertTrue(discountPackageContentDOList.get(0).getGmtModified().equals(list.get(0).getGmtModified()));
        Assert.assertTrue(discountPackageContentDOList.get(0).getId().equals(list.get(0).getId()));
        Assert.assertTrue(discountPackageContentDOList.get(0).getName().equals(list.get(0).getName()));
        Assert.assertTrue(discountPackageContentDOList.get(0).getQuantity().equals(list.get(0).getQuantity()));
        Assert.assertTrue(discountPackageContentDOList.get(0).getStatus().equals(list.get(0).getStatus().getValue()));
        Assert.assertTrue(discountPackageContentDOList.get(0).getSubtotal().equals(list.get(0).getSubtotal()));
        Assert.assertTrue(discountPackageContentDOList.get(0).getUnit().equals(list.get(0).getUnit()));

    }

    @Test
    public void convertDO() {
        DiscountPackageContentSaveForeignParam contentSaveForeignParam = new DiscountPackageContentSaveForeignParam();
        contentSaveForeignParam.setName("name");
        contentSaveForeignParam.setQuantity(1);
        contentSaveForeignParam.setUnit("unit");
        contentSaveForeignParam.setUnitPrice(BigDecimal.TEN);
        DiscountPackageContentDO discountPackageContentDO = DiscountPackageContentConverter.convert(1L, contentSaveForeignParam);
        Assert.assertTrue(contentSaveForeignParam.getName().equals(discountPackageContentDO.getName()));
        Assert.assertTrue(contentSaveForeignParam.getQuantity().equals(discountPackageContentDO.getQuantity()));
        Assert.assertTrue(contentSaveForeignParam.getUnitPrice().multiply(new BigDecimal(contentSaveForeignParam.getQuantity())).equals(discountPackageContentDO.getSubtotal()));

    }

    @Test
    public void convertDO2() {
        DiscountPackageContentUpdateForeignParam param = new DiscountPackageContentUpdateForeignParam();
        param.setName("name");
        param.setQuantity(1);
        param.setUnit("unit");
        param.setUnitPrice(BigDecimal.ONE);
        DiscountPackageContentDO discountPackageContentDO = DiscountPackageContentConverter.convert(1L, param);
        Assert.assertTrue(param.getName().equals(discountPackageContentDO.getName()));
        Assert.assertTrue(param.getQuantity().equals(discountPackageContentDO.getQuantity()));
        Assert.assertTrue(param.getUnitPrice().multiply(new BigDecimal(param.getQuantity())).equals(discountPackageContentDO.getSubtotal()));
    }

    @Test
    public void convertDO3() {
        DiscountPackageContentUpdateForeignParam param = new DiscountPackageContentUpdateForeignParam();
        param.setName("name");
        param.setQuantity(1);
        param.setUnit("unit");
        param.setUnitPrice(BigDecimal.ONE);
        param.setId(1L);
        DiscountPackageContentDO discountPackageContentDO = DiscountPackageContentConverter.convert(param);
        Assert.assertTrue(param.getName().equals(discountPackageContentDO.getName()));
        Assert.assertTrue(param.getUnit().equals(discountPackageContentDO.getUnit()));
        Assert.assertTrue(param.getId().equals(discountPackageContentDO.getId()));
        Assert.assertTrue(param.getQuantity().equals(discountPackageContentDO.getQuantity()));
        Assert.assertTrue(param.getUnitPrice().multiply(new BigDecimal(param.getQuantity())).equals(discountPackageContentDO.getSubtotal()));
    }

    @Test
    public void convertDTO() {
        DiscountPackageContentBO packageContentBO = new DiscountPackageContentBO();
        packageContentBO.setId(1L);
        packageContentBO.setName("NAME");
        packageContentBO.setQuantity(1);
        packageContentBO.setSubtotal(BigDecimal.TEN);
        packageContentBO.setUnit("unit");
        packageContentBO.setUnitPrice(BigDecimal.ONE);
        DiscountPackageContentDTO contentDTO = DiscountPackageContentConverter.convert(packageContentBO);
        Assert.assertTrue(packageContentBO.getName().equals(contentDTO.getName()));
        Assert.assertTrue(packageContentBO.getUnit().equals(contentDTO.getUnit()));
        Assert.assertTrue(packageContentBO.getId().equals(contentDTO.getId()));
        Assert.assertTrue(packageContentBO.getQuantity().equals(contentDTO.getQuantity()));
        Assert.assertTrue(packageContentBO.getUnitPrice().equals(contentDTO.getUnitPrice()));
        Assert.assertTrue(packageContentBO.getSubtotal().equals(contentDTO.getSubtotal()));
    }

    @Test
    public void convertDiscountPackageContentDTOList(){
        List<DiscountPackageContentBO> discountPackageContentBOList = new ArrayList<>();
        DiscountPackageContentBO packageContentBO = new DiscountPackageContentBO();
        packageContentBO.setId(1L);
        packageContentBO.setName("NAME");
        packageContentBO.setQuantity(1);
        packageContentBO.setSubtotal(BigDecimal.TEN);
        packageContentBO.setUnit("unit");
        packageContentBO.setUnitPrice(BigDecimal.ONE);
        discountPackageContentBOList.add(packageContentBO);
        List<DiscountPackageContentDTO> list = DiscountPackageContentConverter.convertDiscountPackageContentDTOList(discountPackageContentBOList);
        Assert.assertTrue(discountPackageContentBOList.get(0).getName().equals(list.get(0).getName()));
        Assert.assertTrue(discountPackageContentBOList.get(0).getUnit().equals(list.get(0).getUnit()));
        Assert.assertTrue(discountPackageContentBOList.get(0).getId().equals(list.get(0).getId()));
        Assert.assertTrue(discountPackageContentBOList.get(0).getQuantity().equals(list.get(0).getQuantity()));
        Assert.assertTrue(discountPackageContentBOList.get(0).getUnitPrice().equals(list.get(0).getUnitPrice()));
        Assert.assertTrue(discountPackageContentBOList.get(0).getSubtotal().equals(list.get(0).getSubtotal()));
    }
}
