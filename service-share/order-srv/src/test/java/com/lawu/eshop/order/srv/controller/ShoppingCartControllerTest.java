package com.lawu.eshop.order.srv.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.order.dto.ShoppingCartDTO;
import com.lawu.eshop.order.param.ShoppingCartSaveParam;
import com.lawu.eshop.order.param.ShoppingCartUpdateParam;
import com.lawu.eshop.order.srv.OrderSrvApplicationTest;
import com.lawu.eshop.order.srv.constants.PropertyNameConstant;
import com.lawu.eshop.order.srv.domain.PropertyDO;
import com.lawu.eshop.order.srv.domain.ShoppingCartDO;
import com.lawu.eshop.order.srv.domain.ShoppingCartDOExample;
import com.lawu.eshop.order.srv.mapper.PropertyDOMapper;
import com.lawu.eshop.order.srv.mapper.ShoppingCartDOMapper;
import com.lawu.eshop.order.srv.service.impl.ShoppingCartServiceImplTest;
import com.lawu.framework.web.HttpCode;

/**
 * 
 * @author jiangxinjun
 * @date 2017年7月24日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OrderSrvApplicationTest.class)
@WebAppConfiguration
public class ShoppingCartControllerTest {

    private MockMvc mvc;

    @Autowired
    private ShoppingCartController shoppingCartController;
    
	@Autowired
	private ShoppingCartDOMapper shoppingCartDOMapper;
	
	@Autowired
	private PropertyDOMapper propertyDOMapper;
    
    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(shoppingCartController).build();
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void save() throws Exception {
    	PropertyDO propertyDO = new PropertyDO();
    	propertyDO.setGmtCreate(new Date());
    	propertyDO.setGmtModified(new Date());
    	propertyDO.setName(PropertyNameConstant.MAX_SHOPPING_CART_QUANTITY);
    	propertyDO.setRemark("购物车数量的最大限制");
    	propertyDO.setValue("100");
    	propertyDOMapper.insert(propertyDO);
    	
    	Long memberId = 1L;
    	ShoppingCartSaveParam expected = new ShoppingCartSaveParam();
    	expected.setMerchantId(1L);
    	expected.setMerchantName("merchantName");
    	expected.setMerchantStoreId(1L);
    	expected.setProductId(1L);
    	expected.setProductModelId(1L);
    	expected.setQuantity(1);
    	expected.setSalesPrice(new BigDecimal(1));
    	
    	String content = JSONObject.toJSONString(expected);
    	RequestBuilder request = MockMvcRequestBuilders.post("/shoppingCart/" + memberId).contentType(MediaType.APPLICATION_JSON).content(content);    	
        ResultActions perform = mvc.perform(request);
        perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        ShoppingCartDOExample example = new ShoppingCartDOExample();
    	example.createCriteria().andMemberIdEqualTo(memberId);
    	List<ShoppingCartDO> result = shoppingCartDOMapper.selectByExample(example);
    	Assert.assertNotNull(result);
    	Assert.assertEquals(1, result.size());
    	for (ShoppingCartDO actual : result) {
    		Assert.assertEquals(memberId, actual.getMemberId());
    		ShoppingCartServiceImplTest.assertShoppingCartDO(expected, actual);
    	}
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void findListByMemberId() throws Exception {
    	ShoppingCartDO expected = new ShoppingCartDO();
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setMemberId(1L);
    	expected.setMerchantId(1L);
    	expected.setMerchantName("merchantName");
    	expected.setMerchantStoreId(1L);
    	expected.setProductId(1L);
    	expected.setProductModelId(1L);
    	expected.setQuantity(1);
    	expected.setSalesPrice(new BigDecimal(1));
    	shoppingCartDOMapper.insert(expected);
    	
    	RequestBuilder request = MockMvcRequestBuilders.get("/shoppingCart/list/" + expected.getMemberId());    	
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_OK))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        List<ShoppingCartDTO> actual = JSONObject.parseArray(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), ShoppingCartDTO.class);
        Assert.assertNotNull(actual);
        Assert.assertEquals(1, actual.size());
        for (ShoppingCartDTO item : actual) {
        	Assert.assertNotNull(item);
        	Assert.assertEquals(expected.getId(), item.getId());
        	Assert.assertEquals(expected.getMerchantId(), item.getMerchantId());
        	Assert.assertEquals(expected.getMerchantName(), item.getMerchantName());
        	Assert.assertEquals(expected.getMerchantStoreId(), item.getMerchantStoreId());
        	Assert.assertEquals(expected.getProductId(), item.getProductId());
        	Assert.assertEquals(expected.getProductModelId(), item.getProductModelId());
        	Assert.assertEquals(expected.getQuantity(), item.getQuantity());
        	Assert.assertEquals(expected.getSalesPrice().doubleValue(), item.getSalesPrice().doubleValue(), 0D);
        }
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void update() throws Exception {
    	ShoppingCartDO shoppingCartDO = new ShoppingCartDO();
    	shoppingCartDO.setGmtCreate(new Date());
    	shoppingCartDO.setGmtModified(new Date());
    	shoppingCartDO.setMemberId(1L);
    	shoppingCartDO.setMerchantId(1L);
    	shoppingCartDO.setMerchantName("merchantName");
    	shoppingCartDO.setMerchantStoreId(1L);
    	shoppingCartDO.setProductId(1L);
    	shoppingCartDO.setProductModelId(1L);
    	shoppingCartDO.setQuantity(1);
    	shoppingCartDO.setSalesPrice(new BigDecimal(1));
    	shoppingCartDOMapper.insert(shoppingCartDO);
    	
    	ShoppingCartUpdateParam expected = new ShoppingCartUpdateParam();
    	expected.setProductModelId(1L);
    	expected.setQuantity(1);
    	
    	String content = JSONObject.toJSONString(expected);
    	RequestBuilder request = MockMvcRequestBuilders.put("/shoppingCart/update/" + shoppingCartDO.getId()).param("memberId", shoppingCartDO.getMemberId().toString()) .contentType(MediaType.APPLICATION_JSON).content(content);    	
        ResultActions perform = mvc.perform(request);
        perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
    	ShoppingCartDO actual = shoppingCartDOMapper.selectByPrimaryKey(shoppingCartDO.getId());
		Assert.assertEquals(actual.getProductModelId(), expected.getProductModelId());
		Assert.assertEquals(actual.getQuantity(), expected.getQuantity());
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void delete() throws Exception {
    	ShoppingCartDO expected = new ShoppingCartDO();
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setMemberId(1L);
    	expected.setMerchantId(1L);
    	expected.setMerchantName("merchantName");
    	expected.setMerchantStoreId(1L);
    	expected.setProductId(1L);
    	expected.setProductModelId(1L);
    	expected.setQuantity(1);
    	expected.setSalesPrice(new BigDecimal(1));
    	shoppingCartDOMapper.insert(expected);
    	
    	List<Long> ids = new ArrayList<>();
    	ids.add(expected.getId());
    	
    	String content = JSONObject.toJSONString(ids);
    	RequestBuilder request = MockMvcRequestBuilders.put("/shoppingCart/delete").param("memberId", expected.getMemberId().toString()) .contentType(MediaType.APPLICATION_JSON).content(content);    	
        ResultActions perform = mvc.perform(request);
        perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
    	ShoppingCartDO actual = shoppingCartDOMapper.selectByPrimaryKey(expected.getId());
    	Assert.assertNull(actual);
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void findListByIds() throws Exception {
    	ShoppingCartDO expected = new ShoppingCartDO();
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setMemberId(1L);
    	expected.setMerchantId(1L);
    	expected.setMerchantName("merchantName");
    	expected.setMerchantStoreId(1L);
    	expected.setProductId(1L);
    	expected.setProductModelId(1L);
    	expected.setQuantity(1);
    	expected.setSalesPrice(new BigDecimal(1));
    	shoppingCartDOMapper.insert(expected);
    	
    	RequestBuilder request = MockMvcRequestBuilders.get("/shoppingCart/list/findListByIds/" + expected.getMemberId().toString()).param("ids", expected.getId().toString());  	
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_OK))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        List<ShoppingCartDTO> actual = JSONObject.parseArray(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), ShoppingCartDTO.class);
        Assert.assertNotNull(actual);
        Assert.assertEquals(1, actual.size());
        for (ShoppingCartDTO item : actual) {
        	Assert.assertNotNull(item);
        	Assert.assertEquals(expected.getId(), item.getId());
        	Assert.assertEquals(expected.getMerchantId(), item.getMerchantId());
        	Assert.assertEquals(expected.getMerchantName(), item.getMerchantName());
        	Assert.assertEquals(expected.getMerchantStoreId(), item.getMerchantStoreId());
        	Assert.assertEquals(expected.getProductId(), item.getProductId());
        	Assert.assertEquals(expected.getProductModelId(), item.getProductModelId());
        	Assert.assertEquals(expected.getQuantity(), item.getQuantity());
        	Assert.assertEquals(expected.getSalesPrice().doubleValue(), item.getSalesPrice().doubleValue(), 0D);
        }
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void count() throws Exception {
    	ShoppingCartDO expected = new ShoppingCartDO();
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setMemberId(1L);
    	expected.setMerchantId(1L);
    	expected.setMerchantName("merchantName");
    	expected.setMerchantStoreId(1L);
    	expected.setProductId(1L);
    	expected.setProductModelId(1L);
    	expected.setQuantity(1);
    	expected.setSalesPrice(new BigDecimal(1));
    	shoppingCartDOMapper.insert(expected);
    	
    	RequestBuilder request = MockMvcRequestBuilders.get("/shoppingCart/count/" + expected.getMemberId().toString());    	
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_OK))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        Long actual = JSONObject.parseObject(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), Long.class);
        Assert.assertNotNull(actual);
        Assert.assertEquals(1, actual.intValue());
    }
}
