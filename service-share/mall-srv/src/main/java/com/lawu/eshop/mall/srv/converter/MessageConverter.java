package com.lawu.eshop.mall.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.common.constants.MessageTypeEnum;
import com.lawu.eshop.mall.constants.MessageStatusReturnEnum;
import com.lawu.eshop.mall.dto.MessageDTO;
import com.lawu.eshop.mall.dto.OperatorMessageDTO;
import com.lawu.eshop.mall.param.MessageRemarkParam;
import com.lawu.eshop.mall.srv.bo.MessageBO;
import com.lawu.eshop.mall.srv.bo.MessageStatisticsBO;
import com.lawu.eshop.mall.srv.bo.MessageTemplateBO;
import com.lawu.eshop.mall.srv.domain.MessageDO;
import com.lawu.eshop.mall.srv.domain.MessageTemplateDO;
import com.lawu.utils.DateUtil;

/**
 * 站内信息转换类
 * Created by Administrator on 2017/3/29.
 */
public class MessageConverter {

    public static MessageStatisticsBO coverStatisticsBO(MessageDO messageDO) {
        if (messageDO == null) {
            return null;
        }
        MessageStatisticsBO messageStatisticsBO = new MessageStatisticsBO();
        messageStatisticsBO.setContent(messageDO.getContent());
        messageStatisticsBO.setType(messageDO.getType());
        messageStatisticsBO.setGmtCreate(messageDO.getGmtCreate());
        return messageStatisticsBO;
    }

    public static MessageBO coverBO(MessageDO messageDO) {
        if (messageDO == null) {
            return null;
        }
        MessageBO messageBO = new MessageBO();
        messageBO.setContent(messageDO.getContent());
        messageBO.setType(messageDO.getType());
        messageBO.setId(messageDO.getId());
        messageBO.setStatus(messageDO.getStatus());
        messageBO.setTitle(messageDO.getTitle());
        messageBO.setUserNum(messageDO.getUserNum());
        messageBO.setRelateId(messageDO.getRelateId());
        messageBO.setGmtCreate(messageDO.getGmtCreate());
        messageBO.setImgUrl(messageDO.getImgUrl());
        messageBO.setRemark(messageDO.getRemark());
        return messageBO;
    }

    public static List<MessageDTO> coverDTOS(List<MessageBO> messageBOS) {
        if (messageBOS.isEmpty()) {
            return new ArrayList<>();
        }
        List<MessageDTO> messageDTOS = new ArrayList<>();
        for (MessageBO messageBO : messageBOS) {
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setStatusEnum(MessageStatusReturnEnum.getEnum(messageBO.getStatus()));
            messageDTO.setId(messageBO.getId());
            messageDTO.setMessageTypeEnum(MessageTypeEnum.getEnum(messageBO.getType()));
            messageDTO.setContent(messageBO.getContent());
            messageDTO.setTitle(messageBO.getTitle());
            String createTime = DateUtil.getDateFormat(messageBO.getGmtCreate());
            String now = DateUtil.getDateFormat(new Date());
            if(now.equals(createTime)){
                messageDTO.setCreateTime(DateUtil.getTimeFormat(messageBO.getGmtCreate()));
            }else{
                messageDTO.setCreateTime(createTime);
            }
            messageDTO.setGmtCreate(messageBO.getGmtCreate());
            messageDTO.setRelateId(messageBO.getRelateId());
            messageDTO.setImgUrl(messageBO.getImgUrl());
            if (StringUtils.isNotEmpty(messageBO.getRemark())) {
                messageDTO.setRemarkParam(JSONObject.parseObject(messageBO.getRemark(), MessageRemarkParam.class));
            }
            messageDTOS.add(messageDTO);
        }
        return messageDTOS;
    }

    public static MessageTemplateBO coverTemplateBO(MessageTemplateDO messageTemplateDO) {
        if(messageTemplateDO == null){
            return null;
        }
        MessageTemplateBO messageTemplateBO = new MessageTemplateBO();
        messageTemplateBO.setId(messageTemplateDO.getId());
        messageTemplateBO.setContent(messageTemplateDO.getContent());
        messageTemplateBO.setTitle(messageTemplateDO.getTitle());
        messageTemplateBO.setType(messageTemplateDO.getType());
        return messageTemplateBO;
    }

    public static List<OperatorMessageDTO> coverOperatorDTOS(List<MessageBO> records) {
        if(records == null ){
            return new ArrayList<>();
        }
        List<OperatorMessageDTO> list = new ArrayList<>();
        for(MessageBO messageBO : records){
            OperatorMessageDTO messageDTO = new OperatorMessageDTO();
            messageDTO.setUserNum(messageBO.getUserNum());
            messageDTO.setId(messageBO.getId());
            messageDTO.setTitle(messageBO.getTitle());
            messageDTO.setContent(messageBO.getContent());
            messageDTO.setGmtCreate(messageBO.getGmtCreate());
            messageDTO.setMessageType(MessageTypeEnum.getEnum(messageBO.getType()));
            list.add(messageDTO);
        }
        return list;
    }

	public static MessageDTO coverDTO(MessageBO messageBO) {
		 MessageDTO messageDTO = new MessageDTO();
		 if(messageBO==null){
			 return messageDTO;
		 }
         messageDTO.setStatusEnum(MessageStatusReturnEnum.getEnum(messageBO.getStatus()));
         messageDTO.setId(messageBO.getId());
         messageDTO.setMessageTypeEnum(MessageTypeEnum.getEnum(messageBO.getType()));
         messageDTO.setContent(messageBO.getContent());
         messageDTO.setTitle(messageBO.getTitle());
         messageDTO.setGmtCreate(messageBO.getGmtCreate());
		 return messageDTO;
	}
}
