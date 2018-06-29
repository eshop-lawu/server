package com.lawu.eshop.jobs.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.jobs.service.ReportAreaVolumnMonthService;
import com.lawu.eshop.jobs.service.StatisticsSrvService;
import com.lawu.eshop.statistics.dto.ReportAreaVolumnDailyInMonthDTO;
import com.lawu.eshop.statistics.param.ReportAreaVolumnMonthParam;
import com.lawu.framework.web.Result;
import com.lawu.utils.DateUtil;
@Service
public class ReportAreaVolumnMonthServiceImpl implements ReportAreaVolumnMonthService{

	@Autowired
	private StatisticsSrvService statisticsSrvService;
	
	@Override
	public void executeCollectAreaVolumnMonth() {
		Date firstDayOfLastMonth = DateUtil.getFirstDayOfMonth(DateUtil.getMonthBefore(new Date()));
		Date lastDayOfLastMonth = DateUtil.getLastDayOfMonth(DateUtil.getMonthBefore(new Date()));
		Result<List<ReportAreaVolumnDailyInMonthDTO>> result = statisticsSrvService.selectReportAreaVolumeDailyInMonth(DateUtil.getDateFormat(firstDayOfLastMonth), DateUtil.getDateFormat(lastDayOfLastMonth));
		if(result.getModel() != null && !result.getModel().isEmpty()) {
			for(ReportAreaVolumnDailyInMonthDTO dto : result.getModel()) {
				ReportAreaVolumnMonthParam reportAreaVolumnMonthParam = new ReportAreaVolumnMonthParam();
				reportAreaVolumnMonthParam.setAreaId(dto.getAreaId());
				reportAreaVolumnMonthParam.setCityId(dto.getCityId());
				reportAreaVolumnMonthParam.setGmtReport(firstDayOfLastMonth);
				reportAreaVolumnMonthParam.setProvinceId(dto.getProvinceId());
				reportAreaVolumnMonthParam.setReportTotalMoney(dto.getTotalMoney());
				reportAreaVolumnMonthParam.setType(dto.getType());
				statisticsSrvService.insertReportAreaVolumnMonth(reportAreaVolumnMonthParam);
			}
		}
	}

}
