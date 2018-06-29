/**
 * 
 */
package com.lawu.eshop.ad.param;

import java.math.BigDecimal;

import com.lawu.eshop.ad.constants.RedPacketPutWayEnum;

/**
 * 红包保存param
 * 
 * @author lihj
 * @date 2017年8月3日
 */
public class UserRedPacketSaveParam {

	private RedPacketPutWayEnum redPacketPutWayEnum;
	private int totalCount;
	private BigDecimal totalMoney;
	private String userNum;
	private String userAccount;
	private String nickname;
	
	/**
	 * @return the redPacketPutWayEnum
	 */
	public RedPacketPutWayEnum getRedPacketPutWayEnum() {
		return redPacketPutWayEnum;
	}
	/**
	 * @param redPacketPutWayEnum the redPacketPutWayEnum to set
	 */
	public void setRedPacketPutWayEnum(RedPacketPutWayEnum redPacketPutWayEnum) {
		this.redPacketPutWayEnum = redPacketPutWayEnum;
	}
	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}
	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * @return the totalMoney
	 */
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}
	/**
	 * @param totalMoney the totalMoney to set
	 */
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}
	/**
	 * @return the userNum
	 */
	public String getUserNum() {
		return userNum;
	}
	/**
	 * @param userNum the userNum to set
	 */
	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
	/**
	 * @return the userAccount
	 */
	public String getUserAccount() {
		return userAccount;
	}
	/**
	 * @param userAccount the userAccount to set
	 */
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
