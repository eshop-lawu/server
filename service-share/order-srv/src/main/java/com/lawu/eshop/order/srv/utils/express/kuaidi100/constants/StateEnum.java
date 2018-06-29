package com.lawu.eshop.order.srv.utils.express.kuaidi100.constants;

import com.lawu.eshop.order.constants.ExpressInquiriesDetailStateEnum;

/**
 * 快递100物流状态码
 * 
 * @author Sunny
 * @date 2017年4月15日
 */
public enum StateEnum {

    /**
     * 在途，即货物处于运输过程中
     */
	ON_THE_WAY("0", "在途，即货物处于运输过程中", ExpressInquiriesDetailStateEnum.ON_THE_WAY),

	/**
	 * 揽件，货物已由快递公司揽收并且产生了第一条跟踪信息
	 */
	PICK_UP("1", "揽件，货物已由快递公司揽收并且产生了第一条跟踪信息", ExpressInquiriesDetailStateEnum.ON_THE_WAY),

	/**
	 * 疑难，货物寄送过程出了问题
	 */
	PROBLEM_PIECES("2", "疑难，货物寄送过程出了问题", ExpressInquiriesDetailStateEnum.PROBLEM_PIECES),

	/**
	 * 签收，收件人已签收
	 */
	SIGN_IN("3", "签收，收件人已签收", ExpressInquiriesDetailStateEnum.SIGN_IN),
	
	/**
	 * 退签，即货物由于用户拒签、超区等原因退回，而且发件人已经签收
	 */
	SIGN_OUT("4", "退签，即货物由于用户拒签、超区等原因退回，而且发件人已经签收", ExpressInquiriesDetailStateEnum.PROBLEM_PIECES),
	
	/**
	 * 派件，即快递正在进行同城派件
	 */
	SEND_PIECES("5", "派件，即快递正在进行同城派件", ExpressInquiriesDetailStateEnum.ON_THE_WAY),
	
	/**
	 * 退回，货物正处于退回发件人的途中
	 */
	RETURN("6", "退回，货物正处于退回发件人的途中", ExpressInquiriesDetailStateEnum.PROBLEM_PIECES);

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
