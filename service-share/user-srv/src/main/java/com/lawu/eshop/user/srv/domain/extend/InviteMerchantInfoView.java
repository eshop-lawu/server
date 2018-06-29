package com.lawu.eshop.user.srv.domain.extend;

import com.lawu.eshop.user.dto.MerchantStatusEnum;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */
public class InviteMerchantInfoView implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String account;

	private Integer level;

	private String storeName;

	private String regionName;

	private String address;

	private String principalName;

	private Date gmtCreate;

	private Byte status;

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

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPrincipalName() {
		return principalName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}
}
