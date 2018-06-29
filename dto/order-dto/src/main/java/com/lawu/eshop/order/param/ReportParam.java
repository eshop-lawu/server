package com.lawu.eshop.order.param;

import javax.validation.constraints.NotNull;

import com.lawu.eshop.order.constants.ReportFansRiseRateEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * <p>
 * Description: 粉丝数据-粉丝增长
 * </p>
 * @author Yangqh
 * @date 2017年5月2日 下午2:24:04
 *
 */
@ApiModel
public class ReportParam {

	@ApiModelProperty(name = "flag", required = true, value = "标记(DAY-日增长|MONTH-月增长)")
	@NotNull(message = "flag不能为空")
	private ReportFansRiseRateEnum flag;

	public ReportFansRiseRateEnum getFlag() {
		return flag;
	}

	public void setFlag(ReportFansRiseRateEnum flag) {
		this.flag = flag;
	}

	
}
