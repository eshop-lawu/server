package com.lawu.eshop.property.srv.domain.extend;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class WithdrawCashOperDOView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Byte status;
	private String auditFailReason;
	private Long auditUserId;
	private String auditUserName;
	private Date gmtModified;
	private Date gmtAccept;
	private Date gmtFinish;

	private String currentScale;
	private BigDecimal money;

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public String getAuditFailReason() {
		return auditFailReason;
	}
	public void setAuditFailReason(String auditFailReason) {
		this.auditFailReason = auditFailReason;
	}
	public Long getAuditUserId() {
		return auditUserId;
	}
	public void setAuditUserId(Long auditUserId) {
		this.auditUserId = auditUserId;
	}
	public String getAuditUserName() {
		return auditUserName;
	}
	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}
	public Date getGmtModified() {
		return gmtModified;
	}
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	public Date getGmtAccept() {
		return gmtAccept;
	}
	public void setGmtAccept(Date gmtAccept) {
		this.gmtAccept = gmtAccept;
	}
	public Date getGmtFinish() {
		return gmtFinish;
	}
	public void setGmtFinish(Date gmtFinish) {
		this.gmtFinish = gmtFinish;
	}

	public String getCurrentScale() {
		return currentScale;
	}

	public void setCurrentScale(String currentScale) {
		this.currentScale = currentScale;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}
}
