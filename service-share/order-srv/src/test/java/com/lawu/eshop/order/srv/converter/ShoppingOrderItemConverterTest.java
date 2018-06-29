package com.lawu.eshop.order.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.order.constants.ShoppingOrderStatusEnum;
import com.lawu.eshop.order.dto.CommentOrderDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderItemDTO;
import com.lawu.eshop.order.param.ShoppingOrderSettlementItemParam;
import com.lawu.eshop.order.srv.bo.ShoppingOrderItemBO;
import com.lawu.eshop.order.srv.domain.ShoppingOrderItemDO;

/**
 * 
 * @author jiangxinjun
 * @date 2017年7月25日
 */
public class ShoppingOrderItemConverterTest {

	@Test
	public void convertShoppingOrderItemBO() {
		ShoppingOrderItemDO expected = initShoppingOrderItemDO();
		ShoppingOrderItemBO actual = ShoppingOrderItemConverter.convert(expected);
		assertShoppingOrderItemBO(expected, actual);
	}
	
	@Test
	public void convertShoppingOrderItemDTO() {
		ShoppingOrderItemBO expected = initShoppingOrderItemBO();
		ShoppingOrderItemDTO actual = ShoppingOrderItemConverter.convert(expected);
		assertShoppingOrderItemDTO(expected, actual);
	}
	
	@Test
	public void convertShoppingOrderItemDTOList() {
		List<ShoppingOrderItemBO> expected = new ArrayList<>();
		expected.add(initShoppingOrderItemBO());
		List<ShoppingOrderItemDTO> actual = ShoppingOrderItemConverter.convertShoppingOrderItemDTOList(expected);
		assertShoppingOrderItemDTOList(expected, actual);
	}
	
	@Test
	public void convertShoppingOrderItemBOList() {
		List<ShoppingOrderItemDO> expected = new ArrayList<>();
		expected.add(initShoppingOrderItemDO());
		List<ShoppingOrderItemBO> actual = ShoppingOrderItemConverter.convert(expected);
		assertShoppingOrderItemBOList(expected, actual);
	}
	
	@Test
	public void convertCommentOrderDTO() {
		ShoppingOrderItemBO expected = initShoppingOrderItemBO();
		CommentOrderDTO actual = ShoppingOrderItemConverter.coverCommentStatusDTO(expected);
		assertCommentOrderDTO(expected, actual);
	}
	
