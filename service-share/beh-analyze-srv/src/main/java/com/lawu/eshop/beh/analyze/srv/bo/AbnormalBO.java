package com.lawu.eshop.beh.analyze.srv.bo;

import java.util.Date;

import com.lawu.eshop.beh.analyze.constants.ProcessEnum;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;

/**
 * @author zhangyong
 * @date 2018/1/17.
 */
public class AbnormalBO {
    private Long id;

    private String account;

    private String userNum;

    private UserTypeEnum type;

    private Boolean isShortHf;

    private Boolean isLongHf;

    private Boolean isManyShortHf;

    private Boolean isManyLongHf;

    private Boolean isOneDayHf;

    private Boolean isEarlyHf;

    private Boolean isIpHf;

    private Boolean isActivityAbnormal;

    private ProcessEnum process;
    
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

    public Boolean getIsActivityAbnormal() {
        return isActivityAbnormal;
    }

    public void setIsActivityAbnormal(Boolean activityAbnormal) {
        isActivityAbnormal = activityAbnormal;
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
}
