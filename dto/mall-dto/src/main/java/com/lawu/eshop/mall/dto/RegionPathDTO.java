package com.lawu.eshop.mall.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author jiangxinjun
 * @date 2017年9月13日
 */
public class RegionPathDTO {

    @ApiModelProperty(value = "路径")
    private String path;

    @ApiModelProperty(value = "父级区域")
    private Integer parent_id;

    @ApiModelProperty(value = "区域ID")
    private Integer id;

    @ApiModelProperty(value = "区域名称")
    private String name;

    @ApiModelProperty(value = "层级")
    private Integer level;
    
    public String getPath() {
        return path;
    }
    
    public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
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

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public void setPath(String path) {
		this.path = path;
	}
    
}
