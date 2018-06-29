package com.lawu.eshop.activity.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/1/3.
 */
public class HelpRedpacketAttendSendDTO {

    @ApiModelProperty(value = "参与id")
    private Long id;

    @ApiModelProperty(value = "活动id")
    private Integer activityId;

    @ApiModelProperty(value = "用户编号")
    private String userNum;

    @ApiModelProperty(value = "openid")
    private String wxOpenid;

    @ApiModelProperty(value = "红包金额")
    private Integer finalMoney;

    @ApiModelProperty(value = "商户名称")
    private String sendName;

    @ApiModelProperty(value = "祝福语")
    private String wishing;

    @ApiModelProperty(value = "活动名称")
    private String actName;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "红包发放记录id")
    private Long sendRecordId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }

    public Integer getFinalMoney() {
        return finalMoney;
    }

    public void setFinalMoney(Integer finalMoney) {
        this.finalMoney = finalMoney;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getWishing() {
        return wishing;
    }

    public void setWishing(String wishing) {
        this.wishing = wishing;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getSendRecordId() {
        return sendRecordId;
    }

    public void setSendRecordId(Long sendRecordId) {
        this.sendRecordId = sendRecordId;
    }
}
