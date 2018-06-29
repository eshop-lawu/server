package com.lawu.eshop.property.srv.service.impl.transaction;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.common.constants.UserCommonConstant;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.mq.dto.game.GameDialTakeLotteryNotification;
import com.lawu.eshop.mq.dto.game.reply.GameDialTakeLotteryReply;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.constants.MerchantTransactionTypeEnum;
import com.lawu.eshop.property.constants.PayTypeEnum;
import com.lawu.eshop.property.constants.PropertyType;
import com.lawu.eshop.property.param.PropertyInfoDataParam;
import com.lawu.eshop.property.srv.service.PropertyInfoDataService;
import com.lawu.eshop.property.srv.service.PropertyService;

/**
 * 活动模块，领奖(余额/积分) - 从事务
 *
 * @author meishuquan
 * @date 2018/2/28
 */
@Service
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_GAME_SRV, tags = MqConstant.TAG_GAME_DIAL_TAKE_LOTTERY)
public class GameDialTakeLotteryTransactionFollowServiceImpl extends AbstractTransactionFollowService<GameDialTakeLotteryNotification, GameDialTakeLotteryReply> {

    @Autowired
    private PropertyInfoDataService propertyInfoDataService;

    @Autowired
    private PropertyService propertyService;

    @Override
    public void execute(GameDialTakeLotteryNotification notification) {

        int retCode = ResultCode.FAIL;
        PropertyInfoDataParam propertyInfoDataParam = new PropertyInfoDataParam();
        propertyInfoDataParam.setUserNum(notification.getUserNum());
        propertyInfoDataParam.setPoint(notification.getMoney());
        propertyInfoDataParam.setBizId(String.valueOf(notification.getId()));
        propertyInfoDataParam.setGmtExecute(notification.getGmtExecute());

        if (PayTypeEnum.BALANCE.getVal().equals(notification.getPayTypeEnum().getVal())) {
            propertyInfoDataParam.setTempBizId(String.valueOf(notification.getId()));
            if (notification.getUserNum().startsWith(UserCommonConstant.MEMBER_NUM_TAG)) {
                propertyInfoDataParam.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.BACKAGE);
                propertyInfoDataParam.setTitle(MemberTransactionTypeEnum.BACKAGE.getName());
                propertyInfoDataParam.setTransactionDesc(MemberTransactionTypeEnum.BACKAGE.getDescPrefix());
            } else if (notification.getUserNum().startsWith(UserCommonConstant.MERCHANT_NUM_TAG)) {
                propertyInfoDataParam.setMerchantTransactionTypeEnum(MerchantTransactionTypeEnum.BACKAGE);
                propertyInfoDataParam.setTitle(MerchantTransactionTypeEnum.BACKAGE.getName());
                propertyInfoDataParam.setTransactionDesc(MerchantTransactionTypeEnum.BACKAGE.getDescPrefix());
            }
            retCode = propertyInfoDataService.doHanlderAddBalance(propertyInfoDataParam);

        } else if (PayTypeEnum.POINT.getVal().equals(notification.getPayTypeEnum().getVal())) {
            if (notification.getUserNum().startsWith(UserCommonConstant.MEMBER_NUM_TAG)) {
                propertyInfoDataParam.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.BACKAGE);
            } else if (notification.getUserNum().startsWith(UserCommonConstant.MERCHANT_NUM_TAG)) {
                propertyInfoDataParam.setMerchantTransactionTypeEnum(MerchantTransactionTypeEnum.BACKAGE);
            }
            String pointRate = propertyService.getValue(PropertyType.MEMBER_BALANCE_PAY_POINT_SCALE);
            double point = BigDecimal.valueOf(Double.valueOf(notification.getMoney())).multiply(BigDecimal.valueOf(Double.valueOf(pointRate))).doubleValue();
            propertyInfoDataParam.setPoint(String.valueOf(point));
            retCode = propertyInfoDataService.doHanlderAddPoint(propertyInfoDataParam);
        }
        if (retCode == ResultCode.SUCCESS) {
            notification.setResult(true);
        }
    }

    @Override
    public GameDialTakeLotteryReply getReply(GameDialTakeLotteryNotification notification) {
        GameDialTakeLotteryReply reply = new GameDialTakeLotteryReply();
        reply.setResult(notification.isResult());
        return reply;
    }

}
