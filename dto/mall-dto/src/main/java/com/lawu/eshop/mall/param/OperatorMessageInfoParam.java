package com.lawu.eshop.mall.param;

import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2017/4/19.
 */
public class OperatorMessageInfoParam {
    @ApiModelProperty(value = "推送标题",required = true)
    private String title;
    @ApiModelProperty(value = "推送内容",required = true)
    private String content;
    @ApiModelProperty(value = "手机号",required = true)
    private String moblie;
    @ApiModelProperty(value = "用户类型")
    private UserTypeEnum userType;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMoblie() {
        return moblie;
    }

    public void setMoblie(String moblie) {
        this.moblie = moblie;
    }

    public UserTypeEnum getUserType() {
        return userType;
    }

    public void setUserType(UserTypeEnum userType) {
        this.userType = userType;
    }
}
