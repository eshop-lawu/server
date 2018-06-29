package com.lawu.eshop.jobs.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.jobs.service.PropertySrvService;
import com.lawu.eshop.jobs.service.ReportAreaVolumnDailyService;
import com.lawu.eshop.jobs.service.StatisticsSrvService;
import com.lawu.eshop.property.dto.TotalSalesGroupByAreaDTO;
import com.lawu.eshop.property.param.TotalSalesQueryParam;
import com.lawu.eshop.statistics.param.ReportAreaVolumnDailyParam;
import com.lawu.framework.web.Result;
import com.lawu.utils.DateUtil;

@Service
public class ReportAreaVolumnDailyServiceImpl implements ReportAreaVolumnDailyService{

	@Autowired
	private PropertySrvService propertySrvService;
	
	@Autowired
	private StatisticsSrvService statisticsSrvService;
	
	@Override
	public void executeCollectAreaVolumnDaily() {
		TotalSalesQueryParam totalSalesQueryParam = new TotalSalesQueryParam();
		totalSalesQueryParam.setDate(DateUtil.getDayBefore(new Date()));
		Result<List<TotalSalesGroupByAreaDTO>> result = propertySrvService.selectTotalSalesGroupByArea(totalSalesQueryParam);
		if(result.getModel() != null && !result.getModel().isEmpty()) {
			for(TotalSalesGroupByAreaDTO dto : result.getModel()) {
				ReportAreaVolumnDailyParam reportAreaVolumnDailyParam = new ReportAreaVolumnDailyParam();
				if(dto.getAreaId() == null) {
					continue;
				}
				reportAreaVolumnDailyParam.setAreaId(dto.getAreaId());
				reportAreaVolumnDailyParam.setCityId(dto.getCityId());
				reportAreaVolumnDailyParam.setGmtReport(DateUtil.getDayBefore(new Date()));
				reportAreaVolumnDailyParam.setProvinceId(dto.getProvinceId());
				if(dto.getPayOrderAmount() != null && dto.getPayOrderAmount().compareTo(BigDecimal.ZERO) == 1) {
					reportAreaVolumnDailyParam.setReportTotalMoney(dto.getPayOrderAmount());
					reportAreaVolumnDailyParam.setType((byte)1);
					statisticsSrvService.insertReportAreaVolumnDaily(reportAreaVolumnDailyParam);
				}
				if(dto.getShoppingOrderAmount() != null && dto.getShoppingOrderAmount().compareTo(BigDecimal.ZERO) == 1) {
					reportAreaVolumnDailyParam.setReportTotalMoney(dto.getShoppingOrderAmount());
					reportAreaVolumnDailyParam.setType((byte)2);
					statisticsSrvService.insertReportAreaVolumnDaily(reportAreaVolumnDailyParam);
				}
			}
		}
	}

}
