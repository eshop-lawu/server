package com.lawu.eshop.jobs.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.jobs.service.AdSrvService;
import com.lawu.eshop.jobs.service.PropertySrvService;
import com.lawu.eshop.jobs.service.ReportEarningExtendService;
import com.lawu.eshop.jobs.service.ReportEarningService;
import com.lawu.eshop.statistics.dto.ReportEarningsDailyDTO;
import com.lawu.eshop.statistics.param.ReportEarningParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.utils.DateUtil;

@Service
public class ReportEarningServiceImpl extends BaseController implements ReportEarningExtendService {

	private static Logger logger = LoggerFactory.getLogger(ReportEarningServiceImpl.class);

	@Autowired
	private AdSrvService adSrvService;

	@Autowired
	private ReportEarningService reportEarningService;

	@Autowired
	private PropertySrvService propertySrvService;

	/*@Override
	public void executeCollectDailyData() {
		Date lastDay = reportEarningService.getDaily();
		if (lastDay != null && !DateUtils.isSameDay(lastDay, DateUtil.getSomeDay(new Date(), -1))) {
			int betweenDay = DateUtil.daysOfTwo(lastDay);
			for (int i = 1; i < betweenDay; i++) {
				String today = DateUtil.getDateFormat(DateUtil.getSomeDay(lastDay, i), "yyyy-MM-dd");
				ReportEarningParam param = new ReportEarningParam();
				Result<List<ReportAdEarningsDTO>> adPointResult = adSrvService.getReportEarnings(today);
				if (isSuccess(adPointResult)) {
					List<ReportAdEarningsDTO> list = adPointResult.getModel();
					BigDecimal adTotlePoint = new BigDecimal("0");
					BigDecimal userTotlePoint = new BigDecimal("0");
					BigDecimal loveTotlePoint = new BigDecimal("0");
					for (ReportAdEarningsDTO reportEarningsDTO : list) {
						adTotlePoint = adTotlePoint.add(reportEarningsDTO.getAdPoint());
						Result<ReportEarningsDTO> result = propertySrvService.getReportEarnings(reportEarningsDTO.getId());
						ReportEarningsDTO dto = result.getModel();
						userTotlePoint = userTotlePoint.add(dto.getUserPoint());
						loveTotlePoint = loveTotlePoint.add(dto.getLovaPoint());
					}
					param.setGmtReport(DateUtil.formatDate(today, "yyyy-MM-dd"));
					param.setAdPoint(adTotlePoint);
					param.setLovePoint(loveTotlePoint);
					param.setUserPoint(userTotlePoint);
					param.setPlatformPoint(adTotlePoint.subtract(loveTotlePoint.add(userTotlePoint)));
				}
				reportEarningService.saveDaily(param);
			}
		} else {
			String today = DateUtil.getDateFormat(DateUtil.getDayBefore(new Date()), "yyyy-MM-dd");
			ReportEarningParam param = new ReportEarningParam();
			Result<List<ReportAdEarningsDTO>> adPointResult = adSrvService.getReportEarnings(today);
			if (isSuccess(adPointResult)) {
				List<ReportAdEarningsDTO> list = adPointResult.getModel();
				BigDecimal adTotlePoint = new BigDecimal("0");
				BigDecimal userTotlePoint = new BigDecimal("0");
				BigDecimal loveTotlePoint = new BigDecimal("0");
				for (ReportAdEarningsDTO reportEarningsDTO : list) {
					adTotlePoint = adTotlePoint.add(reportEarningsDTO.getAdPoint());
					Result<ReportEarningsDTO> result = propertySrvService.getReportEarnings(reportEarningsDTO.getId());
					ReportEarningsDTO dto = result.getModel();
					userTotlePoint = userTotlePoint.add(dto.getUserPoint());
					loveTotlePoint = loveTotlePoint.add(dto.getLovaPoint());
				}
				param.setGmtReport(DateUtil.formatDate(today, "yyyy-MM-dd"));
				param.setAdPoint(adTotlePoint);
				param.setLovePoint(loveTotlePoint);
				param.setUserPoint(userTotlePoint);
				param.setPlatformPoint(adTotlePoint.subtract(loveTotlePoint.add(userTotlePoint)));
			}
			reportEarningService.saveDaily(param);
		}
	}*/

