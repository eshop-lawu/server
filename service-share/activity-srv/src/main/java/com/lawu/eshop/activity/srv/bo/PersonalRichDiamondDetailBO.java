package com.lawu.eshop.activity.srv.bo;
/** 
 * 
 * @author lihj
 * @date 2018年5月4日
 */

import java.math.BigDecimal;

import com.lawu.eshop.activity.constants.RichDiamondRecordTypeEnum;

public class PersonalRichDiamondDetailBO {
	private Long id;
	private BigDecimal diamond;
	private RichDiamondRecordTypeEnum typeEnum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getDiamond() {
		return diamond;
	}

	public void setDiamond(BigDecimal diamond) {
		this.diamond = diamond;
	}

	public RichDiamondRecordTypeEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(RichDiamondRecordTypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}

}
