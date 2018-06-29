package com.lawu.eshop.agent.param;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2017/8/9.
 */
public class AgentUserParam {

    @ApiModelProperty(value = "账号",required = true)
    private String account ;

    @ApiModelProperty(value = "密码",required = true)
    private String pwd;

    @ApiModelProperty(value = "手机号",required = true)
    private String mobile;

    @ApiModelProperty(value = "姓名",required = true)
    private String name;

    @ApiModelProperty(value = "省市区",required = true)
    private String regionPath;

    @ApiModelProperty(value = "省市区",required = true)
    private String regionName;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegionPath() {
        return regionPath;
    }

    public void setRegionPath(String regionPath) {
        this.regionPath = regionPath;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}
