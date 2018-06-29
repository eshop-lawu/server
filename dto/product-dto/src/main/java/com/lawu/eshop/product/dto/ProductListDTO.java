package com.lawu.eshop.product.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.product.constant.ProductPositionEnum;
import com.lawu.eshop.product.constant.ProductStatusEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/4/16.
 */
public class ProductListDTO {

    @ApiModelProperty(value = "ID", required = true)
    private Long id;

    @ApiModelProperty(value = "名称", required = true)
    private String name;

    @ApiModelProperty(value = "图片", required = true)
    private String featureImage;

    @ApiModelProperty(value = "PRODUCT_STATUS_DEL--删除，PRODUCT_STATUS_UP--上架，PRODUCT_STATUS_DOWN--下架，PRODUCT_STATUS_TEMP--草稿", required = true)
    private ProductStatusEnum statusEnum;

    @ApiModelProperty(value = "商品位置：SHOPWINDOW--橱窗", required = true)
    private ProductPositionEnum positionEnum;

    @ApiModelProperty(value = "销量", required = true)
    private Integer totalSalesVolume;

    @ApiModelProperty(value = "库存", required = true)
    private Integer totalInventory;

    @ApiModelProperty(value = "上架时间", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gmtUp;

    @ApiModelProperty(value = "下架时间", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gmtDown;

    @ApiModelProperty(value = "添加时间", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gmtCreate;

    @ApiModelProperty(value = "最低价", required = true)
    private BigDecimal minPrice;

    @ApiModelProperty(value = "收藏人数", required = true)
    private Integer totalFavorite;

    @ApiModelProperty(value = "商品类别", required = true)
    private String productCategory;

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

    public String getFeatureImage() {
        return featureImage;
    }

    public void setFeatureImage(String featureImage) {
        this.featureImage = featureImage;
    }

    public ProductStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(ProductStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public ProductPositionEnum getPositionEnum() {
        return positionEnum;
    }

    public void setPositionEnum(ProductPositionEnum positionEnum) {
        this.positionEnum = positionEnum;
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

    public Date getGmtUp() {
        return gmtUp;
    }

    public void setGmtUp(Date gmtUp) {
        this.gmtUp = gmtUp;
    }

    public Date getGmtDown() {
        return gmtDown;
    }

    public void setGmtDown(Date gmtDown) {
        this.gmtDown = gmtDown;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getTotalFavorite() {
        return totalFavorite;
    }

    public void setTotalFavorite(Integer totalFavorite) {
        this.totalFavorite = totalFavorite;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
}
