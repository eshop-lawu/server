package com.lawu.eshop.beh.analyze.srv.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lawu.eshop.beh.analyze.srv.service.AbnormalRecordService;
import com.lawu.framework.core.event.AsyncEventHandle;

/**
 * @author zhangyong
 * @date 2018/3/2.
 */
@Component
public class ActivityEventHandle implements AsyncEventHandle<ActivityEvent> {

    @Autowired
    private AbnormalRecordService abnormalRecordService;

    @Override
    public void execute(ActivityEvent event) {
        abnormalRecordService.addActiveAbnormalInfo(event.getAddParam());
    }
}
