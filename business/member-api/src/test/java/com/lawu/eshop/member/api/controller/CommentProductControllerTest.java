package com.lawu.eshop.member.api.controller;

import com.lawu.authorization.interceptor.AuthorizationInterceptor;
import com.lawu.eshop.mall.constants.CommentAnonymousEnum;
import com.lawu.eshop.mall.constants.CommentGradeEnum;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author yangqh
 * @date 2017/7/26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MemberApiApplicationTest.class)
@WebAppConfiguration
public class CommentProductControllerTest {

    private MockMvc mvc;

    @Autowired
    private CommentProductController commentProductController;

    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(commentProductController).addInterceptors(authorizationInterceptor).build();
    }

    @Test
    public void saveCommentProductInfo() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("content","烂东西");
        params.add("gradeEnum", CommentGradeEnum.COMMENT_STAR_LEVEL_FIVE.name());
        params.add("productId","1");
        params.add("shoppingOrderItemId","1");
        params.add("anonymousEnum", CommentAnonymousEnum.UN_COMMENT_ANONYMOUS.name());
        params.add("merchantId","1");
        params.add("productModelId","1");
        RequestBuilder request = post("/commentProduct/saveCommentProductInfo").header("authorization","").params(params);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void getCommentProducts() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("productId","1");
        params.add("currentPage","1");
        params.add("pageSize","10");
        RequestBuilder request = get("/commentProduct/getCommentProducts").header("authorization","").params(params);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void getCommentProductsWithImgs() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("productId","1");
        params.add("currentPage","1");
        params.add("pageSize","10");
        RequestBuilder request = get("/commentProduct/getCommentProductsWithImgs").header("authorization","").params(params);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void getCommentAvgGrade() {
        RequestBuilder request = get("/commentProduct/getCommentProductAvgGrade/1").param("productId","1");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void addCommentProductInfo() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("content","烂东西");
        params.add("gradeEnum", CommentGradeEnum.COMMENT_STAR_LEVEL_FIVE.name());
        params.add("productId","1");
        params.add("shoppingOrderItemId","1");
        params.add("anonymousEnum", CommentAnonymousEnum.UN_COMMENT_ANONYMOUS.name());
        params.add("merchantId","1");
        params.add("productModelId","1");
        RequestBuilder request = post("/commentProduct/addCommentProductInfo").header("authorization","").params(params);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

}
