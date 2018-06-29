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
import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.property.constants.BusinessDepositOperEnum;
import com.lawu.eshop.property.constants.BusinessDepositStatusEnum;
import com.lawu.eshop.property.param.BusinessDepositOperDataParam;
import com.lawu.eshop.property.param.BusinessDepositQueryDataParam;
import com.lawu.eshop.property.param.BusinessDepositSaveDataParam;
import com.lawu.eshop.property.param.BusinessRefundDepositDataParam;
import com.lawu.eshop.property.param.NotifyCallBackParam;
import com.lawu.eshop.property.srv.PropertySrvApplicationTest;
import com.lawu.eshop.property.srv.domain.BusinessDepositDO;
import com.lawu.eshop.property.srv.mapper.BankDOMapper;
import com.lawu.eshop.property.srv.mapper.BusinessDepositDOMapper;
import com.lawu.framework.web.HttpCode;

/**
 * @author yangqh
 * @date 2017/7/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PropertySrvApplicationTest.class)
@WebAppConfiguration
public class BusinessDepositControllerTest {

    private MockMvc mvc;

    @Autowired
    private BusinessDepositController businessDepositController;

    @Autowired
    private BankDOMapper bankDOMapper;

    @Autowired
    private BusinessDepositDOMapper businessDepositDOMapper;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(businessDepositController).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void save() {
        BusinessDepositSaveDataParam param = new BusinessDepositSaveDataParam();
        param.setBusinessId(1L);
        param.setUserNum("M10001");
        param.setBusinessAccount("fdfdf");
        param.setCityId("11");
        param.setAreaId("111");
        param.setProvinceId("1");
        param.setBusinessName("zans");
        param.setDeposit("1000");

        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/businessDeposit/save").contentType(MediaType.APPLICATION_JSON).content(requestJson);

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
    public void doHandleDepositNotify() {
        NotifyCallBackParam param = new NotifyCallBackParam();
        param.setUserNum("M10001");
        param.setBizIds("1");
        param.setOutTradeNo("111111");
        param.setTradeNo("22222");
        param.setTransactionPayTypeEnum(TransactionPayTypeEnum.ALIPAY);
        param.setBuyerLogonId("1232**drferf");
        param.setBody("缴纳保证金");
        param.setMerchantId(1L);
        param.setTotalFee("1000");
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/businessDeposit/doHandleDepositNotify").contentType(MediaType.APPLICATION_JSON).content(requestJson);

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
    public void selectDepositList() {
        BusinessDepositDO bddo = new BusinessDepositDO();
        bddo.setBusinessId(1L);
        bddo.setUserNum("B10001");
        bddo.setBusinessAccount("17512036361");
        bddo.setBusinessName("张三");
        bddo.setDepositNumber(IdWorkerHelperImpl.generate(BizIdType.DEPOSIT));
        bddo.setAmount(new BigDecimal("1000"));
        bddo.setStatus(BusinessDepositStatusEnum.PAYING.getVal());
        bddo.setProvinceId(Long.valueOf("1"));
        bddo.setCityId(Long.valueOf("101"));
        bddo.setAreaId(Long.valueOf("10101"));
        bddo.setGmtCreate(new Date());
        businessDepositDOMapper.insertSelective(bddo);

        BusinessDepositQueryDataParam query = new BusinessDepositQueryDataParam();
        query.setContent("17512036361");
        query.setPageSize(10);
        query.setCurrentPage(1);
        String requestJson = JSONObject.toJSONString(query);
        RequestBuilder request = post("/businessDeposit/selectDepositList").contentType(MediaType.APPLICATION_JSON).content(requestJson);

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
    public void updateBusinessDeposit() {
        BusinessDepositDO bddo = new BusinessDepositDO();
        bddo.setBusinessId(1L);
        bddo.setUserNum("B10001");
        bddo.setBusinessAccount("17512036361");
        bddo.setBusinessName("张三");
        bddo.setDepositNumber(IdWorkerHelperImpl.generate(BizIdType.DEPOSIT));
        bddo.setAmount(new BigDecimal("1000"));
        bddo.setStatus(BusinessDepositStatusEnum.PAYING.getVal());
        bddo.setProvinceId(Long.valueOf("1"));
        bddo.setCityId(Long.valueOf("101"));
        bddo.setAreaId(Long.valueOf("10101"));
        bddo.setGmtCreate(new Date());
        businessDepositDOMapper.insertSelective(bddo);

        BusinessDepositOperDataParam param = new BusinessDepositOperDataParam();
        param.setId(bddo.getId().toString());
        param.setOperUserName("admin");
        param.setOperUserId(1L);
        param.setUserNum("M10001");
        param.setFailReason("no");
        param.setBusinessId(1L);
        param.setBusinessDepositOperEnum(BusinessDepositOperEnum.REFUND_FAILURE);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/businessDeposit/updateBusinessDeposit").contentType(MediaType.APPLICATION_JSON).content(requestJson);

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
    public void selectDeposit() {
        BusinessDepositDO bddo = new BusinessDepositDO();
        bddo.setBusinessId(1L);
        bddo.setUserNum("B10001");
        bddo.setBusinessAccount("17512036361");
        bddo.setBusinessName("张三");
        bddo.setDepositNumber(IdWorkerHelperImpl.generate(BizIdType.DEPOSIT));
        bddo.setAmount(new BigDecimal("1000"));
        bddo.setStatus(BusinessDepositStatusEnum.PAYING.getVal());
        bddo.setProvinceId(Long.valueOf("1"));
        bddo.setCityId(Long.valueOf("101"));
        bddo.setAreaId(Long.valueOf("10101"));
        bddo.setGmtCreate(new Date());
        businessDepositDOMapper.insertSelective(bddo);

        RequestBuilder request = get("/businessDeposit/selectDeposit/"+bddo.getId());
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
    public void refundDeposit() {
        BusinessDepositDO bddo = new BusinessDepositDO();
        bddo.setBusinessId(1L);
        bddo.setUserNum("B10001");
        bddo.setBusinessAccount("17512036361");
        bddo.setBusinessName("张三");
        bddo.setDepositNumber(IdWorkerHelperImpl.generate(BizIdType.DEPOSIT));
        bddo.setAmount(new BigDecimal("1000"));
        bddo.setStatus(BusinessDepositStatusEnum.PAYING.getVal());
        bddo.setProvinceId(Long.valueOf("1"));
        bddo.setCityId(Long.valueOf("101"));
        bddo.setAreaId(Long.valueOf("10101"));
        bddo.setGmtCreate(new Date());
        businessDepositDOMapper.insertSelective(bddo);

        BusinessRefundDepositDataParam param = new BusinessRefundDepositDataParam();
        param.setUserNum("M10001");
        param.setPayPwd("123456");
        param.setMsg("");
        param.setBusinessBankAccountId("1");
        param.setId(bddo.getId().toString());
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/businessDeposit/refundDeposit").contentType(MediaType.APPLICATION_JSON).content(requestJson);

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
    public void getDepositValue() {
        RequestBuilder request = get("/businessDeposit/getDepositValue");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
}
