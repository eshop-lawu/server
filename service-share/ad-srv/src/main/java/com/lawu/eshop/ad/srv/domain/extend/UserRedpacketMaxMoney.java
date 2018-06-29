/**
 * 
 */
package com.lawu.eshop.ad.srv.domain.extend;

import java.math.BigDecimal;

/**
 * @author lihj
 * @date 2017年8月8日
 */
public class UserRedpacketMaxMoney {

	private BigDecimal maxMoney;
	/**
	 * 是否可以领取红包
	 */
	private boolean flag;
	
	
	private boolean sysWords;

	/**
	 * @return the maxMoney
	 */
	public BigDecimal getMaxMoney() {
		return maxMoney;
	}

	/**
	 * @param maxMoney
	 *            the maxMoney to set
	 */
	public void setMaxMoney(BigDecimal maxMoney) {
		this.maxMoney = maxMoney;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public boolean isSysWords() {
		return sysWords;
	}

	public void setSysWords(boolean sysWords) {
		this.sysWords = sysWords;
	}
	
	

	
}
