package com.lawu.eshop.statistics.srv.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.statistics.dto.ReportCommonBackDTO;
import com.lawu.eshop.statistics.param.AgentReportParam;
import com.lawu.eshop.statistics.param.AgentWithdrawCashParam;
import com.lawu.eshop.statistics.param.ReportAreaWithdrawParam;
import com.lawu.eshop.statistics.param.ReportKCommonParam;
import com.lawu.eshop.statistics.srv.bo.ReportAreaWithdrawBO;
import com.lawu.eshop.statistics.srv.bo.ReportAreaWithdrawDailyBO;
import com.lawu.eshop.statistics.srv.bo.ReportWithdrawDailyBO;
import com.lawu.eshop.statistics.srv.domain.ReportAreaWithdrawDailyDO;
import com.lawu.eshop.statistics.srv.domain.ReportAreaWithdrawDailyDOExample;
import com.lawu.eshop.statistics.srv.domain.ReportAreaWithdrawMonthDO;
import com.lawu.eshop.statistics.srv.domain.ReportWithdrawDailyDO;
import com.lawu.eshop.statistics.srv.domain.ReportWithdrawDailyDOExample;
import com.lawu.eshop.statistics.srv.domain.ReportWithdrawMonthDO;
import com.lawu.eshop.statistics.srv.domain.ReportWithdrawMonthDOExample;
import com.lawu.eshop.statistics.srv.domain.extend.ReportAreaWithdrawDOView;
import com.lawu.eshop.statistics.srv.mapper.ReportAreaWithdrawDailyDOMapper;
import com.lawu.eshop.statistics.srv.mapper.ReportAreaWithdrawMonthDOMapper;
import com.lawu.eshop.statistics.srv.mapper.ReportWithdrawDailyDOMapper;
import com.lawu.eshop.statistics.srv.mapper.ReportWithdrawMonthDOMapper;
import com.lawu.eshop.statistics.srv.mapper.extend.ReportAreaWithdrawDOMapperExtend;
import com.lawu.eshop.statistics.srv.service.WithdrawCashService;
import com.lawu.utils.DateUtil;

@Service
public class WithdrawCashServiceImpl implements WithdrawCashService {

	@Autowired
	private ReportWithdrawDailyDOMapper reportWithdrawDailyDOMapper;
	@Autowired
	private ReportWithdrawMonthDOMapper reportWithdrawMonthDOMapper;

	@Autowired
	private ReportAreaWithdrawDailyDOMapper reportAreaWithdrawDailyDOMapper;

	@Autowired
	private ReportAreaWithdrawMonthDOMapper reportAreaWithdrawMonthDOMapper;

	@Autowired
	private ReportAreaWithdrawDOMapperExtend reportAreaWithdrawDOMapperExtend;
	
	@Override
	public void saveDaily(ReportKCommonParam param) {
		ReportWithdrawDailyDO record = new ReportWithdrawDailyDO();
		record.setGmtCreate(param.getGmtCreate());
		record.setGmtReport(param.getGmtReport());
		record.setMemberMoney(param.getMemberMoney());
		record.setMerchantMoney(param.getMerchantMoney());
		record.setTotalMoney(param.getTotalMoney());
		reportWithdrawDailyDOMapper.insertSelective(record);
	}

	@Override
	public void saveMonth(ReportKCommonParam param) {
		ReportWithdrawMonthDO record = new ReportWithdrawMonthDO();
		record.setGmtCreate(param.getGmtCreate());
		record.setGmtReport(param.getGmtReport());
		record.setMemberMoney(param.getMemberMoney());
		record.setMerchantMoney(param.getMerchantMoney());
		record.setTotalMoney(param.getTotalMoney());
		reportWithdrawMonthDOMapper.insertSelective(record);
		
	}

