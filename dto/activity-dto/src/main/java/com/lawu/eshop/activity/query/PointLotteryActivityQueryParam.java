package com.lawu.eshop.activity.query;

import com.lawu.eshop.activity.constants.PointLotteryActivityRecordStatusEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author jiangxinjun
 * @createDate 2018年2月5日
 * @updateDate 2018年2月5日
 */
@ApiModel
public class PointLotteryActivityQueryParam extends AbstractPageParam {
    
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private PointLotteryActivityRecordStatusEnum status;
    
    /**
     * 抽奖手机号
     */
    @ApiModelProperty(value = "抽奖手机号")
    private String mobile;

    public PointLotteryActivityRecordStatusEnum getStatus() {
        return status;
    }

    public void setStatus(PointLotteryActivityRecordStatusEnum status) {
        this.status = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
}
