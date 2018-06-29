/**
 * 
 */
package com.lawu.eshop.mall.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.assertj.core.util.Lists;
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
import com.gexin.fastjson.JSON;
import com.lawu.eshop.mall.dto.InformEnum;
import com.lawu.eshop.mall.dto.InformStatusEnum;
import com.lawu.eshop.mall.param.InformEditParam;
import com.lawu.eshop.mall.param.InformQueryParam;
import com.lawu.eshop.mall.param.InformSaveParam;
import com.lawu.eshop.mall.srv.MallSrvApplicationTest;
import com.lawu.framework.web.HttpCode;


/**
 * @author lihj
 * @date 2017年7月31日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MallSrvApplicationTest.class)
@WebAppConfiguration
public class InformControllerTest {

	private MockMvc mvc;
	
	@Autowired
	private InformController informController;
	
	private Logger log = Logger.getLogger(InformControllerTest.class);
	
	@Before
	public void setUp() throws Exception{
		mvc=MockMvcBuilders.standaloneSetup(informController).build();
	}
	
	/**
	 * 添加举报信息
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void addInform(){
		InformSaveParam info =initSaveData("1");
		RequestBuilder request =post("/inform/addInform").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(info));
		try{
			ResultActions preform = mvc.perform(request);
			log.info(preform.andReturn().getResponse().getContentAsString());
			MvcResult mvcResult = preform.andExpect(status().is(HttpCode.SC_CREATED))
					.andDo(MockMvcResultHandlers.print()).andReturn();
			Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
		}catch(Exception e){
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	/**
	 * 查询举报信息
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void selectInformList(){
		InformSaveParam save =initSaveData("1");
		InformSaveParam save1 =initSaveData("2");
		InformQueryParam info =initQueryParam();
		RequestBuilder requestsave =post("/inform/addInform").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(save));
		RequestBuilder requestsave1 =post("/inform/addInform").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(save1));
		RequestBuilder request =post("/inform/selectInformList").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(info));
		try{
			ResultActions preform = mvc.perform(requestsave);
			ResultActions preform1 = mvc.perform(requestsave1);
			ResultActions query = mvc.perform(request);
			log.info(query.andReturn().getResponse().getContentAsString());
			MvcResult mvcResult = query.andExpect(status().is(HttpCode.SC_CREATED))
					.andDo(MockMvcResultHandlers.print()).andReturn();
			Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
		}catch(Exception e){
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		
	}

	/**
	 * 运营后台修改处理举报信息
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void editInform(){
		InformSaveParam save =initSaveData("1");
		InformSaveParam save1 =initSaveData("2");
		InformQueryParam info =initQueryParam();
		InformQueryParam infoDown =initQueryParamDown();
		RequestBuilder requestsave =post("/inform/addInform").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(save));
		RequestBuilder requestsave1 =post("/inform/addInform").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(save1));
		RequestBuilder request =post("/inform/selectInformList").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(info));
		try{
			ResultActions preform = mvc.perform(requestsave);
			ResultActions preform1 = mvc.perform(requestsave1);
			ResultActions query = mvc.perform(request);
			String str = query.andReturn().getResponse().getContentAsString();
			log.info(str);
			List<String> list = forJson(str);
			for (int i = 0; i < list.size(); i++) {
				InformEditParam edit =initInformEditParam(list.get(i));
				RequestBuilder editReq =post("/inform/editInform").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(edit));
				ResultActions editPre = mvc.perform(editReq);
				log.info(editPre.andReturn().getResponse().getContentAsString());
				MvcResult mvcResult = editPre.andExpect(status().is(HttpCode.SC_CREATED))
						.andDo(MockMvcResultHandlers.print()).andReturn();
				Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
			}
			RequestBuilder requestdown =post("/inform/selectInformList").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(infoDown));
			ResultActions queryDown = mvc.perform(requestdown);
			log.info(queryDown.andReturn().getResponse().getContentAsString());
		}catch(Exception e){
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	private List<String> forJson(String str) {
		List<String> listids =Lists.newArrayList();
		JSONObject json =JSONObject.parseObject(str);
		JSONObject model =(JSONObject)json.get("model");
		List<JSONObject> list=(List<JSONObject>) model.get("records");
		for (int i = 0; i < list.size(); i++) {
			JSONObject obj =list.get(i);
			listids.add(obj.getString("id"));
		}
		return listids;
	}
	/**
	 * @return
	 */
	private InformEditParam initInformEditParam(String id) {
		InformEditParam param =new InformEditParam();
		param.setAuditorId(1);
		param.setAuditorName("admin");
		param.setAuditTime(new Date());
		param.setGmtModified(new Date());
		param.setId(Long.valueOf(id));
		param.setRemark("违规下架了");
		param.setStatus(InformStatusEnum.INFORM_ALREADY_PROCESSED.getVal());
		return param;
	}

	/**
	 * @return
	 */
	private InformQueryParam initQueryParam() {
		InformQueryParam param =new InformQueryParam();
		param.setCurrentPage(1);
		param.setPageSize(10);
		param.setInformType(InformEnum.INFORM_TYPE_MERCHANT);
		param.setStatus(InformStatusEnum.INFORM_PENDING);
		return param;
	}
	/**
	 * @return
	 */
	private InformQueryParam initQueryParamDown() {
		InformQueryParam param =new InformQueryParam();
		param.setCurrentPage(1);
		param.setPageSize(10);
		param.setInformType(InformEnum.INFORM_TYPE_MERCHANT);
		param.setStatus(InformStatusEnum.INFORM_ALREADY_PROCESSED);
		return param;
	}

	/**
	 * @return
	 */
	private InformSaveParam initSaveData(String str) {
		InformSaveParam param =new InformSaveParam();
		param.setContent("content".concat(str));
		param.setGmtCreate(new Date());
		param.setGmtModified(new Date());
		param.setInformerAccount("15013627266".concat(str));
		param.setInformerUserNum("M0000001".concat(str));
		param.setInformtItemId(1L);
		param.setInformtItemName("informtItemName".concat(str));
		param.setInformType(InformEnum.INFORM_TYPE_MERCHANT.getVal());
		param.setStatus(InformStatusEnum.INFORM_PENDING.getVal());
		return param;
	}
	
}
