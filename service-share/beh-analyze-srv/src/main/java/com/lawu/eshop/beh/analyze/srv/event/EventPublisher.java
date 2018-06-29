package com.lawu.eshop.beh.analyze.srv.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.lawu.eshop.beh.analyze.param.AbnormalJobAddParam;

/**
 * @author zhangyong
 * @date 2018/3/2.
 */
@Component
public class EventPublisher {

    @Autowired
    ApplicationContext applicationContext;

    public void publishActivityEvent(AbnormalJobAddParam param) {

        applicationContext.publishEvent(new ActivityEvent(this, param));
    }
}
