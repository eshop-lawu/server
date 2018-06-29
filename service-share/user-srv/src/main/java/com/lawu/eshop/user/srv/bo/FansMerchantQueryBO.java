package com.lawu.eshop.user.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.eshop.common.constants.ManageTypeEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author lihj
 * @date 2018/6/8
 */
public class FansMerchantQueryBO {
    private Long merchantId;

    private String name;

    private String industryName;

    private BigDecimal feedbackRate;

    private String path;

    private Integer fansCount;

    private int distance;

    private Long merchantStoreId;
    
    private ManageTypeEnum typeEnum;
    
    private Date fansDate;
    
    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public BigDecimal getFeedbackRate() {
        return feedbackRate;
    }

    public void setFeedbackRate(BigDecimal feedbackRate) {
        this.feedbackRate = feedbackRate;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getFansCount() {
        return fansCount;
    }

    public void setFansCount(Integer fansCount) {
        this.fansCount = fansCount;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Long getMerchantStoreId() {
        return merchantStoreId;
    }

    public void setMerchantStoreId(Long merchantStoreId) {
        this.merchantStoreId = merchantStoreId;
    }

	public ManageTypeEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(ManageTypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}

	public Date getFansDate() {
		return fansDate;
	}

	public void setFansDate(Date fansDate) {
		this.fansDate = fansDate;
	}
    
}
