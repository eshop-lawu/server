package com.lawu.eshop.user.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.lawu.eshop.user.dto.CertifTypeEnum;
import com.lawu.eshop.user.dto.MerchantStatusEnum;
import com.lawu.eshop.user.dto.MerchantStoreImageEnum;
import com.lawu.eshop.user.dto.MerchantStoreTypeEnum;
import com.lawu.eshop.user.param.ApplyStoreParam;
import com.lawu.eshop.user.param.ListMerchantStoreParam;
import com.lawu.eshop.user.param.MerchantStoreParam;
import com.lawu.eshop.user.param.ShoppingOrderFindUserInfoParam;
import com.lawu.eshop.user.param.StoreIndexParam;
import com.lawu.eshop.user.param.StoreStatisticsParam;
import com.lawu.eshop.user.srv.UserSrvApplicationTest;
import com.lawu.eshop.user.srv.domain.MerchantDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreAuditDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreImageDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreProfileDO;
import com.lawu.eshop.user.srv.mapper.MerchantDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreAuditDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreImageDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreProfileDOMapper;
import com.lawu.framework.web.HttpCode;
import com.lawu.utils.DataTransUtil;

/**
 * @author meishuquan
 * @date 2017/7/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserSrvApplicationTest.class)
@WebAppConfiguration
public class MerchantStoreControllerTest {

    private MockMvc mvc;

    @Autowired
    private MerchantStoreController merchantStoreController;

    @Autowired
    private MerchantStoreDOMapper merchantStoreDOMapper;

    @Autowired
    private MerchantStoreImageDOMapper merchantStoreImageDOMapper;

    @Autowired
    private MerchantStoreProfileDOMapper merchantStoreProfileDOMapper;

    @Autowired
    private MerchantStoreAuditDOMapper merchantStoreAuditDOMapper;

    @Autowired
    private MerchantDOMapper merchantDOMapper;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(merchantStoreController).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectMerchantStoreByMId() {
        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(200L);
        storeDO.setName("测试店铺");
        storeDO.setRegionPath("44/4403/440303");
        storeDO.setRegionName("广东省深圳市南山区");
        storeDO.setAddress("大冲商务中心");
        storeDO.setLongitude(new BigDecimal(104.23));
        storeDO.setLatitude(new BigDecimal(22.36));
        storeDO.setIntro("店铺介绍");
        storeDO.setStatus(DataTransUtil.intToByte(1));
        storeDO.setIsNoReasonReturn(true);
        merchantStoreDOMapper.insertSelective(storeDO);

        MerchantStoreImageDO storeImageDO = new MerchantStoreImageDO();
        storeImageDO.setMerchantId(200L);
        storeImageDO.setMerchantStoreId(storeDO.getId());
        storeImageDO.setStatus(true);
        storeImageDO.setType(MerchantStoreImageEnum.STORE_IMAGE_LOGO.val);
        storeImageDO.setPath("pic");
        storeImageDO.setGmtModified(new Date());
        storeImageDO.setGmtCreate(new Date());
        merchantStoreImageDOMapper.insertSelective(storeImageDO);

        RequestBuilder request = get("/merchantStore/selectMerchantStoreByMId").param("merchantId", "200");
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
    public void selectMerchantStore() {
        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(200L);
        storeDO.setName("测试店铺");
        storeDO.setRegionPath("44/4403/440303");
        storeDO.setRegionName("广东省深圳市南山区");
        storeDO.setAddress("大冲商务中心");
        storeDO.setLongitude(new BigDecimal(104.23));
        storeDO.setLatitude(new BigDecimal(22.36));
        storeDO.setIntro("店铺介绍");
        storeDO.setStatus(DataTransUtil.intToByte(1));
        storeDO.setIsNoReasonReturn(true);
        merchantStoreDOMapper.insertSelective(storeDO);

        MerchantStoreProfileDO profileDO = new MerchantStoreProfileDO();
        profileDO.setId(storeDO.getId());
        profileDO.setMerchantId(200L);
        profileDO.setManageType(DataTransUtil.intToByte(2));
        profileDO.setCertifType(DataTransUtil.intToByte(2));
        merchantStoreProfileDOMapper.insertSelective(profileDO);

        MerchantStoreImageDO storeImageDO = new MerchantStoreImageDO();
        storeImageDO.setMerchantId(200L);
        storeImageDO.setMerchantStoreId(storeDO.getId());
        storeImageDO.setPath("pic");
        storeImageDO.setStatus(true);
        for (int i = 1; i <= 6; i++) {
            storeImageDO.setType(DataTransUtil.intToByte(i));
            merchantStoreImageDOMapper.insertSelective(storeImageDO);
        }

        MerchantStoreParam merchantStoreParam = new MerchantStoreParam();
        merchantStoreParam.setStoreUrl("pic");
        merchantStoreParam.setEnvironmentUrl("pic");
        merchantStoreParam.setLicenseUrl("pic");
        merchantStoreParam.setOtherUrl("pic");
        merchantStoreParam.setLogoUrl("pic");
        merchantStoreParam.setIdcardUrl("pic");
        merchantStoreParam.setManageType(MerchantStoreTypeEnum.ENTITY_MERCHANT);
        merchantStoreParam.setCertifType(CertifTypeEnum.CERTIF_TYPE_LICENSE);

        //初始化门店审核
        MerchantStoreAuditDO storeAuditDO = new MerchantStoreAuditDO();
        storeAuditDO.setMerchantId(200L);
        storeAuditDO.setMerchantStoreId(storeDO.getId());
        storeAuditDO.setContent(net.sf.json.JSONObject.fromObject(merchantStoreParam).toString());
        storeAuditDO.setStatus(DataTransUtil.intToByte(1));
        storeAuditDO.setType(DataTransUtil.intToByte(1));
        storeAuditDO.setGmtModified(new Date());
        storeAuditDO.setGmtCreate(new Date());
        merchantStoreAuditDOMapper.insertSelective(storeAuditDO);

        RequestBuilder request = get("/merchantStore/findMerchantStoreInfo/" + storeDO.getId());
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
    public void saveMerchantStoreInfo() {
        MerchantStoreParam param = new MerchantStoreParam();
        param.setName("测试店铺");
        param.setRegionPath("44/4403/440303");
        param.setRegionName("广东省深圳市南山区");
        param.setAddress("大冲商务中心");
        param.setLongitude(new BigDecimal(104.23));
        param.setLatitude(new BigDecimal(22.36));
        param.setIndustryPath("10");
        param.setIndustryName("食品");
        param.setIntro("店铺介绍");
        param.setPrincipalName("测试人");
        param.setPrincipalMobile("13888888888");
        param.setCompanyName("测试公司");
        param.setRegNumber("test");
        param.setOperatorCardId("8888");
        param.setCompanyAddress("大冲商务中心");
        param.setLicenseIndate(new Date());
        param.setManageType(MerchantStoreTypeEnum.ENTITY_MERCHANT);
        param.setCertifType(CertifTypeEnum.CERTIF_TYPE_LICENSE);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/merchantStore/saveMerchantStoreInfo/200").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void updateMerchantStoreInfo() {
        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(200L);
        storeDO.setName("测试店铺");
        storeDO.setRegionPath("44/4403/440303");
        storeDO.setRegionName("广东省深圳市南山区");
        storeDO.setAddress("大冲商务中心");
        storeDO.setLongitude(new BigDecimal(104.23));
        storeDO.setLatitude(new BigDecimal(22.36));
        storeDO.setIntro("店铺介绍");
        storeDO.setStatus(DataTransUtil.intToByte(1));
        storeDO.setIsNoReasonReturn(true);
        merchantStoreDOMapper.insertSelective(storeDO);

        MerchantStoreParam param = new MerchantStoreParam();
        param.setName("测试店铺");
        param.setRegionPath("44/4403/440303");
        param.setRegionName("广东省深圳市南山区");
        param.setAddress("大冲商务中心");
        param.setLongitude(new BigDecimal(104.23));
        param.setLatitude(new BigDecimal(22.36));
        param.setIndustryPath("10");
        param.setIndustryName("食品");
        param.setIntro("店铺介绍");
        param.setPrincipalName("测试人");
        param.setPrincipalMobile("13888888888");
        param.setCompanyName("测试公司");
        param.setRegNumber("test");
        param.setCompanyAddress("大冲商务中心");
        param.setLicenseIndate(new Date());
        param.setManageType(MerchantStoreTypeEnum.ENTITY_MERCHANT);
        param.setCertifType(CertifTypeEnum.CERTIF_TYPE_LICENSE);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = put("/merchantStore/updateMerchantStoreInfo/" + storeDO.getId()).param("merchantId", "200").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void getMerchantInfoForShoppingCart() {
        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(200L);
        storeDO.setName("测试店铺");
        storeDO.setRegionPath("44/4403/440303");
        storeDO.setRegionName("广东省深圳市南山区");
        storeDO.setAddress("大冲商务中心");
        storeDO.setLongitude(new BigDecimal(104.23));
        storeDO.setLatitude(new BigDecimal(22.36));
        storeDO.setIntro("店铺介绍");
        storeDO.setStatus(DataTransUtil.intToByte(1));
        storeDO.setIsNoReasonReturn(true);
        merchantStoreDOMapper.insertSelective(storeDO);

        RequestBuilder request = get("/merchantStore/shoppingCart/200");
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
    public void saveMerchantStoreAuditInfo() {
        MerchantStoreParam param = new MerchantStoreParam();
        param.setName("测试店铺");
        param.setRegionPath("44/4403/440303");
        param.setRegionName("广东省深圳市南山区");
        param.setAddress("大冲商务中心");
        param.setLongitude(new BigDecimal(104.23));
        param.setLatitude(new BigDecimal(22.36));
        param.setIndustryPath("10");
        param.setIndustryName("食品");
        param.setIntro("店铺介绍");
        param.setPrincipalName("测试人");
        param.setPrincipalMobile("13888888888");
        param.setCompanyName("测试公司");
        param.setRegNumber("test");
        param.setCompanyAddress("大冲商务中心");
        param.setLicenseIndate(new Date());
        param.setManageType(MerchantStoreTypeEnum.ENTITY_MERCHANT);
        param.setCertifType(CertifTypeEnum.CERTIF_TYPE_LICENSE);
        param.setMerchantStoreStatus(MerchantStatusEnum.MERCHANT_STATUS_CANCEL);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/merchantStore/saveMerchantStoreAuditInfo/300").param("merchantId", "200").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void findIsNoReasonReturnById() {
        RequestBuilder request = get("/merchantStore/findIsNoReasonReturnById").param("merchantId", "200");
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
    public void shoppingOrderFindUserInfo() {
        ShoppingOrderFindUserInfoParam param = new ShoppingOrderFindUserInfoParam();
        List<Long> merchantIdList = new ArrayList<>();
        merchantIdList.add(200L);
        param.setMerchantIdList(merchantIdList);
        param.setMemberId(100L);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = put("/merchantStore/shoppingOrderFindUserInfo").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void storeDetail() {
        RequestBuilder request = get("/merchantStore/storeDetail/300").param("memberId", "100");
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
    public void findCashUserInfo() {
        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(200L);
        storeDO.setName("测试店铺");
        storeDO.setRegionPath("44/4403/440303");
        storeDO.setRegionName("广东省深圳市南山区");
        storeDO.setAddress("大冲商务中心");
        storeDO.setLongitude(new BigDecimal(104.23));
        storeDO.setLatitude(new BigDecimal(22.36));
        storeDO.setIntro("店铺介绍");
        storeDO.setStatus(DataTransUtil.intToByte(1));
        storeDO.setIsNoReasonReturn(true);
        merchantStoreDOMapper.insertSelective(storeDO);

        RequestBuilder request = get("/merchantStore/findCashUserInfo/" + storeDO.getMerchantId());
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
    public void findStoreNameAndImgByMerchantId() {
        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(200L);
        storeDO.setName("测试店铺");
        storeDO.setRegionPath("44/4403/440303");
        storeDO.setRegionName("广东省深圳市南山区");
        storeDO.setAddress("大冲商务中心");
        storeDO.setLongitude(new BigDecimal(104.23));
        storeDO.setLatitude(new BigDecimal(22.36));
        storeDO.setIntro("店铺介绍");
        storeDO.setStatus(DataTransUtil.intToByte(1));
        storeDO.setIsNoReasonReturn(true);
        merchantStoreDOMapper.insertSelective(storeDO);

        MerchantStoreImageDO storeImageDO = new MerchantStoreImageDO();
        storeImageDO.setMerchantId(200L);
        storeImageDO.setMerchantStoreId(storeDO.getId());
        storeImageDO.setPath("pic");
        storeImageDO.setStatus(true);
        for (int i = 1; i <= 6; i++) {
            storeImageDO.setType(DataTransUtil.intToByte(i));
            merchantStoreImageDOMapper.insertSelective(storeImageDO);
        }

        RequestBuilder request = get("/merchantStore/findStoreNameAndImgByMerchantId/200");
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
    public void applyPhysicalStore() {
        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(200L);
        storeDO.setName("测试店铺");
        storeDO.setRegionPath("44/4403/440303");
        storeDO.setRegionName("广东省深圳市南山区");
        storeDO.setAddress("大冲商务中心");
        storeDO.setLongitude(new BigDecimal(104.23));
        storeDO.setLatitude(new BigDecimal(22.36));
        storeDO.setIntro("店铺介绍");
        storeDO.setStatus(DataTransUtil.intToByte(1));
        storeDO.setIsNoReasonReturn(true);
        merchantStoreDOMapper.insertSelective(storeDO);

        ApplyStoreParam param = new ApplyStoreParam();
        param.setLogoUrl("pic");
        param.setEnvironmentUrl("pic");
        param.setStoreUrl("pic");
        param.setManageType(MerchantStoreTypeEnum.ENTITY_MERCHANT);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = put("/merchantStore/applyPhysicalStore/200").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void updateNoReasonReturn() {
        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(200L);
        storeDO.setName("测试店铺");
        storeDO.setRegionPath("44/4403/440303");
        storeDO.setRegionName("广东省深圳市南山区");
        storeDO.setAddress("大冲商务中心");
        storeDO.setLongitude(new BigDecimal(104.23));
        storeDO.setLatitude(new BigDecimal(22.36));
        storeDO.setIntro("店铺介绍");
        storeDO.setStatus(DataTransUtil.intToByte(1));
        storeDO.setIsNoReasonReturn(true);
        merchantStoreDOMapper.insertSelective(storeDO);

        RequestBuilder request = put("/merchantStore/updateNoReasonReturn/200");
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
    public void shoppingStoreDetail() {
        RequestBuilder request = get("/merchantStore/shoppingStoreDetail/300").param("memberId", "100");
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
    public void getMerchantStore() {
        RequestBuilder request = get("/merchantStore/getMerchantStore/300");
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
    public void selectAllMerchantStore() {
        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(200L);
        storeDO.setName("测试店铺");
        storeDO.setRegionPath("44/4403/440303");
        storeDO.setRegionName("广东省深圳市南山区");
        storeDO.setAddress("大冲商务中心");
        storeDO.setLongitude(new BigDecimal(104.23));
        storeDO.setLatitude(new BigDecimal(22.36));
        storeDO.setIntro("店铺介绍");
        storeDO.setStatus(DataTransUtil.intToByte(1));
        storeDO.setIsNoReasonReturn(true);
        merchantStoreDOMapper.insertSelective(storeDO);

        MerchantStoreParam param = new MerchantStoreParam();
        param.setRegionPath("44/4403/440303");
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/merchantStore/selectAllMerchantStore").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void getMemberProductDetailStore() {
        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(200L);
        storeDO.setName("测试店铺");
        storeDO.setRegionPath("44/4403/440303");
        storeDO.setRegionName("广东省深圳市南山区");
        storeDO.setAddress("大冲商务中心");
        storeDO.setLongitude(new BigDecimal(104.23));
        storeDO.setLatitude(new BigDecimal(22.36));
        storeDO.setIntro("店铺介绍");
        storeDO.setStatus(DataTransUtil.intToByte(1));
        storeDO.setIsNoReasonReturn(true);
        merchantStoreDOMapper.insertSelective(storeDO);

        RequestBuilder request = get("/merchantStore/getMemberProductDetailStore").param("merchantId", "200");
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
    public void getManageType() {
        RequestBuilder request = get("/merchantStore/getManageType").param("merchantId", "200");
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
    public void listMerchantStore() {
        ListMerchantStoreParam param = new ListMerchantStoreParam();
        param.setStatus(DataTransUtil.intToByte(1));
        param.setManageType(DataTransUtil.intToByte(1));
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/merchantStore/listMerchantStore").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void updateStoreStatistics() {
        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(200L);
        storeDO.setName("测试店铺");
        storeDO.setRegionPath("44/4403/440303");
        storeDO.setRegionName("广东省深圳市南山区");
        storeDO.setAddress("大冲商务中心");
        storeDO.setLongitude(new BigDecimal(104.23));
        storeDO.setLatitude(new BigDecimal(22.36));
        storeDO.setIntro("店铺介绍");
        storeDO.setStatus(DataTransUtil.intToByte(1));
        storeDO.setIsNoReasonReturn(true);
        merchantStoreDOMapper.insertSelective(storeDO);

        StoreStatisticsParam param = new StoreStatisticsParam();
        param.setAverageScore(new BigDecimal(20));
        param.setAverageConsumeAmount(new BigDecimal(50));
        param.setFeedbackRate(new BigDecimal(30));
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = put("/merchantStore/updateStoreStatistics/" + storeDO.getId()).contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void rebuildStoreIndex() {
        List<StoreIndexParam> indexParamList = new ArrayList<>();
        StoreIndexParam indexParam = new StoreIndexParam();
        indexParam.setMerchantStoreId(300L);
        indexParam.setFavoreInfo("满100减10");
        indexParam.setDiscountPackage("两人豪华午餐");
        indexParam.setDiscountOrdinal(0.9);
        indexParamList.add(indexParam);
        String requestJson = JSONObject.toJSONString(indexParamList);
        RequestBuilder request = post("/merchantStore/rebuildStoreIndex").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void delInvalidStoreIndex() {
        RequestBuilder request = get("/merchantStore/delInvalidStoreIndex");
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
    public void getPayOrderStoreInfo() {
        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(200L);
        storeDO.setName("测试店铺");
        storeDO.setRegionPath("44/4403/440303");
        storeDO.setRegionName("广东省深圳市南山区");
        storeDO.setAddress("大冲商务中心");
        storeDO.setLongitude(new BigDecimal(104.23));
        storeDO.setLatitude(new BigDecimal(22.36));
        storeDO.setIntro("店铺介绍");
        storeDO.setStatus(DataTransUtil.intToByte(1));
        storeDO.setIsNoReasonReturn(true);
        merchantStoreDOMapper.insertSelective(storeDO);

        MerchantStoreImageDO storeImageDO = new MerchantStoreImageDO();
        storeImageDO.setMerchantId(200L);
        storeImageDO.setMerchantStoreId(storeDO.getId());
        storeImageDO.setPath("pic");
        storeImageDO.setStatus(true);
        for (int i = 1; i <= 6; i++) {
            storeImageDO.setType(DataTransUtil.intToByte(i));
            merchantStoreImageDOMapper.insertSelective(storeImageDO);
        }

        RequestBuilder request = get("/merchantStore/getPayOrderStoreInfo").param("merchantIds", storeDO.getMerchantId().toString());
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
    public void getMerchantStoreByIds() {
        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(200L);
        storeDO.setName("测试店铺");
        storeDO.setRegionPath("44/4403/440303");
        storeDO.setRegionName("广东省深圳市南山区");
        storeDO.setAddress("大冲商务中心");
        storeDO.setLongitude(new BigDecimal(104.23));
        storeDO.setLatitude(new BigDecimal(22.36));
        storeDO.setIntro("店铺介绍");
        storeDO.setStatus(DataTransUtil.intToByte(1));
        storeDO.setIsNoReasonReturn(true);
        merchantStoreDOMapper.insertSelective(storeDO);

        RequestBuilder request = get("/merchantStore/getMerchantStoreByIds").param("merchantStoreIds", storeDO.getId().toString());
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
    public void getAdMerchantStoreByIds() {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(merchantDO.getId());
        storeDO.setName("测试店铺");
        storeDO.setRegionPath("44/4403/440303");
        storeDO.setRegionName("广东省深圳市南山区");
        storeDO.setAddress("大冲商务中心");
        storeDO.setLongitude(new BigDecimal(104.23));
        storeDO.setLatitude(new BigDecimal(22.36));
        storeDO.setIntro("店铺介绍");
        storeDO.setStatus(DataTransUtil.intToByte(1));
        storeDO.setIsNoReasonReturn(true);
        merchantStoreDOMapper.insertSelective(storeDO);

        MerchantStoreProfileDO profileDO = new MerchantStoreProfileDO();
        profileDO.setId(storeDO.getId());
        profileDO.setMerchantId(merchantDO.getId());
        profileDO.setManageType(DataTransUtil.intToByte(2));
        profileDO.setCertifType(DataTransUtil.intToByte(2));
        merchantStoreProfileDOMapper.insertSelective(profileDO);

        MerchantStoreImageDO imageDO = new MerchantStoreImageDO();
        imageDO.setMerchantId(merchantDO.getId());
        imageDO.setMerchantStoreId(storeDO.getId());
        imageDO.setStatus(true);
        imageDO.setType(DataTransUtil.intToByte(3));
        imageDO.setPath("pic");
        imageDO.setGmtModified(new Date());
        imageDO.setGmtCreate(new Date());
        merchantStoreImageDOMapper.insertSelective(imageDO);

        RequestBuilder request = get("/merchantStore/getAdMerchantStoreByIds").param("merchantIds", merchantDO.getId().toString());
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
    public void getMerchantStoreById() {
        RequestBuilder request = get("/merchantStore/merchantStoreId/200");
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
    public void merchantStoreIsExist() {
        RequestBuilder request = get("/merchantStore/merchantStoreIsExist/300");
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
    public void getLogoUrlByStoreId() {
        RequestBuilder request = get("/merchantStore/getLogoUrlByStoreId/300");
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
    public void getStoreUrlByStoreId() {
        RequestBuilder request = get("/merchantStore/getStoreUrlByStoreId/300");
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
    public void getPayOrderDetailStoreInfo() {
        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(200L);
        storeDO.setName("测试店铺");
        storeDO.setRegionPath("44/4403/440303");
        storeDO.setRegionName("广东省深圳市南山区");
        storeDO.setAddress("大冲商务中心");
        storeDO.setLongitude(new BigDecimal(104.23));
        storeDO.setLatitude(new BigDecimal(22.36));
        storeDO.setIntro("店铺介绍");
        storeDO.setStatus(DataTransUtil.intToByte(1));
        storeDO.setIsNoReasonReturn(true);
        merchantStoreDOMapper.insertSelective(storeDO);

        MerchantStoreImageDO storeImageDO = new MerchantStoreImageDO();
        storeImageDO.setMerchantId(200L);
        storeImageDO.setMerchantStoreId(storeDO.getId());
        storeImageDO.setPath("pic");
        storeImageDO.setStatus(true);
        for (int i = 1; i <= 6; i++) {
            storeImageDO.setType(DataTransUtil.intToByte(i));
            merchantStoreImageDOMapper.insertSelective(storeImageDO);
        }

        RequestBuilder request = get("/merchantStore/getPayOrderDetailStoreInfo").param("merchantId", "200");
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
    public void getPayOrderMerchantInfo() {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(merchantDO.getId());
        storeDO.setName("测试店铺");
        storeDO.setRegionPath("44/4403/440303");
        storeDO.setRegionName("广东省深圳市南山区");
        storeDO.setAddress("大冲商务中心");
        storeDO.setLongitude(new BigDecimal(104.23));
        storeDO.setLatitude(new BigDecimal(22.36));
        storeDO.setIntro("店铺介绍");
        storeDO.setStatus(DataTransUtil.intToByte(1));
        storeDO.setIsNoReasonReturn(true);
        merchantStoreDOMapper.insertSelective(storeDO);

        RequestBuilder request = get("/merchantStore/getPayOrderMerchantInfo").param("merchantId", merchantDO.getId().toString());
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
    public void findAccountAndRegionPathByNum() {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum("B0001");
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(merchantDO.getId());
        storeDO.setName("测试店铺");
        storeDO.setRegionPath("44/4403/440303");
        storeDO.setRegionName("广东省深圳市南山区");
        storeDO.setAddress("大冲商务中心");
        storeDO.setLongitude(new BigDecimal(104.23));
        storeDO.setLatitude(new BigDecimal(22.36));
        storeDO.setIntro("店铺介绍");
        storeDO.setStatus(DataTransUtil.intToByte(1));
        storeDO.setIsNoReasonReturn(true);
        merchantStoreDOMapper.insertSelective(storeDO);

        RequestBuilder request = get("/merchantStore/findAccountAndRegionPathByNum").param("merchantNum", merchantDO.getNum());
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
    public void getNameBymerchantId() {
        RequestBuilder request = get("/merchantStore/getNameBymerchantId/200");
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
    public void listNewMerchant() {
        RequestBuilder request = get("/merchantStore/listNewMerchant").param("regionPath", "44/4403");
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
    public void listRecommendFoodConsume() {
        RequestBuilder request = get("/merchantStore/listRecommendFoodConsume/10").param("regionPath", "44/4403");
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
    public void listRecommendFoodComment() {
        RequestBuilder request = get("/merchantStore/listRecommendFoodComment/10").param("regionPath", "44/4403");
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
    public void selectMerchantStoreAdInfo() {
        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(200L);
        storeDO.setName("测试店铺");
        storeDO.setRegionPath("44/4403/440303");
        storeDO.setRegionName("广东省深圳市南山区");
        storeDO.setAddress("大冲商务中心");
        storeDO.setLongitude(new BigDecimal(104.23));
        storeDO.setLatitude(new BigDecimal(22.36));
        storeDO.setIntro("店铺介绍");
        storeDO.setStatus(DataTransUtil.intToByte(1));
        storeDO.setIsNoReasonReturn(true);
        merchantStoreDOMapper.insertSelective(storeDO);

        MerchantStoreImageDO storeImageDO = new MerchantStoreImageDO();
        storeImageDO.setMerchantId(200L);
        storeImageDO.setMerchantStoreId(storeDO.getId());
        storeImageDO.setStatus(true);
        storeImageDO.setType(MerchantStoreImageEnum.STORE_IMAGE_LOGO.val);
        storeImageDO.setPath("pic");
        storeImageDO.setGmtModified(new Date());
        storeImageDO.setGmtCreate(new Date());
        merchantStoreImageDOMapper.insertSelective(storeImageDO);

        RequestBuilder request = get("/merchantStore/selectMerchantStoreAdInfo/"+storeDO.getMerchantId());
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
    public void delSolrDocsById(){
        RequestBuilder request = delete("/merchantStore/delSolrDocsById").param("merchantStoreId", "1");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_NO_CONTENT)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_NO_CONTENT, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void delAllStoreIndex(){
        RequestBuilder request = get("/merchantStore/delAllStoreIndex");
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
