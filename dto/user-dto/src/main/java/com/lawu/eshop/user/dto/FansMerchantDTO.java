package com.lawu.eshop.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author meishuquan
 * @date 2017/4/6.
 */
public class FansMerchantDTO {

    @ApiModelProperty(value = "会员ID")
    private Long memberId;

    @ApiModelProperty(value = "会员编号")
    private String num;

    @ApiModelProperty(value = "会员账号")
    private String account;

    @ApiModelProperty(value = "区域路径")
    private String regionPath;

    @ApiModelProperty(value = "区域名称")
    private String regionName;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像")
    private String headimg;

    @ApiModelProperty(value = "等级")
    private Integer level;

    @ApiModelProperty(value = "成为粉丝时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gmtCreate;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRegionPath() {
        return regionPath;
    }

    public void setRegionPath(String regionPath) {
        this.regionPath = regionPath;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
