package com.lawu.eshop.user.srv.bo;

import java.math.BigDecimal;

import com.lawu.eshop.user.dto.CertifTypeEnum;
import com.lawu.eshop.user.dto.MerchantStoreTypeEnum;

/**
 * 商家门店信息
 * Created by Administrator on 2017/3/24.
 */
public class MerchantStoreBO {
	
	private Long id;

	private Long merchantId;

    /**
     *
     * 店铺名称
     * merchant_store.name
     *
     * @mbg.generated 2017-03-24 10:29:55
     */
    private String name;

    /**
     *
     * 省市区
     * merchant_store.region_path
     *
     * @mbg.generated 2017-03-24 10:29:55
     */
    private String regionPath;

    /**
     *
     * 店铺地址信息
     * merchant_store.address
     *
     * @mbg.generated 2017-03-24 10:29:55
     */
    private String address;

    /**
     *
     * 经度
     * merchant_store.longitude
     *
     * @mbg.generated 2017-03-24 10:29:55
     */
    private BigDecimal longitude;

    /**
     *
     * 纬度
     * merchant_store.latitude
     *
     * @mbg.generated 2017-03-24 10:29:55
     */
    private BigDecimal latitude;

    /**
     *
     * 主营业务
     * merchant_store.industry_path
     *
     * @mbg.generated 2017-03-24 10:29:55
     */
    private String industryPath;

    private String industryName;

    /**
     *
     * 店铺介绍
     * merchant_store.intro
     *
     * @mbg.generated 2017-03-24 10:29:55
     */
    private String intro;

    /**
     * 负责人
     *
     */
    private String principalName;

    /**
     * 负责人电话
     */
    private String principalMobile;

    private CertifTypeEnum certifType;
    
    
    private MerchantStoreTypeEnum  manageTypeEnum;
    

	public MerchantStoreTypeEnum getManageTypeEnum() {
		return manageTypeEnum;
	}

	public void setManageTypeEnum(MerchantStoreTypeEnum manageTypeEnum) {
		this.manageTypeEnum = manageTypeEnum;
	}

	public CertifTypeEnum getCertifType() {
		return certifType;
	}

	public void setCertifType(CertifTypeEnum certifType) {
		this.certifType = certifType;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegionPath() {
        return regionPath;
    }

    public void setRegionPath(String regionPath) {
        this.regionPath = regionPath;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getIndustryPath() {
        return industryPath;
    }

    public void setIndustryPath(String industryPath) {
        this.industryPath = industryPath;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public String getPrincipalMobile() {
        return principalMobile;
    }

    public void setPrincipalMobile(String principalMobile) {
        this.principalMobile = principalMobile;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }
}
