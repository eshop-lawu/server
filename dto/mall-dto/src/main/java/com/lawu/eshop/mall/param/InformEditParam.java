package com.lawu.eshop.mall.param;

import java.util.Date;

public class InformEditParam {
	
	/**
	 *
	 * 
	 * inform.id
	 *
	 * @mbg.generated
	 */
	private Long id;

	/**
	 *
	 * 0：待处理，1：已处理，2：不处理 inform.status
	 *
	 * @mbg.generated
	 */
	private Byte status;

	/**
	 *
	 * 审核人员ID inform.auditor_id
	 *
	 * @mbg.generated
	 */
	private Integer auditorId;

	/**
	 *
	 * 审核人员名称 inform.auditor_name
	 *
	 * @mbg.generated
	 */
	private String auditorName;

	/**
	 *
	 * 审核备注 inform.remark
	 *
	 * @mbg.generated
	 */
	private String remark;

	/**
	 *
	 * 审核时间 inform.audit_time
	 *
	 * @mbg.generated
	 */
	private Date auditTime;

	/**
	 *
	 * 修改时间 inform.gmt_modified
	 *
	 * @mbg.generated
	 */
	private Date gmtModified;

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
	 * @return the status
	 */
	public Byte getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Byte status) {
		this.status = status;
	}

	/**
	 * @return the auditorId
	 */
	public Integer getAuditorId() {
		return auditorId;
	}

	/**
	 * @param auditorId
	 *            the auditorId to set
	 */
	public void setAuditorId(Integer auditorId) {
		this.auditorId = auditorId;
	}

	/**
	 * @return the auditorName
	 */
	public String getAuditorName() {
		return auditorName;
	}

	/**
	 * @param auditorName
	 *            the auditorName to set
	 */
	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the auditTime
	 */
	public Date getAuditTime() {
		return auditTime;
	}

	/**
	 * @param auditTime
	 *            the auditTime to set
	 */
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	/**
	 * @return the gmtModified
	 */
	public Date getGmtModified() {
		return gmtModified;
	}

	/**
	 * @param gmtModified
	 *            the gmtModified to set
	 */
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

}