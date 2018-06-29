package com.lawu.eshop.product.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author meishuquan
 * @date 2018/4/16.
 */
public class ProductListBO {

    private Long id;

    private String name;

    private String featureImage;

    private Byte status;

    private Byte position;

    private Integer totalSalesVolume;

    private Integer totalInventory;

    private Date gmtUp;

    private Date gmtDown;

    private Date gmtCreate;

    private BigDecimal minPrice;

    private Integer totalFavorite;

    private String categoryName;

    private String categorySubitemName;

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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getPosition() {
        return position;
    }

    public void setPosition(Byte position) {
        this.position = position;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategorySubitemName() {
        return categorySubitemName;
    }

    public void setCategorySubitemName(String categorySubitemName) {
        this.categorySubitemName = categorySubitemName;
    }
}
