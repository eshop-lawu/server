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
public class ShoppingOrderAutoCommentTransactionMainServiceImplTest {

	@SuppressWarnings("rawtypes")
	@Autowired
	@Qualifier("shoppingOrderAutoCommentTransactionMainServiceImpl")
	private TransactionMainService shoppingOrderAutoCommentTransactionMainServiceImpl;
	
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
    	ShoppingOrderDO shoppingOrderDO = new ShoppingOrderDO();
    	shoppingOrderDO.setCommodityTotalPrice(new BigDecimal(1));
    	shoppingOrderDO.setActualAmount(new BigDecimal(1));
    	shoppingOrderDO.setFreightPrice(new BigDecimal(0));
    	shoppingOrderDO.setGmtCreate(new Date());
    	shoppingOrderDO.setGmtModified(new Date());
    	shoppingOrderDO.setIsFans(true);
    	shoppingOrderDO.setIsNeedsLogistics(true);
    	shoppingOrderDO.setIsNoReasonReturn(false);
    	shoppingOrderDO.setMemberId(1L);
    	shoppingOrderDO.setMemberNum("M0001");
    	shoppingOrderDO.setMerchantId(1L);
    	shoppingOrderDO.setMerchantName("拉乌网络");
    	shoppingOrderDO.setMerchantStoreId(1L);
    	shoppingOrderDO.setMerchantNum("B0001");
    	shoppingOrderDO.setOrderStatus(ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue());
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
    	shoppingOrderDOMapper.insertSelective(shoppingOrderDO);
    	
    	ShoppingOrderItemDO expected = new ShoppingOrderItemDO();
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsAllowRefund(true);
    	expected.setIsEvaluation(false);
    	expected.setOrderStatus(ShoppingOrderStatusEnum.PENDING.getValue());
    	expected.setProductFeatureImage("test.jpg");
    	expected.setProductId(1L);
    	expected.setProductName("productName");
    	expected.setProductModelId(1L);
    	expected.setProductModelName("test");
    	expected.setQuantity(1);
    	expected.setRegularPrice(new BigDecimal(1));
    	expected.setSalesPrice(new BigDecimal(1));
    	expected.setSendTime(0);
    	expected.setShoppingOrderId(shoppingOrderDO.getId());
    	shoppingOrderItemDOMapper.insert(expected);
    	// 发送消息
		shoppingOrderAutoCommentTransactionMainServiceImpl.sendNotice(expected.getId());
		// 交易发送消息是否正常
    	TransactionRecordDOExample example = new TransactionRecordDOExample();
    	example.createCriteria().andRelateIdEqualTo(expected.getId());
    	TransactionRecordDO transactionRecordDO = transactionRecordDOMapper.selectByExample(example).get(0);
		Assert.assertNotNull(transactionRecordDO);
		Assert.assertNotNull(transactionRecordDO.getGmtModified());
		Assert.assertNotNull(transactionRecordDO.getGmtCreate());
		Assert.assertNotNull(transactionRecordDO.getId());
		Assert.assertNotNull(transactionRecordDO.getType());
		Assert.assertEquals(false, transactionRecordDO.getIsProcessed());
		Assert.assertEquals(expected.getId(), transactionRecordDO.getRelateId());
		Assert.assertEquals(0L, transactionRecordDO.getTimes().longValue());
		// 接收回复消息
		Reply reply = new Reply();
		reply.setTransactionId(transactionRecordDO.getId());
		shoppingOrderAutoCommentTransactionMainServiceImpl.receiveCallback(reply, new Date().getTime());
		// 校验回复消息处理是否正常
		transactionRecordDO = transactionRecordDOMapper.selectByPrimaryKey(transactionRecordDO.getId());
		Assert.assertEquals(true, transactionRecordDO.getIsProcessed());
	}

}
