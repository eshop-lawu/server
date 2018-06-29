package com.lawu.eshop.jobs.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.jobs.service.PropertySrvService;
import com.lawu.eshop.jobs.service.RegionService;
import com.lawu.eshop.jobs.service.ReportAreaAdPointDailyService;
import com.lawu.eshop.jobs.service.StatisticsSrvService;
import com.lawu.eshop.mall.dto.RegionDTO;
import com.lawu.eshop.property.dto.ReportAdPointGroupByAreaDTO;
import com.lawu.eshop.statistics.dto.ReportAreaAdPointDailyDTO;
import com.lawu.eshop.statistics.param.ReportAreaAdPointDailyParams;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.utils.DateUtil;
@Service
public class ReportAreaAdPointDailyServiceImpl extends BaseController implements ReportAreaAdPointDailyService {

	@Autowired
	private PropertySrvService propertySrvService;
	
	@Autowired
	private StatisticsSrvService statisticsSrvService;
	
	@Autowired
	private RegionService regionService;
	
	@Override
	public void executeCollectReportAreaAdPointDaily() {
		/*Date date = DateUtil.getDayBefore(new Date());
		String bdate = DateUtil.getDateFormat(date) + " 00:00:00";
		String edate = DateUtil.getDateFormat(date) + " 23:59:59";
		//获取昨天的发广告的区域统计
		Result<List<ReportAdPointGroupByAreaDTO>>  result = propertySrvService.getReportAdPointGroupByArea(param);
		if(result.getModel() != null && !result.getModel().isEmpty()) {
			for(ReportAdPointGroupByAreaDTO dto : result.getModel()) {
				if(dto.getAreaId() == null) {
					continue;
				}
				//查询当天当区域是否有统计信息,有则把统计信息删除重新统计
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
	*/
	}

}
