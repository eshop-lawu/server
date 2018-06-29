package com.lawu.eshop.mall.srv.controller;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.mall.constants.CommentAnonymousEnum;
import com.lawu.eshop.mall.constants.CommentGradeEnum;
import com.lawu.eshop.mall.constants.CommentStatusEnum;
import com.lawu.eshop.mall.param.CommentListParam;
import com.lawu.eshop.mall.param.CommentMerchantListParam;
import com.lawu.eshop.mall.param.CommentMerchantParam;
import com.lawu.eshop.mall.param.PayOrderAutoCommentParam;
import com.lawu.eshop.mall.srv.MallSrvApplicationTest;
import com.lawu.eshop.mall.srv.domain.CommentMerchantDO;
import com.lawu.eshop.mall.srv.mapper.CommentMerchantDOMapper;
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

import java.math.BigDecimal;
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
public class CommentMerchantControllerTest {

    private MockMvc mvc;

    @Autowired
    private CommentMerchantController commentMerchantController;

    @Autowired
    private CommentMerchantDOMapper commentMerchantDOMapper;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(commentMerchantController).build();
    }


    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveCommentMerchantInfo()  {
        CommentMerchantParam param = new CommentMerchantParam();
        param.setMerchantId(1L);
        param.setAnonymousEnum(CommentAnonymousEnum.COMMENT_ANONYMOUS);
        param.setAvgSpend(BigDecimal.valueOf(10L));
        param.setContent("test");
        param.setGradeEnum(CommentGradeEnum.COMMENT_STAR_LEVEL_FIVE);
        param.setOrderId(3L);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/commentMerchant/saveCommentMerchantInfo/1")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson)
                .param("commentPic", "comment/1494898117327258307.png");

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
    public void getCommentMerchantAllList(){
        CommentMerchantListParam param = new CommentMerchantListParam();
        param.setCurrentPage(1);
        param.setPageSize(10);
        param.setMerchantId(1L);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/commentMerchant/getCommentMerchantAllList")
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
    public void getCommentMerchantListWithImgs(){


        CommentMerchantListParam param = new CommentMerchantListParam();
        param.setCurrentPage(1);
        param.setPageSize(10);
        param.setMerchantId(1L);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/commentMerchant/getCommentMerchantListWithImgs")
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
    public void replyMerchantComment(){
        RequestBuilder request = null;
        request = put("/commentMerchant/replyMerchantComment/1")
                .param("replyContent","test").param("merchantId","1");

        ResultActions perform = null;
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        CommentMerchantDO commentMerchantDO ;
        commentMerchantDO = new CommentMerchantDO();
        commentMerchantDO.setGmtCreate(new Date());
        commentMerchantDO.setMerchantId(1L);
        commentMerchantDO.setPayOrderId(1L);
        commentMerchantDO.setAvgSpend(BigDecimal.TEN);
        commentMerchantDO.setContent("test");
        commentMerchantDO.setGrade(CommentGradeEnum.COMMENT_STAR_LEVEL_FIVE.val);
        commentMerchantDO.setStatus(CommentStatusEnum.COMMENT_STATUS_VALID.val);
        commentMerchantDO.setIsAnonymous(true);
        commentMerchantDOMapper.insert(commentMerchantDO);

         request = put("/commentMerchant/replyMerchantComment/"+commentMerchantDO.getId())
                .param("replyContent","test").param("merchantId","1");

        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }


        commentMerchantDO.setGrade(CommentGradeEnum.COMMENT_STAR_LEVEL_FIVE.val);
        commentMerchantDO.setStatus(CommentStatusEnum.COMMENT_STATUS_VALID.val);
        commentMerchantDO.setIsAnonymous(true);
        commentMerchantDOMapper.insert(commentMerchantDO);

        request = put("/commentMerchant/replyMerchantComment/"+commentMerchantDO.getId())
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
    public void getCommentAvgGrade(){
        RequestBuilder request = null;
        ResultActions perform = null;
        CommentMerchantDO commentMerchantDO = new CommentMerchantDO();
        commentMerchantDO.setGmtCreate(new Date());
        commentMerchantDO.setMerchantId(1L);
        commentMerchantDO.setPayOrderId(1L);
        commentMerchantDO.setAvgSpend(BigDecimal.TEN);
        commentMerchantDO.setContent("test");
        commentMerchantDO.setReplyContent("test");
        commentMerchantDO.setGrade(CommentGradeEnum.COMMENT_STAR_LEVEL_FIVE.val);
        commentMerchantDO.setStatus(CommentStatusEnum.COMMENT_STATUS_VALID.val);
        commentMerchantDO.setIsAnonymous(true);
        commentMerchantDOMapper.insert(commentMerchantDO);
        request = get("/commentMerchant/getCommentAvgGrade/1");
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
    public void getCommentMerchantListOperator(){
        CommentListParam param = new CommentListParam();
        param.setCurrentPage(1);
        param.setPageSize(10);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/commentMerchant/getCommentMerchantListOperator/")
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
    public void delCommentMerchantInfo(){
        CommentMerchantDO commentMerchantDO = new CommentMerchantDO();
        commentMerchantDO.setGmtCreate(new Date());
        commentMerchantDO.setMerchantId(1L);
        commentMerchantDO.setPayOrderId(1L);
        commentMerchantDO.setAvgSpend(BigDecimal.TEN);
        commentMerchantDO.setContent("test");
        commentMerchantDO.setReplyContent("test");
        commentMerchantDO.setGrade(CommentGradeEnum.COMMENT_STAR_LEVEL_FIVE.val);
        commentMerchantDO.setStatus(CommentStatusEnum.COMMENT_STATUS_VALID.val);
        commentMerchantDO.setIsAnonymous(true);
        commentMerchantDOMapper.insert(commentMerchantDO);
        RequestBuilder request = delete("/commentMerchant/delCommentMerchantInfo/"+commentMerchantDO.getId());

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
    public void getGradeByOrderId(){
        CommentMerchantDO commentMerchantDO = new CommentMerchantDO();
        commentMerchantDO.setGmtCreate(new Date());
        commentMerchantDO.setMerchantId(1L);
        commentMerchantDO.setPayOrderId(1L);
        commentMerchantDO.setMemberId(1L);
        commentMerchantDO.setAvgSpend(BigDecimal.TEN);
        commentMerchantDO.setContent("test");
        commentMerchantDO.setReplyContent("test");
        commentMerchantDO.setGrade(CommentGradeEnum.COMMENT_STAR_LEVEL_FIVE.val);
        commentMerchantDO.setStatus(CommentStatusEnum.COMMENT_STATUS_VALID.val);
        commentMerchantDO.setIsAnonymous(true);
        commentMerchantDOMapper.insert(commentMerchantDO);
        RequestBuilder request = get("/commentMerchant/getGradeByOrderId").param("memberId","1").param("id",""+commentMerchantDO.getId());

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
    public void payOrderAutoComment(){
        PayOrderAutoCommentParam param = new PayOrderAutoCommentParam();
        param.setPayOrderId(1L);
        param.setAvgSpend(BigDecimal.TEN);
        param.setMerchantId(1L);
        param.setMemberId(1L);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/commentMerchant/payOrderAutoComment")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson);
        ResultActions perform = null;
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
