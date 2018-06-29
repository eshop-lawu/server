package com.lawu.eshop.activity.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.activity.constants.HelpRedpacketActivityStatusEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 助力红包活动列表DTO
 * 
 * @author jiangxinjun
 * @createDate 2018年1月15日
 * @updateDate 2018年1月15日
 */
@ApiModel
public class HelpRedpacketActivityQueryDTO {
    
    /**
     * id
     */
    @ApiModelProperty(value = "活动id", required = true)
    private Integer id;
    
    /**
     * 活动主题
     */
    @ApiModelProperty(value = "活动主题", required = true)
    private String activityTheme;

    /**
     * 报名开始时间
     */
    @JsonFormat
    @ApiModelProperty(value = "报名开始时间", required = true)
    private Date regStartTime;

    /**
     * 最小红包额度
     */
    @ApiModelProperty(value = "最小红包额度", required = true)
    private BigDecimal minRedpacket;

    /**
     * 最大红包额度
     */
    @ApiModelProperty(value = "最大红包额度", required = true)
    private BigDecimal maxRedpacket;

    /**
     * 红包叠加倍数
     */
    @ApiModelProperty(value = "红包叠加倍数", required = true)
    private BigDecimal multiple;

    /**
     * 活动是否开放
     */
    @ApiModelProperty(value = "活动是否开放", required = true)
    private Boolean open;
    
    /**
    * 是否关闭活动入口
    */
    @ApiModelProperty(value = "是否关闭活动入口", required = true)
    private Boolean closeEntry;
    
    /**
     * 活动状态
     */
    @ApiModelProperty(value = "活动状态(NOT_STARTED-未开始|REGISTING-报名中|REGIST_END-报名结束|BEGINNING-开抢中|END-已结束)", required = true)
    private HelpRedpacketActivityStatusEnum status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActivityTheme() {
        return activityTheme;
    }

    public void setActivityTheme(String activityTheme) {
        this.activityTheme = activityTheme;
    }

    public Date getRegStartTime() {
        return regStartTime;
    }

    public void setRegStartTime(Date regStartTime) {
        this.regStartTime = regStartTime;
    }

    public BigDecimal getMinRedpacket() {
        return minRedpacket;
    }

    public void setMinRedpacket(BigDecimal minRedpacket) {
        this.minRedpacket = minRedpacket;
    }

    public BigDecimal getMaxRedpacket() {
        return maxRedpacket;
    }

    public void setMaxRedpacket(BigDecimal maxRedpacket) {
        this.maxRedpacket = maxRedpacket;
    }

    public BigDecimal getMultiple() {
        return multiple;
    }

    public void setMultiple(BigDecimal multiple) {
        this.multiple = multiple;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Boolean getCloseEntry() {
        return closeEntry;
    }

    public void setCloseEntry(Boolean closeEntry) {
        this.closeEntry = closeEntry;
    }

    public HelpRedpacketActivityStatusEnum getStatus() {
        return status;
    }

    public void setStatus(HelpRedpacketActivityStatusEnum status) {
        this.status = status;
    }
    
}