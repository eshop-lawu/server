 package com.lawu.eshop.member.api.controller;

 import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

import com.lawu.authorization.interceptor.AuthorizationInterceptor;
import com.lawu.eshop.member.api.MemberApiApplicationTest;
import com.lawu.eshop.order.constants.ShoppingOrderStatusToMemberEnum;
import com.lawu.eshop.order.constants.ShoppingRefundTypeEnum;
import com.lawu.framework.web.HttpCode;

 /**
  * @author yangqh
  * @date 2017/8/2
  */
 @RunWith(SpringJUnit4ClassRunner.class)
 @SpringBootTest(classes = MemberApiApplicationTest.class)
 @WebAppConfiguration
 public class ShoppingOrderControllerTest {

     private MockMvc mvc;

     @Autowired
     private ShoppingOrderController shoppingOrderController;

     @Autowired
     private AuthorizationInterceptor authorizationInterceptor;

     @Before
     public void setUp() throws Exception {
         mvc = MockMvcBuilders.standaloneSetup(shoppingOrderController).addInterceptors(authorizationInterceptor).build();
     }

     @Test
     public void selectPageByMemberId() {
         MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
         params.add("currentPage","1");
         params.add("pageSize","10");
         params.add("keyword","fdf");
         params.add("orderStatus",ShoppingOrderStatusToMemberEnum.BE_EVALUATED.name());
         RequestBuilder request = get("/shoppingOrder/selectPageByMemberId").header("authorization","").params(params);
         try {
             ResultActions perform = mvc.perform(request);
             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail(e.getMessage());
         }
     }

     @Test
     public void get1() {
         RequestBuilder request = get("/shoppingOrder/get/1").header("authorization","").param("id","1");
         try {
             ResultActions perform = mvc.perform(request);
             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail(e.getMessage());
         }
     }

     @Test
     public void getExpressInfo() {
         RequestBuilder request = get("/shoppingOrder/getExpressInfo/1").header("authorization","").param("id","1");
         try {
             ResultActions perform = mvc.perform(request);
             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail(e.getMessage());
         }
     }

     @Test
     public void cancelOrder() {
         RequestBuilder request = put("/shoppingOrder/cancelOrder/1").header("authorization","").param("id","1");
         try {
             ResultActions perform = mvc.perform(request);
             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail(e.getMessage());
         }
     }

     @Test
     public void deleteOrder() {
         RequestBuilder request = delete("/shoppingOrder/deleteOrder/1").header("authorization","").param("id","1");
         try {
             ResultActions perform = mvc.perform(request);
             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_NO_CONTENT)).andDo(MockMvcResultHandlers.print()).andReturn();
         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail(e.getMessage());
         }
     }

     @Test
     public void tradingSuccess() {
         RequestBuilder request = put("/shoppingOrder/tradingSuccess/1").header("authorization","").param("id","1");
         try {
             ResultActions perform = mvc.perform(request);
             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail(e.getMessage());
         }
     }

     @Test
     public void requestRefund() {
         MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
         params.add("reason","1");
         params.add("description","10");
         params.add("type", ShoppingRefundTypeEnum.REFUND.name());
         RequestBuilder request = post("/shoppingOrder/requestRefund/1").header("authorization","").param("shoppingOrderitemId","1").params(params);
         try {
             ResultActions perform = mvc.perform(request);
             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail(e.getMessage());
         }
     }

     @Test
     public void selectRefundPageByMemberId() {
         MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
         params.add("currentPage","1");
         params.add("pageSize","10");
         params.add("keyword","fdf");
         RequestBuilder request = get("/shoppingOrder/selectRefundPageByMemberId").header("authorization","").params(params);
         try {
             ResultActions perform = mvc.perform(request);
             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail(e.getMessage());
         }
     }

     @Test
     public void orderPayment() {
         RequestBuilder request = get("/shoppingOrder/orderPayment/1").header("authorization","").param("id","1");
         try {
             ResultActions perform = mvc.perform(request);
             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail(e.getMessage());
         }
     }
     
     @Test
     public void applyRefund() {
         MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
         params.add("reason","1");
         params.add("description","10");
         params.add("type", ShoppingRefundTypeEnum.REFUND.name());
         params.add("voucherPicture", "voucherPicture");
         RequestBuilder request = post("/shoppingOrder/applyRefund/1").header("authorization","").param("shoppingOrderitemId","1").params(params);
         try {
             ResultActions perform = mvc.perform(request);
             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail(e.getMessage());
         }
     }
 }
