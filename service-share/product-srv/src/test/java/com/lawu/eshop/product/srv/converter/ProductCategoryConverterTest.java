/**
 *
 */
package com.lawu.eshop.product.srv.converter;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.product.constant.ProductCategoryTypeEnum;
import com.lawu.eshop.product.dto.OperatorProductCategoryListDTO;
import com.lawu.eshop.product.dto.ProductCategoryDTO;
import com.lawu.eshop.product.dto.ProductCategoryListDTO;
import com.lawu.eshop.product.srv.bo.ProductCategoryBO;
import com.lawu.eshop.product.srv.domain.ProductCategoryeDO;


/**
 * @author lihj
 * @date 2017年7月13日
 */
public class ProductCategoryConverterTest {

    @Test
    public void convertBO() {
        ProductCategoryeDO pcd = new ProductCategoryeDO();
        pcd.setId(1);
        pcd.setName("测试");
        pcd.setLevel(Byte.valueOf("1"));
        pcd.setParentId(2);
        pcd.setPath("123123123");
        pcd.setType(Byte.valueOf("2"));
        pcd.setIsVirtual(false);
        pcd.setImageUrl("image");
        pcd.setOrdinal(Byte.valueOf("3"));
        ProductCategoryBO bo = ProductCategoryConverter.convertBO(pcd);
        Assert.assertEquals(bo.getId(), pcd.getId());
        Assert.assertEquals(bo.getName(), pcd.getName());
        Assert.assertEquals(Byte.valueOf(bo.getLevel().toString()), pcd.getLevel());
        Assert.assertEquals(bo.getParentId(), pcd.getParentId());
        Assert.assertEquals(bo.getPath(), pcd.getPath());
        Assert.assertEquals(Byte.valueOf(bo.getType().toString()), pcd.getType());
        Assert.assertEquals(pcd.getOrdinal().intValue(), bo.getOrdinal().intValue());
        Assert.assertEquals(bo.getImageUrl(), pcd.getImageUrl());
        Assert.assertEquals(bo.getVirtual(), pcd.getIsVirtual());
    }

    @Test
    public void convertDTO() {
        ProductCategoryBO bo = new ProductCategoryBO();
        bo.setId(1);
        bo.setName("测");
        bo.setPath("www.baidu.com");
        bo.setParentId(2);
        bo.setLevel(3);
        bo.setVirtual(false);
        bo.setImageUrl("image");
        bo.setOrdinal(3);
        ProductCategoryDTO dto = ProductCategoryConverter.convertDTO(bo);
        Assert.assertEquals(dto.getId(), bo.getId());
        Assert.assertEquals(dto.getName(), bo.getName());
        Assert.assertEquals(dto.getPath(), bo.getPath());
        Assert.assertEquals(dto.getParentId(), bo.getParentId());
        Assert.assertEquals(dto.getLevel(), bo.getLevel());
        Assert.assertEquals(dto.getOrdinal().intValue(), bo.getOrdinal().intValue());
        Assert.assertEquals(dto.getImageUrl(), bo.getImageUrl());
        Assert.assertEquals(dto.getIsVirtual(), bo.getVirtual());
    }

    @Test
    public void convertBOS() {
        List<ProductCategoryeDO> categoryDOS = new ArrayList<>();
        ProductCategoryeDO pcd = new ProductCategoryeDO();
        pcd.setId(1);
        pcd.setName("测试");
        pcd.setLevel(Byte.valueOf("1"));
        pcd.setParentId(2);
        pcd.setPath("123123123");
        pcd.setType(Byte.valueOf("2"));
        pcd.setIsVirtual(false);
        pcd.setImageUrl("image");
        pcd.setOrdinal(Byte.valueOf("3"));
        categoryDOS.add(pcd);

        List<ProductCategoryBO> categoryBOS = ProductCategoryConverter.convertBOS(categoryDOS);
        Assert.assertEquals(categoryBOS.get(0).getId(), pcd.getId());
        Assert.assertEquals(categoryBOS.get(0).getName(), pcd.getName());
        Assert.assertEquals(Byte.valueOf(categoryBOS.get(0).getLevel().toString()), pcd.getLevel());
        Assert.assertEquals(categoryBOS.get(0).getParentId(), pcd.getParentId());
        Assert.assertEquals(categoryBOS.get(0).getPath(), pcd.getPath());
        Assert.assertEquals(Byte.valueOf(categoryBOS.get(0).getType().toString()), pcd.getType());
        Assert.assertEquals(pcd.getOrdinal().intValue(), categoryBOS.get(0).getOrdinal().intValue());
        Assert.assertEquals(categoryBOS.get(0).getImageUrl(), pcd.getImageUrl());
        Assert.assertEquals(categoryBOS.get(0).getVirtual(), pcd.getIsVirtual());
    }

