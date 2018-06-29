package com.lawu.eshop.activity.param;


import com.lawu.framework.core.page.AbstractPageParam;

/**
 * @author zhangyong
 * @date 2018/3/1.
 */
public class SignAbnormalParam  extends AbstractPageParam {

    private Long attendId;

    public Long getAttendId() {
        return attendId;
    }

    public void setAttendId(Long attendId) {
        this.attendId = attendId;
    }
}
