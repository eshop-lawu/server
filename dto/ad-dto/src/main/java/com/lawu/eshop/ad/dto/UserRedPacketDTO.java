/**
 * 
 */
package com.lawu.eshop.ad.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.eshop.ad.constants.RedPacketPutWayEnum;
import com.lawu.eshop.ad.constants.UserRedPacketEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author lihj
 * @date 2017年8月4日
 */
public class UserRedPacketDTO {

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "红包类型 PUT_WAY_COMMON 普通红包、PUT_WAY_LUCK 手气红包")
	private RedPacketPutWayEnum redPacketPutWayEnum;

	@ApiModelProperty(value = "红包类型中文")
	private String typeStr;

	@ApiModelProperty(value = "红包总数")
	private Integer totalCount;
	@ApiModelProperty(value = "总金额")
	private BigDecimal totalMoney;

	@ApiModelProperty(value = "红包状态 [USER_STATUS_EFFECTIVE 有效、USER_STATUS_OVER 领取完、USER_STATUS_OUT 过期] ")
	private UserRedPacketEnum userRedPacketEnum;
	@ApiModelProperty(value = "红包创建时间")
	private Date gmtCreate;
	@ApiModelProperty(value = "红包创建时间格式化后")
	private String gmtCreateStr;
	
	@ApiModelProperty(value = "用户id")
	private Long memberId;
	

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
	 * @return the typeStr
	 */
	public String getTypeStr() {
		return typeStr;
	}

	/**
	 * @param typeStr
	 *            the typeStr to set
	 */
	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
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

	/**
	 * @return the gmtCreateStr
	 */
	public String getGmtCreateStr() {
		return gmtCreateStr;
	}

	/**
	 * @param gmtCreateStr
	 *            the gmtCreateStr to set
	 */
	public void setGmtCreateStr(String gmtCreateStr) {
		this.gmtCreateStr = gmtCreateStr;
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

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	
}
