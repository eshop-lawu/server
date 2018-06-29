package com.lawu.eshop.user.srv.bo;

import java.util.Date;

public class FansInviteContentBO {

	private Long id;
	private Long merchantId;
	private String merchantNum;
	private String url;
	private String logoUrl;
	private String merchantStoreName;
	private String inviteContent;
	private String merchantStoreIntro;
	private Long fansInviteDetailId;
	private Boolean isOverdue;
	private Integer refuseNumber;
	private Date gmtCreate;
	private Date gmtModified;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantNum() {
		return merchantNum;
	}

	public void setMerchantNum(String merchantNum) {
		this.merchantNum = merchantNum;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getMerchantStoreName() {
		return merchantStoreName;
	}

	public void setMerchantStoreName(String merchantStoreName) {
		this.merchantStoreName = merchantStoreName;
	}

	public String getInviteContent() {
		return inviteContent;
	}

	public void setInviteContent(String inviteContent) {
		this.inviteContent = inviteContent;
	}

	public String getMerchantStoreIntro() {
		return merchantStoreIntro;
	}

	public void setMerchantStoreIntro(String merchantStoreIntro) {
		this.merchantStoreIntro = merchantStoreIntro;
	}

	public Long getFansInviteDetailId() {
		return fansInviteDetailId;
	}

	public void setFansInviteDetailId(Long fansInviteDetailId) {
		this.fansInviteDetailId = fansInviteDetailId;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Boolean getIsOverdue() {
		return isOverdue;
	}

	public void setIsOverdue(Boolean overdue) {
		isOverdue = overdue;
	}

	public Integer getRefuseNumber() {
		return refuseNumber;
	}

	public void setRefuseNumber(Integer refuseNumber) {
		this.refuseNumber = refuseNumber;
	}
}
