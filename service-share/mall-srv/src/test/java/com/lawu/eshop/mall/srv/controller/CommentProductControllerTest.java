package com.lawu.eshop.mall.srv.controller;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.mall.constants.CommentAnonymousEnum;
import com.lawu.eshop.mall.constants.CommentGradeEnum;
import com.lawu.eshop.mall.constants.CommentStatusEnum;
import com.lawu.eshop.mall.param.CommentListParam;
import com.lawu.eshop.mall.param.CommentMerchantListParam;
import com.lawu.eshop.mall.param.CommentProductListParam;
import com.lawu.eshop.mall.param.CommentProductParam;
import com.lawu.eshop.mall.srv.MallSrvApplicationTest;
import com.lawu.eshop.mall.srv.domain.CommentProductDO;
import com.lawu.eshop.mall.srv.mapper.CommentProductDOMapper;
import com.lawu.framework.web.HttpCode;

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

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author zhangyong
 * @date 2017/7/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MallSrvApplicationTest.class)
@WebAppConfiguration
public class CommentProductControllerTest {
    private MockMvc mvc;

    @Autowired
    private  CommentProductController commentProductController;

    @Autowired
    private CommentProductDOMapper commentProductDOMapper;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(commentProductController).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveCommentProductInfo(){
        CommentProductParam param = new CommentProductParam();
        param.setContent("test");
        param.setAnonymousEnum(CommentAnonymousEnum.COMMENT_ANONYMOUS);
        param.setMerchantId(1L);
        param.setGradeEnum(CommentGradeEnum.COMMENT_STAR_LEVEL_FOUR);
        param.setProductId(1L);
        param.setProductModelId(1L);
        param.setShoppingOrderItemId(1L);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/commentProduct/saveCommentProductInfo/1")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson)
                .param("headImg", "comment/1494898117327258307.png");

        ResultActions perform = null;
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getCommentProducts(){
        CommentProductListParam param = new CommentProductListParam();
        param.setCurrentPage(1);
        param.setCurrentPage(10);
        param.setProductId(1L);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/commentProduct/getCommentProducts")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson);

        ResultActions perform = null;
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getCommentProductsWithImgs(){
        CommentProductListParam param = new CommentProductListParam();
        param.setProductId(1L);
        param.setCurrentPage(1);
        param.setPageSize(10);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/commentProduct/getCommentProductsWithImgs")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson);

        ResultActions perform = null;
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void replyProductComment(){
        RequestBuilder request = null;
        request = put("/commentProduct/replyProductComment/1")
                .param("replyContent","test").param("merchantId","1");

        ResultActions perform = null;
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }

        CommentProductDO commentProductDO = new CommentProductDO();
        commentProductDO.setMemberId(1L);
        commentProductDO.setGmtCreate(new Date());
        commentProductDO.setMerchantId(1L);
        commentProductDO.setProductId(1L);
        commentProductDO.setOrderItemId(1L);
        commentProductDO.setContent("test");
        commentProductDO.setGrade(CommentGradeEnum.COMMENT_STAR_LEVEL_FIVE.val);
        commentProductDO.setIsAnonymous(true);
        commentProductDO.setStatus(CommentStatusEnum.COMMENT_STATUS_VALID.val);
        commentProductDO.setProductModelId(1L);
        commentProductDOMapper.insert(commentProductDO);

        request = put("/commentProduct/replyProductComment/"+commentProductDO.getId())
                .param("replyContent","test").param("merchantId","1");

        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }


        commentProductDO.setGrade(CommentGradeEnum.COMMENT_STAR_LEVEL_FIVE.val);
        commentProductDO.setStatus(CommentStatusEnum.COMMENT_STATUS_VALID.val);
        commentProductDO.setReplyContent("test");
        commentProductDO.setIsAnonymous(true);
        commentProductDOMapper.insert(commentProductDO);

        request = put("/commentProduct/replyProductComment/"+commentProductDO.getId())
                .param("replyContent","test").param("merchantId","1");

        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void delCommentProductInfo(){

        RequestBuilder request  = delete("/commentProduct/delCommentProductInfo/1");

        ResultActions perform = null;
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_NO_CONTENT)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getCommentAvgGrade(){
        RequestBuilder request = null;
        request  = get("/commentProduct/getCommentAvgGrade/1");
        ResultActions perform = null;
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }

        CommentProductDO commentProductDO = new CommentProductDO();
        commentProductDO.setMemberId(1L);
        commentProductDO.setGmtCreate(new Date());
        commentProductDO.setMerchantId(1L);
        commentProductDO.setProductId(1L);
        commentProductDO.setOrderItemId(1L);
        commentProductDO.setContent("test");
        commentProductDO.setGrade(CommentGradeEnum.COMMENT_STAR_LEVEL_FIVE.val);
        commentProductDO.setIsAnonymous(true);
        commentProductDO.setStatus(CommentStatusEnum.COMMENT_STATUS_VALID.val);
        commentProductDO.setProductModelId(1L);
        commentProductDOMapper.insert(commentProductDO);

        request  = get("/commentProduct/getCommentAvgGrade/1");
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getCommentProductListOperator(){
        CommentListParam param = new CommentListParam();
        param.setCurrentPage(1);
        param.setPageSize(10);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = null;
        request  = post("/commentProduct/getCommentProductListOperator").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        ResultActions perform = null;
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getProductCommentListByMerchantId(){
        CommentMerchantListParam param = new CommentMerchantListParam();
        param.setMerchantId(1L);
        param.setPageSize(10);
        param.setCurrentPage(1);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = null;
        request  = post("/commentProduct/getProductCommentListByMerchantId").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        ResultActions perform = null;
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getProductCommentIdsByMerchantId(){
        CommentMerchantListParam param = new CommentMerchantListParam();
        param.setMerchantId(1L);
        param.setCurrentPage(1);
        param.setPageSize(10);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = null;
        request  = post("/commentProduct/getProductCommentIdsByMerchantId").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        ResultActions perform = null;
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }

        CommentProductDO commentProductDO = new CommentProductDO();
        commentProductDO.setContent("test");
        commentProductDO.setIsAnonymous(CommentAnonymousEnum.COMMENT_ANONYMOUS.val);
        commentProductDO.setMerchantId(1L);
        commentProductDO.setGrade(CommentGradeEnum.COMMENT_STAR_LEVEL_FOUR.val);
        commentProductDO.setProductId(1L);
        commentProductDO.setProductModelId(1L);
        commentProductDO.setOrderItemId(1L);
        commentProductDO.setStatus((byte) 0x01);
        commentProductDOMapper.insert(commentProductDO);

        request  = post("/commentProduct/getProductCommentIdsByMerchantId").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void delCommentByProductModelId(){

        RequestBuilder request = null;
        request  = delete("/commentProduct/delCommentByProductModelId").param("productModelId","1");
        ResultActions perform = null;
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_NO_CONTENT)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void delCommentByOrderItemId(){
        RequestBuilder request = null;
        request  = delete("/commentProduct/delCommentByOrderItemId").param("orderItemId","1");
        ResultActions perform = null;
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_NO_CONTENT)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void geNewlyProductComment(){
        RequestBuilder request = null;
        request  = get("/commentProduct/geNewlyProductComment").param("productId","1");
        ResultActions perform = null;
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getProductCommentCount(){
        RequestBuilder request = null;
        request  = get("/commentProduct/getProductCommentCount").param("productId","1");
        ResultActions perform = null;
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
