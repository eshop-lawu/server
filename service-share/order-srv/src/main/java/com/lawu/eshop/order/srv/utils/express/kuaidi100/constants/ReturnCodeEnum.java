package com.lawu.eshop.order.srv.utils.express.kuaidi100.constants;

/**
 * 快递100查询状态码
 * 
 * @author jiangxinjun
 * @date 2017年9月21日
 */
public enum ReturnCodeEnum {

    /**
     * 提交的数据不完整，或者贵公司没授权
     */
	DATA_INCOMPLETE_OR_NOT_AUTHORIZED("400", "提交的数据不完整，或者贵公司没授权"),

	/**
	 * 表示查询失败，或没有POST提交
	 */
	SEARCH_FAILED("500", "表示查询失败，或没有POST提交"),
	
	/**
	 * 服务器错误，快递100服务器压力过大或需要升级，暂停服务
	 */
	SERVER_ERROR("501", "服务器错误，快递100服务器压力过大或需要升级，暂停服务"),
	
	/**
	 * 服务器繁忙，查询并发量超出约定额度
	 */
	SERVER_IS_BUSY("502", "服务器繁忙，查询并发量超出约定额度"),
	
	/**
	 * 验证签名失败
	 */
	VERIFY_SIGNATURE_FAILED("503", "验证签名失败");

	private String value;
	
	private String label;
	
	ReturnCodeEnum(String value, String label) {
		this.value = value;
		this.label = label;
	}

	public String getValue() {
		return value;
	}
	
	public String getLabel() {
		return label;
	}

	public static ReturnCodeEnum getEnum(String value) {
		for (ReturnCodeEnum item : ReturnCodeEnum.values()) {
			if (item.getValue().equals(value)) {
				return item;
			}
		}
		return null;
	}
}
