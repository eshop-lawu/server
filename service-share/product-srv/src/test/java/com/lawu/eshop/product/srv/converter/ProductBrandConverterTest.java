package com.lawu.eshop.product.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.product.dto.OperatorProductBrandDTO;
import com.lawu.eshop.product.dto.ProductBrandDetailDTO;
import com.lawu.eshop.product.dto.ProductBrandListDTO;
import com.lawu.eshop.product.srv.bo.OperatorProductBrandBO;
import com.lawu.eshop.product.srv.bo.ProductBrandBO;
import com.lawu.eshop.product.srv.domain.ProductBrandDO;
import com.lawu.eshop.product.srv.domain.extend.OperatorProductBrandDOView;

/**
 * @author zhangyong
 * @date 2018/4/19.
 */
public class ProductBrandConverterTest {

    @Test
    public void covertBOS() {
        List<ProductBrandDO> brandDOS = new ArrayList<>();
        List<ProductBrandBO> list = ProductBrandConverter.covertBOS(brandDOS);
        Assert.assertEquals(0, list.size());
        ProductBrandDO brandDO = new ProductBrandDO();
        brandDO.setId(1L);
        brandDO.setBrandName("name");
        brandDO.setProductCategoryId(2);
        brandDO.setOrdinal((byte) 3);
        brandDOS.add(brandDO);
        List<ProductBrandBO> brandBOS = ProductBrandConverter.covertBOS(brandDOS);
        Assert.assertEquals(brandDO.getId(), brandBOS.get(0).getId());
        Assert.assertEquals(brandDO.getOrdinal().intValue(), brandBOS.get(0).getOrdinal().intValue());
        Assert.assertEquals(brandDO.getProductCategoryId(), brandBOS.get(0).getProductCategoryId());
        Assert.assertEquals(brandDO.getBrandName(), brandBOS.get(0).getBrandName());

    }

    @Test
    public void covertDTOS() {
        List<ProductBrandBO> brandBOS = new ArrayList<>();
        List<ProductBrandListDTO> list = ProductBrandConverter.covertDTOS(brandBOS);
        Assert.assertEquals(0, list.size());
        ProductBrandBO brandBO = new ProductBrandBO();
        brandBO.setId(1L);
        brandBO.setBrandName("name");
        brandBO.setProductCategoryId(2);
        brandBOS.add(brandBO);

        List<ProductBrandListDTO> listDTOS = ProductBrandConverter.covertDTOS(brandBOS);
        Assert.assertEquals(brandBO.getId(), listDTOS.get(0).getId());
        Assert.assertEquals(brandBO.getProductCategoryId(), listDTOS.get(0).getProductCategoryId());
        Assert.assertEquals(brandBO.getBrandName(), listDTOS.get(0).getBrandName());
    }

    @Test
    public void covertBO() {
        ProductBrandDO brandDO = new ProductBrandDO();
        brandDO.setId(1L);
        brandDO.setBrandName("name");
        brandDO.setProductCategoryId(2);
        brandDO.setOrdinal((byte) 3);
        ProductBrandBO brandBO = ProductBrandConverter.covertBO(brandDO);
        Assert.assertEquals(brandDO.getId(), brandBO.getId());
        Assert.assertEquals(brandDO.getOrdinal().intValue(), brandBO.getOrdinal().intValue());
        Assert.assertEquals(brandDO.getProductCategoryId(), brandBO.getProductCategoryId());
        Assert.assertEquals(brandDO.getBrandName(), brandBO.getBrandName());
    }

    @Test
    public void covertDTO() {
        ProductBrandBO brandBO = new ProductBrandBO();
        brandBO.setId(1L);
        brandBO.setBrandName("name");
        brandBO.setProductCategoryId(2);
        brandBO.setOrdinal(3);
        ProductBrandDetailDTO detailDTO = ProductBrandConverter.covertDTO(brandBO);
        Assert.assertEquals(brandBO.getId(), detailDTO.getId());
        Assert.assertEquals(brandBO.getOrdinal().intValue(), detailDTO.getOrdinal().intValue());
        Assert.assertEquals(brandBO.getProductCategoryId(), detailDTO.getProductCategoryId());
        Assert.assertEquals(brandBO.getBrandName(), detailDTO.getBrandName());
    }

    @Test
    public void covertOperatorBOS() {
        List<OperatorProductBrandDOView> brandDOViews = new ArrayList<>();
        List<OperatorProductBrandBO> list = ProductBrandConverter.covertOperatorBOS(brandDOViews);
        Assert.assertEquals(0, list.size());
        OperatorProductBrandDOView brandDO = new OperatorProductBrandDOView();
        brandDO.setId(1L);
        brandDO.setBrandName("name");
        brandDO.setCategoryName("cName");
        brandDO.setOrdinal((byte) 3);
        brandDO.setGmtCreate(new Date());
        brandDOViews.add(brandDO);
        List<OperatorProductBrandBO> brandBOS = ProductBrandConverter.covertOperatorBOS(brandDOViews);
        Assert.assertEquals(brandDO.getId(), brandBOS.get(0).getId());
        Assert.assertEquals(brandDO.getBrandName(), brandBOS.get(0).getBrandName());
        Assert.assertEquals(brandDO.getOrdinal().intValue(), brandBOS.get(0).getOrdinal().intValue());
        Assert.assertEquals(brandDO.getGmtCreate(), brandBOS.get(0).getGmtCreate());
        Assert.assertEquals(brandDO.getCategoryName(), brandBOS.get(0).getCategoryName());

    }

    @Test
    public void covertOperatorDTOS(){
        List<OperatorProductBrandBO> brandBOS = new ArrayList<>();
        List<OperatorProductBrandDTO> list = ProductBrandConverter.covertOperatorDTOS(brandBOS);
        Assert.assertEquals(0, list.size());
        OperatorProductBrandBO brandBO = new OperatorProductBrandBO();
        brandBO.setId(1L);
        brandBO.setBrandName("name");
        brandBO.setCategoryName("cName");
        brandBO.setOrdinal(3);
        brandBO.setGmtCreate(new Date());
        brandBOS.add(brandBO);
        List<OperatorProductBrandDTO> brandDTOS = ProductBrandConverter.covertOperatorDTOS(brandBOS);
        Assert.assertEquals(brandBO.getId(), brandDTOS.get(0).getId());
        Assert.assertEquals(brandBO.getBrandName(), brandDTOS.get(0).getBrandName());
        Assert.assertEquals(brandBO.getOrdinal().intValue(), brandDTOS.get(0).getOrdinal().intValue());
        Assert.assertEquals(brandBO.getGmtCreate(), brandDTOS.get(0).getGmtCreate());
        Assert.assertEquals(brandBO.getCategoryName(), brandDTOS.get(0).getCategoryName());
    }
}
