package com.lawu.eshop.activity.dto;

import java.math.BigDecimal;
import java.util.List;

import com.lawu.eshop.activity.constants.HelpRedpacketActivityStatusEnum;

import io.swagger.annotations.ApiModelProperty;
/**
 * 活动详情
 * @Description
 * @author zhangrc
 * @date 2017年12月28日
 */
public class HelpRedpacketAttendDTO {

	/**
	 * 主键 
	 */
	@ApiModelProperty(value = "主键")
	private Long id;
	
	/**
	 * 活动主题
	 */
	@ApiModelProperty(value = "活动主题")
	private String activityTheme;
	
	/**
	 * 开枪倒计时
	 */
	@ApiModelProperty(value = "startTime")
	private Long startTime;
	
	/**
	 * 结束倒计时
	 */
	@ApiModelProperty(value = "结束倒计时")
	private Long endTime;
	
	/**
	 * 是否参与
	 */
	@ApiModelProperty(value = "是否参与 Yes 参入 false 没有")
	private Boolean isAttend;
	
	/**
	 * 倍数
	 */
	@ApiModelProperty(value = "红包倍数")
	private Double helpCount;
	
	/**
	 * 活动状态
	 */
	@ApiModelProperty(value = "NOT_STARTED 未开始 REGISTING报名中 REGIST_END报名结束 BEGINNING开抢中 END已结束")
	private HelpRedpacketActivityStatusEnum activityStatusEnum;
	
	/**
	 * 助力好友
	 */
	private List<InviteRecordDTO> inviteList;
	
	/**
	 * 用户账号
	 */
	@ApiModelProperty(value = "用户账号")
	private String account;
	
	/**
	 * 领取的金额
	 */
	@ApiModelProperty(value = "领取的金额")
	private BigDecimal money;
	
	/**
	 * 是否领取
	 */
	@ApiModelProperty(value = "是否领取")
	private Boolean isGet;
	
	@ApiModelProperty(value = "公众号链接")
	private String weiChatSubScription;
	
	
	/**
	 * 活动结束发放总金额
	 */
	@ApiModelProperty(value = "活动结束发放总金额")
	private BigDecimal totalMoney;
	
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

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActivityTheme() {
		return activityTheme;
	}

	public void setActivityTheme(String activityTheme) {
		this.activityTheme = activityTheme;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public Boolean getIsAttend() {
		return isAttend;
	}

	public void setIsAttend(Boolean isAttend) {
		this.isAttend = isAttend;
	}

	public Double getHelpCount() {
		return helpCount;
	}

	public void setHelpCount(Double helpCount) {
		this.helpCount = helpCount;
	}

	public HelpRedpacketActivityStatusEnum getActivityStatusEnum() {
		return activityStatusEnum;
	}

	public void setActivityStatusEnum(HelpRedpacketActivityStatusEnum activityStatusEnum) {
		this.activityStatusEnum = activityStatusEnum;
	}

	public List<InviteRecordDTO> getInviteList() {
		return inviteList;
	}

	public void setInviteList(List<InviteRecordDTO> inviteList) {
		this.inviteList = inviteList;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Boolean getIsGet() {
		return isGet;
	}

	public void setIsGet(Boolean isGet) {
		this.isGet = isGet;
	}

	public String getWeiChatSubScription() {
		return weiChatSubScription;
	}

	public void setWeiChatSubScription(String weiChatSubScription) {
		this.weiChatSubScription = weiChatSubScription;
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
	
}
