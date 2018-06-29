package com.lawu.eshop.activity.srv.bo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.lawu.eshop.activity.constants.HelpRedpacketActivityStatusEnum;
import com.lawu.eshop.activity.constants.RedPacketActivityTypeEnum;
import com.lawu.eshop.activity.constants.RedPacketTypeEnum;

/**
 * 助力红包活动BO
 * 
 * @author jiangxinjun
 * @createDate 2017年12月28日
 * @updateDate 2017年12月28日
 */
public class HelpRedpacketActivityBO {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 活动主题
     */
    private String activityTheme;

    /**
     * 报名开始时间
     */
    private Date regStartTime;

    /**
     * 报名结束时间
     */
    private Date regEndTime;

    /**
     * 抢红包开始时间
     */
    private Date startTime;

    /**
     * 抢红包结束时间
     */
    private Date endTime;

    /**
     * 最小红包额度
     */
    private BigDecimal minRedpacket;

    /**
     * 最大红包额度
     */
    private BigDecimal maxRedpacket;

    /**
     * 手动分配红包总额
     */
    private BigDecimal totalManualAmount;

    /**
     * 自动分配红包总额
     */
    private BigDecimal totalAutoAmount;

    /**
     * 红包叠加倍数
     */
    private BigDecimal multiple;

    /**
     * 活动是否开放
     */
    private Boolean isOpen;
    
    /**
    * 是否关闭活动入口
    */
    private Boolean isCloseEntry;
    
    /**
     * 活动开始图片
     */
    private String startPic;

    /**
     * 活动结束图片
     */
    private String endPic;

    /**
     * 活动结束链接
     */
    private String endUrl;
    
    /**
     * 红包活动类型
     */
    private RedPacketActivityTypeEnum type;
    
    /**
     * 红包类型
     */
    private RedPacketTypeEnum redpacketType;
    
    /**
     * 微信红包商户名称
     */
    private String wxSendName;

    /**
     * 微信红包祝福语
     */
    private String wxWishing;

    /**
     * 微信红包活动名称
     */
    private String wxActName;

    /**
     * 微信红包备注
     */
    private String wxRemark;
    
    /**
     * 手动分配红包信息
     */
    private String manualInfo;
    
    /**
     * 拼图图片
     */
    private String puzzlePic;
    
    /**
     * 活动规则
     */
    private List<String> rules;
    
    /**
     * 显示领取金额
     */
    private BigDecimal sentAmount;
    
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

    public BigDecimal getTotalManualAmount() {
        return totalManualAmount;
    }

    public void setTotalManualAmount(BigDecimal totalManualAmount) {
        this.totalManualAmount = totalManualAmount;
    }

    public BigDecimal getTotalAutoAmount() {
        return totalAutoAmount;
    }

    public void setTotalAutoAmount(BigDecimal totalAutoAmount) {
        this.totalAutoAmount = totalAutoAmount;
    }

    public BigDecimal getMultiple() {
        return multiple;
    }

    public void setMultiple(BigDecimal multiple) {
        this.multiple = multiple;
    }

    public Boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    public Boolean getIsCloseEntry() {
        return isCloseEntry;
    }

    public void setIsCloseEntry(Boolean isCloseEntry) {
        this.isCloseEntry = isCloseEntry;
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

    public RedPacketActivityTypeEnum getType() {
        return type;
    }

    public void setType(RedPacketActivityTypeEnum type) {
        this.type = type;
    }

    public RedPacketTypeEnum getRedpacketType() {
        return redpacketType;
    }

    public void setRedpacketType(RedPacketTypeEnum redpacketType) {
        this.redpacketType = redpacketType;
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
    
    public String getManualInfo() {
        return manualInfo;
    }

    public void setManualInfo(String manualInfo) {
        this.manualInfo = manualInfo;
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

	public HelpRedpacketActivityStatusEnum getStatus() {
        if (getRegStartTime() == null || getRegEndTime() == null || getStartTime() == null || getEndTime() == null) {
            return null;
        }
        Date now = new Date();
        if (now.getTime() < getRegStartTime().getTime()) {
            return HelpRedpacketActivityStatusEnum.NOT_STARTED;
        }
        if (now.getTime() < getRegEndTime().getTime()) {
            return HelpRedpacketActivityStatusEnum.REGISTING;
        }
        if (now.getTime() < getStartTime().getTime()) {
            return HelpRedpacketActivityStatusEnum.REGIST_END;
        }
        if (now.getTime() < getEndTime().getTime()) {
            return HelpRedpacketActivityStatusEnum.BEGINNING;
        }
        return HelpRedpacketActivityStatusEnum.END;
    }
}