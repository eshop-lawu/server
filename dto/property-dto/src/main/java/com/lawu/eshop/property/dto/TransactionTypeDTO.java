package com.lawu.eshop.property.dto;

import java.io.Serializable;

/**
 * @author Sunny
 * @date 2017/4/19
 */
public class TransactionTypeDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String value;
	
	private String name;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
