package com.lawu.eshop.pay.sdk.weixin.sdk.common;

import java.io.Serializable;

/**
 * @Description 封装 返回Json 格式数据
 * @author 杨清华
 * @date 2016年4月19日
 */
public class JsonResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;
	private String subCode;
	private String status;

	/**
	 * 数据
	 */
	private Object data;
	
	private Object data1;
	
	/**
	 * 信息
	 */
	private String message;
	/**
	 * 是否成功
	 */
	private boolean success;

	public String getSubCode() {
		return subCode;
	}

	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public Object getData1() {
		return data1;
	}

	public void setData1(Object data1) {
		this.data1 = data1;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public JsonResult() {
		super();
	}

	public JsonResult(Object data, String message, boolean success) {
		this.data = data;
		this.message = message;
		this.success = success;
	}

	public JsonResult(Object data, String message) {
		this.data = data;
		this.message = message;
		this.success = true;
	}

	public JsonResult(Object data) {
		this.data = data;
		this.success = true;
	}
}
