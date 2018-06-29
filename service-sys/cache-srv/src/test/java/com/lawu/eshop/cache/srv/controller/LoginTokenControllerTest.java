package com.lawu.eshop.cache.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.lawu.eshop.cache.srv.CacheSrvApplicationTest;
import com.lawu.eshop.cache.srv.EmbeddedRedis;
import com.lawu.eshop.cache.srv.service.LoginTokenService;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.framework.web.HttpCode;

/**
 * @author Leach
 * @date 2017/10/11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CacheSrvApplicationTest.class)
@WebAppConfiguration
public class LoginTokenControllerTest extends EmbeddedRedis {
    private MockMvc mvc;

    @Autowired
    private LoginTokenController loginTokenController;

    @Autowired
    private LoginTokenService loginTokenService;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(loginTokenController).build();
    }

    @Test
    public void setTokenOneToOne() throws Exception {

        Integer userLoginType = 1;
        String account = "11111111111";
        String token = "token11111111111";

        RequestBuilder request = put("/loginToken/setTokenOneToOne")
                .param("userLoginType", String.valueOf(userLoginType))
                .param("account", account)
                .param("token", token)
                .param("expireSeconds", "3600")
                .param("tokenClearType", "1");

        ResultActions perform = mvc.perform(request);

        perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(ResultCode.SUCCESS))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void setTokenOneToMany() throws Exception {

        Integer userLoginType = 1;
        String account = "22222222222";
        String token = "token22222222222";

        RequestBuilder request = put("/loginToken/setTokenOneToMany")
                .param("userLoginType", String.valueOf(userLoginType))
                .param("account", account)
                .param("token", token)
                .param("expireSeconds", "3600");

        ResultActions perform = mvc.perform(request);

        perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(ResultCode.SUCCESS))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAccount() throws Exception {

        Integer userLoginType = 1;
        String account = "33333333333";
        String token = "token33333333333";
        Integer tokenClearType = 1;
        loginTokenService.setTokenOneToOne(userLoginType, account, token, 3600, tokenClearType);

        RequestBuilder request = get("/loginToken/getAccount")
                .param("userLoginType", String.valueOf(userLoginType))
                .param("token", token)
                .param("flushExpireAfterOperation", Boolean.TRUE.toString())
                .param("expireSeconds", "3600")
                .param("singleTokenWithUser", Boolean.TRUE.toString())
                .param("tokenClearType", String.valueOf(tokenClearType));

        ResultActions perform = mvc.perform(request);

        perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_OK))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(ResultCode.SUCCESS))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model").value(account))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getTokenClearType() throws Exception {

        Integer userLoginType = 1;
        String account = "44444444444";
        String token = "token44444444444";
        Integer tokenClearType = 1;

        loginTokenService.setTokenOneToOne(userLoginType, account, token, 3600, tokenClearType);

        // 未删除前
        RequestBuilder request = get("/loginToken/getTokenClearType")
                .param("userLoginType", String.valueOf(userLoginType))
                .param("token", token);

        ResultActions perform = mvc.perform(request);

        perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_OK))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(ResultCode.SUCCESS))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model").value(0))
                .andDo(MockMvcResultHandlers.print());

        // 删除后
        loginTokenService.delRelationshipByAccount(userLoginType, account, 3600, tokenClearType);
        RequestBuilder request1 = get("/loginToken/getTokenClearType")
                .param("userLoginType", String.valueOf(userLoginType))
                .param("token", token);

        ResultActions perform1 = mvc.perform(request1);

        perform1.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_OK))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(ResultCode.SUCCESS))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model").value(tokenClearType))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void delRelationshipByAccount() throws Exception {

        Integer userLoginType = 1;
        String account = "55555555555";
        String token = "token55555555555";
        Integer tokenClearType = 1;
        loginTokenService.setTokenOneToOne(userLoginType, account, token, 3600, tokenClearType);

        RequestBuilder request = delete("/loginToken/delRelationshipByAccount")
                .param("userLoginType", String.valueOf(userLoginType))
                .param("account", account)
                .param("expireSeconds", "3600")
                .param("tokenClearType", String.valueOf(tokenClearType));

        ResultActions perform = mvc.perform(request);

        perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_NO_CONTENT))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void delRelationshipByToken() throws Exception {

        Integer userLoginType = 1;
        String account = "66666666666";
        String token = "token-66666666666";
        Integer tokenClearType = 1;
        loginTokenService.setTokenOneToOne(userLoginType, account, token, 3600, tokenClearType);

        RequestBuilder request = delete("/loginToken/delRelationshipByToken")
                .param("userLoginType", String.valueOf(userLoginType))
                .param("token", token)
                .param("singleTokenWithUser", Boolean.TRUE.toString())
                .param("expireSeconds", "3600")
                .param("tokenClearType", String.valueOf(tokenClearType));

        ResultActions perform = mvc.perform(request);

        perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_NO_CONTENT))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print());
    }

}
