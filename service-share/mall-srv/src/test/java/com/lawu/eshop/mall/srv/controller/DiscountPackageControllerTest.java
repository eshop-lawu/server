package com.lawu.eshop.mall.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.mall.constants.DiscountPackageStatusQueryEnum;
import com.lawu.eshop.mall.param.DiscountPackageImageSaveParam;
import com.lawu.eshop.mall.param.DiscountPackageImageUpdateParam;
import com.lawu.eshop.mall.param.DiscountPackageSaveParam;
import com.lawu.eshop.mall.param.DiscountPackageUpdateParam;
import com.lawu.eshop.mall.param.foreign.DiscountPackageContentSaveForeignParam;
import com.lawu.eshop.mall.param.foreign.DiscountPackageContentUpdateForeignParam;
import com.lawu.eshop.mall.param.foreign.DiscountPackageQueryForeignParam;
import com.lawu.eshop.mall.srv.MallSrvApplicationTest;
import com.lawu.eshop.mall.srv.domain.DiscountPackageContentDO;
import com.lawu.eshop.mall.srv.domain.DiscountPackageDO;
import com.lawu.eshop.mall.srv.domain.DiscountPackageImageDO;
import com.lawu.eshop.mall.srv.domain.DiscountPackagePurchaseNotesDO;
import com.lawu.eshop.mall.srv.mapper.DiscountPackageContentDOMapper;
import com.lawu.eshop.mall.srv.mapper.DiscountPackageDOMapper;
import com.lawu.eshop.mall.srv.mapper.DiscountPackageImageDOMapper;
import com.lawu.eshop.mall.srv.mapper.DiscountPackagePurchaseNotesDOMapper;
import com.lawu.framework.web.HttpCode;

