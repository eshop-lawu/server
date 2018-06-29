package com.lawu.eshop.game.srv.domain.extend;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月19日
 */
public class FreeCountDOView {
	
	private int freeCount;
	
	private int shareCount;
	
	private String userNum;
	
	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public int getFreeCount() {
		return freeCount;
	}

	public void setFreeCount(int freeCount) {
		this.freeCount = freeCount;
	}

	public int getShareCount() {
		return shareCount;
	}

	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}
	
	

}
