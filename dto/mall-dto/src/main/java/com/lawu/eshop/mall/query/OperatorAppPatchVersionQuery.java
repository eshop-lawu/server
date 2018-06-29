package com.lawu.eshop.mall.query;

import com.lawu.framework.core.page.AbstractPageParam;

/**
 * @author meishuquan
 * @date 2017/12/12.
 */
public class OperatorAppPatchVersionQuery extends AbstractPageParam {

    private Long appVersionId;

    private String sortName;

    private String sortOrder;

    public Long getAppVersionId() {
        return appVersionId;
    }

    public void setAppVersionId(Long appVersionId) {
        this.appVersionId = appVersionId;
    }

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
