package com.lawu.eshop.jobs.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.jobs.service.ReportSalesExtendService;
import com.lawu.eshop.jobs.service.ReportSalesService;
import com.lawu.eshop.jobs.service.TransactionDetailService;
import com.lawu.eshop.property.dto.TotalSalesDTO;
import com.lawu.eshop.property.param.TotalSalesQueryParam;
import com.lawu.eshop.statistics.constants.ReportTypeEnum;
import com.lawu.eshop.statistics.param.PlatformTotalSalesSaveParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.utils.DateUtil;

@Service
public class ReportSalesExtendServiceImpl extends BaseController implements ReportSalesExtendService {
	
	private static Logger logger = LoggerFactory.getLogger(ReportSalesExtendServiceImpl.class);
	
	@Autowired
	private ReportSalesService reportSalesService;
	
	@Autowired
	private TransactionDetailService transactionDetailService;
	
	/**
	 * 定时任务<p>
	 * 保存平台总销量记录
	 * 
	 * @author Sunny
	 * @date 2017年7月3日
	 */
	@SuppressWarnings("rawtypes")
	public void executeSave() {
		Date now = DateUtil.getNowDate();
		Date date = DateUtil.getDayBefore(now);
		TotalSalesQueryParam totalSalesQueryParam = new TotalSalesQueryParam();
		totalSalesQueryParam.setDate(date);
		Result<TotalSalesDTO> selectTotalSalesResult = transactionDetailService.selectTotalSales(totalSalesQueryParam);
		if (!isSuccess(selectTotalSalesResult)) {
			logger.error("服务调用异常", selectTotalSalesResult.getMsg());
			return;
		}
		PlatformTotalSalesSaveParam platformTotalSalesSaveParam = new PlatformTotalSalesSaveParam();
		platformTotalSalesSaveParam.setType(ReportTypeEnum.DAILY);
		platformTotalSalesSaveParam.setPayOrderAmount(selectTotalSalesResult.getModel().getPayOrderAmount());
		platformTotalSalesSaveParam.setShoppingOrderAmount(selectTotalSalesResult.getModel().getShoppingOrderAmount());
		platformTotalSalesSaveParam.setGmtReport(date);
		Result saveDayResult = reportSalesService.save(platformTotalSalesSaveParam);
		if (!isSuccess(saveDayResult)) {
			logger.error("服务调用异常", saveDayResult.getMsg());
			return;
		}
		
		// 查找当月的第一天
		Date firstDayOfMonth = DateUtil.getFirstDayOfMonth(now);
		// 如果是当月的第一天保存一条月记录
		if (firstDayOfMonth.equals(now)) {
			platformTotalSalesSaveParam = new PlatformTotalSalesSaveParam();
			platformTotalSalesSaveParam.setType(ReportTypeEnum.MONTH);
			date = DateUtil.getMonthBefore(now);
			platformTotalSalesSaveParam.setGmtReport(date);
			try {
				// 休眠2秒后执行
				Thread.sleep(2000);
				Result saveMonthResult = reportSalesService.save(platformTotalSalesSaveParam);
				if (!isSuccess(saveMonthResult)) {
					logger.error("服务调用异常", saveMonthResult.getMsg());
					return;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
