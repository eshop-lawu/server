package com.lawu.eshop.activity.dto;
/** 
 * 未领取E钻集合
 * @author lihj
 * @date 2018年5月4日
 */

import java.math.BigDecimal;

import com.lawu.eshop.activity.constants.RichDiamondRecordTypeEnum;

import io.swagger.annotations.ApiModelProperty;

public class PersonalRichDiamondDetailDTO {
	@ApiModelProperty(value = "E钻ID")
	private Long id;
	@ApiModelProperty(value = "E钻数量")
	private BigDecimal diamond;
	@ApiModelProperty(value = "E钻类型")
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