	public static void assertCommentOrderDTO(ShoppingOrderItemBO expected, CommentOrderDTO actual) {
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getIsEvaluation(), actual.getEvaluation());
	}
	
	public static void assertShoppingOrderItemDO(ShoppingOrderSettlementItemParam expected, ShoppingOrderItemDO actual, Long shoppingOrderId) {
		Assert.assertNotNull(actual);
		Assert.assertEquals(shoppingOrderId, actual.getShoppingOrderId());
		Assert.assertEquals(expected.getProductFeatureImage(), actual.getProductFeatureImage());
    	Assert.assertEquals(expected.getProductModelName(), actual.getProductModelName());
    	Assert.assertEquals(expected.getProductName(), actual.getProductName());
    	Assert.assertEquals(expected.getIsAllowRefund(), actual.getIsAllowRefund());
    	Assert.assertEquals(expected.getProductId(), actual.getProductId());
    	Assert.assertEquals(expected.getProductModelId(), actual.getProductModelId());
    	Assert.assertEquals(expected.getQuantity(), actual.getQuantity());
    	Assert.assertEquals(expected.getRegularPrice().doubleValue(), actual.getRegularPrice().doubleValue(), 0D);
    	Assert.assertEquals(expected.getSalesPrice().doubleValue(), actual.getSalesPrice().doubleValue(), 0D);
    	Assert.assertEquals(ShoppingOrderStatusEnum.PENDING.getValue(), actual.getOrderStatus());
	}
	
	public static void assertShoppingOrderItemDTOList(List<ShoppingOrderItemBO> expected, List<ShoppingOrderItemDTO> actual) {
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.size(), actual.size());
		for (int i = 0; i < expected.size(); i++) {
			assertShoppingOrderItemDTO(expected.get(i), actual.get(i));
		}
	}
	
	public static void assertShoppingOrderItemBOList(List<ShoppingOrderItemDO> expected, List<ShoppingOrderItemBO> actual) {
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.size(), actual.size());
		for (int i = 0; i < expected.size(); i++) {
			assertShoppingOrderItemBO(expected.get(i), actual.get(i));
		}
	}
	
	public static void assertShoppingOrderItemDTO(ShoppingOrderItemBO expected, ShoppingOrderItemDTO actual) {
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getId(), actual.getId());
		Assert.assertEquals(expected.getIsAllowRefund(), actual.getIsAllowRefund());
		Assert.assertEquals(expected.getIsEvaluation(), actual.getIsEvaluation());
		Assert.assertEquals(expected.getOrderStatus(), actual.getOrderStatus());
		Assert.assertEquals(expected.getProductFeatureImage(), actual.getProductFeatureImage());
		Assert.assertEquals(expected.getProductId(), actual.getProductId());
		Assert.assertEquals(expected.getProductModelId(), actual.getProductModelId());
		Assert.assertEquals(expected.getProductModelName(), actual.getProductModelName());
		Assert.assertEquals(expected.getProductName(), actual.getProductName());
		Assert.assertEquals(expected.getQuantity(), actual.getQuantity());
		Assert.assertEquals(expected.getRefundStatus(), actual.getRefundStatus() != null ? actual.getRefundStatus().getValue() : null);
		Assert.assertEquals(expected.getRegularPrice().doubleValue(), actual.getRegularPrice().doubleValue(), 0D);
	}
	
	public static void assertShoppingOrderItemBO(ShoppingOrderItemDO expected, ShoppingOrderItemBO actual) {
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getId(), actual.getId());
		Assert.assertEquals(expected.getGmtCreate().getTime(), actual.getGmtCreate().getTime());
		Assert.assertEquals(expected.getGmtModified().getTime(), actual.getGmtModified().getTime());
		Assert.assertEquals(expected.getIsAllowRefund(), actual.getIsAllowRefund());
		Assert.assertEquals(expected.getIsEvaluation(), actual.getIsEvaluation());
		Assert.assertEquals(expected.getOrderStatus(), actual.getOrderStatus().getValue());
		Assert.assertEquals(expected.getProductFeatureImage(), actual.getProductFeatureImage());
		Assert.assertEquals(expected.getProductId(), actual.getProductId());
		Assert.assertEquals(expected.getProductModelId(), actual.getProductModelId());
		Assert.assertEquals(expected.getProductModelName(), actual.getProductModelName());
		Assert.assertEquals(expected.getProductName(), actual.getProductName());
		Assert.assertEquals(expected.getQuantity(), actual.getQuantity());
		Assert.assertEquals(expected.getRefundStatus(), actual.getRefundStatus() != null ? actual.getRefundStatus().getValue() : null);
		Assert.assertEquals(expected.getRegularPrice().doubleValue(), actual.getRegularPrice().doubleValue(), 0D);
		Assert.assertEquals(expected.getSendTime(), actual.getSendTime());
		Assert.assertEquals(expected.getShoppingOrderId(), actual.getShoppingOrderId());
	}
	
	public static ShoppingOrderSettlementItemParam initShoppingOrderSettlementItemParam() {
		ShoppingOrderSettlementItemParam rtn = new ShoppingOrderSettlementItemParam();
		rtn.setIsAllowRefund(true);
		rtn.setProductFeatureImage("test.jpg");
		rtn.setProductId(1L);
		rtn.setProductModelId(1L);
		rtn.setProductName("productName");
		rtn.setProductModelName("productModelName");
		rtn.setQuantity(1);
		rtn.setRegularPrice(new BigDecimal(1));
		rtn.setSalesPrice(new BigDecimal(1));
		rtn.setShoppingCartId(1L);
		return rtn;
	}
	
	private ShoppingOrderItemBO initShoppingOrderItemBO() {
		ShoppingOrderItemBO rtn = new ShoppingOrderItemBO();
		rtn.setId(1L);
		rtn.setGmtCreate(new Date());
		rtn.setGmtModified(new Date());
		rtn.setIsAllowRefund(true);
		rtn.setIsEvaluation(false);
		rtn.setOrderStatus(ShoppingOrderStatusEnum.PENDING);
		rtn.setProductFeatureImage("test.jpg");
		rtn.setProductId(1L);
		rtn.setProductName("productName");
		rtn.setProductModelId(1L);
		rtn.setProductModelName("test");
		rtn.setQuantity(1);
		rtn.setRegularPrice(new BigDecimal(1));
		rtn.setSalesPrice(new BigDecimal(1));
		rtn.setSendTime(0);
		rtn.setShoppingOrderId(1L);
		return rtn;
	}
	
	private ShoppingOrderItemDO initShoppingOrderItemDO() {
		ShoppingOrderItemDO rtn = new ShoppingOrderItemDO();
		rtn.setId(1L);
		rtn.setGmtCreate(new Date());
		rtn.setGmtModified(new Date());
		rtn.setIsAllowRefund(true);
		rtn.setIsEvaluation(false);
		rtn.setOrderStatus(ShoppingOrderStatusEnum.PENDING.getValue());
		rtn.setProductFeatureImage("test.jpg");
		rtn.setProductId(1L);
		rtn.setProductName("productName");
		rtn.setProductModelId(1L);
		rtn.setProductModelName("test");
		rtn.setQuantity(1);
		rtn.setRegularPrice(new BigDecimal(1));
		rtn.setSalesPrice(new BigDecimal(1));
		rtn.setSendTime(0);
		rtn.setShoppingOrderId(1L);
		return rtn;
	}
}
