package com.lawu.eshop.order.srv.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.order.constants.CommissionStatusEnum;
import com.lawu.eshop.order.constants.ShoppingOrderStatusEnum;
import com.lawu.eshop.order.constants.ShoppingRefundTypeEnum;
import com.lawu.eshop.order.param.foreign.ShoppingRefundQueryForeignParam;
import com.lawu.eshop.order.srv.OrderSrvApplicationTest;
import com.lawu.eshop.order.srv.bo.ShoppingOrderItemBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderItemExtendBO;
import com.lawu.eshop.order.srv.converter.ShoppingOrderConverterTest;
import com.lawu.eshop.order.srv.converter.ShoppingOrderItemConverterTest;
import com.lawu.eshop.order.srv.converter.ShoppingRefundDetailConverterTest;
import com.lawu.eshop.order.srv.domain.ShoppingOrderDO;
import com.lawu.eshop.order.srv.domain.ShoppingOrderItemDO;
import com.lawu.eshop.order.srv.domain.ShoppingRefundDetailDO;
import com.lawu.eshop.order.srv.mapper.ShoppingOrderDOMapper;
import com.lawu.eshop.order.srv.mapper.ShoppingOrderItemDOMapper;
import com.lawu.eshop.order.srv.mapper.ShoppingRefundDetailDOMapper;
import com.lawu.eshop.order.srv.service.ShoppingOrderItemService;
import com.lawu.framework.core.page.Page;

