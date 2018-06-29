package com.lawu.eshop.mall.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2018/6/7.
 */
public class FavoriteCountDTO {
    @ApiModelProperty(value = "收藏广告数量")
    private Integer adCount;

    @ApiModelProperty(value = "收藏咻一咻数量")
    private Integer epraiseCount;

    @ApiModelProperty(value = "收藏商品数量")
    private Integer productCount;

    @ApiModelProperty(value = "关注店铺数量")
    private Integer storeCount;

    public Integer getAdCount() {
        return adCount;
    }

    public void setAdCount(Integer adCount) {
        this.adCount = adCount;
    }

    public Integer getEpraiseCount() {
        return epraiseCount;
    }

    public void setEpraiseCount(Integer epraiseCount) {
        this.epraiseCount = epraiseCount;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Integer getStoreCount() {
        return storeCount;
    }

    public void setStoreCount(Integer storeCount) {
        this.storeCount = storeCount;
    }
}
