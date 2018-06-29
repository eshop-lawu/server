/**
 * 
 */
package com.lawu.eshop.ad.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.eshop.ad.constants.RedPacketPutWayEnum;
import com.lawu.eshop.ad.constants.UserRedPacketEnum;

/**
 * @author lihj
 * @date 2017年8月4日
 */
public class UserRedPacketBO {
	/**
	 *
	 * 
	 * user_red_packet.id
	 *
	 * @mbg.generated
	 */
	private Long id;

	/**
	 *
	 * 用户num user_red_packet.user_num
	 *
	 * @mbg.generated
	 */
	private String userNum;

	/**
	 *
	 * 用户账号 user_red_packet.user_account
	 *
	 * @mbg.generated
	 */
	private String userAccount;

	/**
	 *
	 * 红包类型[0普通红包,1拼手气红包] user_red_packet.type
	 *
	 * @mbg.generated
	 */
	private RedPacketPutWayEnum redPacketPutWayEnum;

	/**
	 *
	 * 红包总数 user_red_packet.total_count
	 *
	 * @mbg.generated
	 */
	private Integer totalCount;

	/**
	 *
	 * 总金额 user_red_packet.total_money
	 *
	 * @mbg.generated
	 */
	private BigDecimal totalMoney;

	/**
	 *
	 * 红包状态 1有效 2领取完 3过期 user_red_packet.status
	 *
	 * @mbg.generated
	 */
	private UserRedPacketEnum userRedPacketEnum;

	/**
	 *
	 * 创建时间 user_red_packet.gmt_create
	 *
	 * @mbg.generated
	 */
	private Date gmtCreate;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the userNum
	 */
	public String getUserNum() {
		return userNum;
	}

	/**
	 * @param userNum
	 *            the userNum to set
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
	 * @param userAccount
	 *            the userAccount to set
	 */
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	/**
	 * @return the totalCount
	 */
	public Integer getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount
	 *            the totalCount to set
	 */
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * @return the totalMoney
	 */
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	/**
	 * @param totalMoney
	 *            the totalMoney to set
	 */
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	/**
	 * @return the gmtCreate
	 */
	public Date getGmtCreate() {
		return gmtCreate;
	}

	/**
	 * @param gmtCreate
	 *            the gmtCreate to set
	 */
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public RedPacketPutWayEnum getRedPacketPutWayEnum() {
		return redPacketPutWayEnum;
	}

	public void setRedPacketPutWayEnum(RedPacketPutWayEnum redPacketPutWayEnum) {
		this.redPacketPutWayEnum = redPacketPutWayEnum;
	}

	public UserRedPacketEnum getUserRedPacketEnum() {
		return userRedPacketEnum;
	}

	public void setUserRedPacketEnum(UserRedPacketEnum userRedPacketEnum) {
		this.userRedPacketEnum = userRedPacketEnum;
	}

}
