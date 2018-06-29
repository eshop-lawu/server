package com.lawu.eshop.product.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.product.param.OperatorProductSpecParam;
import com.lawu.eshop.product.param.ProductSpecParam;
import com.lawu.eshop.product.srv.ProductSrvApplicationTest;
import com.lawu.eshop.product.srv.bo.OperatorProductSpecBO;
import com.lawu.eshop.product.srv.bo.ProductSpecBO;
import com.lawu.eshop.product.srv.domain.ProductCategoryeDO;
import com.lawu.eshop.product.srv.domain.ProductSpecDO;
import com.lawu.eshop.product.srv.mapper.ProductCategoryeDOMapper;
import com.lawu.eshop.product.srv.mapper.ProductSpecDOMapper;
import com.lawu.eshop.product.srv.service.ProductSpecService;
import com.lawu.framework.core.page.Page;

/**
 * @author zhangyong
 * @date 2018/4/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductSrvApplicationTest.class)
public class ProductSpecServiceImplTest {

    @Autowired
    private ProductSpecService productSpecService;

    @Autowired
    private ProductSpecDOMapper productSpecDOMapper;

    @Autowired
    private ProductCategoryeDOMapper productCategoryeDOMapper;

    @Test
    @Rollback
    @Transactional
    public void getProductSpecByCategoryId() {
        ProductSpecDO specDO = new ProductSpecDO();
        specDO.setGmtCreate(new Date());
        specDO.setStatus(true);
        specDO.setSpecName("颜色");
        specDO.setProductCategoryId(1);
        specDO.setOrdinal((byte) 1);
        productSpecDOMapper.insertSelective(specDO);
        List<ProductSpecBO> specBOS = productSpecService.getProductSpecByCategoryId(specDO.getProductCategoryId());
        Assert.assertEquals(specDO.getId(), specBOS.get(0).getId());
        Assert.assertEquals(specDO.getProductCategoryId(), specBOS.get(0).getProductCategoryId());
        Assert.assertEquals(specDO.getSpecName(), specBOS.get(0).getSpecName());
    }

    @Test
    @Rollback
    @Transactional
    public void addProductSpec() {
        ProductSpecParam param = new ProductSpecParam();
        param.setSpecName("颜色");
        param.setProductCategoryId(1);
        param.setOrdinal(2);
        productSpecService.addProductSpec(param);
        List<ProductSpecDO> specDOS = productSpecDOMapper.selectByExample(null);
        Assert.assertEquals(param.getSpecName(), specDOS.get(0).getSpecName());
        Assert.assertEquals(param.getOrdinal().intValue(), specDOS.get(0).getOrdinal().intValue());
        Assert.assertEquals(param.getProductCategoryId(), specDOS.get(0).getProductCategoryId());
    }

    @Test
    @Rollback
    @Transactional
    public void editProductSpec() {
        ProductSpecDO specDO = new ProductSpecDO();
        specDO.setGmtCreate(new Date());
        specDO.setStatus(true);
        specDO.setSpecName("颜色");
        specDO.setProductCategoryId(1);
        specDO.setOrdinal((byte) 1);
        productSpecDOMapper.insertSelective(specDO);
        ProductSpecParam param = new ProductSpecParam();
        param.setSpecName("颜色2");
        param.setProductCategoryId(2);
        param.setOrdinal(3);
        productSpecService.editProductSpec(specDO.getId(), param);
        List<ProductSpecDO> specDOS = productSpecDOMapper.selectByExample(null);
        Assert.assertEquals(param.getSpecName(), specDOS.get(0).getSpecName());
        Assert.assertEquals(param.getOrdinal().intValue(), specDOS.get(0).getOrdinal().intValue());
    }

    @Test
    @Rollback
    @Transactional
    public void getOperatorProductSpecList() {
        OperatorProductSpecParam param = new OperatorProductSpecParam();
        param.setCurrentPage(1);
        param.setPageSize(10);
        ProductCategoryeDO categoryeDO = new ProductCategoryeDO();
        categoryeDO.setImageUrl("image");
        categoryeDO.setOrdinal((byte) 1);
        categoryeDO.setIsVirtual(false);
        categoryeDO.setType((byte) 1);
        categoryeDO.setName("test");
        productCategoryeDOMapper.insertSelective(categoryeDO);
        ProductSpecDO specDO = new ProductSpecDO();
        specDO.setGmtCreate(new Date());
        specDO.setStatus(true);
        specDO.setSpecName("颜色");
        specDO.setProductCategoryId(categoryeDO.getId());
        specDO.setOrdinal((byte) 1);
        productSpecDOMapper.insertSelective(specDO);

        Page<OperatorProductSpecBO> page = productSpecService.getOperatorProductSpecList(param);
        Assert.assertEquals(specDO.getId(), page.getRecords().get(0).getId());
        Assert.assertEquals(specDO.getSpecName(), page.getRecords().get(0).getSpecName());
        Assert.assertEquals(categoryeDO.getName(), page.getRecords().get(0).getCategoryName());
        Assert.assertEquals(specDO.getOrdinal().intValue(), page.getRecords().get(0).getOrdinal().intValue());
    }

    @Test
    @Rollback
    @Transactional
    public void delProductSpec() {
        ProductSpecDO specDO = new ProductSpecDO();
        specDO.setGmtCreate(new Date());
        specDO.setStatus(true);
        specDO.setSpecName("颜色");
        specDO.setProductCategoryId(2);
        specDO.setOrdinal((byte) 1);
        productSpecDOMapper.insertSelective(specDO);
        productSpecService.delProductSpec(specDO.getId());
        List<ProductSpecDO> specDOS = productSpecDOMapper.selectByExample(null);
        Assert.assertEquals(false, specDOS.get(0).getStatus());
    }

    @Test
    @Rollback
    @Transactional
    public void getSpecDetailById(){
        ProductSpecDO specDO = new ProductSpecDO();
        specDO.setGmtCreate(new Date());
        specDO.setStatus(true);
        specDO.setSpecName("颜色");
        specDO.setProductCategoryId(2);
        specDO.setOrdinal((byte) 1);
        productSpecDOMapper.insertSelective(specDO);
        ProductSpecBO specBO = productSpecService.getSpecDetailById(specDO.getId());
        Assert.assertEquals(specBO.getId(), specDO.getId());
        Assert.assertEquals(specBO.getSpecName(), specDO.getSpecName());
        Assert.assertEquals(specBO.getProductCategoryId(), specDO.getProductCategoryId());
        Assert.assertEquals(specBO.getOrdinal().intValue(), specDO.getOrdinal().intValue());
    }
}
