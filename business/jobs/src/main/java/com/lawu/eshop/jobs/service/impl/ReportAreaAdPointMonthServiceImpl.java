package com.lawu.eshop.jobs.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.jobs.service.ReportAreaAdPointMonthService;
import com.lawu.eshop.jobs.service.StatisticsSrvService;
import com.lawu.eshop.statistics.dto.ReportAreaAdPointMonthDTO;
import com.lawu.eshop.statistics.param.ReportAreaAdPointMonthParams;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.utils.DateUtil;

@Service
public class ReportAreaAdPointMonthServiceImpl extends BaseController implements ReportAreaAdPointMonthService {

	@Autowired
	private StatisticsSrvService statisticsSrvService;
	
	@Override
	public void executeCollectReportAreaAdPointMonth() {
		Date firstDayOfLastMonth = DateUtil.getFirstDayOfMonth(DateUtil.getMonthBefore(new Date()));
		Date lastDayOfLastMonth = DateUtil.getLastDayOfMonth(DateUtil.getMonthBefore(new Date()));
		Result<List<ReportAreaAdPointMonthDTO>> result = statisticsSrvService.selectReportAreaAdPointDailyInMonth(DateUtil.getDateFormat(firstDayOfLastMonth), DateUtil.getDateFormat(lastDayOfLastMonth));
		if(result.getModel() != null && !result.getModel().isEmpty()) {
			for(ReportAreaAdPointMonthDTO dto : result.getModel()) {
				
				
				ReportAreaAdPointMonthParams param = new ReportAreaAdPointMonthParams();
				param.setAreaId(dto.getAreaId());
				param.setCityId(dto.getCityId());
				param.setProvinceId(dto.getProvinceId());
				param.setReportTotalPoint(dto.getReportTotalPoint());
				statisticsSrvService.insertReportAreaAdPointMonth(param);
			}
		}
	}
}
