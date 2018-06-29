/**
 * 
 */
package com.lawu.eshop.ad.param;

import java.math.BigDecimal;

import com.lawu.eshop.ad.constants.RedPacketPutWayEnum;

import io.swagger.annotations.ApiParam;

/**
 * 红包新增用param
 * 
 * @author lihj
 * @date 2017年8月3日
 */
public class UserRedPacketAddParam {

	@ApiParam(name = "redPacketPutWayEnum", required = true, value = "PUT_WAY_COMMON 普通红包、PUT_WAY_LUCK 手气红包")
	private RedPacketPutWayEnum redPacketPutWayEnum;
	
	@ApiParam(name = "totalCount", required = true, value = "红包数量")
	private int totalCount;
	
	@ApiParam(name = "totalMoney", required = true, value = "红包总金额")
	private BigDecimal totalMoney;
	
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

}
