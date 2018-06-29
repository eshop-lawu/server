package com.lawu.eshop.framework.web.impl.dto;

import com.lawu.eshop.framework.web.impl.constants.MerchantStoreTypeEnum;
import com.lawu.eshop.framework.web.impl.constants.UserSexEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Leach
 * @date 2017/3/14
 */
public class TokenDTO {

    @ApiModelProperty(value = "用户编号", required = true)
    private String userNum;

    @ApiModelProperty(value = "用户token，登录后每次请求必须带上该标志", required = true)
    private String token;

    @ApiModelProperty(value = "融云token")
    private String ryToken;

    @ApiModelProperty(value = "true:冻结，false未冻结")
    private Boolean isFreeze;

    @ApiModelProperty(value = "商家ID")
    private Long merchantId;

    private UserSexEnum userSex;
    
    /**
     * 店铺类型
     */
    @ApiModelProperty(value = "门店类型(NOT_MERCHANT-没有店铺|NORMAL_MERCHANT-普通店铺|ENTITY_MERCHANT-实体店铺)")
    private MerchantStoreTypeEnum merchantStoreType;
    
    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRyToken() {
        return ryToken;
    }

    public void setRyToken(String ryToken) {
        this.ryToken = ryToken;
    }

    public Boolean getIsFreeze() {
        return isFreeze;
    }

    public void setIsFreeze(Boolean freeze) {
        isFreeze = freeze;
    }

	public MerchantStoreTypeEnum getMerchantStoreType() {
		return merchantStoreType;
	}

	public void setMerchantStoreType(MerchantStoreTypeEnum merchantStoreType) {
		this.merchantStoreType = merchantStoreType;
	}

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public UserSexEnum getUserSex() {
        return userSex;
    }

    public void setUserSex(UserSexEnum userSex) {
        this.userSex = userSex;
    }
}
