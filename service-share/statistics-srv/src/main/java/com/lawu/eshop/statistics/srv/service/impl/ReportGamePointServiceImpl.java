package com.lawu.eshop.statistics.srv.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.statistics.constants.GameTypeEnum;
import com.lawu.eshop.statistics.param.GamePointReportParam;
import com.lawu.eshop.statistics.param.ReportGamePointQuery;
import com.lawu.eshop.statistics.srv.bo.ReportGamePointBO;
import com.lawu.eshop.statistics.srv.bo.ReportTotalGamePointBO;
import com.lawu.eshop.statistics.srv.domain.ReportGamePointDailyDO;
import com.lawu.eshop.statistics.srv.domain.ReportGamePointDailyDOExample;
import com.lawu.eshop.statistics.srv.domain.extend.ReportGamePointDailyDOView;
import com.lawu.eshop.statistics.srv.domain.extend.ReportTotalGamePointView;
import com.lawu.eshop.statistics.srv.mapper.ReportGamePointDailyDOMapper;
import com.lawu.eshop.statistics.srv.mapper.extend.ReportGamePointDailyDOMapperExtend;
import com.lawu.eshop.statistics.srv.service.ReportGamePointService;
import com.lawu.utils.DateUtil;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月23日
 */
@Service
public class ReportGamePointServiceImpl implements ReportGamePointService {
	
	@Autowired
	private ReportGamePointDailyDOMapper  reportGamePointDailyDOMapper;

	@Autowired
	private ReportGamePointDailyDOMapperExtend reportGamePointDailyDOMapperExtend;

	@Override
	public Date getDaily() {
		ReportGamePointDailyDOExample example = new ReportGamePointDailyDOExample();
		example.setOrderByClause("gmt_report desc");
		List<ReportGamePointDailyDO> list = reportGamePointDailyDOMapper.selectByExample(example);
		if(list != null && !list.isEmpty())
			return list.get(0).getGmtReport();
		return null;
	}

	@Override
	public void saveReportGamePointDaily(List<GamePointReportParam> list) {
		
		for (GamePointReportParam gamePointReportParam : list) {
			ReportGamePointDailyDO record = new ReportGamePointDailyDO();
			record.setGameType(gamePointReportParam.getGameType().getVal());
			record.setStandAloneExpendPoint(gamePointReportParam.getStandAloneExpendPoint());
			record.setStandAloneIncomePoint(gamePointReportParam.getStandAloneIncomePoint());
			record.setRandomExpendPoint(gamePointReportParam.getRandomExpendPoint());
			record.setRandomIncomePoint(gamePointReportParam.getRandomIncomePoint());
			record.setManyPeopleExpendPoint(gamePointReportParam.getManyPeopleExpendPoint());
			record.setManyPeopleIncomePoint(gamePointReportParam.getManyPeopleIncomePoint());
			record.setTotalExpendPoint(gamePointReportParam.getTotalExpendPoint());
			record.setTotalIncomePoint(gamePointReportParam.getTotalIncomePoint());
			record.setGmtReport(gamePointReportParam.getGmtReport());
			record.setGmtCreate(new Date());
			reportGamePointDailyDOMapper.insertSelective(record);
		}
		
	}
	
	
	@Override
	public ReportGamePointBO selectReport(ReportGamePointQuery query) {
		String bdate = query.getBdate();
		String edate =query.getEdate();
		
		if("".equals(query.getBdate()) || "".equals(query.getEdate())){
			bdate = DateUtil.getDateFormat(new Date(), "yyyy-MM")+"-01";
			edate = DateUtil.getDateFormat(new Date(), "yyyy-MM-dd");
		}
		
		List<String> xAxisData = new ArrayList<>();
		List<BigDecimal> yAxisStandAloneExpendPointData = new ArrayList<>();
		List<BigDecimal> yAxisStandAloneIncomePointData = new ArrayList<>();
		List<BigDecimal> yAxisRandomExpendPointData = new ArrayList<>();
		List<BigDecimal> yAxisRandomIncomePointData = new ArrayList<>();
		List<BigDecimal> yAxisManyPeopleExpendPointData = new ArrayList<>();
		List<BigDecimal> yAxisManyPeopleIncomePointData = new ArrayList<>();
		List<BigDecimal> totalIncomePointData = new ArrayList<>();
		
		ReportGamePointDailyDOExample example = new ReportGamePointDailyDOExample();
		Date begin = DateUtil.formatDate(bdate, "yyyy-MM-dd");
		Date end = DateUtil.formatDate(edate, "yyyy-MM-dd");
		example.createCriteria().andGmtReportBetween(begin, end).andGameTypeEqualTo(query.getTypeEnum().getVal());
		example.setOrderByClause(" gmt_report asc ");
		List<ReportGamePointDailyDO> rntList = reportGamePointDailyDOMapper.selectByExample(example);
		for(ReportGamePointDailyDO rdo : rntList){
			String day = DateUtil.getDateFormat(rdo.getGmtReport(), "MM-dd");
			xAxisData.add(day);
			yAxisStandAloneExpendPointData.add(rdo.getStandAloneExpendPoint());
			yAxisStandAloneIncomePointData.add(rdo.getStandAloneIncomePoint());
			yAxisRandomExpendPointData.add(rdo.getRandomExpendPoint());
			yAxisRandomIncomePointData.add(rdo.getRandomIncomePoint());
			yAxisManyPeopleExpendPointData.add(rdo.getManyPeopleExpendPoint());
			yAxisManyPeopleIncomePointData.add(rdo.getManyPeopleIncomePoint());
			totalIncomePointData.add(rdo.getTotalIncomePoint());
		}
		
		ReportGamePointBO bo=new ReportGamePointBO();
		bo.setxAxisData(xAxisData);
		bo.setyAxisStandAloneExpendPointData(yAxisStandAloneExpendPointData);
		bo.setyAxisStandAloneIncomePointData(yAxisStandAloneIncomePointData);
		bo.setyAxisRandomExpendPointData(yAxisRandomExpendPointData);
		bo.setyAxisRandomIncomePointData(yAxisRandomIncomePointData);
		bo.setyAxisManyPeopleExpendPointData(yAxisManyPeopleExpendPointData);
		bo.setyAxisManyPeopleIncomePointData(yAxisManyPeopleIncomePointData);
		bo.setTotalIncomePointData(totalIncomePointData);
		bo.setBdate(bdate);
		bo.setEdate(edate);
		return bo;
	}

	@Override
	public ReportGamePointDailyDOView getTotalGamePoint(GameTypeEnum param) {

		return reportGamePointDailyDOMapperExtend.getTotalGamePoint(param.getVal());
	}
	
	@Override
	public ReportTotalGamePointBO selectTotalReport(GameTypeEnum typeEnum) {
		ReportTotalGamePointView view = reportGamePointDailyDOMapperExtend.selectTotalReport(typeEnum.getVal());
		
		ReportTotalGamePointBO bo = new ReportTotalGamePointBO();
		bo.setyAxisStandAloneExpendPointData(view.getyAxisStandAloneExpendPointData());
		bo.setyAxisStandAloneIncomePointData(view.getyAxisStandAloneIncomePointData());
		bo.setyAxisRandomExpendPointData(view.getyAxisRandomExpendPointData());
		bo.setyAxisRandomIncomePointData(view.getyAxisRandomIncomePointData());
		bo.setyAxisManyPeopleExpendPointData(view.getyAxisManyPeopleExpendPointData());
		bo.setyAxisManyPeopleIncomePointData(view.getyAxisManyPeopleIncomePointData());
		return bo;
	}
	

}