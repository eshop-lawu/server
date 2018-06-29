 package com.lawu.eshop.member.api.controller;

 import com.lawu.authorization.interceptor.AuthorizationInterceptor;
import com.lawu.eshop.mall.constants.SuggestionClientType;
 import com.lawu.eshop.member.api.MemberApiApplicationTest;
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
  * @date 2017/8/2
  */
 @RunWith(SpringJUnit4ClassRunner.class)
 @SpringBootTest(classes = MemberApiApplicationTest.class)
 @WebAppConfiguration
 public class SuggestionControllerTest {

     private MockMvc mvc;

     @Autowired
     private SuggestionController suggestionController;

     @Autowired
     private AuthorizationInterceptor authorizationInterceptor;

     @Before
     public void setUp() throws Exception {
         mvc = MockMvcBuilders.standaloneSetup(suggestionController).addInterceptors(authorizationInterceptor).build();
     }

     @Test
     public void save() {
         MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
         params.add("content","1");
         params.add("clientType", SuggestionClientType.ANDROID.name());
         RequestBuilder request = post("/suggestion/").header("authorization","").params(params);
         try {
             ResultActions perform = mvc.perform(request);
             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail(e.getMessage());
         }
     }
 }
