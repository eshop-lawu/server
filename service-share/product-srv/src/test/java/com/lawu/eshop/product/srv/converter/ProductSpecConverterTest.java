package com.lawu.eshop.product.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.product.dto.OperatorProductSpecDTO;
import com.lawu.eshop.product.dto.ProductSpecDetailDTO;
import com.lawu.eshop.product.dto.ProductSpecListDTO;
import com.lawu.eshop.product.param.ProductSpecCustomParam;
import com.lawu.eshop.product.srv.bo.OperatorProductSpecBO;
import com.lawu.eshop.product.srv.bo.ProductSpecBO;
import com.lawu.eshop.product.srv.domain.ProductSpecDO;
import com.lawu.eshop.product.srv.domain.extend.OperatorProductSpecDOView;

/**
 * @author zhangyong
 * @date 2018/4/19.
 */
public class ProductSpecConverterTest {

    @Test
    public void convertBOS() {
        List<ProductSpecDO> specDOS = new ArrayList<>();
        ProductSpecDO specDO = new ProductSpecDO();
        specDO.setId(1L);
        specDO.setSpecName("NAME");
        specDO.setProductCategoryId(2);
        specDOS.add(specDO);
        List<ProductSpecBO> specBOS = ProductSpecConverter.convertBOS(specDOS);
        Assert.assertEquals(specDO.getId(), specBOS.get(0).getId());
        Assert.assertEquals(specDO.getSpecName(), specBOS.get(0).getSpecName());
        Assert.assertEquals(specDO.getProductCategoryId(), specBOS.get(0).getProductCategoryId());
    }

    @Test
    public void convertDTOS() {
        List<ProductSpecBO> specBOS = new ArrayList<>();
        ProductSpecBO specBO = new ProductSpecBO();
        specBO.setId(1L);
        specBO.setSpecName("NAME");
        specBO.setProductCategoryId(2);
        specBOS.add(specBO);
        List<ProductSpecListDTO> listDTOS = ProductSpecConverter.convertDTOS(specBOS);
        Assert.assertEquals(specBO.getId(), listDTOS.get(0).getId());
        Assert.assertEquals(specBO.getSpecName(), listDTOS.get(0).getSpecName());
        Assert.assertEquals(specBO.getProductCategoryId(), listDTOS.get(0).getProductCategoryId());
    }

    @Test
    public void convertOperatorBOS() {
        List<OperatorProductSpecDOView> specDOViews = new ArrayList<>();
        OperatorProductSpecDOView doView = new OperatorProductSpecDOView();
        doView.setId(1L);
        doView.setOrdinal((byte) 1);
        doView.setSpecName("name");
        doView.setGmtCreate(new Date());
        doView.setCategoryName("food");
        specDOViews.add(doView);
        List<OperatorProductSpecBO> specBOS = ProductSpecConverter.convertOperatorBOS(specDOViews);
        Assert.assertEquals(doView.getId(), specBOS.get(0).getId());
        Assert.assertEquals(doView.getSpecName(), specBOS.get(0).getSpecName());
        Assert.assertEquals(doView.getCategoryName(), specBOS.get(0).getCategoryName());
        Assert.assertEquals(doView.getOrdinal().intValue(), specBOS.get(0).getOrdinal().intValue());
        Assert.assertEquals(doView.getGmtCreate(), specBOS.get(0).getGmtCreate());
    }

    @Test
    public void convertOperatorDTOS() {
        List<OperatorProductSpecBO> records = new ArrayList<>();
        OperatorProductSpecBO specBO = new OperatorProductSpecBO();
        specBO.setId(1L);
        specBO.setOrdinal(1);
        specBO.setSpecName("name");
        specBO.setGmtCreate(new Date());
        specBO.setCategoryName("food");
        records.add(specBO);
        List<OperatorProductSpecDTO> specDTOS = ProductSpecConverter.convertOperatorDTOS(records);
        Assert.assertEquals(specBO.getId(), specDTOS.get(0).getId());
        Assert.assertEquals(specBO.getSpecName(), specDTOS.get(0).getSpecName());
        Assert.assertEquals(specBO.getCategoryName(), specDTOS.get(0).getCategoryName());
        Assert.assertEquals(specBO.getOrdinal().intValue(), specDTOS.get(0).getOrdinal().intValue());
        Assert.assertEquals(specBO.getGmtCreate(), specDTOS.get(0).getGmtCreate());
    }

    @Test
    public void convertBO() {
        ProductSpecDO specDO = new ProductSpecDO();
        specDO.setId(1L);
        specDO.setSpecName("NAME");
        specDO.setProductCategoryId(2);
        specDO.setOrdinal((byte) 3);
        ProductSpecBO specBO = ProductSpecConverter.convertBO(specDO);
        Assert.assertEquals(specBO.getId(), specDO.getId());
        Assert.assertEquals(specBO.getSpecName(), specDO.getSpecName());
        Assert.assertEquals(specBO.getProductCategoryId(), specDO.getProductCategoryId());
        Assert.assertEquals(specBO.getOrdinal().intValue(), specDO.getOrdinal().intValue());
    }

    @Test
    public void convertDTO() {
        ProductSpecBO specBO = new ProductSpecBO();
        specBO.setId(1L);
        specBO.setSpecName("NAME");
        specBO.setProductCategoryId(2);
        specBO.setOrdinal(3);
        ProductSpecDetailDTO detailDTO = ProductSpecConverter.convertDTO(specBO);
        Assert.assertEquals(specBO.getId(), detailDTO.getId());
        Assert.assertEquals(specBO.getSpecName(), detailDTO.getSpecName());
        Assert.assertEquals(specBO.getProductCategoryId(), detailDTO.getProductCategoryId());
        Assert.assertEquals(specBO.getOrdinal().intValue(), detailDTO.getOrdinal().intValue());
    }

    @Test
    public void convertProductSpecCustomParamList() {
        List<ProductSpecBO> productSpecBOList = new ArrayList<>();
        ProductSpecBO specBO = new ProductSpecBO();
        specBO.setId(1L);
        specBO.setSpecName("NAME");
        specBO.setProductCategoryId(2);
        specBO.setOrdinal(3);
        productSpecBOList.add(specBO);
        List<ProductSpecCustomParam> params = ProductSpecConverter.convertProductSpecCustomParamList(productSpecBOList);
        Assert.assertEquals(specBO.getId(), params.get(0).getSpecId());
        Assert.assertEquals(specBO.getSpecName(), params.get(0).getSpecName());
    }

}
