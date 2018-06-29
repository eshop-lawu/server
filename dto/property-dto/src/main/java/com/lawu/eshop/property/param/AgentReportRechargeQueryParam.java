package com.lawu.eshop.property.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class AgentReportRechargeQueryParam {

	@NotBlank(message = "date不能为空")
	private String date;

	@NotNull(message = "status不能为空")
	private Byte status;

	@NotNull(message = "channel不能为空")
	private Byte channel;
	
	private int offset;
	
	private int pageSize;

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}

	public Byte getChannel() {
		return channel;
	}

	public void setChannel(Byte channel) {
		this.channel = channel;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
