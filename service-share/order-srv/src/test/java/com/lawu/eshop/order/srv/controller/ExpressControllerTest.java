package com.lawu.eshop.order.srv.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.order.dto.ExpressRecognitionDetailDTO;
import com.lawu.eshop.order.dto.foreign.ExpressInquiriesDetailDTO;
import com.lawu.eshop.order.param.ExpressQueryParam;
import com.lawu.eshop.order.srv.OrderSrvApplicationTest;
import com.lawu.framework.web.HttpCode;

/**
 * 
 * @author jiangxinjun
 * @date 2017年9月11日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OrderSrvApplicationTest.class)
@WebAppConfiguration
public class ExpressControllerTest {

    private MockMvc mvc;

    @Autowired
    private ExpressController expressController;
    
    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(expressController).build();
    }

    @Test
    public void inquiries() throws Exception {
		ExpressQueryParam expected = new ExpressQueryParam();
		expected.setExpCode("SF");;
		expected.setExpNo("123456");
    	String content = JSONObject.toJSONString(expected);
    	
    	RequestBuilder request = MockMvcRequestBuilders.put("/express/inquiries").contentType(MediaType.APPLICATION_JSON).content(content);
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        ExpressInquiriesDetailDTO actual = JSONObject.parseObject(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), ExpressInquiriesDetailDTO.class);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getState());
    }
    
    @Test
    public void recognition() throws Exception {
    	RequestBuilder request = MockMvcRequestBuilders.get("/express/recognition/" + "123456");
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_OK))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        ExpressRecognitionDetailDTO actual = JSONObject.parseObject(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), ExpressRecognitionDetailDTO.class);
        Assert.assertNotNull(actual);
    }
}