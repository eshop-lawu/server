package com.lawu.eshop.member.ws.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.game.constants.ConnType;

/**
 * @author Leach
 * @date 2018/3/29
 */
@Component
public class EventPublisher {

    @Autowired
    ApplicationContext applicationContext;

    public void publishOfflineEvent(String userNum, ConnType type, boolean offlineSelf) {
        applicationContext.publishEvent(new OfflineEvent(this, userNum, type, offlineSelf));
    }

    public void publishRichPowerTaskEvent(String num, GameTypeEnum gameTypeEnum) {
        applicationContext.publishEvent(new RichPowerTaskEvent(this, num, gameTypeEnum));
    }
}
