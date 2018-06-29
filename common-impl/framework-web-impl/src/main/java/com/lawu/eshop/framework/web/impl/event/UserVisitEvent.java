package com.lawu.eshop.framework.web.impl.event;

import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.framework.core.event.AsyncEvent;

/**
 * @author Leach
 * @date 2017/7/2
 */
public class UserVisitEvent extends AsyncEvent {

    private String userNum;

    private UserTypeEnum userType;

    private Long userId;

    public UserVisitEvent(Object source, String userNum, UserTypeEnum userType,Long userId) {
        super(source);
        this.userNum = userNum;
        this.userType = userType;
        this.userId = userId;
    }

    public String getUserNum() {
        return userNum;
    }

    public UserTypeEnum getUserType() {
        return userType;
    }

    public Long getUserId() {
        return userId;
    }
}
