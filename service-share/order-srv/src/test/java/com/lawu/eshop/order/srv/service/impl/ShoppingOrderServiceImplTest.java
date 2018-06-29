package com.lawu.eshop.order.srv.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.mq.dto.order.reply.ShoppingOrderCreateOrderReply;
import com.lawu.eshop.mq.dto.property.ShoppingOrderPaymentNotification;
import com.lawu.eshop.order.constants.CommissionStatusEnum;
import com.lawu.eshop.order.constants.RefundStatusEnum;
import com.lawu.eshop.order.constants.ReportFansRiseRateEnum;
import com.lawu.eshop.order.constants.ShoppingOrderStatusEnum;
import com.lawu.eshop.order.constants.ShoppingOrderStatusToMemberEnum;
import com.lawu.eshop.order.constants.ShoppingOrderStatusToMerchantEnum;
import com.lawu.eshop.order.constants.ShoppingRefundTypeEnum;
import com.lawu.eshop.order.dto.ReportRiseRateDTO;
import com.lawu.eshop.order.dto.ReportRiseRerouceDTO;
import com.lawu.eshop.order.param.ReportDataParam;
import com.lawu.eshop.order.param.ShoppingOrderRequestRefundParam;
import com.lawu.eshop.order.param.ShoppingOrderSettlementItemParam;
import com.lawu.eshop.order.param.ShoppingOrderSettlementParam;
import com.lawu.eshop.order.param.ShoppingOrderUpdateInfomationParam;
import com.lawu.eshop.order.param.foreign.ShoppingOrderQueryForeignToMemberParam;
import com.lawu.eshop.order.param.foreign.ShoppingOrderQueryForeignToMerchantParam;
import com.lawu.eshop.order.param.foreign.ShoppingOrderQueryForeignToOperatorParam;
import com.lawu.eshop.order.srv.OrderSrvApplicationTest;
import com.lawu.eshop.order.srv.bo.ShoppingOrderBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderExtendBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderIsNoOnGoingOrderBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderItemBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderMoneyBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderNumberOfOrderStatusBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderNumberOfOrderStatusForMerchantBO;
import com.lawu.eshop.order.srv.constants.PropertyNameConstant;
import com.lawu.eshop.order.srv.converter.ShoppingOrderConverterTest;
import com.lawu.eshop.order.srv.converter.ShoppingOrderItemConverterTest;
import com.lawu.eshop.order.srv.domain.PropertyDO;
import com.lawu.eshop.order.srv.domain.ShoppingCartDO;
import com.lawu.eshop.order.srv.domain.ShoppingOrderDO;
import com.lawu.eshop.order.srv.domain.ShoppingOrderDOExample;
import com.lawu.eshop.order.srv.domain.ShoppingOrderItemDO;
import com.lawu.eshop.order.srv.domain.ShoppingOrderItemDOExample;
import com.lawu.eshop.order.srv.domain.ShoppingRefundDetailDO;
import com.lawu.eshop.order.srv.domain.ShoppingRefundDetailDOExample;
import com.lawu.eshop.order.srv.domain.ShoppingRefundProcessDO;
import com.lawu.eshop.order.srv.domain.ShoppingRefundProcessDOExample;
import com.lawu.eshop.order.srv.domain.extend.ShoppingOrderItemExtendDO;
import com.lawu.eshop.order.srv.mapper.PropertyDOMapper;
import com.lawu.eshop.order.srv.mapper.ShoppingCartDOMapper;
import com.lawu.eshop.order.srv.mapper.ShoppingOrderDOMapper;
import com.lawu.eshop.order.srv.mapper.ShoppingOrderItemDOMapper;
import com.lawu.eshop.order.srv.mapper.ShoppingRefundDetailDOMapper;
import com.lawu.eshop.order.srv.mapper.ShoppingRefundProcessDOMapper;
import com.lawu.eshop.order.srv.service.ShoppingOrderService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

