package com.lawu.eshop.pay.param;

/**
 * <p> alipay.system.oauth.token 换取授权访问令牌 </p>
 *
 */
public class AlipayOauthTakenResponse {

    private String accessToken;

    private String alipayUserId;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAlipayUserId() {
        return alipayUserId;
    }

    public void setAlipayUserId(String alipayUserId) {
        this.alipayUserId = alipayUserId;
    }
}
