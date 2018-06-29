package com.lawu.eshop.user.param;

import io.swagger.annotations.ApiParam;


/**
 *
 *
 * Created by zhangyong on 2017/3/23.
 */
public class MerchantProfileParam {

    @ApiParam(name="websiteUrl", value = "官网链接")
    private String websiteUrl;

    @ApiParam(name="taobaoUrl", value = "淘宝链接")
    private String taobaoUrl;

    @ApiParam(name="tmallUrl", value = "天猫链接")
    private String tmallUrl;

    @ApiParam(name="jdUrl", value = "京东链接")
    private String jdUrl;


    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getTaobaoUrl() {
        return taobaoUrl;
    }

    public void setTaobaoUrl(String taobaoUrl) {
        this.taobaoUrl = taobaoUrl;
    }

    public String getTmallUrl() {
        return tmallUrl;
    }

    public void setTmallUrl(String tmallUrl) {
        this.tmallUrl = tmallUrl;
    }

    public String getJdUrl() {
        return jdUrl;
    }

    public void setJdUrl(String jdUrl) {
        this.jdUrl = jdUrl;
    }

}
