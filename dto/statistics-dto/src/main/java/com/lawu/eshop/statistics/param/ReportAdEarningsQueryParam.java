package com.lawu.eshop.statistics.param;

import java.util.Date;

import com.lawu.eshop.statistics.constants.AdStatusEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiParam;

public class ReportAdEarningsQueryParam  extends AbstractPageParam {
	
	@ApiParam (name="merchantNum", value = "商家编号")
	private String merchantNum;

	@ApiParam (name="adTitle", value = "广告名称")
	private String adTitle;

	@ApiParam(value = " AD_STATUS_ADD 上架 AD_STATUS_PUTING 投放中  AD_STATUS_PUTED 投放结束")
	private AdStatusEnum adStatusEnum;
	
	@ApiParam (name="beginTime", value = "统计时间开始")
	private String beginTime;
	
	@ApiParam (name="endTime", value = "统计时间结束")
	private String endTime;

	public String getMerchantNum() {
		return merchantNum;
	}

	public void setMerchantNum(String merchantNum) {
		this.merchantNum = merchantNum;
	}

	public String getAdTitle() {
		return adTitle;
	}

	public void setAdTitle(String adTitle) {
		this.adTitle = adTitle;
	}

	public AdStatusEnum getAdStatusEnum() {
		return adStatusEnum;
	}

	public void setAdStatusEnum(AdStatusEnum adStatusEnum) {
		this.adStatusEnum = adStatusEnum;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	

	

	
}
