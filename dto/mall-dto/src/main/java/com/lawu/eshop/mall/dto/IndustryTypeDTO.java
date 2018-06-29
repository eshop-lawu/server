package com.lawu.eshop.mall.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author meishuquan
 * @date 2017/4/5.
 */
public class IndustryTypeDTO {

    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "父ID")
    private Short parentId;

    @ApiModelProperty(value = "路径")
    private String path;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "图片路径")
    private String imageUrl;

    @ApiModelProperty(value = "二级行业")
    List<IndustryTypeDTO> industryTypeDTOS;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getParentId() {
        return parentId;
    }

    public void setParentId(Short parentId) {
        this.parentId = parentId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<IndustryTypeDTO> getIndustryTypeDTOS() {
        return industryTypeDTOS;
    }

    public void setIndustryTypeDTOS(List<IndustryTypeDTO> industryTypeDTOS) {
        this.industryTypeDTOS = industryTypeDTOS;
    }
}
