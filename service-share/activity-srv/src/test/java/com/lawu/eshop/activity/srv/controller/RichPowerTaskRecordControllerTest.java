package com.lawu.eshop.activity.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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
import com.lawu.eshop.activity.param.RichPowerTaskRecordParam;
import com.lawu.eshop.activity.srv.ActivitySrvApplicationTest;
import com.lawu.eshop.cache.constants.PowerTaskTypeEnum;
import com.lawu.framework.web.HttpCode;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitySrvApplicationTest.class)
public class RichPowerTaskRecordControllerTest {

    private MockMvc mvc;

    @Autowired
    private RichPowerTaskRecordController richPowerTaskRecordController;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(richPowerTaskRecordController).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveRichPowerTaskRecord() {
    	RichPowerTaskRecordParam param = new RichPowerTaskRecordParam();
    	param.setMemberNum("M949121843325829209");
		param.setType(PowerTaskTypeEnum.AD);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/richPowerTaskRecord/saveRichPowerTaskRecord").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
    
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getPowerTasks() {
        RequestBuilder request = get("/richPowerTaskRecord/getPowerTasks").param("memberNum", "M949121843325829209");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void resetTaskRecord(){
        RequestBuilder request = put("/richPowerTaskRecord/resetTaskRecord").param("currentPage", "1").param("pageSize", "10");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getPowerTaskRecordListCount(){
        RequestBuilder request = get("/richPowerTaskRecord/getPowerTaskRecordListCount");
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
