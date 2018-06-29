package com.lawu.eshop.property.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class CashBackageOperDataParam extends CashBackageOperParam{

	@NotNull(message="auditUserId不能为空")
	private Long auditUserId;
	
	@NotBlank(message="auditUserName不能为空")
	private String auditUserName;
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
	

}
