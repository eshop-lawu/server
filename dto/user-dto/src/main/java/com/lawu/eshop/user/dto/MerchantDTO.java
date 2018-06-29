package com.lawu.eshop.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2017/3/23
 */
@ApiModel
public class MerchantDTO {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "编号")
    private String num;

    @ApiModelProperty(value = "头像")
    private String headimg;

    @ApiModelProperty(value = "等级")
    private Integer level;

    @ApiModelProperty(value = "是否冻结", required = true)
    private Boolean isFreeze;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Boolean getIsFreeze() {
        return isFreeze;
    }

    public void setIsFreeze(Boolean freeze) {
        isFreeze = freeze;
    }
}
