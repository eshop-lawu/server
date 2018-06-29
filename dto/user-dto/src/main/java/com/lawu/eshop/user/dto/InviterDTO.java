package com.lawu.eshop.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2017/3/23
 */
@ApiModel
public class InviterDTO {

    @ApiModelProperty(value = "邀请人ID")
    private Long inviterId;

    @ApiModelProperty(value = "邀请人编号")
    private String userNum;

    @ApiModelProperty(value = "邀请人名字")
    private String inviterName;
    
    /**
     * 是否冻结
     */
    @ApiModelProperty(value = "是否冻结", required = true)
    private Boolean freeze;

    public Long getInviterId() {
        return inviterId;
    }

    public void setInviterId(Long inviterId) {
        this.inviterId = inviterId;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getInviterName() {
        return inviterName;
    }

    public void setInviterName(String inviterName) {
        this.inviterName = inviterName;
    }

    public Boolean getFreeze() {
        return freeze;
    }

    public void setFreeze(Boolean freeze) {
        this.freeze = freeze;
    }
    
}
