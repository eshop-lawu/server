package com.lawu.eshop.operator.api.result;

import java.util.ArrayList;
import java.util.List;

import com.lawu.framework.core.page.Page;

public class TableJson<T> {
	private long total;
	private List<T> rows;
	private String messages;

	public TableJson(Page<T> page) {
		if (null == page || page.getRecords().isEmpty()) {
			this.setRows(new ArrayList<T>());
			this.setTotal(0);
		} else {
			this.setRows(page.getRecords());
			this.setTotal(page.getTotalCount());
		}
		this.setMessages("success");
	}

	/**
	 * @return the total
	 */
	public long getTotal() {
		return total;
	}

	/**
	 * @return the rows
	 */
	public List<T> getRows() {
		return rows;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(long total) {
		this.total = total;
	}

	/**
	 * @param rows
	 *            the rows to set
	 */
	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	/**
	 * @return the messages
	 */
	public String getMessages() {
		return messages;
	}

	/**
	 * @param messages
	 *            the messages to set
	 */
	public void setMessages(String messages) {
		this.messages = messages;
	}

}
