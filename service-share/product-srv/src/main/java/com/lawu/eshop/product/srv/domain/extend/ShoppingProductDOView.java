package com.lawu.eshop.product.srv.domain.extend;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zhangyong
 * @date 2017/6/16.
 */
public class ShoppingProductDOView  implements Serializable{

    private static final long serialVersionUID = -352979881347170656L;
    private Long productId;

    private String name;

    private String featureImage;

    private BigDecimal minPrice;

    private  BigDecimal maxPrice;

    private Integer totalSalesVolume;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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
}
