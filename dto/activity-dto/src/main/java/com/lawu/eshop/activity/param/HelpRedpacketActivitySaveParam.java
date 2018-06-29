package com.lawu.eshop.activity.param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.activity.constants.RedPacketActivityTypeEnum;
import com.lawu.eshop.activity.constants.RedPacketTypeEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 助力红包更新参数
 * @author jiangxinjun
 * @createDate 2017年12月28日
 * @updateDate 2017年12月28日
 */
@ApiModel
public class HelpRedpacketActivitySaveParam {
    
    /**
     * 活动主题
     */
    @NotBlank(message = "活动主题不能为空")
    @Length(max = 50, message = "活动主题限制50字")
    @ApiModelProperty(value = "活动主题", required = true)
    private String activityTheme;

    /**
     * 报名开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat
    @NotNull(message = "报名开始时间不能为空")
    @ApiModelProperty(value = "报名开始时间", required = true)
    private Date regStartTime;

    /**
     * 报名结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat
    @NotNull(message = "报名结束时间不能为空")
    @ApiModelProperty(value = "报名结束时间", required = true)
    private Date regEndTime;

    /**
     * 抢红包开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat
    @NotNull(message = "抢红包开始时间不能为空")
    @ApiModelProperty(value = "抢红包开始时间", required = true)
    private Date startTime;

    /**
     * 抢红包结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat
    @NotNull(message = "抢红包结束时间不能为空")
    @ApiModelProperty(value = "抢红包结束时间", required = true)
    private Date endTime;

    /**
     * 最小红包额度
     */
    @DecimalMin(value = "0.01", message = "最小红包额度不能小于0.01")
    @ApiModelProperty(value = "最小红包额度", required = true)
    private BigDecimal minRedpacket;

    /**
     * 最大红包额度
     */
    @DecimalMin(value = "0.01", message = "最大红包额度不能小于0.01")
    @ApiModelProperty(value = "最大红包额度", required = true)
    private BigDecimal maxRedpacket;

    /**
     * 红包叠加倍数
     */
    @DecimalMin(value = "0.01", message = "红包叠加倍数不能小于0.01")
    @ApiModelProperty(value = "红包叠加倍数", required = true)
    private BigDecimal multiple;

    /**
     * 活动是否开放
     */
    @NotNull(message = "活动是否开放不能为空")
    @ApiModelProperty(value = "活动是否开放", required = true)
    private Boolean open;
    
    /**
    * 是否关闭活动入口
    */
    @NotNull(message = "是否关闭活动入口不能为空")
    @ApiModelProperty(value = "是否关闭活动入口", required = true)
    private Boolean closeEntry;
    
    /**
     * 活动开始图片
     */
    @NotBlank(message = "活动开始图片不能为空")
    @ApiModelProperty(value = "活动开始图片", required = true)
    private String startPic;

    /**
     * 活动结束图片
     */
    @NotBlank(message = "活动结束图片不能为空")
    @ApiModelProperty(value = "活动结束图片", required = true)
    private String endPic;

    /**
     * 活动结束链接
     */
    @NotBlank(message = "活动结束链接不能为空")
    @ApiModelProperty(value = "活动结束链接", required = true)
    private String endUrl;
    
    /**
     * 红包类型
     */
    @NotNull(message = "红包类型不能为空")
    @ApiModelProperty(value = "红包类型", required = true)
    private RedPacketTypeEnum redpacketType;
    
    /**
     * 红包活动类型
     */
    @NotNull(message = "红包类型不能为空")
    @ApiModelProperty(value = "红包活动类型", required = true)
    private RedPacketActivityTypeEnum type;
    
    /**
     * 微信红包商户名称
     */
    @Length(max = 200, message = "微信红包商户名称限制200字")
    @ApiModelProperty(value = "微信红包商户名称")
    private String wxSendName;

    /**
     * 微信红包祝福语
     */
    @Length(max = 200, message = "微信红包祝福语限制200字")
    @ApiModelProperty(value = "微信红包祝福语")
    private String wxWishing;

    /**
     * 微信红包活动名称
     */
    @Length(max = 200, message = "微信红包活动名称限制200字")
    @ApiModelProperty(value = "微信红包活动名称")
    private String wxActName;

    /**
     * 微信红包备注
     */
    @Length(max = 200, message = "微信红包备注限制200字")
    @ApiModelProperty(value = "微信红包备注")
    private String wxRemark;
    
    /**
     * 拼图图片
     */
    @ApiModelProperty(value = "拼图图片")
    private String puzzlePic;
    
    /**
     * 活动规则
     */
    @ApiModelProperty(value = "活动规则")
    private List<String> rules;
    
    /**
     * 显示领取金额
     */
    @ApiModelProperty(value = "显示领取金额")
    private BigDecimal sentAmount;
    
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

    public Date getRegEndTime() {
        return regEndTime;
    }

    public void setRegEndTime(Date regEndTime) {
        this.regEndTime = regEndTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public String getStartPic() {
        return startPic;
    }

    public void setStartPic(String startPic) {
        this.startPic = startPic;
    }

    public String getEndPic() {
        return endPic;
    }

    public void setEndPic(String endPic) {
        this.endPic = endPic;
    }

    public String getEndUrl() {
        return endUrl;
    }

    public void setEndUrl(String endUrl) {
        this.endUrl = endUrl;
    }

    public RedPacketTypeEnum getRedpacketType() {
        return redpacketType;
    }

    public void setRedpacketType(RedPacketTypeEnum redpacketType) {
        this.redpacketType = redpacketType;
    }

    public RedPacketActivityTypeEnum getType() {
        return type;
    }

    public void setType(RedPacketActivityTypeEnum type) {
        this.type = type;
    }

    public String getWxSendName() {
        return wxSendName;
    }

    public void setWxSendName(String wxSendName) {
        this.wxSendName = wxSendName;
    }

    public String getWxWishing() {
        return wxWishing;
    }

    public void setWxWishing(String wxWishing) {
        this.wxWishing = wxWishing;
    }

    public String getWxActName() {
        return wxActName;
    }

    public void setWxActName(String wxActName) {
        this.wxActName = wxActName;
    }

    public String getWxRemark() {
        return wxRemark;
    }

    public void setWxRemark(String wxRemark) {
        this.wxRemark = wxRemark;
    }

    public String getPuzzlePic() {
        return puzzlePic;
    }

    public void setPuzzlePic(String puzzlePic) {
        this.puzzlePic = puzzlePic;
    }

    public List<String> getRules() {
        return rules;
    }

    public void setRules(List<String> rules) {
        this.rules = rules;
    }

	public BigDecimal getSentAmount() {
		return sentAmount;
	}

	public void setSentAmount(BigDecimal sentAmount) {
		this.sentAmount = sentAmount;
	}
    
    
    
}
