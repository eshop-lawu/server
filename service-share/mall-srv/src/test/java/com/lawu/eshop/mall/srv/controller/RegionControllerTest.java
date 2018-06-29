package com.lawu.eshop.mall.srv.controller;

import com.lawu.eshop.mall.srv.MallSrvApplicationTest;
import com.lawu.framework.web.HttpCode;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author zhangyong
 * @date 2017/7/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MallSrvApplicationTest.class)
@WebAppConfiguration
public class RegionControllerTest {
    private MockMvc mvc;

    @Autowired
    private  RegionController regionController;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(regionController).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getRegionFullName(){
        RequestBuilder request = null;
        ResultActions perform = null;
        request = get("/region/getRegionFullName/1");
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getAreaName(){
        RequestBuilder request = null;
        ResultActions perform = null;
        request = get("/region/getAreaName").param("regionPath","44/4403");
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public  void getRegionLevelTwo(){
        RequestBuilder request = null;
        ResultActions perform = null;
        request = get("/region/getRegionLevelTwo");
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getRegion(){
        RequestBuilder request = null;
        ResultActions perform = null;
        request = get("/region/getRegion/4403");
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateRegionLonLat(){
        RequestBuilder request = null;
        ResultActions perform = null;
        request = get("/region/updateRegionLonLat");
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
