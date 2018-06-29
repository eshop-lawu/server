package com.lawu.eshop.activity.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.activity.constants.PointLotteryActivityOrderStatusEnum;
import com.lawu.eshop.activity.param.PointLotteryAttentParam;
import com.lawu.eshop.activity.srv.ActivitySrvApplicationTest;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityOrderDO;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityRecordDO;
import com.lawu.eshop.activity.srv.mapper.PointLotteryActivityOrderDOMapper;
import com.lawu.eshop.activity.srv.mapper.PointLotteryActivityRecordDOMapper;
import com.lawu.framework.web.HttpCode;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitySrvApplicationTest.class)
public class PointLotteryActivityOrderControllerTest {

    private MockMvc mvc;

    @Autowired
    private PointLotteryActivityOrderController pointLotteryActivityOrderController;

    @Autowired
    private PointLotteryActivityOrderDOMapper pointLotteryActivityOrderDOMapper;

    @Autowired
    private PointLotteryActivityRecordDOMapper pointLotteryActivityRecordDOMapper;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(pointLotteryActivityOrderController).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void savePointLotteryActivityOrder() {
        PointLotteryAttentParam param = new PointLotteryAttentParam();
        param.setStatusEnum(PointLotteryActivityOrderStatusEnum.PENDING);
        param.setPayPoint(BigDecimal.valueOf(10));
        param.setPointLotteryActivityId(100L);
        param.setAttentCount(1);
        param.setMobile("1366666666");
        param.setUserNum("M001");
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/pointLotteryActivityOrder/savePointLotteryActivityOrder").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void getPointLotteryAttentInfo() {
        PointLotteryActivityOrderDO orderDO = new PointLotteryActivityOrderDO();
        orderDO.setPointLotteryActivityId(100L);
        orderDO.setStatus((byte) 1);
        pointLotteryActivityOrderDOMapper.insertSelective(orderDO);

        PointLotteryActivityRecordDO recordDO = new PointLotteryActivityRecordDO();
        recordDO.setUserNum("M001");
        recordDO.setPointLotteryActivityId(100L);
        recordDO.setPointLotteryActivityOrderId(orderDO.getId());
        recordDO.setLotteryNum(10);
        pointLotteryActivityRecordDOMapper.insertSelective(recordDO);

        RequestBuilder request = get("/pointLotteryActivityOrder/getPointLotteryAttentInfo/" + orderDO.getId());
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