/**
 * 
 * @author jiangxinjun
 * @date 2017年7月20日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OrderSrvApplicationTest.class)
public class ShoppingOrderItemServiceImplTest {
	
    @Autowired
    private ShoppingOrderItemService shoppingOrderItemService;
    
	@Autowired
	private ShoppingOrderDOMapper shoppingOrderDOMapper;
	
	@Autowired
	private ShoppingOrderItemDOMapper shoppingOrderItemDOMapper;
	
	@Autowired
	private ShoppingRefundDetailDOMapper shoppingRefundDetailDOMapper;
	
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void get() {
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
    	expected.setShoppingOrderId(1L);
    	shoppingOrderItemDOMapper.insert(expected);
    	ShoppingOrderItemBO actual = shoppingOrderItemService.get(expected.getId());
    	ShoppingOrderItemConverterTest.assertShoppingOrderItemBO(expected, actual);
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void commentsSuccessful() {
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
    	expected.setShoppingOrderId(1L);
    	shoppingOrderItemDOMapper.insert(expected);
    	
    	shoppingOrderItemService.commentsSuccessful(expected.getId());
    	
    	ShoppingOrderItemDO actual = shoppingOrderItemDOMapper.selectByPrimaryKey(expected.getId());
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(true, actual.getIsEvaluation());
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getByComment() {
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
    	
    	ShoppingOrderItemExtendBO actual = shoppingOrderItemService.getByComment(shoppingOrderItemDO.getId());
    	Assert.assertNotNull(actual);
    	ShoppingOrderItemConverterTest.assertShoppingOrderItemBO(shoppingOrderItemDO, actual);
    	ShoppingOrderConverterTest.assertShoppingOrderBO(expected, actual.getShoppingOrder());
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectRefundPage() {
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
    	
    	ShoppingRefundDetailDO shoppingRefundDetailDO = new ShoppingRefundDetailDO();
    	shoppingRefundDetailDO.setAmount(shoppingOrderItemDO.getSalesPrice().multiply(new BigDecimal(shoppingOrderItemDO.getQuantity())));
    	shoppingRefundDetailDO.setDescription("就是想退款");
    	shoppingRefundDetailDO.setGmtModified(new Date());
    	shoppingRefundDetailDO.setGmtCreate(new Date());
    	shoppingRefundDetailDO.setReason("七天无理由退货");
    	shoppingRefundDetailDO.setShoppingOrderItemId(shoppingOrderItemDO.getId());
    	shoppingRefundDetailDO.setStatus(StatusEnum.VALID.getValue());
    	shoppingRefundDetailDO.setType(ShoppingRefundTypeEnum.RETURN_REFUND.getValue());
    	shoppingRefundDetailDOMapper.insert(shoppingRefundDetailDO);
    	
    	ShoppingRefundQueryForeignParam param = new ShoppingRefundQueryForeignParam();
    	param.setCurrentPage(1);
    	param.setPageSize(10);
    	param.setKeyword(expected.getOrderNum());
    	Page<ShoppingOrderItemExtendBO> actual = shoppingOrderItemService.selectRefundPage(param);
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(param.getCurrentPage(), actual.getCurrentPage());
    	Assert.assertEquals(1, actual.getTotalCount().intValue());
    	Assert.assertNotNull(actual.getRecords());
    	for (ShoppingOrderItemExtendBO item : actual.getRecords()) {
    		ShoppingOrderItemConverterTest.assertShoppingOrderItemBO(shoppingOrderItemDO, item);
	    	ShoppingOrderConverterTest.assertShoppingOrderBO(expected, item.getShoppingOrder());
	    	ShoppingRefundDetailConverterTest.assertShoppingRefundDetailBO(shoppingRefundDetailDO, item.getShoppingRefundDetail());
    	}
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectRefundPageByMemberId() {
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
    	
    	ShoppingRefundDetailDO shoppingRefundDetailDO = new ShoppingRefundDetailDO();
    	shoppingRefundDetailDO.setAmount(shoppingOrderItemDO.getSalesPrice().multiply(new BigDecimal(shoppingOrderItemDO.getQuantity())));
    	shoppingRefundDetailDO.setDescription("就是想退款");
    	shoppingRefundDetailDO.setGmtModified(new Date());
    	shoppingRefundDetailDO.setGmtCreate(new Date());
    	shoppingRefundDetailDO.setReason("七天无理由退货");
    	shoppingRefundDetailDO.setShoppingOrderItemId(shoppingOrderItemDO.getId());
    	shoppingRefundDetailDO.setStatus(StatusEnum.VALID.getValue());
    	shoppingRefundDetailDO.setType(ShoppingRefundTypeEnum.RETURN_REFUND.getValue());
    	shoppingRefundDetailDOMapper.insert(shoppingRefundDetailDO);
    	
    	ShoppingRefundQueryForeignParam param = new ShoppingRefundQueryForeignParam();
    	param.setCurrentPage(1);
    	param.setPageSize(10);
    	param.setKeyword(expected.getOrderNum());
    	Page<ShoppingOrderItemExtendBO> actual = shoppingOrderItemService.selectRefundPageByMemberId(expected.getMemberId(), param);
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(param.getCurrentPage(), actual.getCurrentPage());
    	Assert.assertEquals(1, actual.getTotalCount().intValue());
    	Assert.assertNotNull(actual.getRecords());
    	for (ShoppingOrderItemExtendBO item : actual.getRecords()) {
    		ShoppingOrderItemConverterTest.assertShoppingOrderItemBO(shoppingOrderItemDO, item);
	    	ShoppingOrderConverterTest.assertShoppingOrderBO(expected, item.getShoppingOrder());
	    	ShoppingRefundDetailConverterTest.assertShoppingRefundDetailBO(shoppingRefundDetailDO, item.getShoppingRefundDetail());
    	}
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectRefundPageByMerchantId() {
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
    	
    	ShoppingRefundDetailDO shoppingRefundDetailDO = new ShoppingRefundDetailDO();
    	shoppingRefundDetailDO.setAmount(shoppingOrderItemDO.getSalesPrice().multiply(new BigDecimal(shoppingOrderItemDO.getQuantity())));
    	shoppingRefundDetailDO.setDescription("就是想退款");
    	shoppingRefundDetailDO.setGmtModified(new Date());
    	shoppingRefundDetailDO.setGmtCreate(new Date());
    	shoppingRefundDetailDO.setReason("七天无理由退货");
    	shoppingRefundDetailDO.setShoppingOrderItemId(shoppingOrderItemDO.getId());
    	shoppingRefundDetailDO.setStatus(StatusEnum.VALID.getValue());
    	shoppingRefundDetailDO.setType(ShoppingRefundTypeEnum.RETURN_REFUND.getValue());
    	shoppingRefundDetailDOMapper.insert(shoppingRefundDetailDO);
    	
    	ShoppingRefundQueryForeignParam param = new ShoppingRefundQueryForeignParam();
    	param.setCurrentPage(1);
    	param.setPageSize(10);
    	param.setKeyword(expected.getOrderNum());
    	Page<ShoppingOrderItemExtendBO> actual = shoppingOrderItemService.selectRefundPageByMerchantId(expected.getMerchantId(), param);
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(param.getCurrentPage(), actual.getCurrentPage());
    	Assert.assertEquals(1, actual.getTotalCount().intValue());
    	Assert.assertNotNull(actual.getRecords());
    	for (ShoppingOrderItemExtendBO item : actual.getRecords()) {
    		ShoppingOrderItemConverterTest.assertShoppingOrderItemBO(shoppingOrderItemDO, item);
	    	ShoppingOrderConverterTest.assertShoppingOrderBO(expected, item.getShoppingOrder());
	    	ShoppingRefundDetailConverterTest.assertShoppingRefundDetailBO(shoppingRefundDetailDO, item.getShoppingRefundDetail());
    	}
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getOrderItemProductName() {
        /*
         * 插入一条订单记录，其下有两条订单项记录
         */
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
        expected.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
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
        
        ShoppingOrderItemDO shoppingOrderItemDO2 = new ShoppingOrderItemDO();
        shoppingOrderItemDO2.setGmtCreate(new Date());
        shoppingOrderItemDO2.setGmtModified(new Date());
        shoppingOrderItemDO2.setIsAllowRefund(true);
        shoppingOrderItemDO2.setIsEvaluation(false);
        shoppingOrderItemDO2.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
        shoppingOrderItemDO2.setProductFeatureImage("test.jpg");
        shoppingOrderItemDO2.setProductId(1L);
        shoppingOrderItemDO2.setProductName("productName2");
        shoppingOrderItemDO2.setProductModelId(1L);
        shoppingOrderItemDO2.setProductModelName("test");
        shoppingOrderItemDO2.setQuantity(1);
        shoppingOrderItemDO2.setRegularPrice(new BigDecimal(1));
        shoppingOrderItemDO2.setSalesPrice(new BigDecimal(1));
        shoppingOrderItemDO2.setSendTime(0);
        shoppingOrderItemDO2.setShoppingOrderId(expected.getId());
        shoppingOrderItemDOMapper.insert(shoppingOrderItemDO2);
        
        String actual = shoppingOrderItemService.getOrderItemProductName(expected.getId());
        Assert.assertEquals(shoppingOrderItemDO.getProductName(), actual);
    }
}
