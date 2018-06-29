package com.lawu.eshop.statistics.srv.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.statistics.param.ReportAreaPointConsumeDailyParam;
import com.lawu.eshop.statistics.srv.bo.ReportAreaPointConsumeDailyBO;
import com.lawu.eshop.statistics.srv.bo.ReportAreaPointConsumeDailyInMonthBO;
import com.lawu.eshop.statistics.srv.domain.ReportAreaPointConsumeDailyDO;
import com.lawu.eshop.statistics.srv.domain.ReportAreaPointConsumeDailyDOExample;
import com.lawu.eshop.statistics.srv.domain.extend.ReportAreaPointConsumeDailyInMonthDOView;
import com.lawu.eshop.statistics.srv.mapper.ReportAreaPointConsumeDailyDOMapper;
import com.lawu.eshop.statistics.srv.mapper.extend.ReportAreaPointConsumeMonthDOMapperExtend;
import com.lawu.eshop.statistics.srv.service.ReportAreaPointConsumeDailyService;
import com.lawu.utils.DateUtil;

@Service
public class ReportAreaPointConsumeDailyServiceImpl implements ReportAreaPointConsumeDailyService{

	@Autowired
	private ReportAreaPointConsumeDailyDOMapper reportAreaPointConsumeDailyMapper;
	
	@Autowired
	private ReportAreaPointConsumeMonthDOMapperExtend reportAreaPointConsumeMonthDOMapperExtend;
	
	@Override
	public int insertReportAreaPointConsumeDaily(ReportAreaPointConsumeDailyParam param) {
		ReportAreaPointConsumeDailyDOExample example = new ReportAreaPointConsumeDailyDOExample();
		example.createCriteria().andAreaIdEqualTo(param.getAreaId()).andGmtReportEqualTo(param.getGmtReport());
		reportAreaPointConsumeDailyMapper.deleteByExample(example);
		ReportAreaPointConsumeDailyDO reportAreaPointConsumeDaily = new ReportAreaPointConsumeDailyDO();
		reportAreaPointConsumeDaily.setAreaId(param.getAreaId());
		reportAreaPointConsumeDaily.setCityId(param.getCityId());
		reportAreaPointConsumeDaily.setGmtCreate(new Date());
		reportAreaPointConsumeDaily.setGmtReport(param.getGmtReport());
		reportAreaPointConsumeDaily.setMemberPoint(param.getMemberPoint());
		reportAreaPointConsumeDaily.setMemberRechargePoint(param.getMemberRechargePoint());
		reportAreaPointConsumeDaily.setMerchantPoint(param.getMerchantPoint());
		reportAreaPointConsumeDaily.setMerchantRechargePoint(param.getMerchantRechargePoint());
		reportAreaPointConsumeDaily.setProvinceId(param.getProvinceId());
		reportAreaPointConsumeDaily.setTotalPoint(param.getTotalPoint());
		reportAreaPointConsumeDaily.setTotalRechargePoint(param.getTotalRechargePoint());
		return reportAreaPointConsumeDailyMapper.insertSelective(reportAreaPointConsumeDaily);
	}

	@Override
	public List<ReportAreaPointConsumeDailyBO> getReportAreaPointConsumeDaily(Integer cityId, String bdate, String edate) {
		ReportAreaPointConsumeDailyDOExample reportAreaPointConsumeDailyExample = new ReportAreaPointConsumeDailyDOExample();
		reportAreaPointConsumeDailyExample.createCriteria().andCityIdEqualTo(cityId).andGmtReportBetween(DateUtil.getDateFormat(bdate), DateUtil.getDateFormat(edate));
		List<ReportAreaPointConsumeDailyDO> doList = reportAreaPointConsumeDailyMapper.selectByExample(reportAreaPointConsumeDailyExample);
		List<ReportAreaPointConsumeDailyBO> rtnList = new ArrayList<>();
		if(doList != null && !doList.isEmpty()) {
			for(ReportAreaPointConsumeDailyDO Do : doList) {
				ReportAreaPointConsumeDailyBO bo = new ReportAreaPointConsumeDailyBO();
				bo.setAreaId(Do.getAreaId());
				bo.setCityId(Do.getCityId());
				bo.setGmtCreate(Do.getGmtCreate());
				bo.setGmtReport(Do.getGmtReport());
				bo.setMemberPoint(Do.getMemberPoint());
				bo.setMemberRechargePoint(Do.getMemberRechargePoint());
				bo.setMerchantPoint(Do.getMerchantPoint());
				bo.setMerchantRechargePoint(Do.getMerchantRechargePoint());
				bo.setTotalPoint(Do.getTotalPoint());
				bo.setTotalRechargePoint(Do.getTotalRechargePoint());
				rtnList.add(bo);
			}
		}
		return rtnList;
	}

	@Override
	public List<ReportAreaPointConsumeDailyInMonthBO> selectReportAreaPointConsumeDailyInMonth(String bdate, String edate) {
		List<ReportAreaPointConsumeDailyInMonthDOView> viewList = reportAreaPointConsumeMonthDOMapperExtend.selectReportAreaPointConsumeDailyInMonth(bdate, edate);
		List<ReportAreaPointConsumeDailyInMonthBO> rtnList = new ArrayList<>();
		if(viewList != null && !viewList.isEmpty()) {
			for(ReportAreaPointConsumeDailyInMonthDOView view : viewList) {
				ReportAreaPointConsumeDailyInMonthBO bo = new ReportAreaPointConsumeDailyInMonthBO();
				bo.setAreaId(view.getAreaId());
				bo.setCityId(view.getCityId());
				bo.setProvinceId(view.getProvinceId());
				bo.setMemberPoint(view.getMemberPoint());
				bo.setMemberRechargePoint(view.getMemberRechargePoint());
				bo.setMerchantPoint(view.getMerchantPoint());
				bo.setMerchantRechargePoint(view.getMerchantRechargePoint());
				rtnList.add(bo);
			}
		}
		return rtnList;
	}

}
