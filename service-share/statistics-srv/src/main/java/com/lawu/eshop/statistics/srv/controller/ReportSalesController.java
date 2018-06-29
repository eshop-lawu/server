package com.lawu.eshop.statistics.srv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.statistics.dto.ReportDataDTO;
import com.lawu.eshop.statistics.param.PlatformTotalSalesQueryParam;
import com.lawu.eshop.statistics.param.PlatformTotalSalesSaveParam;
import com.lawu.eshop.statistics.srv.bo.ReportSalesBO;
import com.lawu.eshop.statistics.srv.converter.ReportSalesConverter;
import com.lawu.eshop.statistics.srv.service.ReportSalesService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 平台总销量接口提供类
 * 
 * @author Sunny
 * @date 2017年7月3日
 */
@RestController
@RequestMapping(value = "reportSales/")
public class ReportSalesController extends BaseController {

	@Autowired
	private ReportSalesService reportSalesService;
	
	/**
	 * 保存平台总销量记录
	 * 
	 * @param param
	 * @author Sunny
	 * @date 2017年7月3日
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/", method = RequestMethod.POST)
    public Result save(@RequestBody PlatformTotalSalesSaveParam param){
		reportSalesService.save(param);
    	return successCreated();
    }
    
    /**
     * 查询平台总销量记录
     * 
     * @param param
     * @return
     * @author Sunny
     * @date 2017年7月3日
     */
	@RequestMapping(value = "list", method = RequestMethod.PUT)
    public Result<ReportDataDTO> list(@RequestBody PlatformTotalSalesQueryParam param) {
		List<ReportSalesBO> reportSalesBOList = reportSalesService.list(param);
		ReportDataDTO rtn = ReportSalesConverter.convert(reportSalesBOList, param);
    	return successGet(rtn);
    }
}
