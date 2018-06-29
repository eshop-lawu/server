package com.lawu.eshop.product.param;

import com.lawu.framework.core.page.AbstractPageParam;

/**
 * @author zhangyong
 * @date 2018/4/16.
 */
public class OperatorProductCategoryParam extends AbstractPageParam {

    private Byte level;

    private String name;

    private Integer parentId;

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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
