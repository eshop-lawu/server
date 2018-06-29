package com.lawu.eshop.mall.srv.bo;

import java.util.List;

/**
 * @author meishuquan
 * @date 2017/4/5.
 */
public class IndustryTypeBO {

    private Integer id;

    private Short parentId;

    private String path;

    private String name;

    private String imageUrl;

    List<IndustryTypeBO> industryTypeBOList;

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

    public List<IndustryTypeBO> getIndustryTypeBOList() {
        return industryTypeBOList;
    }

    public void setIndustryTypeBOList(List<IndustryTypeBO> industryTypeBOList) {
        this.industryTypeBOList = industryTypeBOList;
    }
}
