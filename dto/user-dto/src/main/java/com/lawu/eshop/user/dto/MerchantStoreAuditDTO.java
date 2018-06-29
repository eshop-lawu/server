package com.lawu.eshop.user.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.user.constants.MerchantAuditStatusEnum;
import com.lawu.eshop.user.dto.param.MerchantAuditTypeEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2017/4/27.
 */
public class MerchantStoreAuditDTO {

    @ApiModelProperty(name = "id", value = "主键", required = true)
    private Long id;

    @ApiModelProperty(name = "name", value = "店铺名称", required = true)
    private String name;

    @ApiModelProperty(name = "regionPath", value = "省市区", required = true)
    private String regionPath;

    @ApiModelProperty(name = "regionName", value = "省市区名称", required = true)
    private String regionName;

    @ApiModelProperty(name = "address", value = "店铺地址信息", required = true)
    private String address;

    @ApiModelProperty(name = "longitude", value = "经度", required = true)
    private BigDecimal longitude;

    @ApiModelProperty(name = "latitude", value = "纬度", required = true)
    private BigDecimal latitude;

    @ApiModelProperty(name = "industryPath", value = "主营业务", required = true)
    private String industryPath;

    @ApiModelProperty(name = "industryName", value = "主营业务名称", required = true)
    private String industryName;

    @ApiModelProperty(name = "intro", value = "店铺介绍", required = true)
    private String intro;

    @ApiModelProperty(name = "principalName", value = "负责人名字", required = true)
    private String principalName;

    @ApiModelProperty(name = "principalMobile", value = "负责人手机", required = true)
    private String principalMobile;

    @ApiModelProperty(name = "companyName", value = "注册公司名称", required = true)
    private String companyName;

    @ApiModelProperty(name = "regNumber", value = "营业执照号码")
    private String regNumber;

    @ApiModelProperty(name = "companyAddress", value = "经营住所")
    private String companyAddress;

    @ApiModelProperty(name = "licenseIndate", value = "营业执照有效期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date licenseIndate;

    @ApiModelProperty(name = "manageType", value = "经营类型", required = true)
    private MerchantStoreTypeEnum manageType;

    @ApiModelProperty(name = "certifType", value = "证件类型", required = true)
    private CertifTypeEnum certifType;

    @ApiModelProperty(name = "operatorCardId", value = "个人经营者身份证号码")
    private String operatorCardId;

    @ApiModelProperty(name = "operatorName", value = "个人经营者姓名")
    private String operatorName;

    @ApiModelProperty(name = "storeUrl", value = "门店照")
    private List<String> storeUrl;

    @ApiModelProperty(name = "environmentUrl", value = "门店环境照")
    private List<String> environmentUrl;

    @ApiModelProperty(name = "idcardUrl", value = "身份证照")
    private List<String> idcardUrl;

    @ApiModelProperty(name = "licenseUrl", value = "营业执照照片")
    private List<String> licenseUrl;

    @ApiModelProperty(name = "logoUrl", value = "门店logo")
    private String logoUrl;

    @ApiModelProperty(name = "otherUrl", value = "其他照片")
    private List<String> otherUrl;

    @ApiModelProperty(value = "门店ID")
    private Long storeId;

    @ApiModelProperty(value = "商家ID")
    private Long merchantId;

    @ApiModelProperty(value = "状态 MERCHANT_AUDIT_STATUS_UNCHECK:未审核,MERCHANT_AUDIT_STATUS_CHECKED:审核通过,MERCHANT_AUDIT_STATUS_CHECK_FAILED:审核不通过")
    private MerchantAuditStatusEnum statusEnum;

    @ApiModelProperty(value = "审核类型 AUDIT_TYPE_STORE:申请实体店, AUDIT_TYPE_EDIT_INFO:修改资料")
    private MerchantAuditTypeEnum typeEnum;

    @ApiModelProperty(value = "审核人ID")
    private Integer auditorId;

    @ApiModelProperty(value = "审核人")
    private String  auditorName;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "审核时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date auditTime;

    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gmtModified;

    @ApiModelProperty(value = "关键词")
    private String keywords;

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

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public Date getLicenseIndate() {
        return licenseIndate;
    }

    public void setLicenseIndate(Date licenseIndate) {
        this.licenseIndate = licenseIndate;
    }

    public MerchantStoreTypeEnum getManageType() {
        return manageType;
    }

    public void setManageType(MerchantStoreTypeEnum manageType) {
        this.manageType = manageType;
    }

    public CertifTypeEnum getCertifType() {
        return certifType;
    }

    public void setCertifType(CertifTypeEnum certifType) {
        this.certifType = certifType;
    }

    public String getOperatorCardId() {
        return operatorCardId;
    }

    public void setOperatorCardId(String operatorCardId) {
        this.operatorCardId = operatorCardId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public MerchantAuditStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(MerchantAuditStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public MerchantAuditTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(MerchantAuditTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public Integer getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Integer auditorId) {
        this.auditorId = auditorId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

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

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public List<String> getStoreUrl() {
        return storeUrl;
    }

    public void setStoreUrl(List<String> storeUrl) {
        this.storeUrl = storeUrl;
    }

    public List<String> getEnvironmentUrl() {
        return environmentUrl;
    }

    public void setEnvironmentUrl(List<String> environmentUrl) {
        this.environmentUrl = environmentUrl;
    }

    public List<String> getIdcardUrl() {
        return idcardUrl;
    }

    public void setIdcardUrl(List<String> idcardUrl) {
        this.idcardUrl = idcardUrl;
    }

    public List<String> getLicenseUrl() {
        return licenseUrl;
    }

    public void setLicenseUrl(List<String> licenseUrl) {
        this.licenseUrl = licenseUrl;
    }

    public List<String> getOtherUrl() {
        return otherUrl;
    }

    public void setOtherUrl(List<String> otherUrl) {
        this.otherUrl = otherUrl;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
