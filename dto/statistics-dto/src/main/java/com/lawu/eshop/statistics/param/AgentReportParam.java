package com.lawu.eshop.statistics.param;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author zhangyong
 * @date 2017/8/11.
 */
public class AgentReportParam {

    @ApiModelProperty(value = "开始时间")
    @NotBlank(message="开始时间不能为空")
    private String beginTime;

    @ApiModelProperty(value = "结束时间")
    @NotBlank(message="结束时间不能为空")
    private String endTime;

    @ApiModelProperty(value = "区域路径",required = true)
    @NotBlank(message="参数错误")
    private String regionPath;

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRegionPath() {
        return regionPath;
    }

    public void setRegionPath(String regionPath) {
        this.regionPath = regionPath;
    }
}
