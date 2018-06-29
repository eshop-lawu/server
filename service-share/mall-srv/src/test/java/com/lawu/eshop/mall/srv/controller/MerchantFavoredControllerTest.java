package com.lawu.eshop.mall.srv.controller;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.mall.constants.MerchantFavoredTypeEnum;
import com.lawu.eshop.mall.param.MerchantFavoredParam;
import com.lawu.eshop.mall.srv.MallSrvApplicationTest;
import com.lawu.eshop.mall.srv.domain.MerchantFavoredDO;
import com.lawu.eshop.mall.srv.mapper.MerchantFavoredDOMapper;
import com.lawu.framework.web.HttpCode;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author zhangyong
 * @date 2017/7/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MallSrvApplicationTest.class)
@WebAppConfiguration
public class MerchantFavoredControllerTest {
    private MockMvc mvc;

    @Autowired
    private MerchantFavoredController merchantFavoredController;

    @Autowired
    private MerchantFavoredDOMapper merchantFavoredDOMapper;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(merchantFavoredController).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveMerchantFavoredInfo(){
        MerchantFavoredParam param = new MerchantFavoredParam();
        param.setDiscountRate(BigDecimal.ONE);
        param.setFavoredAmount(BigDecimal.valueOf(20));
        param.setReachAmount(BigDecimal.valueOf(100));
        param.setEntireBeginTime(new Date());
        param.setEntireEndTime(new Date());
        param.setTypeEnum(MerchantFavoredTypeEnum.TYPE_DISCOUNT);
        param.setValidDayBeginTime("07:00");
        param.setValidDayEndTime("18:00");
        param.setValidWeekTime("每周");
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = null;
        ResultActions perform = null;
        request = post("/merchantFavored/saveMerchantFavoredInfo/1").param("storeId","2").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request = post("/merchantFavored/saveMerchantFavoredInfo/1").param("storeId","2").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void findFavoredByMerchantId(){
        RequestBuilder request = null;
        ResultActions perform = null;
        request = get("/merchantFavored/findFavoredByMerchantId/1");
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        MerchantFavoredDO merchantFavoredDO = new MerchantFavoredDO();
        merchantFavoredDO.setDiscountRate(BigDecimal.ONE);
        merchantFavoredDO.setFavoredAmount(BigDecimal.valueOf(20));
        merchantFavoredDO.setReachAmount(BigDecimal.valueOf(100));
        merchantFavoredDO.setEntireBeginTime(new Date());
        merchantFavoredDO.setEntireEndTime(new Date());
        merchantFavoredDO.setType(MerchantFavoredTypeEnum.TYPE_DISCOUNT.val);
        merchantFavoredDO.setStatus(StatusEnum.VALID.getValue());
        merchantFavoredDO.setValidDayBeginTime("07:00");
        merchantFavoredDO.setValidDayEndTime("18:00");
        merchantFavoredDO.setValidWeekTime("每周");
        merchantFavoredDO.setMerchantId(1L);
        merchantFavoredDOMapper.insert(merchantFavoredDO);
        request = get("/merchantFavored/findFavoredByMerchantId/1");
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
    public void delMerchantFavoredInfo(){
        RequestBuilder request = null;
        ResultActions perform = null;
        request = delete("/merchantFavored/delMerchantFavoredInfo/1").param("merchantId","1").param("storeId","2");
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        MerchantFavoredDO merchantFavoredDO = new MerchantFavoredDO();
        merchantFavoredDO.setDiscountRate(BigDecimal.ONE);
        merchantFavoredDO.setFavoredAmount(BigDecimal.valueOf(20));
        merchantFavoredDO.setReachAmount(BigDecimal.valueOf(100));
        merchantFavoredDO.setEntireBeginTime(new Date());
        merchantFavoredDO.setEntireEndTime(new Date());
        merchantFavoredDO.setType(MerchantFavoredTypeEnum.TYPE_DISCOUNT.val);
        merchantFavoredDO.setStatus(StatusEnum.VALID.getValue());
        merchantFavoredDO.setValidDayBeginTime("07:00");
        merchantFavoredDO.setValidDayEndTime("18:00");
        merchantFavoredDO.setValidWeekTime("每周");
        merchantFavoredDO.setMerchantId(1L);
        merchantFavoredDOMapper.insert(merchantFavoredDO);
        request = delete("/merchantFavored/delMerchantFavoredInfo/"+merchantFavoredDO.getId()).param("merchantId","1").param("storeId","2");
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_NO_CONTENT)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateMerchantFavoredInfo(){
        RequestBuilder request = null;
        ResultActions perform = null;
        MerchantFavoredParam param = new MerchantFavoredParam();
        param.setDiscountRate(BigDecimal.ONE);
        param.setFavoredAmount(BigDecimal.valueOf(20));
        param.setReachAmount(BigDecimal.valueOf(100));
        param.setEntireBeginTime(new Date());
        param.setEntireEndTime(new Date());
        param.setTypeEnum(MerchantFavoredTypeEnum.TYPE_DISCOUNT);
        param.setValidDayBeginTime("07:00");
        param.setValidDayEndTime("18:00");
        param.setValidWeekTime("每周");
        String requestJson = JSONObject.toJSONString(param);
        request = put("/merchantFavored/updateMerchantFavoredInfo/1").param("storeId","2").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        MerchantFavoredDO merchantFavoredDO = new MerchantFavoredDO();
        merchantFavoredDO.setDiscountRate(BigDecimal.ONE);
        merchantFavoredDO.setFavoredAmount(BigDecimal.valueOf(20));
        merchantFavoredDO.setReachAmount(BigDecimal.valueOf(100));
        merchantFavoredDO.setEntireBeginTime(new Date());
        merchantFavoredDO.setEntireEndTime(new Date());
        merchantFavoredDO.setType(MerchantFavoredTypeEnum.TYPE_DISCOUNT.val);
        merchantFavoredDO.setStatus(StatusEnum.VALID.getValue());
        merchantFavoredDO.setValidDayBeginTime("07:00");
        merchantFavoredDO.setValidDayEndTime("18:00");
        merchantFavoredDO.setValidWeekTime("每周");
        merchantFavoredDO.setMerchantId(1L);
        merchantFavoredDOMapper.insert(merchantFavoredDO);
        request = put("/merchantFavored/updateMerchantFavoredInfo/1").param("storeId","2").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void findFavoredById(){
        RequestBuilder request = null;
        ResultActions perform = null;
        request = get("/merchantFavored/findFavoredById/1");
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        MerchantFavoredDO merchantFavoredDO = new MerchantFavoredDO();
        merchantFavoredDO.setDiscountRate(BigDecimal.ONE);
        merchantFavoredDO.setFavoredAmount(BigDecimal.valueOf(20));
        merchantFavoredDO.setReachAmount(BigDecimal.valueOf(100));
        merchantFavoredDO.setEntireBeginTime(new Date());
        merchantFavoredDO.setEntireEndTime(new Date());
        merchantFavoredDO.setType(MerchantFavoredTypeEnum.TYPE_DISCOUNT.val);
        merchantFavoredDO.setStatus(StatusEnum.VALID.getValue());
        merchantFavoredDO.setValidDayBeginTime("07:00");
        merchantFavoredDO.setValidDayEndTime("18:00");
        merchantFavoredDO.setValidWeekTime("每周");
        merchantFavoredDO.setMerchantId(1L);
        merchantFavoredDOMapper.insert(merchantFavoredDO);
        request = get("/merchantFavored/findFavoredById/"+merchantFavoredDO.getId());
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
