package com.lawu.eshop.mall.dto;

import com.lawu.eshop.mall.constants.RegionLevelEnum;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author zhangyong
 * @date 2017/4/10.
 */
public class RegionDTO {

    @ApiModelProperty(value = "路径")
    private String path;

    @ApiModelProperty(value = "父级区域")
    private Integer parentId;

    @ApiModelProperty(value = "区域ID")
    private Integer id;

    @ApiModelProperty(value = "区域名称")
    private String name;

    @ApiModelProperty(value = "层级")
    private RegionLevelEnum levelEnum;

    @ApiModelProperty(value = "经度")
    private BigDecimal longitude;

    @ApiModelProperty(value = "纬度")
    private BigDecimal latitude;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

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

    public RegionLevelEnum getLevelEnum() {
        return levelEnum;
    }

    public void setLevelEnum(RegionLevelEnum levelEnum) {
        this.levelEnum = levelEnum;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
}
