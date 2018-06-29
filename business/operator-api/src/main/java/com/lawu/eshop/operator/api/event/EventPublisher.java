package com.lawu.eshop.operator.api.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.lawu.eshop.operator.param.LogEventParam;

/**
 * @author zhangrc
 * @date 2017/12/27
 */
@Component
public class EventPublisher{

    @Autowired
    ApplicationContext applicationContext;

    public void publishLogEvent(LogEventParam param) {
       
        applicationContext.publishEvent(new LogEvent(this,  param));
    }

	

}
