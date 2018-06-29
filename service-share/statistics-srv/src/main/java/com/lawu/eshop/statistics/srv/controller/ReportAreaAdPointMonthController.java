package com.lawu.eshop.statistics.srv.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.statistics.dto.ReportAreaAdPointMonthDTO;
import com.lawu.eshop.statistics.param.AgentSelectAreaAdPointParam;
import com.lawu.eshop.statistics.param.ReportAreaAdPointMonthParams;
import com.lawu.eshop.statistics.srv.bo.ReportAreaAdPointMonthBO;
import com.lawu.eshop.statistics.srv.service.ReportAreaAdPointMonthService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@RestController
@RequestMapping(value = "reportAreaAdPointMonth/")
public class ReportAreaAdPointMonthController extends BaseController {

	@Autowired
	private ReportAreaAdPointMonthService reportAreaAdPointMonthService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "insertReportAreaAdPointMonth", method = RequestMethod.POST)
	public Result insertReportAreaAdPointMonth(@RequestBody ReportAreaAdPointMonthParams param) {
		reportAreaAdPointMonthService.insertReportAreaAdPointMonth(param);
		return successCreated(ResultCode.SUCCESS);
	}
	
	@RequestMapping(value = "selectReportAreaAdPointMonth", method = RequestMethod.POST)
	public Result<List<ReportAreaAdPointMonthDTO>> selectReportAreaAdPointMonth(@RequestBody AgentSelectAreaAdPointParam param) {
		List<ReportAreaAdPointMonthBO> boList = reportAreaAdPointMonthService.selectReportAreaAdPointMonth(param);
		List<ReportAreaAdPointMonthDTO> rtnList = new ArrayList<ReportAreaAdPointMonthDTO>();
		if(boList != null && !boList.isEmpty()) {
			for(ReportAreaAdPointMonthBO BO : boList) {
				ReportAreaAdPointMonthDTO dto = new ReportAreaAdPointMonthDTO();
				dto.setAreaId(BO.getAreaId());
				dto.setCityId(BO.getCityId());
				dto.setId(BO.getId());
				dto.setProvinceId(BO.getProvinceId());
				dto.setReportTotalPoint(BO.getReportTotalPoint());
				dto.setGmtCreate(BO.getGmtCreate());
				dto.setGmtReport(BO.getGmtReport());
				rtnList.add(dto);
			}
		}
		return successCreated(rtnList);
	}
}
 