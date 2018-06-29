package com.lawu.eshop.order.srv.jobs;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.order.constants.CommissionStatusEnum;
import com.lawu.eshop.order.constants.ShoppingOrderStatusEnum;
import com.lawu.eshop.order.srv.OrderSrvApplicationTest;
import com.lawu.eshop.order.srv.constants.PropertyNameConstant;
import com.lawu.eshop.order.srv.domain.PropertyDO;
import com.lawu.eshop.order.srv.domain.ShoppingOrderDO;
import com.lawu.eshop.order.srv.domain.ShoppingOrderItemDO;
import com.lawu.eshop.order.srv.mapper.PropertyDOMapper;
import com.lawu.eshop.order.srv.mapper.ShoppingOrderDOMapper;
import com.lawu.eshop.order.srv.mapper.ShoppingOrderItemDOMapper;
import com.lawu.utils.DateUtil;

/**
 * 自动取消未付款的订单定时任务单元测试类
 * 
 * @author jiangxinjun
 * @createDate 2017年11月14日
 * @updateDate 2017年11月14日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OrderSrvApplicationTest.class)
public class ShoppingOrderAutoCancelOrderJobTest {
    
    @Autowired
    private ShoppingOrderAutoCancelOrderJob shoppingOrderAutoCancelOrderJob;
    
    @Autowired
    private ShardingContext shardingContext;
    
    @Autowired
    private PropertyDOMapper propertyDOMapper;
    
    @Autowired
    private ShoppingOrderDOMapper shoppingOrderDOMapper;
    
    @Autowired
    private ShoppingOrderItemDOMapper shoppingOrderItemDOMapper;
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void execute() {
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
        
        shoppingOrderAutoCancelOrderJob.execute(shardingContext);
        
        ShoppingOrderDO actual = shoppingOrderDOMapper.selectByPrimaryKey(expected.getId());
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getGmtTransaction());
        Assert.assertEquals(ShoppingOrderStatusEnum.CANCEL_TRANSACTION.getValue(), actual.getOrderStatus());
        
        ShoppingOrderItemDO actualShoppingOrderItemDO = shoppingOrderItemDOMapper.selectByPrimaryKey(shoppingOrderItemDO.getId());
        Assert.assertEquals(ShoppingOrderStatusEnum.CANCEL_TRANSACTION.getValue(), actualShoppingOrderItemDO.getOrderStatus());
    }
}
