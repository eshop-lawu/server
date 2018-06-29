 package com.lawu.eshop.member.api.controller;

import com.lawu.authorization.interceptor.AuthorizationInterceptor;
import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.member.api.MemberApiApplicationTest;
import com.lawu.eshop.property.constants.PayTypeEnum;
import com.lawu.framework.web.HttpCode;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

 /**
  * @author yangqh
  * @date 2017/8/1
  */
 @RunWith(SpringJUnit4ClassRunner.class)
 @SpringBootTest(classes = MemberApiApplicationTest.class)
 @WebAppConfiguration
 public class RechargeControllerTest {

     private MockMvc mvc;

     @Autowired
     private RechargeController rechargeController;

     @Autowired
     private AuthorizationInterceptor authorizationInterceptor;

     @Before
     public void setUp() throws Exception {
         mvc = MockMvcBuilders.standaloneSetup(rechargeController).addInterceptors(authorizationInterceptor).build();
     }

     @Test
     public void save() {
         MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
         params.add("rechargeMoney","12");
         params.add("payTypeEnum", PayTypeEnum.BALANCE.name());
         params.add("transactionPayTypeEnum", TransactionPayTypeEnum.ALIPAY.name());
         params.add("payPwd","123456");
         RequestBuilder request = post("/recharge/save").header("authorization","").params(params);
         try {
             ResultActions perform = mvc.perform(request);
             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail(e.getMessage());
         }
     }
 }
