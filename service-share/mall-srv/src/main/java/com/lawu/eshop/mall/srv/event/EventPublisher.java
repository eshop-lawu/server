package com.lawu.eshop.mall.srv.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.lawu.eshop.mall.param.OperatorMessageParam;

/**
 * @author zhangyong
 * @date 2018/2/27.
 */
@Component
public class EventPublisher {
    @Autowired
    private ApplicationContext applicationContext;

    public void publishBatchAddMessages(OperatorMessageParam param) {
        PublishBatchMessagesEvent event = new PublishBatchMessagesEvent(this);
        event.setContent(param.getContent());
        event.setTitle(param.getTitle());
        event.setUserTypeEnum(param.getUserTypeEnum());
        event.setParams(param.getParams());
        event.setArea(param.getArea());
        applicationContext.publishEvent(event);
    }
}
