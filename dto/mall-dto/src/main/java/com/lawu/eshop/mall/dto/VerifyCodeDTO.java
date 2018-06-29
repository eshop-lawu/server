package com.lawu.eshop.mall.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author meishuquan
 * @date 2017/3/28.
 */
public class VerifyCodeDTO {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "验证码")
    private String code;

    @ApiModelProperty(value = "发送时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