    @Test
    public void convertDTOS() {
        List<ProductCategoryBO> bos = new ArrayList<>();
        ProductCategoryBO bo = new ProductCategoryBO();
        bo.setId(1);
        bo.setName("测");
        bo.setPath("www.baidu.com");
        bo.setParentId(2);
        bo.setLevel(3);
        bo.setVirtual(false);
        bo.setImageUrl("image");
        bo.setOrdinal(3);
        bos.add(bo);
        List<ProductCategoryDTO> categoryDTOS = ProductCategoryConverter.convertDTOS(bos);
        Assert.assertEquals(categoryDTOS.get(0).getId(), bo.getId());
        Assert.assertEquals(categoryDTOS.get(0).getName(), bo.getName());
        Assert.assertEquals(categoryDTOS.get(0).getPath(), bo.getPath());
        Assert.assertEquals(categoryDTOS.get(0).getParentId(), bo.getParentId());
        Assert.assertEquals(categoryDTOS.get(0).getLevel(), bo.getLevel());
        Assert.assertEquals(categoryDTOS.get(0).getOrdinal().intValue(), bo.getOrdinal().intValue());
        Assert.assertEquals(categoryDTOS.get(0).getImageUrl(), bo.getImageUrl());
        Assert.assertEquals(categoryDTOS.get(0).getIsVirtual(), bo.getVirtual());
    }

    @Test
    public void convertOperatorDTOS() {
        List<ProductCategoryBO> records = new ArrayList<>();
        ProductCategoryBO bo = new ProductCategoryBO();
        bo.setId(1);
        bo.setName("测");
        bo.setPath("www.baidu.com");
        bo.setParentId(2);
        bo.setLevel(3);
        bo.setVirtual(false);
        bo.setImageUrl("image");
        bo.setOrdinal(3);
        bo.setType(ProductCategoryTypeEnum.PRODUCT_CATEGORY_HOT.getVal());
        records.add(bo);
        List<OperatorProductCategoryListDTO> listDTOS = ProductCategoryConverter.convertOperatorDTOS(records);
        Assert.assertEquals(listDTOS.get(0).getId(), bo.getId());
        Assert.assertEquals(listDTOS.get(0).getName(), bo.getName());
        Assert.assertEquals(listDTOS.get(0).getType().getVal(), bo.getType());
        Assert.assertEquals(listDTOS.get(0).getParentId(), bo.getParentId());
        Assert.assertEquals(listDTOS.get(0).getLevel(), bo.getLevel());
        Assert.assertEquals(listDTOS.get(0).getOrdinal().intValue(), bo.getOrdinal().intValue());
        Assert.assertEquals(listDTOS.get(0).getImageUrl(), bo.getImageUrl());
        Assert.assertEquals(listDTOS.get(0).getIsVirtual(), bo.getVirtual());
    }

    @Test
    public void convertHotDTOS() {
        List<ProductCategoryBO> categoryBOS = new ArrayList<>();
        ProductCategoryBO bo = new ProductCategoryBO();
        bo.setId(1);
        bo.setName("测");
        bo.setPath("www.baidu.com");
        bo.setParentId(2);
        bo.setLevel(3);
        bo.setVirtual(false);
        bo.setImageUrl("image");
        bo.setOrdinal(3);
        bo.setType(ProductCategoryTypeEnum.PRODUCT_CATEGORY_HOT.getVal());
        categoryBOS.add(bo);
        List<ProductCategoryListDTO> listDTOS = ProductCategoryConverter.convertHotDTOS(categoryBOS);
        Assert.assertEquals(bo.getId(), listDTOS.get(0).getId());
        Assert.assertEquals(bo.getName(), listDTOS.get(0).getName());
        Assert.assertEquals(bo.getImageUrl(), listDTOS.get(0).getImageUrl());
    }

}
