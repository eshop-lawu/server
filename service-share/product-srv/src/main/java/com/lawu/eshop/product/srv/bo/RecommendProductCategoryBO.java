package com.lawu.eshop.product.srv.bo;

import java.util.Date;

/**
 * @author meishuquan
 * @date 2017/4/10.
 */
public class RecommendProductCategoryBO {

    private Long id;

    private Integer categoryId;

    private String categoryName;

    private Date gmtCreate;

    private Boolean isvisible;

    private Boolean isHot;

    private Byte ordinal;

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
