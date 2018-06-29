package com.lawu.eshop.activity.param;/**
 * Created by ${Yangqh} on 2017/12/28.
 */

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

/**
 * <p> </p>
 *
 * @author yangqh
 * @date 2017/12/28 11:24
 */
public class DoHelpParam {

    @ApiModelProperty(value = "活动ID")
    private String regOrigin;

    @NotNull(message = "attendId不能为空")
    @Min(value=1,message = "参与ID不能小于1")
    @ApiParam(name="attendId", value = "参与详情表ID")
    private Long attendId;

    @NotBlank(message = "账号不能为空")
    @ApiParam(name="accout", value = "账号")
    private String accout;

    @NotBlank(message = "密码不能为空")
    @ApiParam(name="password", value = "密码")
    private String password;

    public String getRegOrigin() {
        return regOrigin;
    }

    public void setRegOrigin(String regOrigin) {
        this.regOrigin = regOrigin;
    }

    public Long getAttendId() {
        return attendId;
    }

    public void setAttendId(Long attendId) {
        this.attendId = attendId;
    }

    public String getAccout() {
        return accout;
    }

    public void setAccout(String accout) {
        this.accout = accout;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
