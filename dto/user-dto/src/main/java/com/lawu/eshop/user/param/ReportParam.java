package com.lawu.eshop.user.param;

import java.io.Serializable;

import com.lawu.eshop.user.constants.ReportFansRiseRateEnum;

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
public class ReportParam implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "flag", required = true, value = "标记(DAY-日增长|MONTH-月增长)")
	private ReportFansRiseRateEnum flag;

	public ReportFansRiseRateEnum getFlag() {
		return flag;
	}

	public void setFlag(ReportFansRiseRateEnum flag) {
		this.flag = flag;
	}


}
