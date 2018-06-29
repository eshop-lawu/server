package com.lawu.eshop.activity.srv.bo;

import java.math.BigDecimal;
import java.util.List;

import com.lawu.eshop.activity.constants.HelpRedpacketActivityStatusEnum;

public class HelpRedpacketAttendBO {

	/**
	 * 主键 
	 */
	private Long id;
	
	/**
	 * 活动主题
	 */
	private String activityTheme;
	
	/**
	 * 开枪倒计时
	 */
	private Long startTime;
	
	/**
	 * 结束倒计时
	 */
	private Long endTime;
	
	/**
	 * 是否参与
	 */
	private Boolean isAttend;
	
	/**
	 * 倍数
	 */
	private Double helpCount;
	
	private HelpRedpacketActivityStatusEnum activityStatusEnum;
	
	private List<InviteRecordBO> inviteList;
	
	/**
	 * 用户账号
	 */
	private String account;
	
	/**
	 * 领取的金额
	 */
	private BigDecimal money;
	
	/**
	 * 是否领取
	 */
	private Boolean isGet = false;
	
	/**
	 * 活动结束发放总金额
	 */
	private BigDecimal totalMoney;
	
    /**
     * 拼图图片
     */
    private String puzzlePic;
	
    /**
     * 活动规则
     */
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

	public List<InviteRecordBO> getInviteList() {
		return inviteList;
	}

	public void setInviteList(List<InviteRecordBO> inviteList) {
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
