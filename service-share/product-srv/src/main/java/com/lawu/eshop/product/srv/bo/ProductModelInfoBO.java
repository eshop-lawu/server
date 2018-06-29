package com.lawu.eshop.product.srv.bo;

import java.math.BigDecimal;

/**
 * @author meishuquan
 * @date 2018/4/25.
 */
public class ProductModelInfoBO {

    private Long id;

    private String modelKey;

    private BigDecimal price;

    private Integer inventory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelKey() {
        return modelKey;
    }

    public void setModelKey(String modelKey) {
        this.modelKey = modelKey;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }
}
