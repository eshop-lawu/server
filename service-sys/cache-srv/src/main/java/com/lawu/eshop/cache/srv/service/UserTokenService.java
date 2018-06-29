package com.lawu.eshop.cache.srv.service;

/**
 * @author Leach
 * @date 2017/3/20
 */
@Deprecated
public interface UserTokenService {

    void setMemberTokenOneToOne(String account, String token, Integer expireSeconds);

    void setMemberTokenOneToMany(String account, String token, Integer expireSeconds);

    String getMemberAccount(String token, Boolean flushExpireAfterOperation, Integer expireSeconds, Boolean singleTokenWithUser);

    void delMemberRelationshipByAccount(String account);

    void delMemberRelationshipByToken(String token, Boolean singleTokenWithUser);


    void setMerchantTokenOneToOne(String account, String token, Integer expireSeconds);

    void setMerchantTokenOneToMany(String account, String token, Integer expireSeconds);

    String getMerchantAccount(String token, Boolean flushExpireAfterOperation, Integer expireSeconds, Boolean singleTokenWithUser);

    void delMerchantRelationshipByAccount(String account);

    void delMerchantRelationshipByToken(String token, Boolean singleTokenWithUser);
}
