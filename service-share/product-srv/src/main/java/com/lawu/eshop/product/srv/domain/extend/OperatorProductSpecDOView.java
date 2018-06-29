package com.lawu.eshop.product.srv.domain.extend;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangyong
 * @date 2018/4/17.
 */
public class OperatorProductSpecDOView implements Serializable{

    private static final long serialVersionUID = 5099977806926992096L;
    private Long id;

    private Integer productCategoryId;

    private String categoryName;

    private String specName;

    private Byte ordinal;

    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public Byte getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Byte ordinal) {
        this.ordinal = ordinal;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
