package com.lawu.eshop.property.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.property.constants.SexEnum;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author meishuquan
 * @date 2017/4/24.
 */
public class FansInviteDetailDTO {

    @ApiModelProperty(value = "邀请区域")
    private String regionName;

    @ApiModelProperty(value = "性别，SEX_MALE--男，SEX_FEMALE--女，SEX_SECRET--全部")
    private SexEnum sexEnum;

    @ApiModelProperty(value = "年龄区间，不限年龄为空")
    private String age;

    @ApiModelProperty(value = "邀请粉丝数量")
    private Integer inviteFansCount;

    @ApiModelProperty(value = "消费积分")
    private BigDecimal consumePoint;

    @ApiModelProperty(value = "邀请日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gmtCreate;

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getInviteFansCount() {
        return inviteFansCount;
    }

    public void setInviteFansCount(Integer inviteFansCount) {
        this.inviteFansCount = inviteFansCount;
    }

    public BigDecimal getConsumePoint() {
        return consumePoint;
    }

    public void setConsumePoint(BigDecimal consumePoint) {
        this.consumePoint = consumePoint;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public SexEnum getSexEnum() {
        return sexEnum;
    }

    public void setSexEnum(SexEnum sexEnum) {
        this.sexEnum = sexEnum;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
