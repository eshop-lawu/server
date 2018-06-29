package com.lawu.eshop.merchant.api.controller;

import com.lawu.eshop.merchant.api.MerchantApiApplicationTest;
import com.lawu.framework.web.HttpCode;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MerchantApiApplicationTest.class)
@WebAppConfiguration
public class MerchantFavoredControllerTest {
    private MockMvc mvc;

    @Autowired
    private MerchantFavoredController merchantFavoredController;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(merchantFavoredController).build();
    }

    @Test
    public void saveMerchantFavoredInfo() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("typeEnum", "TYPE_FULL");
        RequestBuilder request = post("/merchantFavored/saveMerchantFavoredInfo").header("authorization", "8888").params(map);
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
    public void findFavoredByMerchantId() {
        RequestBuilder request = get("/merchantFavored/findFavoredByMerchantId").header("authorization", "8888");
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
    public void delMerchantFavoredInfo() {
        RequestBuilder request = delete("/merchantFavored/delMerchantFavoredInfo/10").header("authorization", "8888");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_NO_CONTENT)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_NO_CONTENT, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void updateMerchantFavoredInfo() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("typeEnum", "TYPE_FULL");
        RequestBuilder request = put("/merchantFavored/updateMerchantFavoredInfo").header("authorization", "8888").params(map);
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
