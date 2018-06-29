package com.lawu.eshop.user.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.user.constants.MerchantAuditStatusEnum;
import com.lawu.eshop.user.dto.CertifTypeEnum;
import com.lawu.eshop.user.dto.MerchantStoreTypeEnum;
import com.lawu.eshop.user.dto.param.MerchantAuditTypeEnum;
import com.lawu.eshop.user.param.ListStoreAuditParam;
import com.lawu.eshop.user.param.MerchantAuditParam;
import com.lawu.eshop.user.param.MerchantStoreParam;
import com.lawu.eshop.user.srv.UserSrvApplicationTest;
import com.lawu.eshop.user.srv.domain.MerchantDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreAuditDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreDO;
import com.lawu.eshop.user.srv.mapper.MerchantDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreAuditDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreDOMapper;
import com.lawu.framework.web.HttpCode;
import com.lawu.utils.DataTransUtil;

/**
 * @author meishuquan
 * @date 2017/7/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserSrvApplicationTest.class)
@WebAppConfiguration
public class MerchantAuditControllerTest {

    private MockMvc mvc;

    @Autowired
    private MerchantAuditController merchantAuditController;

    @Autowired
    private MerchantDOMapper merchantDOMapper;

    @Autowired
    private MerchantStoreDOMapper merchantStoreDOMapper;

    @Autowired
    private MerchantStoreAuditDOMapper merchantStoreAuditDOMapper;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(merchantAuditController).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateMerchantAudit() {
        //初始化商家
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        //初始化门店
        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(merchantDO.getId());
        storeDO.setName("测试店铺");
        storeDO.setRegionPath("44/4403/440303");
        storeDO.setRegionName("广东省深圳市南山区");
        storeDO.setAddress("大冲商务中心");
        storeDO.setLongitude(new BigDecimal(114.23));
        storeDO.setLatitude(new BigDecimal(22.36));
        storeDO.setIndustryPath("10");
        storeDO.setIndustryName("食品");
        storeDO.setIntro("测试店铺介绍");
        storeDO.setStatus(DataTransUtil.intToByte(0));
        storeDO.setIsNoReasonReturn(true);
        storeDO.setGmtCreate(new Date());
        merchantStoreDOMapper.insertSelective(storeDO);

        MerchantStoreParam merchantStoreParam = new MerchantStoreParam();
        merchantStoreParam.setStoreUrl("pic");
        merchantStoreParam.setEnvironmentUrl("pic");
        merchantStoreParam.setLicenseUrl("pic");
        merchantStoreParam.setOtherUrl("pic");
        merchantStoreParam.setLogoUrl("pic");
        merchantStoreParam.setIdcardUrl("pic");
        merchantStoreParam.setManageType(MerchantStoreTypeEnum.NORMAL_MERCHANT);
        merchantStoreParam.setCertifType(CertifTypeEnum.CERTIF_TYPE_IDCARD);

        //初始化门店审核
        MerchantStoreAuditDO storeAuditDO = new MerchantStoreAuditDO();
        storeAuditDO.setMerchantId(merchantDO.getId());
        storeAuditDO.setMerchantStoreId(storeDO.getId());
        storeAuditDO.setContent(net.sf.json.JSONObject.fromObject(merchantStoreParam).toString());
        storeAuditDO.setStatus(DataTransUtil.intToByte(0));
        storeAuditDO.setType(DataTransUtil.intToByte(2));
        storeAuditDO.setGmtModified(new Date());
        storeAuditDO.setGmtCreate(new Date());
        merchantStoreAuditDOMapper.insertSelective(storeAuditDO);

        MerchantAuditParam param = new MerchantAuditParam();
        param.setAuditStatusEnum(MerchantAuditStatusEnum.MERCHANT_AUDIT_STATUS_CHECKED);
        param.setTypeEnum(MerchantAuditTypeEnum.AUDIT_TYPE_EDIT_INFO);
        param.setMerchantStoreId(storeDO.getId());
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = put("/audit/updateMerchantAudit/" + storeAuditDO.getId()).contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void getMerchantAuditInfo() {
        //初始化商家
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        //初始化门店
        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(merchantDO.getId());
        storeDO.setName("测试店铺");
        storeDO.setRegionPath("44/4403/440303");
        storeDO.setRegionName("广东省深圳市南山区");
        storeDO.setAddress("大冲商务中心");
        storeDO.setLongitude(new BigDecimal(114.23));
        storeDO.setLatitude(new BigDecimal(22.36));
        storeDO.setIndustryPath("10");
        storeDO.setIndustryName("食品");
        storeDO.setIntro("测试店铺介绍");
        storeDO.setStatus(DataTransUtil.intToByte(0));
        storeDO.setIsNoReasonReturn(true);
        storeDO.setGmtCreate(new Date());
        merchantStoreDOMapper.insertSelective(storeDO);

        MerchantStoreParam merchantStoreParam = new MerchantStoreParam();
        merchantStoreParam.setStoreUrl("pic");
        merchantStoreParam.setEnvironmentUrl("pic");
        merchantStoreParam.setLicenseUrl("pic");
        merchantStoreParam.setOtherUrl("pic");
        merchantStoreParam.setLogoUrl("pic");
        merchantStoreParam.setIdcardUrl("pic");
        merchantStoreParam.setManageType(MerchantStoreTypeEnum.NORMAL_MERCHANT);
        merchantStoreParam.setCertifType(CertifTypeEnum.CERTIF_TYPE_IDCARD);

        //初始化门店审核
        MerchantStoreAuditDO storeAuditDO = new MerchantStoreAuditDO();
        storeAuditDO.setMerchantId(merchantDO.getId());
        storeAuditDO.setMerchantStoreId(storeDO.getId());
        storeAuditDO.setContent(net.sf.json.JSONObject.fromObject(merchantStoreParam).toString());
        storeAuditDO.setStatus(DataTransUtil.intToByte(0));
        storeAuditDO.setType(DataTransUtil.intToByte(2));
        storeAuditDO.setGmtModified(new Date());
        storeAuditDO.setGmtCreate(new Date());
        merchantStoreAuditDOMapper.insertSelective(storeAuditDO);

        RequestBuilder request = get("/audit/getMerchantAuditInfo/" + merchantDO.getId());
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
    public void listAllStoreAudit() {
        ListStoreAuditParam param = new ListStoreAuditParam();
        param.setStatusEnum(MerchantAuditStatusEnum.MERCHANT_AUDIT_STATUS_CHECKED);
        param.setTypeEnum(MerchantAuditTypeEnum.AUDIT_TYPE_EDIT_INFO);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/audit/listAllStoreAudit").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void getMerchantStoreAudit() {
        RequestBuilder request = get("/audit/getMerchantStoreAudit/100");
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
    public void getRecentMerchantAuditRecord() {
        RequestBuilder request = get("/audit/getRecentMerchantAuditRecord").param("merchantId", "200");
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
    public void getRecentMerchantAuditSuccessRecord() {
        RequestBuilder request = get("/audit/getRecentMerchantAuditSuccessRecord/200");
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
