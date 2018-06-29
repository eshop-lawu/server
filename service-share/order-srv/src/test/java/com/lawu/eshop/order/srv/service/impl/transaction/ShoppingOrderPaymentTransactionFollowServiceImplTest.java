package com.lawu.eshop.order.srv.service.impl.transaction;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.compensating.transaction.domain.FollowTransactionRecordDO;
import com.lawu.compensating.transaction.domain.FollowTransactionRecordDOExample;
import com.lawu.compensating.transaction.mapper.FollowTransactionRecordDOMapper;
import com.lawu.compensating.transaction.service.TransactionFollowService;
import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.mq.dto.property.ShoppingOrderPaymentNotification;
import com.lawu.eshop.order.constants.CommissionStatusEnum;
import com.lawu.eshop.order.constants.ShoppingOrderStatusEnum;
import com.lawu.eshop.order.srv.EmbeddedRedis;
import com.lawu.eshop.order.srv.OrderSrvApplicationTest;
import com.lawu.eshop.order.srv.domain.ShoppingOrderDO;
import com.lawu.eshop.order.srv.domain.ShoppingOrderItemDO;
import com.lawu.eshop.order.srv.mapper.ShoppingOrderDOMapper;
import com.lawu.eshop.order.srv.mapper.ShoppingOrderItemDOMapper;

/**
 * 
 * @author jiangxinjun
 * @date 2017年7月12日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OrderSrvApplicationTest.class, properties = {"lawu.synchronization-lock.redisson.enabled=true"})
public class ShoppingOrderPaymentTransactionFollowServiceImplTest extends EmbeddedRedis {

    @SuppressWarnings("rawtypes")
	@Autowired
    @Qualifier("shoppingOrderPaymentTransactionFollowServiceImpl")
    private TransactionFollowService shoppingOrderPaymentTransactionFollowServiceImpl;

	@Autowired
	private ShoppingOrderDOMapper shoppingOrderDOMapper;
	
	@Autowired
	private ShoppingOrderItemDOMapper shoppingOrderItemDOMapper;
	
	@Autowired
	private FollowTransactionRecordDOMapper followTransactionRecordDOMapper;
	
    @SuppressWarnings("unchecked")
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void receiveNotice() {
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
    	notification.setShoppingOrderIds(expected.getId().toString());
    	notification.setPaymentMethod(TransactionPayTypeEnum.ALIPAY);
		notification.setTransactionId(1L);
		notification.setThirdNumber("123456");
		shoppingOrderPaymentTransactionFollowServiceImpl.receiveNotice(notification, new Date().getTime());
		
		ShoppingOrderDO actual = shoppingOrderDOMapper.selectByPrimaryKey(expected.getId());
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(ShoppingOrderStatusEnum.BE_SHIPPED.getValue(), actual.getOrderStatus());
    	Assert.assertEquals(notification.getPaymentMethod(), actual.getPaymentMethod());
    	Assert.assertEquals(notification.getThirdNumber(), actual.getThirdNumber());
		
    	ShoppingOrderItemDO actualShoppingOrderItemDO = shoppingOrderItemDOMapper.selectByPrimaryKey(shoppingOrderItemDO.getId());
    	Assert.assertNotNull(actualShoppingOrderItemDO);
    	Assert.assertEquals(ShoppingOrderStatusEnum.BE_SHIPPED.getValue(), actualShoppingOrderItemDO.getOrderStatus());
    	
    	FollowTransactionRecordDOExample example = new FollowTransactionRecordDOExample();
    	example.createCriteria().andTransationIdEqualTo(notification.getTransactionId());
    	FollowTransactionRecordDO followTransactionRecordDO = followTransactionRecordDOMapper.selectByExample(example).get(0);
    	Assert.assertNotNull(followTransactionRecordDO);
    	Assert.assertNotNull(followTransactionRecordDO.getTopic());
    	Assert.assertNotNull(followTransactionRecordDO.getGmtCreate());
    	Assert.assertNotNull(followTransactionRecordDO.getId());
    	Assert.assertEquals(notification.getTransactionId(), followTransactionRecordDO.getTransationId());
    }
    
}
