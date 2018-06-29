package com.lawu.eshop.beh.analyze.param;

import com.lawu.eshop.beh.analyze.constants.ProcessEnum;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2018/1/17.
 */
@ApiModel
public class AbnormalParam extends AbstractPageParam{

    /**
     * 用户账号
     */
    @ApiModelProperty(value = "用户账号", required = false)
    private String account;
    
    /**
     * 用户类型
     */
    @ApiModelProperty(value = "用户类型", required = false)
    private UserTypeEnum userType;
    
    /**
     * 处理类型
     */
    @ApiModelProperty(value = "处理类型", required = false)
    private ProcessEnum processType;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public UserTypeEnum getUserType() {
        return userType;
    }

    public void setUserType(UserTypeEnum userType) {
        this.userType = userType;
    }

    public ProcessEnum getProcessType() {
        return processType;
    }

    public void setProcessType(ProcessEnum processType) {
        this.processType = processType;
    }
    
}
