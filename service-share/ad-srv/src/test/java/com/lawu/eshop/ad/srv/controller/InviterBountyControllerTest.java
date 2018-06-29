package com.lawu.eshop.ad.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

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

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.ad.param.InviterBountyParam;
import com.lawu.eshop.ad.param.InviterBountyQueryParam;
import com.lawu.eshop.ad.srv.AdSrvApplicationTest;
import com.lawu.framework.web.HttpCode;

/**
 * @author zhangrc
 * @date 2017/12/22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AdSrvApplicationTest.class)
@WebAppConfiguration
public class InviterBountyControllerTest {

    private MockMvc mvc;

    @Autowired
    private InviterBountyController inviterBountyController;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(inviterBountyController).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveInviterBounty() { 
    	InviterBountyParam param = new InviterBountyParam();
    	param.setAuditorId(1000l);
    	param.setMoney(BigDecimal.valueOf(5));
    	String requestJson = JSONObject.toJSONString(param);
    	
        RequestBuilder request = post("/inviterBounty/saveInviterBounty").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void setInviterBounty() { 
    	
        RequestBuilder request = put("/inviterBounty/setInviterBounty/"+1).param("auditorId", "1000");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void queryInviterBounty() { 
    	InviterBountyQueryParam param = new InviterBountyQueryParam();
    	param.setCurrentPage(1);
    	param.setPageSize(10);
    	String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/inviterBounty/queryInviterBounty/").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }
    
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getInviterBounty() { 
        RequestBuilder request = get("/inviterBounty/getInviterBounty/").param("userNum", "M1000001");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }
    
}
