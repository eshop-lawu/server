package com.lawu.eshop.common.dto;

/**
 * 
 * <p>
 * Description: 计算提成时，查询上3级推荐用户的用户编号和等级
 * </p>
 * @author Yangqh
 * @date 2017年4月26日 下午5:28:24
 *
 */
public class CommissionInvitersUserDTO {

	private Integer level;//用户等级
	private String userNum;//用户编号
	private String currentUserNum;//当事人
	private int flag;//1-推荐E友收益|2-推荐商家收益
	private int dept;
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getUserNum() {
		return userNum;
	}
	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getDept() {
		return dept;
	}
	public void setDept(int dept) {
		this.dept = dept;
	}

	public String getCurrentUserNum() {
		return currentUserNum;
	}

	public void setCurrentUserNum(String currentUserNum) {
		this.currentUserNum = currentUserNum;
	}
}
