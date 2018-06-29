package com.lawu.eshop.order.srv.service.impl.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.common.constants.OrderRefundStatusEnum;
import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.mq.dto.order.ShoppingRefundAgreeToRefundNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.order.constants.ShoppingOrderStatusEnum;
import com.lawu.eshop.order.srv.constants.TransactionConstant;
import com.lawu.eshop.order.srv.domain.ShoppingOrderDO;
import com.lawu.eshop.order.srv.domain.ShoppingOrderItemDO;
import com.lawu.eshop.order.srv.domain.ShoppingOrderItemDOExample;
import com.lawu.eshop.order.srv.domain.ShoppingRefundDetailDO;
import com.lawu.eshop.order.srv.domain.ShoppingRefundDetailDOExample;
import com.lawu.eshop.order.srv.mapper.ShoppingOrderDOMapper;
import com.lawu.eshop.order.srv.mapper.ShoppingOrderItemDOMapper;
import com.lawu.eshop.order.srv.mapper.ShoppingRefundDetailDOMapper;

/**
 * 商家确认退款事务-主模块 发送消息给property模块，退款给买家
 * 
 * @author jiangxinjun
 * @createDate 2017年4月15日
 * @updateDate 2018年4月18日
 */
@Service("shoppingRefundAgreeToRefundTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.AGREE_TO_REFUND, topic = MqConstant.TOPIC_ORDER_SRV, tags = MqConstant.TAG_AGREE_TO_REFUND)
public class ShoppingRefundAgreeToRefundTransactionMainServiceImpl
        extends AbstractTransactionMainService<ShoppingRefundAgreeToRefundNotification, Reply> {

    @Autowired
    private ShoppingRefundDetailDOMapper shoppingRefundDetailDOMapper;
    
    @Autowired
    private ShoppingOrderItemDOMapper shoppingOrderItemDOMapper;
    
    @Autowired
    private ShoppingOrderDOMapper shoppingOrderDOMapper;
    
    @Override
    public ShoppingRefundAgreeToRefundNotification selectNotification(Long shoppingOrderItemId) {
        ShoppingRefundAgreeToRefundNotification rtn = new ShoppingRefundAgreeToRefundNotification();
        ShoppingOrderItemDO shoppingOrderItemDO  = shoppingOrderItemDOMapper.selectByPrimaryKey(shoppingOrderItemId);
        ShoppingRefundDetailDOExample shoppingRefundDetailDOExample = new ShoppingRefundDetailDOExample();
        shoppingRefundDetailDOExample.createCriteria().andShoppingOrderItemIdEqualTo(shoppingOrderItemId).andStatusEqualTo(StatusEnum.VALID.getValue());
        List<ShoppingRefundDetailDO> shoppingRefundDetailDOList = shoppingRefundDetailDOMapper.selectByExample(shoppingRefundDetailDOExample);
        ShoppingRefundDetailDO shoppingRefundDetailDO = shoppingRefundDetailDOList.get(0);
        ShoppingOrderDO shoppingOrderDO = shoppingOrderDOMapper.selectByPrimaryKey(shoppingOrderItemDO.getShoppingOrderId());
        ShoppingOrderItemDOExample example = new ShoppingOrderItemDOExample();
        example.createCriteria().andShoppingOrderIdEqualTo(shoppingOrderItemDO.getShoppingOrderId())
        .andOrderStatusNotEqualTo(ShoppingOrderStatusEnum.CANCEL_TRANSACTION.getValue()).andIdNotEqualTo(shoppingOrderItemDO.getId());
        Long count = shoppingOrderItemDOMapper.countByExample(example);
        // 是否是最后一个退款项
        boolean isLast = (count == 0);
        rtn.setActualAmount(shoppingRefundDetailDO.getActualAmount());
        rtn.setAmount(shoppingRefundDetailDO.getAmount());
        rtn.setFreightPrice(shoppingOrderDO.getFreightPrice());
        rtn.setPoint(shoppingRefundDetailDO.getPoint());
        rtn.setOrderItemProdcutName(shoppingOrderItemDO.getProductName());
        if (ShoppingOrderStatusEnum.TRADING_SUCCESS == ShoppingOrderStatusEnum.getEnum(shoppingRefundDetailDO.getOrderStatus())) {
            rtn.setStatus(OrderRefundStatusEnum.FINISH);
        } else if (ShoppingOrderStatusEnum.BE_SHIPPED == ShoppingOrderStatusEnum.getEnum(shoppingRefundDetailDO.getOrderStatus())) {
            rtn.setStatus(OrderRefundStatusEnum.IN_PROCESSING_UNSHIPPED);
        } else if (ShoppingOrderStatusEnum.TO_BE_RECEIVED == ShoppingOrderStatusEnum.getEnum(shoppingRefundDetailDO.getOrderStatus())) {
            rtn.setStatus(OrderRefundStatusEnum.IN_PROCESSING);
        }
        rtn.setMemberNum(shoppingOrderDO.getMemberNum());
        rtn.setMerchantNum(shoppingOrderDO.getMerchantNum());
        rtn.setShoppingOrderId(shoppingOrderDO.getId());
        rtn.setShoppingOrderItemId(shoppingOrderItemDO.getId());
        rtn.setPaymentMethod(TransactionPayTypeEnum.getEnum(shoppingOrderDO.getPaymentMethod()));
        rtn.setIsLast(isLast);
        rtn.setThirdNumber(shoppingOrderDO.getThirdNumber());
        rtn.setGmtExecute(shoppingOrderDO.getGmtModified());
        return rtn;
    }
}
