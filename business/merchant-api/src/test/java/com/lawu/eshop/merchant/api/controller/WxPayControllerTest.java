package com.lawu.eshop.merchant.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
public class WxPayControllerTest {
    private MockMvc mvc;

    @Autowired
    private WxPayController wxPayController;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(wxPayController).build();
    }

    @Test
    public void getPrepayInfo() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("bizIds", "10");
        map.add("thirdPayBodyEnum", "B_RECHARGE_BALANCE_I");
        map.add("bizFlagEnum", "BUSINESS_PAY_BALANCE");
        RequestBuilder request = post("/wxPay/getPrepayInfo").header("authorization", "8888").params(map);
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
    public void initPcPay() {
        RequestBuilder request = get("/wxPay/initPcPay").param("token", "8888").param("bizIds", "10").param("thirdPayBodyEnum", "B_RECHARGE_BALANCE_I").param("bizFlagEnum", "BUSINESS_PAY_BALANCE");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

}
