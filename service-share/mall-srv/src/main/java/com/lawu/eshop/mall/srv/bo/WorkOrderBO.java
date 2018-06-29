package com.lawu.eshop.mall.srv.bo;

import java.util.Date;

public class WorkOrderBO {

	private Long id;
	private String userNum;
	private String account;
	private String name;
	private String content;
	private String replyContent;
	private Integer auditorId;
	private String auditorName;
	private byte status;
	private byte type;
	private Date gmtCreat;
	private Date gmtModified;
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
	public Date getGmtCreat() {
		return gmtCreat;
	}
	public void setGmtCreat(Date gmtCreat) {
		this.gmtCreat = gmtCreat;
	}
	public Date getGmtDeal() {
		return gmtDeal;
	}
	public void setGmtDeal(Date gmtDeal) {
		this.gmtDeal = gmtDeal;
	}
}
