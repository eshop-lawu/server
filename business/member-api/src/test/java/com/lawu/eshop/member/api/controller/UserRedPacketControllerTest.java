/**
 * 
 */
package com.lawu.eshop.member.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.lawu.authorization.interceptor.AuthorizationInterceptor;
import com.lawu.eshop.member.api.MemberApiApplicationTest;
import com.lawu.framework.web.HttpCode;

/**
 * @author lihj
 * @date 2017年8月8日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MemberApiApplicationTest.class)
@WebAppConfiguration
public class UserRedPacketControllerTest {

	private MockMvc mvc;

	@Autowired
	private UserRedPacketController userRedPacketController;
	
    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;
	
	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(userRedPacketController).addInterceptors(authorizationInterceptor)
				.build();
	}

	@Test
	public void addUserRedPacket() {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("redPacketPutWayEnum", "PUT_WAY_COMMON");
		params.add("totalCount","1");
		params.add("totalMoney","10");
		RequestBuilder request = post("/userRedPacket/addUserRedPacket").header("authorization","").params(params);
		try {
			ResultActions perform = mvc.perform(request);
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void selectUserRedPacketList() {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("currentPage", "1");
		params.add("pageSize","10");
		RequestBuilder request = get("/userRedPacket/selectUserRedPacketList").header("authorization","").params(params);
		try {
			ResultActions perform = mvc.perform(request);
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void getUserRedpacketMaxMoney() {
		RequestBuilder request = post("/userRedPacket/getUserRedpacketMaxMoney").param("redPacketId","1").param("memberId","1");
		try {
			ResultActions perform = mvc.perform(request);
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void getUserRedpacketMoney() {
		RequestBuilder request = post("/userRedPacket/getUserRedpacketMoney").param("phoneNumber","1").param("redPacketId","1");
		try {
			ResultActions perform = mvc.perform(request);
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
}
