package com.lawu.eshop.merchant.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.lawu.eshop.merchant.api.MerchantApiApplicationTest;
import com.lawu.framework.web.HttpCode;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MerchantApiApplicationTest.class)
@WebAppConfiguration
public class ProductControllerTest {
    private MockMvc mvc;

    @Autowired
    private ProductController productController;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void selectProduct() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("productSortFieldEnum", "TOTAL_INVENTORY");
        map.add("productStatus", "PRODUCT_STATUS_UP");
        RequestBuilder request = post("/product/selectProduct").header("authorization", "8888").params(map);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void updateProductStatus() {
        RequestBuilder request = put("/product/updateProductStatus").header("authorization", "8888").param("ids", "10").param("productStatus", "PRODUCT_STATUS_UP");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void selectEditProductById() {
        RequestBuilder request = get("/product/selectEditProductById").header("authorization", "8888").param("productId", "10");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void saveProduct() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("productId", "10");
        map.add("imageContents", "[\"pic\"]");
        map.add("backProductImageUrls", "[\"pic\"]");
        map.add("backProductDetailImageUrls", "[\"pic\"]");
        map.add("spec", "[{\"id\":10,\"name\":\"test\",\"originalPrice\":10,\"price\":10,\"inventory\":10,\"inventoryTrans\":10,\"salesVolume\":10}]");
        RequestBuilder request = post("/product/saveProduct").header("authorization", "8888").params(map);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void saveProductInfo() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("productId", "10");
        map.add("imageContents", "[\"pic\"]");
        map.add("backProductImageUrls", "[\"pic\"]");
        map.add("backProductDetailImageUrls", "[\"pic\"]");
        map.add("spec", "[{\"id\":10,\"name\":\"test\",\"originalPrice\":10,\"price\":10,\"inventory\":10,\"inventoryTrans\":10,\"salesVolume\":10}]");
        RequestBuilder request = post("/product/saveProductInfo").header("authorization", "8888").params(map);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

}
