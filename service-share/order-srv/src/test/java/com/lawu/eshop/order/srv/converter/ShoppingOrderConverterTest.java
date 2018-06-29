package com.lawu.eshop.order.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.order.constants.CommissionStatusEnum;
import com.lawu.eshop.order.constants.ShoppingOrderStatusEnum;
import com.lawu.eshop.order.dto.ReportRiseRerouceDTO;
import com.lawu.eshop.order.dto.ShoppingOrderCommissionDTO;
import com.lawu.eshop.order.dto.ShoppingOrderIsNoOnGoingOrderDTO;
import com.lawu.eshop.order.dto.ShoppingOrderPaymentDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExpressDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderNumberOfOrderStatusDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderNumberOfOrderStatusForMerchantForeignDTO;
import com.lawu.eshop.order.param.ShoppingOrderSettlementItemParam;
import com.lawu.eshop.order.param.ShoppingOrderSettlementParam;
import com.lawu.eshop.order.param.ShoppingOrderUpdateInfomationParam;
import com.lawu.eshop.order.srv.OrderSrvApplicationTest;
import com.lawu.eshop.order.srv.bo.ExpressInquiriesDetailBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderIsNoOnGoingOrderBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderNumberOfOrderStatusBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderNumberOfOrderStatusForMerchantBO;
import com.lawu.eshop.order.srv.domain.ShoppingOrderDO;
import com.lawu.eshop.order.srv.domain.extend.ReportFansSaleTransFormDO;

