package com.lawu.eshop.activity.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.activity.param.HelpRedpacketAttendParam;
import com.lawu.eshop.activity.param.HelpRedpacketDetailOperatorParam;
import com.lawu.eshop.activity.srv.ActivitySrvApplicationTest;
import com.lawu.framework.web.HttpCode;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitySrvApplicationTest.class)
public class HelpRedpacketAttendDetailControllerTest {
    
    private MockMvc mvc;
    
    @Autowired
    private HelpRedpacketAttendDetailController helpRedpacketAttendDetailController;
    
    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(helpRedpacketAttendDetailController).build();
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void helpRedpacketAttendDetail() {
    	
        RequestBuilder request = get("/helpRedpacketAttendDetail/helpRedpacketAttendDetail").param("userNum", "M894378717298556928").param("helpCount", "10");
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
    public void helpRedpacketAttend() {
    	HelpRedpacketAttendParam attendParam = new HelpRedpacketAttendParam();
    	attendParam.setAccount("18267122441");
		attendParam.setHeadimg("head/1503319642099389798.jpg");
		attendParam.setNickname("E店用户");
		attendParam.setUserNum("M894378717298556928");
		attendParam.setWxOpenid("1234");
		String requestJson = JSONObject.toJSONString(attendParam);
        RequestBuilder request = post("/helpRedpacketAttendDetail/helpRedpacketAttend").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void helpRedpacketAttendPageOperator() {
    	HelpRedpacketDetailOperatorParam param = new HelpRedpacketDetailOperatorParam();
    	param.setCurrentPage(1);
    	param.setPageSize(20);
		String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/helpRedpacketAttendDetail/helpRedpacketAttendPageOperator").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void getHelpRedpacket() {
    	
        RequestBuilder request = put("/helpRedpacketAttendDetail/getHelpRedpacket").param("userNum", "M894378717298556928");
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
    public void listSendRedpacketAttendDetail() {
        RequestBuilder request = get("/helpRedpacketAttendDetail/listSendRedpacketAttendDetail").param("offset", "0").param("pageSize", "0");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
   
    
}
