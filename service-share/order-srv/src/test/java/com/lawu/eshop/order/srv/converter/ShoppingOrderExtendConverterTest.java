package com.lawu.eshop.order.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.order.constants.CommissionStatusEnum;
import com.lawu.eshop.order.constants.ShoppingOrderStatusEnum;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExtendDetailDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExtendQueryDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderItemDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderQueryToMerchantDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderQueryToOperatorDTO;
import com.lawu.eshop.order.dto.foreign.TraceDTO;
import com.lawu.eshop.order.srv.bo.ExpressInquiriesDetailBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderExtendBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderItemBO;
import com.lawu.framework.core.page.Page;

/**
 * 
 * @author jiangxinjun
 * @date 2017年7月25日
 */
public class ShoppingOrderExtendConverterTest {

	@Test
	public void convertShoppingOrderExtendDetailDTO() {
		ShoppingOrderExtendBO expected = initShoppingOrderExtendBO();
		ExpressInquiriesDetailBO expectedExpressInquiriesDetailBO = ExpressConverterTest.initExpressInquiriesDetailBO();
		ShoppingOrderExtendDetailDTO actual = ShoppingOrderExtendConverter.convert(expected, expectedExpressInquiriesDetailBO);
		assertShoppingOrderExtendDetailDTO(expected, actual);
		for (int i = 0; i < expected.getItems().size(); i++) {
			assertShoppingOrderItemDTO(expected.getItems().get(i), actual.getItems().get(i));
		}
		Assert.assertEquals(expectedExpressInquiriesDetailBO.getState(), actual.getState());
		TraceDTO traceDTO = actual.getTrace();
		Assert.assertEquals(expectedExpressInquiriesDetailBO.getTraces().get(0).getAcceptStation(), traceDTO.getAcceptStation());
		Assert.assertEquals(expectedExpressInquiriesDetailBO.getTraces().get(0).getAcceptTime(), traceDTO.getAcceptTime());
		Assert.assertEquals(expectedExpressInquiriesDetailBO.getTraces().get(0).getRemark(), traceDTO.getRemark());
	}
	
	@Test
	public void convertShoppingOrderExtendQueryDTO() {
		ShoppingOrderExtendBO expected = initShoppingOrderExtendBO();
		ShoppingOrderExtendQueryDTO actual = ShoppingOrderExtendConverter.convertShoppingOrderExtendQueryDTO(expected);
		assertShoppingOrderExtendQueryDTO(expected, actual);
	}
	
	@Test
	public void convertShoppingOrderExtendQueryDTOList() {
		List<ShoppingOrderExtendBO> expected = new ArrayList<>();
		expected.add(initShoppingOrderExtendBO());
		List<ShoppingOrderExtendQueryDTO> actual = ShoppingOrderExtendConverter.convertShoppingOrderExtendQueryDTOList(expected);
		assertShoppingOrderExtendQueryDTOList(expected, actual);
	}
	
	@Test
	public void convertShoppingOrderExtendQueryDTOPage() {
		Page<ShoppingOrderExtendBO> expected = new Page<>();
		List<ShoppingOrderExtendBO> list = new ArrayList<>();
		list.add(initShoppingOrderExtendBO());
		expected.setRecords(list);
		expected.setCurrentPage(1);
		expected.setTotalCount(1);
		Page<ShoppingOrderExtendQueryDTO> actual = ShoppingOrderExtendConverter.convertShoppingOrderExtendQueryDTOPage(expected);
		Assert.assertEquals(expected.getCurrentPage(), actual.getCurrentPage());
		Assert.assertEquals(expected.getTotalCount(), actual.getTotalCount());
		assertShoppingOrderExtendQueryDTOList(expected.getRecords(), actual.getRecords());
	}
	
