package com.lawu.eshop.ad.srv.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.ad.dto.ReportAdEarningsDTO;
import com.lawu.eshop.ad.param.AdReportParam;
import com.lawu.eshop.ad.srv.bo.ReportEarningsBO;
import com.lawu.eshop.ad.srv.service.ReportEarningsService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@RestController
@RequestMapping(value = "reportEarnings/")
public class ReportEarningsController extends BaseController{
	
	@Autowired
	private ReportEarningsService reportEarningsService;
	
	
	@RequestMapping(value = "getReportEarnings", method = RequestMethod.POST)
    public Result<List<ReportAdEarningsDTO>> getReportEarnings(@RequestBody AdReportParam param) {
		List<ReportEarningsBO> list=reportEarningsService.getReportEarnings(param);
		List<ReportAdEarningsDTO> dtoList=new ArrayList<>();
		for (ReportEarningsBO reportEarningsBO : list) {
			
			ReportAdEarningsDTO dto=new ReportAdEarningsDTO();
			dto.setAdPoint(reportEarningsBO.getAdPoint());
			dto.setId(reportEarningsBO.getId());
			dtoList.add(dto);
			
		}
		 return  successCreated(dtoList);
    }

}
