package com.lawu.eshop.mall.srv.event;

import java.util.List;

import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.mall.param.PushParam;
import com.lawu.framework.core.event.AsyncEvent;

/**
 * @author zhangyong
 * @date 2018/2/27.
 */
public class PublishBatchMessagesEvent extends AsyncEvent {
    private static final long serialVersionUID = -4555091377168812554L;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public PublishBatchMessagesEvent(Object source) {
        super(source);
    }

    /**
     * 推送标题
     */
    private String title;

    /**
     * 推送内容
     */
    private String content;

    /**
     * 用户类型
     */
    private UserTypeEnum userTypeEnum;

    /**
     * 区域
     */
    private String area;


    /**
     * 推送cid，userNum
     */
    private List<PushParam> params;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserTypeEnum getUserTypeEnum() {
        return userTypeEnum;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setUserTypeEnum(UserTypeEnum userTypeEnum) {
        this.userTypeEnum = userTypeEnum;
    }

    public List<PushParam> getParams() {
        return params;
    }

    public void setParams(List<PushParam> params) {
        this.params = params;
    }
}
