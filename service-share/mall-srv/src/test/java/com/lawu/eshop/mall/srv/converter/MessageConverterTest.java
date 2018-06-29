package com.lawu.eshop.mall.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.common.constants.MessageTypeEnum;
import com.lawu.eshop.mall.constants.MessageStatusEnum;
import com.lawu.eshop.mall.dto.MessageDTO;
import com.lawu.eshop.mall.dto.OperatorMessageDTO;
import com.lawu.eshop.mall.srv.bo.MessageBO;
import com.lawu.eshop.mall.srv.bo.MessageStatisticsBO;
import com.lawu.eshop.mall.srv.bo.MessageTemplateBO;
import com.lawu.eshop.mall.srv.domain.MessageDO;
import com.lawu.eshop.mall.srv.domain.MessageTemplateDO;

/**
 * @author zhangyong
 * @date 2017/7/19.
 */
public class MessageConverterTest {

    @Test
    public void coverStatisticsBO() {
        MessageDO messageDO = new MessageDO();
        messageDO.setContent("test");
        messageDO.setType(MessageTypeEnum.MESSAGE_TYPE_PLATFORM_NOTICE.getVal());
        messageDO.setGmtCreate(new Date());
        MessageStatisticsBO statisticsBO = MessageConverter.coverStatisticsBO(messageDO);
        Assert.assertEquals(messageDO.getContent(), statisticsBO.getContent());
    }

    @Test
    public void coverBO() {
        MessageDO messageDO = new MessageDO();
        messageDO.setContent("test");
        messageDO.setType(MessageTypeEnum.MESSAGE_TYPE_PLATFORM_NOTICE.getVal());
        messageDO.setId(1L);
        messageDO.setStatus(MessageStatusEnum.MESSAGE_STATUS_UNREAD.val);
        messageDO.setTitle("title");
        messageDO.setUserNum("123");
        messageDO.setGmtCreate(new Date());
        MessageBO messageBO = MessageConverter.coverBO(messageDO);
        Assert.assertEquals(messageDO.getContent(), messageBO.getContent());
    }

    @Test
    public void coverDTOS() {
        List<MessageBO> messageBOS = new ArrayList<>();
        MessageBO messageBO = new MessageBO();
        messageBO.setContent("test");
        messageBO.setType(MessageTypeEnum.MESSAGE_TYPE_PLATFORM_NOTICE.getVal());
        messageBO.setId(1L);
        messageBO.setStatus(MessageStatusEnum.MESSAGE_STATUS_UNREAD.val);
        messageBO.setTitle("title");
        messageBO.setUserNum("123");
        messageBO.setGmtCreate(new Date());
        messageBOS.add(messageBO);
        List<MessageDTO> messageDTOS = MessageConverter.coverDTOS(messageBOS);
        Assert.assertEquals(messageBOS.get(0).getContent(), messageDTOS.get(0).getContent());
    }

    @Test
    public void coverTemplateBO() {
        MessageTemplateDO messageTemplateDO = new MessageTemplateDO();
        messageTemplateDO.setId(1);
        messageTemplateDO.setContent("TEST");
        messageTemplateDO.setTitle("TITLE");
        messageTemplateDO.setType(MessageTypeEnum.MESSAGE_TYPE_PLATFORM_NOTICE.getVal());
        MessageTemplateBO messageTemplateBO = MessageConverter.coverTemplateBO(messageTemplateDO);
        Assert.assertEquals(messageTemplateDO.getContent(), messageTemplateBO.getContent());
    }

    @Test
    public void coverOperatorDTOS() {
        List<MessageBO> records = new ArrayList<>();
        MessageBO messageBO = new MessageBO();
        messageBO.setContent("test");
        messageBO.setType(MessageTypeEnum.MESSAGE_TYPE_PLATFORM_NOTICE.getVal());
        messageBO.setId(1L);
        messageBO.setStatus(MessageStatusEnum.MESSAGE_STATUS_UNREAD.val);
        messageBO.setTitle("title");
        messageBO.setUserNum("123");
        messageBO.setGmtCreate(new Date());
        records.add(messageBO);
        List<OperatorMessageDTO> list = MessageConverter.coverOperatorDTOS(records);
        Assert.assertEquals(records.get(0).getContent(), list.get(0).getContent());
    }

    @Test
    public void coverDTO() {
        MessageBO messageBO = new MessageBO();
        messageBO.setContent("test");
        messageBO.setType(MessageTypeEnum.MESSAGE_TYPE_PLATFORM_NOTICE.getVal());
        messageBO.setId(1L);
        messageBO.setStatus(MessageStatusEnum.MESSAGE_STATUS_UNREAD.val);
        messageBO.setTitle("title");
        messageBO.setUserNum("123");
        messageBO.setGmtCreate(new Date());

        MessageDTO messageDTO = MessageConverter.coverDTO(messageBO);
        Assert.assertEquals(messageBO.getContent(), messageDTO.getContent());
    }
}
