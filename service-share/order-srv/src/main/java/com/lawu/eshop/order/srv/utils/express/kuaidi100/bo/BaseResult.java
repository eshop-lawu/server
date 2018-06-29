package com.lawu.eshop.order.srv.utils.express.kuaidi100.bo;

/**
 * 快递100基础接口封装数据
 * 
 * @author jiangxinjun
 * @date 2017年9月27日
 */
public class BaseResult {
	
	/**
	 * 查询结果：false表示查询失败
	 */
	private Boolean result;
	
	/**
	 * 失败的代号
	 */
	private String returnCode;
	
	/**
	 * 失败内容表述
	 */
	private String message;

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}
	
	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
