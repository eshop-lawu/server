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

import com.lawu.eshop.product.param.OperatorSpecOptionListParam;
import com.lawu.eshop.product.param.ProductSpecOptionParam;
import com.lawu.eshop.product.srv.ProductSrvApplicationTest;
import com.lawu.eshop.product.srv.bo.OperatorProductSpecOptionBO;
import com.lawu.eshop.product.srv.bo.ProductSpecOptionBO;
import com.lawu.eshop.product.srv.domain.ProductSpecDO;
import com.lawu.eshop.product.srv.domain.ProductSpecOptionDO;
import com.lawu.eshop.product.srv.mapper.ProductSpecDOMapper;
import com.lawu.eshop.product.srv.mapper.ProductSpecOptionDOMapper;
import com.lawu.eshop.product.srv.service.ProductSpecOptionService;
import com.lawu.framework.core.page.Page;

/**
 * @author zhangyong
 * @date 2018/4/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductSrvApplicationTest.class)
public class ProductSpecOptionServiceImplTest {

    @Autowired
    private ProductSpecOptionService productSpecOptionService;
    @Autowired
    private ProductSpecOptionDOMapper productSpecOptionDOMapper;

    @Autowired
    private ProductSpecDOMapper productSpecDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getSpecOptionBySpecId() {
        ProductSpecOptionDO optionDO = new ProductSpecOptionDO();
        optionDO.setStatus(true);
        optionDO.setGmtCreate(new Date());
        optionDO.setOrdinal((byte) 1);
        optionDO.setIconUrl("url");
        optionDO.setOptionName("尺寸");
        optionDO.setProductSpecId(1L);
        productSpecOptionDOMapper.insertSelective(optionDO);
        List<ProductSpecOptionBO> optionBOS = productSpecOptionService.getSpecOptionBySpecId(optionDO.getProductSpecId());
        Assert.assertEquals(optionBOS.get(0).getId(), optionDO.getId());
        Assert.assertEquals(optionBOS.get(0).getOptionName(), optionDO.getOptionName());
        Assert.assertEquals(optionBOS.get(0).getIconUrl(), optionDO.getIconUrl());
        Assert.assertEquals(optionBOS.get(0).getProductSpecId(), optionDO.getProductSpecId());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void addProductSpecOption() {
        ProductSpecOptionParam param = new ProductSpecOptionParam();
        param.setOrdinal(1);
        param.setIconUrl("url");
        param.setOptionName("尺寸");
        param.setProductSpecId(1L);
        productSpecOptionService.addProductSpecOption(param);
        List<ProductSpecOptionDO> optionDOS = productSpecOptionDOMapper.selectByExample(null);
        Assert.assertEquals(optionDOS.get(0).getOptionName(), param.getOptionName());
        Assert.assertEquals(optionDOS.get(0).getIconUrl(), param.getIconUrl());
        Assert.assertEquals(optionDOS.get(0).getProductSpecId(), param.getProductSpecId());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void editProductSpecOption() {
        ProductSpecOptionParam param = new ProductSpecOptionParam();
        param.setOrdinal(2);
        param.setIconUrl("url2");
        param.setOptionName("尺寸2");
        param.setProductSpecId(2L);
        ProductSpecOptionDO optionDO = new ProductSpecOptionDO();
        optionDO.setStatus(true);
        optionDO.setGmtCreate(new Date());
        optionDO.setOrdinal((byte) 1);
        optionDO.setIconUrl("url");
        optionDO.setOptionName("尺寸");
        optionDO.setProductSpecId(1L);
        productSpecOptionDOMapper.insertSelective(optionDO);
        productSpecOptionService.editProductSpecOption(optionDO.getId(), param);
        List<ProductSpecOptionDO> optionDOS = productSpecOptionDOMapper.selectByExample(null);
        Assert.assertEquals(optionDOS.get(0).getOptionName(), param.getOptionName());
        Assert.assertEquals(optionDOS.get(0).getIconUrl(), param.getIconUrl());
        Assert.assertEquals(optionDOS.get(0).getOrdinal().intValue(), param.getOrdinal().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void delProductSpecOption() {
        ProductSpecOptionDO optionDO = new ProductSpecOptionDO();
        optionDO.setStatus(true);
        optionDO.setGmtCreate(new Date());
        optionDO.setOrdinal((byte) 1);
        optionDO.setIconUrl("url");
        optionDO.setOptionName("尺寸");
        optionDO.setProductSpecId(1L);
        productSpecOptionDOMapper.insertSelective(optionDO);
        productSpecOptionService.delProductSpecOption(optionDO.getId());
        List<ProductSpecOptionDO> optionDOS = productSpecOptionDOMapper.selectByExample(null);
        Assert.assertEquals(optionDOS.get(0).getStatus(), false);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getSpecOptionDetail() {
        ProductSpecOptionDO optionDO = new ProductSpecOptionDO();
        optionDO.setStatus(true);
        optionDO.setGmtCreate(new Date());
        optionDO.setOrdinal((byte) 1);
        optionDO.setIconUrl("url");
        optionDO.setOptionName("尺寸");
        optionDO.setProductSpecId(1L);
        productSpecOptionDOMapper.insertSelective(optionDO);
        ProductSpecOptionBO optionBO = productSpecOptionService.getSpecOptionDetail(optionDO.getId());
        Assert.assertEquals(optionBO.getOptionName(), optionDO.getOptionName());
        Assert.assertEquals(optionBO.getIconUrl(), optionDO.getIconUrl());
        Assert.assertEquals(optionBO.getOrdinal().intValue(), optionDO.getOrdinal().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Test
    @Rollback
    public void getOperatorSpecOptionList() {
        ProductSpecDO specDO = new ProductSpecDO();
        specDO.setStatus(true);
        specDO.setProductCategoryId(1);
        specDO.setSpecName("大小");
        specDO.setGmtCreate(new Date());
        productSpecDOMapper.insertSelective(specDO);
        ProductSpecOptionDO optionDO = new ProductSpecOptionDO();
        optionDO.setStatus(true);
        optionDO.setGmtCreate(new Date());
        optionDO.setOrdinal((byte) 1);
        optionDO.setIconUrl("url");
        optionDO.setOptionName("尺寸");
        optionDO.setProductSpecId(specDO.getId());
        productSpecOptionDOMapper.insertSelective(optionDO);
        OperatorSpecOptionListParam param = new OperatorSpecOptionListParam();
        param.setCurrentPage(1);
        param.setPageSize(10);

        Page<OperatorProductSpecOptionBO> optionBOPage = productSpecOptionService.getOperatorSpecOptionList(param);
        Assert.assertEquals(optionBOPage.getRecords().get(0).getId(), optionDO.getId());
        Assert.assertEquals(optionBOPage.getRecords().get(0).getIconUrl(), optionDO.getIconUrl());
        Assert.assertEquals(optionBOPage.getRecords().get(0).getOptionName(), optionDO.getOptionName());
        Assert.assertEquals(optionBOPage.getRecords().get(0).getProductSpecId(), optionDO.getProductSpecId());
        Assert.assertEquals(optionBOPage.getRecords().get(0).getGmtCreate(), optionDO.getGmtCreate());
        Assert.assertEquals(optionBOPage.getRecords().get(0).getOrdinal().intValue(), optionDO.getOrdinal().intValue());
        Assert.assertEquals(optionBOPage.getRecords().get(0).getSpecName(), specDO.getSpecName());
    }
}