	@Test
	public void convertShoppingOrderQueryToMerchantDTO() {
		ShoppingOrderExtendBO expected = initShoppingOrderExtendBO();
		ShoppingOrderQueryToMerchantDTO actual = ShoppingOrderExtendConverter.convertShoppingOrderQueryToMerchantDTO(expected);
		assertShoppingOrderQueryToMerchantDTO(expected, actual);
	}
	
	@Test
	public void convertShoppingOrderQueryToMerchantDTOList() {
		List<ShoppingOrderExtendBO> expected = new ArrayList<>();
		expected.add(initShoppingOrderExtendBO());
		List<ShoppingOrderQueryToMerchantDTO> actual = ShoppingOrderExtendConverter.convertShoppingOrderQueryToMerchantDTOList(expected);
		assertShoppingOrderQueryToMerchantDTOList(expected, actual);
	}
	
	@Test
	public void convertShoppingOrderQueryToMerchantDTOPage() {
		Page<ShoppingOrderExtendBO> expected = new Page<>();
		List<ShoppingOrderExtendBO> list = new ArrayList<>();
		list.add(initShoppingOrderExtendBO());
		expected.setRecords(list);
		expected.setCurrentPage(1);
		expected.setTotalCount(1);
		Page<ShoppingOrderQueryToMerchantDTO> actual = ShoppingOrderExtendConverter.convertShoppingOrderQueryToMerchantDTOPage(expected);
		Assert.assertEquals(expected.getCurrentPage(), actual.getCurrentPage());
		Assert.assertEquals(expected.getTotalCount(), actual.getTotalCount());
		assertShoppingOrderQueryToMerchantDTOList(expected.getRecords(), actual.getRecords());
	}
	
	@Test
	public void convertShoppingOrderQueryToOperatorDTO() {
		ShoppingOrderExtendBO expected = initShoppingOrderExtendBO();
		ShoppingOrderQueryToOperatorDTO actual = ShoppingOrderExtendConverter.convertShoppingOrderQueryToOperatorDTO(expected);
		assertShoppingOrderQueryToOperatorDTO(expected, actual);
	}
	
	@Test
	public void convertShoppingOrderQueryToOperatorDTOList() {
		List<ShoppingOrderExtendBO> expected = new ArrayList<>();
		expected.add(initShoppingOrderExtendBO());
		List<ShoppingOrderQueryToOperatorDTO> actual = ShoppingOrderExtendConverter.convertShoppingOrderQueryToOperatorDTOList(expected);
		assertShoppingOrderQueryToOperatorDTOList(expected, actual);
	}
	
