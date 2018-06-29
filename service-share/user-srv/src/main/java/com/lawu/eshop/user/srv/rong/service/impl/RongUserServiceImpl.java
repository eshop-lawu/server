package com.lawu.eshop.user.srv.rong.service.impl;

import com.lawu.eshop.user.srv.UserSrvConfig;
import com.lawu.eshop.user.srv.rong.RongCloud;
import com.lawu.eshop.user.srv.rong.models.CheckOnlineResult;
import com.lawu.eshop.user.srv.rong.models.CodeSuccessResult;
import com.lawu.eshop.user.srv.rong.models.HistoryMessageResult;
import com.lawu.eshop.user.srv.rong.models.TokenResult;
import com.lawu.eshop.user.srv.rong.service.RongUserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @author zhangyong
 * @date 2017/4/14.
 */
@Service
public class RongUserServiceImpl implements RongUserService, InitializingBean, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private RongCloud rongCloud;

    @Override
    public TokenResult getRongToken(String userId, String name, String portraitUri){
        // 获取 Token 方法
        TokenResult userGetTokenResult = rongCloud.user.getToken(userId, name, portraitUri);
        return userGetTokenResult;
    }

    @Override
    public CodeSuccessResult refreshUserInfo(String userId, String name, String portraitUri){
        CodeSuccessResult userRefreshResult = rongCloud.user.refresh(userId, name, portraitUri);
        return userRefreshResult;
    }

    @Override
    public CheckOnlineResult checkOnline(String userId){
        CheckOnlineResult userCheckOnlineResult = rongCloud.user.checkOnline(userId);
        return userCheckOnlineResult;
    }

    @Override
    public HistoryMessageResult getHistory(String date) {
        HistoryMessageResult messageResult = rongCloud.message.getHistory(date);
        return  messageResult;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        UserSrvConfig userSrvConfig = applicationContext.getBean(UserSrvConfig.class);
        rongCloud = RongCloud.getInstance(userSrvConfig.getRongYunAppKey(), userSrvConfig.getRongYunAppSecret());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;
    }
}
