 package com.lawu.eshop.member.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.lawu.authorization.interceptor.AuthorizationInterceptor;
import com.lawu.eshop.member.api.MemberApiApplicationTest;
import com.lawu.eshop.order.param.ShoppingCartParam;
import com.lawu.eshop.order.param.foreign.ShoppingOrderBuyNowCreateOrderForeignParam;
import com.lawu.eshop.order.param.foreign.ShoppingOrderSettlementForeignParam;
import com.lawu.framework.web.HttpCode;

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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

 /**
  * @author yangqh
  * @date 2017/8/1
  */
 @RunWith(SpringJUnit4ClassRunner.class)
 @SpringBootTest(classes = MemberApiApplicationTest.class)
 @WebAppConfiguration
 public class ShoppingCartControllerTest {

     private MockMvc mvc;

     @Autowired
     private ShoppingCartController shoppingCartController;

     @Autowired
     private AuthorizationInterceptor authorizationInterceptor;

     @Before
     public void setUp() throws Exception {
         mvc = MockMvcBuilders.standaloneSetup(shoppingCartController).addInterceptors(authorizationInterceptor).build();
     }

     @Test
     public void save() {
         MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
         params.add("productModelId","1");
         params.add("quantity","10");
         RequestBuilder request = post("/shoppingCart/save").header("authorization","").params(params);
         try {
             ResultActions perform = mvc.perform(request);
             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail(e.getMessage());
         }
     }

     @Test
     public void findListByMemberId() {
         RequestBuilder request = get("/shoppingCart/findListByMemberId").header("authorization","");
         try {
             ResultActions perform = mvc.perform(request);
             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail(e.getMessage());
         }
     }

     @Test
     public void update() {
         MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
         params.add("productModelId","1");
         params.add("quantity","10");
         RequestBuilder request = put("/shoppingCart/update/1").header("authorization","").param("id","1").params(params);
         try {
             ResultActions perform = mvc.perform(request);
             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail(e.getMessage());
         }
     }

     @Test
     public void delete1() {
         List<Long> ids = new ArrayList<>();
         ids.add(1L);
         String requestJson = JSONObject.toJSONString(ids);
         RequestBuilder request = delete("/shoppingCart/delete").header("authorization","").contentType(MediaType.APPLICATION_JSON).content(requestJson);
         try {
             ResultActions perform = mvc.perform(request);
             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_NO_CONTENT)).andDo(MockMvcResultHandlers.print()).andReturn();
         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail(e.getMessage());
         }
     }

     @Test
     public void settlement() {
         List<Long> ids = new ArrayList<>();

         ids.add(1L);
         String requestJson = JSONObject.toJSONString(ids);
         RequestBuilder request = post("/shoppingCart/settlement").header("authorization","").contentType(MediaType.APPLICATION_JSON).content(requestJson);
         try {
             ResultActions perform = mvc.perform(request);
             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail(e.getMessage());
         }
     }

     @Test
     public void createOrder() {
         List<ShoppingOrderSettlementForeignParam> params = new ArrayList<>();
         ShoppingOrderSettlementForeignParam param = new ShoppingOrderSettlementForeignParam();
         param.setMerchantId(1L);
         param.setAddressId(1L);
         param.setFreightPrice(new BigDecimal("1"));
         List<Long> ids = new ArrayList<>();
         ids.add(1L);
         param.setIds(ids);
         param.setMessage("messgae");
         params.add(param);
         String requestJson = JSONObject.toJSONString(params);
         RequestBuilder request = post("/shoppingCart/createOrder").header("authorization","").contentType(MediaType.APPLICATION_JSON).content(requestJson);
         try {
             ResultActions perform = mvc.perform(request);
             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail(e.getMessage());
         }
     }

     @Test
     public void buyNow() {
         ShoppingCartParam param = new ShoppingCartParam();
         param.setProductModelId(1L);
         param.setQuantity(1);
         String requestJson = JSONObject.toJSONString(param);
         RequestBuilder request = post("/shoppingCart/buyNow").header("authorization","").contentType(MediaType.APPLICATION_JSON).content(requestJson);
         try {
             ResultActions perform = mvc.perform(request);
             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail(e.getMessage());
         }
     }

     @Test
     public void buyNowCreateOrder() {
         ShoppingOrderBuyNowCreateOrderForeignParam param = new ShoppingOrderBuyNowCreateOrderForeignParam();
         param.setProductModelId(1L);
         param.setQuantity(1);
         param.setAddressId(1L);
         param.setFreightPrice(new BigDecimal("1"));
         param.setMessage("gun");
         String requestJson = JSONObject.toJSONString(param);
         RequestBuilder request = post("/shoppingCart/buyNowCreateOrder").header("authorization","").contentType(MediaType.APPLICATION_JSON).content(requestJson);
         try {
             ResultActions perform = mvc.perform(request);
             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail(e.getMessage());
         }
     }
 }
