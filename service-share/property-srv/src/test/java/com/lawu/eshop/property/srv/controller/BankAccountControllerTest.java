package com.lawu.eshop.property.srv.controller;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.property.param.BankAccountParam;
import com.lawu.eshop.property.srv.PropertySrvApplicationTest;
import com.lawu.eshop.property.srv.domain.BankAccountDO;
import com.lawu.eshop.property.srv.domain.BankDO;
import com.lawu.eshop.property.srv.mapper.BankAccountDOMapper;
import com.lawu.eshop.property.srv.mapper.BankDOMapper;
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
 * @author yangqh
 * @date 2017/7/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PropertySrvApplicationTest.class)
@WebAppConfiguration
public class BankAccountControllerTest {

    private MockMvc mvc;

    @Autowired
    private BankAccountController bankAccountController;

    @Autowired
    private BankAccountDOMapper bankAccountDOMapper;

    @Autowired
    private BankDOMapper bankDOMapper;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(bankAccountController).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveBankAccount() {
        BankDO bdo = new BankDO();
        bdo.setName("test");
        bdo.setIconUrl("/1.jpg");
        bdo.setStatus(new Byte("1"));
        bdo.setOrdinal(1);
        bdo.setGmtCreate(new Date());
        bankDOMapper.insert(bdo);

        BankAccountParam param = new BankAccountParam();
        param.setAccountName("zhangsan");
        param.setAccountNumber("6217852000014838927");
        param.setSubBranchName("南山");
        param.setBankId(bdo.getId());
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/bankAccount/saveBankAccount").contentType(MediaType.APPLICATION_JSON).content(requestJson).param("userNum", "M10001");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
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

        BankAccountDO bankAccountDO=new BankAccountDO();
        bankAccountDO.setUserNum("M10001");
        bankAccountDO.setAccountName("习大大");
        bankAccountDO.setAccountNumber("6217852000014838927");
        bankAccountDO.setBankId(bdo.getId());
        bankAccountDO.setSubBranchName("南山支行");
        bankAccountDO.setStatus(new Byte("1"));
        BankDO bankDO=bankDOMapper.selectByPrimaryKey(bdo.getId());
        bankAccountDO.setNote("中银(0000)");
        bankAccountDOMapper.insert(bankAccountDO);

        RequestBuilder request = get("/bankAccount/selectMyBankAccount").param("userNum","M10001");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_ACCEPTED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void remove() {
        RequestBuilder request = delete("/bankAccount/remove/1");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_NO_CONTENT)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectAccount() {
        BankDO bdo = new BankDO();
        bdo.setName("test");
        bdo.setIconUrl("/1.jpg");
        bdo.setStatus(new Byte("1"));
        bdo.setOrdinal(1);
        bdo.setGmtCreate(new Date());
        bankDOMapper.insert(bdo);

        BankAccountDO bankAccountDO=new BankAccountDO();
        bankAccountDO.setUserNum("M10001");
        bankAccountDO.setAccountName("习大大");
        bankAccountDO.setAccountNumber("6217852000014838927");
        bankAccountDO.setBankId(bdo.getId());
        bankAccountDO.setSubBranchName("南山支行");
        bankAccountDO.setStatus(new Byte("1"));
        BankDO bankDO=bankDOMapper.selectByPrimaryKey(bdo.getId());
        bankAccountDO.setNote("中银(0000)");
        bankAccountDOMapper.insert(bankAccountDO);

        RequestBuilder request = get("/bankAccount/selectAccount/"+bankAccountDO.getId());
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_ACCEPTED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateBankAccount() {
        BankDO bdo = new BankDO();
        bdo.setName("test");
        bdo.setIconUrl("/1.jpg");
        bdo.setStatus(new Byte("1"));
        bdo.setOrdinal(1);
        bdo.setGmtCreate(new Date());
        bankDOMapper.insert(bdo);

        BankAccountDO bankAccountDO=new BankAccountDO();
        bankAccountDO.setUserNum("M10001");
        bankAccountDO.setAccountName("习大大");
        bankAccountDO.setAccountNumber("6217852000014838927");
        bankAccountDO.setBankId(bdo.getId());
        bankAccountDO.setSubBranchName("南山支行");
        bankAccountDO.setStatus(new Byte("1"));
        BankDO bankDO=bankDOMapper.selectByPrimaryKey(bdo.getId());
        bankAccountDO.setNote("中银(0000)");
        bankAccountDOMapper.insert(bankAccountDO);

        BankAccountParam param = new BankAccountParam();
        param.setAccountName("zhangsan");
        param.setAccountNumber("6217852000014838921");
        param.setSubBranchName("南山");
        param.setBankId(bdo.getId());
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = put("/bankAccount/updateBankAccount/"+bankAccountDO.getId()).contentType(MediaType.APPLICATION_JSON).content(requestJson).param("userNum", "M10001");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
}
