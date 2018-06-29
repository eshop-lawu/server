package com.lawu.eshop.jobs.impl.agent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.jobs.service.PropertyRechargeService;
import com.lawu.eshop.jobs.service.StatisticsReportAreaRechargeService;
import com.lawu.eshop.property.constants.ThirdPayStatusEnum;
import com.lawu.eshop.property.dto.ReportAreaRechargeDailyDTO;
import com.lawu.eshop.property.param.AgentReportRechargeQueryParam;
import com.lawu.eshop.statistics.param.AgentReportRechargeSaveParam;
import com.lawu.framework.web.Result;
import com.lawu.jobsextend.AbstractPageJob;
import com.lawu.utils.DateUtil;

/**
 * 按地区日统计用户商家充值
 * @author yangqh
 * @date 2017/8/14.
 */
public class ReportAreaRechargeDailyJob extends AbstractPageJob<ReportAreaRechargeDailyDTO>{
    private static Logger logger = LoggerFactory.getLogger(ReportAreaRechargeDailyJob.class);
    
    @Autowired
	private PropertyRechargeService propertyRechargeService;
	@Autowired
	private StatisticsReportAreaRechargeService statisticsReportAreaRechargeService;

	
	@Override
	public List<ReportAreaRechargeDailyDTO> queryPage(int offset, int pageSize) {
		String today = DateUtil.getDateFormat(DateUtil.getDayBefore(new Date()),"yyyy-MM-dd");
		statisticsReportAreaRechargeService.deleteDailyByReportDate(today);

		AgentReportRechargeQueryParam param = new AgentReportRechargeQueryParam();
		param.setDate(today);
		param.setStatus(ThirdPayStatusEnum.SUCCESS.getVal());
		param.setChannel(TransactionPayTypeEnum.BALANCE.getVal());
		param.setOffset(offset);
		param.setPageSize(pageSize);
		Result<List<ReportAreaRechargeDailyDTO>> rtnResult = propertyRechargeService.selectAgentAreaReportRechargeListByDate(param);
		if(ResultCode.SUCCESS != rtnResult.getRet()){
			logger.error("充值报表统计(按日)定时采集数据异常：{}",rtnResult.getMsg());
		}
		List<ReportAreaRechargeDailyDTO> rntList = rtnResult.getModel();
		if(rntList.isEmpty()){
			logger.info("充值报表统计(按日)定时采集数据srv返回空！");
		}
		return rntList;
	}


	@Override
	public void executeSingle(ReportAreaRechargeDailyDTO dto) {
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
		List<AgentReportRechargeSaveParam> saveParams = new ArrayList<>();
		saveParams.add(saveParam);
		Result result = statisticsReportAreaRechargeService.saveDaily(saveParams);
		if(result.getRet() != ResultCode.SUCCESS){
			logger.error("充值报表统计(按日)采集数据保存report_area_recharge_daily表异常！");
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
