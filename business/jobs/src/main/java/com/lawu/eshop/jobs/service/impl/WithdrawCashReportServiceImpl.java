package com.lawu.eshop.jobs.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.lawu.eshop.property.dto.WithdrawCashTotalReportDTO;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.common.constants.UserCommonConstant;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.jobs.service.PropertyWithdrawCashService;
import com.lawu.eshop.jobs.service.RegionService;
import com.lawu.eshop.jobs.service.StatisticsWithdrawCashService;
import com.lawu.eshop.jobs.service.WithdrawCashReportService;
import com.lawu.eshop.mall.dto.RegionDTO;
import com.lawu.eshop.property.constants.CashStatusEnum;
import com.lawu.eshop.property.dto.WithdrawCashReportDTO;
import com.lawu.eshop.property.param.AgentWithdrawCashReportParam;
import com.lawu.eshop.property.param.WithdrawCashReportParam;
import com.lawu.eshop.statistics.dto.ReportWithdrawDailyDTO;
import com.lawu.eshop.statistics.param.AgentWithdrawCashParam;
import com.lawu.eshop.statistics.param.ReportKCommonParam;
import com.lawu.framework.web.Result;
import com.lawu.utils.DateUtil;

@Service
public class WithdrawCashReportServiceImpl implements WithdrawCashReportService {

	private static Logger logger = LoggerFactory.getLogger(WithdrawCashReportServiceImpl.class);

	@Autowired
	private PropertyWithdrawCashService propertyWithdrawCashService;
	@Autowired
	private StatisticsWithdrawCashService statisticsWithdrawCashService;

	@Autowired
	private RegionService regionService;
	
