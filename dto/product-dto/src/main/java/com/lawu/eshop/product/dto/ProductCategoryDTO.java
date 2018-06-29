package com.lawu.eshop.product.dto;


import io.swagger.annotations.ApiModelProperty;

/**
 * @author Yangqh
 * @date 2017/3/13
 */
public class ProductCategoryDTO {

    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "父类ID")
    private Integer parentId;

    @ApiModelProperty(value = "家族图谱")
    private String path;

    @ApiModelProperty(value = "层级")
    private Integer level;

    @ApiModelProperty(value = "是否虚拟商品,0否 1是")
    private Boolean isVirtual;

    @ApiModelProperty(value = "顺序排序")
    private Integer ordinal;

    @ApiModelProperty(value = "图片路径")
    private String imageUrl;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(Boolean virtual) {
        isVirtual = virtual;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
