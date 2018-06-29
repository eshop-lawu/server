package com.lawu.eshop.order.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.order.constants.CommissionStatusEnum;
import com.lawu.eshop.order.constants.RefundStatusEnum;
import com.lawu.eshop.order.constants.ShoppingOrderStatusEnum;
import com.lawu.eshop.order.constants.ShoppingRefundTypeEnum;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderItemRefundDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderItemRefundForMerchantDTO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderItemExtendBO;
import com.lawu.eshop.order.srv.bo.ShoppingRefundDetailExtendBO;
import com.lawu.eshop.order.srv.bo.ShoppingRefundProcessBO;
import com.lawu.eshop.order.srv.domain.ShoppingOrderDO;
import com.lawu.eshop.order.srv.domain.ShoppingRefundProcessDO;
import com.lawu.eshop.order.srv.domain.extend.ShoppingOrderItemExtendDO;
import com.lawu.eshop.order.srv.domain.extend.ShoppingRefundDetailExtendDO;
import com.lawu.framework.core.page.Page;


/**
 * 
 * @author jiangxinjun
 * @date 2017年7月25日
 */
public class ShoppingOrderItemExtendConverterTest {

	@Test
	public void convertShoppingOrderItemBO() {
		ShoppingOrderItemExtendDO expected = initShoppingOrderItemExtendDO();
		ShoppingOrderItemExtendBO actual = ShoppingOrderItemExtendConverter.convert(expected);
		assertShoppingOrderItemExtendBO(expected, actual);
	}
	
	@Test
	public void convertShoppingOrderItemBOList() {
		List<ShoppingOrderItemExtendDO> expected = new ArrayList<>();
		expected.add(initShoppingOrderItemExtendDO());
		List<ShoppingOrderItemExtendBO> actual = ShoppingOrderItemExtendConverter.convert(expected);
		assertShoppingOrderItemExtendBOList(expected, actual);
	}
	
	@Test
	public void convertShoppingOrderItemRefundDTO() {
		ShoppingOrderItemExtendBO expected = initShoppingOrderItemExtendBO();
		ShoppingOrderItemRefundDTO actual = ShoppingOrderItemExtendConverter.convertShoppingOrderItemRefundDTO(expected);
		assertShoppingOrderItemRefundDTO(expected, actual);
	}
	
	@Test
	public void convertShoppingOrderItemRefundDTOList() {
		List<ShoppingOrderItemExtendBO> expected = new ArrayList<>();
		expected.add(initShoppingOrderItemExtendBO());
		List<ShoppingOrderItemRefundDTO> actual = ShoppingOrderItemExtendConverter.convertShoppingOrderItemRefundDTOList(expected);
		assertShoppingOrderItemRefundDTOList(expected, actual);
	}
	
