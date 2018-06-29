package com.lawu.eshop.user.srv.domain.extend;

public class MerchantAdInfoView {
	
	private Long merchantId;
	
	private Long merchantStoreId;
	
	private String name;
	
	private String path;
	
	private byte manageType;

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getMerchantStoreId() {
		return merchantStoreId;
	}

	public void setMerchantStoreId(Long merchantStoreId) {
		this.merchantStoreId = merchantStoreId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public byte getManageType() {
		return manageType;
	}

	public void setManageType(byte manageType) {
		this.manageType = manageType;
	}
	
	

}
