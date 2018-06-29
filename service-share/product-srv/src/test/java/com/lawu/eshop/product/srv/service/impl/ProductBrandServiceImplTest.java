package com.lawu.eshop.product.srv.service.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.product.param.OperatorProductBrandParam;
import com.lawu.eshop.product.param.ProductBrandParam;
import com.lawu.eshop.product.srv.ProductSrvApplicationTest;
import com.lawu.eshop.product.srv.bo.OperatorProductBrandBO;
import com.lawu.eshop.product.srv.bo.ProductBrandBO;
import com.lawu.eshop.product.srv.domain.ProductBrandDO;
import com.lawu.eshop.product.srv.domain.ProductCategoryeDO;
import com.lawu.eshop.product.srv.mapper.ProductBrandDOMapper;
import com.lawu.eshop.product.srv.mapper.ProductCategoryeDOMapper;
import com.lawu.eshop.product.srv.service.ProductBrandService;
import com.lawu.framework.core.page.Page;

/**
 * @author zhangyong
 * @date 2018/4/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductSrvApplicationTest.class)
public class ProductBrandServiceImplTest {

    @Autowired
    private ProductBrandService productBrandService;

    @Autowired
    private ProductBrandDOMapper productBrandDOMapper;

    @Autowired
    private ProductCategoryeDOMapper productCategoryeDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getProductBrandByCategoryId() {
        ProductBrandDO brandDO = new ProductBrandDO();
        brandDO.setBrandName("NAME");
        brandDO.setProductCategoryId(2);
        brandDO.setOrdinal((byte) 1);
        productBrandDOMapper.insertSelective(brandDO);

        List<ProductBrandBO> brandBOS = productBrandService.getProductBrandByCategoryId(brandDO.getProductCategoryId());
        Assert.assertEquals(brandBOS.get(0).getOrdinal().intValue(), brandDO.getOrdinal().intValue());
        Assert.assertEquals(brandBOS.get(0).getBrandName(), brandDO.getBrandName());
        Assert.assertEquals(brandBOS.get(0).getId(), brandDO.getId());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getProductBrandById() {
        ProductBrandDO brandDO = new ProductBrandDO();
        brandDO.setBrandName("NAME");
        brandDO.setProductCategoryId(2);
        brandDO.setOrdinal((byte) 1);
        productBrandDOMapper.insertSelective(brandDO);

        ProductBrandBO brandBO = productBrandService.getProductBrandById(brandDO.getId());
        Assert.assertEquals(brandBO.getOrdinal().intValue(), brandDO.getOrdinal().intValue());
        Assert.assertEquals(brandBO.getBrandName(), brandDO.getBrandName());
        Assert.assertEquals(brandBO.getId(), brandDO.getId());
    }

    @Test
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    public void addProductBrand() {
        ProductBrandParam param = new ProductBrandParam();
        param.setProductCategoryId(1);
        param.setBrandName("name");
        param.setOrdinal(1);
        productBrandService.addProductBrand(param);
        List<ProductBrandDO> brandDOS = productBrandDOMapper.selectByExample(null);
        Assert.assertEquals(brandDOS.get(0).getOrdinal().intValue(), param.getOrdinal().intValue());
        Assert.assertEquals(brandDOS.get(0).getBrandName(), param.getBrandName());
        Assert.assertEquals(brandDOS.get(0).getProductCategoryId(), param.getProductCategoryId());
    }

    @Test
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    public void getProductBrandDetail() {
        ProductBrandDO brandDO = new ProductBrandDO();
        brandDO.setBrandName("NAME");
        brandDO.setProductCategoryId(2);
        brandDO.setOrdinal((byte) 1);
        productBrandDOMapper.insertSelective(brandDO);
        ProductBrandBO brandBO = productBrandService.getProductBrandDetail(brandDO.getId());
        Assert.assertEquals(brandBO.getOrdinal().intValue(), brandDO.getOrdinal().intValue());
        Assert.assertEquals(brandBO.getBrandName(), brandDO.getBrandName());
        Assert.assertEquals(brandBO.getId(), brandDO.getId());
    }

    @Test
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    public void editProductBrand() {
        ProductBrandDO brandDO = new ProductBrandDO();
        brandDO.setBrandName("NAME");
        brandDO.setProductCategoryId(2);
        brandDO.setOrdinal((byte) 1);
        productBrandDOMapper.insertSelective(brandDO);
        ProductBrandParam param = new ProductBrandParam();
        param.setBrandName("name");
        param.setOrdinal(3);
        productBrandService.editProductBrand(brandDO.getId(), param);
        List<ProductBrandDO> brandDOS = productBrandDOMapper.selectByExample(null);
        Assert.assertEquals(brandDOS.get(0).getOrdinal().intValue(), param.getOrdinal().intValue());
        Assert.assertEquals(brandDOS.get(0).getBrandName(), param.getBrandName());
    }

    @Test
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    public void getOperatorProductBrandList() {

        ProductCategoryeDO categoryeDO = new ProductCategoryeDO();
        categoryeDO.setImageUrl("image");
        categoryeDO.setOrdinal((byte) 1);
        categoryeDO.setIsVirtual(false);
        categoryeDO.setType((byte) 1);
        categoryeDO.setName("test");
        productCategoryeDOMapper.insertSelective(categoryeDO);

        ProductBrandDO brandDO = new ProductBrandDO();
        brandDO.setBrandName("NAME");
        brandDO.setProductCategoryId(categoryeDO.getId());
        brandDO.setOrdinal((byte) 1);
        brandDO.setStatus(true);
        productBrandDOMapper.insertSelective(brandDO);

        OperatorProductBrandParam param = new OperatorProductBrandParam();
        param.setCurrentPage(1);
        param.setPageSize(10);
        Page<OperatorProductBrandBO> brandBOPage = productBrandService.getOperatorProductBrandList(param);

        Assert.assertEquals(brandDO.getId(), brandBOPage.getRecords().get(0).getId());
        Assert.assertEquals(brandDO.getOrdinal().intValue(), brandBOPage.getRecords().get(0).getOrdinal().intValue());
        Assert.assertEquals(brandDO.getBrandName(), brandBOPage.getRecords().get(0).getBrandName());
        Assert.assertEquals(categoryeDO.getName(), brandBOPage.getRecords().get(0).getCategoryName());

    }

    @Test
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    public void delProductBrand() {
        ProductBrandDO brandDO = new ProductBrandDO();
        brandDO.setBrandName("NAME");
        brandDO.setProductCategoryId(1);
        brandDO.setOrdinal((byte) 1);
        brandDO.setStatus(true);
        productBrandDOMapper.insertSelective(brandDO);
        productBrandService.delProductBrand(brandDO.getId());

        List<ProductBrandDO> brandDOS = productBrandDOMapper.selectByExample(null);
        Assert.assertEquals(false, brandDOS.get(0).getStatus());


    }


}
