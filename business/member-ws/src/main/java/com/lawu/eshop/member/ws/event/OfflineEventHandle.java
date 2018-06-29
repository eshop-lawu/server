package com.lawu.eshop.member.ws.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lawu.eshop.member.ws.channel.ChannelManager;
import com.lawu.eshop.member.ws.processor.GameRevProcessorDispatch;
import com.lawu.framework.core.event.AsyncEventHandle;

/**
 * @author Leach
 * @date 2018/3/29
 */
@Component
public class OfflineEventHandle implements AsyncEventHandle<OfflineEvent> {

    private Logger logger = LoggerFactory.getLogger(OfflineEventHandle.class);

    @Autowired
    private GameRevProcessorDispatch gameRevProcessorDispatch;

    @Override
    public void execute(OfflineEvent offlineEvent) {
        if (offlineEvent.getConnType() != null && !offlineEvent.isOfflineSelf()) {
            // 关闭前处理
            try {
                gameRevProcessorDispatch.offline(offlineEvent.getConnType(), offlineEvent.getUserNum());
                ChannelManager.removeChannel(offlineEvent.getUserNum());
            } catch (Exception e) {
                logger.error("Fail to offline before channel inactive", e);
            }
        }
    }
}
