package com.lawu.eshop.user.srv.service.impl.transaction;

import java.util.Date;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.mq.dto.property.SalesOrderPaymentUpdateUserGradeNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.user.constants.PropertyType;
import com.lawu.eshop.user.srv.bo.MemberBO;
import com.lawu.eshop.user.srv.bo.UserGradeBO;
import com.lawu.eshop.user.srv.domain.UserGradeDetailDO;
import com.lawu.eshop.user.srv.mapper.UserGradeDetailDOMapper;
import com.lawu.eshop.user.srv.service.MemberService;
import com.lawu.eshop.user.srv.service.PropertyService;
import com.lawu.eshop.user.srv.service.UserGradeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 购物支付订单成功后修改用户等级事务处理-从模块
 *
 * @author yangqh
 * @date 2017年11月24日
 */
@Service("shoppingOrderPaymentUpdateUserGradeTransactionFollowServiceImpl")
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_PROPERTY_SRV, tags = MqConstant.TAG_SHOPPING_ORDER_PAY_UPDATE_USER_GRADE)
public class ShoppingOrderPaymentUpdateUserGradeTransactionFollowServiceImpl extends AbstractTransactionFollowService<SalesOrderPaymentUpdateUserGradeNotification, Reply> {

    @Autowired
    private UserGradeService userGradeService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private UserGradeDetailDOMapper userGradeDetailDOMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void execute(SalesOrderPaymentUpdateUserGradeNotification notification) {
        MemberBO member = memberService.findMemberByNum(notification.getUserNum());
        String growthValueRate = propertyService.getValue(PropertyType.GROWTH_VALUE_RATE);
        Integer growthValue = Double.valueOf(notification.getPayMoney().intValue() * Double.parseDouble(growthValueRate)).intValue();
        Integer resultGrowthValue = member.getGrowthValue() + growthValue;
        UserGradeBO userGradeBO = userGradeService.selectUserGradeByMinGrowthValue(resultGrowthValue);
        Byte userCurrentGrade = member.getGrade();
        Byte ofterGrade = userGradeBO.getGradeValue();
        if (ofterGrade > userCurrentGrade) {
            userCurrentGrade = ofterGrade;
        }
        memberService.updateGradeInfoByUserNum(notification.getUserNum(), resultGrowthValue, userCurrentGrade);

        UserGradeDetailDO userGradeDetailDO = new UserGradeDetailDO();
        userGradeDetailDO.setUserNum(notification.getUserNum());
        userGradeDetailDO.setTransactionDetailId(notification.getTransactionDetailId());
        userGradeDetailDO.setTransactionDetailType(notification.getTransactionType());
        userGradeDetailDO.setBizId(notification.getOrderId());
        userGradeDetailDO.setGrowthValue(growthValue);
        userGradeDetailDO.setGradeBefore(member.getGrade());
        userGradeDetailDO.setGradeAfter(userCurrentGrade);
        userGradeDetailDO.setGrowthValueBefore(member.getGrowthValue());
        userGradeDetailDO.setGrowthValueAfter(resultGrowthValue);
        userGradeDetailDO.setGmtCreate(new Date());
        userGradeDetailDOMapper.insertSelective(userGradeDetailDO);
    }
}