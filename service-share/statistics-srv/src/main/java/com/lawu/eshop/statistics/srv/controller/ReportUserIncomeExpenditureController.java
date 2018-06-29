package com.lawu.eshop.statistics.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.statistics.dto.ReportUserIncomeExpenditureDTO;
import com.lawu.eshop.statistics.param.ReportUserIncomeExpenditureQueryParam;
import com.lawu.eshop.statistics.param.ReportUserIncomeExpenditureSaveParam;
import com.lawu.eshop.statistics.param.ReportUserIncomeExpenditureSaveWrapperParam;
import com.lawu.eshop.statistics.srv.bo.ReportUserIncomeExpenditureBO;
import com.lawu.eshop.statistics.srv.converter.ReportUserIncomeExpenditureConverter;
import com.lawu.eshop.statistics.srv.service.ReportUserIncomeExpenditureService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 平台总销量接口提供类
 * 
 * @author Sunny
 * @date 2017年7月3日
 */
@RestController
@RequestMapping(value = "reportUserIncomeExpenditure/")
public class ReportUserIncomeExpenditureController extends BaseController {

	@Autowired
	private ReportUserIncomeExpenditureService reportUserIncomeExpenditureService;

	/**
	 * 保存用户支出收入记录
	 * 
	 * @param param
	 * @author Sunny
	 * @date 2017年7月3日
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST)
	public Result save(@RequestBody ReportUserIncomeExpenditureSaveParam param) {
		reportUserIncomeExpenditureService.save(param);
		return successCreated();
	}
	
	/**
	 * 批量保存用户支出收入记录
	 * 
	 * @param param
	 * @author Sunny
	 * @date 2017年7月3日
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "batch", method = RequestMethod.POST)
	public Result batchSave(@RequestBody ReportUserIncomeExpenditureSaveWrapperParam param) {
		reportUserIncomeExpenditureService.batchSave(param);
		return successCreated();
	}

	/**
	 * 查询用户支出收入记录
	 * 
	 * @param param
	 * @return
	 * @author Sunny
	 * @date 2017年7月3日
	 */
	@RequestMapping(value = "page", method = RequestMethod.PUT)
	public Result<Page<ReportUserIncomeExpenditureDTO>> page(@RequestBody ReportUserIncomeExpenditureQueryParam param) {
		Page<ReportUserIncomeExpenditureBO> reportSalesBOPage = reportUserIncomeExpenditureService.page(param);
		Page<ReportUserIncomeExpenditureDTO> rtn = ReportUserIncomeExpenditureConverter.convert(reportSalesBOPage);
		return successGet(rtn);
	}
}
