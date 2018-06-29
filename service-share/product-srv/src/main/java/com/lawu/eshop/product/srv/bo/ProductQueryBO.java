package com.lawu.eshop.product.srv.bo;

import com.lawu.eshop.product.constant.ProductStatusEnum;

/**
 * 产品类别BO
 *
 * @author Yangqh
 * @date 2017/3/22
 */
public class ProductQueryBO {

    private Long id;
    private Long merchantId;
    private String category;
    private String name;
    private ProductStatusEnum status;
    private String gmtCreate;
    private String totalInventory;
    private String totalSalesVolume;
    private String totalFavorite;
    private String featureImage;
    private String spec;
    private String minPrice;
    private String remark;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ProductStatusEnum status) {
        this.status = status;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getFeatureImage() {
        return featureImage;
    }

    public void setFeatureImage(String featureImage) {
        this.featureImage = featureImage;
    }

    public String getTotalInventory() {
        return totalInventory;
    }

    public void setTotalInventory(String totalInventory) {
        this.totalInventory = totalInventory;
    }

    public String getTotalSalesVolume() {
        return totalSalesVolume;
    }

    public void setTotalSalesVolume(String totalSalesVolume) {
        this.totalSalesVolume = totalSalesVolume;
    }

    public String getTotalFavorite() {
        return totalFavorite;
    }

    public void setTotalFavorite(String totalFavorite) {
        this.totalFavorite = totalFavorite;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    

}
