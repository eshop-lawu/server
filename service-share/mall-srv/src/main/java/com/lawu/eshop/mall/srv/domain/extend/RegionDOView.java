package com.lawu.eshop.mall.srv.domain.extend;

import java.io.Serializable;

/**
 * @author zhangyong
 * @date 2017/4/10.
 */
public class RegionDOView implements Serializable{
    private static final long serialVersionUID = 1L;
    /**
     *
     * 区域ID
     * region.id
     *
     * @mbg.generated 2017-03-22 02:41:06
     */
    private Integer id;

    /**
     *
     * 父级区域
     * region.parent_id
     *
     * @mbg.generated 2017-03-22 02:41:06
     */
    private Integer parentId;

    /**
     *
     * 路径
     * region.path
     *
     * @mbg.generated 2017-03-22 02:41:06
     */
    private String path;

    /**
     *
     * 层级
     * region.level
     *
     * @mbg.generated 2017-03-22 02:41:06
     */
    private Byte level;

    /**
     *
     * 区域名称
     * region.name
     *
     * @mbg.generated 2017-03-22 02:41:06
     */
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
