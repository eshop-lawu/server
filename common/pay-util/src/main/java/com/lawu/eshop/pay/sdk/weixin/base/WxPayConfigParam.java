package com.lawu.eshop.pay.sdk.weixin.base;

import com.lawu.eshop.pay.param.BizTypeEnum;

/**
 * @author meishuquan
 * @date 2017/5/4.
 */
public class WxPayConfigParam {

    private BizTypeEnum bizTypeEnum;

    private String wxpayAppIdMember;

    private String wxpayMchIdMember;

    private String wxpayKey;

    private String wxpayAppId;

    private String wxpayMchId;

    private String wxpayCertLocalPathMember;

    private String wxpayCertPasswordMember;

    private String wxpayCertBasePath;

    private String wxpayRefundApi;

    private String wxpayHttpsRequestClassName;

    private String wxpayKeyApp;

    private String wxpayMmpaymkttransfersApi;

    public String getWxpayAppIdMember() {
        return wxpayAppIdMember;
    }

    public void setWxpayAppIdMember(String wxpayAppIdMember) {
        this.wxpayAppIdMember = wxpayAppIdMember;
    }

    public String getWxpayMchIdMember() {
        return wxpayMchIdMember;
    }

    public void setWxpayMchIdMember(String wxpayMchIdMember) {
        this.wxpayMchIdMember = wxpayMchIdMember;
    }

    public String getWxpayKey() {
        return wxpayKey;
    }

    public void setWxpayKey(String wxpayKey) {
        this.wxpayKey = wxpayKey;
    }

    public String getWxpayAppId() {
        return wxpayAppId;
    }

    public void setWxpayAppId(String wxpayAppId) {
        this.wxpayAppId = wxpayAppId;
    }

    public String getWxpayMchId() {
        return wxpayMchId;
    }

    public void setWxpayMchId(String wxpayMchId) {
        this.wxpayMchId = wxpayMchId;
    }

    public String getWxpayCertLocalPathMember() {
        return wxpayCertLocalPathMember;
    }

    public void setWxpayCertLocalPathMember(String wxpayCertLocalPathMember) {
        this.wxpayCertLocalPathMember = wxpayCertLocalPathMember;
    }

    public String getWxpayCertPasswordMember() {
        return wxpayCertPasswordMember;
    }

    public void setWxpayCertPasswordMember(String wxpayCertPasswordMember) {
        this.wxpayCertPasswordMember = wxpayCertPasswordMember;
    }

    public String getWxpayCertBasePath() {
        return wxpayCertBasePath;
    }

    public void setWxpayCertBasePath(String wxpayCertBasePath) {
        this.wxpayCertBasePath = wxpayCertBasePath;
    }

    public String getWxpayRefundApi() {
        return wxpayRefundApi;
    }

    public void setWxpayRefundApi(String wxpayRefundApi) {
        this.wxpayRefundApi = wxpayRefundApi;
    }

    public String getWxpayHttpsRequestClassName() {
        return wxpayHttpsRequestClassName;
    }

    public void setWxpayHttpsRequestClassName(String wxpayHttpsRequestClassName) {
        this.wxpayHttpsRequestClassName = wxpayHttpsRequestClassName;
    }

    public String getWxpayKeyApp() {
        return wxpayKeyApp;
    }

    public void setWxpayKeyApp(String wxpayKeyApp) {
        this.wxpayKeyApp = wxpayKeyApp;
    }

    public String getWxpayMmpaymkttransfersApi() {
        return wxpayMmpaymkttransfersApi;
    }

    public void setWxpayMmpaymkttransfersApi(String wxpayMmpaymkttransfersApi) {
        this.wxpayMmpaymkttransfersApi = wxpayMmpaymkttransfersApi;
    }

    public BizTypeEnum getBizTypeEnum() {
        return bizTypeEnum;
    }

    public void setBizTypeEnum(BizTypeEnum bizTypeEnum) {
        this.bizTypeEnum = bizTypeEnum;
    }
}
