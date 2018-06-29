package com.lawu.eshop.user.srv.controller;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.user.param.AddressParam;
import com.lawu.eshop.user.srv.UserSrvApplicationTest;
import com.lawu.eshop.user.srv.domain.AddressDO;
import com.lawu.eshop.user.srv.mapper.AddressDOMapper;
import com.lawu.framework.web.HttpCode;

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

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author meishuquan
 * @date 2017/7/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserSrvApplicationTest.class)
@WebAppConfiguration
public class AddressControllerTest {

    private MockMvc mvc;

    @Autowired
    private AddressController addressController;

    @Autowired
    private AddressDOMapper addressDOMapper;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(addressController).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectByUserNum() {
        RequestBuilder request = get("/address/selectByUserNum/M0001");
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
    public void saveWithUserNum() {
        AddressParam param = new AddressParam();
        param.setName("测试人");
        param.setMobile("13666666666");
        param.setRegionPath("44/4403/440303");
        param.setRegionName("广东省深圳市南山区");
        param.setAddr("大冲商务中心");
        param.setPostcode("550505");
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/address/saveWithUserNum/M0001").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void getById() {
        RequestBuilder request = get("/address/get/100");
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
    public void update() {
        AddressParam param = new AddressParam();
        param.setName("测试人");
        param.setMobile("13666666666");
        param.setRegionPath("44/4403/440303");
        param.setRegionName("广东省深圳市南山区");
        param.setAddr("大冲商务中心");
        param.setPostcode("550505");
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/address/update/100").contentType(MediaType.APPLICATION_JSON).content(requestJson).param("userNum", "M0001");
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
    public void remove() {
        AddressDO addressDO = new AddressDO();
        addressDO.setUserId(100L);
        addressDO.setUserNum("M0001");
        addressDO.setIsDefault(true);
        addressDO.setStatus(StatusEnum.VALID.getValue());
        addressDO.setMobile("13666666666");
        addressDO.setGmtCreate(new Date());
        addressDO.setGmtModified(new Date());
        addressDOMapper.insertSelective(addressDO);

        RequestBuilder request = delete("/address/remove/" + addressDO.getId()).param("userNum", addressDO.getUserNum());
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
    public void updateDefault() {
        AddressDO addressDO = new AddressDO();
        addressDO.setUserId(100L);
        addressDO.setUserNum("M0001");
        addressDO.setIsDefault(true);
        addressDO.setStatus(StatusEnum.VALID.getValue());
        addressDO.setMobile("13666666666");
        addressDO.setGmtCreate(new Date());
        addressDO.setGmtModified(new Date());
        addressDOMapper.insertSelective(addressDO);

        RequestBuilder request = get("/address/updateDefault/" + addressDO.getId()).param("userNum", addressDO.getUserNum());
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
