package com.lawu.eshop.merchant.api.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lawu.eshop.merchant.api.service.UserLoginLogService;
import com.lawu.eshop.user.param.UserLoginLogParam;
import com.lawu.framework.core.event.AsyncEventHandle;

/**
 * @author zhangyong
 * @date 2017/7/4.
 */
@Component
public class LoginEventHandle implements AsyncEventHandle<LoginEvent> {

    @Autowired
    private UserLoginLogService userLoginLogService;

    @Override
    public void execute(LoginEvent event) {
        UserLoginLogParam loginLogParam = new UserLoginLogParam();
        loginLogParam.setUserNum(event.getUserNum());
        loginLogParam.setAccount(event.getAccount());
        loginLogParam.setUserType(event.getUserType());
        loginLogParam.setImei(event.getImei());
        loginLogParam.setPlatform(event.getPlatform());
        loginLogParam.setPlatformVer(event.getPlatformVer());
        loginLogParam.setAppVer(event.getAppVer());
        loginLogParam.setCityId(event.getCityId());
        loginLogParam.setChannel(event.getChannel());
        loginLogParam.setIpAddr(event.getIpAddr());
        userLoginLogService.saveLoginLog(loginLogParam);
    }
}
