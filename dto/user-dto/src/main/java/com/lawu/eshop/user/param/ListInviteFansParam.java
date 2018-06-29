package com.lawu.eshop.user.param;

import com.lawu.eshop.user.constants.UserSexEnum;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2017/4/26.
 */
public class ListInviteFansParam {

    @ApiModelProperty(value = "区域路径，全国为空", required = false)
    private String regionPath;

    @ApiModelProperty(value = "性别，SEX_MALE--男，SEX_FEMALE--女，SEX_SECRET--全部", required = true)
    private UserSexEnum userSexEnum;

    @ApiModelProperty(value = "年龄限制，true--有年龄要求(startAge、endAge必填)，false--没有年龄要求", required = true)
    private Boolean isAgeLimit;

    @ApiModelProperty(value = "最小年龄")
    private Integer startAge;

    @ApiModelProperty(value = "最大年龄")
    private Integer endAge;

    @ApiModelProperty(value = "邀请人数")
    private Integer inviteCount;

    public String getRegionPath() {
        return regionPath;
    }

    public void setRegionPath(String regionPath) {
        this.regionPath = regionPath;
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

    public Integer getInviteCount() {
        return inviteCount;
    }

    public void setInviteCount(Integer inviteCount) {
        this.inviteCount = inviteCount;
    }
}
