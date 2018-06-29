package com.lawu.eshop.property.srv.domain.extend;

/**
 *
 */
public class IncomeMsgExample {
   
	/**
	 * 开始时间
	 */
	private String begin;
	
	/**
	 * 结束时间
	 */
	private String end;

	private int offset;
	private int pageSize;

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}