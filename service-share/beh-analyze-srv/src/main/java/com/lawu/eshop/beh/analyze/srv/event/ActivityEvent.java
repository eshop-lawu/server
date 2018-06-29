package com.lawu.eshop.beh.analyze.srv.event;

import com.lawu.eshop.beh.analyze.param.AbnormalJobAddParam;
import com.lawu.framework.core.event.AsyncEvent;

/**
 * @author zhangyong
 * @date 2018/3/2.
 */
public class ActivityEvent extends AsyncEvent {

    private AbnormalJobAddParam addParam;
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public ActivityEvent(Object source, AbnormalJobAddParam addParam) {
        super(source);
        this.addParam = addParam;
    }

    public AbnormalJobAddParam getAddParam() {
        return addParam;
    }
}
