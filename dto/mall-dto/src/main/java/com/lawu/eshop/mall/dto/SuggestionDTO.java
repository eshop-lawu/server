package com.lawu.eshop.mall.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.mall.constants.SuggestionClientType;
import com.lawu.eshop.mall.constants.SuggestionUserType;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 意见反馈BO
 *
 * @author Sunny
 * @date 2017/3/24
 */
public class SuggestionDTO {

    /**
     * 主键
     */
    @ApiModelProperty(value = "意见反馈ID")
    private Integer id;

    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号")
    private String userNum;

    /**
     * 建议内容
     */
    @ApiModelProperty(value = "建议内容")
    private String content;

    /**
     * 用户端类型:1是商家，2是会员
     */
    @ApiModelProperty(value = "MEMBER:用户,MERCHANT:商家")
    private SuggestionUserType userType;

    /**
     * 客户端类型：1是android，2是ios
     */
    @ApiModelProperty(value = "ANDROID,IOS")
    private SuggestionClientType clientType;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtModified;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public SuggestionUserType getUserType() {
        return userType;
    }

    public void setUserType(SuggestionUserType userType) {
        this.userType = userType;
    }

    public SuggestionClientType getClientType() {
        return clientType;
    }

    public void setClientType(SuggestionClientType clientType) {
        this.clientType = clientType;
    }
}
