package com.lawu.eshop.beh.analyze.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.lawu.eshop.beh.analyze.param.AbnormalJobAddParam;
import com.lawu.eshop.beh.analyze.param.AbnormalParam;
import com.lawu.eshop.beh.analyze.srv.BehAnalyzeSrvApplicationTest;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.framework.web.HttpCode;

/**
 * @author zhangyong
 * @date 2018/1/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BehAnalyzeSrvApplicationTest.class)
@WebAppConfiguration
public class AbnormalRecordControllerTest {
    private MockMvc mvc;

    @Autowired
    private AbnormalRecordController abnormalRecordController;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(abnormalRecordController).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Test
    @Rollback
    public void getAbnormalRecord() {
        AbnormalParam param = new AbnormalParam();
        param.setCurrentPage(1);
        param.setPageSize(10);
        String requestJson = JSONObject.toJSONString(param);
        try {
            RequestBuilder request = post("/abnormal/getAbnormalRecord").contentType(MediaType.APPLICATION_JSON).content(requestJson);
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    public void editAbnormalRecord() {
        AbnormalJobAddParam param = new AbnormalJobAddParam();
        param.setType(UserTypeEnum.MEMBER);
        param.setAccount("13111111111");
        param.setUserNum("M132456");
        String requestJson = JSONObject.toJSONString(param);
        try {
            RequestBuilder request = post("/abnormal/editAbnormalRecord").contentType(MediaType.APPLICATION_JSON).content(requestJson);
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
}
