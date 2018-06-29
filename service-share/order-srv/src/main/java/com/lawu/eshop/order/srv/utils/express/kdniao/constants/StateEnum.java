package com.lawu.eshop.order.srv.utils.express.kdniao.constants;

import com.lawu.eshop.order.constants.ExpressInquiriesDetailStateEnum;

/**
 * 物流状态码
 * 
 * @author Sunny
 * @date 2017年4月15日
 */
public enum StateEnum {

    /**
     * 无轨迹
     */
	NO_INFO("0", "无轨迹", ExpressInquiriesDetailStateEnum.NO_INFO),

	/**
	 * 在途中
	 */
	ON_THE_WAY("2", "在途中", ExpressInquiriesDetailStateEnum.ON_THE_WAY),

	/**
	 * 签收
	 */
	SIGN_IN("3", "签收", ExpressInquiriesDetailStateEnum.SIGN_IN),

	/**
	 * 问题件
	 */
	PROBLEM_PIECES("4", "问题件", ExpressInquiriesDetailStateEnum.PROBLEM_PIECES);

	private String value;
	
	private String label;
	
	private ExpressInquiriesDetailStateEnum state;
	
	StateEnum(String value, String label, ExpressInquiriesDetailStateEnum state) {
		this.value = value;
		this.label = label;
		this.state = state;
	}

	public String getValue() {
		return value;
	}
	
	public String getLabel() {
		return label;
	}
	
	public ExpressInquiriesDetailStateEnum getState() {
		return state;
	}

	public static StateEnum getEnum(String value) {
		for (StateEnum item : StateEnum.values()) {
			if (item.getValue().equals(value)) {
				return item;
			}
		}
		return null;
	}
}
