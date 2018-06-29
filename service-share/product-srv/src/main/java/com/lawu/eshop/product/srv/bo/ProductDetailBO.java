package com.lawu.eshop.product.srv.bo;

import java.math.BigDecimal;
import java.util.List;

import com.lawu.eshop.product.dto.MemberProductImageDetailDTO;

/**
 * @author meishuquan
 * @date 2018/4/17.
 */
public class ProductDetailBO {

    private Long id;

    private Long merchantId;

    private Integer categoryId;

    private Long categorySubitemId;

    private String name;

    private String content;

    private String featureImage;

    private String spec;

    private BigDecimal minPrice;

    private BigDecimal maxPrice;

    private Integer totalSalesVolume;

    private Integer totalInventory;

    private Boolean isAllowRefund;

    private List<String> imagesHeadUrl;

    private List<MemberProductImageDetailDTO> imageDetail;

    private List<ProductModelInfoBO> modelInfo;

    private Byte status;

    private String merchantNum;

    private String provinceName;

    private String cityName;

    private String freight;

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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCategorySubitemId() {
        return categorySubitemId;
    }

    public void setCategorySubitemId(Long categorySubitemId) {
        this.categorySubitemId = categorySubitemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFeatureImage() {
        return featureImage;
    }

    public void setFeatureImage(String featureImage) {
        this.featureImage = featureImage;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getTotalSalesVolume() {
        return totalSalesVolume;
    }

    public void setTotalSalesVolume(Integer totalSalesVolume) {
        this.totalSalesVolume = totalSalesVolume;
    }

    public Integer getTotalInventory() {
        return totalInventory;
    }

    public void setTotalInventory(Integer totalInventory) {
        this.totalInventory = totalInventory;
    }


    public List<String> getImagesHeadUrl() {
        return imagesHeadUrl;
    }

    public Boolean getIsAllowRefund() {
        return isAllowRefund;
    }

    public void setIsAllowRefund(Boolean allowRefund) {
        isAllowRefund = allowRefund;
    }

    public void setImagesHeadUrl(List<String> imagesHeadUrl) {
        this.imagesHeadUrl = imagesHeadUrl;
    }

    public List<MemberProductImageDetailDTO> getImageDetail() {
        return imageDetail;
    }

    public void setImageDetail(List<MemberProductImageDetailDTO> imageDetail) {
        this.imageDetail = imageDetail;
    }

    public List<ProductModelInfoBO> getModelInfo() {
        return modelInfo;
    }

    public void setModelInfo(List<ProductModelInfoBO> modelInfo) {
        this.modelInfo = modelInfo;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getMerchantNum() {
        return merchantNum;
    }

    public void setMerchantNum(String merchantNum) {
        this.merchantNum = merchantNum;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }
}
