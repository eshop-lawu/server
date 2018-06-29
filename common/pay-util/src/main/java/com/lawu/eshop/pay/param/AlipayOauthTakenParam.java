package com.lawu.eshop.pay.param;

/**
 * <p> alipay.system.oauth.token 换取授权访问令牌 </p>
 *
 * @author yangqh
 * @date 2018/5/7
 */
public class AlipayOauthTakenParam {

    private String code;    //授权码，用户对应用授权后得到。

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
