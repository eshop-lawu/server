package com.lawu.eshop.product.srv.bo;

import java.util.List;

import com.lawu.eshop.product.srv.domain.extend.ProductCategoryItemDOView;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/4/23.
 */
public class ProductCategoryTierBO extends ProductCategoryItemDOView {

    @ApiModelProperty(value = "商品子类别")
    private List<ProductCategoryItemDOView> itemDOViews;

    public List<ProductCategoryItemDOView> getItemDOViews() {
        return itemDOViews;
    }

    public void setItemDOViews(List<ProductCategoryItemDOView> itemDOViews) {
        this.itemDOViews = itemDOViews;
    }
}
