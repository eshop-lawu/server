package com.lawu.eshop.user.dto;

import com.lawu.eshop.user.constants.UserSexEnum;

/**
 * @author Leach
 * @date 2017/3/27
 */
public class LoginUserDTO {

    private Long id;

    private String num;

    private String account;

    private String ryToken;

    private Boolean isFreeze;

    private UserSexEnum userSex;
    
    /**
     * 门店类型
     */
    private MerchantStoreTypeEnum merchantStoreType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    public UserSexEnum getUserSex() {
        return userSex;
    }

    public void setUserSex(UserSexEnum userSex) {
        this.userSex = userSex;
    }
}
