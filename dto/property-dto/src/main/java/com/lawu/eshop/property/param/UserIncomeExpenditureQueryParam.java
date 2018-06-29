package com.lawu.eshop.property.param;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 查询用户收入支出
 * 
 * @author Sunny
 * @createDate 2017年7月3日
 * @updateDate 2017年11月16日
 */
public class UserIncomeExpenditureQueryParam {
	
	/**
	 * 查询时间
	 */
    @NotNull(message = "查询日期不能为空")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	/**
	 * 偏移量
	 */
    @NotNull(message = "偏移量不能为空")
	private Integer offset;
	
	/**
	 * 每页大小
	 */
    @NotNull(message = "每页大小不能为空")
	private Integer pageSize;
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

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
	
}
