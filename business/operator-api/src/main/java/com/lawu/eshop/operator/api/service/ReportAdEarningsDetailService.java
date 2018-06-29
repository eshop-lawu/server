package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.ad.dto.ReportAdEarningsDetailDTO;
import com.lawu.eshop.ad.param.ReportAdEarningsDetailParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

@FeignClient(value = "ad-srv")
public interface ReportAdEarningsDetailService {
	
	
	/**
	 * 广告收益详情统计
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "reportAdEarningsDetail/getReportAdEarningsDetail", method = RequestMethod.GET)
    public Result<Page<ReportAdEarningsDetailDTO>> getReportAdEarningsDetail(@RequestBody ReportAdEarningsDetailParam param);

}
