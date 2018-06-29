package com.lawu.eshop.mall.query;

import com.lawu.framework.core.page.AbstractPageParam;

/**
 * @author meishuquan
 * @date 2018/3/1.
 */
public class OperatorWindowMessageQuery extends AbstractPageParam {

    private String sortName;

    private String sortOrder;

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }
}
