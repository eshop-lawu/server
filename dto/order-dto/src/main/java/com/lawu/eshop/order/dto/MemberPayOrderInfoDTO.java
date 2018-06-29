package com.lawu.eshop.order.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.order.constants.EvaluationEnum;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhangyong
 * @date 2017/6/23.
 */
public class MemberPayOrderInfoDTO {
    @ApiModelProperty(value = "买单ID")
    private Long id;

    @ApiModelProperty(value = "实际消费")
    private BigDecimal actualAmount;

    @ApiModelProperty(value = "优惠金额")
    private BigDecimal favoredAmount;

    @ApiModelProperty(value = "消费总额")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "EVALUATION_SUCCESS：已评，UN_EVALUATION：未评")
    private EvaluationEnum evaluationEnum;

    @ApiModelProperty(value = "商家ID")
    private Long merchantId;

    @ApiModelProperty(value = "买单时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

    @ApiModelProperty(value = "商家名称")
    private String name;

    @ApiModelProperty(value = "门店LOGO")
    private String storeUrl;

    @ApiModelProperty(value = "负责人手机号")
    private String principalMobile;

    @ApiModelProperty(value = "评分")
    private Byte grade;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "买单编号")
    private String orderNum;

    @ApiModelProperty(value = "门店Id")
    private Long merchantStoreId;

    @ApiModelProperty(value = "省市区")
    private String regionName;

    @ApiModelProperty(value = "商家编号")
    private String userNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public BigDecimal getFavoredAmount() {
        return favoredAmount;
    }

    public void setFavoredAmount(BigDecimal favoredAmount) {
        this.favoredAmount = favoredAmount;
    }

    public EvaluationEnum getEvaluationEnum() {
        return evaluationEnum;
    }

    public void setEvaluationEnum(EvaluationEnum evaluationEnum) {
        this.evaluationEnum = evaluationEnum;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStoreUrl() {
        return storeUrl;
    }

    public void setStoreUrl(String storeUrl) {
        this.storeUrl = storeUrl;
    }

    public String getPrincipalMobile() {
        return principalMobile;
    }

    public void setPrincipalMobile(String principalMobile) {
        this.principalMobile = principalMobile;
    }

    public Byte getGrade() {
        return grade;
    }

    public void setGrade(Byte grade) {
        this.grade = grade;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getMerchantStoreId() {
        return merchantStoreId;
    }

    public void setMerchantStoreId(Long merchantStoreId) {
        this.merchantStoreId = merchantStoreId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }
}
