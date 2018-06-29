package com.lawu.eshop.activity.dto;

import java.math.BigDecimal;

import com.lawu.eshop.activity.param.SaveRedpacketParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 生成红包DTO
 * @author jiangxinjun
 * @createDate 2017年12月29日
 * @updateDate 2017年12月29日
 */
@ApiModel
public class GenerateRedpacketDTO extends SaveRedpacketParam {
    
    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号", required = true)
    private String userNum;

    /**
     * 用户账号
     */
    @ApiModelProperty(value = "用户账号", required = true)
    private String account;

    /**
     * 用户图像
     */
    @ApiModelProperty(value = "用户图像", required = true)
    private String headimg;

    /**
     * 助力人数
     */
    @ApiModelProperty(value = "助力人数", required = true)
    private Integer helpCount;

    /**
     * 原始红包金额
     */
    @ApiModelProperty(value = "原始红包金额", required = true)
    private BigDecimal originalMoney;

    /**
     * 最终红包金额
     */
    @ApiModelProperty(value = "最终红包金额", required = true)
    private BigDecimal finalMoney;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public Integer getHelpCount() {
        return helpCount;
    }

    public void setHelpCount(Integer helpCount) {
        this.helpCount = helpCount;
    }

    public BigDecimal getOriginalMoney() {
        return originalMoney;
    }

    public void setOriginalMoney(BigDecimal originalMoney) {
        this.originalMoney = originalMoney;
    }

    public BigDecimal getFinalMoney() {
        return finalMoney;
    }

    public void setFinalMoney(BigDecimal finalMoney) {
        this.finalMoney = finalMoney;
    }
    
}
