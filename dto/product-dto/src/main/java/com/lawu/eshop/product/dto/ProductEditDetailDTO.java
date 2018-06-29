package com.lawu.eshop.product.dto;

import com.lawu.eshop.product.param.EditProductModelEntityParam;
import com.lawu.eshop.product.param.FreightParam;
import com.lawu.eshop.product.param.ProductSpecCustomParam;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

public class ProductEditDetailDTO {

    @ApiModelProperty(value = "商品ID")
    private Long id;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "类目ID字符串，用/分隔")
    private String categoryIdPath;

    @ApiModelProperty(value = "类目名称字符串，用/分隔")
    private String categoryNamePath;

    @ApiModelProperty(value = "品牌ID")
    private Long brandId;

    @ApiModelProperty(value = "品牌名称")
    private String brandName;

    @ApiModelProperty(value = "商品型号信息")
    private List<EditProductModelEntityParam> productModelParam;

    @ApiModelProperty(value = "商品最小价格")
    private BigDecimal minPrice;

    @ApiModelProperty(value = "商品最大价格")
    private BigDecimal maxPrice;

    @ApiModelProperty(value = "商品总库存")
    private Integer totalInventory;

    @ApiModelProperty(value = "商品头部图片")
    private List<String> titleImages;

    @ApiModelProperty(value = "商品描述")
    private String content;

    @ApiModelProperty(value = "商品描述图片")
    private List<String> detailImages;

    @ApiModelProperty(value = "商品图片描述内容")
    private List<String> detailContent;

    @ApiModelProperty(value = "是否退换货")
    private Boolean isAllowRefund;

    @ApiModelProperty(value = "是否包邮")
    private Boolean isExpressFree;

    @ApiModelProperty(value = "邮费信息")
    private FreightParam freightParam;

    @ApiModelProperty(value = "省ID")
    private Long provinceId;

    @ApiModelProperty(value = "市ID")
    private Long cityId;

    @ApiModelProperty(value = "发货地名称")
    private String delivery;

    @ApiModelProperty(value = "商家自定义规格")
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

    public String getCategoryIdPath() {
        return categoryIdPath;
    }

    public void setCategoryIdPath(String categoryIdPath) {
        this.categoryIdPath = categoryIdPath;
    }

    public String getCategoryNamePath() {
        return categoryNamePath;
    }

    public void setCategoryNamePath(String categoryNamePath) {
        this.categoryNamePath = categoryNamePath;
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

    public Boolean getIsAllowRefund() {
        return isAllowRefund;
    }

    public void setIsAllowRefund(Boolean isAllowRefund) {
        this.isAllowRefund = isAllowRefund;
    }

    public Boolean getIsExpressFree() {
        return isExpressFree;
    }

    public void setIsExpressFree(Boolean isExpressFree) {
        this.isExpressFree = isExpressFree;
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

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }
}
