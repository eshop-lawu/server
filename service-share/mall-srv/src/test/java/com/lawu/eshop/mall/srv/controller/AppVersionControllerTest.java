package com.lawu.eshop.mall.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

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
import com.lawu.eshop.mall.constants.AppStatusEnum;
import com.lawu.eshop.mall.constants.AppTypeEnum;
import com.lawu.eshop.mall.constants.MobileTypeEnum;
import com.lawu.eshop.mall.param.AppVersionOperatorParam;
import com.lawu.eshop.mall.param.AppVersionParam;
import com.lawu.eshop.mall.srv.MallSrvApplicationTest;
import com.lawu.eshop.mall.srv.domain.AppVersionDO;
import com.lawu.eshop.mall.srv.mapper.AppVersionDOMapper;
import com.lawu.framework.web.HttpCode;

/**
 * app版本管理
 * 
 * @author zhangrc
 * @date 2017/09/26
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MallSrvApplicationTest.class)
@WebAppConfiguration
public class AppVersionControllerTest {
	
	private MockMvc mvc;

	@Autowired
	private AppVersionController appVersionController;

	@Autowired
	private AppVersionDOMapper appVersionDOMapper;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(appVersionController).build();
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void saveAppVersion() {
		AppVersionParam param = new AppVersionParam();
		param.setAppBigVersion("2.4.18.t");
		param.setAppType(AppTypeEnum.MEMBER);
		param.setAppBigVersion("2");
		param.setIsForce(false);
		param.setMobileType(MobileTypeEnum.Android);
		param.setUpdateDesc("更新内容");
		String requestJson = JSONObject.toJSONString(param);
		
		RequestBuilder request = post("/appVersion/saveAppVersion/")
				.contentType(MediaType.APPLICATION_JSON).content(requestJson);

		ResultActions perform = null;
		try {
			perform = mvc.perform(request);
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED))
					.andDo(MockMvcResultHandlers.print()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getAppVersion() {
		AppVersionDO appVersion = new AppVersionDO();
		appVersion.setAppBigVersion("2");
		appVersion.setAppType(AppTypeEnum.MEMBER.val);
		appVersion.setAppVersion("2.4.18.t");
		appVersion.setIsForce(false);
		appVersion.setPlatform(MobileTypeEnum.Android.val);
		appVersion.setStatus(AppStatusEnum.ENABLE.val);
		appVersion.setUpdateDesc("更新内容");
		appVersion.setGmtCreate(new Date());
		
		appVersionDOMapper.insert(appVersion);
		
		RequestBuilder request = get("/appVersion/getAppVersion/"+appVersion.getAppType()).param("mobileType", String.valueOf(appVersion.getPlatform()));

		ResultActions perform = null;
		try { 
			perform = mvc.perform(request);
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED))
					.andDo(MockMvcResultHandlers.print()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void updateAppVersion() {
		AppVersionDO appVersion = new AppVersionDO();
		appVersion.setAppBigVersion("2");
		appVersion.setAppType(AppTypeEnum.MEMBER.val);
		appVersion.setAppVersion("2.4.18.t");
		appVersion.setIsForce(false);
		appVersion.setPlatform(MobileTypeEnum.Android.val);
		appVersion.setStatus(AppStatusEnum.ENABLE.val);
		appVersion.setUpdateDesc("更新内容");
		appVersion.setGmtCreate(new Date());
		
		appVersionDOMapper.insert(appVersion);
		 
		RequestBuilder request = put("/appVersion/updateAppVersion/"+appVersion.getId()).param("statusEnum", "DISENABLE");

		ResultActions perform = null;
		try {
			perform = mvc.perform(request);
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED))
					.andDo(MockMvcResultHandlers.print()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getVersionOperator() {
		AppVersionDO appVersion = new AppVersionDO();
		appVersion.setAppBigVersion("2");
		appVersion.setAppType(AppTypeEnum.MEMBER.val);
		appVersion.setAppVersion("2.4.18.t");
		appVersion.setIsForce(false);
		appVersion.setPlatform(MobileTypeEnum.Android.val);
		appVersion.setStatus(AppStatusEnum.ENABLE.val);
		appVersion.setUpdateDesc("更新内容");
		appVersion.setGmtCreate(new Date());
		
		appVersionDOMapper.insert(appVersion);
		
		AppVersionOperatorParam query = new AppVersionOperatorParam();
		query.setCurrentPage(1);
		query.setPageSize(10);
		String requestJson = JSONObject.toJSONString(query);
		RequestBuilder request = post("/appVersion/getVersionOperator") .contentType(MediaType.APPLICATION_JSON).content(requestJson);;

		ResultActions perform = null;
		try {
			perform = mvc.perform(request);
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK))
					.andDo(MockMvcResultHandlers.print()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
