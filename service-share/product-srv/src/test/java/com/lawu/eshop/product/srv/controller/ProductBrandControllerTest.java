package com.lawu.eshop.product.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.lawu.eshop.product.param.OperatorProductBrandParam;
import com.lawu.eshop.product.param.ProductBrandParam;
import com.lawu.eshop.product.srv.ProductSrvApplicationTest;
import com.lawu.eshop.product.srv.domain.ProductBrandDO;
import com.lawu.eshop.product.srv.domain.ProductCategoryeDO;
import com.lawu.eshop.product.srv.mapper.ProductBrandDOMapper;
import com.lawu.eshop.product.srv.mapper.ProductCategoryeDOMapper;
import com.lawu.framework.web.HttpCode;

/**
 * @author zhangyong
 * @date 2018/4/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductSrvApplicationTest.class)
@WebAppConfiguration
public class ProductBrandControllerTest {
    private MockMvc mvc;

    private Logger log = Logger.getLogger(ProductBrandControllerTest.class);

    @Autowired
    private ProductBrandController productBrandController;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(productBrandController).build();
    }

    @Autowired
    private ProductBrandDOMapper productBrandDOMapper;

    @Autowired
    private ProductCategoryeDOMapper productCategoryeDOMapper;

    @Transactional
    @Test
    @Rollback
    public void getProductBrandByCategoryId() {
        ProductBrandDO brandDO = new ProductBrandDO();
        brandDO.setOrdinal((byte) 1);
        brandDO.setProductCategoryId(1);
        brandDO.setStatus(true);
        brandDO.setBrandName("两只松鼠");
        brandDO.setGmtCreate(new Date());
        productBrandDOMapper.insertSelective(brandDO);
        RequestBuilder request = get("/productBrand/getProductBrandByCategoryId").param("productCategoryId", brandDO.getProductCategoryId().toString());
        try {
            ResultActions perform = mvc.perform(request);
            log.info(perform.andReturn().getResponse().getContentAsString());
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print())
                    .andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Transactional
    @Test
    @Rollback
    public void addProductBrand() {
        ProductBrandParam param = new ProductBrandParam();
        param.setBrandName("两只松鼠");
        param.setProductCategoryId(3);
        param.setOrdinal(2);
        RequestBuilder request = post("/productBrand/addProductBrand").contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(param));
        try {
            ResultActions perform = mvc.perform(request);
            log.info(perform.andReturn().getResponse().getContentAsString());
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print())
                    .andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Rollback
    @Test
    @Transactional
    public void getProductBrandDetail() {
        ProductBrandDO brandDO = new ProductBrandDO();
        brandDO.setOrdinal((byte) 1);
        brandDO.setProductCategoryId(1);
        brandDO.setStatus(true);
        brandDO.setBrandName("两只松鼠");
        brandDO.setGmtCreate(new Date());
        productBrandDOMapper.insertSelective(brandDO);
        RequestBuilder request = get("/productBrand/getProductBrandDetail/" + brandDO.getId());
        try {
            ResultActions perform = mvc.perform(request);
            log.info(perform.andReturn().getResponse().getContentAsString());
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print())
                    .andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }


    @Transactional
    @Test
    @Rollback
    public void editProductBrand() {
        ProductBrandDO brandDO = new ProductBrandDO();
        brandDO.setOrdinal((byte) 1);
        brandDO.setProductCategoryId(1);
        brandDO.setStatus(true);
        brandDO.setBrandName("两只松鼠");
        brandDO.setGmtCreate(new Date());
        productBrandDOMapper.insertSelective(brandDO);
        ProductBrandParam param = new ProductBrandParam();
        param.setBrandName("两只松鼠2");
        param.setProductCategoryId(1);
        param.setOrdinal(2);
        RequestBuilder request = post("/productBrand/editProductBrand/" + brandDO.getId()).contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(param));
        try {
            ResultActions perform = mvc.perform(request);
            log.info(perform.andReturn().getResponse().getContentAsString());
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print())
                    .andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Transactional
    @Test
    @Rollback
    public void getOperatorProductBrandList() {
        OperatorProductBrandParam param = new OperatorProductBrandParam();
        param.setCurrentPage(1);
        param.setPageSize(10);

        ProductCategoryeDO categoryDO = new ProductCategoryeDO();
        categoryDO.setImageUrl("image");
        categoryDO.setName("食品");
        categoryDO.setType((byte) 1);
        categoryDO.setIsVirtual(false);
        categoryDO.setStatue(Boolean.TRUE);
        categoryDO.setParentId(0);
        categoryDO.setLevel((byte) 1);
        productCategoryeDOMapper.insertSelective(categoryDO);
        ProductBrandDO brandDO = new ProductBrandDO();
        brandDO.setOrdinal((byte) 1);
        brandDO.setProductCategoryId(categoryDO.getId());
        brandDO.setStatus(true);
        brandDO.setBrandName("两只松鼠");
        brandDO.setGmtCreate(new Date());
        productBrandDOMapper.insertSelective(brandDO);

        RequestBuilder request = post("/productBrand/getOperatorProductBrandList").contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(param));
        try {
            ResultActions perform = mvc.perform(request);
            log.info(perform.andReturn().getResponse().getContentAsString());
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print())
                    .andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Rollback
    @Test
    @Transactional
    public void delProductBrand() {
        ProductBrandDO brandDO = new ProductBrandDO();
        brandDO.setOrdinal((byte) 1);
        brandDO.setProductCategoryId(1);
        brandDO.setStatus(true);
        brandDO.setBrandName("两只松鼠");
        brandDO.setGmtCreate(new Date());
        productBrandDOMapper.insertSelective(brandDO);

        RequestBuilder request = put("/productBrand/delProductBrand").param("ids", brandDO.getId().toString());
        try {
            ResultActions perform = mvc.perform(request);
            log.info(perform.andReturn().getResponse().getContentAsString());
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print())
                    .andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }
}
