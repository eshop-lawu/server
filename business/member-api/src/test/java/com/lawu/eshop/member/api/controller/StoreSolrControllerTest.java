 package com.lawu.eshop.member.api.controller;

 import com.lawu.authorization.interceptor.AuthorizationInterceptor;
import com.lawu.eshop.member.api.MemberApiApplicationTest;
 import com.lawu.eshop.user.constants.StoreSolrEnum;
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

 import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
 import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

 /**
  * @author yangqh
  * @date 2017/8/2
  */
 @RunWith(SpringJUnit4ClassRunner.class)
 @SpringBootTest(classes = MemberApiApplicationTest.class)
 @WebAppConfiguration
 public class StoreSolrControllerTest {

     private MockMvc mvc;

     @Autowired
     private StoreSolrController storeSolrController;

     @Autowired
     private AuthorizationInterceptor authorizationInterceptor;

     @Before
     public void setUp() throws Exception {
         mvc = MockMvcBuilders.standaloneSetup(storeSolrController).addInterceptors(authorizationInterceptor).build();
     }

     @Test
     public void listStore() {
         MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
         params.add("name","1");
         params.add("industryPath","1");
         params.add("distance","1");
         params.add("regionPath","1");
         params.add("longitude","1");
         params.add("latitude","1");
         params.add("latitude","1");
         params.add("storeId","1");
         params.add("storeSolrEnum", StoreSolrEnum.DISTANCE_SORT.name());
         RequestBuilder request = get("/storeSolr/listStore").params(params);
         try {
             ResultActions perform = mvc.perform(request);
             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail(e.getMessage());
         }
     }

     /*
     @Test
     public void listStoreSearchWord() {
         RequestBuilder request = get("/storeSolr/listStoreSearchWord").param("name","11/111");
         try {
             ResultActions perform = mvc.perform(request);
             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail(e.getMessage());
         }
     }
*/
     @Test
     public void listRecommendStore() {
         RequestBuilder request = get("/storeSolr/listRecommendStore").param("regionPath","11/111/1111");
         try {
             ResultActions perform = mvc.perform(request);
             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail(e.getMessage());
         }
     }

//     @Test
//     public void listNewMerchant() {
//         RequestBuilder request = get("/storeSolr/listNewMerchant").param("regionPath","11/111/1111");
//         try {
//             ResultActions perform = mvc.perform(request);
//             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
//         } catch (Exception e) {
//             e.printStackTrace();
//             Assert.fail(e.getMessage());
//         }
//     }

     @Test
     public void discountStore() {
         MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
         params.add("regionPath","1");
         params.add("longitude","1");
         params.add("latitude","1");
         params.add("currentPage","1");
         params.add("pageSize","10");
         RequestBuilder request = get("/storeSolr/discountStore").params(params);
         try {
             ResultActions perform = mvc.perform(request);
             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail(e.getMessage());
         }
     }

//     @Test
//     public void listRecommendFood() {
//         MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
//         params.add("regionPath","1");
//         params.add("longitude","1");
//         params.add("latitude","1");
//         params.add("currentPage","1");
//         params.add("pageSize","10");
//         RequestBuilder request = get("/storeSolr/listRecommendFood").params(params);
//         try {
//             ResultActions perform = mvc.perform(request);
//             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
//         } catch (Exception e) {
//             e.printStackTrace();
//             Assert.fail(e.getMessage());
//         }
//     }

//     @Test
//     public void t(){
//         RecommendFoodDTO dto = new RecommendFoodDTO();
//         dto.setLatitude(new BigDecimal("33"));
//         dto.setLongitude(new BigDecimal("343"));
//         dto.setFavoreInfo("33");
//         dto.setDistance(Double.parseDouble("3"));
//         dto.setDiscountPackage("dd");
//         dto.setAddress("sdfd");
//         dto.setAreaName("fdfd");
//         dto.setAverageConsumeAmount(new BigDecimal(2));
//         dto.setAverageScore(new BigDecimal("3"));
//         dto.setBuyNumbers(1);
//         dto.setCommentsCount(1);
//         dto.setIndustryName("#3");
//         dto.setMerchantId(1L);
//         dto.setMerchantStoreId(1L);
//         dto.setName("fdf");
//         dto.setRegionName("fdfd");
//         dto.setStorePic("fdfd");
//         System.out.println(JSON.toJSONString(dto));
//     }
 }