/**
 * @author zhangyong
 * @date 2017/7/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MallSrvApplicationTest.class)
@WebAppConfiguration
public class DiscountPackageControllerTest {
    private MockMvc mvc;

    @Autowired
    private DiscountPackageController discountPackageController;

    @Autowired
    private DiscountPackageDOMapper discountPackageDOMapper;

    @Autowired
    private DiscountPackageContentDOMapper discountPackageContentDOMapper;

    @Autowired
    private DiscountPackageImageDOMapper discountPackageImageDOMapper;
    
    @Autowired
    private DiscountPackagePurchaseNotesDOMapper discountPackagePurchaseNotesDOMapper;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(discountPackageController).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listForMember() {
        RequestBuilder request = MockMvcRequestBuilders.get("/discountPackage/member/list/1");

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
    public void listForMerchant() {

        DiscountPackageQueryForeignParam param = new DiscountPackageQueryForeignParam();
        param.setStatus(DiscountPackageStatusQueryEnum.UP);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/discountPackage/merchant/list/1").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void get() {
    	DiscountPackageDO discountPackageDO = new DiscountPackageDO();
        discountPackageDO.setMerchantId(1L);
        discountPackageDO.setGmtCreate(new Date());
        discountPackageDO.setName("test");
        discountPackageDO.setIsReservation(true);
        discountPackageDO.setStatus((byte) 1);
        discountPackageDO.setOriginalPrice(BigDecimal.TEN);
        discountPackageDO.setGmtUp(new Date());
        discountPackageDO.setMerchantStoreId(1L);
        discountPackageDO.setUseTimeBegin(new Date());
        discountPackageDO.setPrice(BigDecimal.TEN);
        discountPackageDO.setValidityPeriodEnd(new Date());
        discountPackageDO.setValidityPeriodBegin(new Date());
        discountPackageDO.setUseTimeBegin(new Date());
        discountPackageDO.setUseTimeEnd(new Date());
        discountPackageDO.setUseTimeWeek("1");
        discountPackageDOMapper.insert(discountPackageDO);
        DiscountPackageContentDO contentDO = new DiscountPackageContentDO();
        contentDO.setDiscountPackageId(discountPackageDO.getId());
        contentDO.setGmtCreate(new Date());
        contentDO.setName("test");
        contentDO.setQuantity(1);
        contentDO.setStatus((byte) 1);
        contentDO.setSubtotal(BigDecimal.TEN);
        contentDO.setUnitPrice(BigDecimal.TEN);
        discountPackageContentDOMapper.insert(contentDO);
        DiscountPackageImageDO imageDO = new DiscountPackageImageDO();
        imageDO.setGmtCreate(new Date());
        imageDO.setImage("default.png");
        imageDO.setDiscountPackageId(discountPackageDO.getId());
        imageDO.setStatus((byte) 1);
        imageDO.setDescription("");
        discountPackageImageDOMapper.insert(imageDO);
    	
        RequestBuilder request = MockMvcRequestBuilders.get("/discountPackage/" + discountPackageDO.getId()).param("merchantId", discountPackageDO.getMerchantId().toString());
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
    public void getByMember() {
    	DiscountPackagePurchaseNotesDO discountPackagePurchaseNotesDO = new DiscountPackagePurchaseNotesDO();
    	discountPackagePurchaseNotesDO.setGmtCreate(new Date());
    	discountPackagePurchaseNotesDO.setNote("免费停车");
    	discountPackagePurchaseNotesDOMapper.insert(discountPackagePurchaseNotesDO);
    	
    	DiscountPackageDO discountPackageDO = new DiscountPackageDO();
        discountPackageDO.setMerchantId(1L);
        discountPackageDO.setGmtCreate(new Date());
        discountPackageDO.setName("test");
        discountPackageDO.setIsReservation(true);
        discountPackageDO.setStatus((byte) 1);
        discountPackageDO.setOriginalPrice(BigDecimal.TEN);
        discountPackageDO.setGmtUp(new Date());
        discountPackageDO.setMerchantStoreId(1L);
        discountPackageDO.setUseTimeBegin(new Date());
        discountPackageDO.setPrice(BigDecimal.TEN);
        discountPackageDO.setValidityPeriodEnd(new Date());
        discountPackageDO.setValidityPeriodBegin(new Date());
        discountPackageDO.setUseTimeBegin(new Date());
        discountPackageDO.setUseTimeEnd(new Date());
        discountPackageDO.setUseTimeWeek("1");
        discountPackageDO.setAdvanceBookingTime("30分钟");
        discountPackageDO.setPurchaseNotes(discountPackagePurchaseNotesDO.getId().toString());
        discountPackageDOMapper.insert(discountPackageDO);
        DiscountPackageContentDO contentDO = new DiscountPackageContentDO();
        contentDO.setDiscountPackageId(discountPackageDO.getId());
        contentDO.setGmtCreate(new Date());
        contentDO.setName("test");
        contentDO.setQuantity(1);
        contentDO.setStatus((byte) 1);
        contentDO.setSubtotal(BigDecimal.TEN);
        contentDO.setUnitPrice(BigDecimal.TEN);
        discountPackageContentDOMapper.insert(contentDO);
        DiscountPackageImageDO imageDO = new DiscountPackageImageDO();
        imageDO.setGmtCreate(new Date());
        imageDO.setImage("default.png");
        imageDO.setDiscountPackageId(discountPackageDO.getId());
        imageDO.setStatus((byte) 1);
        imageDO.setDescription("");
        discountPackageImageDOMapper.insert(imageDO);
    	
        RequestBuilder request = MockMvcRequestBuilders.get("/discountPackage/member/" + discountPackageDO.getId());
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
    public void save() {
        List<DiscountPackageContentSaveForeignParam> discountPackageContents = new ArrayList<>();
        DiscountPackageContentSaveForeignParam contentSaveForeignParam = new DiscountPackageContentSaveForeignParam();
        contentSaveForeignParam.setName("test");
        contentSaveForeignParam.setQuantity(1);
        contentSaveForeignParam.setUnit("unit");
        contentSaveForeignParam.setUnitPrice(BigDecimal.TEN);
        discountPackageContents.add(contentSaveForeignParam);
        List<DiscountPackageImageSaveParam> discountPackageImages = new ArrayList<>();
        DiscountPackageImageSaveParam imageSaveParam = new DiscountPackageImageSaveParam();
        imageSaveParam.setImage("default.png");
        imageSaveParam.setDescription("description");
        discountPackageImages.add(imageSaveParam);

        DiscountPackageSaveParam param = new DiscountPackageSaveParam();
        param.setMerchantStoreId(1L);
        param.setCoverImage("123.png");
        param.setName("test");
        param.setDiscountPackageContents(discountPackageContents);
        param.setDiscountPackageImages(discountPackageImages);
        param.setOtherInstructions("instructions");
        param.setIsReservation(false);
        param.setUseRules("rule");
        param.setPrice(BigDecimal.TEN);
        param.setUseTimeBegin(new Date());
        param.setUseTimeWeek("week");
        param.setAdvanceBookingTime("30分钟");
        param.setPurchaseNotes("1");
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/discountPackage/1").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        ResultActions perform = null;
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void update() {
        DiscountPackageDO discountPackageDO = new DiscountPackageDO();
        discountPackageDO.setMerchantId(1L);
        discountPackageDO.setGmtCreate(new Date());
        discountPackageDO.setName("test");
        discountPackageDO.setIsReservation(true);
        discountPackageDO.setStatus((byte) 1);
        discountPackageDO.setOriginalPrice(BigDecimal.TEN);
        discountPackageDO.setGmtUp(new Date());
        discountPackageDO.setMerchantStoreId(1L);
        discountPackageDO.setUseTimeBegin(new Date());
        discountPackageDO.setPrice(BigDecimal.TEN);
        discountPackageDO.setValidityPeriodEnd(new Date());
        discountPackageDO.setValidityPeriodBegin(new Date());
        discountPackageDO.setUseTimeBegin(new Date());
        discountPackageDO.setUseTimeEnd(new Date());
        discountPackageDO.setUseTimeWeek("1");
        discountPackageDOMapper.insert(discountPackageDO);
        DiscountPackageContentDO contentDO = new DiscountPackageContentDO();
        contentDO.setDiscountPackageId(discountPackageDO.getId());
        contentDO.setGmtCreate(new Date());
        contentDO.setName("test");
        contentDO.setQuantity(1);
        contentDO.setStatus((byte) 1);
        contentDO.setSubtotal(BigDecimal.TEN);
        contentDO.setUnitPrice(BigDecimal.TEN);
        discountPackageContentDOMapper.insert(contentDO);
        DiscountPackageImageDO imageDO = new DiscountPackageImageDO();
        imageDO.setGmtCreate(new Date());
        imageDO.setImage("default.png");
        imageDO.setDiscountPackageId(discountPackageDO.getId());
        imageDO.setStatus((byte) 1);
        imageDO.setDescription("");
        discountPackageImageDOMapper.insert(imageDO);

        List<DiscountPackageContentUpdateForeignParam> updateForeignParams = new ArrayList<>();
        DiscountPackageContentUpdateForeignParam updateForeignParam = new DiscountPackageContentUpdateForeignParam();
        updateForeignParam.setUnitPrice(BigDecimal.ONE);
        updateForeignParam.setQuantity(1);
        updateForeignParam.setUnit("unit");
        updateForeignParams.add(updateForeignParam);
        List<DiscountPackageImageUpdateParam> imageUpdateParams = new ArrayList<>();
        DiscountPackageImageUpdateParam imageUpdateParam = new DiscountPackageImageUpdateParam();
        imageUpdateParam.setDescription("TEST");
        imageUpdateParams.add(imageUpdateParam);

        DiscountPackageUpdateParam param = new DiscountPackageUpdateParam();
        param.setName("test2");
        param.setAdvanceBookingTime("30分钟");
        param.setPurchaseNotes("1");
        param.setDiscountPackageContents(updateForeignParams);
        param.setDiscountPackageImages(imageUpdateParams);
        String requestJson = JSONObject.toJSONString(param);

        RequestBuilder request = put("/discountPackage/1/" + discountPackageDO.getId()).contentType(MediaType.APPLICATION_JSON).content(requestJson);
        ResultActions perform = null;
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void deleteInfo() {
        RequestBuilder request = null;
        ResultActions perform = null;
        request = put("/discountPackage/1/delete/1");
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DiscountPackageDO discountPackageDO = new DiscountPackageDO();
        discountPackageDO.setMerchantId(2L);
        discountPackageDO.setGmtCreate(new Date());
        discountPackageDO.setName("test");
        discountPackageDO.setIsReservation(true);
        discountPackageDO.setStatus((byte) 1);
        discountPackageDO.setOriginalPrice(BigDecimal.TEN);
        discountPackageDO.setGmtUp(new Date());
        discountPackageDO.setMerchantStoreId(1L);
        discountPackageDO.setUseTimeBegin(new Date());
        discountPackageDO.setPrice(BigDecimal.TEN);
        discountPackageDO.setValidityPeriodEnd(new Date());
        discountPackageDO.setValidityPeriodBegin(new Date());
        discountPackageDO.setUseTimeBegin(new Date());
        discountPackageDO.setUseTimeEnd(new Date());
        discountPackageDO.setUseTimeWeek("1");
        discountPackageDOMapper.insert(discountPackageDO);
        request = put("/discountPackage/1/delete/1");
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void up() {
        RequestBuilder request = null;
        ResultActions perform = null;
        request = put("/discountPackage/1/up/1");
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }

        DiscountPackageDO discountPackageDO = new DiscountPackageDO();
        discountPackageDO.setMerchantId(2L);
        discountPackageDO.setGmtCreate(new Date());
        discountPackageDO.setName("test");
        discountPackageDO.setIsReservation(true);
        discountPackageDO.setStatus((byte) 1);
        discountPackageDO.setOriginalPrice(BigDecimal.TEN);
        discountPackageDO.setGmtUp(new Date());
        discountPackageDO.setMerchantStoreId(1L);
        discountPackageDO.setUseTimeBegin(new Date());
        discountPackageDO.setPrice(BigDecimal.TEN);
        discountPackageDO.setValidityPeriodEnd(new Date());
        discountPackageDO.setValidityPeriodBegin(new Date());
        discountPackageDO.setUseTimeBegin(new Date());
        discountPackageDO.setUseTimeEnd(new Date());
        discountPackageDO.setUseTimeWeek("1");
        discountPackageDOMapper.insert(discountPackageDO);
        request = put("/discountPackage/1/up/1");
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void down() {
        RequestBuilder request ;
        ResultActions perform = null;
        request = put("/discountPackage/1/down/1");
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }

        DiscountPackageDO discountPackageDO = new DiscountPackageDO();
        discountPackageDO.setMerchantId(2L);
        discountPackageDO.setGmtCreate(new Date());
        discountPackageDO.setName("test");
        discountPackageDO.setIsReservation(true);
        discountPackageDO.setStatus((byte) 1);
        discountPackageDO.setOriginalPrice(BigDecimal.TEN);
        discountPackageDO.setGmtUp(new Date());
        discountPackageDO.setMerchantStoreId(1L);
        discountPackageDO.setUseTimeBegin(new Date());
        discountPackageDO.setPrice(BigDecimal.TEN);
        discountPackageDO.setValidityPeriodEnd(new Date());
        discountPackageDO.setValidityPeriodBegin(new Date());
        discountPackageDO.setUseTimeBegin(new Date());
        discountPackageDO.setUseTimeEnd(new Date());
        discountPackageDO.setUseTimeWeek("1");
        discountPackageDOMapper.insert(discountPackageDO);
        request = put("/discountPackage/1/down/1");
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void deleteList() {

        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        String requestJson = JSONObject.toJSONString(ids);
        RequestBuilder request = null;

        request = put("/discountPackage/1/delete").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        ResultActions perform = null;
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DiscountPackageDO discountPackageDO = new DiscountPackageDO();
        discountPackageDO.setMerchantId(2L);
        discountPackageDO.setGmtCreate(new Date());
        discountPackageDO.setName("test");
        discountPackageDO.setIsReservation(true);
        discountPackageDO.setStatus((byte) 1);
        discountPackageDO.setOriginalPrice(BigDecimal.TEN);
        discountPackageDO.setGmtUp(new Date());
        discountPackageDO.setMerchantStoreId(1L);
        discountPackageDO.setUseTimeBegin(new Date());
        discountPackageDO.setPrice(BigDecimal.TEN);
        discountPackageDO.setValidityPeriodEnd(new Date());
        discountPackageDO.setValidityPeriodBegin(new Date());
        discountPackageDO.setUseTimeBegin(new Date());
        discountPackageDO.setUseTimeEnd(new Date());
        discountPackageDO.setUseTimeWeek("1");
        discountPackageDOMapper.insert(discountPackageDO);

        List<Long> ids2 = new ArrayList<>();
        ids2.add(discountPackageDO.getId());
        String requestJson2 = JSONObject.toJSONString(ids2);
        request = put("/discountPackage/1/delete").contentType(MediaType.APPLICATION_JSON).content(requestJson2);
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void upList() {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        RequestBuilder request = null;
        String requestJson = JSONObject.toJSONString(ids);
         request = put("/discountPackage/1/up").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        ResultActions perform = null;
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }

        DiscountPackageDO discountPackageDO = new DiscountPackageDO();
        discountPackageDO.setMerchantId(2L);
        discountPackageDO.setGmtCreate(new Date());
        discountPackageDO.setName("test");
        discountPackageDO.setIsReservation(true);
        discountPackageDO.setStatus((byte) 1);
        discountPackageDO.setOriginalPrice(BigDecimal.TEN);
        discountPackageDO.setGmtUp(new Date());
        discountPackageDO.setMerchantStoreId(1L);
        discountPackageDO.setUseTimeBegin(new Date());
        discountPackageDO.setPrice(BigDecimal.TEN);
        discountPackageDO.setValidityPeriodEnd(new Date());
        discountPackageDO.setValidityPeriodBegin(new Date());
        discountPackageDO.setUseTimeBegin(new Date());
        discountPackageDO.setUseTimeEnd(new Date());
        discountPackageDO.setUseTimeWeek("1");
        discountPackageDOMapper.insert(discountPackageDO);

        List<Long> ids2 = new ArrayList<>();
        ids2.add(discountPackageDO.getId());
        String requestJson2 = JSONObject.toJSONString(ids2);
        request = put("/discountPackage/1/up").contentType(MediaType.APPLICATION_JSON).content(requestJson2);
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void downList() {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        String requestJson = JSONObject.toJSONString(ids);
        RequestBuilder request = null;
         request = put("/discountPackage/1/down").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        ResultActions perform = null;
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }

        DiscountPackageDO discountPackageDO = new DiscountPackageDO();
        discountPackageDO.setMerchantId(2L);
        discountPackageDO.setGmtCreate(new Date());
        discountPackageDO.setName("test");
        discountPackageDO.setIsReservation(true);
        discountPackageDO.setStatus((byte) 1);
        discountPackageDO.setOriginalPrice(BigDecimal.TEN);
        discountPackageDO.setGmtUp(new Date());
        discountPackageDO.setMerchantStoreId(1L);
        discountPackageDO.setUseTimeBegin(new Date());
        discountPackageDO.setPrice(BigDecimal.TEN);
        discountPackageDO.setValidityPeriodEnd(new Date());
        discountPackageDO.setValidityPeriodBegin(new Date());
        discountPackageDO.setUseTimeBegin(new Date());
        discountPackageDO.setUseTimeEnd(new Date());
        discountPackageDO.setUseTimeWeek("1");
        discountPackageDOMapper.insert(discountPackageDO);

        List<Long> ids2 = new ArrayList<>();
        ids2.add(discountPackageDO.getId());
        String requestJson2 = JSONObject.toJSONString(ids2);
        request = put("/discountPackage/1/down").contentType(MediaType.APPLICATION_JSON).content(requestJson2);
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
