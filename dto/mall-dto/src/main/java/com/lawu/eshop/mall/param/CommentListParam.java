package com.lawu.eshop.mall.param;

import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2017/4/7.
 */
public class CommentListParam extends AbstractPageParam {

    @ApiModelProperty(value = "开始时间")
    private String beginDate;

    @ApiModelProperty(value = "结束时间")
    private String endDate;

    @ApiModelProperty(value = "排序字段")
    private String sortName;

    @ApiModelProperty(value = "排序方式")
    private String sortOrder;

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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
