package com.lawu.eshop.product.srv.bo;

import java.math.BigDecimal;

/**
 * @author meishuquan
 * @date 2017/4/6.
 */
public class ProductRecommendBO {

    private String featureImage;

    private String content;

    private String name;

    private BigDecimal originalPrice;

    private BigDecimal price;

    public String getFeatureImage() {
        return featureImage;
    }

    public void setFeatureImage(String featureImage) {
        this.featureImage = featureImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
