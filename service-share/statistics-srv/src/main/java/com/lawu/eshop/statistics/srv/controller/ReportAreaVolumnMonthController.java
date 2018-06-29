package com.lawu.eshop.statistics.srv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.statistics.dto.ReportAreaVolumnMonthDTO;
import com.lawu.eshop.statistics.param.ReportAreaVolumnMonthParam;
import com.lawu.eshop.statistics.srv.bo.ReportAreaVolumnMonthBO;
import com.lawu.eshop.statistics.srv.converter.ReportAreaVolumnMonthConverter;
import com.lawu.eshop.statistics.srv.service.ReportAreaVolumnMonthService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.utils.DateUtil;
/**
 * @author hongqm
 * @date 2017/8/14
 */
@RestController
@RequestMapping(value = "reportAreaVolumnMonth/")
public class ReportAreaVolumnMonthController extends BaseController {

	@Autowired
	private ReportAreaVolumnMonthService reportAreaVolumnMonthService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "insertReportAreaVolumnMonth", method = RequestMethod.POST)
    public Result insertReportAreaVolumnMonth(@RequestBody ReportAreaVolumnMonthParam param) {
		reportAreaVolumnMonthService.insertReportAreaVolumnMonth(param);
        return successCreated(ResultCode.SUCCESS);
    }
	
	
	
	@RequestMapping(value = "selectReportAreaVolumnMonth", method = RequestMethod.GET)
	Result<List<ReportAreaVolumnMonthDTO>> selectReportAreaVolumnMonth(@RequestParam("cityId")Integer cityId,@RequestParam("bdate") String bdate,@RequestParam("edate") String edate) {
		List<ReportAreaVolumnMonthBO> list = reportAreaVolumnMonthService.selectReportAreaVolumnMonth(cityId, DateUtil.getDateFormat(bdate+"-01"), DateUtil.getDateFormat(edate+"-01"));
		List<ReportAreaVolumnMonthDTO> rtnList = ReportAreaVolumnMonthConverter.converBOtoDTOList(list);
        return successGet(rtnList);
    }
}
