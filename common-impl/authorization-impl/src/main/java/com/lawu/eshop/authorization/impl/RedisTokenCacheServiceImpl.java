package com.lawu.eshop.authorization.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawu.authorization.constants.TokenClearType;
import com.lawu.authorization.manager.TokenCacheService;
import com.lawu.framework.web.Result;

/**
 * @author Leach
 * @date 2017/10/11
 */
public class RedisTokenCacheServiceImpl implements TokenCacheService {

    @Autowired
    private LoginTokenService loginTokenService;

    /**
     * 用户登录类型
     */
    private UserLoginType userLoginType;

    /**
     * 缓存超时时间
     */
    private Integer expireSeconds = 86400;

    public void setUserLoginType(UserLoginType userLoginType) {
        this.userLoginType = userLoginType;
    }

    public void setExpireSeconds(Integer expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    public UserLoginType getUserLoginType() {
        return userLoginType;
    }

    public Integer getExpireSeconds() {
        return expireSeconds;
    }

    @Override
    public void setTokenOneToOne(String account, String token, Integer expireSeconds) {
        loginTokenService.setTokenOneToOne(userLoginType.getVal(), account, token, expireSeconds == null ? this.expireSeconds : expireSeconds, TokenClearType.LOGOUT_PASSIVE.getVal());
    }

    @Override
    public void setTokenOneToMany(String account, String token, Integer expireSeconds) {

        loginTokenService.setTokenOneToMany(userLoginType.getVal(), account, token, expireSeconds == null ? this.expireSeconds : expireSeconds);
    }

    @Override
    public String getAccount(String token, Boolean flushExpireAfterOperation, Integer expireSeconds, Boolean singleTokenWithUser) {
        Result<String> result = loginTokenService.getAccount(userLoginType.getVal(), token, flushExpireAfterOperation, expireSeconds == null ? this.expireSeconds : expireSeconds, singleTokenWithUser);
        return result.getModel();
    }

    @Override
    public void delRelationshipByAccount(String account, Integer expireSeconds, TokenClearType tokenClearType) {
        loginTokenService.delRelationshipByAccount(userLoginType.getVal(), account, expireSeconds == null ? this.expireSeconds : expireSeconds, tokenClearType == null ? null : tokenClearType.getVal());
    }

    @Override
    public void delRelationshipByToken(String token, Boolean singleTokenWithUser, Integer expireSeconds) {
        loginTokenService.delRelationshipByToken(userLoginType.getVal(), token, singleTokenWithUser, expireSeconds == null ? this.expireSeconds : expireSeconds, null);
    }

    @Override
    public TokenClearType getTokenClearType(String token) {
        Result<Integer> result = loginTokenService.getTokenClearType(userLoginType.getVal(), token);
        Integer tokenClearType = result.getModel();
        return tokenClearType == null ? null : TokenClearType.getEnum(tokenClearType);
    }
}
