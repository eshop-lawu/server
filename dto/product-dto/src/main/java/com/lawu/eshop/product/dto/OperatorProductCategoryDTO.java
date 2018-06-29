package com.lawu.eshop.product.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2018/3/14.
 */
public class OperatorProductCategoryDTO {
    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "分类ID")
    private Integer categoryId;

    @ApiModelProperty(value = "名称")
    private String categoryName;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    @ApiModelProperty(value = "是否可见")
    private Boolean isvisible;

    @ApiModelProperty(value = "是否热门")
    private Boolean isHot;

    @ApiModelProperty(value = "排序字段")
    private Byte ordinal;

    @ApiModelProperty(value = "图片")
    private String imagePath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Boolean getIsvisible() {
        return isvisible;
    }

    public void setIsvisible(Boolean isvisible) {
        this.isvisible = isvisible;
    }

    public Boolean getIsHot() {
        return isHot;
    }

    public void setIsHot(Boolean hot) {
        isHot = hot;
    }

    public Byte getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Byte ordinal) {
        this.ordinal = ordinal;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