	@Override
	public List<ReportWithdrawDailyBO> getDailyList(String reportDate) {
		ReportWithdrawDailyDOExample example = new ReportWithdrawDailyDOExample();
		Date begin = DateUtil.formatDate(reportDate+"-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
		Date end = DateUtil.getLastDayOfMonth(begin);
		example.createCriteria().andGmtReportBetween(begin, end);
		List<ReportWithdrawDailyDO> rntList = reportWithdrawDailyDOMapper.selectByExample(example);
		List<ReportWithdrawDailyBO> boList = new ArrayList<>();
		for(ReportWithdrawDailyDO rdo : rntList){
			ReportWithdrawDailyBO bo = new ReportWithdrawDailyBO();
			bo.setGmtCreate(rdo.getGmtCreate());
			bo.setGmtReport(rdo.getGmtReport());
			bo.setId(rdo.getId());
			bo.setMemberMoney(rdo.getMemberMoney());
			bo.setMerchantMoney(rdo.getMerchantMoney());
			bo.setTotalMoney(rdo.getTotalMoney());
			boList.add(bo);
		}
		return boList;
	}

	@Override
	public void deleteDailyByReportDate(String reportDate) {
		ReportWithdrawDailyDOExample example = new ReportWithdrawDailyDOExample();
		example.createCriteria().andGmtReportEqualTo(DateUtil.formatDate(reportDate, "yyyy-MM-dd"));
		reportWithdrawDailyDOMapper.deleteByExample(example);
	}

	@Override
	public void deleteMonthByReportDate(String reportDate) {
		ReportWithdrawMonthDOExample example = new ReportWithdrawMonthDOExample();
		example.createCriteria().andGmtReportEqualTo(DateUtil.formatDate(reportDate, "yyyy-MM"));
		reportWithdrawMonthDOMapper.deleteByExample(example);
	}

	@Override
	public ReportCommonBackDTO selectReport(String bdate,String edate) {
		if("".equals(bdate) || "".equals(edate)){
			bdate = DateUtil.getDateFormat(new Date(), "yyyy-MM")+"-01";
			edate = DateUtil.getDateFormat(new Date(), "yyyy-MM-dd");
		}
		ReportCommonBackDTO dto = new ReportCommonBackDTO();
		List<String> xAxisData = new ArrayList<>();
		List<BigDecimal> yAxisMemberData = new ArrayList<>();
		List<BigDecimal> yAxisMerchantData = new ArrayList<>();
		List<BigDecimal> yAxisTotalData = new ArrayList<>();
		if(bdate.length() > 7){
			ReportWithdrawDailyDOExample example = new ReportWithdrawDailyDOExample();
			Date begin = DateUtil.formatDate(bdate, "yyyy-MM-dd");
			Date end = DateUtil.formatDate(edate, "yyyy-MM-dd");
			example.createCriteria().andGmtReportBetween(begin, end);
			example.setOrderByClause(" gmt_report asc ");
			List<ReportWithdrawDailyDO> rntList = reportWithdrawDailyDOMapper.selectByExample(example);
			for(ReportWithdrawDailyDO rdo : rntList){
				String day = DateUtil.getDateFormat(rdo.getGmtReport(), "MM-dd");
				xAxisData.add(day);
				yAxisMemberData.add(rdo.getMemberMoney().setScale(2));
				yAxisMerchantData.add(rdo.getMerchantMoney().setScale(2));
				yAxisTotalData.add(rdo.getTotalMoney().setScale(2));
			}
		}else {
			ReportWithdrawMonthDOExample example = new ReportWithdrawMonthDOExample();
			Date begin = DateUtil.formatDate(bdate+"-01", "yyyy-MM-dd");
			Date endFirst = DateUtil.formatDate(edate+"-01", "yyyy-MM-dd");
			Date end = DateUtil.getLastDayOfMonth(endFirst);
			example.createCriteria().andGmtReportBetween(begin, end);
			example.setOrderByClause(" gmt_report asc ");
			List<ReportWithdrawMonthDO> rntList = reportWithdrawMonthDOMapper.selectByExample(example);
			for(ReportWithdrawMonthDO rdo : rntList){
				String day = DateUtil.getDateFormat(rdo.getGmtReport(), "yyyy-MM");
				xAxisData.add(day);
				yAxisMemberData.add(rdo.getMemberMoney().setScale(2));
				yAxisMerchantData.add(rdo.getMerchantMoney().setScale(2));
				yAxisTotalData.add(rdo.getTotalMoney().setScale(2));
			}
		}
		dto.setxAxisData(xAxisData);
		dto.setyAxisMemberData(yAxisMemberData);
		dto.setyAxisMerchantData(yAxisMerchantData);
		dto.setyAxisTotalData(yAxisTotalData);
		dto.setBdate(bdate);
		dto.setEdate(edate);
		return dto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveAgentDaily(AgentWithdrawCashParam param) {
		ReportAreaWithdrawDailyDO withdrawDailyDO = new ReportAreaWithdrawDailyDO();
		withdrawDailyDO.setCityName(param.getCityName());
		withdrawDailyDO.setGmtCreate(new Date());
		withdrawDailyDO.setCityId(param.getCityId());
		withdrawDailyDO.setMemberMoney(param.getMemberMoney());
		withdrawDailyDO.setMerchantMoney(param.getMerchantMoney());
		withdrawDailyDO.setTotalMoney(param.getTotalMoney());
		withdrawDailyDO.setGmtReport(param.getGmtReport());
		reportAreaWithdrawDailyDOMapper.insertSelective(withdrawDailyDO);
	}

	@Override
	public List<ReportAreaWithdrawDailyBO> selectReportAreaWithdrawCashList(String month, Integer cityId) {
		ReportAreaWithdrawDailyDOExample example = new ReportAreaWithdrawDailyDOExample();
		Date begin = DateUtil.formatDate(month + "-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
		Date end = DateUtil.getLastDayOfMonth(begin);
		example.createCriteria().andGmtReportBetween(begin, end).andCityIdEqualTo(cityId);
		List<ReportAreaWithdrawDailyDO> listDOS = reportAreaWithdrawDailyDOMapper.selectByExample(example);
		if (listDOS.isEmpty()) {
			return new ArrayList<>();
		}
		List<ReportAreaWithdrawDailyBO> list = new ArrayList<>();
		ReportAreaWithdrawDailyBO withdrawDailyBO;
		for (ReportAreaWithdrawDailyDO withdrawDailyDO : listDOS) {
			withdrawDailyBO = new ReportAreaWithdrawDailyBO();
			withdrawDailyBO.setId(withdrawDailyDO.getId());
			withdrawDailyBO.setGmtReport(withdrawDailyDO.getGmtReport());
			withdrawDailyBO.setMemberMoney(withdrawDailyDO.getMemberMoney());
			withdrawDailyBO.setMerchantMoney(withdrawDailyDO.getMerchantMoney());
			withdrawDailyBO.setCityId(withdrawDailyDO.getCityId());
			withdrawDailyBO.setCityName(withdrawDailyDO.getCityName());
			withdrawDailyBO.setTotalMoney(withdrawDailyDO.getTotalMoney());
			list.add(withdrawDailyBO);
		}
		return list;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveAgentMonth(AgentWithdrawCashParam param) {
		ReportAreaWithdrawMonthDO withdrawMonthDO = new ReportAreaWithdrawMonthDO();
		withdrawMonthDO.setCityName(param.getCityName());
		withdrawMonthDO.setGmtCreate(new Date());
		withdrawMonthDO.setCityId(param.getCityId());
		withdrawMonthDO.setMemberMoney(param.getMemberMoney());
		withdrawMonthDO.setMerchantMoney(param.getMerchantMoney());
		withdrawMonthDO.setTotalMoney(param.getTotalMoney());
		withdrawMonthDO.setGmtReport(param.getGmtReport());
		reportAreaWithdrawMonthDOMapper.insertSelective(withdrawMonthDO);
	}

	@Override
	public ReportAreaWithdrawBO selectAreaWithdrawDailyReport(AgentReportParam param) {
		String beginTime;
		String endTime;
		if (StringUtils.isEmpty(param.getBeginTime()) || StringUtils.isEmpty(param.getEndTime())) {
			endTime = DateUtil.getDateFormat(new Date(), "yyyy-MM-dd");
			beginTime = DateUtil.getDateFormat(new Date(), "yyyy-MM") + "-01";
		} else {
			endTime = param.getEndTime();
			beginTime = param.getBeginTime();
		}
		String[] pathArr = param.getRegionPath().split("/");
		ReportAreaWithdrawParam withdrawParam = new ReportAreaWithdrawParam();
		withdrawParam.setBeginDate(beginTime);
		withdrawParam.setEndDate(endTime);
		withdrawParam.setCityId(Integer.valueOf(pathArr[1]));
		ReportAreaWithdrawDOView withdrawDOView = reportAreaWithdrawDOMapperExtend.selectAreaWithdrawDailyReport(withdrawParam);
		ReportAreaWithdrawBO reportAreaWithdrawBO = new ReportAreaWithdrawBO();
		if (withdrawDOView == null) {
			reportAreaWithdrawBO.setMemberMoney(BigDecimal.ZERO);
			reportAreaWithdrawBO.setMerchantMoney(BigDecimal.ZERO);
			reportAreaWithdrawBO.setTotalMoney(BigDecimal.ZERO);
		} else {
			reportAreaWithdrawBO.setMemberMoney(withdrawDOView.getMemberMoney());
			reportAreaWithdrawBO.setMerchantMoney(withdrawDOView.getMerchantMoney());
			reportAreaWithdrawBO.setTotalMoney(withdrawDOView.getTotalMoney());
		}
		return reportAreaWithdrawBO;
	}

	@Override
	public ReportAreaWithdrawBO selectAreaWithdrawMonthReport(AgentReportParam param) {
		String beginDate;
		String endDate;
		if(StringUtils.isEmpty(param.getBeginTime()) || StringUtils.isEmpty(param.getEndTime())){
			endDate = DateUtil.getDateFormat(new Date(), "yyyy-MM"+"-01");
			beginDate = DateUtil.getDateFormat(new Date(), "yyyy"+"-01-01");
		}else{
			endDate = param.getEndTime()+"-01";
			beginDate = param.getBeginTime()+"-01";
		}

		String[] pathArr = param.getRegionPath().split("/");
		ReportAreaWithdrawParam withdrawParam = new ReportAreaWithdrawParam();
		withdrawParam.setBeginDate(beginDate);
		withdrawParam.setEndDate(endDate);
		withdrawParam.setCityId(Integer.valueOf(pathArr[1]));
		ReportAreaWithdrawDOView withdrawDOView = reportAreaWithdrawDOMapperExtend.selectAreaWithdrawMonthReport(withdrawParam);
		ReportAreaWithdrawBO reportAreaWithdrawBO = new ReportAreaWithdrawBO();
		if (withdrawDOView == null) {
			reportAreaWithdrawBO.setMemberMoney(BigDecimal.ZERO);
			reportAreaWithdrawBO.setTotalMoney(BigDecimal.ZERO);
			reportAreaWithdrawBO.setMerchantMoney(BigDecimal.ZERO);
		} else {
			reportAreaWithdrawBO.setMemberMoney(withdrawDOView.getMemberMoney());
			reportAreaWithdrawBO.setMerchantMoney(withdrawDOView.getMerchantMoney());
			reportAreaWithdrawBO.setTotalMoney(withdrawDOView.getTotalMoney());
		}
		return reportAreaWithdrawBO;
	}

	@Override
	public Date getLastReportWithdraw() {
		ReportWithdrawDailyDOExample example = new ReportWithdrawDailyDOExample();
		example.setOrderByClause("gmt_report desc");
		List<ReportWithdrawDailyDO> list = reportWithdrawDailyDOMapper.selectByExample(example);
		if(list != null && !list.isEmpty())
			return list.get(0).getGmtReport();
		return null;
	}

	@Override
	public Date getLastReportWithdrawMonth() {
		ReportWithdrawMonthDOExample example = new ReportWithdrawMonthDOExample();
		example.setOrderByClause("gmt_report desc");
		List<ReportWithdrawMonthDO> list = reportWithdrawMonthDOMapper.selectByExample(example);
		if(list != null && !list.isEmpty())
			return list.get(0).getGmtReport();
		return null;
	}
}
