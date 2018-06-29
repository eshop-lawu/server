package com.lawu.eshop.activity.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.lawu.eshop.activity.param.DiamondConfigParam;
import com.lawu.eshop.activity.param.PowerTaskBaseConfigParam;
import com.lawu.eshop.activity.param.PowerTaskConfigParam;
import com.lawu.eshop.activity.srv.ActivitySrvApplicationTest;
import com.lawu.eshop.activity.srv.servcie.RichConfigService;
import com.lawu.eshop.cache.constants.PowerTaskStatusEnum;
import com.lawu.eshop.cache.constants.PowerTaskTypeEnum;
import com.lawu.framework.web.HttpCode;

/**
 * @author zhangrc
 * @Description
 * @date 2018年5月3日
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitySrvApplicationTest.class)
public class RichConfigControllerTest {

    private MockMvc mvc;

    @Autowired
    private RichConfigController richConfigController;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(richConfigController).build();
    }

    @Autowired
    private RichConfigService richConfigService;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void savePowerTaskConfig() {
        PowerTaskConfigParam param = new PowerTaskConfigParam();
        List<PowerTaskBaseConfigParam> tasks = new ArrayList<>();
        PowerTaskBaseConfigParam configParam = new PowerTaskBaseConfigParam();
        configParam.setType(PowerTaskTypeEnum.LOGIN);
        configParam.setTaskCount(1);
        configParam.setPowerCount(1);
        configParam.setStatus(PowerTaskStatusEnum.ENABLED);
        tasks.add(configParam);
        param.setTasks(tasks);
        param.setPowerEffectiveTime(new Date());
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/richConfig/savePowerTaskConfig").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void getPowerTaskConfig() {
        RequestBuilder request = get("/richConfig/getPowerTaskConfig");
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
    public void saveDiamondConfig() {
        DiamondConfigParam param = new DiamondConfigParam();
        param.setRichPopulation(300);
        param.setCreationPeople(100);
        param.setDailyDiamond(BigDecimal.valueOf(100));
        param.setOnceDiamond(BigDecimal.valueOf(0.25));
        param.setDailyDiamondLucky(BigDecimal.valueOf(20));
        param.setOnceDiamondLucky(BigDecimal.valueOf(3));
        param.setGainDiamondLuckyPower(10);
        param.setGainDiamondLuckyConsumePower(5);
        param.setGainDiamondLuckyScale(BigDecimal.valueOf(10));
        param.setInitPower(30);
        param.setInitReleaseTime("08:00");
        param.setReleaseInterval(2);
        param.setIsOpen(true);
        param.setDiamondGrowTime(48);
        param.setDiamondEffectiveTime(new Date());
        param.setNotice("test");
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/richConfig/saveDiamondConfig").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void getDiamondConfig() {
        RequestBuilder request = get("/richConfig/getDiamondConfig");
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
    public void effectiveJob() {
        RequestBuilder request = get("/richConfig/effectiveJob");
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
