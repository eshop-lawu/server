package com.lawu.eshop.property.srv.service;

import java.util.List;

import com.lawu.eshop.property.param.ReportAdEarningsPointParam;
import com.lawu.eshop.property.srv.bo.ReportAdEarningsPointBO;
import com.lawu.eshop.property.srv.bo.ReportEarningsBO;

public interface ReportAdEarningsPointService {
	
	ReportAdEarningsPointBO  getReportAdEarningsPoint(ReportAdEarningsPointParam ReportAdEarningsPointParam);
	
	
	ReportEarningsBO getReportEarnings(List<Long> bizIds);

}
