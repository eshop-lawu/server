package com.lawu.eshop.user.srv.controller;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.user.param.CollectionUserRegParam;
import com.lawu.eshop.user.param.WeixinUserBindParam;
import com.lawu.eshop.user.param.WeixinUserParam;
import com.lawu.eshop.user.srv.UserSrvApplicationTest;
import com.lawu.framework.web.HttpCode;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserSrvApplicationTest.class)
@WebAppConfiguration
public class WeixinUserControllerTest {

	private MockMvc mvc;

	@Autowired
	private WeixinUserController weixinUserController;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(weixinUserController).build();
	}
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getWeixinUserInfo() {
      
        RequestBuilder request = get("/weixinUser/getWeixinUserInfo").param("userNum", "M944153045875294208");
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
    public void getWeixinUserIsBind() {
      
        RequestBuilder request = get("/weixinUser/getWeixinUserIsBind").param("openId", "oQ1GBwLhd_rvVEL830obbJHhdHLQ");
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
    public void weixinUserBind() {
		WeixinUserBindParam bindParam = new WeixinUserBindParam();
		bindParam.setAccount("18576620612");
		bindParam.setPwd("123456");
		bindParam.setOpenId("oQ1GBwLhd_rvVEL830obbJHhdHLQ");
		String requestJson = JSONObject.toJSONString(bindParam);
        RequestBuilder request = post("/weixinUser/weixinUserBind").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void saveWeixinUser() {
		WeixinUserParam param = new WeixinUserParam();
		param.setOpenid("oQ1GBwLhd_rvVEL830obbJHhdHLQ");
		param.setNickname("E店用户");
		param.setSex(1);
		param.setCity("");
		param.setProvince("安道尔");
		param.setCountry("");
		param.setHeadimgurl("");
		param.setLanguage("zh_CN");
		param.setUnionid("ouYw4wcSrGkFXtaVtWAfETkdnYZo");
		param.setGroupid(0l);
		String requestJson = JSONObject.toJSONString(param);
		
        RequestBuilder request = post("/weixinUser/saveWeixinUser").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

}
