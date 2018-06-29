package com.lawu.eshop.activity.srv.event;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.lawu.eshop.activity.param.GenerateNormalRedpacketParam;

/**
 * 
 * @author jiangxinjun
 * @createDate 2018年1月4日
 * @updateDate 2018年1月4日
 */
@Component
public class EventPublisher {

    @Autowired
    private ApplicationContext applicationContext;

    public void publishGenerateNormalRedpacketEvent(Integer activityId, GenerateNormalRedpacketParam param, BigDecimal redpacketAmount) {
        GenerateNormalRedpacketEvent event = new GenerateNormalRedpacketEvent(this);
        event.setTotalAutoAmount(param.getTotalAutoAmount());
        event.setActivityId(activityId);
        event.setRedpacketAmount(redpacketAmount);
        applicationContext.publishEvent(event);
    }
}
