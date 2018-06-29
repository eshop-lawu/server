package com.lawu.eshop.mall.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.lawu.eshop.common.constants.MessageTypeEnum;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.mall.constants.MessageStatusEnum;
import com.lawu.eshop.mall.param.MessageInfoParam;
import com.lawu.eshop.mall.param.MessageParam;
import com.lawu.eshop.mall.param.MessageQueryParam;
import com.lawu.eshop.mall.param.MessageTempParam;
import com.lawu.eshop.mall.param.OperatorMessageInfoParam;
import com.lawu.eshop.mall.param.OperatorMessageParam;
import com.lawu.eshop.mall.param.PushParam;
import com.lawu.eshop.mall.srv.MallSrvApplicationTest;
import com.lawu.eshop.mall.srv.domain.MessageDO;
import com.lawu.eshop.mall.srv.mapper.MessageDOMapper;
import com.lawu.framework.web.HttpCode;

/**
 * @author zhangyong
 * @date 2017/7/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MallSrvApplicationTest.class)
@WebAppConfiguration
public class MessageControllerTest {
    private MockMvc mvc;

    @Autowired
    private MessageController messageController;

    @Autowired
    private MessageDOMapper messageDOMapper;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(messageController).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getMessageStatistics(){
        RequestBuilder request = null;
        ResultActions perform = null;
        request = get("/message/getMessageStatistics/1");
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        MessageDO messageDO = new MessageDO();
        messageDO.setGmtCreate(new Date());
        messageDO.setUserNum("M0001");
        messageDO.setStatus(MessageStatusEnum.MESSAGE_STATUS_UNREAD.val);
        messageDO.setContent("test");
        messageDOMapper.insert(messageDO);
        request = get("/message/getMessageStatistics/M0001");
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
    public void getMessageList(){
        RequestBuilder request = null;
        ResultActions perform = null;
        MessageParam param = new MessageParam();
        param.setCurrentPage(1);
        param.setPageSize(10);
        String requestJson = JSONObject.toJSONString(param);
        request = post("/message/getMessageList/1").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        MessageDO messageDO = new MessageDO();
        messageDO.setGmtCreate(new Date());
        messageDO.setUserNum("M0001");
        messageDO.setStatus(MessageStatusEnum.MESSAGE_STATUS_UNREAD.val);
        messageDO.setContent("test");
        messageDOMapper.insert(messageDO);
        request = post("/message/getMessageList/M0001").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void updateMessageStatus(){
        RequestBuilder request = null;
        ResultActions perform = null;
        request = put("/message/updateMessageStatus/1").param("userNum","userNum");
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
    public void delMessageStatus(){
        RequestBuilder request = null;
        ResultActions perform = null;
        request = delete("/message/delMessageStatus/1").param("userNum","userNum");
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
    public void saveMessage(){
        MessageInfoParam param = new MessageInfoParam();
        MessageTempParam tempParam;
        param.setRelateId(1L);
        // 0,12
        param.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_INVITE_FANS);
        tempParam = new MessageTempParam();
        tempParam.setUserName("test");
        tempParam.setMerchantName("merchant");
        param.setMessageParam(tempParam);
        RequestBuilder request = null;
        ResultActions perform = null;
        String requestJson = JSONObject.toJSONString(param);
        request = post("/message/saveMessage/1").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void saveMessageOperator(){
        RequestBuilder request = null;
        ResultActions perform = null;
        OperatorMessageInfoParam param = new OperatorMessageInfoParam();
        param.setContent("test");
        param.setTitle("title");
        param.setMoblie("123");
        param.setUserType(UserTypeEnum.MEMBER);
        String data = JSONObject.toJSONString(param);
        request = post("/message/saveMessageOperator/userNum").contentType(MediaType.APPLICATION_JSON).content(data);
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
    public void saveMessageToAll(){
        RequestBuilder request = null;
        ResultActions perform = null;
        OperatorMessageParam param = new OperatorMessageParam();
        param.setContent("test");
        param.setTitle("title");
        param.setUserTypeEnum(UserTypeEnum.MEMBER);
        List<PushParam> list = new ArrayList<>();
        PushParam param1 = new PushParam();
        param1.setGtCid("123456");
        param1.setUserNum("123456");
        list.add(param1);
        param.setParams(list);
        String data = JSONObject.toJSONString(param);
        request = post("/message/saveMessageToAll").contentType(MediaType.APPLICATION_JSON).content(data);
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
    public void getOperatorMessageList(){
        RequestBuilder request = null;
        ResultActions perform = null;
        MessageQueryParam param = new MessageQueryParam();
        param.setCurrentPage(1);
        param.setPageSize(10);
        String data = JSONObject.toJSONString(param);
        request = post("/message/getOperatorMessageList").contentType(MediaType.APPLICATION_JSON).content(data);
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        MessageDO messageDO = new MessageDO();
        messageDO.setGmtCreate(new Date());
        messageDO.setUserNum("M0001");
        messageDO.setStatus(MessageStatusEnum.MESSAGE_STATUS_UNREAD.val);
        messageDO.setContent("test");
        messageDOMapper.insert(messageDO);

        request = post("/message/getOperatorMessageList").contentType(MediaType.APPLICATION_JSON).content(data);
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
    public  void delMessageByIds(){
        RequestBuilder request = null;
        ResultActions perform = null;
        request = delete("/message/delMessageByIds").param("ids","1").param("userNum","userNum");
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
    public void selectMessageById(){
        RequestBuilder request = null;
        ResultActions perform = null;
        request = get("/message/selectMessageById/1");
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
