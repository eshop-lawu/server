package com.lawu.eshop.product.srv.bo;

import com.lawu.eshop.product.param.EditProductModelEntityParam;
import com.lawu.eshop.product.param.FreightParam;
import com.lawu.eshop.product.param.ProductSpecCustomParam;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

public class ProductEditDetailBO {

    private Long id;

    private String name;

    private Integer categoryId;

    private String categoryName;

    private Long categorySubitemId;

    private String categorySubitemName;

    private Long brandId;

    private String brandName;

    private List<EditProductModelEntityParam> productModelParam;

    private BigDecimal minPrice;

    private BigDecimal maxPrice;

    private Integer totalInventory;

    private List<String> titleImages;

    private String content;

    private List<String> detailImages;

    private List<String> detailContent;

    private Boolean isAllowRefund;

    private Boolean isExpressFree;

    private FreightParam freightParam;

    private Long provinceId;

    private Long cityId;

    private String provinceName;

    private String cityName;

    private List<ProductSpecCustomParam> productSpecCustomParam;

    public List<ProductSpecCustomParam> getProductSpecCustomParam() {
        return productSpecCustomParam;
    }

    public void setProductSpecCustomParam(List<ProductSpecCustomParam> productSpecCustomParam) {
        this.productSpecCustomParam = productSpecCustomParam;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getCategorySubitemId() {
        return categorySubitemId;
    }

    public void setCategorySubitemId(Long categorySubitemId) {
        this.categorySubitemId = categorySubitemId;
    }

    public String getCategorySubitemName() {
        return categorySubitemName;
    }

    public void setCategorySubitemName(String categorySubitemName) {
        this.categorySubitemName = categorySubitemName;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public List<EditProductModelEntityParam> getProductModelParam() {
        return productModelParam;
    }

    public void setProductModelParam(List<EditProductModelEntityParam> productModelParam) {
        this.productModelParam = productModelParam;
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

    public Integer getTotalInventory() {
        return totalInventory;
    }

    public void setTotalInventory(Integer totalInventory) {
        this.totalInventory = totalInventory;
    }

    public List<String> getTitleImages() {
        return titleImages;
    }

    public void setTitleImages(List<String> titleImages) {
        this.titleImages = titleImages;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getDetailImages() {
        return detailImages;
    }

    public void setDetailImages(List<String> detailImages) {
        this.detailImages = detailImages;
    }

    public List<String> getDetailContent() {
        return detailContent;
    }

    public void setDetailContent(List<String> detailContent) {
        this.detailContent = detailContent;
    }

    public Boolean getAllowRefund() {
        return isAllowRefund;
    }

    public void setAllowRefund(Boolean allowRefund) {
        isAllowRefund = allowRefund;
    }

    public Boolean getExpressFree() {
        return isExpressFree;
    }

    public void setExpressFree(Boolean expressFree) {
        isExpressFree = expressFree;
    }

    public FreightParam getFreightParam() {
        return freightParam;
    }

    public void setFreightParam(FreightParam freightParam) {
        this.freightParam = freightParam;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
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
}
