package com.lawu.eshop.mall.srv.event;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lawu.eshop.common.constants.MessageTypeEnum;
import com.lawu.eshop.mall.param.PushParam;
import com.lawu.eshop.mall.srv.service.MessageService;
import com.lawu.eshop.mq.dto.user.MessagePushInfo;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.framework.core.event.AsyncEventHandle;
import com.lawu.mq.message.MessageProducerService;

/**
 * @author zhangyong
 * @date 2018/2/27.
 */
@Component
public class PublishBatchMessagesEventHandle implements AsyncEventHandle<PublishBatchMessagesEvent> {

    private static final Logger logger = LoggerFactory.getLogger(PublishBatchMessagesEventHandle.class);
    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageProducerService messageProducerService;

    @Override
    public void execute(PublishBatchMessagesEvent event) {
        if (event.getParams().isEmpty()) {
            return;
        }
        List<PushParam> pushParams = new ArrayList<>();
        for (int i = 0; i < event.getParams().size(); i++) {
            pushParams.add(event.getParams().get(i));
            if (pushParams.size() >= 100) {
                //大于100条--批量处理100循环插入
                try {
                    messageService.batchMessages(event.getTitle(), event.getContent(), pushParams);
                } catch (Exception e) {
                    logger.error("批量保存失败", e);
                }
                //清空list
                pushParams.clear();
            }
        }
        //处理剩余不足100条数据循环插入
        if (!pushParams.isEmpty()) {
            try {
                messageService.batchMessages(event.getTitle(), event.getContent(), pushParams);
            } catch (Exception e) {
                logger.error("批量保存失败", e);
            }
        }
        MessagePushInfo pushInfo = new MessagePushInfo();
        pushInfo.setTitle(event.getTitle());
        pushInfo.setContent(event.getContent());
        pushInfo.setUserType(event.getUserTypeEnum().getValue());
        pushInfo.setMessageType(MessageTypeEnum.MESSAGE_TYPE_PLATFORM_NOTICE.getVal());
        pushInfo.setArea(event.getArea());
        //推送全部
        if ("all".equals(event.getArea())) {
            messageProducerService.sendMessage(MqConstant.TOPIC_MALL_SRV, MqConstant.TAG_GTPUSHALL, pushInfo);
        } else {
            messageProducerService.sendMessage(MqConstant.TOPIC_MALL_SRV, MqConstant.TAG_GTPUSH_AREA, pushInfo);
        }
    }
}
