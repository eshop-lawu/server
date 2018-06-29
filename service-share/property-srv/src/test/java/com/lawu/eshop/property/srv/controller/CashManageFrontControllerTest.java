package com.lawu.eshop.property.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Date;

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
import com.lawu.eshop.property.constants.CashStatusEnum;
import com.lawu.eshop.property.param.CashBillDataParam;
import com.lawu.eshop.property.param.CashDataParam;
import com.lawu.eshop.property.srv.PropertySrvApplicationTest;
import com.lawu.eshop.property.srv.domain.WithdrawCashDO;
import com.lawu.eshop.property.srv.mapper.BankAccountDOMapper;
import com.lawu.eshop.property.srv.mapper.BankDOMapper;
import com.lawu.eshop.property.srv.mapper.WithdrawCashDOMapper;
import com.lawu.framework.web.HttpCode;

/**
 * @author yangqh
 * @date 2017/7/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PropertySrvApplicationTest.class)
@WebAppConfiguration
public class CashManageFrontControllerTest {

    private MockMvc mvc;

    @Autowired
    private CashManageFrontController cashManageFrontController;

    @Autowired
    private BankDOMapper bankDOMapper;

    @Autowired
    private WithdrawCashDOMapper withdrawCashDOMapper;

    @Autowired
    private BankAccountDOMapper bankAccountDOMapper;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(cashManageFrontController).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void save() {
        CashDataParam param = new CashDataParam();
        param.setUserType(new Byte("1"));
        param.setPayPwd("123456");
        param.setTransactionType(new Byte("1"));
        param.setName("提现");
        param.setRegionFullName("广东省/深圳市/南山区");
        param.setCashMoney("10");
        param.setAccount("1232312");
        param.setAreaId(111);
        param.setCityId(11);
        param.setProvinceId(1);
        param.setGmtCreate(new Date());
        param.setBusinessBankAccountId(1L);

        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/cashFront/save").contentType(MediaType.APPLICATION_JSON).content(requestJson);

        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void findCashList() {
        WithdrawCashDO withdrawCashDO = new WithdrawCashDO();
        withdrawCashDO.setCashMoney(new BigDecimal("10"));
        withdrawCashDO.setCurrentScale("1");
        withdrawCashDO.setMoney(BigDecimal.valueOf(10));
        withdrawCashDO.setUserNum("10001");
        withdrawCashDO.setUserType(new Byte("1"));
        withdrawCashDO.setChannel(new Byte("1"));
        withdrawCashDO.setStatus(CashStatusEnum.APPLY.getVal());
        withdrawCashDO.setBusinessBankAccountId(1L);
        withdrawCashDO.setCashNumber("1111111111111111111");
        withdrawCashDO.setGmtCreate(new Date());
        withdrawCashDO.setAccount("17512036361");
        withdrawCashDO.setName("习大大");
        withdrawCashDO.setProvinceId(1);
        withdrawCashDO.setCityId(11);
        withdrawCashDO.setAreaId(111);
        withdrawCashDO.setGmtAccept(new Date());
        withdrawCashDO.setGmtFinish(new Date());
        withdrawCashDO.setRegionFullName("北京市/县辖/东城区");
        int ret = withdrawCashDOMapper.insertSelective(withdrawCashDO);
        Assert.assertEquals(1,ret);

        CashBillDataParam param = new  CashBillDataParam();
        param.setUserNum("M10001");
        param.setPageSize(10);
        param.setCurrentPage(1);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/cashFront/findCashList").contentType(MediaType.APPLICATION_JSON).content(requestJson);

        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void cashDetail() {
        RequestBuilder request = get("/cashFront/cashDetail").param("id","1");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

}
