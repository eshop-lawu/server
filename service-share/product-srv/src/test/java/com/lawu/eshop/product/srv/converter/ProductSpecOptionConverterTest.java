package com.lawu.eshop.product.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.product.dto.OperatorProductSpecOptionDTO;
import com.lawu.eshop.product.dto.ProductSpecOptionDetailDTO;
import com.lawu.eshop.product.dto.ProductSpecOptionListDTO;
import com.lawu.eshop.product.srv.bo.OperatorProductSpecOptionBO;
import com.lawu.eshop.product.srv.bo.ProductSpecOptionBO;
import com.lawu.eshop.product.srv.domain.ProductSpecOptionDO;
import com.lawu.eshop.product.srv.domain.extend.OperatorProductSpecOptionDOView;

/**
 * @author zhangyong
 * @date 2018/4/19.
 */
public class ProductSpecOptionConverterTest {

    @Test
    public void convertBOS() {
        List<ProductSpecOptionDO> list = new ArrayList<>();
        ProductSpecOptionDO optionDO = new ProductSpecOptionDO();
        optionDO.setIconUrl("url");
        optionDO.setOptionName("size");
        optionDO.setId(1L);
        optionDO.setProductSpecId(2L);
        list.add(optionDO);
        List<ProductSpecOptionBO> optionBOS = ProductSpecOptionConverter.convertBOS(list);
        Assert.assertEquals(optionDO.getId(), optionBOS.get(0).getId());
        Assert.assertEquals(optionDO.getIconUrl(), optionBOS.get(0).getIconUrl());
        Assert.assertEquals(optionDO.getProductSpecId(), optionBOS.get(0).getProductSpecId());
        Assert.assertEquals(optionDO.getOptionName(), optionBOS.get(0).getOptionName());
    }

    @Test
    public void convertDTOS() {
        List<ProductSpecOptionBO> list = new ArrayList<>();
        ProductSpecOptionBO optionBO = new ProductSpecOptionBO();
        optionBO.setIconUrl("url");
        optionBO.setOptionName("size");
        optionBO.setId(1L);
        optionBO.setProductSpecId(2L);
        list.add(optionBO);
        List<ProductSpecOptionListDTO> listDTOS = ProductSpecOptionConverter.convertDTOS(list);
        Assert.assertEquals(optionBO.getId(), listDTOS.get(0).getId());
        Assert.assertEquals(optionBO.getIconUrl(), listDTOS.get(0).getIconUrl());
        Assert.assertEquals(optionBO.getProductSpecId(), listDTOS.get(0).getProductSpecId());
        Assert.assertEquals(optionBO.getOptionName(), listDTOS.get(0).getOptionName());
    }

    @Test
    public void convertBO() {
        ProductSpecOptionDO optionDO = new ProductSpecOptionDO();
        optionDO.setIconUrl("url");
        optionDO.setOptionName("name");
        optionDO.setId(1L);
        optionDO.setProductSpecId(2L);
        optionDO.setOrdinal((byte) 3);
        ProductSpecOptionBO optionBO = ProductSpecOptionConverter.convertBO(optionDO);

        Assert.assertEquals(optionBO.getId(), optionDO.getId());
        Assert.assertEquals(optionBO.getIconUrl(), optionDO.getIconUrl());
        Assert.assertEquals(optionBO.getProductSpecId(), optionDO.getProductSpecId());
        Assert.assertEquals(optionBO.getOptionName(), optionDO.getOptionName());
        Assert.assertEquals(optionBO.getOrdinal().intValue(), optionDO.getOrdinal().intValue());
    }

    @Test
    public void convertDTO() {
        ProductSpecOptionBO optionBO = new ProductSpecOptionBO();
        optionBO.setIconUrl("url");
        optionBO.setOptionName("name");
        optionBO.setId(1L);
        optionBO.setProductSpecId(2L);
        optionBO.setOrdinal(3);
        ProductSpecOptionDetailDTO detailDTO = ProductSpecOptionConverter.convertDTO(optionBO);

        Assert.assertEquals(optionBO.getId(), detailDTO.getId());
        Assert.assertEquals(optionBO.getIconUrl(), detailDTO.getIconUrl());
        Assert.assertEquals(optionBO.getProductSpecId(), detailDTO.getProductSpecId());
        Assert.assertEquals(optionBO.getOptionName(), detailDTO.getOptionName());
        Assert.assertEquals(optionBO.getOrdinal().intValue(), detailDTO.getOrdinal().intValue());
    }

    @Test
    public void convertOperatorBOS() {
        List<OperatorProductSpecOptionDOView> optionDOViews = new ArrayList<>();
        OperatorProductSpecOptionDOView optionDO = new OperatorProductSpecOptionDOView();
        optionDO.setIconUrl("url");
        optionDO.setOptionName("name");
        optionDO.setId(1L);
        optionDO.setProductSpecId(2L);
        optionDO.setOrdinal((byte) 3);
        optionDO.setGmtCreate(new Date());
        optionDO.setSpecName("food");
        optionDOViews.add(optionDO);
        List<OperatorProductSpecOptionBO> optionBOS = ProductSpecOptionConverter.convertOperatorBOS(optionDOViews);
        Assert.assertEquals(optionDO.getId(), optionBOS.get(0).getId());
        Assert.assertEquals(optionDO.getIconUrl(), optionBOS.get(0).getIconUrl());
        Assert.assertEquals(optionDO.getProductSpecId(), optionBOS.get(0).getProductSpecId());
        Assert.assertEquals(optionDO.getOptionName(), optionBOS.get(0).getOptionName());
        Assert.assertEquals(optionDO.getOrdinal().intValue(), optionBOS.get(0).getOrdinal().intValue());
        Assert.assertEquals(optionDO.getGmtCreate(), optionBOS.get(0).getGmtCreate());
        Assert.assertEquals(optionDO.getSpecName(), optionBOS.get(0).getSpecName());
    }

    @Test
    public void convertOperatorDTOS() {
        List<OperatorProductSpecOptionBO> optionBOS = new ArrayList<>();
        OperatorProductSpecOptionBO optionBO = new OperatorProductSpecOptionBO();
        optionBO.setIconUrl("url");
        optionBO.setOptionName("name");
        optionBO.setId(1L);
        optionBO.setProductSpecId(2L);
        optionBO.setOrdinal(3);
        optionBO.setGmtCreate(new Date());
        optionBO.setSpecName("food");
        optionBOS.add(optionBO);
        List<OperatorProductSpecOptionDTO> optionDTOS = ProductSpecOptionConverter.convertOperatorDTOS(optionBOS);
        Assert.assertEquals(optionBO.getId(), optionDTOS.get(0).getId());
        Assert.assertEquals(optionBO.getIconUrl(), optionDTOS.get(0).getIconUrl());
        Assert.assertEquals(optionBO.getProductSpecId(), optionDTOS.get(0).getProductSpecId());
        Assert.assertEquals(optionBO.getOptionName(), optionDTOS.get(0).getOptionName());
        Assert.assertEquals(optionBO.getOrdinal().intValue(), optionDTOS.get(0).getOrdinal().intValue());
        Assert.assertEquals(optionBO.getGmtCreate(), optionDTOS.get(0).getGmtCreate());
        Assert.assertEquals(optionBO.getSpecName(), optionDTOS.get(0).getSpecName());
    }
}
