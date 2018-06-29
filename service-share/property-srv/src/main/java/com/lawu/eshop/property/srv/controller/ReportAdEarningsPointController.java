package com.lawu.eshop.property.srv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.property.dto.ReportAdEarningsPointDTO;
import com.lawu.eshop.property.dto.ReportEarningsDTO;
import com.lawu.eshop.property.param.ReportAdEarningsPointParam;
import com.lawu.eshop.property.srv.bo.ReportAdEarningsPointBO;
import com.lawu.eshop.property.srv.bo.ReportEarningsBO;
import com.lawu.eshop.property.srv.service.ReportAdEarningsPointService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@RestController
@RequestMapping(value = "reportAdEarningsPoint/")
public class ReportAdEarningsPointController extends BaseController{
	
	@Autowired
	private ReportAdEarningsPointService reportAdEarningsPointService;
	
	
	@RequestMapping(value = "getReportAdEarningsPoint", method = RequestMethod.POST)
	public Result<ReportAdEarningsPointDTO> getReportAdEarningsPoint(@RequestBody ReportAdEarningsPointParam reportAdEarningsPointParam) {
		 
		ReportAdEarningsPointBO bo=reportAdEarningsPointService.getReportAdEarningsPoint(reportAdEarningsPointParam);
		ReportAdEarningsPointDTO dto=new ReportAdEarningsPointDTO();
		dto.setUserTotalPoint(bo.getUserTotalPoint());
		dto.setLoveTotalPoint(bo.getLoveTotalPoint());
		return successGet(dto);
		
	}
	
	
	@RequestMapping(value = "getReportEarnings", method = RequestMethod.GET)
	public Result<ReportEarningsDTO> getReportEarnings(@RequestParam("bizIds") List<Long> bizIds) {
		 
		ReportEarningsBO bo=reportAdEarningsPointService.getReportEarnings(bizIds);
		ReportEarningsDTO dto=new ReportEarningsDTO();
		dto.setUserPoint(bo.getUserPoint());
		dto.setLovaPoint(bo.getLovaPoint());
		
		return successGet(dto);
		
	}

}