	@SuppressWarnings({ "rawtypes" })
	@Override
	public void executeCollectDailyData() {
		//查询出最后一条提现日统计的时间
		Date lastDay = statisticsWithdrawCashService.getLastReportWithdraw().getModel();
		if(lastDay != null && !DateUtils.isSameDay(lastDay, DateUtil.getSomeDay(new Date(), -2))) {
			int betweenDay = DateUtil.daysOfTwo(lastDay);
			for(int i = 0; i < betweenDay - 1; i++) {
				String today = DateUtil.getDateFormat(DateUtil.getDayBefore(DateUtil.getSomeDay(lastDay, 2 + i)),"yyyy-MM-dd");
				statisticsWithdrawCashService.deleteDailyByReportDate(today);
				WithdrawCashReportParam param = new WithdrawCashReportParam();
				param.setDate(today);
				param.setStatus(CashStatusEnum.SUCCESS.getVal());
				Result<WithdrawCashTotalReportDTO> rntResult = propertyWithdrawCashService.selectWithdrawCashTotalByDateAndStatus(param);
				
				if(ResultCode.SUCCESS != rntResult.getRet()){
					logger.error("提现报表统计定时采集数据异常：{}",rntResult.getMsg());
					return;
				}

				BigDecimal memberMoney = rntResult.getModel().getMemberCashMoney();
				BigDecimal merchantMoney = rntResult.getModel().getMerchantCashMoney();
				BigDecimal totalMoney = new BigDecimal(0);
				if(memberMoney != null){
					totalMoney = memberMoney.add(merchantMoney == null ? BigDecimal.ZERO : merchantMoney);
				}else if(merchantMoney != null){
					totalMoney = merchantMoney.add(memberMoney == null ? BigDecimal.ZERO : memberMoney);
				}

				ReportKCommonParam reportWithdraw = new ReportKCommonParam();
				reportWithdraw.setGmtCreate(new Date());
				reportWithdraw.setGmtReport(DateUtil.formatDate(today, "yyyy-MM-dd"));
				reportWithdraw.setMemberMoney(memberMoney == null ? BigDecimal.ZERO : memberMoney);
				reportWithdraw.setMerchantMoney(merchantMoney == null ? BigDecimal.ZERO : merchantMoney);
				reportWithdraw.setTotalMoney(totalMoney);
				Result result = statisticsWithdrawCashService.saveDaily(reportWithdraw);
				if(result.getRet() != ResultCode.SUCCESS){
					logger.error("提现报表统计时采集数据保存report_withdraw_daily表异常！{}",result.getMsg());
				}
			}
		} else {
			String today = DateUtil.getDateFormat(DateUtil.getDayBefore(new Date()),"yyyy-MM-dd");
			statisticsWithdrawCashService.deleteDailyByReportDate(today);
			
			WithdrawCashReportParam param = new WithdrawCashReportParam();
			param.setDate(today);
			param.setStatus(CashStatusEnum.SUCCESS.getVal());
			Result<WithdrawCashTotalReportDTO> rntResult = propertyWithdrawCashService.selectWithdrawCashTotalByDateAndStatus(param);
			
			if(ResultCode.SUCCESS != rntResult.getRet()){
				logger.error("提现报表统计定时采集数据异常：{}",rntResult.getMsg());
				return;
			}

			BigDecimal memberMoney = rntResult.getModel().getMemberCashMoney();
			BigDecimal merchantMoney = rntResult.getModel().getMerchantCashMoney();
			BigDecimal totalMoney = new BigDecimal(0);
			if(memberMoney != null){
				totalMoney = memberMoney.add(merchantMoney == null ? BigDecimal.ZERO : merchantMoney);
			}else if(merchantMoney != null){
				totalMoney = merchantMoney.add(memberMoney == null ? BigDecimal.ZERO : memberMoney);
			}

			ReportKCommonParam reportWithdraw = new ReportKCommonParam();
			reportWithdraw.setGmtCreate(new Date());
			reportWithdraw.setGmtReport(DateUtil.formatDate(today, "yyyy-MM-dd"));
			reportWithdraw.setMemberMoney(memberMoney == null ? BigDecimal.ZERO : memberMoney);
			reportWithdraw.setMerchantMoney(merchantMoney == null ? BigDecimal.ZERO : merchantMoney);
			reportWithdraw.setTotalMoney(totalMoney);
			Result result = statisticsWithdrawCashService.saveDaily(reportWithdraw);
			if(result.getRet() != ResultCode.SUCCESS){
				logger.error("提现报表统计时采集数据保存report_withdraw_daily表异常！{}",result.getMsg());
			}
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void executeCollectMonthData() {
		Date lastDay = statisticsWithdrawCashService.getLastReportWithdrawMonth().getModel();
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
				statisticsWithdrawCashService.deleteMonthByReportDate(month);
				
				Result<List<ReportWithdrawDailyDTO>> rntResult = statisticsWithdrawCashService.getDailyList(month);
				List<ReportWithdrawDailyDTO> rntList = rntResult.getModel();
				if(rntList.isEmpty()){
					logger.info("提现报表统计(按月)定时采集数据srv返回空！");
				}
				
				BigDecimal memberMoney = new BigDecimal("0");
				BigDecimal merchantMoney = new BigDecimal("0");
				BigDecimal totalMoney = new BigDecimal("0");
				for(ReportWithdrawDailyDTO dto : rntList){
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
				Result result = statisticsWithdrawCashService.saveMonth(reportWithdraw);
				if(result.getRet() != ResultCode.SUCCESS){
					logger.error("提现报表统计定时采集数据保存report_withdraw_month表异常！{}",result.getMsg());
				}
			}
		} else {
			//要统计的年月
			String month = DateUtil.getDateFormat(DateUtil.getMonthBefore(new Date()),"yyyy-MM");
			statisticsWithdrawCashService.deleteMonthByReportDate(month);
			
			Result<List<ReportWithdrawDailyDTO>> rntResult = statisticsWithdrawCashService.getDailyList(month);
			List<ReportWithdrawDailyDTO> rntList = rntResult.getModel();
			if(rntList.isEmpty()){
				logger.info("提现报表统计(按月)定时采集数据srv返回空！");
			}
			
			BigDecimal memberMoney = new BigDecimal("0");
			BigDecimal merchantMoney = new BigDecimal("0");
			BigDecimal totalMoney = new BigDecimal("0");
			for(ReportWithdrawDailyDTO dto : rntList){
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
			Result result = statisticsWithdrawCashService.saveMonth(reportWithdraw);
			if(result.getRet() != ResultCode.SUCCESS){
				logger.error("提现报表统计定时采集数据保存report_withdraw_month表异常！{}",result.getMsg());
			}
		}
	}

	@Override
	public void executeCollectAgentDailyData() {
		//查询二级城市Path

		Result<List<RegionDTO>> regionResult = regionService.getRegionLevelTwo();
		if (regionResult.getModel().isEmpty()) {
			return;
		}
		String today = DateUtil.getDateFormat(DateUtil.getDayBefore(new Date()),"yyyy-MM-dd");
		AgentWithdrawCashReportParam param = new AgentWithdrawCashReportParam();
		param.setDate(today);
		param.setStatus(CashStatusEnum.SUCCESS.getVal());
		for (RegionDTO regionDTO : regionResult.getModel()) {
			param.setCityId(regionDTO.getId());
			Result<WithdrawCashTotalReportDTO> rntResult = propertyWithdrawCashService.selectAgentWithdrawCashTotal(param);
			//存在提现记录
			BigDecimal memberMoney = rntResult.getModel().getMemberCashMoney();
			BigDecimal merchantMoney = rntResult.getModel().getMerchantCashMoney();
			BigDecimal totalMoney = new BigDecimal(0);
			if(memberMoney != null){
				totalMoney = memberMoney.add(merchantMoney == null ? BigDecimal.ZERO : merchantMoney);
			}else if(merchantMoney != null){
				totalMoney = merchantMoney.add(memberMoney == null ? BigDecimal.ZERO : memberMoney);
			}

			AgentWithdrawCashParam reportWithdraw = new AgentWithdrawCashParam();
			reportWithdraw.setGmtReport(DateUtil.formatDate(today, "yyyy-MM-dd"));
			reportWithdraw.setMemberMoney(memberMoney == null ? BigDecimal.ZERO : memberMoney);
			reportWithdraw.setMerchantMoney(merchantMoney == null ? BigDecimal.ZERO : merchantMoney);
			reportWithdraw.setTotalMoney(totalMoney);
			reportWithdraw.setCityId(regionDTO.getId());
			reportWithdraw.setCityName(regionDTO.getName());
			statisticsWithdrawCashService.saveAgentDaily(reportWithdraw);
		}
	}

	@Override
	public void executeCollectAgentMonthData() {
		//查询二级城市Path

		Result<List<RegionDTO>> regionResult = regionService.getRegionLevelTwo();
		if (regionResult.getModel().isEmpty()) {
			return;
		}
		String month = DateUtil.getDateFormat(DateUtil.getMonthBefore(new Date()),"yyyy-MM");
		for (RegionDTO regionDTO : regionResult.getModel()) {
			Result<List<ReportWithdrawDailyDTO>> rntResult = statisticsWithdrawCashService.selectReportAreaWithdrawCashList(month,regionDTO.getId());
			if(!rntResult.getModel().isEmpty()) {
				//存在提现记录统计
				BigDecimal memberMoney = new BigDecimal("0");
				BigDecimal merchantMoney = new BigDecimal("0");
				BigDecimal totalMoney = new BigDecimal("0");
				for (ReportWithdrawDailyDTO dto : rntResult.getModel()) {
					memberMoney = memberMoney.add(dto.getMemberMoney() == null ? BigDecimal.ZERO : dto.getMemberMoney());
					merchantMoney = merchantMoney.add(dto.getMerchantMoney() == null ? BigDecimal.ZERO : dto.getMerchantMoney());
					totalMoney = totalMoney.add(dto.getTotalMoney() == null ? BigDecimal.ZERO : dto.getTotalMoney());
				}

				AgentWithdrawCashParam reportWithdraw = new AgentWithdrawCashParam();
				reportWithdraw.setGmtReport(DateUtil.formatDate(month+"-01", "yyyy-MM-dd"));
				reportWithdraw.setMemberMoney(memberMoney);
				reportWithdraw.setMerchantMoney(merchantMoney);
				reportWithdraw.setTotalMoney(memberMoney.add(merchantMoney));
				reportWithdraw.setCityId(regionDTO.getId());
				reportWithdraw.setCityName(regionDTO.getName());
				statisticsWithdrawCashService.saveAgentMonth(reportWithdraw);

			}
		}

	}

}
