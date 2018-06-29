package com.lawu.eshop.activity.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

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
import com.lawu.eshop.activity.constants.PointLotteryActivityPrizeImageTypeEnum;
import com.lawu.eshop.activity.constants.PointLotteryActivityStatusEnum;
import com.lawu.eshop.activity.param.PointLotteryActivityParam;
import com.lawu.eshop.activity.query.OperatorPointLotteryActivityQuery;
import com.lawu.eshop.activity.query.PointLotteryActivityQuery;
import com.lawu.eshop.activity.srv.ActivitySrvApplicationTest;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityDO;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityPrizeImageDO;
import com.lawu.eshop.activity.srv.mapper.PointLotteryActivityDOMapper;
import com.lawu.eshop.activity.srv.mapper.PointLotteryActivityPrizeImageDOMapper;
import com.lawu.framework.web.HttpCode;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitySrvApplicationTest.class)
public class PointLotteryActivityControllerTest {

    private MockMvc mvc;

    @Autowired
    private PointLotteryActivityController pointLotteryActivityController;

    @Autowired
    private PointLotteryActivityPrizeImageDOMapper pointLotteryActivityPrizeImageDOMapper;

    @Autowired
    private PointLotteryActivityDOMapper pointLotteryActivityDOMapper;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(pointLotteryActivityController).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listPointLotteryActivity() {
        PointLotteryActivityQuery param = new PointLotteryActivityQuery();
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/pointLotteryActivity/listPointLotteryActivity").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void getPointLotteryDetail() {
        RequestBuilder request = get("/pointLotteryActivity/getPointLotteryDetail/1").param("userNum", "M001");
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
    public void getPointLotteryInfo() {
        RequestBuilder request = get("/pointLotteryActivity/getPointLotteryInfo/1");
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
    public void savePointLotteryActivity() {
        PointLotteryActivityParam param = new PointLotteryActivityParam();
        param.setBeginTime(new Date());
        param.setEndTime(new Date());
        param.setStatusEnum(PointLotteryActivityStatusEnum.PUBLISHED);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/pointLotteryActivity/savePointLotteryActivity").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void listOperatorPointLotteryActivity() {
        OperatorPointLotteryActivityQuery param = new OperatorPointLotteryActivityQuery();
        param.setStatusEnum(PointLotteryActivityStatusEnum.PUBLISHED);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/pointLotteryActivity/listOperatorPointLotteryActivity").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void updatePointLotteryActivityStatus() {
        RequestBuilder request = put("/pointLotteryActivity/updatePointLotteryActivityStatus/1").param("statusEnum", "PUBLISHED");
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
    public void executeUpdatePointLotteryActivityStatus() {
        RequestBuilder request = put("/pointLotteryActivity/executeUpdatePointLotteryActivityStatus");
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
    public void getPointLotteryActivity() {
        PointLotteryActivityDO activityDO = new PointLotteryActivityDO();
        activityDO.setPrizeName("test");
        activityDO.setLotteryTime(new Date());
        activityDO.setStatus(PointLotteryActivityStatusEnum.PROCESSING.getVal());
        pointLotteryActivityDOMapper.insertSelective(activityDO);

        PointLotteryActivityPrizeImageDO imageDO = new PointLotteryActivityPrizeImageDO();
        imageDO.setPointLotteryActivityId(activityDO.getId());
        imageDO.setType(PointLotteryActivityPrizeImageTypeEnum.ACTIVITY_INTRODUCTIONS.getVal());
        pointLotteryActivityPrizeImageDOMapper.insertSelective(imageDO);

        imageDO = new PointLotteryActivityPrizeImageDO();
        imageDO.setPointLotteryActivityId(activityDO.getId());
        imageDO.setType(PointLotteryActivityPrizeImageTypeEnum.LOTTERY_INFO.getVal());
        pointLotteryActivityPrizeImageDOMapper.insertSelective(imageDO);

        RequestBuilder request = get("/pointLotteryActivity/getPointLotteryActivity/" + activityDO.getId());
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
    public void listRelatePointLotteryActivity() {
        RequestBuilder request = get("/pointLotteryActivity/listRelatePointLotteryActivity");
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
