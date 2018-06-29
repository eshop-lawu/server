package com.lawu.eshop.property.srv.domain.extend;

import java.util.Date;

/**
 * 用户收入支出查询DO
 * 
 * @author jiangxinjun
 * @createDate 2017年7月3日
 * @updateDate 2017年11月16日
 */
public class UserIncomeExpenditureExample {
   
	/**
	 * 开始时间
	 */
	private Date start;
	
	/**
	 * 结束时间
	 */
	private Date end;
	
	/**
     * 偏移量
     */
    private Integer offset;
    
    /**
     * 每页大小
     */
    private Integer pageSize;
	
	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
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