package com.lawu.eshop.order.srv.utils.express.kdniao.constants;

/**
 * 返回编码枚举
 * 
 * @author jiangxinjun
 * @date 2017年9月5日
 */
public enum CodeEnum {
	
    /**
     * 成功
     */
	SUCCESS(100, "成功"),
	
	/**
	 * 缺少必要参数
	 */
	MISSING_REQUIRED_PARAMETERS(101, "缺少必要参数"),
	
	/**
	 * 校验问题
	 */
	CHECK_PROBLEM(102, "校验问题"),
	
	/**
	 * 格式问题
	 */
	FORMAT_PROBLEM(103, "格式问题"),
	
	/**
	 * 用户问题
	 */
	USER_PROBLEM(104, "用户问题"),
	
	/**
	 * 其他错误
	 */
	OTHER_ERRORS(105, "其他错误"),
	
	/**
	 * RequestData格式有误
	 */
	REQUESTDATA_FORMAT_IS_WRONG(401, "RequestData格式有误"),
	
	/**
	 * 缺少快递单号
	 */
	NO_EXPRESS_SINGLE_NUMBER(402, "缺少快递单号"),
	
	/**
	 * 快递单号有特殊字符
	 */
	SPECIAL_CHARACTERS_FOR_EXPRESS_NUMBER(403, "快递单号有特殊字符"),
	
	/**
	 * 快递单号长度不符
	 */
	EXPRESS_SINGLE_NUMBER_LENGTH_DOES_NOT_MATCH(404, "快递单号长度不符"),
	
	/**
	 * 超出查询次数限制(日查询次数<=3万)
	 */
	EXCEEDED_QUERY_LIMIT(405, "超出查询次数限制(日查询次数<=3万)");
	
	private Integer value;
	
	private String label;
	
	CodeEnum(int value, String label){
		this.value = value;
		this.label = label;
	}
	
	public Integer getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
	
	public static CodeEnum getEnum(Integer value){
		for (CodeEnum item : CodeEnum.values()) {
			if (item.getValue().equals(value)) {
				return item;
			}
		}
		return null;
	}
}
