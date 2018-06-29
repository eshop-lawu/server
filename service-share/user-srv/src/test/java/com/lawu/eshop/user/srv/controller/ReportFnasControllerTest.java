//package com.lawu.eshop.user.srv.controller;
//
//import com.lawu.framework.web.HttpCode;
//import com.lawu.eshop.user.constants.ReportFansRiseRateEnum;
//import com.lawu.eshop.user.param.ReportDataParam;
//import com.lawu.eshop.user.srv.UserSrvApplicationTest;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// * @author meishuquan
// * @date 2017/7/18
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = UserSrvApplicationTest.class)
//@WebAppConfiguration
//public class ReportFnasControllerTest {
//
//    private MockMvc mvc;
//
//    @Autowired
//    private ReportFnasController reportFnasController;
//
//    @Before
//    public void setUp() throws Exception {
//        mvc = MockMvcBuilders.standaloneSetup(reportFnasController).build();
//    }
//
//   @Transactional(rollbackFor = Exception.class)
//    @Rollback
//    @Test
//    public void fansRiseRate() {
//        ReportDataParam param = new ReportDataParam();
//        param.setMerchantId(200L);
//        param.setFlag(ReportFansRiseRateEnum.DAY);
//        String requestJson = JSONObject.toJSONString(param);
//        RequestBuilder request = post("/reportFans/fansRiseRate").contentType(MediaType.APPLICATION_JSON).content(requestJson);
//        try {
//            ResultActions perform = mvc.perform(request);
//            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
//            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
//        } catch (Exception e) {
//            e.printStackTrace();
//            Assert.fail(e.getMessage());
//        }
//    }
//
//    @Transactional(rollbackFor = Exception.class)
//    @Rollback
//    @Test
//    public void fansRiseSource() {
//        ReportDataParam param = new ReportDataParam();
//        param.setMerchantId(200L);
//        param.setFlag(ReportFansRiseRateEnum.DAY);
//        String requestJson = JSONObject.toJSONString(param);
//        RequestBuilder request = post("/reportFans/fansRiseSource").contentType(MediaType.APPLICATION_JSON).content(requestJson);
//        try {
//            ResultActions perform = mvc.perform(request);
//            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
//            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
//        } catch (Exception e) {
//            e.printStackTrace();
//            Assert.fail(e.getMessage());
//        }
//    }
//
//}
