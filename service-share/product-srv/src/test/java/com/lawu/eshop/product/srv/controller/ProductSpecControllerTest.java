package com.lawu.eshop.product.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import com.lawu.eshop.product.param.OperatorProductSpecParam;
import com.lawu.eshop.product.param.ProductSpecParam;
import com.lawu.eshop.product.srv.ProductSrvApplicationTest;
import com.lawu.eshop.product.srv.domain.ProductCategoryeDO;
import com.lawu.eshop.product.srv.domain.ProductSpecDO;
import com.lawu.eshop.product.srv.mapper.ProductCategoryeDOMapper;
import com.lawu.eshop.product.srv.mapper.ProductSpecDOMapper;
import com.lawu.framework.web.HttpCode;

/**
 * @author zhangyong
 * @date 2018/4/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductSrvApplicationTest.class)
@WebAppConfiguration
public class ProductSpecControllerTest {

    private MockMvc mvc;

    private Logger log = Logger.getLogger(ProductSpecControllerTest.class);

    @Autowired
    private ProductSpecController productSpecController;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(productSpecController).build();
    }

    @Autowired
    private ProductSpecDOMapper productSpecDOMapper;

    @Autowired
    private ProductCategoryeDOMapper productCategoryeDOMapper;


    @Transactional
    @Test
    @Rollback
    public void getProductSpecByCategoryId() {
        ProductSpecDO specDO = new ProductSpecDO();
        specDO.setGmtModified(new Date());
        specDO.setStatus(true);
        specDO.setSpecName("大小");
        specDO.setProductCategoryId(2);
        specDO.setGmtCreate(new Date());
        specDO.setOrdinal((byte) 1);
        productSpecDOMapper.insertSelective(specDO);
        RequestBuilder request = get("/productSpec/getProductSpecByCategoryId").param("productCategoryId", specDO.getProductCategoryId().toString());
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
    public void addProductSpec() {
        ProductSpecParam param = new ProductSpecParam();
        param.setProductCategoryId(2);
        param.setSpecName("大小");
        param.setOrdinal(3);
        RequestBuilder request = post("/productSpec/addProductSpec").contentType(MediaType.APPLICATION_JSON)
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
    public void editProductSpec() {
        ProductSpecParam param = new ProductSpecParam();
        param.setProductCategoryId(2);
        param.setSpecName("大小");
        param.setOrdinal(3);
        ProductSpecDO specDO = new ProductSpecDO();
        specDO.setGmtModified(new Date());
        specDO.setStatus(true);
        specDO.setSpecName("尺寸");
        specDO.setProductCategoryId(2);
        specDO.setGmtCreate(new Date());
        specDO.setOrdinal((byte) 1);
        productSpecDOMapper.insertSelective(specDO);
        RequestBuilder request = post("/productSpec/editProductSpec/" + specDO.getId()).contentType(MediaType.APPLICATION_JSON)
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
    @Rollback
    @Test
    public void getOperatorProductSpecList() {
        OperatorProductSpecParam param = new OperatorProductSpecParam();
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
        ProductSpecDO specDO = new ProductSpecDO();
        specDO.setGmtModified(new Date());
        specDO.setStatus(true);
        specDO.setSpecName("尺寸");
        specDO.setProductCategoryId(categoryDO.getId());
        specDO.setGmtCreate(new Date());
        specDO.setOrdinal((byte) 1);
        productSpecDOMapper.insertSelective(specDO);

        RequestBuilder request = post("/productSpec/getOperatorProductSpecList").contentType(MediaType.APPLICATION_JSON)
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

    @Transactional
    @Rollback
    @Test
    public void delProductSpec() {
        ProductSpecDO specDO = new ProductSpecDO();
        specDO.setGmtModified(new Date());
        specDO.setStatus(true);
        specDO.setSpecName("尺寸");
        specDO.setProductCategoryId(2);
        specDO.setGmtCreate(new Date());
        specDO.setOrdinal((byte) 1);
        productSpecDOMapper.insertSelective(specDO);
        RequestBuilder request = put("/productSpec/delProductSpec").param("ids", specDO.getId().toString());
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
    @Rollback
    @Test
    public void getSpecDetailById() {
        ProductSpecDO specDO = new ProductSpecDO();
        specDO.setGmtModified(new Date());
        specDO.setStatus(true);
        specDO.setSpecName("尺寸");
        specDO.setProductCategoryId(2);
        specDO.setGmtCreate(new Date());
        specDO.setOrdinal((byte) 1);
        productSpecDOMapper.insertSelective(specDO);
        RequestBuilder request = get("/productSpec/getSpecDetailById/" + specDO.getId());
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
}
