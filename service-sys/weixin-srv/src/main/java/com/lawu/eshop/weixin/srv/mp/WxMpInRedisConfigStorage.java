package com.lawu.eshop.weixin.srv.mp;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.StringRedisTemplate;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;

/**
 * @author Leach
 * @date 2018/1/19
 */
public class WxMpInRedisConfigStorage extends WxMpInMemoryConfigStorage {

    private final static String ACCESS_TOKEN_KEY = "wechat_access_token_";

    private final static String JSAPI_TICKET_KEY = "wechat_jsapi_ticket_";

    private final static String CARDAPI_TICKET_KEY = "wechat_cardapi_ticket_";

    private StringRedisTemplate stringRedisTemplate;

    private String accessTokenKey;

    private String jsapiTicketKey;

    private String cardapiTicketKey;

    public WxMpInRedisConfigStorage(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 每个公众号生成独有的存储key
     *
     * @param appId
     */
    @Override
    public void setAppId(String appId) {
        super.setAppId(appId);
        this.accessTokenKey = ACCESS_TOKEN_KEY.concat(appId);
        this.jsapiTicketKey = JSAPI_TICKET_KEY.concat(appId);
        this.cardapiTicketKey = CARDAPI_TICKET_KEY.concat(appId);
    }

    @Override
    public String getAccessToken() {
        return stringRedisTemplate.opsForValue().get(accessTokenKey);
    }

    @Override
    public boolean isAccessTokenExpired() {
        return stringRedisTemplate.getExpire(accessTokenKey, TimeUnit.SECONDS) < 2;
    }

    @Override
    public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
        stringRedisTemplate.boundValueOps(accessTokenKey).set(accessToken, expiresInSeconds - 200, TimeUnit.SECONDS);
    }

    @Override
    public void expireAccessToken() {
        stringRedisTemplate.expire(accessTokenKey, 0, TimeUnit.SECONDS);
    }

    @Override
    public String getJsapiTicket() {
        return stringRedisTemplate.opsForValue().get(jsapiTicketKey);
    }

    @Override
    public boolean isJsapiTicketExpired() {
        return stringRedisTemplate.getExpire(jsapiTicketKey, TimeUnit.SECONDS) < 2;
    }

    @Override
    public synchronized void updateJsapiTicket(String jsapiTicket, int expiresInSeconds) {
        stringRedisTemplate.boundValueOps(jsapiTicketKey).set(jsapiTicket, expiresInSeconds - 200, TimeUnit.SECONDS);
    }

    @Override
    public void expireJsapiTicket() {
        stringRedisTemplate.expire(jsapiTicketKey, 0, TimeUnit.SECONDS);
    }

    @Override
    public String getCardApiTicket() {
        return stringRedisTemplate.opsForValue().get(cardapiTicketKey);
    }

    @Override
    public boolean isCardApiTicketExpired() {
        return stringRedisTemplate.getExpire(cardapiTicketKey, TimeUnit.SECONDS) < 2;
    }

    @Override
    public synchronized void updateCardApiTicket(String cardApiTicket, int expiresInSeconds) {
        stringRedisTemplate.boundValueOps(cardapiTicketKey).set(cardApiTicket, expiresInSeconds - 200, TimeUnit.SECONDS);
    }

    @Override
    public void expireCardApiTicket() {
        stringRedisTemplate.expire(cardapiTicketKey, 0, TimeUnit.SECONDS);
    }
}
