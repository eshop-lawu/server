package com.lawu.eshop.mall.param;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import com.lawu.framework.core.page.AbstractPageParam;

/**
 * @author zhangyong
 * @date 2017/4/10.
 */
public class SuggestionListParam extends AbstractPageParam implements Serializable{

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
