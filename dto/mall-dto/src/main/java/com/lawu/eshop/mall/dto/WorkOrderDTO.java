package com.lawu.eshop.mall.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

public class WorkOrderDTO {
	@ApiModelProperty(value = "工单编号")
	private Long id;
	
	@ApiModelProperty(value = "提交人编号")
	private String userNum;
	
	@ApiModelProperty(value = "提交人账号")
	private String account;
	
	@ApiModelProperty(value = "提交人姓名")
	private String name;
	
	@ApiModelProperty(value = "工单内容")
	private String content;
	
	@ApiModelProperty(value = "回复内容")
	private String replyContent;
	
	@ApiModelProperty(value = "处理人编号")
	private Integer auditorId;

	@ApiModelProperty(value = "处理人名称")
	private String auditorName;

	@ApiModelProperty(value = "工单状态： 1--未处理，2--已回复，3--不予处理")
	private byte status;

	@ApiModelProperty(value = "工单类型：类型 1--用户，2--商家")
	private byte type;

	@ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtCreate;

	@ApiModelProperty(value = "修改时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtModified;

	@ApiModelProperty(value = "处理时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtDeal;

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public Integer getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(Integer auditorId) {
		this.auditorId = auditorId;
	}

	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public Date getGmtDeal() {
		return gmtDeal;
	}

	public void setGmtDeal(Date gmtDeal) {
		this.gmtDeal = gmtDeal;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

}
