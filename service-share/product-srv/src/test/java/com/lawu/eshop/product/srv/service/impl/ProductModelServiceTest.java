/**
 * 
 */
package com.lawu.eshop.product.srv.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.lawu.eshop.mq.dto.order.ProductModeUpdateInventoryDTO;
import com.lawu.eshop.mq.dto.order.ShoppingOrderCancelOrderNotification;
import com.lawu.eshop.mq.dto.order.ShoppingOrderCreateOrderNotification;
import com.lawu.eshop.mq.dto.order.ShoppingOrderTradingSuccessIncreaseSalesNotification;
import com.lawu.eshop.mq.dto.order.reply.ShoppingOrderCreateOrderReply;
import com.lawu.eshop.product.constant.ProductStatusEnum;
import com.lawu.eshop.product.param.ListProductParam;
import com.lawu.eshop.product.srv.ProductSrvApplicationTest;
import com.lawu.eshop.product.srv.bo.CommentProductInfoBO;
import com.lawu.eshop.product.srv.bo.ProductInfoBO;
import com.lawu.eshop.product.srv.bo.ProductQueryBO;
import com.lawu.eshop.product.srv.bo.ShoppingCartProductModelBO;
import com.lawu.eshop.product.srv.domain.ProductDO;
import com.lawu.eshop.product.srv.domain.ProductDOExample;
import com.lawu.eshop.product.srv.domain.ProductModelDO;
import com.lawu.eshop.product.srv.domain.ProductModelDOExample;
import com.lawu.eshop.product.srv.mapper.ProductDOMapper;
import com.lawu.eshop.product.srv.mapper.ProductModelDOMapper;
import com.lawu.eshop.product.srv.service.ProductModelService;
import com.lawu.eshop.product.srv.service.ProductService;
import com.lawu.framework.core.page.Page;

