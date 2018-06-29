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

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.domain.TransactionRecordDO;
import com.lawu.compensating.transaction.domain.TransactionRecordDOExample;
import com.lawu.compensating.transaction.mapper.TransactionRecordDOMapper;
import com.lawu.compensating.transaction.service.TransactionMainService;
import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.order.constants.CommissionStatusEnum;
import com.lawu.eshop.order.constants.ShoppingOrderStatusEnum;
import com.lawu.eshop.order.srv.OrderSrvApplicationTest;
import com.lawu.eshop.order.srv.domain.ShoppingOrderDO;
import com.lawu.eshop.order.srv.domain.ShoppingOrderItemDO;
import com.lawu.eshop.order.srv.mapper.ShoppingOrderDOMapper;
import com.lawu.eshop.order.srv.mapper.ShoppingOrderItemDOMapper;

/**
 * 
 * @author jiangxinjun
 * @date 2017年7月24日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OrderSrvApplicationTest.class)
public class ShoppingRefundAgreeToRefundDeleteCommentTransactionMainServiceImplTest {

	@SuppressWarnings("rawtypes")
	@Autowired
	@Qualifier("shoppingRefundAgreeToRefundDeleteCommentTransactionMainServiceImpl")
	private TransactionMainService shoppingRefundAgreeToRefundDeleteCommentTransactionMainServiceImpl;
	
	@Autowired
	private ShoppingOrderDOMapper shoppingOrderDOMapper;
	
	@Autowired
	private ShoppingOrderItemDOMapper shoppingOrderItemDOMapper;
	
	@Autowired
	private TransactionRecordDOMapper transactionRecordDOMapper;

	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void receiveNotice() {
		// 初始化数据
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
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setIsDone(true);
    	expected.setShoppingCartIdsStr("1");
    	expected.setSendTime(0);
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
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
    	// 发送消息
		shoppingRefundAgreeToRefundDeleteCommentTransactionMainServiceImpl.sendNotice(shoppingOrderItemDO.getId());
		
		// 交易发送消息是否正常
    	TransactionRecordDOExample example = new TransactionRecordDOExample();
    	example.createCriteria().andRelateIdEqualTo(shoppingOrderItemDO.getId());
    	TransactionRecordDO transactionRecordDO = transactionRecordDOMapper.selectByExample(example).get(0);
		Assert.assertNotNull(transactionRecordDO);
		Assert.assertNotNull(transactionRecordDO.getGmtModified());
		Assert.assertNotNull(transactionRecordDO.getGmtCreate());
		Assert.assertNotNull(transactionRecordDO.getId());
		Assert.assertNotNull(transactionRecordDO.getType());
		Assert.assertEquals(false, transactionRecordDO.getIsProcessed());
		Assert.assertEquals(shoppingOrderItemDO.getId(), transactionRecordDO.getRelateId());
		Assert.assertEquals(0L, transactionRecordDO.getTimes().longValue());
		
		// 接收回复消息
    	Reply reply = new Reply();
    	reply.setTransactionId(transactionRecordDO.getId());
    	shoppingRefundAgreeToRefundDeleteCommentTransactionMainServiceImpl.receiveCallback(reply, new Date().getTime());
    	
		// 校验接口回复消息处理是否正常
		transactionRecordDO = transactionRecordDOMapper.selectByPrimaryKey(transactionRecordDO.getId());
		Assert.assertEquals(true, transactionRecordDO.getIsProcessed());
	}

}
