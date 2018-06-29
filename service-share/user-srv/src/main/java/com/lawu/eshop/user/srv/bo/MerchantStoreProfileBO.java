package com.lawu.eshop.user.srv.bo;

/**
 * 商家门店信息
 * Created by zhangyong on 2017/3/24.
 */
public class MerchantStoreProfileBO {


    /**
     * 商家id
     */
    private Long merchantId;

    /**
     * 营业执照号
     */
   private String regNumber;

    /**
     * 身份证号
     */
   private String operatorCardId;

    /**
     *
     * 负责人名字
     * merchant_store.principal_name
     *
     * @mbg.generated 2017-03-24 18:33:49
     */
    private String principalName;

    /**
     *
     * 负责人手机
     * merchant_store.principal_mobile
     *
     * @mbg.generated 2017-03-24 18:33:49
     */
    private String principalMobile;

    private Byte manageType;

    private Byte certifType;

    private String logoUrl;

    private Long merchantStoreId;



    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getOperatorCardId() {
        return operatorCardId;
    }

    public void setOperatorCardId(String operatorCardId) {
        this.operatorCardId = operatorCardId;
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

    public Byte getManageType() {
        return manageType;
    }

    public void setManageType(Byte manageType) {
        this.manageType = manageType;
    }

    public Byte getCertifType() {
        return certifType;
    }

    public void setCertifType(Byte certifType) {
        this.certifType = certifType;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Long getMerchantStoreId() {
        return merchantStoreId;
    }

    public void setMerchantStoreId(Long merchantStoreId) {
        this.merchantStoreId = merchantStoreId;
    }
}
