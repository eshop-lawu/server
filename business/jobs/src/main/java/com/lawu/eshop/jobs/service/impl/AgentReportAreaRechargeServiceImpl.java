package com.lawu.eshop.jobs.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.jobs.service.AgentReportAreaRechargeService;
import com.lawu.eshop.jobs.service.StatisticsReportAreaRechargeService;
import com.lawu.eshop.statistics.param.AgentReportRechargeSaveParam;
import com.lawu.framework.web.Result;
import com.lawu.utils.DateUtil;

@Service
public class AgentReportAreaRechargeServiceImpl implements AgentReportAreaRechargeService {

	private static Logger logger = LoggerFactory.getLogger(AgentReportAreaRechargeServiceImpl.class);

	@Autowired
	private StatisticsReportAreaRechargeService statisticsReportAreaRechargeService;

	@SuppressWarnings("rawtypes")
	@Override
	public void executeCollectMonthData() {
		String month = DateUtil.getDateFormat(DateUtil.getMonthBefore(new Date()),"yyyy-MM");
		statisticsReportAreaRechargeService.deleteMonthByReportDate(month);

		Result<List<com.lawu.eshop.statistics.dto.ReportAreaRechargeDailyDTO>> rtnResult = statisticsReportAreaRechargeService.getDailyList(month);
		List<com.lawu.eshop.statistics.dto.ReportAreaRechargeDailyDTO> rntList = rtnResult.getModel();
		if(rntList.isEmpty()){
			logger.info("充值报表统计(按月)定时采集数据srv返回空！");
		}
		List<AgentReportRechargeSaveParam> saveParams = new ArrayList<>();
		for(com.lawu.eshop.statistics.dto.ReportAreaRechargeDailyDTO dto : rtnResult.getModel()){
			AgentReportRechargeSaveParam saveParam = new AgentReportRechargeSaveParam();
			saveParam.setGmtCreate(dto.getGmtCreate());
			saveParam.setGmtReport(dto.getGmtReport());
			saveParam.setMemberRechargeBalance(dto.getMemberRechargeBalance());
			saveParam.setMemberRechargePoint(dto.getMemberRechargePoint());
			saveParam.setMerchantRechargeBalance(dto.getMerchantRechargeBalance());
			saveParam.setMerchantRechargePoint(dto.getMerchantRechargePoint());
			saveParam.setTotalRechargeBalance(dto.getTotalRechargeBalance());
			saveParam.setTotalRechargePoint(dto.getTotalRechargePoint());
			saveParam.setProvinceId(dto.getProvinceId());
			saveParam.setCityId(dto.getCityId());
			saveParam.setAreaId(dto.getAreaId());
			saveParams.add(saveParam);
		}
		Result result = statisticsReportAreaRechargeService.saveMonth(saveParams);
		if(result.getRet() != ResultCode.SUCCESS){
			logger.error("充值报表统计(按月)定时采集数据保存report_area_recharge_month表异常！");
		}
	}
	
}
