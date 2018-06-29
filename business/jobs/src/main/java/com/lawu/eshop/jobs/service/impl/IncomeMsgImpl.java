package com.lawu.eshop.jobs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.common.constants.MessageTypeEnum;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.jobs.service.IncomeMsgService;
import com.lawu.eshop.jobs.service.MessageService;
import com.lawu.eshop.mall.param.MessageInfoParam;
import com.lawu.eshop.mall.param.MessageTempParam;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.constants.MerchantTransactionTypeEnum;
import com.lawu.eshop.property.dto.IncomeMsgDTO;

/**
 * @author yangqh
 * @date 2017/7/3.
 */
@Service
public class IncomeMsgImpl implements IncomeMsgService {

    @Autowired
    private MessageService messageService;

    @Override
    public void execute(List<IncomeMsgDTO> list) {

        for(IncomeMsgDTO dto : list){
            MessageInfoParam messageInfoParam = new MessageInfoParam();
            messageInfoParam.setRelateId(0L);
            MessageTempParam messageTempParam = new MessageTempParam();
            if(dto.getUserNum().startsWith(UserTypeEnum.MERCHANT.name())){
                messageTempParam.setUserName("E店商家");
            } else if(dto.getUserNum().startsWith(UserTypeEnum.MEMBER.name())){
                messageTempParam.setUserName("E店用户");
            }
            if(dto.getMsgType() == 1){
                messageTempParam.setEarningAmount(dto.getMoney());
                if(MerchantTransactionTypeEnum.SALES_COMMISSION.getValue().equals(dto.getType()) ||
                        MemberTransactionTypeEnum.SALES_COMMISSION.getValue().equals(dto.getType())){
                    messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_RECOMMEND_MEMBER_BALANCE);
                }
                if(MerchantTransactionTypeEnum.LOWER_INCOME.getValue().equals(dto.getType()) ||
                        MemberTransactionTypeEnum.LOWER_INCOME.getValue().equals(dto.getType())){
                    messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_RECOMMEND_MEMBER_BALANCE);
                }
                if(MerchantTransactionTypeEnum.VOLUME_COMMISSION.getValue().equals(dto.getType()) ||
                        MemberTransactionTypeEnum.VOLUME_COMMISSION.getValue().equals(dto.getType())){
                    messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_RECOMMEND_MERCHANT_BALANCE);
                }
            } else if(dto.getMsgType() == 2){
                messageTempParam.setEarningPoint(dto.getMoney());
                if(MerchantTransactionTypeEnum.SALES_COMMISSION.getValue().equals(dto.getType()) ||
                        MemberTransactionTypeEnum.SALES_COMMISSION.getValue().equals(dto.getType())){
                    messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_RECOMMEND_MEMBER_POINT);
                }
                if(MerchantTransactionTypeEnum.LOWER_INCOME.getValue().equals(dto.getType()) ||
                        MemberTransactionTypeEnum.LOWER_INCOME.getValue().equals(dto.getType())){
                    messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_RECOMMEND_MEMBER_POINT);
                }
                if(MerchantTransactionTypeEnum.VOLUME_COMMISSION.getValue().equals(dto.getType()) ||
                        MemberTransactionTypeEnum.VOLUME_COMMISSION.getValue().equals(dto.getType())){
                    messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_RECOMMEND_MERCHANT_POINT);
                }
            }
            messageInfoParam.setMessageParam(messageTempParam);
            messageService.saveMessage(dto.getUserNum(), messageInfoParam);
        }
    }
}
