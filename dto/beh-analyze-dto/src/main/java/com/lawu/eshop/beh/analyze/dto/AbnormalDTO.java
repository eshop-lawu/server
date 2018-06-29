package com.lawu.eshop.beh.analyze.dto;

import java.util.Date;

import com.lawu.eshop.beh.analyze.constants.ProcessEnum;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2018/1/17.
 */
public class AbnormalDTO {

    private Long id;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "编号")
    private String userNum;

    @ApiModelProperty(value = "类型")
    private UserTypeEnum type;

    @ApiModelProperty(value = "是否短高频")
    private Boolean isShortHf;

    @ApiModelProperty(value = "是否长高频")
    private Boolean isLongHf;

    @ApiModelProperty(value = "是否多次短高频")
    private Boolean isManyShortHf;

    @ApiModelProperty(value = "是否多次长高频")
    private Boolean isManyLongHf;

    @ApiModelProperty(value = "是否一天注册下线高频")
    private Boolean isOneDayHf;

    @ApiModelProperty(value = "是否凌晨注册高频")
    private Boolean isEarlyHf;

    @ApiModelProperty(value = "是否相同ip高频")
    private Boolean isIpHf;

    @ApiModelProperty(value = "是否已处理")
    private ProcessEnum process;
    
    @ApiModelProperty(value = "时间")
    private Date gmtCreate;

    @ApiModelProperty(value = "是否活动注册异常")
    private Boolean isActivityAbnormal;

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

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public UserTypeEnum getType() {
        return type;
    }

    public void setType(UserTypeEnum type) {
        this.type = type;
    }

    public Boolean getShortHf() {
        return isShortHf;
    }

    public void setShortHf(Boolean shortHf) {
        isShortHf = shortHf;
    }

    public Boolean getLongHf() {
        return isLongHf;
    }

    public void setLongHf(Boolean longHf) {
        isLongHf = longHf;
    }

    public Boolean getManyShortHf() {
        return isManyShortHf;
    }

    public void setManyShortHf(Boolean manyShortHf) {
        isManyShortHf = manyShortHf;
    }

    public Boolean getManyLongHf() {
        return isManyLongHf;
    }

    public void setManyLongHf(Boolean manyLongHf) {
        isManyLongHf = manyLongHf;
    }

    public Boolean getOneDayHf() {
        return isOneDayHf;
    }

    public void setOneDayHf(Boolean oneDayHf) {
        isOneDayHf = oneDayHf;
    }

    public Boolean getEarlyHf() {
        return isEarlyHf;
    }

    public void setEarlyHf(Boolean earlyHf) {
        isEarlyHf = earlyHf;
    }

    public Boolean getIpHf() {
        return isIpHf;
    }

    public void setIpHf(Boolean ipHf) {
        isIpHf = ipHf;
    }

    public ProcessEnum getProcess() {
        return process;
    }

    public void setProcess(ProcessEnum process) {
        this.process = process;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Boolean getIsActivityAbnormal() {
        return isActivityAbnormal;
    }

    public void setIsActivityAbnormal(Boolean activityAbnormal) {
        isActivityAbnormal = activityAbnormal;
    }
}
