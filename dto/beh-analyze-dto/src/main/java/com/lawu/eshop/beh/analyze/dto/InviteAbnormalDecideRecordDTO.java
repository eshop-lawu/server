package com.lawu.eshop.beh.analyze.dto;

import com.lawu.eshop.beh.analyze.constants.ProcessEnum;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 注册异常判定记录DTO
 * @author jiangxinjun
 * @createDate 2018年1月18日
 * @updateDate 2018年1月18日
 */
@ApiModel
public class InviteAbnormalDecideRecordDTO {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", required = true)
    private Long id;

    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号", required = true)
    private String userNum;

    /**
     * 账号
     */
    @ApiModelProperty(value = "账户", required = true)
    private String account;

    /**
     * 用户类型
     */
    @ApiModelProperty(value = "用户类型", required = true)
    private UserTypeEnum type;

    /**
     * 是否短高频
     */
    @ApiModelProperty(value = "是否短高频", required = true)
    private Boolean isShortHf;

    /**
     * 是否长高频
     */
    @ApiModelProperty(value = "是否长高频", required = true)
    private Boolean isLongHf;

    /**
     * 是否多次短高频
     */
    @ApiModelProperty(value = "是否多次短高频", required = true)
    private Boolean isManyShortHf;

    /**
     * 是否多次短高频
     */
    @ApiModelProperty(value = "是否多次短高频", required = true)
    private Boolean isManyLongHf;

    /**
     * 是否一天注册下线高频
     */
    @ApiModelProperty(value = "是否一天注册下线高频", required = true)
    private Boolean isOneDayHf;

    /**
     * 是否凌晨注册高频
     */
    @ApiModelProperty(value = "是否凌晨注册高频", required = true)
    private Boolean isEarlyHf;

    /**
     * 是否相同ip高频
     */
    @ApiModelProperty(value = "是否相同ip高频", required = true)
    private Boolean isIpHf;

    /**
     * 是否异常
     */
    @ApiModelProperty(value = "是否异常", required = true)
    private Boolean isAbnormal;

    /**
     * 处理类型
     */
    @ApiModelProperty(value = "处理类型", required = true)
    private ProcessEnum processType;

    /**
     * 是否已通知
     */
    @ApiModelProperty(value = "是否已通知", required = true)
    private Boolean isNoticed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public UserTypeEnum getType() {
        return type;
    }

    public void setType(UserTypeEnum type) {
        this.type = type;
    }

    public Boolean getIsShortHf() {
        return isShortHf;
    }

    public void setIsShortHf(Boolean isShortHf) {
        this.isShortHf = isShortHf;
    }

    public Boolean getIsLongHf() {
        return isLongHf;
    }

    public void setIsLongHf(Boolean isLongHf) {
        this.isLongHf = isLongHf;
    }

    public Boolean getIsManyShortHf() {
        return isManyShortHf;
    }

    public void setIsManyShortHf(Boolean isManyShortHf) {
        this.isManyShortHf = isManyShortHf;
    }

    public Boolean getIsManyLongHf() {
        return isManyLongHf;
    }

    public void setIsManyLongHf(Boolean isManyLongHf) {
        this.isManyLongHf = isManyLongHf;
    }

    public Boolean getIsOneDayHf() {
        return isOneDayHf;
    }

    public void setIsOneDayHf(Boolean isOneDayHf) {
        this.isOneDayHf = isOneDayHf;
    }

    public Boolean getIsEarlyHf() {
        return isEarlyHf;
    }

    public void setIsEarlyHf(Boolean isEarlyHf) {
        this.isEarlyHf = isEarlyHf;
    }

    public Boolean getIsIpHf() {
        return isIpHf;
    }

    public void setIsIpHf(Boolean isIpHf) {
        this.isIpHf = isIpHf;
    }

    public Boolean getIsAbnormal() {
        return isAbnormal;
    }

    public void setIsAbnormal(Boolean isAbnormal) {
        this.isAbnormal = isAbnormal;
    }

    public ProcessEnum getProcessType() {
        return processType;
    }

    public void setProcessType(ProcessEnum processType) {
        this.processType = processType;
    }

    public Boolean getIsNoticed() {
        return isNoticed;
    }

    public void setIsNoticed(Boolean isNoticed) {
        this.isNoticed = isNoticed;
    }
    
}