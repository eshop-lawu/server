package com.lawu.eshop.property.srv.controller;

import com.lawu.eshop.property.srv.PropertySrvApplicationTest;
import com.lawu.eshop.property.srv.domain.BankDO;
import com.lawu.eshop.property.srv.mapper.BankDOMapper;
import com.lawu.framework.web.HttpCode;

import org.junit.Assert;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author yangqh
 * @date 2017/7/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PropertySrvApplicationTest.class)
@WebAppConfiguration
public class BankControllerTest {

    private MockMvc mvc;

    @Autowired
    private BankController bankController;

    @Autowired
    private BankDOMapper bankDOMapper;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(bankController).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectMyBankAccount() {
        BankDO bdo = new BankDO();
        bdo.setName("test");
        bdo.setIconUrl("/1.jpg");
        bdo.setStatus(new Byte("1"));
        bdo.setOrdinal(1);
        bdo.setGmtCreate(new Date());
        bankDOMapper.insert(bdo);

        RequestBuilder request = get("/bank/findBank");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_ACCEPTED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

}
