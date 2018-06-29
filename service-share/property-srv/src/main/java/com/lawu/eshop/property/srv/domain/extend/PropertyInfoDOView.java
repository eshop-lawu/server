package com.lawu.eshop.property.srv.domain.extend;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PropertyInfoDOView implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private BigDecimal balance;
    private Date gmtModified;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public Date getGmtModified() {
		return gmtModified;
	}
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
    
}