/**
 * @author lihj
 * @date 2017年7月17日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductSrvApplicationTest.class)
@WebAppConfiguration
public class ProductModelServiceTest {
	private MockMvc mvc;

	private Logger log = Logger.getLogger(ProductModelServiceTest.class);

	@Autowired
	private ProductModelService productModelService;

	@Autowired
	private ProductDOMapper productDOMapper;
	@Autowired
	private ProductModelDOMapper productModelDOMapper;
	@Autowired
	private ProductService productService;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(productModelService).build();
	}

	/**
	 * 根据产品型号ID查询
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getShoppingCartProductModel() {
		Long pid = initproductData();
		initproductModelData(pid);
		ProductModelDOExample example = new ProductModelDOExample();
		List<ProductModelDO> list = productModelDOMapper.selectByExample(example);
		for (int i = 0; i < list.size(); i++) {
			ShoppingCartProductModelBO sc = productModelService.getShoppingCartProductModel(list.get(i).getId());
			log.info(JSON.toJSON(sc));
			Assert.assertNotNull(sc);
		}
	}

	/**
	 * 根据产品型号ID列表查询
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getShoppingCartProductModelList() {
		Long pid = initproductData();
		initproductModelData(pid);
		List<Long> ids = Lists.newArrayList();
		ProductModelDOExample example = new ProductModelDOExample();
		List<ProductModelDO> list = productModelDOMapper.selectByExample(example);
		for (int i = 0; i < list.size(); i++) {
			ids.add(list.get(i).getId());
		}
		ProductDOExample pr = new ProductDOExample();
		List<ProductDO> productDOs = productDOMapper.selectByExample(pr);
		List<ShoppingCartProductModelBO> listbo = productModelService.getShoppingCartProductModel(ids);
		Assert.assertNotNull(listbo);
		Assert.assertEquals(list.size(), 1);
	}

	/**
	 * 创建购物订单 订单模块发送消息减商品库存，并且保存商品库存流水记录
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void lessInventory() {
		Long pid = initproductData();
		Long pmid = initproductModelData(pid);
		ShoppingOrderCreateOrderNotification so = new ShoppingOrderCreateOrderNotification();
		so.setShoppingOrderId(1L);
		so.setTransactionId(2L);
		List<ProductModeUpdateInventoryDTO> list = Lists.newArrayList();
		ProductModeUpdateInventoryDTO dto = new ProductModeUpdateInventoryDTO();
		dto.setProdecutModelId(pmid);
		dto.setQuantity(1);
		list.add(dto);
		so.setParams(list);
		productModelService.lessInventory(so);
		ShoppingCartProductModelBO bo = productModelService.getShoppingCartProductModel(pmid);
		log.info(JSON.toJSONString(bo));
		Assert.assertEquals(Integer.valueOf(999), bo.getInventory());
	}

	/**
	 * 取消购物订单
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void releaseInventory() {
		Long pid = initproductData();
		Long pmid = initproductModelData(pid);
		List<ProductModeUpdateInventoryDTO> list = Lists.newArrayList();
		ShoppingOrderCreateOrderNotification so = new ShoppingOrderCreateOrderNotification();
		ProductModeUpdateInventoryDTO dto = new ProductModeUpdateInventoryDTO();
		so.setShoppingOrderId(1L);
		so.setTransactionId(2L);
		dto.setProdecutModelId(pmid);
		dto.setQuantity(1);
		list.add(dto);
		so.setParams(list);
		ShoppingOrderCancelOrderNotification sco = new ShoppingOrderCancelOrderNotification();
		sco.setShoppingOrderId(1L);
		sco.setTransactionId(2L);
		sco.setParams(list);
		productModelService.lessInventory(so);
		productModelService.releaseInventory(sco);
		ShoppingCartProductModelBO bo = productModelService.getShoppingCartProductModel(pmid);
		log.info(JSON.toJSONString(bo));
		Assert.assertEquals(Integer.valueOf(1000), bo.getInventory());
	}

	/**
	 * 商家查看评价时，显示商品信息和其型号信息
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void selectCommentProductInfo() {
		Long productId = initproductData();
		Long productModelId = initproductModelData(productId);
		CommentProductInfoBO bo = productModelService.selectCommentProductInfo(productModelId);
		log.info(JSON.toJSON(bo));
	}

	/**
	 * 确认收货，增加销量
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void increaseSales() {
		Long pid = initproductData();
		Long pmid = initproductModelData(pid);
		ShoppingOrderTradingSuccessIncreaseSalesNotification stu = new ShoppingOrderTradingSuccessIncreaseSalesNotification();
		stu.setShoppingOrderId(1L);
		stu.setTransactionId(2L);
		List<ProductModeUpdateInventoryDTO> list =Lists.newArrayList();
		ProductModeUpdateInventoryDTO dto =new ProductModeUpdateInventoryDTO();
		dto.setProdecutModelId(pmid);
		dto.setQuantity(5);
		list.add(dto);
		stu.setParams(list);
		productModelService.increaseSales(stu);
		ShoppingCartProductModelBO bo = productModelService.getShoppingCartProductModel(pmid);
		log.info(JSON.toJSONString(bo));
		ListProductParam param =new ListProductParam();
		param.setCurrentPage(1);
		param.setName("测试");
		param.setPageSize(10);
		param.setSortName("id");
		param.setSortOrder("desc");
		param.setStatusEnum(ProductStatusEnum.PRODUCT_STATUS_UP);
		Page<ProductQueryBO> page = productService.listAllProduct(param);
		for (int i = 0; i < page.getRecords().size(); i++) {
			ProductInfoBO bo1 = productService.getProductById(page.getRecords().get(i).getId());
			log.info(JSON.toJSON(bo1));
			Assert.assertNotNull(bo1);
		}
	}

	/**
	 * 检查库存是否扣除成功
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void checkLessInventory() {
		Long pid = initproductData();
		Long pmid = initproductModelData(pid);
		ShoppingOrderCreateOrderNotification sco = new ShoppingOrderCreateOrderNotification();
		sco.setShoppingOrderId(1L);
		sco.setTransactionId(2L);
		List<ProductModeUpdateInventoryDTO> list =Lists.newArrayList();
		ProductModeUpdateInventoryDTO dto = new ProductModeUpdateInventoryDTO();
		dto.setProdecutModelId(pmid);
		dto.setQuantity(1);
		list.add(dto);
		sco.setParams(list);
		ShoppingOrderCreateOrderReply so = productModelService.checkLessInventory(sco);
		log.info(JSON.toJSON(so));
		Assert.assertNull(so.getResult());
		
	}

	/**
	 * 
	 */
	private Long initproductModelData(Long pid) {
		ProductModelDO pm = new ProductModelDO();
		pm.setGmtCreate(new Date());
		pm.setGmtModified(new Date());
		pm.setInventory(1000);
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
		ProductDO p = new ProductDO();
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
		p.setStatus(Byte.valueOf("2"));
		p.setTotalFavorite(1);
		p.setTotalInventory(2);
		p.setTotalSalesVolume(3);
		productDOMapper.insert(p);
		return p.getId();
	}
}
