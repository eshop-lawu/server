package com.lawu.eshop.order.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.order.dto.ShoppingCartDTO;
import com.lawu.eshop.order.param.ShoppingCartSaveParam;
import com.lawu.eshop.order.param.ShoppingCartUpdateParam;
import com.lawu.eshop.order.srv.bo.ShoppingCartBO;
import com.lawu.eshop.order.srv.domain.ShoppingCartDO;

/**
 * 
 * @author jiangxinjun
 * @date 2017年7月25日
 */
public class ShoppingCartConverterTest {

	@Test
	public void convertShoppingCartBO() {
    	ShoppingCartDO expected = initShoppingCartDO();
    	ShoppingCartBO actual = ShoppingCartConverter.convert(expected);
    	assertShoppingCartBO(expected, actual);
	}
	
	@Test
	public void convertShoppingCartDTO() {
		ShoppingCartBO expected = initShoppingCartBO();
		ShoppingCartDTO actual = ShoppingCartConverter.convert(expected);
		assertShoppingCartDTO(expected, actual);
	}
	
	@Test
	public void convertShoppingCartDOBySave() {
		ShoppingCartSaveParam expected = initShoppingCartSaveParam();
		Long memberId = 1L;
		ShoppingCartDO actual = ShoppingCartConverter.convert(expected, memberId);
		Assert.assertNotNull(actual);
		Assert.assertNotNull(actual.getGmtCreate());
		Assert.assertNotNull(actual.getGmtModified());
    	Assert.assertEquals(expected.getMerchantId(), actual.getMerchantId());
    	Assert.assertEquals(expected.getMerchantName(), actual.getMerchantName());
    	Assert.assertEquals(expected.getMerchantStoreId(), actual.getMerchantStoreId());
    	Assert.assertEquals(expected.getProductId(), actual.getProductId());
    	Assert.assertEquals(expected.getProductModelId(), actual.getProductModelId());
    	Assert.assertEquals(expected.getQuantity(), actual.getQuantity());
    	Assert.assertEquals(expected.getSalesPrice().doubleValue(), actual.getSalesPrice().doubleValue(), 0D);
    	Assert.assertEquals(memberId, actual.getMemberId());
	}
	
	@Test
	public void convertShoppingCartDOByUpdate() {
		ShoppingCartUpdateParam expected = new ShoppingCartUpdateParam();
		expected.setProductModelId(1L);
		expected.setQuantity(1);
		Long id = 1L;
		ShoppingCartDO actual = ShoppingCartConverter.convert(expected, id);
		Assert.assertNotNull(actual);
		Assert.assertNotNull(id);
		Assert.assertNotNull(actual.getGmtModified());
    	Assert.assertEquals(expected.getProductModelId(), actual.getProductModelId());
    	Assert.assertEquals(expected.getQuantity(), actual.getQuantity());
	}
	
	@Test
	public void convertBOS() {
		List<ShoppingCartDO> expected = new ArrayList<>();
		expected.add(initShoppingCartDO());
		List<ShoppingCartBO> actual = ShoppingCartConverter.convertBOS(expected);
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.size(), actual.size());
		for (int i = 0; i < actual.size(); i++) {
			assertShoppingCartBO(expected.get(i), actual.get(i));
		}
	}
	
	@Test
	public void convertDTOS() {
		List<ShoppingCartBO> expected = new ArrayList<>();
		expected.add(initShoppingCartBO());
		List<ShoppingCartDTO> actual = ShoppingCartConverter.convertDTOS(expected);
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.size(), actual.size());
		for (int i = 0; i < actual.size(); i++) {
			assertShoppingCartDTO(expected.get(i), actual.get(i));
		}
	}

    public static void assertShoppingCartBO(ShoppingCartDO expected, ShoppingCartBO actual){
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(expected.getId(), actual.getId());
    	Assert.assertEquals(expected.getMerchantId(), actual.getMerchantId());
    	Assert.assertEquals(expected.getMerchantName(), actual.getMerchantName());
    	Assert.assertEquals(expected.getMerchantStoreId(), actual.getMerchantStoreId());
    	Assert.assertEquals(expected.getProductId(), actual.getProductId());
    	Assert.assertEquals(expected.getProductModelId(), actual.getProductModelId());
    	Assert.assertEquals(expected.getQuantity(), actual.getQuantity());
    	Assert.assertEquals(expected.getSalesPrice().doubleValue(), actual.getSalesPrice().doubleValue(), 0D);
    }
    
    private static void assertShoppingCartDTO(ShoppingCartBO expected, ShoppingCartDTO actual){
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(expected.getId(), actual.getId());
    	Assert.assertEquals(expected.getMerchantId(), actual.getMerchantId());
    	Assert.assertEquals(expected.getMerchantName(), actual.getMerchantName());
    	Assert.assertEquals(expected.getMerchantStoreId(), actual.getMerchantStoreId());
    	Assert.assertEquals(expected.getProductId(), actual.getProductId());
    	Assert.assertEquals(expected.getProductModelId(), actual.getProductModelId());
    	Assert.assertEquals(expected.getQuantity(), actual.getQuantity());
    	Assert.assertEquals(expected.getSalesPrice().doubleValue(), actual.getSalesPrice().doubleValue(), 0D);
    }
    
    private ShoppingCartDO initShoppingCartDO() {
		ShoppingCartDO rtn = new ShoppingCartDO();
		rtn.setId(1L);
    	rtn.setGmtCreate(new Date());
    	rtn.setGmtModified(new Date());
    	rtn.setMemberId(1L);
    	rtn.setMerchantId(1L);
    	rtn.setMerchantName("merchantName");
    	rtn.setMerchantStoreId(1L);
    	rtn.setProductId(1L);
    	rtn.setProductModelId(1L);
    	rtn.setQuantity(1);
    	rtn.setSalesPrice(new BigDecimal(1));
    	return rtn;
	}
    
    private ShoppingCartBO initShoppingCartBO() {
    	ShoppingCartBO rtn = new ShoppingCartBO();
    	rtn.setId(1L);
    	rtn.setMerchantId(1L);
    	rtn.setMerchantName("merchantName");
    	rtn.setMerchantStoreId(1L);
    	rtn.setProductId(1L);
    	rtn.setProductModelId(1L);
    	rtn.setQuantity(1);
    	rtn.setSalesPrice(new BigDecimal(1));
    	return rtn;
	}
    
    private ShoppingCartSaveParam initShoppingCartSaveParam() {
    	ShoppingCartSaveParam rtn = new ShoppingCartSaveParam();
		rtn.setMerchantId(1L);
		rtn.setMerchantName("merchantName");
		rtn.setMerchantStoreId(1L);
		rtn.setProductId(1L);
		rtn.setProductModelId(1L);
		rtn.setQuantity(1);
		rtn.setSalesPrice(new BigDecimal(1));
    	return rtn;
	}
}
