package com.lawu.eshop.property.srv.controller;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.property.constants.*;
import com.lawu.eshop.property.param.*;
import com.lawu.eshop.property.srv.PropertySrvApplicationTest;
import com.lawu.eshop.property.srv.domain.BankAccountDO;
import com.lawu.eshop.property.srv.domain.BankDO;
import com.lawu.eshop.property.srv.domain.WithdrawCashDO;
import com.lawu.eshop.property.srv.mapper.BankAccountDOMapper;
import com.lawu.eshop.property.srv.mapper.BankDOMapper;
import com.lawu.eshop.property.srv.mapper.WithdrawCashDOMapper;
import com.lawu.framework.web.HttpCode;
import com.lawu.utils.DateUtil;

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

import java.math.BigDecimal;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author yangqh
 * @date 2017/7/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PropertySrvApplicationTest.class)
@WebAppConfiguration
public class CashManageBackageControllerTest {

    private MockMvc mvc;

    @Autowired
    private CashManageBackageController cashManageBackageController;

    @Autowired
    private BankDOMapper bankDOMapper;

    @Autowired
    private WithdrawCashDOMapper withdrawCashDOMapper;

    @Autowired
    private BankAccountDOMapper bankAccountDOMapper;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(cashManageBackageController).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void findCashInfo() {
        CashBackageQueryDataParam param = new CashBackageQueryDataParam();
        param.setBeginDate(DateUtil.getDateFormat(new Date(),"yyyy-MM-dd"));
        param.setEndDate(DateUtil.getDateFormat(new Date(),"yyyy-MM-dd"));
        param.setCurrentPage(1);
        param.setPageSize(10);
        param.setUserTypeEnum(UserTypeEnum.MEMBER);
        param.setCashStatsuEnum(CashStatusEnum.ACCEPT);

        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/cashBackage/findCashInfo").contentType(MediaType.APPLICATION_JSON).content(requestJson);

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
    public void getTotalNum() {
        CashBackageQuerySumParam param = new CashBackageQuerySumParam();
        param.setUserTypeEnum(UserTypeEnum.MEMBER);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/cashBackage/getTotalNum").contentType(MediaType.APPLICATION_JSON).content(requestJson);

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
    public void findCashInfoDetail() {
        BankDO bdo = new BankDO();
        bdo.setName("test");
        bdo.setIconUrl("/1.jpg");
        bdo.setStatus(new Byte("1"));
        bdo.setOrdinal(1);
        bdo.setGmtCreate(new Date());
        bankDOMapper.insert(bdo);

        BankAccountDO bankAccountDO=new BankAccountDO();
        bankAccountDO.setUserNum("10001");
        bankAccountDO.setAccountName("习大大");
        bankAccountDO.setAccountNumber("6217852000014838927");
        bankAccountDO.setBankId(bdo.getId());
        bankAccountDO.setSubBranchName("南山支行");
        bankAccountDO.setStatus(new Byte("1"));
        BankDO bankDO=bankDOMapper.selectByPrimaryKey(bdo.getId());
        bankAccountDO.setNote("中银(0000)");
        bankAccountDOMapper.insert(bankAccountDO);

        WithdrawCashDO withdrawCashDO = new WithdrawCashDO();
        withdrawCashDO.setCashMoney(new BigDecimal("10"));
        withdrawCashDO.setCurrentScale("1");
        withdrawCashDO.setMoney(BigDecimal.valueOf(10));
        withdrawCashDO.setUserNum("M10001");
        withdrawCashDO.setUserType(new Byte("1"));
        withdrawCashDO.setChannel(new Byte("1"));
        withdrawCashDO.setStatus(CashStatusEnum.APPLY.getVal());
        withdrawCashDO.setBusinessBankAccountId(bankAccountDO.getId());
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

        CashBackageQueryDetailParam param = new CashBackageQueryDetailParam();
        param.setUserNum("M10001");
        param.setCurrentPage(1);
        param.setPageSize(10);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/cashBackage/findCashInfoDetail").contentType(MediaType.APPLICATION_JSON).content(requestJson);

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
    public void updateWithdrawCash() {
        CashBackageOperDataParam param = new CashBackageOperDataParam();
        param.setAuditUserName("admin");
        param.setAuditUserId(1L);
        param.setId("1");
        param.setFailReason("");
        param.setCashOperEnum(CashOperEnum.SUCCESS);
        param.setUserNum("M10001");
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/cashBackage/updateWithdrawCash").contentType(MediaType.APPLICATION_JSON).content(requestJson);

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
    public void selectWithdrawCashListByDateAndStatus() {
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

        WithdrawCashReportParam param = new  WithdrawCashReportParam();
        param.setDate(DateUtil.getDateFormat(new Date(),"yyyy-MM-dd"));
        param.setStatus(CashStatusEnum.APPLY.getVal());
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/cashBackage/selectWithdrawCashListByDateAndStatus").contentType(MediaType.APPLICATION_JSON).content(requestJson);

        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
