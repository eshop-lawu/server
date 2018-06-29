package com.lawu.eshop.product.dto;

import com.lawu.eshop.product.constant.ProductCategoryTypeEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2018/4/16.
 */
public class OperatorProductCategoryListDTO {

    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "商品分类名称")
    private String name;

    @ApiModelProperty(value = "父类")
    private Integer parentId;

    @ApiModelProperty(value = "父类名称")
    private String parentName;

    @ApiModelProperty(value = "图标")
    private String imageUrl;

    @ApiModelProperty(value = "层级")
    private Integer level;

    @ApiModelProperty(value = "排序字段")
    private Integer ordinal;

    @ApiModelProperty(value = "0隐藏1显示")
    private Boolean statue;

    @ApiModelProperty(value = "是否虚拟商品,0否 1是")
    private Boolean isVirtual;

    private ProductCategoryTypeEnum type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    public Boolean getStatue() {
        return statue;
    }

    public void setStatue(Boolean statue) {
        this.statue = statue;
    }

    public Boolean getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(Boolean virtual) {
        isVirtual = virtual;
    }

    public ProductCategoryTypeEnum getType() {
        return type;
    }

    public void setType(ProductCategoryTypeEnum type) {
        this.type = type;
    }
}
