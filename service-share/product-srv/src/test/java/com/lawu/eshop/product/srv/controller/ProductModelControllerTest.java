/**
 * 
 */
package com.lawu.eshop.product.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

import com.lawu.eshop.product.srv.ProductSrvApplicationTest;
import com.lawu.eshop.product.srv.domain.ProductDO;
import com.lawu.eshop.product.srv.domain.ProductModelDO;
import com.lawu.eshop.product.srv.mapper.ProductDOMapper;
import com.lawu.eshop.product.srv.mapper.ProductModelDOMapper;
import com.lawu.framework.web.HttpCode;

/**
 * @author lihj
 * @date 2017年7月13日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductSrvApplicationTest.class)
@WebAppConfiguration
public class ProductModelControllerTest {

	private MockMvc mvc;

	private Logger log = Logger.getLogger(ProductModelControllerTest.class);
	@Autowired
	private ProductModelController productModelController;
	@Autowired
	private ProductDOMapper productDOMapper;
	
	@Autowired
	private ProductModelDOMapper productModelDOMapper;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(productModelController).build();
	}
	
	/**
	 * 根据商品型号ID查询购物车商品信息
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getShoppingCartProductModel(){
		RequestBuilder request = get("/productModel/shoppingCart/1");
		try{
			ResultActions perform = mvc.perform(request);
			log.info(perform.andReturn().getResponse().getContentAsString());
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK))
					.andDo(MockMvcResultHandlers.print()).andReturn();
			Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
		}catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	/**
	 * 根据商品型号ID列表查询购物车商品信息
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getShoppingCartProductModel1(){
		
	}

	/**
	 * 商家查看评价时，显示商品信息和其型号信息
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void selectCommentProductInfo(){
		Long pid = initproductData();
		Long pmid = initproductModelData(pid);
		RequestBuilder request = get("/productModel/selectCommentProductInfo/"+pmid);
		try{
			ResultActions perform = mvc.perform(request);
			log.info(perform.andReturn().getResponse().getContentAsString());
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK))
					.andDo(MockMvcResultHandlers.print()).andReturn();
			Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
		}catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		
	}
	
	
	/**
	 * 
	 */
	private Long initproductModelData(Long pid) {
		ProductModelDO pm =new ProductModelDO();
		pm.setGmtCreate(new Date());
		pm.setGmtModified(new Date());
		pm.setInventory(1);
		pm.setMerchantId(2L);
		pm.setName("测试");
		pm.setNum("100");
		pm.setOriginalPrice(new BigDecimal("120"));
		pm.setPrice(new BigDecimal("150"));
		pm.setProductId(pid);
		pm.setSalesVolume(4);
		pm.setStatus(true);
		productModelDOMapper.insert(pm);
		return pm.getId();
	}
	/**
	 * 
	 */
	private Long initproductData() {
		ProductDO p =new ProductDO();
		p.setAverageDailySales(new BigDecimal(100));
		p.setCategoryId(1);
		p.setContent("content");
		p.setFeatureImage("featureImage");
		p.setGmtCreate(new Date());
		p.setGmtDown(new Date());
		p.setGmtModified(new Date());
		p.setImageContent("imageContent");
		p.setIsAllowRefund(true);
		p.setMaxPrice(new BigDecimal(140));
		p.setMerchantId(2L);
		p.setMerchantNum("merchantNum");
		p.setMinPrice(new BigDecimal(99));
		p.setName("测试123");
		p.setNum("500");
		p.setStatus(Byte.valueOf("1"));
		p.setTotalFavorite(1);
		p.setTotalInventory(2);
		p.setTotalSalesVolume(3);
		productDOMapper.insert(p);
		return p.getId();
	}
	
	
	
}
