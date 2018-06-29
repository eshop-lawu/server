package com.lawu.eshop.merchant.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.mall.param.DiscountPackageImageSaveParam;
import com.lawu.eshop.mall.param.DiscountPackageImageUpdateParam;
import com.lawu.eshop.mall.param.DiscountPackageUpdateParam;
import com.lawu.eshop.mall.param.foreign.DiscountPackageContentSaveForeignParam;
import com.lawu.eshop.mall.param.foreign.DiscountPackageContentUpdateForeignParam;
import com.lawu.eshop.mall.param.foreign.DiscountPackageSaveWithImageForeignParam;
import com.lawu.eshop.merchant.api.MerchantApiApplicationTest;
import com.lawu.framework.web.HttpCode;
import com.lawu.utils.DateUtil;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MerchantApiApplicationTest.class)
@WebAppConfiguration
public class DiscountPackageControllerTest {
    private MockMvc mvc;

    @Autowired
    private DiscountPackageController discountPackageController;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(discountPackageController).build();
    }

    @Test
    public void list() {
        RequestBuilder request = get("/discountPackage/list").header("authorization", "8888").param("status", "UP");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void getById() {
        RequestBuilder request = get("/discountPackage/10").header("authorization", "8888");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void save() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("name", "test");
        map.add("price", "10");
        map.add("otherInstructions", "remark");
        map.add("validityPeriodBegin", "2017-07-26");
        map.add("validityPeriodEnd", "2017-07-26");
        map.add("useTimeWeek", "工作日");
        map.add("useTimeBegin", "19:00");
        map.add("useTimeEnd", "22:00");
        map.add("isReservation", "false");
        map.add("discountPackageContents", "[{\"name\":\"test\",\"quantity\":10,\"unit\":\"test\",\"unitPrice\":10}]");
        map.add("discountPackageImages", "[{\"description\":\"pic\"}]");
        RequestBuilder request = post("/discountPackage/").header("authorization", "8888").params(map);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
    
    @Test
    public void saveWithImage() {
    	DiscountPackageSaveWithImageForeignParam param = new DiscountPackageSaveWithImageForeignParam();
        param.setAdvanceBookingTime("1个小时");
        param.setCoverImage("test.jpg");
        param.setIsReservation(true);
        param.setName("test");
        param.setOtherInstructions("otherInstructions");
        param.setPurchaseNotes("1,3");
        param.setUseRules("useRules");
        param.setUseTimeBegin(DateUtil.formatDate("19:00", "HH:mm"));
        param.setUseTimeEnd(DateUtil.formatDate("22:00", "HH:mm"));
        param.setUseTimeWeek("0,1,2");
        param.setValidityPeriodBegin(DateUtil.formatDate("2017-07-26", "yyyy-MM-dd"));
        param.setValidityPeriodEnd(DateUtil.formatDate("2017-07-27", "yyyy-MM-dd"));
        param.setPrice(BigDecimal.valueOf(1));
        
        param.setDiscountPackageContents(new ArrayList<>());
        DiscountPackageContentSaveForeignParam discountPackageContentSaveForeignParam = new DiscountPackageContentSaveForeignParam();
        discountPackageContentSaveForeignParam.setName("佛跳墙");
        discountPackageContentSaveForeignParam.setQuantity(1);
        discountPackageContentSaveForeignParam.setUnit("份");
        discountPackageContentSaveForeignParam.setUnitPrice(BigDecimal.valueOf(100));
        param.getDiscountPackageContents().add(discountPackageContentSaveForeignParam);
        
        param.setDiscountPackageImages(new ArrayList<>());
        DiscountPackageImageSaveParam discountPackageImageSaveParam = new DiscountPackageImageSaveParam();
        discountPackageImageSaveParam.setDescription("description");
        discountPackageImageSaveParam.setImage("test.jpg");
        param.getDiscountPackageImages().add(discountPackageImageSaveParam);
        
        String content = JSONObject.toJSONString(param);
        RequestBuilder request = post("/discountPackage/save").header("authorization", "8888").contentType(MediaType.APPLICATION_JSON_UTF8).content(content);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
    
    @Test
    public void update() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("name", "test");
        map.add("price", "10");
        map.add("otherInstructions", "remark");
        map.add("validityPeriodBegin", "2017-07-26");
        map.add("validityPeriodEnd", "2017-07-26");
        map.add("useTimeWeek", "工作日");
        map.add("useTimeBegin", "19:00");
        map.add("useTimeEnd", "22:00");
        map.add("isReservation", "false");
        map.add("discountPackageContents", "[{\"id\":10,\"name\":\"test\",\"quantity\":10,\"unit\":\"test\",\"unitPrice\":10}]");
        map.add("discountPackageImages", "[{\"id\":10,\"description\":\"pic\"}]");
        RequestBuilder request = post("/discountPackage/10").header("authorization", "8888").params(map);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
    
    @Test
    public void updateWithImage() {
        DiscountPackageUpdateParam param = new DiscountPackageUpdateParam();
        param.setAdvanceBookingTime("1个小时");
        param.setCoverImage("test.jpg");
        param.setIsReservation(true);
        param.setName("test");
        param.setOtherInstructions("otherInstructions");
        param.setPurchaseNotes("1,3");
        param.setUseRules("useRules");
        param.setUseTimeBegin(DateUtil.formatDate("19:00", "HH:mm"));
        param.setUseTimeEnd(DateUtil.formatDate("22:00", "HH:mm"));
        param.setUseTimeWeek("0,1,2");
        param.setValidityPeriodBegin(DateUtil.formatDate("2017-07-26", "yyyy-MM-dd"));
        param.setValidityPeriodEnd(DateUtil.formatDate("2017-07-27", "yyyy-MM-dd"));
        param.setPrice(BigDecimal.valueOf(1));
        
        param.setDiscountPackageContents(new ArrayList<>());
        DiscountPackageContentUpdateForeignParam discountPackageContentUpdateForeignParam = new DiscountPackageContentUpdateForeignParam();
        discountPackageContentUpdateForeignParam.setId(1L);
        discountPackageContentUpdateForeignParam.setName("佛跳墙");
        discountPackageContentUpdateForeignParam.setQuantity(1);
        discountPackageContentUpdateForeignParam.setUnit("份");
        discountPackageContentUpdateForeignParam.setUnitPrice(BigDecimal.valueOf(100));
        param.getDiscountPackageContents().add(discountPackageContentUpdateForeignParam);
        
        param.setDiscountPackageImages(new ArrayList<>());
        DiscountPackageImageUpdateParam discountPackageImageUpdateParam = new DiscountPackageImageUpdateParam();
        discountPackageImageUpdateParam.setId(1L);
        discountPackageImageUpdateParam.setDescription("description");
        discountPackageImageUpdateParam.setImage("test.jpg");
        param.getDiscountPackageImages().add(discountPackageImageUpdateParam);
        
        String content = JSONObject.toJSONString(param);
        RequestBuilder request = post("/discountPackage/update/10").header("authorization", "8888").contentType(MediaType.APPLICATION_JSON_UTF8).content(content);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void remove() {
        RequestBuilder request = delete("/discountPackage/10").header("authorization", "8888");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_NO_CONTENT)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_NO_CONTENT, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void up() {
        RequestBuilder request = put("/discountPackage/up/10").header("authorization", "8888");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void down() {
        RequestBuilder request = put("/discountPackage/down/10").header("authorization", "8888");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void batchDelete() {
        List<Long> idList = new ArrayList<>();
        idList.add(10L);
        String requestJson = JSONObject.toJSONString(idList);
        RequestBuilder request = delete("/discountPackage/delete").header("authorization", "8888").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_NO_CONTENT)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_NO_CONTENT, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void batchUp() {
        List<Long> idList = new ArrayList<>();
        idList.add(10L);
        String requestJson = JSONObject.toJSONString(idList);
        RequestBuilder request = put("/discountPackage/up").header("authorization", "8888").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void batchDown() {
        List<Long> idList = new ArrayList<>();
        idList.add(10L);
        String requestJson = JSONObject.toJSONString(idList);
        RequestBuilder request = put("/discountPackage/down").header("authorization", "8888").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

}
