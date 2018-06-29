package com.lawu.eshop.user.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.common.constants.MemberGradeEnum;
import com.lawu.eshop.user.param.UserGradeQuery;
import com.lawu.eshop.user.param.UserGradeUpdateParam;
import com.lawu.eshop.user.srv.UserSrvApplicationTest;
import com.lawu.eshop.user.srv.domain.UserGradeDO;
import com.lawu.eshop.user.srv.mapper.UserGradeDOMapper;
import com.lawu.framework.web.HttpCode;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserSrvApplicationTest.class)
@WebAppConfiguration
public class UserGradeControllerTest {

    private MockMvc mvc;

    @Autowired
    private UserGradeController userGradeController;

    @Autowired
    private UserGradeDOMapper userGradeDOMapper;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(userGradeController).build();
    }

    @Test
    public void selectLotteryActivityPointByGradeValue() {
        String requestJson = JSONObject.toJSONString(true);
        RequestBuilder request = get("/grade/selectLotteryActivityPointByGradeValue").param("gradeValue","1").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void getLotteryGradeInfo() {
        RequestBuilder request = get("/grade/getLotteryGradeInfo").param("gradeValue", "1");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void selectUserGradeList() {
        RequestBuilder request = get("/grade/selectUserGradeList");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void page() {
        UserGradeQuery dataParam = new UserGradeQuery();
        String requestJson = JSONObject.toJSONString(dataParam);
        RequestBuilder request = post("/grade/page").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void selectUserGradeById() {
        UserGradeDO userGradeDO = new UserGradeDO();
        userGradeDO.setGradeValue(MemberGradeEnum.SILVER.getVal());
        userGradeDO.setGradeWeight(1);
        userGradeDO.setMinGrowthValue(0);
        userGradeDO.setLotteryActivityPoint(100);
        userGradeDO.setGradeName("白银会员");
        userGradeDO.setGmtCreate(new Date());
        userGradeDOMapper.insertSelective(userGradeDO);

        String requestJson = JSONObject.toJSONString(true);
        RequestBuilder request = get("/grade/"+userGradeDO.getId()).param("id",userGradeDO.getId().toString()).contentType(MediaType.APPLICATION_JSON).content(requestJson);;
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void updateById() {
        UserGradeDO userGradeDO = new UserGradeDO();
        userGradeDO.setGradeValue(MemberGradeEnum.SILVER.getVal());
        userGradeDO.setGradeWeight(1);
        userGradeDO.setMinGrowthValue(0);
        userGradeDO.setLotteryActivityPoint(100);
        userGradeDO.setGradeName("白银会员");
        userGradeDO.setGmtCreate(new Date());
        userGradeDOMapper.insertSelective(userGradeDO);

        UserGradeDO userGradeDO1 = new UserGradeDO();
        userGradeDO1.setGradeValue(MemberGradeEnum.PLATINUM.getVal());
        userGradeDO1.setGradeWeight(3);
        userGradeDO1.setMinGrowthValue(300);
        userGradeDO1.setLotteryActivityPoint(300);
        userGradeDO1.setGradeName("铂金会员");
        userGradeDO1.setGmtCreate(new Date());
        userGradeDOMapper.insertSelective(userGradeDO1);

        UserGradeUpdateParam param = new UserGradeUpdateParam();
        param.setGradeName("白银会员");
        param.setGradeWeight(1);
        param.setMinGrowthValue(0);
        param.setLotteryActivityPoint(100);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/grade/updateById/"+userGradeDO.getId()).param("id",userGradeDO.getId().toString()).contentType(MediaType.APPLICATION_JSON).content(requestJson);;
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
}
