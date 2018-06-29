package com.lawu.eshop.jobs.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.jobs.service.PropertyRechargeService;
import com.lawu.eshop.jobs.service.RechargeBalanceReportService;
import com.lawu.eshop.jobs.service.StatisticsRechargeBalanceService;
import com.lawu.eshop.property.constants.PayTypeEnum;
import com.lawu.eshop.property.constants.ThirdPayStatusEnum;
import com.lawu.eshop.property.dto.RechargeReportDTO;
import com.lawu.eshop.property.param.RechargeReportParam;
import com.lawu.eshop.statistics.dto.RechargeBalanceDailyDTO;
import com.lawu.eshop.statistics.param.ReportKCommonParam;
import com.lawu.framework.web.Result;
import com.lawu.utils.DateUtil;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RechargeBalanceReportServiceImpl implements RechargeBalanceReportService {

	private static Logger logger = LoggerFactory.getLogger(RechargeBalanceReportServiceImpl.class);

	@Autowired
	private PropertyRechargeService propertyRechargeService;
	@Autowired
	private StatisticsRechargeBalanceService statisticsRechargeBalanceService;
	
	@SuppressWarnings({ "rawtypes" })
	@Override
	public void executeCollectDailyData() {
		Date lastDay = statisticsRechargeBalanceService.getLastRechargeDay();
		if(lastDay != null && !DateUtils.isSameDay(lastDay, DateUtil.getSomeDay(new Date(), -2))) {
			int betweenDay = DateUtil.daysOfTwo(lastDay);
			for(int i = 0; i < betweenDay - 1; i++) {
				String today = DateUtil.getDateFormat(DateUtil.getDayBefore(DateUtil.getSomeDay(lastDay, 2 + i)),"yyyy-MM-dd");
				statisticsRechargeBalanceService.deleteDailyByReportDate(today);
				
				RechargeReportParam param = new RechargeReportParam();
				param.setDate(today);
				param.setStatus(ThirdPayStatusEnum.SUCCESS.getVal());
				param.setRechargeType(PayTypeEnum.BALANCE.getVal());
				Result<RechargeReportDTO> rntResult = propertyRechargeService.selectWithdrawCashListByDateAndStatus(param);
				
				if(ResultCode.SUCCESS != rntResult.getRet()){
					logger.error("充值余额报表统计定时采集数据异常：{}",rntResult.getMsg());
					return;
				}
				
				RechargeReportDTO dto = rntResult.getModel();
				
				ReportKCommonParam reportWithdraw = new ReportKCommonParam();
				reportWithdraw.setGmtCreate(new Date());
				reportWithdraw.setGmtReport(DateUtil.formatDate(today, "yyyy-MM-dd"));
				reportWithdraw.setMemberMoney(dto.getMemberRechargeMoney() == null ? BigDecimal.ZERO : dto.getMemberRechargeMoney());
				reportWithdraw.setMerchantMoney(dto.getMerchantRechargeMoney() == null ? BigDecimal.ZERO : dto.getMerchantRechargeMoney());
				reportWithdraw.setTotalMoney(dto.getSumRechargeMoney() == null ? BigDecimal.ZERO : dto.getSumRechargeMoney());
				Result result = statisticsRechargeBalanceService.saveDaily(reportWithdraw);
				if(result.getRet() != ResultCode.SUCCESS){
					logger.error("充值余额报表统计时采集数据保存report_recharge_balance_daily表异常！{}",result.getMsg());
				}
			}
		} else {
			String today = DateUtil.getDateFormat(DateUtil.getDayBefore(new Date()),"yyyy-MM-dd");
			statisticsRechargeBalanceService.deleteDailyByReportDate(today);
			
			RechargeReportParam param = new RechargeReportParam();
			param.setDate(today);
			param.setStatus(ThirdPayStatusEnum.SUCCESS.getVal());
			param.setRechargeType(PayTypeEnum.BALANCE.getVal());
			Result<RechargeReportDTO> rntResult = propertyRechargeService.selectWithdrawCashListByDateAndStatus(param);
			
			if(ResultCode.SUCCESS != rntResult.getRet()){
				logger.error("充值余额报表统计定时采集数据异常：{}",rntResult.getMsg());
				return;
			}
			
			RechargeReportDTO dto = rntResult.getModel();
			
			ReportKCommonParam reportWithdraw = new ReportKCommonParam();
			reportWithdraw.setGmtCreate(new Date());
			reportWithdraw.setGmtReport(DateUtil.formatDate(today, "yyyy-MM-dd"));
			reportWithdraw.setMemberMoney(dto.getMemberRechargeMoney() == null ? BigDecimal.ZERO : dto.getMemberRechargeMoney());
			reportWithdraw.setMerchantMoney(dto.getMerchantRechargeMoney() == null ? BigDecimal.ZERO : dto.getMerchantRechargeMoney());
			reportWithdraw.setTotalMoney(dto.getSumRechargeMoney() == null ? BigDecimal.ZERO : dto.getSumRechargeMoney());
			Result result = statisticsRechargeBalanceService.saveDaily(reportWithdraw);
			if(result.getRet() != ResultCode.SUCCESS){
				logger.error("充值余额报表统计时采集数据保存report_recharge_balance_daily表异常！{}",result.getMsg());
			}
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void executeCollectMonthData() {
		Date lastDay = statisticsRechargeBalanceService.getLastRechargeMonth();
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
				//要统计的年月
				String month = DateUtil.getDateFormat(date,"yyyy-MM");
				statisticsRechargeBalanceService.deleteMonthByReportDate(month);
				
				Result<List<RechargeBalanceDailyDTO>> rntResult = statisticsRechargeBalanceService.getDailyList(month);
				List<RechargeBalanceDailyDTO> rntList = rntResult.getModel();
				if(rntList.isEmpty()){
					logger.info("充值余额报表统计(按月)定时采集数据srv返回空！");
				}
				
				BigDecimal memberMoney = new BigDecimal("0");
				BigDecimal merchantMoney = new BigDecimal("0");
				BigDecimal totalMoney = new BigDecimal("0");
				for(RechargeBalanceDailyDTO dto : rntList){
					memberMoney = memberMoney.add(dto.getMemberMoney() == null ? BigDecimal.ZERO : dto.getMemberMoney());
					merchantMoney = merchantMoney.add(dto.getMerchantMoney() == null ? BigDecimal.ZERO : dto.getMerchantMoney());
					totalMoney = totalMoney.add(dto.getTotalMoney() == null ? BigDecimal.ZERO : dto.getTotalMoney());
				}
				
				ReportKCommonParam reportWithdraw = new ReportKCommonParam();
				reportWithdraw.setGmtCreate(new Date());
				reportWithdraw.setGmtReport(DateUtil.formatDate(month+"-01", "yyyy-MM-dd"));
				reportWithdraw.setMemberMoney(memberMoney == null ? BigDecimal.ZERO : memberMoney);
				reportWithdraw.setMerchantMoney(merchantMoney == null ? BigDecimal.ZERO : merchantMoney);
				reportWithdraw.setTotalMoney(totalMoney == null ? BigDecimal.ZERO : totalMoney);
				Result result = statisticsRechargeBalanceService.saveMonth(reportWithdraw);
				if(result.getRet() != ResultCode.SUCCESS){
					logger.error("充值余额报表统计定时采集数据保存report_recharge_balance_month表异常！{}",result.getMsg());
				}
			}
		} else {
			String month = DateUtil.getDateFormat(DateUtil.getMonthBefore(new Date()),"yyyy-MM");
			statisticsRechargeBalanceService.deleteMonthByReportDate(month);
			
			Result<List<RechargeBalanceDailyDTO>> rntResult = statisticsRechargeBalanceService.getDailyList(month);
			List<RechargeBalanceDailyDTO> rntList = rntResult.getModel();
			if(rntList.isEmpty()){
				logger.info("充值余额报表统计(按月)定时采集数据srv返回空！");
			}
			
			BigDecimal memberMoney = new BigDecimal("0");
			BigDecimal merchantMoney = new BigDecimal("0");
			BigDecimal totalMoney = new BigDecimal("0");
			for(RechargeBalanceDailyDTO dto : rntList){
				memberMoney = memberMoney.add(dto.getMemberMoney() == null ? BigDecimal.ZERO : dto.getMemberMoney());
				merchantMoney = merchantMoney.add(dto.getMerchantMoney() == null ? BigDecimal.ZERO : dto.getMerchantMoney());
				totalMoney = totalMoney.add(dto.getTotalMoney() == null ? BigDecimal.ZERO : dto.getTotalMoney());
			}
			
			ReportKCommonParam reportWithdraw = new ReportKCommonParam();
			reportWithdraw.setGmtCreate(new Date());
			reportWithdraw.setGmtReport(DateUtil.formatDate(month+"-01", "yyyy-MM-dd"));
			reportWithdraw.setMemberMoney(memberMoney == null ? BigDecimal.ZERO : memberMoney);
			reportWithdraw.setMerchantMoney(merchantMoney == null ? BigDecimal.ZERO : merchantMoney);
			reportWithdraw.setTotalMoney(totalMoney == null ? BigDecimal.ZERO : totalMoney);
			Result result = statisticsRechargeBalanceService.saveMonth(reportWithdraw);
			if(result.getRet() != ResultCode.SUCCESS){
				logger.error("充值余额报表统计定时采集数据保存report_recharge_balance_month表异常！{}",result.getMsg());
			}
		}
	}
	
}
