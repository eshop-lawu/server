package com.lawu.eshop.ad.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.eshop.ad.constants.PlatformRedPacketStatusEnum;

public class PlatformRedPacketBO {

	/**
	 *
	 * 
	 * platform_red_packet.id
	 *
	 * @mbg.generated
	 */
	private Long id;

	/**
	 *
	 * 金额 platform_red_packet.money
	 *
	 * @mbg.generated
	 */
	private BigDecimal money;

	/**
	 *
	 * 发送红包总数 platform_red_packet.send_count
	 *
	 * @mbg.generated
	 */
	private Integer sendCount;

	/**
	 *
	 * 红包状态 0-禁用 1-启用 platform_red_packet.status
	 *
	 * @mbg.generated
	 */
	private PlatformRedPacketStatusEnum statusEnum;

	/**
	 *
	 * 操作人 platform_red_packet.auditor_id
	 *
	 * @mbg.generated
	 */
	private Long auditorId;

	/**
	 *
	 * 更新时间 platform_red_packet.gmt_modified
	 *
	 * @mbg.generated
	 */
	private Date gmtModified;

	/**
	 *
	 * 创建时间 platform_red_packet.gmt_create
	 *
	 * @mbg.generated
	 */
	private Date gmtCreate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Integer getSendCount() {
		return sendCount;
	}

	public void setSendCount(Integer sendCount) {
		this.sendCount = sendCount;
	}

	public PlatformRedPacketStatusEnum getStatusEnum() {
		return statusEnum;
	}

	public void setStatusEnum(PlatformRedPacketStatusEnum statusEnum) {
		this.statusEnum = statusEnum;
	}

	public Long getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(Long auditorId) {
		this.auditorId = auditorId;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

}