/**
 * 
 * @author jiangxinjun
 * @date 2017年7月25日
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderSrvApplicationTest.class)
public class ShoppingOrderConverterTest {

	@Test
	public void convertShoppingOrderBO() {
    	ShoppingOrderDO expected  = initShoppingOrderDO();
    	ShoppingOrderBO actual = ShoppingOrderConverter.convertShoppingOrderBO(expected);
    	assertShoppingOrderBO(expected, actual);
	}
	
	@Test
	public void convertShoppingOrderIsNoOnGoingOrderBO() {
    	Long expected  = 1L;
    	ShoppingOrderIsNoOnGoingOrderBO actual = ShoppingOrderConverter.convert(expected);
    	assertShoppingOrderIsNoOnGoingOrderBO(expected, actual);
	}
	
	@Test
	public void convertShoppingOrderBOList() {
		List<ShoppingOrderDO> expected = new ArrayList<>();
		expected.add(initShoppingOrderDO());
		List<ShoppingOrderBO> actual = ShoppingOrderConverter.convert(expected);
		for (int i = 0; i < expected.size(); i++) {
			assertShoppingOrderBO(expected.get(i), actual.get(i));
		}
	}
	
	@Test
	public void convertShoppingOrderPaymentDTO() {
		ShoppingOrderBO expected = initShoppingOrderBO();
		ShoppingOrderPaymentDTO actual = ShoppingOrderConverter.convert(expected);
		Assert.assertNotNull(actual);
    	Assert.assertEquals(expected.getId(), actual.getId());
    	Assert.assertEquals(expected.getOrderNum(), actual.getOrderNum());
    	Assert.assertEquals(expected.getOrderTotalPrice().doubleValue(), actual.getOrderTotalPrice().doubleValue(), 0D);
	}
	
	@Test
	public void convertShoppingOrderIsNoOnGoingOrderDTO() {
		ShoppingOrderIsNoOnGoingOrderBO expected = new ShoppingOrderIsNoOnGoingOrderBO();
		expected.setIsNoOnGoingOrder(true);
		ShoppingOrderIsNoOnGoingOrderDTO actual = ShoppingOrderConverter.convert(expected);
		Assert.assertNotNull(actual);
    	Assert.assertEquals(expected.getIsNoOnGoingOrder(), actual.getIsNoOnGoingOrder());
	}
	
	@Test
	public void convertShoppingOrderNumberOfOrderStatusDTO() {
		ShoppingOrderNumberOfOrderStatusBO expected = new ShoppingOrderNumberOfOrderStatusBO();
		expected.setBeShippedCount(1L);
		expected.setEvaluationCount(1L);
		expected.setPendingPaymentCount(1L);
		expected.setRefundingCount(1L);
		expected.setToBeReceivedCount(1L);
		ShoppingOrderNumberOfOrderStatusDTO actual = ShoppingOrderConverter.convert(expected);
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getBeShippedCount(), actual.getBeShippedCount());
		Assert.assertEquals(expected.getEvaluationCount(), actual.getEvaluationCount());
		Assert.assertEquals(expected.getPendingPaymentCount(), actual.getPendingPaymentCount());
		Assert.assertEquals(expected.getRefundingCount(), actual.getRefundingCount());
		Assert.assertEquals(expected.getToBeReceivedCount(), actual.getToBeReceivedCount());
	}
	
	@Test
	public void convertShoppingOrderNumberOfOrderStatusForMerchantForeignDTO() {
		ShoppingOrderNumberOfOrderStatusForMerchantBO expected = new ShoppingOrderNumberOfOrderStatusForMerchantBO();
		expected.setBeShippedCount(1L);
		expected.setPendingPaymentCount(1L);
		expected.setRefundingCount(1L);
		expected.setToBeReceivedCount(1L);
		ShoppingOrderNumberOfOrderStatusForMerchantForeignDTO actual = ShoppingOrderConverter.convert(expected);
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getBeShippedCount(), actual.getBeShippedCount());
		Assert.assertEquals(expected.getPendingPaymentCount(), actual.getPendingPaymentCount());
		Assert.assertEquals(expected.getRefundingCount(), actual.getRefundingCount());
		Assert.assertEquals(expected.getToBeReceivedCount(), actual.getToBeReceivedCount());
	}
	
	@Test
	public void convertReportRiseRerouceDTOList() {
		List<ReportFansSaleTransFormDO> expected = new ArrayList<>();
		ReportFansSaleTransFormDO reportFansSaleTransFormDO = new ReportFansSaleTransFormDO();
		reportFansSaleTransFormDO.setIsFans("1");
		reportFansSaleTransFormDO.setCount(1L);
		expected.add(reportFansSaleTransFormDO);
		reportFansSaleTransFormDO = new ReportFansSaleTransFormDO();
		reportFansSaleTransFormDO.setIsFans("0");
		reportFansSaleTransFormDO.setCount(1L);
		expected.add(reportFansSaleTransFormDO);
		
		List<ReportRiseRerouceDTO> actual = ShoppingOrderConverter.convertReportRiseRerouceDTOList(expected);
		Assert.assertNotNull(actual);
		for (ReportRiseRerouceDTO item : actual) {
			Assert.assertNotNull(item);
			if ("is_fans".equals(item.getName())) {
				Assert.assertEquals("1", item.getValue());
			}
			if ("no_fans".equals(item.getName())) {
				Assert.assertEquals("1", item.getValue());
			}
		}
	}
	
	@Test
	public void convertShoppingOrderDOByUpdate() {
    	ShoppingOrderUpdateInfomationParam expected = initShoppingOrderUpdateInfomationParam();
		Long id = 1L;
		ShoppingOrderDO actual = ShoppingOrderConverter.convert(id, expected);
		assertShoppingOrderDOByUpdate(expected, actual, id);
	}
	
	@Test
	public void convertShoppingOrderCommissionDTOList() {
		List<ShoppingOrderBO> expected = new ArrayList<>();
		expected.add(initShoppingOrderBO());
		List<ShoppingOrderCommissionDTO> actual = ShoppingOrderConverter.convertShoppingOrderCommissionDTOList(expected);
		assertShoppingOrderCommissionDTOList(expected, actual);
	}
	
	@Test
	public void convertShoppingOrderExpressDTO() {
		ShoppingOrderBO expected = initShoppingOrderBO();
		ExpressInquiriesDetailBO expectedExpressInquiriesDetailBO = ExpressConverterTest.initExpressInquiriesDetailBO();
		ShoppingOrderExpressDTO actual = ShoppingOrderConverter.covert(expected, expectedExpressInquiriesDetailBO);
		assertShoppingOrderExpressDTO(expected, actual);
		ExpressConverterTest.assertExpressInquiriesDetailDTO(expectedExpressInquiriesDetailBO, actual.getExpressInquiriesDetailDTO());
	}
	
	public static void assertShoppingOrderExpressDTO(ShoppingOrderBO expected, ShoppingOrderExpressDTO actual) {
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(expected.getExpressCompanyName(), actual.getExpressCompanyName());
    	Assert.assertEquals(expected.getWaybillNum(), actual.getWaybillNum());
    }
	
	public static void assertShoppingOrderCommissionDTOList(List<ShoppingOrderBO> expected, List<ShoppingOrderCommissionDTO> actual) {
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(expected.size(), actual.size());
    	for (int i = 0; i < expected.size(); i++) {
    		ShoppingOrderBO shoppingOrderBO = expected.get(i);
    		ShoppingOrderCommissionDTO shoppingOrderCommissionDTO = actual.get(i);
	    	Assert.assertEquals(shoppingOrderBO.getMemberNum(), shoppingOrderCommissionDTO.getMemberNum());
	    	Assert.assertEquals(shoppingOrderBO.getId(), shoppingOrderCommissionDTO.getId());
	    	Assert.assertEquals(shoppingOrderBO.getActualAmount().doubleValue(), shoppingOrderCommissionDTO.getActualAmount().doubleValue(), 0D);
	    	Assert.assertEquals(shoppingOrderBO.getMerchantNum(), shoppingOrderCommissionDTO	.getMerchantNum());
    	}
    }
	
    public static void assertShoppingOrderDOByUpdate(ShoppingOrderUpdateInfomationParam expected, ShoppingOrderDO actual, Long id) {
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(expected.getConsigneeAddress(), actual.getConsigneeAddress());
    	Assert.assertEquals(expected.getConsigneeMobile(), actual.getConsigneeMobile());
    	Assert.assertEquals(expected.getConsigneeName(), actual.getConsigneeName());
    	Assert.assertEquals(expected.getOrderStatus().getValue(), actual.getOrderStatus());
    	Assert.assertEquals(expected.getExpressCompanyCode(), actual.getExpressCompanyCode());
    	Assert.assertEquals(expected.getExpressCompanyId(), actual.getExpressCompanyId());
    	Assert.assertEquals(expected.getExpressCompanyName(), actual.getExpressCompanyName());
    	Assert.assertEquals(expected.getWaybillNum(), actual.getWaybillNum());
    }
	
    public static void assertShoppingOrderDO(ShoppingOrderSettlementParam expected, ShoppingOrderDO actual) {
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(expected.getConsigneeAddress(), actual.getConsigneeAddress());
    	Assert.assertEquals(expected.getConsigneeMobile(), actual.getConsigneeMobile());
    	Assert.assertEquals(expected.getConsigneeName(), actual.getConsigneeName());
    	Assert.assertEquals(expected.getMemberNum(), actual.getMemberNum());
    	Assert.assertEquals(expected.getMerchantName(), actual.getMerchantName());
    	Assert.assertEquals(expected.getMerchantNum(), actual.getMerchantNum());
    	Assert.assertEquals(expected.getMessage(), actual.getMessage());
    	Assert.assertEquals(expected.getCommodityTotalPrice().doubleValue(), actual.getCommodityTotalPrice().doubleValue(), 0D);
    	Assert.assertEquals(expected.getFreightPrice().doubleValue(), actual.getFreightPrice().doubleValue(), 0D);
    	Assert.assertEquals(expected.getIsFans(), actual.getIsFans());
    	Assert.assertEquals(expected.getIsNoReasonReturn(), actual.getIsNoReasonReturn());
    	Assert.assertEquals(expected.getMemberId(), actual.getMemberId());
    	Assert.assertEquals(expected.getMerchantId(), actual.getMerchantId());
    	Assert.assertEquals(expected.getMerchantStoreId(), actual.getMerchantStoreId());
    	Assert.assertEquals(expected.getOrderTotalPrice().doubleValue(), actual.getOrderTotalPrice().doubleValue(), 0D);
    	Assert.assertEquals(expected.getItems().get(0).getShoppingCartId().toString(), actual.getShoppingCartIdsStr());
    	Assert.assertEquals(ShoppingOrderStatusEnum.PENDING.getValue(), actual.getOrderStatus());
    	Assert.assertEquals(StatusEnum.VALID.getValue(), actual.getStatus());
    }
	
    public static void assertShoppingOrderIsNoOnGoingOrderBO(Long expected, ShoppingOrderIsNoOnGoingOrderBO actual) {
    	Assert.assertNotNull(actual);
    	boolean isNoGoingOrder = false;
    	if (expected == null || expected < 0) {
    		isNoGoingOrder = true;
    	}
    	Assert.assertEquals(isNoGoingOrder, actual.getIsNoOnGoingOrder());
    }
    
    public static void assertShoppingOrderBO(ShoppingOrderDO expected, ShoppingOrderBO actual){
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(expected.getActualAmount().doubleValue(), actual.getActualAmount().doubleValue(), 0D);
    	Assert.assertEquals(expected.getCommissionStatus(), actual.getCommissionStatus().getValue());
    	Assert.assertEquals(expected.getCommodityTotalPrice().doubleValue(), actual.getCommodityTotalPrice().doubleValue(), 0D);
    	Assert.assertEquals(expected.getConsigneeAddress(), actual.getConsigneeAddress());
    	Assert.assertEquals(expected.getConsigneeMobile(), actual.getConsigneeMobile());
    	Assert.assertEquals(expected.getConsigneeName(), actual.getConsigneeName());
    	Assert.assertEquals(expected.getExpressCompanyCode(), actual.getExpressCompanyCode());
    	Assert.assertEquals(expected.getExpressCompanyId(), actual.getExpressCompanyId());
    	Assert.assertEquals(expected.getExpressCompanyName(), actual.getExpressCompanyName());
    	Assert.assertEquals(expected.getFreightPrice().doubleValue(), actual.getFreightPrice().doubleValue(), 0D);
    	Assert.assertEquals(expected.getGmtCommission() != null ? expected.getGmtCommission().getTime() : null, actual.getGmtCommission() != null ? actual.getGmtCommission().getTime() : null);
    	Assert.assertEquals(expected.getGmtCreate().getTime(), actual.getGmtCreate().getTime());
    	Assert.assertEquals(expected.getGmtModified().getTime(), actual.getGmtModified().getTime());
    	Assert.assertEquals(expected.getGmtPayment() != null ? expected.getGmtPayment().getTime() : null, actual.getGmtPayment() != null ? actual.getGmtPayment().getTime() : null);
    	Assert.assertEquals(expected.getId(), actual.getId());
    	Assert.assertEquals(expected.getIsAutomaticReceipt(), actual.getIsAutomaticReceipt());
    	Assert.assertEquals(expected.getIsDone(), actual.getIsDone());
    	Assert.assertEquals(expected.getIsFans(), actual.getIsFans());
    	Assert.assertEquals(expected.getIsNeedsLogistics(), actual.getIsNeedsLogistics());
    	Assert.assertEquals(expected.getIsNoReasonReturn(), actual.getIsNoReasonReturn());
    	Assert.assertEquals(expected.getMemberId(), actual.getMemberId());
    	Assert.assertEquals(expected.getMemberNum(), actual.getMemberNum());
    	Assert.assertEquals(expected.getMerchantId(), actual.getMerchantId());
    	Assert.assertEquals(expected.getMerchantName(), actual.getMerchantName());
    	Assert.assertEquals(expected.getMerchantNum(), actual.getMerchantNum());
    	Assert.assertEquals(expected.getMerchantStoreId(), actual.getMerchantStoreId());
    	Assert.assertEquals(expected.getOrderNum(), actual.getOrderNum());
    	Assert.assertEquals(expected.getOrderStatus(), actual.getOrderStatus().getValue());
    	Assert.assertEquals(expected.getOrderTotalPrice().doubleValue(), actual.getOrderTotalPrice().doubleValue(), 0D);
    	Assert.assertEquals(expected.getPaymentMethod(), actual.getPaymentMethod() != null ? actual.getPaymentMethod().getVal() : null);
    	Assert.assertEquals(expected.getSendTime(), actual.getSendTime());
    	Assert.assertEquals(expected.getShoppingCartIdsStr(), actual.getShoppingCartIdsStr());
    	Assert.assertEquals(expected.getStatus(), actual.getStatus().getValue());
    	Assert.assertEquals(expected.getThirdNumber(), actual.getThirdNumber());
    	Assert.assertEquals(expected.getWaybillNum(), actual.getWaybillNum());
    	Assert.assertEquals(expected.getGmtTransaction() != null ? expected.getGmtTransaction().getTime() : null, expected.getGmtTransaction() != null ? actual.getGmtTransaction().getTime() : null);
    	Assert.assertEquals(expected.getGmtTransport() != null ? expected.getGmtTransport().getTime() : null, actual.getGmtTransport() != null ? actual.getGmtTransport().getTime() : null);
    }
    
    public static ShoppingOrderUpdateInfomationParam initShoppingOrderUpdateInfomationParam() {
    	ShoppingOrderUpdateInfomationParam rtn = new ShoppingOrderUpdateInfomationParam();
    	rtn.setConsigneeAddress("大冲商务中心1301");
    	rtn.setConsigneeMobile("123456");
    	rtn.setConsigneeName("Sunny");
    	rtn.setExpressCompanyCode("STO");
    	rtn.setExpressCompanyId(2);
    	rtn.setExpressCompanyName("申通");
    	rtn.setWaybillNum("654321");
    	rtn.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS);
    	return rtn;
    }
    
    public static ShoppingOrderSettlementParam initShoppingOrderSettlementParam() {
    	ShoppingOrderSettlementParam rtn = new ShoppingOrderSettlementParam();
    	rtn.setCommodityTotalPrice(new BigDecimal(1));
    	rtn.setConsigneeAddress("大冲商务中心");
    	rtn.setConsigneeMobile("123456");
    	rtn.setConsigneeName("Sunny");
    	rtn.setFreightPrice(new BigDecimal(0));
    	rtn.setIsFans(false);
    	rtn.setIsNoReasonReturn(false);
    	rtn.setMemberId(1L);
    	rtn.setMemberNum("M00001");
    	rtn.setMerchantId(1L);
    	rtn.setMerchantName("拉乌网络");
    	rtn.setMerchantNum("B0001");
    	rtn.setMerchantStoreId(1L);
    	rtn.setOrderTotalPrice(new BigDecimal(1));
    	
    	List<ShoppingOrderSettlementItemParam> items = new ArrayList<>();
    	items.add(ShoppingOrderItemConverterTest.initShoppingOrderSettlementItemParam());
    	rtn.setItems(items);
    	return rtn;
    }
    
    private ShoppingOrderDO initShoppingOrderDO() {
    	ShoppingOrderDO rtn  = new ShoppingOrderDO();
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
    	rtn.setOrderStatus(ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue());
    	rtn.setOrderTotalPrice(new BigDecimal(1));
    	rtn.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	rtn.setStatus(StatusEnum.VALID.getValue());
    	rtn.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	return rtn;
	}
    
    private ShoppingOrderBO initShoppingOrderBO() {
    	ShoppingOrderBO rtn  = new ShoppingOrderBO();
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
    	return rtn;
	}
}
