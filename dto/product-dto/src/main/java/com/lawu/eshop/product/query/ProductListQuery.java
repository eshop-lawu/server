package com.lawu.eshop.product.query;

import com.lawu.eshop.product.constant.ProductListSortEnum;
import com.lawu.eshop.product.constant.ProductQueryTypeEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiParam;

/**
 * @author meishuquan
 * @date on 2018/4/16.
 */
public class ProductListQuery extends AbstractPageParam {

    @ApiParam(value = "商品名称", required = false)
    private String name;

    @ApiParam(value = "商品类别", required = false)
    private Integer categoryId;

    @ApiParam(value = "商品子类别", required = false)
    private Long categorySubitemId;

    @ApiParam(value = "排序类型:PRODUCT_STATUS_UP--上架时间，PRODUCT_STATUS_DOWN--下架时间，TOTAL_INVENTORY--库存，TOTAL_SALES_VOLUME--销量", required = false)
    private ProductListSortEnum sortEnum;

    @ApiParam(value = "查询类型:SELLING--出售，WAREHOUSE--仓库，SHOPWINDOW--橱窗，SOLDOUT--售完", required = true)
    private ProductQueryTypeEnum queryTypeEnum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCategorySubitemId() {
        return categorySubitemId;
    }

    public void setCategorySubitemId(Long categorySubitemId) {
        this.categorySubitemId = categorySubitemId;
    }

    public ProductListSortEnum getSortEnum() {
        return sortEnum;
    }

    public void setSortEnum(ProductListSortEnum sortEnum) {
        this.sortEnum = sortEnum;
    }

    public ProductQueryTypeEnum getQueryTypeEnum() {
        return queryTypeEnum;
    }

    public void setQueryTypeEnum(ProductQueryTypeEnum queryTypeEnum) {
        this.queryTypeEnum = queryTypeEnum;
    }
}
