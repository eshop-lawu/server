package com.lawu.eshop.user.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.lawu.eshop.user.param.QqLoginMemberParam;
import com.lawu.eshop.user.srv.UserSrvApplicationTest;
import com.lawu.framework.web.HttpCode;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserSrvApplicationTest.class)
@WebAppConfiguration
public class QqLoginMemberControllerTest {

	private MockMvc mvc;

	@Autowired
	private QqLoginMemberController qqLoginMemberController;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(qqLoginMemberController).build();
	}
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void qqLogin() {
      
        RequestBuilder request = get("/qqLoginMember/qqLogin").param("openId", "oQ1GBwLhd_rvVEL830obbJHhdHLQ");
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
    public void qqMemberBind() {
      
        RequestBuilder request = post("/qqLoginMember/qqMemberBind").param("openId", "oQ1GBwLhd_rvVEL830obbJHhdHLQ").param("account", "18576620613").param("imgUrl", "head/img.jpg");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void qqLoginMemberSave() {
		QqLoginMemberParam bindParam = new QqLoginMemberParam();
		bindParam.setFigureurl("http://q.qlogo.cn/qqapp/1106412506/E3C40482C9C1102C80F91E12FFEDDD14/40");
		bindParam.setGender("男");
		bindParam.setOpenId("oQ1GBwLhd_rvVEL830obbJHhdHLQ");
		bindParam.setNickname("E店用户");
		String requestJson = JSONObject.toJSONString(bindParam);
        RequestBuilder request = post("/qqLoginMember/qqLoginMemberSave").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
	
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getHeadImg() {
		
        RequestBuilder request = get("/qqLoginMember/getHeadImg").param("openId", "oQ1GBwLhd_rvVEL830obbJHhdHLQ");
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
