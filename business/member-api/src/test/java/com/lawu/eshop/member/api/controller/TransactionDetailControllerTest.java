 package com.lawu.eshop.member.api.controller;

 import com.lawu.authorization.interceptor.AuthorizationInterceptor;
import com.lawu.eshop.member.api.MemberApiApplicationTest;
 import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
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

 import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
 import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

 /**
  * @author yangqh
  * @date 2017/8/2
  */
 @RunWith(SpringJUnit4ClassRunner.class)
 @SpringBootTest(classes = MemberApiApplicationTest.class)
 @WebAppConfiguration
 public class TransactionDetailControllerTest {

     private MockMvc mvc;

     @Autowired
     private TransactionDetailController transactionDetailController;

     @Autowired
     private AuthorizationInterceptor authorizationInterceptor;

     @Before
     public void setUp() throws Exception {
         mvc = MockMvcBuilders.standaloneSetup(transactionDetailController).addInterceptors(authorizationInterceptor).build();
     }

     @Test
     public void page() {
         MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
         params.add("transactionType", MemberTransactionTypeEnum.AD_QZ.name());
         params.add("currentPage","1");
         params.add("pageSize","10");
         RequestBuilder request = get("/transactionDetail/page").header("authorization","").params(params);
         try {
             ResultActions perform = mvc.perform(request);
             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail(e.getMessage());
         }
     }

     @Test
     public void getAllTransactionType() {
         RequestBuilder request = get("/transactionDetail/getAllTransactionType").header("authorization","");
         try {
             ResultActions perform = mvc.perform(request);
             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail(e.getMessage());
         }
     }

 }
