package com.lawu.eshop.product.param;

import com.lawu.eshop.product.constant.ProductSortEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/4/17.
 */
public class ProductListSearchParam extends AbstractPageParam {

    @ApiModelProperty(value = "商品类别ID", required = false)
    private Integer categoryId;

    @ApiModelProperty(value = "商品子类别ID", required = false)
    private Integer categorySubitemId;

    @ApiModelProperty(value = "商品名称", required = false)
    private String name;

    @ApiModelProperty(value = "品牌", required = false)
    private Integer brandId;

    @ApiModelProperty(value = "发货城市ID", required = false)
    private Integer cityId;

    @ApiModelProperty(value = "是否包邮", required = false)
    private Boolean isExpressFree;

    @ApiModelProperty(value = "是否支持退换货", required = false)
    private Boolean isAllowRefund;

    @ApiModelProperty(value = "最小价格", required = false)
    private Double minPrice;

    @ApiModelProperty(value = "最大价格", required = false)
    private Double maxPrice;

    @ApiModelProperty(value = "排序类型：PRICE_ASC--价格升序，PRICE_DESC--价格降序，SALESVOLUME_DESC--销量降序", required = false)
    private ProductSortEnum sortEnum;

    @ApiModelProperty(value = "是否是品牌", required = false)
    private Boolean isBrand;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCategorySubitemId() {
        return categorySubitemId;
    }

    public void setCategorySubitemId(Integer categorySubitemId) {
        this.categorySubitemId = categorySubitemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Boolean getIsExpressFree() {
        return isExpressFree;
    }

    public void setIsExpressFree(Boolean expressFree) {
        isExpressFree = expressFree;
    }

    public Boolean getIsAllowRefund() {
        return isAllowRefund;
    }

    public void setIsAllowRefund(Boolean allowRefund) {
        isAllowRefund = allowRefund;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public ProductSortEnum getSortEnum() {
        return sortEnum;
    }

    public void setSortEnum(ProductSortEnum sortEnum) {
        this.sortEnum = sortEnum;
    }

    public Boolean getIsBrand() {
        return isBrand;
    }

    public void setIsBrand(Boolean brand) {
        isBrand = brand;
    }
}
