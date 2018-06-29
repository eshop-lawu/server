package com.lawu.eshop.user.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Assert;
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

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.user.constants.StoreSolrEnum;
import com.lawu.eshop.user.param.DiscountStoreParam;
import com.lawu.eshop.user.param.StoreSolrParam;
import com.lawu.eshop.user.srv.UserSrvApplicationTest;
import com.lawu.framework.web.HttpCode;

/**
 * @author meishuquan
 * @date 2017/7/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserSrvApplicationTest.class)
@WebAppConfiguration
public class StoreSolrControllerTest {

    private MockMvc mvc;

    @Autowired
    private StoreSolrController storeSolrController;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(storeSolrController).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listStore() {
        StoreSolrParam param = new StoreSolrParam();
        param.setName("test");
        param.setIndustryPath("10");
        param.setStoreId(300L);
        param.setDistance(10);
        param.setStoreSolrEnum(StoreSolrEnum.INTELLIGENT_SORT);
        param.setRegionPath("44/4403");
        param.setLongitude(new BigDecimal(104.23));
        param.setLatitude(new BigDecimal(22.36));
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/storeSolr/listStore").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listStoreSearchWord() {
        RequestBuilder request = get("/storeSolr/listStoreSearchWord").param("name", "test").param("regionPath","44/4403");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void discountStore() {
        DiscountStoreParam param = new DiscountStoreParam();
        param.setRegionPath("44/4403");
        param.setLongitude(new BigDecimal(104.23));
        param.setLatitude(new BigDecimal(22.36));
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/storeSolr/discountStore").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

}
