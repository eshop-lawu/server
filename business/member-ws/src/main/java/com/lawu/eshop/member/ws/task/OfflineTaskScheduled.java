package com.lawu.eshop.member.ws.task;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lawu.eshop.member.ws.channel.ChannelManager;
import com.lawu.eshop.member.ws.channel.OfflineChannel;
import com.lawu.eshop.member.ws.event.EventPublisher;

/**
 * @author Leach
 * @date 2018/3/29
 */
@Component
public class OfflineTaskScheduled {

    @Autowired
    private EventPublisher eventPublisher;

    @Scheduled(fixedDelay = 1, initialDelay=1)
    public void run() {
        Map<String, OfflineChannel> offlineChannels = ChannelManager.getOfflineChannels();
        Set<String> userNums = offlineChannels.keySet();

        for (String userNum : userNums) {
            OfflineChannel offlineChannel = offlineChannels.get(userNum);

            Date offlineTime = offlineChannel.getOfflineTime();

            // 超过3秒不重连则认为离线
            if (System.currentTimeMillis() - offlineTime.getTime() >= 2000) {
                ChannelManager.removeOfflineChannel(userNum);
                eventPublisher.publishOfflineEvent(userNum, offlineChannel.getType(), offlineChannel.isOfflineSelf());
                continue;
            }
        }
    }
}
