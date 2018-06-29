package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.statistics.dto.ReportDataDTO;
import com.lawu.eshop.statistics.param.PlatformTotalSalesQueryParam;
import com.lawu.framework.web.Result;

/**
 * 平台总销量接口服务
 * 
 * @author Sunny
 * @date 2017年7月3日
 */
@FeignClient(value = "statistics-srv", path = "reportSales")
public interface ReportSalesService {

    /**
     * 查询平台总销量记录
     * 
     * @param param
     * @return
     * @author Sunny
     * @date 2017年7月3日
     */
	@RequestMapping(value = "list", method = RequestMethod.PUT)
    Result<ReportDataDTO> list(@RequestBody PlatformTotalSalesQueryParam param);

}
