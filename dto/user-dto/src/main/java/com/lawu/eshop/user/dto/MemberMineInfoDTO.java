package com.lawu.eshop.user.dto;

public class MemberMineInfoDTO {
	
	/**
	 * 昵称
	 */
    private String nickname;
	
    /**
     * 头像
     */
    private String headimg;
    
    /**
     * 等级
     */
    private Integer level;
	
	/**
	 * E友总数
	 */
	private Integer inviteMemberCount;
	
	/**
	 * 邀请的商家总数
	 */
	private Integer inviteMerchantCount;

	private Byte grade;

	private Integer growthValue;

	private boolean loginPwdIsExist;
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getInviteMemberCount() {
		return inviteMemberCount;
	}

	public void setInviteMemberCount(Integer inviteMemberCount) {
		this.inviteMemberCount = inviteMemberCount;
	}

	public Integer getInviteMerchantCount() {
		return inviteMerchantCount;
	}

	public void setInviteMerchantCount(Integer inviteMerchantCount) {
		this.inviteMerchantCount = inviteMerchantCount;
	}

	public Byte getGrade() {
		return grade;
	}

	public void setGrade(Byte grade) {
		this.grade = grade;
	}

	public Integer getGrowthValue() {
		return growthValue;
	}

	public void setGrowthValue(Integer growthValue) {
		this.growthValue = growthValue;
	}

	public boolean isLoginPwdIsExist() {
		return loginPwdIsExist;
	}

	public void setLoginPwdIsExist(boolean loginPwdIsExist) {
		this.loginPwdIsExist = loginPwdIsExist;
	}
}