	public static void assertShoppingOrderQueryToOperatorDTOList(List<ShoppingOrderExtendBO> expected, List<ShoppingOrderQueryToOperatorDTO> actual) {
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.size(), actual.size());
		for (int i = 0; i < expected.size(); i++) {
			assertShoppingOrderQueryToOperatorDTO(expected.get(i), actual.get(i));
		}
	}
	
	public static void assertShoppingOrderQueryToOperatorDTO(ShoppingOrderExtendBO expected, ShoppingOrderQueryToOperatorDTO actual) {
		Assert.assertNotNull(actual);
    	Assert.assertEquals(expected.getGmtCreate().getTime(), actual.getGmtCreate().getTime());
    	Assert.assertEquals(expected.getId(), actual.getId());
    	Assert.assertEquals(expected.getOrderNum(), actual.getOrderNum());
    	Assert.assertEquals(expected.getOrderStatus(), actual.getOrderStatus());
	}
	
	public static void assertShoppingOrderQueryToMerchantDTOList(List<ShoppingOrderExtendBO> expected, List<ShoppingOrderQueryToMerchantDTO> actual) {
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.size(), actual.size());
		for (int i = 0; i < expected.size(); i++) {
			assertShoppingOrderQueryToMerchantDTO(expected.get(i), actual.get(i));
		}
	}
	
	public static void assertShoppingOrderQueryToMerchantDTO(ShoppingOrderExtendBO expected, ShoppingOrderQueryToMerchantDTO actual) {
		Assert.assertNotNull(actual);
    	Assert.assertEquals(expected.getConsigneeName(), actual.getConsigneeName());
    	Assert.assertEquals(expected.getGmtCreate().getTime(), actual.getGmtCreate().getTime());
    	Assert.assertEquals(expected.getId(), actual.getId());
    	Assert.assertEquals(expected.getOrderNum(), actual.getOrderNum());
    	Assert.assertEquals(expected.getOrderStatus(), actual.getOrderStatus());
	}
	
	public static void assertShoppingOrderExtendQueryDTOList(List<ShoppingOrderExtendBO> expected, List<ShoppingOrderExtendQueryDTO> actual) {
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.size(), actual.size());
		for (int i = 0; i < expected.size(); i++) {
			assertShoppingOrderExtendQueryDTO(expected.get(i), actual.get(i));
		}
	}
	
	public static void assertShoppingOrderExtendQueryDTO(ShoppingOrderExtendBO expected, ShoppingOrderExtendQueryDTO actual) {
		Assert.assertNotNull(actual);
    	Assert.assertEquals(expected.getFreightPrice().doubleValue(), actual.getFreightPrice().doubleValue(), 0D);
    	Assert.assertEquals(expected.getId(), actual.getId());
    	Assert.assertEquals(expected.getIsDone(), actual.getIsDone());
    	Assert.assertEquals(expected.getIsNeedsLogistics(), actual.getIsNeedsLogistics());
    	Assert.assertEquals(expected.getIsNoReasonReturn(), actual.getIsNoReasonReturn());
    	Assert.assertEquals(expected.getMerchantId(), actual.getMerchantId());
    	Assert.assertEquals(expected.getMerchantName(), actual.getMerchantName());
    	Assert.assertEquals(expected.getMerchantStoreId(), actual.getMerchantStoreId());
    	Assert.assertEquals(expected.getOrderStatus(), actual.getOrderStatus());
    	Assert.assertEquals(expected.getOrderTotalPrice().doubleValue(), actual.getOrderTotalPrice().doubleValue(), 0D);
    	for (int i = 0; i < expected.getItems().size(); i++) {
    		assertShoppingOrderItemDTO(expected.getItems().get(i), actual.getItems().get(i));
    	}
	}
	
	public static void assertShoppingOrderExtendDetailDTO(ShoppingOrderExtendBO expected, ShoppingOrderExtendDetailDTO actual) {
		Assert.assertNotNull(actual);
    	Assert.assertEquals(expected.getActualAmount().doubleValue(), actual.getActualAmount().doubleValue(), 0D);
    	Assert.assertEquals(expected.getCommodityTotalPrice().doubleValue(), actual.getCommodityTotalPrice().doubleValue(), 0D);
    	Assert.assertEquals(expected.getConsigneeAddress(), actual.getConsigneeAddress());
    	Assert.assertEquals(expected.getConsigneeMobile(), actual.getConsigneeMobile());
    	Assert.assertEquals(expected.getConsigneeName(), actual.getConsigneeName());
    	Assert.assertEquals(expected.getExpressCompanyId(), actual.getExpressCompanyId());
    	Assert.assertEquals(expected.getExpressCompanyName(), actual.getExpressCompanyName());
    	Assert.assertEquals(expected.getFreightPrice().doubleValue(), actual.getFreightPrice().doubleValue(), 0D);
    	Assert.assertEquals(expected.getGmtCreate().getTime(), actual.getGmtCreate().getTime());
    	Assert.assertEquals(expected.getGmtPayment() != null ? expected.getGmtPayment().getTime() : null, actual.getGmtPayment() != null ? actual.getGmtPayment().getTime() : null);
    	Assert.assertEquals(expected.getId(), actual.getId());
    	Assert.assertEquals(expected.getIsDone(), actual.getIsDone());
    	Assert.assertEquals(expected.getIsNeedsLogistics(), actual.getIsNeedsLogistics());
    	Assert.assertEquals(expected.getIsNoReasonReturn(), actual.getIsNoReasonReturn());
    	Assert.assertEquals(expected.getMemberNum(), actual.getMemberNum());
    	Assert.assertEquals(expected.getMerchantId(), actual.getMerchantId());
    	Assert.assertEquals(expected.getMerchantName(), actual.getMerchantName());
    	Assert.assertEquals(expected.getMerchantNum(), actual.getMerchantNum());
    	Assert.assertEquals(expected.getMerchantStoreId(), actual.getMerchantStoreId());
    	Assert.assertEquals(expected.getOrderNum(), actual.getOrderNum());
    	Assert.assertEquals(expected.getOrderStatus(), actual.getOrderStatus());
    	Assert.assertEquals(expected.getOrderTotalPrice().doubleValue(), actual.getOrderTotalPrice().doubleValue(), 0D);
    	Assert.assertEquals(expected.getPaymentMethod(), actual.getPaymentMethod());
    	Assert.assertEquals(expected.getWaybillNum(), actual.getWaybillNum());
    	Assert.assertEquals(expected.getGmtTransaction() != null ? expected.getGmtTransaction().getTime() : null, expected.getGmtTransaction() != null ? actual.getGmtTransaction().getTime() : null);
    	Assert.assertEquals(expected.getGmtTransport() != null ? expected.getGmtTransport().getTime() : null, actual.getGmtTransport() != null ? actual.getGmtTransport().getTime() : null);
	}
	
    public static void assertShoppingOrderItemDTO(ShoppingOrderItemBO expected, ShoppingOrderItemDTO actual){
    	Assert.assertNotNull(actual);
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
	
	private ShoppingOrderExtendBO initShoppingOrderExtendBO() {
		ShoppingOrderExtendBO rtn = new ShoppingOrderExtendBO();
    	rtn.setId(1L);
    	rtn.setActualAmount(new BigDecimal(1));
    	rtn.setCommodityTotalPrice(new BigDecimal(1));
    	rtn.setFreightPrice(new BigDecimal(0));
    	rtn.setGmtCreate(new Date());
    	rtn.setGmtModified(new Date());
    	rtn.setIsFans(true);
    	rtn.setIsNeedsLogistics(true);
    	rtn.setIsNoReasonReturn(true);
    	rtn.setMemberId(1L);
    	rtn.setMemberNum("M0001");
    	rtn.setMerchantId(1L);
    	rtn.setMerchantName("merchantName");
    	rtn.setMerchantStoreId(1L);
    	rtn.setMerchantNum("B0001");
    	rtn.setOrderStatus(ShoppingOrderStatusEnum.PENDING_PAYMENT);
    	rtn.setOrderTotalPrice(new BigDecimal(1));
    	rtn.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	rtn.setStatus(StatusEnum.VALID);
    	rtn.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED);
    	
    	rtn.setItems(new ArrayList<>());
    	ShoppingOrderItemBO item = new ShoppingOrderItemBO();
    	item.setGmtCreate(new Date());
    	item.setGmtModified(new Date());
    	item.setIsAllowRefund(true);
    	item.setIsEvaluation(false);
    	item.setOrderStatus(ShoppingOrderStatusEnum.PENDING_PAYMENT);
    	item.setProductFeatureImage("test.jpg");
    	item.setProductId(1L);
    	item.setProductName("productName");
    	item.setProductModelId(1L);
    	item.setProductModelName("test");
    	item.setQuantity(1);
    	item.setRegularPrice(new BigDecimal(1));
    	item.setSalesPrice(new BigDecimal(1));
    	item.setSendTime(0);
    	item.setShoppingOrderId(rtn.getId());
    	rtn.getItems().add(item);
    	return rtn;
	}
}
