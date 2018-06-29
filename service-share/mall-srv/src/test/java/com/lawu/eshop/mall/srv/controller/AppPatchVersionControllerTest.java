package com.lawu.eshop.mall.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
import com.lawu.eshop.mall.constants.AppPatchVersionStatusEnum;
import com.lawu.eshop.mall.param.AppPatchVersionParam;
import com.lawu.eshop.mall.query.OperatorAppPatchVersionQuery;
import com.lawu.eshop.mall.srv.MallSrvApplicationTest;
import com.lawu.framework.web.HttpCode;

/**
 * @author meishuquan
 * @date 2017/12/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MallSrvApplicationTest.class)
@WebAppConfiguration
public class AppPatchVersionControllerTest {

    private MockMvc mvc;

    @Autowired
    private AppPatchVersionController appPatchVersionController;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(appPatchVersionController).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveAppPatchVersion() {
        AppPatchVersionParam param = new AppPatchVersionParam();
        param.setAppVersionId(1L);
        param.setAppVersion("2.6.0");
        param.setPatchVersion(1);
        param.setUpdateDesc("test");
        param.setStatusEnum(AppPatchVersionStatusEnum.ENABLE);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/appPatchVersion/saveAppPatchVersion").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void updateAppPatchVersionStatus() {
        RequestBuilder request = put("/appPatchVersion/updateAppPatchVersionStatus/1").param("statusEnum", "ENABLE");
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
    public void listOperatorAppPatchVersion() {
        OperatorAppPatchVersionQuery query = new OperatorAppPatchVersionQuery();
        query.setAppVersionId(1L);
        String requestJson = JSONObject.toJSONString(query);
        RequestBuilder request = post("/appPatchVersion/listOperatorAppPatchVersion").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void getAppPatchVersion() {
        RequestBuilder request = get("/appPatchVersion/getAppPatchVersion").param("typeEnum", "MEMBER").param("platform", "2").param("appVersion", "2.6.0");
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
