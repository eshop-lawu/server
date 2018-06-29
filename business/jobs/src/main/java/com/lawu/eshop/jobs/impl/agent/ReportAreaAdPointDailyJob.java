package com.lawu.eshop.jobs.impl.agent;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawu.eshop.jobs.service.PropertySrvService;
import com.lawu.eshop.jobs.service.RegionService;
import com.lawu.eshop.jobs.service.StatisticsSrvService;
import com.lawu.eshop.mall.dto.RegionDTO;
import com.lawu.eshop.property.dto.ReportAdPointGroupByAreaDTO;
import com.lawu.eshop.property.param.ReportAdPointParam;
import com.lawu.eshop.statistics.dto.ReportAreaAdPointDailyDTO;
import com.lawu.eshop.statistics.param.ReportAreaAdPointDailyParams;
import com.lawu.framework.web.Result;
import com.lawu.jobsextend.AbstractPageJob;
import com.lawu.utils.DateUtil;

public class ReportAreaAdPointDailyJob extends AbstractPageJob<ReportAdPointGroupByAreaDTO> {
	
	@Autowired
	private PropertySrvService propertySrvService;
	
	@Autowired
	private StatisticsSrvService statisticsSrvService;
	
	@Autowired
	private RegionService regionService;

	@Override
	public List<ReportAdPointGroupByAreaDTO> queryPage(int offset, int pageSize) {
		Date date = DateUtil.getDayBefore(new Date());
		String bdate = DateUtil.getDateFormat(date) + " 00:00:00";
		String edate = DateUtil.getDateFormat(date) + " 23:59:59";
		//获取昨天的发广告的区域统计
		ReportAdPointParam param = new ReportAdPointParam();
		param.setBdate(bdate);
		param.setEdate(edate);
		param.setOffset(offset);
		param.setPageSize(pageSize);
		Result<List<ReportAdPointGroupByAreaDTO>>  result = propertySrvService.getReportAdPointGroupByArea(param);
		
		return result.getModel();
	}

	@Override
	public void executeSingle(ReportAdPointGroupByAreaDTO dto) {
		if(dto.getAreaId() != null) {
			//查询当天当区域是否有统计信息,有则把统计信息删除重新统计
			Date date = DateUtil.getDayBefore(new Date());
			Result<List<ReportAreaAdPointDailyDTO>> dtoResult = statisticsSrvService.selectReportAreaAdPointDaily(dto.getAreaId(), DateUtil.getDateFormat(date));
			if(dtoResult.getModel() != null && !dtoResult.getModel().isEmpty()) {
				statisticsSrvService.deleteReportAreaAdPointDaily(dtoResult.getModel().get(0).getId());
			}
			ReportAreaAdPointDailyParams reportAreaAdPointDailyParams = new ReportAreaAdPointDailyParams();
			reportAreaAdPointDailyParams.setAreaId(dto.getAreaId());
			Result<RegionDTO> regionResult = regionService.getRegion(dto.getAreaId());
			String regionPath = regionResult.getModel().getPath();
			if(regionPath.length() > 0) {
				String[] ids = regionPath.split("/");
				if(ids.length == 3) {
					reportAreaAdPointDailyParams.setProvinceId(Integer.valueOf(ids[0].toString()));
					reportAreaAdPointDailyParams.setCityId(Integer.valueOf(ids[1].toString()));
				}
			}
			reportAreaAdPointDailyParams.setReportTotalPoint(dto.getTotalPoint());
			reportAreaAdPointDailyParams.setGmtCreate(new Date());
			reportAreaAdPointDailyParams.setGmtReport(date);
			statisticsSrvService.insertReportAreaAdPointDaily(reportAreaAdPointDailyParams);
		}
		
	}

	@Override
	public boolean continueWhenSinglePageFail() {
		return false;
	}

	@Override
	public boolean isStatusData() {
		return false;
	}
}
