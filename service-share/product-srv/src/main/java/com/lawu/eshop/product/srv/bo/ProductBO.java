package com.lawu.eshop.product.srv.bo;

/**
 * @author meishuquan
 * @date 2017/6/16.
 */
public class ProductBO {

    private Long id;

    private Integer totalSalesVolume;

    private Double maxPrice;

    private Double minPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotalSalesVolume() {
        return totalSalesVolume;
    }

    public void setTotalSalesVolume(Integer totalSalesVolume) {
        this.totalSalesVolume = totalSalesVolume;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }
}