	@Test
	public void convertShoppingOrderItemRefundDTOPage() {
		Page<ShoppingOrderItemExtendBO> expected = new Page<>();
		List<ShoppingOrderItemExtendBO> list = new ArrayList<>();
		list.add(initShoppingOrderItemExtendBO());
		expected.setCurrentPage(1);
		expected.setTotalCount(1);
		expected.setRecords(list);
		Page<ShoppingOrderItemRefundDTO> actual = ShoppingOrderItemExtendConverter.convertShoppingOrderItemRefundDTOPage(expected);
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getCurrentPage(), actual.getCurrentPage());
		Assert.assertEquals(expected.getTotalCount(), actual.getTotalCount());
		assertShoppingOrderItemRefundDTOList(expected.getRecords(), actual.getRecords());
	}
	
	@Test
	public void convertShoppingOrderItemRefundForMerchantDTO() {
		ShoppingOrderItemExtendBO expected = initShoppingOrderItemExtendBO();
		ShoppingOrderItemRefundForMerchantDTO actual = ShoppingOrderItemExtendConverter.convert(expected);
		assertShoppingOrderItemRefundForMerchantDTO(expected, actual);
	}
	
	@Test
	public void convertShoppingOrderItemRefundForMerchantDTOList() {
		List<ShoppingOrderItemExtendBO> expected = new ArrayList<>();
		expected.add(initShoppingOrderItemExtendBO());
		List<ShoppingOrderItemRefundForMerchantDTO> actual = ShoppingOrderItemExtendConverter.convertShoppingOrderItemRefundForMerchantDTOList(expected);
		assertShoppingOrderItemRefundForMerchantDTOList(expected, actual);
	}
	
	@Test
	public void convertShoppingOrderItemRefundForMerchantDTOPage() {
		Page<ShoppingOrderItemExtendBO> expected = new Page<>();
		List<ShoppingOrderItemExtendBO> list = new ArrayList<>();
		list.add(initShoppingOrderItemExtendBO());
		expected.setCurrentPage(1);
		expected.setTotalCount(1);
		expected.setRecords(list);
		Page<ShoppingOrderItemRefundForMerchantDTO> actual = ShoppingOrderItemExtendConverter.convertShoppingOrderItemRefundForMerchantDTOPage(expected);
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getCurrentPage(), actual.getCurrentPage());
		Assert.assertEquals(expected.getTotalCount(), actual.getTotalCount());
		assertShoppingOrderItemRefundForMerchantDTOList(expected.getRecords(), actual.getRecords());
	}
	
	public static void assertShoppingOrderItemRefundForMerchantDTOList(List<ShoppingOrderItemExtendBO> expected, List<ShoppingOrderItemRefundForMerchantDTO> actual) {
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.size(), actual.size());
		for (int i = 0; i < expected.size(); i++) {
			assertShoppingOrderItemRefundForMerchantDTO(expected.get(i), actual.get(i));
		}
	}
	
	public static void assertShoppingOrderItemRefundForMerchantDTO(ShoppingOrderItemExtendBO expected, ShoppingOrderItemRefundForMerchantDTO actual) {
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getId(), actual.getId());
		Assert.assertEquals(expected.getProductFeatureImage(), actual.getProductFeatureImage());
		Assert.assertEquals(expected.getProductModelName(), actual.getProductModelName());
		Assert.assertEquals(expected.getProductName(), actual.getProductName());
		Assert.assertEquals(expected.getRefundStatus(), actual.getRefundStatus());
		
		Assert.assertEquals(expected.getShoppingOrder().getConsigneeName(), actual.getConsigneeName());
		
		Assert.assertEquals(expected.getShoppingRefundDetail().getId(), actual.getShoppingRefundDetailId());
		Assert.assertEquals(expected.getShoppingRefundDetail().getGmtCreate(), actual.getGmtCreate());
	}
	
	public static void assertShoppingOrderItemRefundDTOList(List<ShoppingOrderItemExtendBO> expected, List<ShoppingOrderItemRefundDTO> actual) {
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.size(), actual.size());
		for (int i = 0; i < expected.size(); i++) {
			assertShoppingOrderItemRefundDTO(expected.get(i), actual.get(i));
		}
	}
	
	public static void assertShoppingOrderItemRefundDTO(ShoppingOrderItemExtendBO expected, ShoppingOrderItemRefundDTO actual) {
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getId(), actual.getId());
		Assert.assertEquals(expected.getProductFeatureImage(), actual.getProductFeatureImage());
		Assert.assertEquals(expected.getProductModelName(), actual.getProductModelName());
		Assert.assertEquals(expected.getProductName(), actual.getProductName());
		Assert.assertEquals(expected.getQuantity(), actual.getQuantity());
		Assert.assertEquals(expected.getRefundStatus(), actual.getRefundStatus());
		
		Assert.assertEquals(expected.getShoppingOrder().getMerchantId(), actual.getMerchantId());
		Assert.assertEquals(expected.getShoppingOrder().getMerchantName(), actual.getMerchantName());
		Assert.assertEquals(expected.getShoppingOrder().getIsNoReasonReturn(), actual.getIsNoReasonReturn());
		Assert.assertEquals(expected.getShoppingOrder().getMerchantStoreId(), actual.getMerchantStoreId());
		
		Assert.assertEquals(expected.getShoppingRefundDetail().getAmount(), actual.getAmount());
		Assert.assertEquals(expected.getShoppingRefundDetail().getId(), actual.getShoppingRefundDetailId());
		Assert.assertEquals(expected.getShoppingRefundDetail().getType(), actual.getType());
	}
	
	public static void assertShoppingOrderItemExtendBOList(List<ShoppingOrderItemExtendDO> expected, List<ShoppingOrderItemExtendBO> actual) {
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.size(), actual.size());
		for (int i = 0; i < expected.size(); i++) {
			assertShoppingOrderItemExtendBO(expected.get(i), actual.get(i));
		}
	}
	
	public static void assertShoppingOrderItemExtendBO(ShoppingOrderItemExtendDO expected, ShoppingOrderItemExtendBO actual) {
		ShoppingOrderItemConverterTest.assertShoppingOrderItemBO(expected, actual);
		ShoppingOrderConverterTest.assertShoppingOrderBO(expected.getShoppingOrder(), actual.getShoppingOrder());
		ShoppingRefundDetailConverterTest.assertShoppingRefundDetailBO(expected.getShoppingRefundDetail(), actual.getShoppingRefundDetail());
		for (int i = 0; i < expected.getShoppingRefundDetail().getShoppingRefundProcessList().size(); i++) {
			ShoppingRefundProcessConverterTest.assertShoppingRefundProcessBO(expected.getShoppingRefundDetail().getShoppingRefundProcessList().get(i), actual.getShoppingRefundDetail().getShoppingRefundProcessList().get(i));
		}
	}
    	
	private ShoppingOrderItemExtendDO initShoppingOrderItemExtendDO() {
		ShoppingOrderItemExtendDO rtn = new ShoppingOrderItemExtendDO();
		
    	ShoppingOrderDO shoppingOrderDO = new ShoppingOrderDO();
    	shoppingOrderDO.setId(1L);
    	shoppingOrderDO.setCommodityTotalPrice(new BigDecimal(1));
    	shoppingOrderDO.setActualAmount(new BigDecimal(1));
    	shoppingOrderDO.setFreightPrice(new BigDecimal(0));
    	shoppingOrderDO.setGmtCreate(new Date());
    	shoppingOrderDO.setGmtModified(new Date());
    	shoppingOrderDO.setGmtTransport(new Date());
    	shoppingOrderDO.setIsFans(true);
    	shoppingOrderDO.setIsNeedsLogistics(true);
    	shoppingOrderDO.setIsNoReasonReturn(true);
    	shoppingOrderDO.setMemberId(1L);
    	shoppingOrderDO.setMemberNum("M0001");
    	shoppingOrderDO.setMerchantId(1L);
    	shoppingOrderDO.setMerchantName("拉乌网络");
    	shoppingOrderDO.setMerchantStoreId(1L);
    	shoppingOrderDO.setMerchantNum("B0001");
    	shoppingOrderDO.setOrderStatus(ShoppingOrderStatusEnum.REFUNDING.getValue());
    	shoppingOrderDO.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	shoppingOrderDO.setOrderTotalPrice(new BigDecimal(1));
    	shoppingOrderDO.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	shoppingOrderDO.setStatus(StatusEnum.VALID.getValue());
    	shoppingOrderDO.setConsigneeAddress("大冲商务中心1301");
    	shoppingOrderDO.setConsigneeMobile("123456");
    	shoppingOrderDO.setConsigneeName("Sunny");
    	shoppingOrderDO.setIsDone(false);
    	shoppingOrderDO.setShoppingCartIdsStr("1");
    	shoppingOrderDO.setSendTime(0);
    	shoppingOrderDO.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	rtn.setShoppingOrder(shoppingOrderDO);
    	
    	rtn.setId(1L);
    	rtn.setGmtCreate(new Date());
    	rtn.setGmtModified(new Date());
    	rtn.setIsAllowRefund(true);
    	rtn.setIsEvaluation(false);
    	rtn.setOrderStatus(ShoppingOrderStatusEnum.REFUNDING.getValue());
    	rtn.setProductFeatureImage("test.jpg");
    	rtn.setProductId(1L);
    	rtn.setProductName("productName");
    	rtn.setProductModelId(1L);
    	rtn.setProductModelName("test");
    	rtn.setQuantity(1);
    	rtn.setRegularPrice(new BigDecimal(1));
    	rtn.setSalesPrice(new BigDecimal(1));
    	// 已经发送两次提醒消息
    	rtn.setSendTime(1);
    	rtn.setShoppingOrderId(shoppingOrderDO.getId());
    	rtn.setRefundStatus(RefundStatusEnum.FILL_RETURN_ADDRESS.getValue());
    	
    	ShoppingRefundDetailExtendDO shoppingRefundDetailExtendDO = new ShoppingRefundDetailExtendDO();
    	shoppingRefundDetailExtendDO.setId(1L);
    	shoppingRefundDetailExtendDO.setType(ShoppingRefundTypeEnum.RETURN_REFUND.getValue());
    	shoppingRefundDetailExtendDO.setAmount(rtn.getSalesPrice().multiply(new BigDecimal(rtn.getQuantity())));
    	shoppingRefundDetailExtendDO.setDescription("就是想退款");
    	shoppingRefundDetailExtendDO.setGmtModified(new Date());
    	shoppingRefundDetailExtendDO.setGmtCreate(new Date());
    	shoppingRefundDetailExtendDO.setReason("七天无理由退货");
    	shoppingRefundDetailExtendDO.setShoppingOrderItemId(rtn.getId());
    	shoppingRefundDetailExtendDO.setStatus(StatusEnum.VALID.getValue());
    	
    	ShoppingRefundProcessDO shoppingRefundProcessDO = new ShoppingRefundProcessDO();
    	shoppingRefundDetailExtendDO.setId(1L);
    	shoppingRefundProcessDO.setGmtCreate(new Date());
    	shoppingRefundProcessDO.setShoppingRefundDetailId(shoppingRefundDetailExtendDO.getId());
    	shoppingRefundProcessDO.setRefundStatus(RefundStatusEnum.TO_BE_CONFIRMED.getValue());
    	shoppingRefundDetailExtendDO.setShoppingRefundProcessList(new ArrayList<>());
    	shoppingRefundDetailExtendDO.getShoppingRefundProcessList().add(shoppingRefundProcessDO);
    	
    	rtn.setShoppingRefundDetail(shoppingRefundDetailExtendDO);
    	return rtn;
	}
	
	public static ShoppingOrderItemExtendBO initShoppingOrderItemExtendBO() {
		ShoppingOrderItemExtendBO rtn = new ShoppingOrderItemExtendBO();
		
    	ShoppingOrderBO shoppingOrderBO = new ShoppingOrderBO();
    	shoppingOrderBO.setId(1L);
    	shoppingOrderBO.setCommodityTotalPrice(new BigDecimal(1));
    	shoppingOrderBO.setActualAmount(new BigDecimal(1));
    	shoppingOrderBO.setFreightPrice(new BigDecimal(0));
    	shoppingOrderBO.setGmtCreate(new Date());
    	shoppingOrderBO.setGmtModified(new Date());
    	shoppingOrderBO.setGmtTransport(new Date());
    	shoppingOrderBO.setIsFans(true);
    	shoppingOrderBO.setIsNeedsLogistics(true);
    	shoppingOrderBO.setIsNoReasonReturn(true);
    	shoppingOrderBO.setMemberId(1L);
    	shoppingOrderBO.setMemberNum("M0001");
    	shoppingOrderBO.setMerchantId(1L);
    	shoppingOrderBO.setMerchantName("拉乌网络");
    	shoppingOrderBO.setMerchantStoreId(1L);
    	shoppingOrderBO.setMerchantNum("B0001");
    	shoppingOrderBO.setOrderStatus(ShoppingOrderStatusEnum.REFUNDING);
    	shoppingOrderBO.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED);
    	shoppingOrderBO.setOrderTotalPrice(new BigDecimal(1));
    	shoppingOrderBO.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	shoppingOrderBO.setStatus(StatusEnum.VALID);
    	shoppingOrderBO.setConsigneeAddress("大冲商务中心1301");
    	shoppingOrderBO.setConsigneeMobile("123456");
    	shoppingOrderBO.setConsigneeName("Sunny");
    	shoppingOrderBO.setIsDone(false);
    	shoppingOrderBO.setShoppingCartIdsStr("1");
    	shoppingOrderBO.setSendTime(0);
    	shoppingOrderBO.setPaymentMethod(TransactionPayTypeEnum.BALANCE);
    	rtn.setShoppingOrder(shoppingOrderBO);
    	
    	rtn.setId(1L);
    	rtn.setGmtCreate(new Date());
    	rtn.setGmtModified(new Date());
    	rtn.setIsAllowRefund(true);
    	rtn.setIsEvaluation(false);
    	rtn.setOrderStatus(ShoppingOrderStatusEnum.REFUNDING);
    	rtn.setProductFeatureImage("test.jpg");
    	rtn.setProductId(1L);
    	rtn.setProductName("productName");
    	rtn.setProductModelId(1L);
    	rtn.setProductModelName("test");
    	rtn.setQuantity(1);
    	rtn.setRegularPrice(new BigDecimal(1));
    	rtn.setSalesPrice(new BigDecimal(1));
    	// 已经发送两次提醒消息
    	rtn.setSendTime(1);
    	rtn.setShoppingOrderId(shoppingOrderBO.getId());
    	rtn.setRefundStatus(RefundStatusEnum.FILL_RETURN_ADDRESS);
    	
    	ShoppingRefundDetailExtendBO shoppingRefundDetailExtendBO = new ShoppingRefundDetailExtendBO();
    	shoppingRefundDetailExtendBO.setId(1L);
    	shoppingRefundDetailExtendBO.setType(ShoppingRefundTypeEnum.RETURN_REFUND);
    	shoppingRefundDetailExtendBO.setAmount(rtn.getSalesPrice().multiply(new BigDecimal(rtn.getQuantity())));
    	shoppingRefundDetailExtendBO.setDescription("就是想退款");
    	shoppingRefundDetailExtendBO.setGmtModified(new Date());
    	shoppingRefundDetailExtendBO.setGmtCreate(new Date());
    	shoppingRefundDetailExtendBO.setReason("七天无理由退货");
    	shoppingRefundDetailExtendBO.setShoppingOrderItemId(rtn.getId());
    	shoppingRefundDetailExtendBO.setStatus(StatusEnum.VALID);
    	
    	ShoppingRefundProcessBO shoppingRefundProcessBO = new ShoppingRefundProcessBO();
    	shoppingRefundProcessBO.setId(1L);
    	shoppingRefundProcessBO.setGmtCreate(new Date());
    	shoppingRefundProcessBO.setShoppingRefundDetailId(shoppingRefundDetailExtendBO.getId());
    	shoppingRefundProcessBO.setRefundStatus(RefundStatusEnum.TO_BE_CONFIRMED);
    	shoppingRefundDetailExtendBO.setShoppingRefundProcessList(new ArrayList<>());
    	shoppingRefundDetailExtendBO.getShoppingRefundProcessList().add(shoppingRefundProcessBO);
    	
    	rtn.setShoppingRefundDetail(shoppingRefundDetailExtendBO);
    	return rtn;
	}
}
