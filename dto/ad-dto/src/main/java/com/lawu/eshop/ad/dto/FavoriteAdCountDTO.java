package com.lawu.eshop.ad.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2018/6/7.
 */
public class FavoriteAdCountDTO {

    @ApiModelProperty(value = "广告数量")
    private Integer adCount;

    @ApiModelProperty(value = "咻一咻数量")
    private Integer epraiseCount;

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
}
