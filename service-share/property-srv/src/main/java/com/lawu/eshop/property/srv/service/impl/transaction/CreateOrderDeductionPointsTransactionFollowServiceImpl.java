package com.lawu.eshop.property.srv.service.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.mq.dto.order.CreateOrderDeductionPointsNotification;
import com.lawu.eshop.mq.dto.order.reply.CreateOrderDeductionPointsReply;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.param.CheckRepeatOfPropertyOperationParam;
import com.lawu.eshop.property.param.PropertyInfoDataParam;
import com.lawu.eshop.property.srv.service.PointDetailService;
import com.lawu.eshop.property.srv.service.PropertyInfoDataService;

/**
 * 创建购物订单扣除积分事务-从模块
 * 
 * @author jiangxinjun
 * @createDate 2018年3月12日
 * @updateDate 2018年3月12日
 */
@Service
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_ORDER_SRV, tags = MqConstant.TAG_CREATE_ORDER_DEDUCTION_POINTS)
public class CreateOrderDeductionPointsTransactionFollowServiceImpl extends
        AbstractTransactionFollowService<CreateOrderDeductionPointsNotification, CreateOrderDeductionPointsReply> {

    @Autowired
    private PropertyInfoDataService propertyInfoDataService;
    
    @Autowired
    private PointDetailService pointDetailService;
    
    @Override
    public void execute(CreateOrderDeductionPointsNotification notification) {
        PropertyInfoDataParam param = new PropertyInfoDataParam();
        param.setUserNum(notification.getMemberNum());
        param.setPoint(notification.getPoint().toString());
        param.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.POINT_SHOPPING);
        param.setBizId(String.valueOf(notification.getShoppingOrderId()));
        param.setGmtExecute(notification.getGmtExecute());
        propertyInfoDataService.doHanlderMinusPoint(param);
    }
    
    @Override
    public CreateOrderDeductionPointsReply getReply(CreateOrderDeductionPointsNotification notification) {
        CheckRepeatOfPropertyOperationParam param = new CheckRepeatOfPropertyOperationParam();
        param.setUserNum(notification.getMemberNum());
        param.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.POINT_SHOPPING);
        param.setBizIds(String.valueOf(notification.getShoppingOrderId()));
        boolean itExist = pointDetailService.verifyRepeatByUserNumAndTransactionTypeAndBizId(param);
        CreateOrderDeductionPointsReply reply = new CreateOrderDeductionPointsReply();
        reply.setIsSuccess(itExist);
        return reply;
    }
}
