package com.lawu.eshop.user.param;

import com.lawu.eshop.user.constants.UserSexEnum;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2017/4/6.
 */
public class InviteFansParam {

    @ApiModelProperty(value = "会员编号,以逗号分隔", required = false)
    private String nums;

    @ApiModelProperty(value = "邀请区域,全国为空字符串", required = false)
    private String regionName;

    @ApiModelProperty(value = "性别，SEX_MALE--男，SEX_FEMALE--女，SEX_SECRET--全部", required = true)
    private UserSexEnum userSexEnum;

    @ApiModelProperty(value = "年龄限制，true--有年龄要求(startAge、endAge必填)，false--没有年龄要求", required = true)
    private Boolean isAgeLimit;

    @ApiModelProperty(value = "最小年龄")
    private Integer startAge;

    @ApiModelProperty(value = "最大年龄")
    private Integer endAge;

    @ApiModelProperty(value = "支付密码", required = true)
    private String payPwd;

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public UserSexEnum getUserSexEnum() {
        return userSexEnum;
    }

    public void setUserSexEnum(UserSexEnum userSexEnum) {
        this.userSexEnum = userSexEnum;
    }

    public Boolean getIsAgeLimit() {
        return isAgeLimit;
    }

    public void setIsAgeLimit(Boolean ageLimit) {
        isAgeLimit = ageLimit;
    }

    public Integer getStartAge() {
        return startAge;
    }

    public void setStartAge(Integer startAge) {
        this.startAge = startAge;
    }

    public Integer getEndAge() {
        return endAge;
    }

    public void setEndAge(Integer endAge) {
        this.endAge = endAge;
    }

    public String getPayPwd() {
        return payPwd;
    }

    public void setPayPwd(String payPwd) {
        this.payPwd = payPwd;
    }
}
