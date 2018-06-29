package com.lawu.eshop.order.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.order.constants.RefundStatusEnum;
import com.lawu.eshop.order.dto.ShoppingRefundProcessDTO;
import com.lawu.eshop.order.srv.bo.ShoppingRefundProcessBO;
import com.lawu.eshop.order.srv.domain.ShoppingRefundProcessDO;

/**
 * 
 * @author jiangxinjun
 * @date 2017年7月25日
 */
public class ShoppingRefundProcessConverterTest {
	
	@Test
	public void convertShoppingRefundProcessBO() {
		ShoppingRefundProcessDO expected = initShoppingRefundProcessDO();
		ShoppingRefundProcessBO actual = ShoppingRefundProcessConverter.convert(expected);
		assertShoppingRefundProcessBO(expected, actual);
	}
	
	@Test
	public void convertShoppingRefundProcessBOList() {
		List<ShoppingRefundProcessDO> expected = new ArrayList<>();
		expected.add(initShoppingRefundProcessDO());
		List<ShoppingRefundProcessBO> actual = ShoppingRefundProcessConverter.convert(expected);
		assertShoppingRefundProcessBOList(expected, actual);
	}
	
	@Test
	public void convertShoppingRefundProcessDTO() {
		ShoppingRefundProcessBO expected = initShoppingRefundProcessBO();
		ShoppingRefundProcessDTO actual = ShoppingRefundProcessConverter.convert(expected);
		assertShoppingRefundProcessDTO(expected, actual);
	}
	
	@Test
	public void convertShoppingRefundProcessDTOList() {
		List<ShoppingRefundProcessBO> expected = new ArrayList<>();
		expected.add(initShoppingRefundProcessBO());
		List<ShoppingRefundProcessDTO> actual = ShoppingRefundProcessConverter.convertShoppingRefundProcessDTOList(expected);
		assertShoppingRefundProcessDTOList(expected, actual);
	}
	
	public static void assertShoppingRefundProcessDTO(ShoppingRefundProcessBO expected, ShoppingRefundProcessDTO actual) {
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getGmtCreate(), actual.getGmtCreate());
		Assert.assertEquals(expected.getRefundStatus(), actual.getRefundStatus());
	}
	
	public static void assertShoppingRefundProcessDTOList(List<ShoppingRefundProcessBO> expected, List<ShoppingRefundProcessDTO> actual) {
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.size(), actual.size());
		for (int i = 0; i < expected.size(); i++) {
			assertShoppingRefundProcessDTO(expected.get(i), actual.get(i));
		}
	}
	
	public static void assertShoppingRefundProcessBO(ShoppingRefundProcessDO expected, ShoppingRefundProcessBO actual) {
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getId(), actual.getId());
		Assert.assertEquals(expected.getGmtCreate(), actual.getGmtCreate());
		Assert.assertEquals(expected.getRefundStatus(), actual.getRefundStatus().getValue());
		Assert.assertEquals(expected.getShoppingRefundDetailId(), actual.getShoppingRefundDetailId());
	}
	
	public static void assertShoppingRefundProcessBOList(List<ShoppingRefundProcessDO> expected, List<ShoppingRefundProcessBO> actual) {
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.size(), actual.size());
		for (int i = 0; i < expected.size(); i++) {
			assertShoppingRefundProcessBO(expected.get(i), actual.get(i));
		}
	}
	
	private ShoppingRefundProcessDO initShoppingRefundProcessDO() {
		ShoppingRefundProcessDO rtn = new ShoppingRefundProcessDO();
    	rtn.setId(1L);
    	rtn.setGmtCreate(new Date());
    	rtn.setShoppingRefundDetailId(1L);
    	rtn.setRefundStatus(RefundStatusEnum.TO_BE_CONFIRMED.getValue());
    	return rtn;
	}
	
	private ShoppingRefundProcessBO initShoppingRefundProcessBO() {
		ShoppingRefundProcessBO rtn = new ShoppingRefundProcessBO();
    	rtn.setId(1L);
    	rtn.setGmtCreate(new Date());
    	rtn.setShoppingRefundDetailId(1L);
    	rtn.setRefundStatus(RefundStatusEnum.TO_BE_CONFIRMED);
    	return rtn;
	}
}
