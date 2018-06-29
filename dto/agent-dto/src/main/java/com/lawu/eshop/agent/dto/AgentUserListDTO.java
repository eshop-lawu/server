package com.lawu.eshop.agent.dto;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2017/8/10.
 */
public class AgentUserListDTO {

    private Long id;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "省市区")
    private String regionPath;

    @ApiModelProperty(value = "省市区")
    private String regionName;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
