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
import com.lawu.eshop.product.param.OperatorSpecOptionListParam;
import com.lawu.eshop.product.param.ProductSpecOptionParam;
import com.lawu.eshop.product.srv.ProductSrvApplicationTest;
import com.lawu.eshop.product.srv.domain.ProductSpecDO;
import com.lawu.eshop.product.srv.domain.ProductSpecOptionDO;
import com.lawu.eshop.product.srv.mapper.ProductSpecDOMapper;
import com.lawu.eshop.product.srv.mapper.ProductSpecOptionDOMapper;
import com.lawu.framework.web.HttpCode;

/**
 * @author zhangyong
 * @date 2018/4/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductSrvApplicationTest.class)
@WebAppConfiguration
public class ProductSpecOptionControllerTest {

    private MockMvc mvc;

    private Logger log = Logger.getLogger(ProductSpecOptionControllerTest.class);

    @Autowired
    private ProductSpecOptionController productSpecOptionController;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(productSpecOptionController).build();
    }

    @Autowired
    private ProductSpecDOMapper productSpecDOMapper;

    @Autowired
    private ProductSpecOptionDOMapper productSpecOptionDOMapper;

    @Transactional
    @Test
    @Rollback
    public void getSpecOptionBySpecId() {
        ProductSpecOptionDO optionDO = new ProductSpecOptionDO();
        optionDO.setStatus(true);
        optionDO.setOptionName("XXL");
        optionDO.setProductSpecId(1L);
        optionDO.setGmtCreate(new Date());
        optionDO.setIconUrl("icon");
        productSpecOptionDOMapper.insertSelective(optionDO);
        RequestBuilder request = get("/productSpecOption/getSpecOptionBySpecId").param("productSpecId", optionDO.getProductSpecId().toString());
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
    public void addProductSpecOption() {
        ProductSpecOptionParam param = new ProductSpecOptionParam();
        param.setIconUrl("icon");
        param.setProductSpecId(1L);
        param.setOptionName("XXL");
        param.setOrdinal(2);
        RequestBuilder request = post("/productSpecOption/addProductSpecOption").contentType(MediaType.APPLICATION_JSON)
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
    public void editProductSpecOption() {
        ProductSpecDO specDO = new ProductSpecDO();
        specDO.setGmtModified(new Date());
        specDO.setStatus(true);
        specDO.setSpecName("尺寸");
        specDO.setProductCategoryId(2);
        specDO.setGmtCreate(new Date());
        specDO.setOrdinal((byte) 1);
        productSpecDOMapper.insertSelective(specDO);

        ProductSpecOptionParam param = new ProductSpecOptionParam();
        param.setIconUrl("icon");
        param.setProductSpecId(1L);
        param.setOptionName("XXL");
        param.setOrdinal(2);
        RequestBuilder request = post("/productSpecOption/editProductSpecOption/" + specDO.getId()).contentType(MediaType.APPLICATION_JSON)
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
    public void delProductSpecOption() {
        ProductSpecOptionDO specDO = new ProductSpecOptionDO();
        specDO.setGmtModified(new Date());
        specDO.setStatus(true);
        specDO.setOptionName("XXL");
        specDO.setProductSpecId(2L);
        specDO.setGmtCreate(new Date());
        specDO.setOrdinal((byte) 1);
        productSpecOptionDOMapper.insertSelective(specDO);
        RequestBuilder request = put("/productSpecOption/delProductSpecOption").param("ids", specDO.getId().toString());
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
    public void getSpecOptionDetail() {
        ProductSpecOptionDO optionDO = new ProductSpecOptionDO();
        optionDO.setGmtModified(new Date());
        optionDO.setStatus(true);
        optionDO.setOptionName("XXL");
        optionDO.setProductSpecId(2L);
        optionDO.setGmtCreate(new Date());
        optionDO.setOrdinal((byte) 1);
        productSpecOptionDOMapper.insertSelective(optionDO);
        RequestBuilder request = get("/productSpecOption/getSpecOptionDetail/" + optionDO.getId());
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
    public void getOperatorSpecOptionList() {
        OperatorSpecOptionListParam param = new OperatorSpecOptionListParam();
        param.setCurrentPage(1);
        param.setPageSize(10);
        ProductSpecDO specDO = new ProductSpecDO();
        specDO.setGmtModified(new Date());
        specDO.setStatus(true);
        specDO.setSpecName("尺寸");
        specDO.setProductCategoryId(2);
        specDO.setGmtCreate(new Date());
        specDO.setOrdinal((byte) 1);
        productSpecDOMapper.insertSelective(specDO);

        ProductSpecOptionDO optionDO = new ProductSpecOptionDO();
        optionDO.setGmtModified(new Date());
        optionDO.setStatus(true);
        optionDO.setOptionName("XXL");
        optionDO.setProductSpecId(specDO.getId());
        optionDO.setGmtCreate(new Date());
        optionDO.setOrdinal((byte) 1);
        productSpecOptionDOMapper.insertSelective(optionDO);
        RequestBuilder request = post("/productSpecOption/getOperatorSpecOptionList").contentType(MediaType.APPLICATION_JSON)
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

}
