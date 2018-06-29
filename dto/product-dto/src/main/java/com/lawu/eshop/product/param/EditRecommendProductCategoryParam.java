package com.lawu.eshop.product.param;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2017/4/24.
 */
public class EditRecommendProductCategoryParam {

    @ApiModelProperty(value = "类别ID", required = true)
    private Integer categoryId;

    @ApiModelProperty(value = "类别名称", required = true)
    private String categoryName;

    @ApiModelProperty(value = "是否可见，false--不可见，true--可见", required = true)
    private Boolean isvisible;

    private Boolean isHot;

    @ApiModelProperty(value = "类别图标", required = true)
    private String imagePath;

    @ApiModelProperty(value = "排序，小的排前面", required = true)
    private Byte ordinal;

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
