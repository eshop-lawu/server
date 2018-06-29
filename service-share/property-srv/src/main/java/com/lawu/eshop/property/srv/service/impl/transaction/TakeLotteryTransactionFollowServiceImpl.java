package com.lawu.eshop.property.srv.service.impl.transaction;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.common.constants.UserCommonConstant;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.mq.dto.activity.TakeLotteryNotification;
import com.lawu.eshop.mq.dto.activity.reply.TakeLotteryReply;
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
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_ACTIVITY_SRV, tags = MqConstant.TAG_TAKE_LOTTERY)
public class TakeLotteryTransactionFollowServiceImpl extends AbstractTransactionFollowService<TakeLotteryNotification, TakeLotteryReply> {

    @Autowired
    private PropertyInfoDataService propertyInfoDataService;

    @Autowired
    private PropertyService propertyService;

    @Override
    public void execute(TakeLotteryNotification takeLotteryNotification) {

        int retCode = ResultCode.FAIL;
        PropertyInfoDataParam propertyInfoDataParam = new PropertyInfoDataParam();
        propertyInfoDataParam.setUserNum(takeLotteryNotification.getUserNum());
        propertyInfoDataParam.setPoint(takeLotteryNotification.getMoney());
        propertyInfoDataParam.setBizId(String.valueOf(takeLotteryNotification.getId()));
        propertyInfoDataParam.setGmtExecute(takeLotteryNotification.getGmtExecute());

        if (PayTypeEnum.BALANCE.getVal().equals(takeLotteryNotification.getPayTypeEnum().getVal())) {
            propertyInfoDataParam.setTempBizId(String.valueOf(takeLotteryNotification.getId()));
            if(takeLotteryNotification.getUserNum().startsWith(UserCommonConstant.MEMBER_NUM_TAG)){
                propertyInfoDataParam.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.BACKAGE);
                propertyInfoDataParam.setTitle(MemberTransactionTypeEnum.BACKAGE.getName());
                propertyInfoDataParam.setTransactionDesc(MemberTransactionTypeEnum.BACKAGE.getDescPrefix());
            }else if(takeLotteryNotification.getUserNum().startsWith(UserCommonConstant.MERCHANT_NUM_TAG)){
                propertyInfoDataParam.setMerchantTransactionTypeEnum(MerchantTransactionTypeEnum.BACKAGE);
                propertyInfoDataParam.setTitle(MerchantTransactionTypeEnum.BACKAGE.getName());
                propertyInfoDataParam.setTransactionDesc(MerchantTransactionTypeEnum.BACKAGE.getDescPrefix());
            }
            retCode = propertyInfoDataService.doHanlderAddBalance(propertyInfoDataParam);

        } else if (PayTypeEnum.POINT.getVal().equals(takeLotteryNotification.getPayTypeEnum().getVal())) {
            if(takeLotteryNotification.getUserNum().startsWith(UserCommonConstant.MEMBER_NUM_TAG)){
                propertyInfoDataParam.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.BACKAGE);
            }else if(takeLotteryNotification.getUserNum().startsWith(UserCommonConstant.MERCHANT_NUM_TAG)){
                propertyInfoDataParam.setMerchantTransactionTypeEnum(MerchantTransactionTypeEnum.BACKAGE);
            }
            String pointRate = propertyService.getValue(PropertyType.MEMBER_BALANCE_PAY_POINT_SCALE);
            double point = BigDecimal.valueOf(Double.valueOf(takeLotteryNotification.getMoney())).multiply(BigDecimal.valueOf(Double.valueOf(pointRate))).doubleValue();
            propertyInfoDataParam.setPoint(String.valueOf(point));
            retCode = propertyInfoDataService.doHanlderAddPoint(propertyInfoDataParam);
        }
        if (retCode == ResultCode.SUCCESS) {
            takeLotteryNotification.setResult(true);
        }
    }

    @Override
    public TakeLotteryReply getReply(TakeLotteryNotification takeLotteryNotification) {
        TakeLotteryReply reply = new TakeLotteryReply();
        reply.setResult(takeLotteryNotification.isResult());
        return reply;
    }

}
