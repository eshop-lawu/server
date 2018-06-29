package com.lawu.eshop.order.constants;

/**
 * @author zhangyong
 * @date 2017/4/11.
 */
public enum EvaluationEnum {
	UN_EVALUATION((boolean) false), // 未评
	EVALUATION_SUCCESS((boolean) true);// 已评
	private Boolean val;

	EvaluationEnum(Boolean val) {
		this.val = val;
	}

	public Boolean getVal() {
		return val;
	}

	public static EvaluationEnum getEnum(Boolean val) {
		EvaluationEnum[] values = EvaluationEnum.values();
		for (EvaluationEnum object : values) {
			if (object.val.equals(val)) {
				return object;
			}
		}
		return null;
	}
}
