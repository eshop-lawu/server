package com.lawu.eshop.property.param;

import java.io.Serializable;

import com.lawu.eshop.property.constants.ConsumptionTypeEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 交易明细查询参数
 * 
 * @author Sunny
 * @date 2017/3/29
 */
@ApiModel
public class TransactionDetailQueryForMerchantParam extends AbstractPageParam implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "消费类型<br/>默认全部<br/>INCOME 收入<br/>EXPENDITURE 支出")
	private ConsumptionTypeEnum consumptionType;

	public ConsumptionTypeEnum getConsumptionType() {
		return consumptionType;
	}

	public void setConsumptionType(ConsumptionTypeEnum consumptionType) {
		this.consumptionType = consumptionType;
	}
	
}