	@Override
	public void executeCollectMonthData() {
		Date lastDay = reportEarningService.getMonth();
		//如果最后一条统计的数据不是前两个月的第一天
		if(lastDay != null && !DateUtils.isSameDay(lastDay, DateUtil.getFirstDayOfMonth(DateUtil.getMonthBefore(DateUtil.getMonthBefore(new Date()))))) {
			int i = 0;
			while(true) {
				//统计lastday的后面几个月直到上个月 
				Date date = DateUtil.nextMonthFirstDate(lastDay, 1 + i);	
				if(DateUtils.isSameDay(date, DateUtil.getFirstDayOfMonth(new Date()))) {
					break;
				}
				i++;
				String month = DateUtil.getDateFormat(date,"yyyy-MM");
				Result<List<ReportEarningsDailyDTO>> result = reportEarningService.getDailyList(month);
				if (ResultCode.SUCCESS != result.getRet()) {
					logger.error("广告收益报表统计定时采集数据异常：{}", result.getMsg());
					return;
				}
				List<ReportEarningsDailyDTO> list = result.getModel();
				if (list.isEmpty()) {
					logger.info("广告收益报表统计(按日)定时采集数据srv返回空！");
				}

				BigDecimal adTotlePoint = new BigDecimal("0");
				BigDecimal userTotlePoint = new BigDecimal("0");
				BigDecimal loveTotlePoint = new BigDecimal("0");
				BigDecimal platformTotlePoint = new BigDecimal("0");

				for (ReportEarningsDailyDTO reportEarningsDailyDTO : list) {
					adTotlePoint=adTotlePoint.add(reportEarningsDailyDTO.getAdPoint());
					userTotlePoint=userTotlePoint.add(reportEarningsDailyDTO.getUserPoint());
					loveTotlePoint=loveTotlePoint.add(reportEarningsDailyDTO.getLovePoint());
					platformTotlePoint=platformTotlePoint.add(reportEarningsDailyDTO.getPlatformPoint());
				}

				ReportEarningParam param = new ReportEarningParam();
				param.setAdPoint(adTotlePoint);
				param.setLovePoint(loveTotlePoint);
				param.setUserPoint(userTotlePoint);
				param.setPlatformPoint(platformTotlePoint);
				param.setGmtReport(DateUtil.formatDate(month + "-01", "yyyy-MM-dd"));

				reportEarningService.saveMonth(param);
			}
		} else {
			String month = DateUtil.getDateFormat(DateUtil.getMonthBefore(new Date()), "yyyy-MM");
			Result<List<ReportEarningsDailyDTO>> result = reportEarningService.getDailyList(month);
			if (ResultCode.SUCCESS != result.getRet()) {
				logger.error("广告收益报表统计定时采集数据异常：{}", result.getMsg());
				return;
			}
			List<ReportEarningsDailyDTO> list = result.getModel();
			if (list.isEmpty()) {
				logger.info("广告收益报表统计(按日)定时采集数据srv返回空！");
			}

			BigDecimal adTotlePoint = new BigDecimal("0");
			BigDecimal userTotlePoint = new BigDecimal("0");
			BigDecimal loveTotlePoint = new BigDecimal("0");
			BigDecimal platformTotlePoint = new BigDecimal("0");

			for (ReportEarningsDailyDTO reportEarningsDailyDTO : list) {
				adTotlePoint=adTotlePoint.add(reportEarningsDailyDTO.getAdPoint());
				userTotlePoint=userTotlePoint.add(reportEarningsDailyDTO.getUserPoint());
				loveTotlePoint=loveTotlePoint.add(reportEarningsDailyDTO.getLovePoint());
				platformTotlePoint=platformTotlePoint.add(reportEarningsDailyDTO.getPlatformPoint());
			}

			ReportEarningParam param = new ReportEarningParam();
			param.setAdPoint(adTotlePoint);
			param.setLovePoint(loveTotlePoint);
			param.setUserPoint(userTotlePoint);
			param.setPlatformPoint(platformTotlePoint);
			param.setGmtReport(DateUtil.formatDate(month + "-01", "yyyy-MM-dd"));

			reportEarningService.saveMonth(param);
		}
	}

}
