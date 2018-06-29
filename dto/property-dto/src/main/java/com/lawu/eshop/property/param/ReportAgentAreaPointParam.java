package com.lawu.eshop.property.param;

/**
 * @author meishuquan
 * @date 2017/11/15.
 */
public class ReportAgentAreaPointParam {

    private Integer offset;

    private Integer pageSize;

    private String beginDate;

    private String endDate;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

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
}
