package com.lawu.eshop.mall.srv.controller;

import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.mall.srv.MallSrvApplicationTest;
import com.lawu.eshop.mall.srv.domain.ExpressCompanyDO;
import com.lawu.eshop.mall.srv.mapper.ExpressCompanyDOMapper;
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

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author zhangyong
 * @date 2017/7/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MallSrvApplicationTest.class)
@WebAppConfiguration
public class ExpressCompanyControllerTest {
    private MockMvc mvc;

    @Autowired
    private ExpressCompanyController expressCompanyController;

    @Autowired
    private ExpressCompanyDOMapper expressCompanyDOMapper;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(expressCompanyController).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void list(){
        RequestBuilder request = get("/expressCompany/list");

        ResultActions perform = null;
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
    public void group(){
        RequestBuilder request = get("/expressCompany/group");

        ResultActions perform = null;
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
    public void getInfo(){
        RequestBuilder request = get("/expressCompany/get/1");

        ResultActions perform = null;
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
    public void listByKeyWord(){
        ExpressCompanyDO companyDO = new ExpressCompanyDO();
        companyDO.setGmtCreate(new Date());
        companyDO.setStatus(StatusEnum.VALID.getValue());
        companyDO.setOrdinal(1);
        companyDO.setCode("code");
        companyDO.setName("keyWord");
        expressCompanyDOMapper.insert(companyDO);
        RequestBuilder request;
        request = get("/expressCompany/keyWord").param("keyWord","keyWord");
        ResultActions perform = null;
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request = get("/expressCompany/keyWord").param("keyWord","test");
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