/**
 * 
 * @author jiangxinjun
 * @date 2017年7月12日
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderSrvApplicationTest.class)
public class ShoppingOrderServiceImplTest {
	
    @Autowired
    private ShoppingOrderService shoppingOrderService;
    
    @Autowired
    private ShoppingCartDOMapper shoppingCartDOMapper;
    
	@Autowired
	private ShoppingOrderDOMapper shoppingOrderDOMapper;
	
	@Autowired
	private ShoppingOrderItemDOMapper shoppingOrderItemDOMapper;
	
	@Autowired
	private ShoppingRefundDetailDOMapper shoppingRefundDetailDOMapper;
	
	@Autowired
	private PropertyDOMapper propertyDOMapper;
	
	@Autowired
	private ShoppingRefundProcessDOMapper shoppingRefundProcessDOMapper;
	
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void cancelOrder() {
    	ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("merchantName");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(false);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	shoppingOrderService.cancelOrder(expected.getMemberId(), expected.getId());
    	
    	ShoppingOrderDO actual = shoppingOrderDOMapper.selectByPrimaryKey(expected.getId());
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(ShoppingOrderStatusEnum.CANCEL_TRANSACTION.getValue(), actual.getOrderStatus());
    	ShoppingOrderItemDO actualShoppingOrderItemDO = shoppingOrderItemDOMapper.selectByPrimaryKey(shoppingOrderItemDO.getId());
    	Assert.assertNotNull(actualShoppingOrderItemDO);
    	Assert.assertEquals(ShoppingOrderStatusEnum.CANCEL_TRANSACTION.getValue(), actualShoppingOrderItemDO.getOrderStatus());
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void commissionShoppingOrder() {
    	ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setExpressCompanyCode("韵达");
    	expected.setExpressCompanyId(1);
    	expected.setExpressCompanyName("韵达");
    	expected.setGmtPayment(new Date());
    	expected.setGmtTransaction(new Date());
    	expected.setGmtTransport(new Date());
    	expected.setIsDone(true);
    	expected.setThirdNumber("654321");
    	expected.setWaybillNum("3923440690592");
    	expected.setGmtCommission(new Date());
    	expected.setShoppingCartIdsStr("1,2");
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	expected.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	List<ShoppingOrderBO> result = shoppingOrderService.commissionShoppingOrder(0,10);
    	Assert.assertNotNull(result);
    	for (ShoppingOrderBO actual : result) {
    		ShoppingOrderConverterTest.assertShoppingOrderBO(expected, actual);
    	}
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void fansSaleTransform() {
    	ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setExpressCompanyCode("韵达");
    	expected.setExpressCompanyId(1);
    	expected.setExpressCompanyName("韵达");
    	expected.setGmtPayment(new Date());
    	expected.setGmtTransaction(new Date());
    	expected.setGmtTransport(new Date());
    	expected.setIsDone(true);
    	expected.setThirdNumber("654321");
    	expected.setWaybillNum("3923440690592");
    	expected.setGmtCommission(new Date());
    	expected.setShoppingCartIdsStr("1,2");
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	ReportDataParam param = new ReportDataParam();
    	param.setFlag(ReportFansRiseRateEnum.DAY);
    	param.setMerchantId(expected.getMerchantId());
    	List<ReportRiseRerouceDTO> result = shoppingOrderService.fansSaleTransform(param);
    	Assert.assertNotNull(result);
    	for (ReportRiseRerouceDTO actual : result) {
    		if ("is_fans".equals(actual.getName())) {
    			Assert.assertEquals("1", actual.getValue());
    		}
    		
    		if ("no_fans".equals(actual.getName())) {
    			Assert.assertEquals("0", actual.getValue());
    		}
    	}
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void get() {
    	ShoppingOrderDO shoppingOrderDO  = new ShoppingOrderDO();
    	shoppingOrderDO.setCommodityTotalPrice(new BigDecimal(1));
    	shoppingOrderDO.setActualAmount(new BigDecimal(1));
    	shoppingOrderDO.setFreightPrice(new BigDecimal(0));
    	shoppingOrderDO.setGmtCreate(new Date());
    	shoppingOrderDO.setGmtModified(new Date());
    	shoppingOrderDO.setIsFans(true);
    	shoppingOrderDO.setIsNeedsLogistics(true);
    	shoppingOrderDO.setIsNoReasonReturn(true);
    	shoppingOrderDO.setMemberId(1L);
    	shoppingOrderDO.setMemberNum("M0001");
    	shoppingOrderDO.setMerchantId(1L);
    	shoppingOrderDO.setMerchantName("拉乌网络");
    	shoppingOrderDO.setMerchantStoreId(1L);
    	shoppingOrderDO.setMerchantNum("B0001");
    	shoppingOrderDO.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	shoppingOrderDO.setOrderTotalPrice(new BigDecimal(1));
    	shoppingOrderDO.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	shoppingOrderDO.setStatus(StatusEnum.VALID.getValue());
    	shoppingOrderDO.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	shoppingOrderDO.setConsigneeAddress("大冲商务中心1301");
    	shoppingOrderDO.setConsigneeMobile("123456");
    	shoppingOrderDO.setConsigneeName("Sunny");
    	shoppingOrderDO.setExpressCompanyCode("韵达");
    	shoppingOrderDO.setExpressCompanyId(1);
    	shoppingOrderDO.setExpressCompanyName("韵达");
    	shoppingOrderDO.setGmtPayment(new Date());
    	shoppingOrderDO.setGmtTransaction(new Date());
    	shoppingOrderDO.setGmtTransport(new Date());
    	shoppingOrderDO.setIsDone(true);
    	shoppingOrderDO.setThirdNumber("654321");
    	shoppingOrderDO.setWaybillNum("3923440690592");
    	shoppingOrderDO.setGmtCommission(new Date());
    	shoppingOrderDO.setShoppingCartIdsStr("1,2");
    	shoppingOrderDO.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	shoppingOrderDO.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(shoppingOrderDO);
    	
    	Map<Long, ShoppingOrderItemDO> expectedMap = new HashMap<>();
    	// 交易成功
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(true);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(shoppingOrderDO.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	expectedMap.put(shoppingOrderItemDO.getId(), shoppingOrderItemDO);
    	
    	// 退款成功
    	shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(true);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.CANCEL_TRANSACTION.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(2L);
    	shoppingOrderItemDO.setProductModelName("productModelName");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(shoppingOrderDO.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	expectedMap.put(shoppingOrderItemDO.getId(), shoppingOrderItemDO);
    	
    	ShoppingOrderExtendBO result = shoppingOrderService.get(shoppingOrderDO.getId());
    	ShoppingOrderConverterTest.assertShoppingOrderBO(shoppingOrderDO, result);
    	for (ShoppingOrderItemBO actual : result.getItems()) {
    		ShoppingOrderItemConverterTest.assertShoppingOrderItemBO(expectedMap.get(actual.getId()), actual);
    	}
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void get1() {
    	ShoppingOrderDO shoppingOrderDO  = new ShoppingOrderDO();
    	shoppingOrderDO.setCommodityTotalPrice(new BigDecimal(1));
    	shoppingOrderDO.setActualAmount(new BigDecimal(1));
    	shoppingOrderDO.setFreightPrice(new BigDecimal(0));
    	shoppingOrderDO.setGmtCreate(new Date());
    	shoppingOrderDO.setGmtModified(new Date());
    	shoppingOrderDO.setIsFans(true);
    	shoppingOrderDO.setIsNeedsLogistics(true);
    	shoppingOrderDO.setIsNoReasonReturn(true);
    	shoppingOrderDO.setMemberId(1L);
    	shoppingOrderDO.setMemberNum("M0001");
    	shoppingOrderDO.setMerchantId(1L);
    	shoppingOrderDO.setMerchantName("拉乌网络");
    	shoppingOrderDO.setMerchantStoreId(1L);
    	shoppingOrderDO.setMerchantNum("B0001");
    	shoppingOrderDO.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	shoppingOrderDO.setOrderTotalPrice(new BigDecimal(1));
    	shoppingOrderDO.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	shoppingOrderDO.setStatus(StatusEnum.VALID.getValue());
    	shoppingOrderDO.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	shoppingOrderDO.setConsigneeAddress("大冲商务中心1301");
    	shoppingOrderDO.setConsigneeMobile("123456");
    	shoppingOrderDO.setConsigneeName("Sunny");
    	shoppingOrderDO.setExpressCompanyCode("韵达");
    	shoppingOrderDO.setExpressCompanyId(1);
    	shoppingOrderDO.setExpressCompanyName("韵达");
    	shoppingOrderDO.setGmtPayment(new Date());
    	shoppingOrderDO.setGmtTransaction(new Date());
    	shoppingOrderDO.setGmtTransport(new Date());
    	shoppingOrderDO.setIsDone(true);
    	shoppingOrderDO.setThirdNumber("654321");
    	shoppingOrderDO.setWaybillNum("3923440690592");
    	shoppingOrderDO.setGmtCommission(new Date());
    	shoppingOrderDO.setShoppingCartIdsStr("1,2");
    	shoppingOrderDO.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	shoppingOrderDO.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(shoppingOrderDO);
    	
    	Map<Long, ShoppingOrderItemDO> expectedMap = new HashMap<>();
    	// 交易成功
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(true);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(shoppingOrderDO.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	expectedMap.put(shoppingOrderItemDO.getId(), shoppingOrderItemDO);
    	
    	// 退款成功
    	shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(true);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.CANCEL_TRANSACTION.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(2L);
    	shoppingOrderItemDO.setProductModelName("productModelName");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(shoppingOrderDO.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	expectedMap.put(shoppingOrderItemDO.getId(), shoppingOrderItemDO);
    	
    	ShoppingOrderExtendBO result = shoppingOrderService.get(shoppingOrderDO.getId(), shoppingOrderDO.getMemberId(), null);
    	ShoppingOrderConverterTest.assertShoppingOrderBO(shoppingOrderDO, result);
    	for (ShoppingOrderItemBO actual : result.getItems()) {
    		ShoppingOrderItemConverterTest.assertShoppingOrderItemBO(expectedMap.get(actual.getId()), actual);
    	}
    	
    	result = shoppingOrderService.get(shoppingOrderDO.getId(), null, shoppingOrderDO.getMerchantId());
    	ShoppingOrderConverterTest.assertShoppingOrderBO(shoppingOrderDO, result);
    	for (ShoppingOrderItemBO actual : result.getItems()) {
    		ShoppingOrderItemConverterTest.assertShoppingOrderItemBO(expectedMap.get(actual.getId()), actual);
    	}
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getShoppingOrder() {
    	ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setExpressCompanyCode("韵达");
    	expected.setExpressCompanyId(1);
    	expected.setExpressCompanyName("韵达");
    	expected.setGmtPayment(new Date());
    	expected.setGmtTransaction(new Date());
    	expected.setGmtTransport(new Date());
    	expected.setIsDone(true);
    	expected.setThirdNumber("654321");
    	expected.setWaybillNum("3923440690592");
    	expected.setGmtCommission(new Date());
    	expected.setShoppingCartIdsStr("1,2");
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	expected.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	ShoppingOrderBO actual = shoppingOrderService.getShoppingOrder(expected.getId());
    	ShoppingOrderConverterTest.assertShoppingOrderBO(expected, actual);
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getShoppingOrder1() {
    	ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setExpressCompanyCode("韵达");
    	expected.setExpressCompanyId(1);
    	expected.setExpressCompanyName("韵达");
    	expected.setGmtPayment(new Date());
    	expected.setGmtTransaction(new Date());
    	expected.setGmtTransport(new Date());
    	expected.setIsDone(true);
    	expected.setThirdNumber("654321");
    	expected.setWaybillNum("3923440690592");
    	expected.setGmtCommission(new Date());
    	expected.setShoppingCartIdsStr("1,2");
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	expected.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	ShoppingOrderBO actual = shoppingOrderService.getShoppingOrder(expected.getId(), expected.getMemberId(), null);
    	ShoppingOrderConverterTest.assertShoppingOrderBO(expected, actual);
    	
    	actual = shoppingOrderService.getShoppingOrder(expected.getId(), null, expected.getMerchantId());
    	ShoppingOrderConverterTest.assertShoppingOrderBO(expected, actual);
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void isNoOnGoingOrder() {
    	ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setExpressCompanyCode("韵达");
    	expected.setExpressCompanyId(1);
    	expected.setExpressCompanyName("韵达");
    	expected.setGmtPayment(new Date());
    	expected.setGmtTransaction(new Date());
    	expected.setGmtTransport(new Date());
    	expected.setIsDone(true);
    	expected.setThirdNumber("654321");
    	expected.setWaybillNum("3923440690592");
    	expected.setGmtCommission(new Date());
    	expected.setShoppingCartIdsStr("1,2");
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	expected.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	// 交易成功
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(true);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.REFUNDING.getValue());
    	shoppingOrderItemDO.setRefundStatus(RefundStatusEnum.TO_BE_REFUNDED.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	ShoppingOrderIsNoOnGoingOrderBO actual = shoppingOrderService.isNoOnGoingOrder(expected.getMerchantId());
    	ShoppingOrderConverterTest.assertShoppingOrderIsNoOnGoingOrderBO(1L, actual);
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void numberOfOrderStartus() {
    	// 待付款
    	ShoppingOrderDO shoppingOrderDO  = new ShoppingOrderDO();
    	shoppingOrderDO.setCommodityTotalPrice(new BigDecimal(1));
    	shoppingOrderDO.setActualAmount(new BigDecimal(1));
    	shoppingOrderDO.setFreightPrice(new BigDecimal(0));
    	shoppingOrderDO.setGmtCreate(new Date());
    	shoppingOrderDO.setGmtModified(new Date());
    	shoppingOrderDO.setIsFans(true);
    	shoppingOrderDO.setIsNeedsLogistics(true);
    	shoppingOrderDO.setIsNoReasonReturn(true);
    	shoppingOrderDO.setMemberId(1L);
    	shoppingOrderDO.setMemberNum("M0001");
    	shoppingOrderDO.setMerchantId(1L);
    	shoppingOrderDO.setMerchantName("拉乌网络");
    	shoppingOrderDO.setMerchantStoreId(1L);
    	shoppingOrderDO.setMerchantNum("B0001");
    	shoppingOrderDO.setOrderStatus(ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue());
    	shoppingOrderDO.setOrderTotalPrice(new BigDecimal(1));
    	shoppingOrderDO.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	shoppingOrderDO.setStatus(StatusEnum.VALID.getValue());
    	shoppingOrderDO.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	shoppingOrderDO.setConsigneeAddress("大冲商务中心1301");
    	shoppingOrderDO.setConsigneeMobile("123456");
    	shoppingOrderDO.setConsigneeName("Sunny");
    	shoppingOrderDO.setExpressCompanyCode("韵达");
    	shoppingOrderDO.setExpressCompanyId(1);
    	shoppingOrderDO.setExpressCompanyName("韵达");
    	shoppingOrderDO.setGmtPayment(new Date());
    	shoppingOrderDO.setGmtTransaction(new Date());
    	shoppingOrderDO.setGmtTransport(new Date());
    	shoppingOrderDO.setIsDone(true);
    	shoppingOrderDO.setThirdNumber("654321");
    	shoppingOrderDO.setWaybillNum("3923440690592");
    	shoppingOrderDO.setGmtCommission(new Date());
    	shoppingOrderDO.setShoppingCartIdsStr("1,2");
    	shoppingOrderDO.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	shoppingOrderDO.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(shoppingOrderDO);
    	
    	// 待发货
    	shoppingOrderDO  = new ShoppingOrderDO();
    	shoppingOrderDO.setCommodityTotalPrice(new BigDecimal(1));
    	shoppingOrderDO.setActualAmount(new BigDecimal(1));
    	shoppingOrderDO.setFreightPrice(new BigDecimal(0));
    	shoppingOrderDO.setGmtCreate(new Date());
    	shoppingOrderDO.setGmtModified(new Date());
    	shoppingOrderDO.setIsFans(true);
    	shoppingOrderDO.setIsNeedsLogistics(true);
    	shoppingOrderDO.setIsNoReasonReturn(true);
    	shoppingOrderDO.setMemberId(1L);
    	shoppingOrderDO.setMemberNum("M0001");
    	shoppingOrderDO.setMerchantId(1L);
    	shoppingOrderDO.setMerchantName("拉乌网络");
    	shoppingOrderDO.setMerchantStoreId(1L);
    	shoppingOrderDO.setMerchantNum("B0001");
    	shoppingOrderDO.setOrderStatus(ShoppingOrderStatusEnum.BE_SHIPPED.getValue());
    	shoppingOrderDO.setOrderTotalPrice(new BigDecimal(1));
    	shoppingOrderDO.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	shoppingOrderDO.setStatus(StatusEnum.VALID.getValue());
    	shoppingOrderDO.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	shoppingOrderDO.setConsigneeAddress("大冲商务中心1301");
    	shoppingOrderDO.setConsigneeMobile("123456");
    	shoppingOrderDO.setConsigneeName("Sunny");
    	shoppingOrderDO.setExpressCompanyCode("韵达");
    	shoppingOrderDO.setExpressCompanyId(1);
    	shoppingOrderDO.setExpressCompanyName("韵达");
    	shoppingOrderDO.setGmtPayment(new Date());
    	shoppingOrderDO.setGmtTransaction(new Date());
    	shoppingOrderDO.setGmtTransport(new Date());
    	shoppingOrderDO.setIsDone(true);
    	shoppingOrderDO.setThirdNumber("654321");
    	shoppingOrderDO.setWaybillNum("3923440690592");
    	shoppingOrderDO.setGmtCommission(new Date());
    	shoppingOrderDO.setShoppingCartIdsStr("1,2");
    	shoppingOrderDO.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	shoppingOrderDO.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(shoppingOrderDO);
    	
    	// 待收货
    	shoppingOrderDO  = new ShoppingOrderDO();
    	shoppingOrderDO.setCommodityTotalPrice(new BigDecimal(1));
    	shoppingOrderDO.setActualAmount(new BigDecimal(1));
    	shoppingOrderDO.setFreightPrice(new BigDecimal(0));
    	shoppingOrderDO.setGmtCreate(new Date());
    	shoppingOrderDO.setGmtModified(new Date());
    	shoppingOrderDO.setIsFans(true);
    	shoppingOrderDO.setIsNeedsLogistics(true);
    	shoppingOrderDO.setIsNoReasonReturn(true);
    	shoppingOrderDO.setMemberId(1L);
    	shoppingOrderDO.setMemberNum("M0001");
    	shoppingOrderDO.setMerchantId(1L);
    	shoppingOrderDO.setMerchantName("拉乌网络");
    	shoppingOrderDO.setMerchantStoreId(1L);
    	shoppingOrderDO.setMerchantNum("B0001");
    	shoppingOrderDO.setOrderStatus(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue());
    	shoppingOrderDO.setOrderTotalPrice(new BigDecimal(1));
    	shoppingOrderDO.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	shoppingOrderDO.setStatus(StatusEnum.VALID.getValue());
    	shoppingOrderDO.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	shoppingOrderDO.setConsigneeAddress("大冲商务中心1301");
    	shoppingOrderDO.setConsigneeMobile("123456");
    	shoppingOrderDO.setConsigneeName("Sunny");
    	shoppingOrderDO.setExpressCompanyCode("韵达");
    	shoppingOrderDO.setExpressCompanyId(1);
    	shoppingOrderDO.setExpressCompanyName("韵达");
    	shoppingOrderDO.setGmtPayment(new Date());
    	shoppingOrderDO.setGmtTransaction(new Date());
    	shoppingOrderDO.setGmtTransport(new Date());
    	shoppingOrderDO.setIsDone(true);
    	shoppingOrderDO.setThirdNumber("654321");
    	shoppingOrderDO.setWaybillNum("3923440690592");
    	shoppingOrderDO.setGmtCommission(new Date());
    	shoppingOrderDO.setShoppingCartIdsStr("1,2");
    	shoppingOrderDO.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	shoppingOrderDO.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(shoppingOrderDO);
    	
    	// 待评价
    	shoppingOrderDO  = new ShoppingOrderDO();
    	shoppingOrderDO.setCommodityTotalPrice(new BigDecimal(1));
    	shoppingOrderDO.setActualAmount(new BigDecimal(1));
    	shoppingOrderDO.setFreightPrice(new BigDecimal(0));
    	shoppingOrderDO.setGmtCreate(new Date());
    	shoppingOrderDO.setGmtModified(new Date());
    	shoppingOrderDO.setIsFans(true);
    	shoppingOrderDO.setIsNeedsLogistics(true);
    	shoppingOrderDO.setIsNoReasonReturn(true);
    	shoppingOrderDO.setMemberId(1L);
    	shoppingOrderDO.setMemberNum("M0001");
    	shoppingOrderDO.setMerchantId(1L);
    	shoppingOrderDO.setMerchantName("拉乌网络");
    	shoppingOrderDO.setMerchantStoreId(1L);
    	shoppingOrderDO.setMerchantNum("B0001");
    	shoppingOrderDO.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	shoppingOrderDO.setOrderTotalPrice(new BigDecimal(1));
    	shoppingOrderDO.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	shoppingOrderDO.setStatus(StatusEnum.VALID.getValue());
    	shoppingOrderDO.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	shoppingOrderDO.setConsigneeAddress("大冲商务中心1301");
    	shoppingOrderDO.setConsigneeMobile("123456");
    	shoppingOrderDO.setConsigneeName("Sunny");
    	shoppingOrderDO.setExpressCompanyCode("韵达");
    	shoppingOrderDO.setExpressCompanyId(1);
    	shoppingOrderDO.setExpressCompanyName("韵达");
    	shoppingOrderDO.setGmtPayment(new Date());
    	shoppingOrderDO.setGmtTransaction(new Date());
    	shoppingOrderDO.setGmtTransport(new Date());
    	shoppingOrderDO.setIsDone(true);
    	shoppingOrderDO.setThirdNumber("654321");
    	shoppingOrderDO.setWaybillNum("3923440690592");
    	shoppingOrderDO.setGmtCommission(new Date());
    	shoppingOrderDO.setShoppingCartIdsStr("1,2");
    	shoppingOrderDO.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	shoppingOrderDO.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(shoppingOrderDO);
    	
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(false);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(shoppingOrderDO.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	// 待退款
    	shoppingOrderDO  = new ShoppingOrderDO();
    	shoppingOrderDO.setCommodityTotalPrice(new BigDecimal(1));
    	shoppingOrderDO.setActualAmount(new BigDecimal(1));
    	shoppingOrderDO.setFreightPrice(new BigDecimal(0));
    	shoppingOrderDO.setGmtCreate(new Date());
    	shoppingOrderDO.setGmtModified(new Date());
    	shoppingOrderDO.setIsFans(true);
    	shoppingOrderDO.setIsNeedsLogistics(true);
    	shoppingOrderDO.setIsNoReasonReturn(true);
    	shoppingOrderDO.setMemberId(1L);
    	shoppingOrderDO.setMemberNum("M0001");
    	shoppingOrderDO.setMerchantId(1L);
    	shoppingOrderDO.setMerchantName("拉乌网络");
    	shoppingOrderDO.setMerchantStoreId(1L);
    	shoppingOrderDO.setMerchantNum("B0001");
    	shoppingOrderDO.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	shoppingOrderDO.setOrderTotalPrice(new BigDecimal(1));
    	shoppingOrderDO.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	shoppingOrderDO.setStatus(StatusEnum.VALID.getValue());
    	shoppingOrderDO.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	shoppingOrderDO.setConsigneeAddress("大冲商务中心1301");
    	shoppingOrderDO.setConsigneeMobile("123456");
    	shoppingOrderDO.setConsigneeName("Sunny");
    	shoppingOrderDO.setExpressCompanyCode("韵达");
    	shoppingOrderDO.setExpressCompanyId(1);
    	shoppingOrderDO.setExpressCompanyName("韵达");
    	shoppingOrderDO.setGmtPayment(new Date());
    	shoppingOrderDO.setGmtTransaction(new Date());
    	shoppingOrderDO.setGmtTransport(new Date());
    	shoppingOrderDO.setIsDone(true);
    	shoppingOrderDO.setThirdNumber("654321");
    	shoppingOrderDO.setWaybillNum("3923440690592");
    	shoppingOrderDO.setGmtCommission(new Date());
    	shoppingOrderDO.setShoppingCartIdsStr("1,2");
    	shoppingOrderDO.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	shoppingOrderDO.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(shoppingOrderDO);
    	
    	shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(false);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.REFUNDING.getValue());
    	shoppingOrderItemDO.setRefundStatus(RefundStatusEnum.TO_BE_REFUNDED.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(shoppingOrderDO.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	ShoppingOrderNumberOfOrderStatusBO actual = shoppingOrderService.numberOfOrderStartus(shoppingOrderDO.getMemberId());
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(1L, actual.getBeShippedCount().longValue());
    	Assert.assertEquals(1L, actual.getPendingPaymentCount().longValue());
    	Assert.assertEquals(1L, actual.getEvaluationCount().longValue());
    	Assert.assertEquals(1L, actual.getRefundingCount().longValue());
    	Assert.assertEquals(1L, actual.getToBeReceivedCount().longValue());
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void numberOfOrderStartusByMerchant() {
    	// 待付款
    	ShoppingOrderDO shoppingOrderDO  = new ShoppingOrderDO();
    	shoppingOrderDO.setCommodityTotalPrice(new BigDecimal(1));
    	shoppingOrderDO.setActualAmount(new BigDecimal(1));
    	shoppingOrderDO.setFreightPrice(new BigDecimal(0));
    	shoppingOrderDO.setGmtCreate(new Date());
    	shoppingOrderDO.setGmtModified(new Date());
    	shoppingOrderDO.setIsFans(true);
    	shoppingOrderDO.setIsNeedsLogistics(true);
    	shoppingOrderDO.setIsNoReasonReturn(true);
    	shoppingOrderDO.setMemberId(1L);
    	shoppingOrderDO.setMemberNum("M0001");
    	shoppingOrderDO.setMerchantId(1L);
    	shoppingOrderDO.setMerchantName("拉乌网络");
    	shoppingOrderDO.setMerchantStoreId(1L);
    	shoppingOrderDO.setMerchantNum("B0001");
    	shoppingOrderDO.setOrderStatus(ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue());
    	shoppingOrderDO.setOrderTotalPrice(new BigDecimal(1));
    	shoppingOrderDO.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	shoppingOrderDO.setStatus(StatusEnum.VALID.getValue());
    	shoppingOrderDO.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	shoppingOrderDO.setConsigneeAddress("大冲商务中心1301");
    	shoppingOrderDO.setConsigneeMobile("123456");
    	shoppingOrderDO.setConsigneeName("Sunny");
    	shoppingOrderDO.setExpressCompanyCode("韵达");
    	shoppingOrderDO.setExpressCompanyId(1);
    	shoppingOrderDO.setExpressCompanyName("韵达");
    	shoppingOrderDO.setGmtPayment(new Date());
    	shoppingOrderDO.setGmtTransaction(new Date());
    	shoppingOrderDO.setGmtTransport(new Date());
    	shoppingOrderDO.setIsDone(true);
    	shoppingOrderDO.setThirdNumber("654321");
    	shoppingOrderDO.setWaybillNum("3923440690592");
    	shoppingOrderDO.setGmtCommission(new Date());
    	shoppingOrderDO.setShoppingCartIdsStr("1,2");
    	shoppingOrderDO.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	shoppingOrderDO.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(shoppingOrderDO);
    	
    	// 待发货
    	shoppingOrderDO  = new ShoppingOrderDO();
    	shoppingOrderDO.setCommodityTotalPrice(new BigDecimal(1));
    	shoppingOrderDO.setActualAmount(new BigDecimal(1));
    	shoppingOrderDO.setFreightPrice(new BigDecimal(0));
    	shoppingOrderDO.setGmtCreate(new Date());
    	shoppingOrderDO.setGmtModified(new Date());
    	shoppingOrderDO.setIsFans(true);
    	shoppingOrderDO.setIsNeedsLogistics(true);
    	shoppingOrderDO.setIsNoReasonReturn(true);
    	shoppingOrderDO.setMemberId(1L);
    	shoppingOrderDO.setMemberNum("M0001");
    	shoppingOrderDO.setMerchantId(1L);
    	shoppingOrderDO.setMerchantName("拉乌网络");
    	shoppingOrderDO.setMerchantStoreId(1L);
    	shoppingOrderDO.setMerchantNum("B0001");
    	shoppingOrderDO.setOrderStatus(ShoppingOrderStatusEnum.BE_SHIPPED.getValue());
    	shoppingOrderDO.setOrderTotalPrice(new BigDecimal(1));
    	shoppingOrderDO.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	shoppingOrderDO.setStatus(StatusEnum.VALID.getValue());
    	shoppingOrderDO.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	shoppingOrderDO.setConsigneeAddress("大冲商务中心1301");
    	shoppingOrderDO.setConsigneeMobile("123456");
    	shoppingOrderDO.setConsigneeName("Sunny");
    	shoppingOrderDO.setExpressCompanyCode("韵达");
    	shoppingOrderDO.setExpressCompanyId(1);
    	shoppingOrderDO.setExpressCompanyName("韵达");
    	shoppingOrderDO.setGmtPayment(new Date());
    	shoppingOrderDO.setGmtTransaction(new Date());
    	shoppingOrderDO.setGmtTransport(new Date());
    	shoppingOrderDO.setIsDone(true);
    	shoppingOrderDO.setThirdNumber("654321");
    	shoppingOrderDO.setWaybillNum("3923440690592");
    	shoppingOrderDO.setGmtCommission(new Date());
    	shoppingOrderDO.setShoppingCartIdsStr("1,2");
    	shoppingOrderDO.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	shoppingOrderDO.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(shoppingOrderDO);
    	
    	// 待收货
    	shoppingOrderDO  = new ShoppingOrderDO();
    	shoppingOrderDO.setCommodityTotalPrice(new BigDecimal(1));
    	shoppingOrderDO.setActualAmount(new BigDecimal(1));
    	shoppingOrderDO.setFreightPrice(new BigDecimal(0));
    	shoppingOrderDO.setGmtCreate(new Date());
    	shoppingOrderDO.setGmtModified(new Date());
    	shoppingOrderDO.setIsFans(true);
    	shoppingOrderDO.setIsNeedsLogistics(true);
    	shoppingOrderDO.setIsNoReasonReturn(true);
    	shoppingOrderDO.setMemberId(1L);
    	shoppingOrderDO.setMemberNum("M0001");
    	shoppingOrderDO.setMerchantId(1L);
    	shoppingOrderDO.setMerchantName("拉乌网络");
    	shoppingOrderDO.setMerchantStoreId(1L);
    	shoppingOrderDO.setMerchantNum("B0001");
    	shoppingOrderDO.setOrderStatus(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue());
    	shoppingOrderDO.setOrderTotalPrice(new BigDecimal(1));
    	shoppingOrderDO.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	shoppingOrderDO.setStatus(StatusEnum.VALID.getValue());
    	shoppingOrderDO.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	shoppingOrderDO.setConsigneeAddress("大冲商务中心1301");
    	shoppingOrderDO.setConsigneeMobile("123456");
    	shoppingOrderDO.setConsigneeName("Sunny");
    	shoppingOrderDO.setExpressCompanyCode("韵达");
    	shoppingOrderDO.setExpressCompanyId(1);
    	shoppingOrderDO.setExpressCompanyName("韵达");
    	shoppingOrderDO.setGmtPayment(new Date());
    	shoppingOrderDO.setGmtTransaction(new Date());
    	shoppingOrderDO.setGmtTransport(new Date());
    	shoppingOrderDO.setIsDone(true);
    	shoppingOrderDO.setThirdNumber("654321");
    	shoppingOrderDO.setWaybillNum("3923440690592");
    	shoppingOrderDO.setGmtCommission(new Date());
    	shoppingOrderDO.setShoppingCartIdsStr("1,2");
    	shoppingOrderDO.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	shoppingOrderDO.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(shoppingOrderDO);
    	
    	// 待评价
    	shoppingOrderDO  = new ShoppingOrderDO();
    	shoppingOrderDO.setCommodityTotalPrice(new BigDecimal(1));
    	shoppingOrderDO.setActualAmount(new BigDecimal(1));
    	shoppingOrderDO.setFreightPrice(new BigDecimal(0));
    	shoppingOrderDO.setGmtCreate(new Date());
    	shoppingOrderDO.setGmtModified(new Date());
    	shoppingOrderDO.setIsFans(true);
    	shoppingOrderDO.setIsNeedsLogistics(true);
    	shoppingOrderDO.setIsNoReasonReturn(true);
    	shoppingOrderDO.setMemberId(1L);
    	shoppingOrderDO.setMemberNum("M0001");
    	shoppingOrderDO.setMerchantId(1L);
    	shoppingOrderDO.setMerchantName("拉乌网络");
    	shoppingOrderDO.setMerchantStoreId(1L);
    	shoppingOrderDO.setMerchantNum("B0001");
    	shoppingOrderDO.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	shoppingOrderDO.setOrderTotalPrice(new BigDecimal(1));
    	shoppingOrderDO.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	shoppingOrderDO.setStatus(StatusEnum.VALID.getValue());
    	shoppingOrderDO.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	shoppingOrderDO.setConsigneeAddress("大冲商务中心1301");
    	shoppingOrderDO.setConsigneeMobile("123456");
    	shoppingOrderDO.setConsigneeName("Sunny");
    	shoppingOrderDO.setExpressCompanyCode("韵达");
    	shoppingOrderDO.setExpressCompanyId(1);
    	shoppingOrderDO.setExpressCompanyName("韵达");
    	shoppingOrderDO.setGmtPayment(new Date());
    	shoppingOrderDO.setGmtTransaction(new Date());
    	shoppingOrderDO.setGmtTransport(new Date());
    	shoppingOrderDO.setIsDone(true);
    	shoppingOrderDO.setThirdNumber("654321");
    	shoppingOrderDO.setWaybillNum("3923440690592");
    	shoppingOrderDO.setGmtCommission(new Date());
    	shoppingOrderDO.setShoppingCartIdsStr("1,2");
    	shoppingOrderDO.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	shoppingOrderDO.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(shoppingOrderDO);
    	
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(false);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(shoppingOrderDO.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	// 待退款
    	shoppingOrderDO  = new ShoppingOrderDO();
    	shoppingOrderDO.setCommodityTotalPrice(new BigDecimal(1));
    	shoppingOrderDO.setActualAmount(new BigDecimal(1));
    	shoppingOrderDO.setFreightPrice(new BigDecimal(0));
    	shoppingOrderDO.setGmtCreate(new Date());
    	shoppingOrderDO.setGmtModified(new Date());
    	shoppingOrderDO.setIsFans(true);
    	shoppingOrderDO.setIsNeedsLogistics(true);
    	shoppingOrderDO.setIsNoReasonReturn(true);
    	shoppingOrderDO.setMemberId(1L);
    	shoppingOrderDO.setMemberNum("M0001");
    	shoppingOrderDO.setMerchantId(1L);
    	shoppingOrderDO.setMerchantName("拉乌网络");
    	shoppingOrderDO.setMerchantStoreId(1L);
    	shoppingOrderDO.setMerchantNum("B0001");
    	shoppingOrderDO.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	shoppingOrderDO.setOrderTotalPrice(new BigDecimal(1));
    	shoppingOrderDO.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	shoppingOrderDO.setStatus(StatusEnum.VALID.getValue());
    	shoppingOrderDO.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	shoppingOrderDO.setConsigneeAddress("大冲商务中心1301");
    	shoppingOrderDO.setConsigneeMobile("123456");
    	shoppingOrderDO.setConsigneeName("Sunny");
    	shoppingOrderDO.setExpressCompanyCode("韵达");
    	shoppingOrderDO.setExpressCompanyId(1);
    	shoppingOrderDO.setExpressCompanyName("韵达");
    	shoppingOrderDO.setGmtPayment(new Date());
    	shoppingOrderDO.setGmtTransaction(new Date());
    	shoppingOrderDO.setGmtTransport(new Date());
    	shoppingOrderDO.setIsDone(true);
    	shoppingOrderDO.setThirdNumber("654321");
    	shoppingOrderDO.setWaybillNum("3923440690592");
    	shoppingOrderDO.setGmtCommission(new Date());
    	shoppingOrderDO.setShoppingCartIdsStr("1,2");
    	shoppingOrderDO.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	shoppingOrderDO.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(shoppingOrderDO);
    	
    	shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(false);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.REFUNDING.getValue());
    	shoppingOrderItemDO.setRefundStatus(RefundStatusEnum.TO_BE_REFUNDED.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(shoppingOrderDO.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	ShoppingOrderNumberOfOrderStatusForMerchantBO actual = shoppingOrderService.numberOfOrderStartusByMerchant(shoppingOrderDO.getMerchantId());
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(1L, actual.getBeShippedCount().longValue());
    	Assert.assertEquals(1L, actual.getPendingPaymentCount().longValue());
    	Assert.assertEquals(1L, actual.getRefundingCount().longValue());
    	Assert.assertEquals(1L, actual.getToBeReceivedCount().longValue());
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void save() {
    	List<ShoppingOrderSettlementParam> params = new ArrayList<>();
    	ShoppingOrderSettlementParam param = ShoppingOrderConverterTest.initShoppingOrderSettlementParam();
    	ShoppingOrderSettlementItemParam item = param.getItems().get(0);
    	params.add(param);
    	List<Long> actual = shoppingOrderService.save(params);
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(1, actual.size());
    	
    	ShoppingOrderDOExample shoppingOrderDOExample = new ShoppingOrderDOExample();
    	shoppingOrderDOExample.createCriteria().andMemberIdEqualTo(param.getMemberId());
    	ShoppingOrderDO shoppingOrderDO = shoppingOrderDOMapper.selectByExample(shoppingOrderDOExample).get(0);
    	
    	ShoppingOrderItemDOExample shoppingOrderItemDOExample = new ShoppingOrderItemDOExample();
    	shoppingOrderItemDOExample.createCriteria().andShoppingOrderIdEqualTo(shoppingOrderDO.getId());
    	ShoppingOrderItemDO shoppingOrderItemDO = shoppingOrderItemDOMapper.selectByExample(shoppingOrderItemDOExample).get(0);
    	
    	ShoppingOrderConverterTest.assertShoppingOrderDO(param, shoppingOrderDO);
    	Assert.assertEquals(actual.get(0), shoppingOrderDO.getId());
    	
    	ShoppingOrderItemConverterTest.assertShoppingOrderItemDO(item, shoppingOrderItemDO, shoppingOrderDO.getId());
    }
    
    /**
     * TODO SQL语句中的DATE_FORMAT函数不兼容
     * 
     * @author jiangxinjun
     * @date 2017年7月13日
     */
    @Ignore
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectByTransactionData() {
    	ShoppingOrderDO shoppingOrderDO  = new ShoppingOrderDO();
    	shoppingOrderDO.setCommodityTotalPrice(new BigDecimal(1));
    	shoppingOrderDO.setActualAmount(new BigDecimal(1));
    	shoppingOrderDO.setFreightPrice(new BigDecimal(0));
    	shoppingOrderDO.setGmtCreate(new Date());
    	shoppingOrderDO.setGmtModified(new Date());
    	shoppingOrderDO.setIsFans(true);
    	shoppingOrderDO.setIsNeedsLogistics(true);
    	shoppingOrderDO.setIsNoReasonReturn(true);
    	shoppingOrderDO.setMemberId(1L);
    	shoppingOrderDO.setMemberNum("M0001");
    	shoppingOrderDO.setMerchantId(1L);
    	shoppingOrderDO.setMerchantName("拉乌网络");
    	shoppingOrderDO.setMerchantStoreId(1L);
    	shoppingOrderDO.setMerchantNum("B0001");
    	shoppingOrderDO.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	shoppingOrderDO.setOrderTotalPrice(new BigDecimal(1));
    	shoppingOrderDO.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	shoppingOrderDO.setStatus(StatusEnum.VALID.getValue());
    	shoppingOrderDO.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	shoppingOrderDO.setConsigneeAddress("大冲商务中心1301");
    	shoppingOrderDO.setConsigneeMobile("123456");
    	shoppingOrderDO.setConsigneeName("Sunny");
    	shoppingOrderDO.setExpressCompanyCode("韵达");
    	shoppingOrderDO.setExpressCompanyId(1);
    	shoppingOrderDO.setExpressCompanyName("韵达");
    	shoppingOrderDO.setGmtPayment(new Date());
    	shoppingOrderDO.setGmtTransaction(new Date());
    	shoppingOrderDO.setGmtTransport(new Date());
    	shoppingOrderDO.setIsDone(true);
    	shoppingOrderDO.setThirdNumber("654321");
    	shoppingOrderDO.setWaybillNum("3923440690592");
    	shoppingOrderDO.setGmtCommission(new Date());
    	shoppingOrderDO.setShoppingCartIdsStr("1,2");
    	shoppingOrderDO.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	shoppingOrderDO.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(shoppingOrderDO);
    	
    	ReportDataParam param = new ReportDataParam();
    	param.setFlag(ReportFansRiseRateEnum.DAY);
    	param.setMerchantId(shoppingOrderDO.getMemberId());
    	
    	ReportRiseRateDTO actual = shoppingOrderService.selectByTransactionData(param);
    	Assert.assertNotNull(actual);
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectOrderMoney() {
    	ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setExpressCompanyCode("韵达");
    	expected.setExpressCompanyId(1);
    	expected.setExpressCompanyName("韵达");
    	expected.setGmtPayment(new Date());
    	expected.setGmtTransaction(new Date());
    	expected.setGmtTransport(new Date());
    	expected.setIsDone(true);
    	expected.setThirdNumber("654321");
    	expected.setWaybillNum("3923440690592");
    	expected.setGmtCommission(new Date());
    	expected.setShoppingCartIdsStr("1,2");
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	expected.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	String orderIds = expected.getId().toString();
    	ShoppingOrderMoneyBO actual = shoppingOrderService.selectOrderMoney(orderIds);
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(expected.getOrderTotalPrice().doubleValue(), actual.getOrderTotalPrice().doubleValue(), 0D);
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectPage() {
    	ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setExpressCompanyCode("韵达");
    	expected.setExpressCompanyId(1);
    	expected.setExpressCompanyName("韵达");
    	expected.setGmtPayment(new Date());
    	expected.setGmtTransaction(new Date());
    	expected.setGmtTransport(new Date());
    	expected.setIsDone(true);
    	expected.setThirdNumber("654321");
    	expected.setWaybillNum("3923440690592");
    	expected.setGmtCommission(new Date());
    	expected.setShoppingCartIdsStr("1,2");
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	expected.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(true);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	
    	ShoppingOrderQueryForeignToOperatorParam param = new ShoppingOrderQueryForeignToOperatorParam();
    	param.setCurrentPage(1);
    	param.setPageSize(10);
    	param.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS);
    	param.setKeyword(expected.getConsigneeName());
    	
    	Page<ShoppingOrderExtendBO> actual = shoppingOrderService.selectPage(param);
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(param.getCurrentPage(), actual.getCurrentPage());
    	Assert.assertEquals(1, actual.getTotalCount().intValue());
    	for (ShoppingOrderExtendBO shoppingOrderExtendBO : actual.getRecords()) {
    		ShoppingOrderConverterTest.assertShoppingOrderBO(expected, shoppingOrderExtendBO);
    		for (ShoppingOrderItemBO shoppingOrderItemBO : shoppingOrderExtendBO.getItems()) {
    			ShoppingOrderItemConverterTest.assertShoppingOrderItemBO(shoppingOrderItemDO, shoppingOrderItemBO);
    		}
    	}
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectPageByMemberId() {
    	ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setExpressCompanyCode("韵达");
    	expected.setExpressCompanyId(1);
    	expected.setExpressCompanyName("韵达");
    	expected.setGmtPayment(new Date());
    	expected.setGmtTransaction(new Date());
    	expected.setGmtTransport(new Date());
    	expected.setIsDone(true);
    	expected.setThirdNumber("654321");
    	expected.setWaybillNum("3923440690592");
    	expected.setGmtCommission(new Date());
    	expected.setShoppingCartIdsStr("1,2");
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	expected.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(false);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	
    	ShoppingOrderQueryForeignToMemberParam param = new ShoppingOrderQueryForeignToMemberParam();
    	param.setCurrentPage(1);
    	param.setPageSize(10);
    	param.setOrderStatus(ShoppingOrderStatusToMemberEnum.BE_EVALUATED);
    	param.setKeyword(expected.getOrderNum());
    	
    	Page<ShoppingOrderExtendBO> actual = shoppingOrderService.selectPageByMemberId(expected.getMemberId(), param);
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(param.getCurrentPage(), actual.getCurrentPage());
    	Assert.assertEquals(1, actual.getTotalCount().intValue());
    	for (ShoppingOrderExtendBO shoppingOrderExtendBO : actual.getRecords()) {
    		ShoppingOrderConverterTest.assertShoppingOrderBO(expected, shoppingOrderExtendBO);
    		for (ShoppingOrderItemBO shoppingOrderItemBO : shoppingOrderExtendBO.getItems()) {
    			ShoppingOrderItemConverterTest.assertShoppingOrderItemBO(shoppingOrderItemDO, shoppingOrderItemBO);
    		}
    	}
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectPageByMerchantId() {
    	ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setExpressCompanyCode("韵达");
    	expected.setExpressCompanyId(1);
    	expected.setExpressCompanyName("韵达");
    	expected.setGmtPayment(new Date());
    	expected.setGmtTransaction(new Date());
    	expected.setGmtTransport(new Date());
    	expected.setIsDone(true);
    	expected.setThirdNumber("654321");
    	expected.setWaybillNum("3923440690592");
    	expected.setGmtCommission(new Date());
    	expected.setShoppingCartIdsStr("1,2");
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	expected.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(false);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	
    	ShoppingOrderQueryForeignToMerchantParam param = new ShoppingOrderQueryForeignToMerchantParam();
    	param.setCurrentPage(1);
    	param.setPageSize(10);
    	param.setOrderStatus(ShoppingOrderStatusToMerchantEnum.COMPLETED);
    	param.setKeyword(expected.getOrderNum());
    	
    	Page<ShoppingOrderExtendBO> actual = shoppingOrderService.selectPageByMerchantId(expected.getMerchantId(), param);
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(param.getCurrentPage(), actual.getCurrentPage());
    	Assert.assertEquals(1, actual.getTotalCount().intValue());
    	for (ShoppingOrderExtendBO shoppingOrderExtendBO : actual.getRecords()) {
    		ShoppingOrderConverterTest.assertShoppingOrderBO(expected, shoppingOrderExtendBO);
    		for (ShoppingOrderItemBO shoppingOrderItemBO : shoppingOrderExtendBO.getItems()) {
    			ShoppingOrderItemConverterTest.assertShoppingOrderItemBO(shoppingOrderItemDO, shoppingOrderItemBO);
    		}
    	}
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateCommissionStatus() {
    	ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setExpressCompanyCode("韵达");
    	expected.setExpressCompanyId(1);
    	expected.setExpressCompanyName("韵达");
    	expected.setGmtPayment(new Date());
    	expected.setGmtTransaction(new Date());
    	expected.setGmtTransport(new Date());
    	expected.setIsDone(true);
    	expected.setThirdNumber("654321");
    	expected.setWaybillNum("3923440690592");
    	expected.setShoppingCartIdsStr("1,2");
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	expected.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	List<Long> ids = new ArrayList<Long>();
    	ids.add(expected.getId());
    	shoppingOrderService.updateCommissionStatus(ids);
    	
    	ShoppingOrderDO actual = shoppingOrderDOMapper.selectByPrimaryKey(expected.getId());
    	Assert.assertNotNull(actual);
    	Assert.assertNotNull(actual.getGmtCommission());
    	Assert.assertEquals(CommissionStatusEnum.CALCULATED.getValue(), actual.getCommissionStatus());
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void deleteOrder() {
    	ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setExpressCompanyCode("韵达");
    	expected.setExpressCompanyId(1);
    	expected.setExpressCompanyName("韵达");
    	expected.setGmtPayment(new Date());
    	expected.setGmtTransaction(new Date());
    	expected.setGmtTransport(new Date());
    	expected.setIsDone(true);
    	expected.setThirdNumber("654321");
    	expected.setWaybillNum("3923440690592");
    	expected.setShoppingCartIdsStr("1,2");
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	expected.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	shoppingOrderService.deleteOrder(expected.getMemberId(), expected.getId());
    	
    	ShoppingOrderDO actual = shoppingOrderDOMapper.selectByPrimaryKey(expected.getId());
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(StatusEnum.INVALID.getValue(), actual.getStatus());
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectAutoCancelOrder() {
    	PropertyDO cancelPropertyDO = new PropertyDO();
    	cancelPropertyDO.setGmtCreate(new Date());
    	cancelPropertyDO.setGmtModified(new Date());
    	cancelPropertyDO.setName(PropertyNameConstant.AUTOMATIC_CANCEL_ORDER);
    	cancelPropertyDO.setRemark("自动取消订单时间");
    	cancelPropertyDO.setValue("2");
    	propertyDOMapper.insert(cancelPropertyDO);
    	
    	// 初始化一天即将提醒支付的订单
    	ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(DateUtil.add(new Date(), Integer.valueOf(cancelPropertyDO.getValue()) * -1, Calendar.DAY_OF_YEAR));
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setIsDone(false);
    	expected.setShoppingCartIdsStr("1,2");
    	expected.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(false);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	List<ShoppingOrderDO> list = shoppingOrderService.selectAutoCancelOrder(0, 10);
    	Assert.assertNotNull(list);
    	Assert.assertEquals(1, list.size());
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectAutoRemindToBeCancelledOrder() {
        PropertyDO remindPpropertyDO = new PropertyDO();
        remindPpropertyDO.setGmtCreate(new Date());
        remindPpropertyDO.setGmtModified(new Date());
        remindPpropertyDO.setRemark("自动取消订单前提醒时间");
        remindPpropertyDO.setValue("1");
        remindPpropertyDO.setName(PropertyNameConstant.AUTOMATIC_REMIND_NO_PAYMENT_ORDER);
        propertyDOMapper.insert(remindPpropertyDO);
        
        // 初始化一天即将提醒支付的订单
        ShoppingOrderDO expected  = new ShoppingOrderDO();
        expected.setCommodityTotalPrice(new BigDecimal(1));
        expected.setActualAmount(new BigDecimal(1));
        expected.setFreightPrice(new BigDecimal(0));
        expected.setGmtCreate(DateUtil.add(new Date(), Integer.valueOf(remindPpropertyDO.getValue()) * -1, Calendar.DAY_OF_YEAR));
        expected.setGmtModified(new Date());
        expected.setIsFans(true);
        expected.setIsNeedsLogistics(true);
        expected.setIsNoReasonReturn(true);
        expected.setMemberId(1L);
        expected.setMemberNum("M0001");
        expected.setMerchantId(1L);
        expected.setMerchantName("拉乌网络");
        expected.setMerchantStoreId(1L);
        expected.setMerchantNum("B0001");
        expected.setOrderStatus(ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue());
        expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
        expected.setOrderTotalPrice(new BigDecimal(1));
        expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
        expected.setStatus(StatusEnum.VALID.getValue());
        expected.setConsigneeAddress("大冲商务中心1301");
        expected.setConsigneeMobile("123456");
        expected.setConsigneeName("Sunny");
        expected.setIsDone(false);
        expected.setShoppingCartIdsStr("1,2");
        expected.setSendTime(0);
        shoppingOrderDOMapper.insertSelective(expected);
        
        ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
        shoppingOrderItemDO.setGmtCreate(new Date());
        shoppingOrderItemDO.setGmtModified(new Date());
        shoppingOrderItemDO.setIsAllowRefund(true);
        shoppingOrderItemDO.setIsEvaluation(false);
        shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue());
        shoppingOrderItemDO.setProductFeatureImage("test.jpg");
        shoppingOrderItemDO.setProductId(1L);
        shoppingOrderItemDO.setProductName("productName");
        shoppingOrderItemDO.setProductModelId(1L);
        shoppingOrderItemDO.setProductModelName("test");
        shoppingOrderItemDO.setQuantity(1);
        shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
        shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
        shoppingOrderItemDO.setSendTime(0);
        shoppingOrderItemDO.setShoppingOrderId(expected.getId());
        shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
        
        List<ShoppingOrderDO> list = shoppingOrderService.selectAutoRemindToBeCancelledOrder(0, 10);
        Assert.assertNotNull(list);
        Assert.assertEquals(1, list.size());
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void executeAutoRemindToBeCancelledOrder() {
        PropertyDO remindPpropertyDO = new PropertyDO();
        remindPpropertyDO.setGmtCreate(new Date());
        remindPpropertyDO.setGmtModified(new Date());
        remindPpropertyDO.setRemark("自动取消订单前提醒时间");
        remindPpropertyDO.setValue("1");
        remindPpropertyDO.setName(PropertyNameConstant.AUTOMATIC_REMIND_NO_PAYMENT_ORDER);
        propertyDOMapper.insert(remindPpropertyDO);
        
        // 初始化一天即将提醒支付的订单
        ShoppingOrderDO expected  = new ShoppingOrderDO();
        expected.setCommodityTotalPrice(new BigDecimal(1));
        expected.setActualAmount(new BigDecimal(1));
        expected.setFreightPrice(new BigDecimal(0));
        expected.setGmtCreate(DateUtil.add(new Date(), Integer.valueOf(remindPpropertyDO.getValue()) * -1, Calendar.DAY_OF_YEAR));
        expected.setGmtModified(new Date());
        expected.setIsFans(true);
        expected.setIsNeedsLogistics(true);
        expected.setIsNoReasonReturn(true);
        expected.setMemberId(1L);
        expected.setMemberNum("M0001");
        expected.setMerchantId(1L);
        expected.setMerchantName("拉乌网络");
        expected.setMerchantStoreId(1L);
        expected.setMerchantNum("B0001");
        expected.setOrderStatus(ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue());
        expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
        expected.setOrderTotalPrice(new BigDecimal(1));
        expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
        expected.setStatus(StatusEnum.VALID.getValue());
        expected.setConsigneeAddress("大冲商务中心1301");
        expected.setConsigneeMobile("123456");
        expected.setConsigneeName("Sunny");
        expected.setIsDone(false);
        expected.setShoppingCartIdsStr("1,2");
        expected.setSendTime(0);
        shoppingOrderDOMapper.insertSelective(expected);
        
        ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
        shoppingOrderItemDO.setGmtCreate(new Date());
        shoppingOrderItemDO.setGmtModified(new Date());
        shoppingOrderItemDO.setIsAllowRefund(true);
        shoppingOrderItemDO.setIsEvaluation(false);
        shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue());
        shoppingOrderItemDO.setProductFeatureImage("test.jpg");
        shoppingOrderItemDO.setProductId(1L);
        shoppingOrderItemDO.setProductName("productName");
        shoppingOrderItemDO.setProductModelId(1L);
        shoppingOrderItemDO.setProductModelName("test");
        shoppingOrderItemDO.setQuantity(1);
        shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
        shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
        shoppingOrderItemDO.setSendTime(0);
        shoppingOrderItemDO.setShoppingOrderId(expected.getId());
        shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
        
        shoppingOrderService.executeAutoRemindToBeCancelledOrder(expected);
        
        ShoppingOrderDO actual = shoppingOrderDOMapper.selectByPrimaryKey(expected.getId());
        Assert.assertNotNull(actual);
        Assert.assertEquals(1, actual.getSendTime().intValue());
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectAutoReleaseFrozenFundsOrder() {
    	PropertyDO propertyDO = new PropertyDO();
    	propertyDO.setGmtCreate(new Date());
    	propertyDO.setGmtModified(new Date());
    	propertyDO.setName(PropertyNameConstant.REFUND_REQUEST_TIME);
    	propertyDO.setRemark("退款申请时间");
    	propertyDO.setValue("7");
    	propertyDOMapper.insert(propertyDO);
    	
    	// 初始化一条超过收货时间的订单
    	ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setGmtTransaction(DateUtil.add(new Date(), Integer.valueOf(propertyDO.getValue()) * -1, Calendar.DAY_OF_YEAR));
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setIsDone(false);
    	expected.setShoppingCartIdsStr("1,2");
    	expected.setSendTime(0);
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(false);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
        List<ShoppingOrderDO> list = shoppingOrderService.selectAutoReleaseFrozenFundsOrder(0, 10);
        Assert.assertNotNull(list);
        Assert.assertEquals(1, list.size());
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void executeAutoReleaseFrozenFundsOrder() {
        PropertyDO propertyDO = new PropertyDO();
        propertyDO.setGmtCreate(new Date());
        propertyDO.setGmtModified(new Date());
        propertyDO.setName(PropertyNameConstant.REFUND_REQUEST_TIME);
        propertyDO.setRemark("退款申请时间");
        propertyDO.setValue("7");
        propertyDOMapper.insert(propertyDO);
        
        // 初始化一条超过退款时间的订单
        ShoppingOrderDO expected  = new ShoppingOrderDO();
        expected.setCommodityTotalPrice(new BigDecimal(1));
        expected.setActualAmount(new BigDecimal(1));
        expected.setFreightPrice(new BigDecimal(0));
        expected.setGmtCreate(new Date());
        expected.setGmtModified(new Date());
        expected.setGmtTransaction(DateUtil.add(new Date(), Integer.valueOf(propertyDO.getValue()) * -1, Calendar.DAY_OF_YEAR));
        expected.setIsFans(true);
        expected.setIsNeedsLogistics(true);
        expected.setIsNoReasonReturn(true);
        expected.setMemberId(1L);
        expected.setMemberNum("M0001");
        expected.setMerchantId(1L);
        expected.setMerchantName("拉乌网络");
        expected.setMerchantStoreId(1L);
        expected.setMerchantNum("B0001");
        expected.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
        expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
        expected.setOrderTotalPrice(new BigDecimal(1));
        expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
        expected.setStatus(StatusEnum.VALID.getValue());
        expected.setConsigneeAddress("大冲商务中心1301");
        expected.setConsigneeMobile("123456");
        expected.setConsigneeName("Sunny");
        expected.setIsDone(false);
        expected.setShoppingCartIdsStr("1,2");
        expected.setSendTime(0);
        expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
        shoppingOrderDOMapper.insertSelective(expected);
        
        ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
        shoppingOrderItemDO.setGmtCreate(new Date());
        shoppingOrderItemDO.setGmtModified(new Date());
        shoppingOrderItemDO.setIsAllowRefund(true);
        shoppingOrderItemDO.setIsEvaluation(false);
        shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
        shoppingOrderItemDO.setProductFeatureImage("test.jpg");
        shoppingOrderItemDO.setProductId(1L);
        shoppingOrderItemDO.setProductName("productName");
        shoppingOrderItemDO.setProductModelId(1L);
        shoppingOrderItemDO.setProductModelName("test");
        shoppingOrderItemDO.setQuantity(1);
        shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
        shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
        shoppingOrderItemDO.setSendTime(0);
        shoppingOrderItemDO.setShoppingOrderId(expected.getId());
        shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
        
        shoppingOrderService.executeAutoReleaseFrozenFundsOrder(expected);
        
        ShoppingOrderDO actual = shoppingOrderDOMapper.selectByPrimaryKey(expected.getId());
        Assert.assertNotNull(actual);
        Assert.assertEquals(true, actual.getIsDone());
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectAutoReceiptOrder() {
    	PropertyDO propertyDO = new PropertyDO();
    	propertyDO.setGmtCreate(new Date());
    	propertyDO.setGmtModified(new Date());
    	propertyDO.setName(PropertyNameConstant.AUTOMATIC_RECEIPT);
    	propertyDO.setRemark("平台自动收货时间");
    	propertyDO.setValue("14");
    	propertyDOMapper.insert(propertyDO);
    	
    	/*
    	 *  初始化一条超过退款时间的订单
    	 *  1.一条订单项记录正常
    	 *  2.另一条订单项记录处于退款
    	 */
    	ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setGmtTransport(DateUtil.add(new Date(), Integer.valueOf(propertyDO.getValue()) * -1, Calendar.DAY_OF_YEAR));
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(false);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setIsDone(false);
    	expected.setShoppingCartIdsStr("1,2");
    	expected.setSendTime(0);
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	expected.setIsRefundItems(true);
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(false);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	ShoppingOrderItemDO refundShoppingOrderItemDO = new ShoppingOrderItemDO();
    	refundShoppingOrderItemDO.setGmtCreate(new Date());
    	refundShoppingOrderItemDO.setGmtModified(new Date());
    	refundShoppingOrderItemDO.setIsAllowRefund(true);
    	refundShoppingOrderItemDO.setIsEvaluation(false);
    	refundShoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.REFUNDING.getValue());
    	refundShoppingOrderItemDO.setRefundStatus(RefundStatusEnum.TO_BE_CONFIRMED.getValue());
    	refundShoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	refundShoppingOrderItemDO.setProductId(1L);
    	refundShoppingOrderItemDO.setProductName("productName");
    	refundShoppingOrderItemDO.setProductModelId(1L);
    	refundShoppingOrderItemDO.setProductModelName("test");
    	refundShoppingOrderItemDO.setQuantity(1);
    	refundShoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	refundShoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	refundShoppingOrderItemDO.setSendTime(0);
    	refundShoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(refundShoppingOrderItemDO);
    	
    	/*
         *  初始化一条超过退款时间的订单,订单项记录正常
         */
        ShoppingOrderDO expected2  = new ShoppingOrderDO();
        expected2.setCommodityTotalPrice(new BigDecimal(1));
        expected2.setActualAmount(new BigDecimal(1));
        expected2.setFreightPrice(new BigDecimal(0));
        expected2.setGmtCreate(new Date());
        expected2.setGmtModified(new Date());
        expected2.setGmtTransport(DateUtil.add(new Date(), Integer.valueOf(propertyDO.getValue()) * -1, Calendar.DAY_OF_YEAR));
        expected2.setIsFans(true);
        expected2.setIsNeedsLogistics(true);
        expected2.setIsNoReasonReturn(false);
        expected2.setMemberId(1L);
        expected2.setMemberNum("M0001");
        expected2.setMerchantId(1L);
        expected2.setMerchantName("拉乌网络");
        expected2.setMerchantStoreId(1L);
        expected2.setMerchantNum("B0001");
        expected2.setOrderStatus(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue());
        expected2.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
        expected2.setOrderTotalPrice(new BigDecimal(1));
        expected2.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
        expected2.setStatus(StatusEnum.VALID.getValue());
        expected2.setConsigneeAddress("大冲商务中心1301");
        expected2.setConsigneeMobile("123456");
        expected2.setConsigneeName("Sunny");
        expected2.setIsDone(false);
        expected2.setShoppingCartIdsStr("1,2");
        expected2.setSendTime(0);
        expected2.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
        shoppingOrderDOMapper.insertSelective(expected2);
        
        ShoppingOrderItemDO shoppingOrderItemDO2 = new ShoppingOrderItemDO();
        shoppingOrderItemDO2.setGmtCreate(new Date());
        shoppingOrderItemDO2.setGmtModified(new Date());
        shoppingOrderItemDO2.setIsAllowRefund(true);
        shoppingOrderItemDO2.setIsEvaluation(false);
        shoppingOrderItemDO2.setOrderStatus(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue());
        shoppingOrderItemDO2.setProductFeatureImage("test.jpg");
        shoppingOrderItemDO2.setProductId(1L);
        shoppingOrderItemDO2.setProductName("productName");
        shoppingOrderItemDO2.setProductModelId(1L);
        shoppingOrderItemDO2.setProductModelName("test");
        shoppingOrderItemDO2.setQuantity(1);
        shoppingOrderItemDO2.setRegularPrice(new BigDecimal(1));
        shoppingOrderItemDO2.setSalesPrice(new BigDecimal(1));
        shoppingOrderItemDO2.setSendTime(0);
        shoppingOrderItemDO2.setShoppingOrderId(expected2.getId());
        shoppingOrderItemDOMapper.insert(shoppingOrderItemDO2);
        
        List<ShoppingOrderDO> list = shoppingOrderService.selectAutoReceiptOrder(1, 10);
        Assert.assertNotNull(list);
        Assert.assertEquals(1, list.size());
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void executeAutoReceipt() {
        PropertyDO propertyDO = new PropertyDO();
        propertyDO.setGmtCreate(new Date());
        propertyDO.setGmtModified(new Date());
        propertyDO.setName(PropertyNameConstant.AUTOMATIC_RECEIPT);
        propertyDO.setRemark("平台自动收货时间");
        propertyDO.setValue("14");
        propertyDOMapper.insert(propertyDO);
        
        /*
         *  初始化一条超过退款时间的订单
         *  1.一条订单项记录正常
         *  2.另一条订单项记录处于退款
         */
        ShoppingOrderDO expected  = new ShoppingOrderDO();
        expected.setCommodityTotalPrice(new BigDecimal(1));
        expected.setActualAmount(new BigDecimal(1));
        expected.setFreightPrice(new BigDecimal(0));
        expected.setGmtCreate(new Date());
        expected.setGmtModified(new Date());
        expected.setGmtTransport(DateUtil.add(new Date(), Integer.valueOf(propertyDO.getValue()) * -1, Calendar.DAY_OF_YEAR));
        expected.setIsFans(true);
        expected.setIsNeedsLogistics(true);
        expected.setIsNoReasonReturn(false);
        expected.setMemberId(1L);
        expected.setMemberNum("M0001");
        expected.setMerchantId(1L);
        expected.setMerchantName("拉乌网络");
        expected.setMerchantStoreId(1L);
        expected.setMerchantNum("B0001");
        expected.setOrderStatus(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue());
        expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
        expected.setOrderTotalPrice(new BigDecimal(1));
        expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
        expected.setStatus(StatusEnum.VALID.getValue());
        expected.setConsigneeAddress("大冲商务中心1301");
        expected.setConsigneeMobile("123456");
        expected.setConsigneeName("Sunny");
        expected.setIsDone(false);
        expected.setShoppingCartIdsStr("1,2");
        expected.setSendTime(0);
        expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
        expected.setIsRefundItems(true);
        shoppingOrderDOMapper.insertSelective(expected);
        
        ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
        shoppingOrderItemDO.setGmtCreate(new Date());
        shoppingOrderItemDO.setGmtModified(new Date());
        shoppingOrderItemDO.setIsAllowRefund(true);
        shoppingOrderItemDO.setIsEvaluation(false);
        shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue());
        shoppingOrderItemDO.setProductFeatureImage("test.jpg");
        shoppingOrderItemDO.setProductId(1L);
        shoppingOrderItemDO.setProductName("productName");
        shoppingOrderItemDO.setProductModelId(1L);
        shoppingOrderItemDO.setProductModelName("test");
        shoppingOrderItemDO.setQuantity(1);
        shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
        shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
        shoppingOrderItemDO.setSendTime(0);
        shoppingOrderItemDO.setShoppingOrderId(expected.getId());
        shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
        
        ShoppingOrderItemDO refundShoppingOrderItemDO = new ShoppingOrderItemDO();
        refundShoppingOrderItemDO.setGmtCreate(new Date());
        refundShoppingOrderItemDO.setGmtModified(new Date());
        refundShoppingOrderItemDO.setIsAllowRefund(true);
        refundShoppingOrderItemDO.setIsEvaluation(false);
        refundShoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.REFUNDING.getValue());
        refundShoppingOrderItemDO.setRefundStatus(RefundStatusEnum.TO_BE_CONFIRMED.getValue());
        refundShoppingOrderItemDO.setProductFeatureImage("test.jpg");
        refundShoppingOrderItemDO.setProductId(1L);
        refundShoppingOrderItemDO.setProductName("productName");
        refundShoppingOrderItemDO.setProductModelId(1L);
        refundShoppingOrderItemDO.setProductModelName("test");
        refundShoppingOrderItemDO.setQuantity(1);
        refundShoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
        refundShoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
        refundShoppingOrderItemDO.setSendTime(0);
        refundShoppingOrderItemDO.setShoppingOrderId(expected.getId());
        shoppingOrderItemDOMapper.insert(refundShoppingOrderItemDO);
        
        /*
         *  初始化一条超过退款时间的订单,订单项记录正常
         */
        ShoppingOrderDO expected2  = new ShoppingOrderDO();
        expected2.setCommodityTotalPrice(new BigDecimal(1));
        expected2.setActualAmount(new BigDecimal(1));
        expected2.setFreightPrice(new BigDecimal(0));
        expected2.setGmtCreate(new Date());
        expected2.setGmtModified(new Date());
        expected2.setGmtTransport(DateUtil.add(new Date(), Integer.valueOf(propertyDO.getValue()) * -1, Calendar.DAY_OF_YEAR));
        expected2.setIsFans(true);
        expected2.setIsNeedsLogistics(true);
        expected2.setIsNoReasonReturn(false);
        expected2.setMemberId(1L);
        expected2.setMemberNum("M0001");
        expected2.setMerchantId(1L);
        expected2.setMerchantName("拉乌网络");
        expected2.setMerchantStoreId(1L);
        expected2.setMerchantNum("B0001");
        expected2.setOrderStatus(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue());
        expected2.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
        expected2.setOrderTotalPrice(new BigDecimal(1));
        expected2.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
        expected2.setStatus(StatusEnum.VALID.getValue());
        expected2.setConsigneeAddress("大冲商务中心1301");
        expected2.setConsigneeMobile("123456");
        expected2.setConsigneeName("Sunny");
        expected2.setIsDone(false);
        expected2.setShoppingCartIdsStr("1,2");
        expected2.setSendTime(0);
        expected2.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
        shoppingOrderDOMapper.insertSelective(expected2);
        
        ShoppingOrderItemDO shoppingOrderItemDO2 = new ShoppingOrderItemDO();
        shoppingOrderItemDO2.setGmtCreate(new Date());
        shoppingOrderItemDO2.setGmtModified(new Date());
        shoppingOrderItemDO2.setIsAllowRefund(true);
        shoppingOrderItemDO2.setIsEvaluation(false);
        shoppingOrderItemDO2.setOrderStatus(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue());
        shoppingOrderItemDO2.setProductFeatureImage("test.jpg");
        shoppingOrderItemDO2.setProductId(1L);
        shoppingOrderItemDO2.setProductName("productName");
        shoppingOrderItemDO2.setProductModelId(1L);
        shoppingOrderItemDO2.setProductModelName("test");
        shoppingOrderItemDO2.setQuantity(1);
        shoppingOrderItemDO2.setRegularPrice(new BigDecimal(1));
        shoppingOrderItemDO2.setSalesPrice(new BigDecimal(1));
        shoppingOrderItemDO2.setSendTime(0);
        shoppingOrderItemDO2.setShoppingOrderId(expected2.getId());
        shoppingOrderItemDOMapper.insert(shoppingOrderItemDO2);
        
        List<ShoppingOrderDO> list = shoppingOrderService.selectAutoReceiptOrder(1, 10);
        Assert.assertNotNull(list);
        Assert.assertEquals(1, list.size());
        for (ShoppingOrderDO item : list) {
            shoppingOrderService.tradingSuccess(item.getId(), true);
        }
        
        ShoppingOrderDO actual = shoppingOrderDOMapper.selectByPrimaryKey(expected.getId());
        Assert.assertNotNull(actual);
        Assert.assertEquals(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue(), actual.getOrderStatus());
        
        ShoppingOrderItemDO actualShoppingOrderItemDO = shoppingOrderItemDOMapper.selectByPrimaryKey(shoppingOrderItemDO.getId());
        Assert.assertNotNull(actualShoppingOrderItemDO);
        Assert.assertEquals(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue(), actualShoppingOrderItemDO.getOrderStatus());
        
        ShoppingOrderItemDO actualRefundShoppingOrderItemDO = shoppingOrderItemDOMapper.selectByPrimaryKey(refundShoppingOrderItemDO.getId());
        Assert.assertNotNull(actualRefundShoppingOrderItemDO);
        Assert.assertEquals(ShoppingOrderStatusEnum.REFUNDING.getValue(), actualRefundShoppingOrderItemDO.getOrderStatus());
        
        ShoppingOrderDO actual2 = shoppingOrderDOMapper.selectByPrimaryKey(expected2.getId());
        Assert.assertNotNull(actual2);
        Assert.assertEquals(true, actual2.getIsAutomaticReceipt());
        Assert.assertEquals(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue(), actual2.getOrderStatus());
        
        ShoppingOrderItemDO actualShoppingOrderItemDO2 = shoppingOrderItemDOMapper.selectByPrimaryKey(shoppingOrderItemDO2.getId());
        Assert.assertNotNull(actualShoppingOrderItemDO2);
        Assert.assertEquals(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue(), actualShoppingOrderItemDO2.getOrderStatus());
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void executeAutoRemindShipments() {
    	PropertyDO propertyDO = new PropertyDO();
    	propertyDO.setGmtCreate(new Date());
    	propertyDO.setGmtModified(new Date());
    	propertyDO.setName(PropertyNameConstant.AUTOMATIC_REMIND_SHIPMENTS);
    	propertyDO.setRemark("平台自动提醒买家发货");
    	propertyDO.setValue("5");
    	propertyDOMapper.insert(propertyDO);
    	
    	// 初始化一条超过退款时间的订单
    	ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setGmtPayment(DateUtil.add(new Date(), Integer.valueOf(propertyDO.getValue()) * -1, Calendar.DAY_OF_YEAR));
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(false);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.BE_SHIPPED.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setIsDone(false);
    	expected.setShoppingCartIdsStr("1,2");
    	expected.setSendTime(0);
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
        shoppingOrderItemDO.setGmtCreate(new Date());
        shoppingOrderItemDO.setGmtModified(new Date());
        shoppingOrderItemDO.setIsAllowRefund(true);
        shoppingOrderItemDO.setIsEvaluation(false);
        shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.BE_SHIPPED.getValue());
        shoppingOrderItemDO.setRefundStatus(null);
        shoppingOrderItemDO.setProductFeatureImage("test.jpg");
        shoppingOrderItemDO.setProductId(1L);
        shoppingOrderItemDO.setProductName("productName");
        shoppingOrderItemDO.setProductModelId(1L);
        shoppingOrderItemDO.setProductModelName("test");
        shoppingOrderItemDO.setQuantity(1);
        shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
        shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
        shoppingOrderItemDO.setSendTime(0);
        shoppingOrderItemDO.setShoppingOrderId(expected.getId());
        shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	shoppingOrderService.executeAutoRemindShipments();
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectAutoCommentOrder() {
    	PropertyDO propertyDO = new PropertyDO();
    	propertyDO.setGmtCreate(new Date());
    	propertyDO.setGmtModified(new Date());
    	propertyDO.setName(PropertyNameConstant.AUTOMATIC_EVALUATION);
    	propertyDO.setRemark("平台自动评论时间");
    	propertyDO.setValue("7");
    	propertyDOMapper.insert(propertyDO);
    	
    	// 初始化一条超过退款时间的订单
    	ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setGmtPayment(new Date());
    	expected.setGmtTransaction(DateUtil.add(new Date(), Integer.valueOf(propertyDO.getValue()) * -1, Calendar.DAY_OF_YEAR));
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(false);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setIsDone(false);
    	expected.setShoppingCartIdsStr("1,2");
    	expected.setSendTime(0);
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(false);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	List<ShoppingOrderItemExtendDO> list = shoppingOrderService.selectAutoCommentOrder(0, 10);
    	Assert.assertNotNull(list);
    	Assert.assertEquals(1, list.size());
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void executeAutoCommentOrder() {
        PropertyDO propertyDO = new PropertyDO();
        propertyDO.setGmtCreate(new Date());
        propertyDO.setGmtModified(new Date());
        propertyDO.setName(PropertyNameConstant.AUTOMATIC_EVALUATION);
        propertyDO.setRemark("平台自动评论时间");
        propertyDO.setValue("7");
        propertyDOMapper.insert(propertyDO);
        
        // 初始化一条超过退款时间的订单
        ShoppingOrderDO expected  = new ShoppingOrderDO();
        expected.setCommodityTotalPrice(new BigDecimal(1));
        expected.setActualAmount(new BigDecimal(1));
        expected.setFreightPrice(new BigDecimal(0));
        expected.setGmtCreate(new Date());
        expected.setGmtModified(new Date());
        expected.setGmtPayment(new Date());
        expected.setGmtTransaction(DateUtil.add(new Date(), Integer.valueOf(propertyDO.getValue()) * -1, Calendar.DAY_OF_YEAR));
        expected.setIsFans(true);
        expected.setIsNeedsLogistics(true);
        expected.setIsNoReasonReturn(false);
        expected.setMemberId(1L);
        expected.setMemberNum("M0001");
        expected.setMerchantId(1L);
        expected.setMerchantName("拉乌网络");
        expected.setMerchantStoreId(1L);
        expected.setMerchantNum("B0001");
        expected.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
        expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
        expected.setOrderTotalPrice(new BigDecimal(1));
        expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
        expected.setStatus(StatusEnum.VALID.getValue());
        expected.setConsigneeAddress("大冲商务中心1301");
        expected.setConsigneeMobile("123456");
        expected.setConsigneeName("Sunny");
        expected.setIsDone(false);
        expected.setShoppingCartIdsStr("1,2");
        expected.setSendTime(0);
        expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
        shoppingOrderDOMapper.insertSelective(expected);
        
        ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
        shoppingOrderItemDO.setGmtCreate(new Date());
        shoppingOrderItemDO.setGmtModified(new Date());
        shoppingOrderItemDO.setIsAllowRefund(true);
        shoppingOrderItemDO.setIsEvaluation(false);
        shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
        shoppingOrderItemDO.setProductFeatureImage("test.jpg");
        shoppingOrderItemDO.setProductId(1L);
        shoppingOrderItemDO.setProductName("productName");
        shoppingOrderItemDO.setProductModelId(1L);
        shoppingOrderItemDO.setProductModelName("test");
        shoppingOrderItemDO.setQuantity(1);
        shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
        shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
        shoppingOrderItemDO.setSendTime(0);
        shoppingOrderItemDO.setShoppingOrderId(expected.getId());
        shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
        
        ShoppingOrderItemExtendDO shoppingOrderItemExtendDO = new ShoppingOrderItemExtendDO();
        shoppingOrderItemExtendDO.setId(shoppingOrderItemDO.getId());
        shoppingOrderService.executeAutoCommentOrder(shoppingOrderItemExtendDO);
        
        ShoppingOrderItemDO actual = shoppingOrderItemDOMapper.selectByPrimaryKey(shoppingOrderItemDO.getId());
        Assert.assertNotNull(actual);
        Assert.assertEquals(true, actual.getIsEvaluation());
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void minusInventorySuccess() {
    	ShoppingCartDO shoppingCartDO = new ShoppingCartDO();
    	shoppingCartDO.setGmtCreate(new Date());
    	shoppingCartDO.setGmtModified(new Date());
    	shoppingCartDO.setMemberId(1L);
    	shoppingCartDO.setMerchantId(1L);
    	shoppingCartDO.setMerchantName("拉乌网络");
    	shoppingCartDO.setMerchantStoreId(1L);
    	shoppingCartDO.setProductModelId(1L);
    	shoppingCartDO.setQuantity(1);
    	shoppingCartDO.setSalesPrice(new BigDecimal(1));
    	shoppingCartDOMapper.insert(shoppingCartDO);
    	
    	// 初始化一条超过退款时间的订单
    	ShoppingOrderDO expected = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(false);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.PENDING.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setIsDone(false);
    	expected.setShoppingCartIdsStr(shoppingCartDO.getId().toString());
    	expected.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(false);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.PENDING.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	ShoppingOrderCreateOrderReply reply = new ShoppingOrderCreateOrderReply();
    	reply.setResult(null);
    	shoppingOrderService.minusInventorySuccess(expected.getId(), reply);
    	
    	ShoppingOrderDO actualShoppingOrderDO = shoppingOrderDOMapper.selectByPrimaryKey(expected.getId());
    	Assert.assertNotNull(actualShoppingOrderDO);
    	Assert.assertEquals(ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue(), actualShoppingOrderDO.getOrderStatus());
    	
    	ShoppingOrderItemDO actual = shoppingOrderItemDOMapper.selectByPrimaryKey(shoppingOrderItemDO.getId());
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue(), actual.getOrderStatus());
    	
    	ShoppingCartDO actualShoppingCartDO = shoppingCartDOMapper.selectByPrimaryKey(shoppingCartDO.getId());
    	Assert.assertNull(actualShoppingCartDO);
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void paymentSuccessful() {
    	ShoppingOrderDO expected = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(false);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setIsDone(false);
    	expected.setShoppingCartIdsStr("1");
    	expected.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(false);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.PENDING.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	ShoppingOrderPaymentNotification notification = new ShoppingOrderPaymentNotification();
    	notification.setPaymentMethod(TransactionPayTypeEnum.ALIPAY);
    	notification.setShoppingOrderIds(expected.getId().toString());
    	notification.setThirdNumber("123456");
    	shoppingOrderService.paymentSuccessful(notification);
    	
    	ShoppingOrderDO actualShoppingOrderDO = shoppingOrderDOMapper.selectByPrimaryKey(expected.getId());
    	Assert.assertNotNull(actualShoppingOrderDO);
    	Assert.assertEquals(ShoppingOrderStatusEnum.BE_SHIPPED.getValue(), actualShoppingOrderDO.getOrderStatus());
    	Assert.assertEquals(notification.getPaymentMethod(), actualShoppingOrderDO.getPaymentMethod());
    	Assert.assertEquals(notification.getThirdNumber(), actualShoppingOrderDO.getThirdNumber());
    	
    	ShoppingOrderItemDO actual = shoppingOrderItemDOMapper.selectByPrimaryKey(shoppingOrderItemDO.getId());
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(ShoppingOrderStatusEnum.BE_SHIPPED.getValue(), actual.getOrderStatus());
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void requestRefund() {
    	ShoppingOrderDO expected = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.BE_SHIPPED.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setIsDone(false);
    	expected.setShoppingCartIdsStr("1");
    	expected.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(false);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.BE_SHIPPED.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	ShoppingOrderRequestRefundParam param = new ShoppingOrderRequestRefundParam();
    	param.setDescription("就是要退款");
    	param.setReason("七天无理由退款");
    	param.setType(ShoppingRefundTypeEnum.REFUND);
    	shoppingOrderService.requestRefund(shoppingOrderItemDO.getId(), expected.getMemberId(), param);
    	
    	ShoppingOrderDO actualShoppingOrderDO = shoppingOrderDOMapper.selectByPrimaryKey(expected.getId());
    	Assert.assertNotNull(actualShoppingOrderDO);
    	Assert.assertEquals(true, actualShoppingOrderDO.getIsRefundItems());
    	
    	ShoppingOrderItemDO actual = shoppingOrderItemDOMapper.selectByPrimaryKey(shoppingOrderItemDO.getId());
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(ShoppingOrderStatusEnum.REFUNDING.getValue(), actual.getOrderStatus());
    	Assert.assertEquals(RefundStatusEnum.TO_BE_REFUNDED.getValue(), actual.getRefundStatus());
    	
    	ShoppingRefundDetailDOExample example = new ShoppingRefundDetailDOExample();
    	example.createCriteria().andShoppingOrderItemIdEqualTo(shoppingOrderItemDO.getId());
    	ShoppingRefundDetailDO actualShoppingRefundDetailDO = shoppingRefundDetailDOMapper.selectByExample(example).get(0);
    	Assert.assertNotNull(actualShoppingRefundDetailDO);
    	Assert.assertEquals(shoppingOrderItemDO.getSalesPrice().multiply(new BigDecimal(shoppingOrderItemDO.getQuantity())).doubleValue(), actualShoppingRefundDetailDO.getAmount().doubleValue(), 0D);
    	Assert.assertEquals(param.getReason(), actualShoppingRefundDetailDO.getReason());
    	Assert.assertEquals(param.getDescription(), actualShoppingRefundDetailDO.getDescription());
    	Assert.assertEquals(param.getType().getValue(), actualShoppingRefundDetailDO.getType());
    	Assert.assertEquals(StatusEnum.VALID.getValue(), actualShoppingRefundDetailDO.getStatus());
    	
    	ShoppingRefundProcessDOExample shoppingRefundProcessDOExample = new ShoppingRefundProcessDOExample();
    	shoppingRefundProcessDOExample.createCriteria().andShoppingRefundDetailIdEqualTo(actualShoppingRefundDetailDO.getId());
    	ShoppingRefundProcessDO shoppingRefundProcessDO = shoppingRefundProcessDOMapper.selectByExample(shoppingRefundProcessDOExample).get(0);
    	Assert.assertNotNull(shoppingRefundProcessDO);
    	Assert.assertNotNull(shoppingRefundProcessDO.getGmtCreate());
    	Assert.assertEquals(actualShoppingRefundDetailDO.getId(), shoppingRefundProcessDO.getShoppingRefundDetailId());
    	Assert.assertEquals(actual.getRefundStatus(), shoppingRefundProcessDO.getRefundStatus());
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void tradingSuccess() {
    	ShoppingOrderDO expected = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setGmtTransport(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setIsDone(false);
    	expected.setShoppingCartIdsStr("1");
    	expected.setSendTime(0);
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(false);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	ShoppingOrderItemDO refundShoppingOrderItemDO = new ShoppingOrderItemDO();
    	refundShoppingOrderItemDO.setGmtCreate(new Date());
    	refundShoppingOrderItemDO.setGmtModified(new Date());
    	refundShoppingOrderItemDO.setIsAllowRefund(true);
    	refundShoppingOrderItemDO.setIsEvaluation(false);
    	refundShoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.REFUNDING.getValue());
    	refundShoppingOrderItemDO.setRefundStatus(RefundStatusEnum.TO_BE_CONFIRMED.getValue());
    	refundShoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	refundShoppingOrderItemDO.setProductId(1L);
    	refundShoppingOrderItemDO.setProductName("productName");
    	refundShoppingOrderItemDO.setProductModelId(1L);
    	refundShoppingOrderItemDO.setProductModelName("test");
    	refundShoppingOrderItemDO.setQuantity(1);
    	refundShoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	refundShoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	refundShoppingOrderItemDO.setSendTime(0);
    	refundShoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(refundShoppingOrderItemDO);
    	
    	ShoppingRefundDetailDO shoppingRefundDetailDO = new ShoppingRefundDetailDO();
    	shoppingRefundDetailDO.setAmount(refundShoppingOrderItemDO.getSalesPrice().multiply(new BigDecimal(refundShoppingOrderItemDO.getQuantity())));
    	shoppingRefundDetailDO.setDescription("就是想退款");
    	shoppingRefundDetailDO.setGmtModified(new Date());
    	shoppingRefundDetailDO.setGmtCreate(new Date());
    	shoppingRefundDetailDO.setReason("七天无理由退货");
    	shoppingRefundDetailDO.setShoppingOrderItemId(refundShoppingOrderItemDO.getId());
    	shoppingRefundDetailDO.setStatus(StatusEnum.VALID.getValue());
    	shoppingRefundDetailDOMapper.insert(shoppingRefundDetailDO);
    	
    	shoppingOrderService.tradingSuccess(expected.getId(), expected.getMemberId(), false);
    	
    	ShoppingOrderDO actual = shoppingOrderDOMapper.selectByPrimaryKey(expected.getId());
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(false, actual.getIsAutomaticReceipt());
    	Assert.assertEquals(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue(), actual.getOrderStatus());
    	
    	ShoppingOrderItemDO actualShoppingOrderItemDO = shoppingOrderItemDOMapper.selectByPrimaryKey(shoppingOrderItemDO.getId());
    	Assert.assertNotNull(actualShoppingOrderItemDO);
    	Assert.assertEquals(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue(), actualShoppingOrderItemDO.getOrderStatus());
    	
    	actualShoppingOrderItemDO = shoppingOrderItemDOMapper.selectByPrimaryKey(refundShoppingOrderItemDO.getId());
    	Assert.assertNotNull(actualShoppingOrderItemDO);
    	Assert.assertNull(actualShoppingOrderItemDO.getRefundStatus());
    	Assert.assertEquals(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue(), actualShoppingOrderItemDO.getOrderStatus());
    	
    	ShoppingRefundDetailDO actualShoppingRefundDetailDO = shoppingRefundDetailDOMapper.selectByPrimaryKey(shoppingRefundDetailDO.getId());
    	Assert.assertNotNull(actualShoppingRefundDetailDO);
    	Assert.assertEquals(StatusEnum.INVALID.getValue(), actualShoppingRefundDetailDO.getStatus());
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void tradingSuccess1() {
    	ShoppingOrderDO expected = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setGmtTransport(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setIsDone(false);
    	expected.setShoppingCartIdsStr("1");
    	expected.setSendTime(0);
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(false);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	ShoppingOrderItemDO refundShoppingOrderItemDO = new ShoppingOrderItemDO();
    	refundShoppingOrderItemDO.setGmtCreate(new Date());
    	refundShoppingOrderItemDO.setGmtModified(new Date());
    	refundShoppingOrderItemDO.setIsAllowRefund(true);
    	refundShoppingOrderItemDO.setIsEvaluation(false);
    	refundShoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.REFUNDING.getValue());
    	refundShoppingOrderItemDO.setRefundStatus(RefundStatusEnum.TO_BE_CONFIRMED.getValue());
    	refundShoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	refundShoppingOrderItemDO.setProductId(1L);
    	refundShoppingOrderItemDO.setProductName("productName");
    	refundShoppingOrderItemDO.setProductModelId(1L);
    	refundShoppingOrderItemDO.setProductModelName("test");
    	refundShoppingOrderItemDO.setQuantity(1);
    	refundShoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	refundShoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	refundShoppingOrderItemDO.setSendTime(0);
    	refundShoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(refundShoppingOrderItemDO);
    	
    	ShoppingRefundDetailDO shoppingRefundDetailDO = new ShoppingRefundDetailDO();
    	shoppingRefundDetailDO.setAmount(refundShoppingOrderItemDO.getSalesPrice().multiply(new BigDecimal(refundShoppingOrderItemDO.getQuantity())));
    	shoppingRefundDetailDO.setDescription("就是想退款");
    	shoppingRefundDetailDO.setGmtModified(new Date());
    	shoppingRefundDetailDO.setGmtCreate(new Date());
    	shoppingRefundDetailDO.setReason("七天无理由退货");
    	shoppingRefundDetailDO.setShoppingOrderItemId(refundShoppingOrderItemDO.getId());
    	shoppingRefundDetailDO.setStatus(StatusEnum.VALID.getValue());
    	shoppingRefundDetailDOMapper.insert(shoppingRefundDetailDO);
    	
    	shoppingOrderService.tradingSuccess(expected.getId(), true);
    	
    	ShoppingOrderDO actual = shoppingOrderDOMapper.selectByPrimaryKey(expected.getId());
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(true, actual.getIsAutomaticReceipt());
    	Assert.assertEquals(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue(), actual.getOrderStatus());
    	
    	ShoppingOrderItemDO actualShoppingOrderItemDO = shoppingOrderItemDOMapper.selectByPrimaryKey(shoppingOrderItemDO.getId());
    	Assert.assertNotNull(actualShoppingOrderItemDO);
    	Assert.assertEquals(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue(), actualShoppingOrderItemDO.getOrderStatus());
    	
    	actualShoppingOrderItemDO = shoppingOrderItemDOMapper.selectByPrimaryKey(refundShoppingOrderItemDO.getId());
    	Assert.assertNotNull(actualShoppingOrderItemDO);
    	Assert.assertNull(actualShoppingOrderItemDO.getRefundStatus());
    	Assert.assertEquals(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue(), actualShoppingOrderItemDO.getOrderStatus());
    	
    	ShoppingRefundDetailDO actualShoppingRefundDetailDO = shoppingRefundDetailDOMapper.selectByPrimaryKey(shoppingRefundDetailDO.getId());
    	Assert.assertNotNull(actualShoppingRefundDetailDO);
    	Assert.assertEquals(StatusEnum.INVALID.getValue(), actualShoppingRefundDetailDO.getStatus());
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void tradingSuccess2() {
    	ShoppingOrderDO expected = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setGmtTransport(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setIsDone(false);
    	expected.setShoppingCartIdsStr("1");
    	expected.setSendTime(0);
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(false);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	ShoppingOrderItemDO refundShoppingOrderItemDO = new ShoppingOrderItemDO();
    	refundShoppingOrderItemDO.setGmtCreate(new Date());
    	refundShoppingOrderItemDO.setGmtModified(new Date());
    	refundShoppingOrderItemDO.setIsAllowRefund(true);
    	refundShoppingOrderItemDO.setIsEvaluation(false);
    	refundShoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.REFUNDING.getValue());
    	refundShoppingOrderItemDO.setRefundStatus(RefundStatusEnum.TO_BE_CONFIRMED.getValue());
    	refundShoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	refundShoppingOrderItemDO.setProductId(1L);
    	refundShoppingOrderItemDO.setProductName("productName");
    	refundShoppingOrderItemDO.setProductModelId(1L);
    	refundShoppingOrderItemDO.setProductModelName("test");
    	refundShoppingOrderItemDO.setQuantity(1);
    	refundShoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	refundShoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	refundShoppingOrderItemDO.setSendTime(0);
    	refundShoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(refundShoppingOrderItemDO);
    	
    	ShoppingRefundDetailDO shoppingRefundDetailDO = new ShoppingRefundDetailDO();
    	shoppingRefundDetailDO.setAmount(refundShoppingOrderItemDO.getSalesPrice().multiply(new BigDecimal(refundShoppingOrderItemDO.getQuantity())));
    	shoppingRefundDetailDO.setDescription("就是想退款");
    	shoppingRefundDetailDO.setGmtModified(new Date());
    	shoppingRefundDetailDO.setGmtCreate(new Date());
    	shoppingRefundDetailDO.setReason("七天无理由退货");
    	shoppingRefundDetailDO.setShoppingOrderItemId(refundShoppingOrderItemDO.getId());
    	shoppingRefundDetailDO.setStatus(StatusEnum.VALID.getValue());
    	shoppingRefundDetailDOMapper.insert(shoppingRefundDetailDO);
    	
    	shoppingOrderService.tradingSuccess(expected.getId(), expected.getMemberId());
    	
    	ShoppingOrderDO actual = shoppingOrderDOMapper.selectByPrimaryKey(expected.getId());
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(false, actual.getIsAutomaticReceipt());
    	Assert.assertEquals(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue(), actual.getOrderStatus());
    	
    	ShoppingOrderItemDO actualShoppingOrderItemDO = shoppingOrderItemDOMapper.selectByPrimaryKey(shoppingOrderItemDO.getId());
    	Assert.assertNotNull(actualShoppingOrderItemDO);
    	Assert.assertEquals(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue(), actualShoppingOrderItemDO.getOrderStatus());
    	
    	actualShoppingOrderItemDO = shoppingOrderItemDOMapper.selectByPrimaryKey(refundShoppingOrderItemDO.getId());
    	Assert.assertNotNull(actualShoppingOrderItemDO);
    	Assert.assertNull(actualShoppingOrderItemDO.getRefundStatus());
    	Assert.assertEquals(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue(), actualShoppingOrderItemDO.getOrderStatus());
    	
    	ShoppingRefundDetailDO actualShoppingRefundDetailDO = shoppingRefundDetailDOMapper.selectByPrimaryKey(shoppingRefundDetailDO.getId());
    	Assert.assertNotNull(actualShoppingRefundDetailDO);
    	Assert.assertEquals(StatusEnum.INVALID.getValue(), actualShoppingRefundDetailDO.getStatus());
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateInformation() {
    	ShoppingOrderDO shoppingOrderDO = new ShoppingOrderDO();
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
    	shoppingOrderDO.setOrderStatus(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue());
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
    	shoppingOrderDOMapper.insertSelective(shoppingOrderDO);
    	
    	ShoppingOrderUpdateInfomationParam expected = ShoppingOrderConverterTest.initShoppingOrderUpdateInfomationParam();
    	shoppingOrderService.updateInformation(shoppingOrderDO.getId(), expected);
    	
    	ShoppingOrderDO actual = shoppingOrderDOMapper.selectByPrimaryKey(shoppingOrderDO.getId());
    	ShoppingOrderConverterTest.assertShoppingOrderDOByUpdate(expected, actual, shoppingOrderDO.getId());
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void orderMoneyForNotify() {
        /*
         * 插入两条待付款订单记录
         */
        ShoppingOrderDO shoppingOrderDO1 = new ShoppingOrderDO();
        shoppingOrderDO1.setCommodityTotalPrice(new BigDecimal(1));
        shoppingOrderDO1.setActualAmount(new BigDecimal(1));
        shoppingOrderDO1.setFreightPrice(new BigDecimal(0));
        shoppingOrderDO1.setGmtCreate(new Date());
        shoppingOrderDO1.setGmtModified(new Date());
        shoppingOrderDO1.setIsFans(true);
        shoppingOrderDO1.setIsNeedsLogistics(true);
        shoppingOrderDO1.setIsNoReasonReturn(true);
        shoppingOrderDO1.setMemberId(1L);
        shoppingOrderDO1.setMemberNum("M0001");
        shoppingOrderDO1.setMerchantId(1L);
        shoppingOrderDO1.setMerchantName("拉乌网络");
        shoppingOrderDO1.setMerchantStoreId(1L);
        shoppingOrderDO1.setMerchantNum("B0001");
        shoppingOrderDO1.setOrderStatus(ShoppingOrderStatusEnum.BE_SHIPPED.getValue());
        shoppingOrderDO1.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
        shoppingOrderDO1.setOrderTotalPrice(new BigDecimal(1));
        shoppingOrderDO1.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
        shoppingOrderDO1.setStatus(StatusEnum.VALID.getValue());
        shoppingOrderDO1.setConsigneeAddress("大冲商务中心1301");
        shoppingOrderDO1.setConsigneeMobile("123456");
        shoppingOrderDO1.setConsigneeName("Sunny");
        shoppingOrderDO1.setIsDone(false);
        shoppingOrderDO1.setShoppingCartIdsStr("1");
        shoppingOrderDO1.setSendTime(0);
        shoppingOrderDOMapper.insertSelective(shoppingOrderDO1);
        
        ShoppingOrderDO shoppingOrderDO2 = new ShoppingOrderDO();
        shoppingOrderDO2.setCommodityTotalPrice(new BigDecimal(1));
        shoppingOrderDO2.setActualAmount(new BigDecimal(1));
        shoppingOrderDO2.setFreightPrice(new BigDecimal(0));
        shoppingOrderDO2.setGmtCreate(new Date());
        shoppingOrderDO2.setGmtModified(new Date());
        shoppingOrderDO2.setIsFans(true);
        shoppingOrderDO2.setIsNeedsLogistics(true);
        shoppingOrderDO2.setIsNoReasonReturn(true);
        shoppingOrderDO2.setMemberId(1L);
        shoppingOrderDO2.setMemberNum("M0001");
        shoppingOrderDO2.setMerchantId(2L);
        shoppingOrderDO2.setMerchantName("拉乌网络");
        shoppingOrderDO2.setMerchantStoreId(1L);
        shoppingOrderDO2.setMerchantNum("B0001");
        shoppingOrderDO2.setOrderStatus(ShoppingOrderStatusEnum.BE_SHIPPED.getValue());
        shoppingOrderDO2.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
        shoppingOrderDO2.setOrderTotalPrice(new BigDecimal(1));
        shoppingOrderDO2.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
        shoppingOrderDO2.setStatus(StatusEnum.VALID.getValue());
        shoppingOrderDO2.setConsigneeAddress("大冲商务中心1301");
        shoppingOrderDO2.setConsigneeMobile("123456");
        shoppingOrderDO2.setConsigneeName("Sunny");
        shoppingOrderDO2.setIsDone(false);
        shoppingOrderDO2.setShoppingCartIdsStr("2");
        shoppingOrderDO2.setSendTime(0);
        shoppingOrderDOMapper.insertSelective(shoppingOrderDO2);
        
        String orderIds = shoppingOrderDO1.getId() + "," + shoppingOrderDO2.getId();
        
        ShoppingOrderMoneyBO actual = shoppingOrderService.orderMoneyForNotify(orderIds);
        Assert.assertNotNull(actual);
        Assert.assertEquals(shoppingOrderDO1.getOrderTotalPrice().add(shoppingOrderDO2.getOrderTotalPrice()).doubleValue(), actual.getOrderTotalPrice().doubleValue(), 0D);
    }
}
