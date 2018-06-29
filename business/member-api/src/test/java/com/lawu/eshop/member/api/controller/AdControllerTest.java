package com.lawu.eshop.member.api.controller;

import com.lawu.authorization.interceptor.AuthorizationInterceptor;
import com.lawu.eshop.ad.constants.AdPraiseStatusEnum;
import com.lawu.eshop.ad.constants.OrderTypeEnum;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author yangqh
 * @date 2017/7/24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MemberApiApplicationTest.class)
@WebAppConfiguration
public class AdControllerTest {

    private MockMvc mvc;

    @Autowired
    private AdController adController;

    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(adController).addInterceptors(authorizationInterceptor).build();
    }

    @Test
    public void selectEgain() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("longitude","12.2121");
        params.add("latitude","22.2121");
        params.add("typeEnum","AD_TYPE_FLAT");
        params.add("currentPage","1");
        params.add("pageSize","10");
        RequestBuilder request = get("/ad/selectEgain").header("authorization","").params(params);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void selectChoiceness() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("longitude","12.2121");
        params.add("latitude","22.2121");
        params.add("currentPage","1");
        params.add("pageSize","10");
        RequestBuilder request = get("/ad/selectChoiceness").header("authorization","").params(params);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void selectListPointTotle() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("longitude","12.2121");
        params.add("latitude","22.2121");
        params.add("currentPage","1");
        params.add("pageSize","10");
        RequestBuilder request = get("/ad/selectListPointTotle").header("authorization","").params(params);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void selectAdDetail() {
        RequestBuilder request = get("/ad/selectAb/1").header("authorization","").param("id", "1");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_ACCEPTED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void selectPraiseListByMember() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("statusEnum", AdPraiseStatusEnum.AD_STATUS_TOBEGIN.name());
        params.add("currentPage","1");
        params.add("pageSize","10");
        RequestBuilder request = get("/ad/selectPraiseListByMember").header("authorization","").params(params);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void selectAbPraise() {
        RequestBuilder request = get("/ad/selectAbPraise/1").header("authorization","").param("id", "1");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void memberRanking() {
        RequestBuilder request = get("/ad/memberRanking/1").header("authorization","").param("id", "1");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void clickPraise() {
        RequestBuilder request = put("/ad/clickPraise/1").header("authorization","").param("id", "1");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void clickAd() {
        RequestBuilder request = put("/ad/clickAd/1").header("authorization","").param("id", "1");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void selectLexicon() {
        RequestBuilder request = get("/ad/selectLexicon").header("authorization","").param("adId", "1");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void selectAdByTitle() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("title", "nimei");
        params.add("longitude","12.2121");
        params.add("latitude","22.2121");
        params.add("currentPage","1");
        params.add("pageSize","10");
        RequestBuilder request = get("/ad/selectAdByTitle").header("authorization","").params(params);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void getRedPacket() {
        RequestBuilder request = put("/ad/getRedPacket").param("merchantId", "1").param("mobile","13800138000");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void doHanlderMinusPoint() {
        RequestBuilder request = post("/ad/doHanlderMinusPoint").header("authorization","").param("id","1");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void selectEgainAd() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("longitude","12.2121");
        params.add("latitude","22.2121");
        params.add("typeEnum","AD_TYPE_FLAT");
        params.add("currentPage","1");
        params.add("pageSize","10");
        RequestBuilder request = get("/ad/selectEgainAd").header("authorization","").params(params);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void getRedPacketInfo() {
        RequestBuilder request = get("/ad/getRedPacketInfo/1").header("authorization","").param("merchantId","1");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void registerGetRedPacket() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("verifyCodeId","123212");
        params.add("account","13800138000");
        params.add("pwd","2212121");
        params.add("smsCode","111111");
        params.add("merchantId","1");
        RequestBuilder request = post("/ad/registerGetRedPacket").header("authorization","").params(params);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void isExistsRedPacket() {
        RequestBuilder request = get("/ad/isExistsRedPacket/1").header("authorization","").param("merchantId","1");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void egainAd() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("longitude","12.2121");
        params.add("latitude","22.2121");
        params.add("typeEnum","AD_TYPE_FLAT");
        params.add("currentPage","1");
        params.add("pageSize","10");
        RequestBuilder request = get("/ad/egainAd").header("authorization","").params(params);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void selectPointTotle() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("longitude","12.2121");
        params.add("latitude","22.2121");
        params.add("orderTypeEnum", OrderTypeEnum.AD_POINT_DESC.name());
        RequestBuilder request = get("/ad/selectPointTotle").header("authorization","").params(params);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void choiceness() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("longitude","12.2121");
        params.add("latitude","22.2121");
        params.add("currentPage","1");
        params.add("pageSize","10");
        RequestBuilder request = get("/ad/choiceness").header("authorization","").params(params);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
}
