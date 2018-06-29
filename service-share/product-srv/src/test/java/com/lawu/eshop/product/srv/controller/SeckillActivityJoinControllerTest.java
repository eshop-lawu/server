package com.lawu.eshop.product.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
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

import com.alibaba.fastjson.JSON;
import com.lawu.eshop.common.constants.MemberGradeEnum;
import com.lawu.eshop.product.constant.ActivityStatusEnum;
import com.lawu.eshop.product.param.JoinSeckillActivityParam;
import com.lawu.eshop.product.param.ModelParam;
import com.lawu.eshop.product.param.SeckillActivityJoinParam;
import com.lawu.eshop.product.param.SeckillActivityManageParam;
import com.lawu.eshop.product.srv.ProductSrvApplicationTest;
import com.lawu.eshop.product.srv.domain.SeckillActivityDO;
import com.lawu.eshop.product.srv.mapper.SeckillActivityDOMapper;
import com.lawu.framework.web.HttpCode;

/**
 * 
 * @Description 商家活动单元测试
 * @author zhangrc
 * @date 2017年11月28日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductSrvApplicationTest.class)
@WebAppConfiguration
public class SeckillActivityJoinControllerTest {
	
	
	private MockMvc mvc;
	
	private Logger log=Logger.getLogger(SeckillActivityJoinControllerTest.class);
	
	@Autowired
	private SeckillActivityJoinController seckillActivityJoinController;
	
	@Autowired
	private SeckillActivityDOMapper seckillActivityDOMapper;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(seckillActivityJoinController).build();
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void queryPage() {
		SeckillActivityJoinParam param = new SeckillActivityJoinParam();
		param.setCurrentPage(1);
		param.setPageSize(20);
		
		RequestBuilder request = post("/seckillActivityJoin/queryPage").contentType(MediaType.APPLICATION_JSON)
				.content(JSON.toJSONString(param));
		try {
			ResultActions perform = mvc.perform(request);
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK))
					.andDo(MockMvcResultHandlers.print()).andReturn();
			Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void queryManagePage() {
		SeckillActivityManageParam param = new SeckillActivityManageParam();
		param.setCurrentPage(1);
		param.setPageSize(20);
		param.setMerchantId(100l);
		
		RequestBuilder request = post("/seckillActivityJoin/queryManagePage").contentType(MediaType.APPLICATION_JSON)
				.content(JSON.toJSONString(param));
		try {
			ResultActions perform = mvc.perform(request);
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK))
					.andDo(MockMvcResultHandlers.print()).andReturn();
			Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void querySeckillActivityDetail() {
		
		SeckillActivityDO record = new SeckillActivityDO();
		record.setActivityStatus(ActivityStatusEnum.NOT_STARTED.getValue());
		record.setEndDate(new Date());
		record.setGmtCreate(new Date());
		record.setIsRemind(false);
		record.setMemberLevel(MemberGradeEnum.GOLD.getVal());
		record.setName("抢购活动");
		record.setProductValidCount(20);
		record.setSellingPrice(BigDecimal.valueOf(9.9));
		record.setStartDate(new Date());
		record.setStatus(Byte.valueOf("1"));
		seckillActivityDOMapper.insertSelective(record);
		
		RequestBuilder request = get("/seckillActivityJoin/querySeckillActivityDetail/"+record.getId()).param("merchantId", "1000");
		try {
			ResultActions perform = mvc.perform(request);
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK))
					.andDo(MockMvcResultHandlers.print()).andReturn();
			Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void querySeckillActivityManageDetail() {
		
		SeckillActivityDO record = new SeckillActivityDO();
		record.setActivityStatus(ActivityStatusEnum.NOT_STARTED.getValue());
		record.setEndDate(new Date());
		record.setGmtCreate(new Date());
		record.setIsRemind(false);
		record.setMemberLevel(MemberGradeEnum.GOLD.getVal());
		record.setName("抢购活动");
		record.setProductValidCount(20);
		record.setSellingPrice(BigDecimal.valueOf(9.9));
		record.setStartDate(new Date());
		record.setStatus(Byte.valueOf("1"));
		seckillActivityDOMapper.insertSelective(record);
		
		RequestBuilder request = get("/seckillActivityJoin/querySeckillActivityManageDetail/"+record.getId()).param("merchantId", "1000");
		try {
			ResultActions perform = mvc.perform(request);
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK))
					.andDo(MockMvcResultHandlers.print()).andReturn();
			Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void joinSeckillActivity() {
		
		SeckillActivityDO record = new SeckillActivityDO();
		record.setActivityStatus(ActivityStatusEnum.NOT_STARTED.getValue());
		record.setEndDate(new Date());
		record.setGmtCreate(new Date());
		record.setIsRemind(false);
		record.setMemberLevel(MemberGradeEnum.GOLD.getVal());
		record.setName("抢购活动");
		record.setProductValidCount(20);
		record.setSellingPrice(BigDecimal.valueOf(9.9));
		record.setStartDate(new Date());
		record.setStatus(Byte.valueOf("1"));
		seckillActivityDOMapper.insertSelective(record);
		
		JoinSeckillActivityParam joinParam = new JoinSeckillActivityParam();
		List<ModelParam> list = new ArrayList<>();
		ModelParam model = new ModelParam();
		model.setCount(10);
		model.setModelId(1l);
		joinParam.setModelList(list);
		joinParam.setProductId(1l);
		joinParam.setSeckillActivityId(record.getId());
		
		RequestBuilder request = post("/seckillActivityJoin/joinSeckillActivity/").contentType(MediaType.APPLICATION_JSON)
				.content(JSON.toJSONString(joinParam)).param("merchantId", "1000");
		try {
			ResultActions perform = mvc.perform(request);
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED))
					.andDo(MockMvcResultHandlers.print()).andReturn();
			Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}


}
