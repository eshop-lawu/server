package com.lawu.eshop.product.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.common.dto.FreightDTO;
import com.lawu.eshop.product.constant.ProductStatusEnum;

/**
 * 购物车产品型号DTO
 * 
 * @author jiangxinjun
 * @createDate 2017年3月30日
 * @updateDate 2018年4月16日
 */
public class ShoppingCartProductModelDTO {

    /**
     * 商品型号id
     */
    private Long id;

    /**
     * 商品型号名称
     */
    private String name;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 特征图片
     */
    private String featureImage;

    /**
     * 商家id
     */
    private Long merchantId;

    /**
     * 原价
     */
    private BigDecimal originalPrice;

    /**
     * 现价
     */
    private BigDecimal price;

    /**
     * 状态
     */
    private ProductStatusEnum status;

    /**
     * 是否支持退换货(0-否1-是)
     */
    private Boolean isAllowRefund;

    /**
     * 库存
     */
    private Integer inventory;

    /**
     * 下架时间
     */
    @JsonFormat
    private Date gmtDown;

    /**
     * 运费计算
     */
    private FreightDTO freight;

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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getFeatureImage() {
        return featureImage;
    }

    public void setFeatureImage(String featureImage) {
        this.featureImage = featureImage;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProductStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ProductStatusEnum status) {
        this.status = status;
    }

    public Boolean getIsAllowRefund() {
        return isAllowRefund;
    }

    public void setIsAllowRefund(Boolean isAllowRefund) {
        this.isAllowRefund = isAllowRefund;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Date getGmtDown() {
        return gmtDown;
    }

    public void setGmtDown(Date gmtDown) {
        this.gmtDown = gmtDown;
    }

    public FreightDTO getFreight() {
        return freight;
    }

    public void setFreight(FreightDTO freight) {
        this.freight = freight;
    }

}
