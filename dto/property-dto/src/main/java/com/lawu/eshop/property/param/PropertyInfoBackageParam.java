package com.lawu.eshop.property.param;

import com.lawu.eshop.property.constants.UserTypeEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2017/5/25.
 */
public class PropertyInfoBackageParam extends AbstractPageParam{

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "编号")
    private String userNum;

    @ApiModelProperty(value = "用户类型")
    private UserTypeEnum userType;

    @ApiModelProperty(value = "排序名称")
    private String sortName;

    @ApiModelProperty(value = "排序类型")
    private String sortOrder;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public UserTypeEnum getUserType() {
        return userType;
    }

    public void setUserType(UserTypeEnum userType) {
        this.userType = userType;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }
}
