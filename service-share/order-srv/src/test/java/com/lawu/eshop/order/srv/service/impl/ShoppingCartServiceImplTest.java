package com.lawu.eshop.order.srv.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.order.param.ShoppingCartSaveParam;
import com.lawu.eshop.order.param.ShoppingCartUpdateParam;
import com.lawu.eshop.order.srv.OrderSrvApplicationTest;
import com.lawu.eshop.order.srv.bo.ShoppingCartBO;
import com.lawu.eshop.order.srv.constants.PropertyNameConstant;
import com.lawu.eshop.order.srv.converter.ShoppingCartConverterTest;
import com.lawu.eshop.order.srv.domain.PropertyDO;
import com.lawu.eshop.order.srv.domain.ShoppingCartDO;
import com.lawu.eshop.order.srv.domain.ShoppingCartDOExample;
import com.lawu.eshop.order.srv.mapper.PropertyDOMapper;
import com.lawu.eshop.order.srv.mapper.ShoppingCartDOMapper;
import com.lawu.eshop.order.srv.service.ShoppingCartService;

/**
 * 
 * @author jiangxinjun
 * @date 2017年7月12日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OrderSrvApplicationTest.class)
public class ShoppingCartServiceImplTest {

    @Autowired
    private ShoppingCartService shoppingCartService;

	@Autowired
	private ShoppingCartDOMapper shoppingCartDOMapper;
	
	@Autowired
	private PropertyDOMapper propertyDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void count() {
    	ShoppingCartDO record = new ShoppingCartDO();
    	record.setGmtCreate(new Date());
    	record.setGmtModified(new Date());
    	record.setMemberId(1L);
    	record.setMerchantId(1L);
    	record.setMerchantName("merchantName");
    	record.setMerchantStoreId(1L);
    	record.setProductId(1L);
    	record.setProductModelId(1L);
    	record.setQuantity(1);
    	record.setSalesPrice(new BigDecimal(1));
    	shoppingCartDOMapper.insert(record);
    	
    	Long result = shoppingCartService.count(record.getMemberId());
    	Assert.assertNotNull(result);
    	Long expected = 1L;
    	Assert.assertEquals(expected, result);
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void findListByIds() {
    	ShoppingCartDO record = new ShoppingCartDO();
    	record.setGmtCreate(new Date());
    	record.setGmtModified(new Date());
    	record.setMemberId(1L);
    	record.setMerchantId(1L);
    	record.setMerchantName("merchantName");
    	record.setMerchantStoreId(1L);
    	record.setProductId(1L);
    	record.setProductModelId(1L);
    	record.setQuantity(1);
    	record.setSalesPrice(new BigDecimal(1));
    	shoppingCartDOMapper.insert(record);
    	
    	List<Long> ids = new ArrayList<>();
    	ids.add(record.getId());
    	List<ShoppingCartBO> result = shoppingCartService.findListByIds(record.getMemberId(), ids);
    	Assert.assertNotNull(result);
    	Assert.assertEquals(1, result.size());
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void findListByMemberId() {
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
    	
    	List<ShoppingCartBO> result = shoppingCartService.findListByMemberId(expected.getMemberId());
    	Assert.assertNotNull(result);
    	Assert.assertEquals(1, result.size());
    	for (ShoppingCartBO actual : result) {
    		ShoppingCartConverterTest.assertShoppingCartBO(expected, actual);
    	}
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void get() {
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
    	
    	ShoppingCartBO actual = shoppingCartService.get(expected.getMemberId(), expected.getId());
    	ShoppingCartConverterTest.assertShoppingCartBO(expected, actual);
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void remove() {
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
    	shoppingCartService.remove(expected.getMemberId(), ids);
    	
    	ShoppingCartDO actual = shoppingCartDOMapper.selectByPrimaryKey(expected.getId());
    	Assert.assertNull(actual);
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void removeById() {
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
    	shoppingCartService.remove(expected.getMemberId(), expected.getId());
    	
    	ShoppingCartDO actual = shoppingCartDOMapper.selectByPrimaryKey(expected.getId());
    	Assert.assertNull(actual);
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void save() {
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
    	shoppingCartService.save(memberId, expected);
    	
    	ShoppingCartDOExample example = new ShoppingCartDOExample();
    	example.createCriteria().andMemberIdEqualTo(memberId);
    	List<ShoppingCartDO> result = shoppingCartDOMapper.selectByExample(example);
    	Assert.assertNotNull(result);
    	Assert.assertEquals(1, result.size());
    	for (ShoppingCartDO actual : result) {
    		Assert.assertEquals(memberId, actual.getMemberId());
    		assertShoppingCartDO(expected, actual);
    	}
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void update() {
    	// 初始化数据
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
    	
    	// 执行
    	ShoppingCartUpdateParam expected = new ShoppingCartUpdateParam();
    	expected.setProductModelId(1L);
    	expected.setQuantity(1);
    	shoppingCartService.update(shoppingCartDO.getId(), shoppingCartDO.getMemberId(), expected);
    	
    	// 验证数据
    	ShoppingCartDOExample example = new ShoppingCartDOExample();
    	example.createCriteria().andMemberIdEqualTo(shoppingCartDO.getMemberId());
    	List<ShoppingCartDO> result = shoppingCartDOMapper.selectByExample(example);
    	Assert.assertNotNull(result);
    	Assert.assertEquals(1, result.size());
    	for (ShoppingCartDO actual : result) {
    		Assert.assertEquals(actual.getProductModelId(), expected.getProductModelId());
    		Assert.assertEquals(actual.getQuantity(), expected.getQuantity());
    	}
    }
    
    public static void assertShoppingCartDO(ShoppingCartSaveParam expected, ShoppingCartDO actual) {
    	Assert.assertEquals(actual.getProductModelId(), expected.getProductModelId());
		Assert.assertEquals(actual.getQuantity(), expected.getQuantity());
		Assert.assertEquals(actual.getMerchantId(), expected.getMerchantId());
		Assert.assertEquals(actual.getMerchantName(), expected.getMerchantName());
		Assert.assertEquals(actual.getMerchantStoreId(), expected.getMerchantStoreId());
		Assert.assertEquals(actual.getProductId(), expected.getProductId());
		Assert.assertEquals(actual.getProductModelId(), expected.getProductModelId());
		Assert.assertEquals(actual.getQuantity(), expected.getQuantity());
		Assert.assertEquals(actual.getSalesPrice().doubleValue(), expected.getSalesPrice().doubleValue(), 0D);
    }
}
