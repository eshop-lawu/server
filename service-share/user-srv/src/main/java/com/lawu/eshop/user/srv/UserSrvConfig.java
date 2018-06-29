package com.lawu.eshop.user.srv;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Leach
 * @date 2017/3/13
 */
@Component
public class UserSrvConfig {

    @Value(value="${image.user_default_headimg}")
    private String user_headimg;

    @Value(value="${image.merchant_default_headimg}")
    private String merchant_headimg;

    @Value(value = "${default_headimg}")
    private String defaultHeadimg;

    @Value(value = "${gt_host}")
    private String gtHost;

    @Value(value = "${gt_merchant_app_id}")
    private String gtMerchantAppId;

    @Value(value = "${gt_merchant_app_key}")
    private String gtMerchantAppKey;

    @Value(value = "${gt_merchant_master_secret}")
    private String gtMerchantMasterSecret;

    @Value(value = "${gt_member_app_id}")
    private String gtMemberAppId;

    @Value(value = "${gt_member_app_key}")
    private String gtMemberAppKey;

    @Value(value = "${gt_member_master_secret}")
    private String gtMemberMasterSecret;

    @Value(value = "${rongyun_app_key}")
    private String rongYunAppKey;

    @Value(value = "${rongyun_app_secret}")
    private String rongYunAppSecret;

    public String getUser_headimg() {
        return user_headimg;
    }

    public String getMerchant_headimg() {
        return merchant_headimg;
    }

    public String getDefaultHeadimg() {
        return defaultHeadimg;
    }

    public String getGtHost() {
        return gtHost;
    }

    public String getGtMerchantAppId() {
        return gtMerchantAppId;
    }

    public String getGtMerchantAppKey() {
        return gtMerchantAppKey;
    }

    public String getGtMerchantMasterSecret() {
        return gtMerchantMasterSecret;
    }

    public String getGtMemberAppId() {
        return gtMemberAppId;
    }

    public String getGtMemberAppKey() {
        return gtMemberAppKey;
    }

    public String getGtMemberMasterSecret() {
        return gtMemberMasterSecret;
    }

    public String getRongYunAppKey() {
        return rongYunAppKey;
    }

    public String getRongYunAppSecret() {
        return rongYunAppSecret;
    }

}
