package com.lawu.eshop.product.dto;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2018/4/18.
 */
public class OperatorProductSpecOptionDTO {
    private Long id;

    private Long productSpecId;

    @ApiModelProperty(value = "规格名称")
    private String specName;

    @ApiModelProperty(value = "规格项名称")
    private String optionName;

    @ApiModelProperty(value = "排序")
    private Integer ordinal;

    @ApiModelProperty(value = "图片")
    private String iconUrl;

    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductSpecId() {
        return productSpecId;
    }

    public void setProductSpecId(Long productSpecId) {
        this.productSpecId = productSpecId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
