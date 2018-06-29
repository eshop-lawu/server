package com.lawu.eshop.product.param;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2018/4/16.
 */
public class ProductCategoryParam {

    @ApiModelProperty(value = "父类ID")
    private Integer parentId;

    private String name;

    @ApiModelProperty(value = "图标")
    private String imageUrl;

    @ApiModelProperty(value = "层级")
    private Byte level;

    @ApiModelProperty(value = "排序")
    private Byte ordinal;

    @ApiModelProperty(value = "是否虚拟产品")
    private Boolean isVirtual;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public Byte getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Byte ordinal) {
        this.ordinal = ordinal;
    }

    public Boolean getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(Boolean virtual) {
        isVirtual = virtual;
    }
}
